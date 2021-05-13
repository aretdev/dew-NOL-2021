package main;

import java.io.BufferedReader;
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
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.fluent.Response;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.ParseException;
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
	            	
		           
		            /*Response requestCentro = Request.post("http://dew-virodbri-2021.dsic.cloud:9090/CentroEducativo/login/")
		                    .body(entity)
		                    .setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
		            String keyRes = requestCentro.returnContent().toString();
	
		            */
	            	/*
	            	 * Nueva forma que permite coger la response body con JSON que devuelve CentroEducativo
	            	 * 
	            	 * Se crea un cliente HTTP para hacer la petición
	            	 * Se crea la petición POST con HttpPost (Modo más largo)
	            	 * Se añaden los headers y la entity (es el body de la petición post)
	            	 * Usamos CloseableHttpResponse para ejecutar la petición Post con HttpClient
	            	 * 
	            	 * Con esto podemos conseguir el Code (200, 406...) y el HTML que será la key del login
	            	 * 
	            	 * */
	            	CloseableHttpClient httpclient = HttpClients.createDefault();
	                HttpPost httpPost = new HttpPost("http://dew-virodbri-2021.dsic.cloud:9090/CentroEducativo/login/");
	                httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
	                httpPost.setEntity(entity);
	                CloseableHttpResponse response1 = httpclient.execute(httpPost);
	                String keyRes = "-1";
	                    HttpEntity entity1 = response1.getEntity();
	                    try {
	                    	//Esto es un parse y puede dar una excepción
							keyRes = EntityUtils.toString(entity1);
							
	                    }catch (ParseException e) {
						}
	                    
	                    EntityUtils.consume(entity1);
	                  //Hay que cerrar la petición 
	                    response1.close();
	                    //Si el código de respuesta es 200 y se ha hecho bien el parse (nos ha dado la key)
	                    //Creamos la sesión
	                if(response1.getCode() == 200 && !keyRes.equals("-1")){
		            	session.setAttribute("dni", user);
			            session.setAttribute("password", pass);
			            session.setAttribute("key", keyRes);
		            }else {
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
