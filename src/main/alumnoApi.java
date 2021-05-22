package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.cookie.Cookie;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.json.JSONArray;

/**
 * Servlet implementation class alumnoApi
 */
public class alumnoApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public alumnoApi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * Cambiar nombreMaquina a tu maquina con CentroEducativo
		 * */
		String nombreMaquina = "masanru6";
		/*
		 * Empezamos a preparar la peticion
		 * 
		 * */
		HttpSession ses = request.getSession(false);
    	BasicCookieStore cookieStore = new BasicCookieStore();
    	CloseableHttpClient httpclient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
    	
    	List<Cookie> cookies = (List<Cookie>) ses.getAttribute("cookie");
    	String dni = ses.getAttribute("dni").toString();
    	String key = ses.getAttribute("key").toString();
    	HttpGet httpGet = null;
    	/*
    	 * Solo aquellos con rolalu pueden realizar esta operacion
    	 * */
    	if(request.isUserInRole("rolalu")) {
    		/*
    		 * Para diferenciar las operaciones del Servlet usamos el parametro opcion
    		 * 
    		 * opcion = asignaturas
    		 * opcion = dni
    		 * */
    		String param = request.getParameter("opcion");
            response.setContentType("application/json");
            
    		if(param.equals("asignaturas")) {
	    		httpGet = new HttpGet("http://dew-"+nombreMaquina+"-2021.dsic.cloud:9090/CentroEducativo/alumnos/"+dni+"/asignaturas?key="+key);
    		} else if(param.equals("dni")) {
	    		httpGet = new HttpGet("http://dew-"+nombreMaquina+"-2021.dsic.cloud:9090/CentroEducativo/alumnos/"+dni+"?key="+key);
    		} else if(param.equals("avatar")) {
    			
    			ServletContext context = getServletContext();
    			String pathToAvatar = context.getRealPath("/WEB-INF/img");
    			
    			response.setContentType("text/plain");
    			response.setCharacterEncoding("UTF-8");
    			BufferedReader origen = new BufferedReader(new FileReader(pathToAvatar+"/"+dni+".pngb64"));
    			response.setContentType("text/plain");
    			
    			PrintWriter out = response.getWriter();
    			out.print("{\"dni\": \""+dni+"\", \"img\": \""); 
    			String linea = origen.readLine(); out.print(linea); 
    			while ((linea = origen.readLine()) != null) {out.print("\n"+linea);}
    			out.print("\"}");
    			out.close(); origen.close();
    			return;
    		}
    	}else {
    		response.setStatus(401);
    		response.getWriter().append("No tienes permitido realizar esta accion!");
    		return;
    	}
    	
    	if(httpGet != null) {
	    	httpGet.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
	        cookieStore.addCookie(cookies.get(0));
	        
	        CloseableHttpResponse response1 = httpclient.execute(httpGet);	
	        String content = "-1";
	        HttpEntity entity1 = response1.getEntity();
	        
	        if(response1.getCode() == 200) {
	            try {
	            	content = EntityUtils.toString(entity1);
	            }catch (ParseException e) {System.out.println("Error entity");}
	            
	            EntityUtils.consume(entity1);
	            response1.close();
	    		response.getWriter().append(content);
	        }else {
	    		response.getWriter().append("No tienes permitido realizar esta accion!");
	        }
    	}
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
