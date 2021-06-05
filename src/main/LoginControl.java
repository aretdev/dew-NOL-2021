package main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	HashMap<String, User> usuarios = null;
    public LoginControl() {
        // TODO Auto-generated constructor stub
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		/*Logger de peticiones*/

		PrintWriter pw2 = new PrintWriter(new FileOutputStream(logFile,true));
		HttpServletRequest req = (HttpServletRequest) request;
		pw2.println(LocalDateTime.now().toString() + " " + req.getQueryString() + " " + req.getRemoteUser() + " "  + request.getRemoteAddr() + " " + req.getServerName() + " " + req.getRequestURI() + " " + req.getMethod());
		pw2.close();
		
		/*Empieza el inicio de sesión*/
		HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(true);
	        if(session.getAttribute("key") == null) {
	        	String userTomcat = req.getRemoteUser();
	            String user = usuarios.get(userTomcat).getDni();
	            String pass = usuarios.get(userTomcat).getPassword();;
	        	JSONObject cred = new JSONObject();
	        	cred.put("dni", user);
	        	cred.put("password", pass);
	            StringEntity entity = new StringEntity(cred.toString());
	            
	            BasicCookieStore cookieStore = new BasicCookieStore();
	        	CloseableHttpClient httpclient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
	                HttpPost httpPost = new HttpPost("http://dew-masanru6-2021.dsic.cloud:9090/CentroEducativo/login/");
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
	                	res.sendRedirect(req.getContextPath() + "/alumnoPrincipal.html");
	                	return;
	                }
	                else if(req.isUserInRole("rolpro")) {
	                	res.sendRedirect(req.getContextPath() + "/profesorPrincipal.html");
	                	return;
	                }
	                
	                
	        }else {
	        if(req.getRequestURI().equals(req.getContextPath() + "/index.html") || 
	        		req.getRequestURI().equals("/dew-NOL-2021/") ) {
	        	if(req.isUserInRole("rolalu")) {
                	res.sendRedirect(req.getContextPath() + "/alumnoPrincipal.html");
                	return;
                }
                else if(req.isUserInRole("rolpro")) {
                	res.sendRedirect(req.getContextPath() + "/profesorPrincipal.html");
                	return;
                }
	        }else if( (req.isUserInRole("rolalu") && req.getRequestURI().equals(req.getContextPath() + "/profesorPrincipal.html")) ||
	        		(req.isUserInRole("rolpro") && req.getRequestURI().equals(req.getContextPath() + "/alumnoPrincipal.html")) ) {
	        	if(req.isUserInRole("rolalu")) {
                	res.sendRedirect(req.getContextPath() + "/alumnoPrincipal.html");
                	return;
                }
                else if(req.isUserInRole("rolpro")) {
                	res.sendRedirect(req.getContextPath() + "/profesorPrincipal.html");
                	return;
                }
	        }else {
	        	chain.doFilter(request, response);
	        }
	     }
        
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
		usuarios = new HashMap<String, User>();
		
		//Profesores
		usuarios.put("rgarcia", new User("23456733H","123456"));
		usuarios.put("peval", new User("10293756L","123456"));
		usuarios.put("manal", new User("06374291A","123456"));
		usuarios.put("jofon", new User("65748923M","123456"));
		
		//Alumnos
		usuarios.put("pegarsan", new User("12345678W","123456"));
		usuarios.put("marfergo", new User("23456387R","123456"));
		usuarios.put("miherllo", new User("34567891F","123456"));
		usuarios.put("laubentor", new User("93847525G","123456"));
		usuarios.put("minalpe", new User("37264096W","123456"));
		
		
		logFile = new File(fConfig.getInitParameter("logPath"));
		try {
			logFile.createNewFile();
		}catch(Exception e) {
			System.out.println("No se pudo crear el fichero");
		}
		
	}

}
