package main;

import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

import org.apache.hc.client5.http.fluent.Content;
import org.apache.hc.client5.http.fluent.Executor;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.fluent.Response;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.message.StatusLine;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Servlet Filter implementation class SessionControl
 */
public class SessionControl implements Filter {

    /**
     * Default constructor. 
     */
    public SessionControl() {
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
        
        String header = req.getHeader("Authorization");
        String user,pass;
        if (header != null && header.startsWith("Basic")) {
        	String encodedUsernamePassword = header.substring("Basic ".length()).trim();
        	byte[] decodedBytes = Base64.getDecoder().decode(encodedUsernamePassword);
        	String decodedCred = new String(decodedBytes);
        	int seperatorIndex = decodedCred.indexOf(':');
        	user = decodedCred.substring(0, seperatorIndex);
        	pass = decodedCred.substring(seperatorIndex + 1);
        } else {
        	System.err.println("Error en la auth web");
        	return;
        }
        if(session.getAttribute("key") == null) {
        	JSONObject cred = new JSONObject();
        	cred.put("dni", user);
        	cred.put("password", pass);
            StringEntity entity = new StringEntity(cred.toString());
            Response requestCentro = Request.post("http://dew-virodbri-2021.dsic.cloud:9090/CentroEducativo/login/")
                    .body(entity)
                    .setHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                    .execute();
            
            session.setAttribute("dni", user);
            session.setAttribute("password", pass);
            session.setAttribute("key", requestCentro.returnContent().toString());
            
        }
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
