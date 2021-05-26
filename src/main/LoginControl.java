package main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.json.JSONObject;




public class LoginControl implements Filter {

	File logFile;
	
    public LoginControl() {
        // TODO Auto-generated constructor stub
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		/*Logger de peticiones*/

		PrintWriter pw2 = new PrintWriter(new FileOutputStream(logFile,true));
		HttpServletRequest res = ((HttpServletRequest)request);
		pw2.println(LocalDateTime.now().toString() + " " + res.getQueryString() + " " + res.getRemoteUser() + " "  + request.getRemoteAddr() + " " + res.getServerName() + " " + res.getRequestURI() + " " + res.getMethod());
		pw2.close();
		
		/*Empieza el inicio de sesión*/
		HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(true);
	        if(session.getAttribute("key") == null) {
	            String user = req.getRemoteUser();
	            String pass = "123456";
	        	JSONObject cred = new JSONObject();
	        	cred.put("dni", user);
	        	cred.put("password", pass);
	            StringEntity entity = new StringEntity(cred.toString());
	            
	            BasicCookieStore cookieStore = new BasicCookieStore();
	        	CloseableHttpClient httpclient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
	                HttpPost httpPost = new HttpPost("http://dew-virodbri-2021.dsic.cloud:9090/CentroEducativo/login/");
	                httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
	                httpPost.setEntity(entity);
	                CloseableHttpResponse response1 = httpclient.execute(httpPost);
	                String keyRes = "-1";
	                    HttpEntity entity1 = response1.getEntity();
	                    try {
							keyRes = EntityUtils.toString(entity1);
							
	                    }catch (ParseException e) {
						}
	                    
	                    EntityUtils.consume(entity1);
	                    response1.close();
	                if(response1.getCode() == 200 && !keyRes.equals("-1")){
		            	session.setAttribute("dni", user);
			            session.setAttribute("password", pass);
			            session.setAttribute("key", keyRes);
			            session.setAttribute("cookie", cookieStore.getCookies());
	                }
	                if(req.isUserInRole("rolalu")) {
	                	req.getRequestDispatcher("/alumnoPrincipal.html").include(request, response);
	                	return;
	                }
	                else if(req.isUserInRole("rolpro")) {
	                	req.getRequestDispatcher("/profesorPrincipal.html").include(request, response);
	                	return;
	                }
	                
	                
	        }

			chain.doFilter(request, response);
        
        
	}
	
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		logFile = new File(fConfig.getInitParameter("logPath"));
		try {
			logFile.createNewFile();
		}catch(Exception e) {
			System.out.println("No se pudo crear el fichero");
		}
		
	}

}
