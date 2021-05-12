package main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.hc.client5.http.HttpResponseException;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.fluent.Response;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.json.JSONObject;



/**
 * Servlet Filter implementation class LoginControl
 */
public class LoginControl implements Filter {

    /**
     * Default constructor. 
     */
    public LoginControl() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(true);
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");

        if(user == null || pass == null) {
        	request.getRequestDispatcher("/login3.html").include(request, response);;
        }else {
        	
	        if(session.getAttribute("key") == null) {
	        	JSONObject cred = new JSONObject();
	        	cred.put("dni", user);
	        	cred.put("password", pass);
	            StringEntity entity = new StringEntity(cred.toString());
	            try {
	            	
		           
		            Response requestCentro = Request.post("http://dew-virodbri-2021.dsic.cloud:9090/CentroEducativo/login/")
		                    .body(entity)
		                    .setHeader(HttpHeaders.CONTENT_TYPE, "application/json")
		                    .execute();
		            String keyRes = requestCentro.returnContent().toString();
	
	                if(!keyRes.equals("-1")){
		            	session.setAttribute("dni", user);
			            session.setAttribute("password", pass);
			            session.setAttribute("key", keyRes);
		            }else {
		            	request.getRequestDispatcher("/login3.html").include(request, response);
	
		            }
	            }catch(HttpResponseException e) {
	            	request.getRequestDispatcher("/login3.html").include(request, response);

	            }
	            /*HttpClient httpClient = HttpClientBuilder.create().build();

	            try {
	                HttpPost requestCentro = new HttpPost("http://dew-virodbri-2021.dsic.cloud:9090/CentroEducativo/login/");
	                StringEntity entity = new StringEntity(cred.toString());
	                requestCentro.addHeader("content-type", "application/json");
	                requestCentro.setEntity(entity);
	                HttpResponse responseCentro = httpClient.execute(requestCentro);

	            } catch (Exception ex) {
	            } finally {
	            }*/

	            
	            
	        }
			chain.doFilter(request, response);
        }
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
