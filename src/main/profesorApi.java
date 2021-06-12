package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPut;
import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.cookie.Cookie;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.json.JSONArray;

/**
 * Servlet implementation class profesorApi
 */
public class profesorApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/*Cada profesor imparte X asignaturas*/
	private HashMap<String, String>  asignaturasProfe = new HashMap<String,String>();
	private HashMap<String, HashMap<String,String>> alumnosProfe = new HashMap<String, HashMap<String,String>>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public profesorApi() {
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
		String nombreMaquina = request.getServerName();
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
    	String param = "";
    	/*
    	 * Solo aquellos con rolpro pueden realizar esta operacion
    	 * */
    	if(request.isUserInRole("rolpro")) {
    		/*
    		 * Para diferenciar las operaciones del Servlet usamos el parametro opcion
    		 * 
    		 * profasig = asignaturas del profesor
    		 * opcion = dni
    		 * */
    		param = request.getParameter("opcion");
            response.setContentType("application/json");
            
    		if(param.equals("profasig")) {
	    		httpGet = new HttpGet("http://"+nombreMaquina+":9090/CentroEducativo/profesores/"+dni+"/asignaturas?key="+key);
    		} else if(param.equals("asigalum")) {
    			String acronimo = request.getParameter("acronimo");
    			if(this.asignaturasProfe.get(dni).contains(acronimo)) {
    				httpGet = new HttpGet("http://"+nombreMaquina+":9090/CentroEducativo/asignaturas/"+acronimo+"/alumnos?key="+key);
    			}else {
    				response.sendError(403, "La asignatura no existe / no es impartida por usted!");
    			}
    		}else if(param.equals("getalumno")) {
    			String dnialumno = request.getParameter("dnialumno");
    			if(this.alumnosProfe.get(dni).get(dnialumno) != null) {
    				httpGet = new HttpGet("http://"+nombreMaquina+":9090/CentroEducativo/alumnos/"+dnialumno+"?key="+key);
    			}else {
    				response.sendError(403,"El alumno solicitado no existe / no tienes permisos para acceder a el.");
    			}
    		}else if(param.equals("dni")) {
	    		httpGet = new HttpGet("http://"+nombreMaquina+":9090/CentroEducativo/profesores/"+dni+"?key="+key);
    		} else if(param.equals("avatar")) {
    			
    			String dniparam = request.getParameter("dniavatar");
    			if(this.alumnosProfe.get(dni).get(dniparam) != null || dniparam.equals(dni)) {
    			
	    			ServletContext context = getServletContext();
	    			String pathToAvatar = context.getRealPath("/WEB-INF/img");
	    			
	    			response.setContentType("text/plain");
	    			response.setCharacterEncoding("UTF-8");
	    			BufferedReader origen = new BufferedReader(new FileReader(pathToAvatar+"/"+dniparam+".pngb64"));
	    			response.setContentType("text/plain");
	    			
	    			PrintWriter out = response.getWriter();
	    			out.print("{\"dni\": \""+dniparam+"\", \"img\": \""); 
	    			String linea = origen.readLine(); out.print(linea); 
	    			while ((linea = origen.readLine()) != null) {out.print("\n"+linea);}
	    			out.print("\"}");
	    			out.close(); origen.close();
	    			return;
    			}else {
    				response.sendError(403,"El alumno solicitado no existe / no tienes permisos para acceder a el.");
    			}
    		}else if(param.equals("setnota")) {
    			
    			String acron = request.getParameter("acron");
    			/*Si el profesor imparte la asignatura*/
    			
	    		if(this.asignaturasProfe.get(dni).contains(acron)) {
	    				
	    			Float nota = Float.parseFloat(request.getParameter("nota"));
	    			if(nota >= 0 && nota <= 10 ) {
		    			String dnialum = request.getParameter("dnialumno");
		    			HttpPut httpPut = new HttpPut("http://"+nombreMaquina+":9090/CentroEducativo/alumnos/"+dnialum+"/asignaturas/"+acron+"?key="+key);
		    			StringEntity notaChanged = new StringEntity(nota.toString());
		    			httpPut.setEntity(notaChanged);
		    			httpPut.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
		    	        cookieStore.addCookie(cookies.get(0));
		    	        
		    	        CloseableHttpResponse response1 = httpclient.execute(httpPut);	
		    	        String content = "-1";
		    	        HttpEntity entity1 = response1.getEntity();
		    	        
		    	        if(response1.getCode() == 200) {
		    	            try {
		    	            	content = EntityUtils.toString(entity1);
		    	            }catch (ParseException e) {System.out.println("Error entity");}
		    	            EntityUtils.consume(entity1);
		    	            response1.close();
		    	            response.setContentType("text/plain");
		    	    		response.getWriter().append(content);
		    	        }else {
		    	    		response.getWriter().append("No tienes permitido realizar esta accion!");
		    	        }
	    			}else {
	    				response.getWriter().append("La nota no se ha podido actualizar");
	    				response.setStatus(500);
	    			}
	    		}else {
    				response.sendError(403, "La asignatura no existe / no es impartida por usted!");
	    		}
	    			
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
	            	
	            	/*Si el profesor no está en el hashmap? y pide las asignaturas?*/
	            	if(this.asignaturasProfe.get(dni) == null && param.equals("profasig") ) {
	            		this.asignaturasProfe.put(dni, content);
	            	}
	            	if(param.equals("asigalum")) {
	            		JSONArray jsonAsigs = new JSONArray(content);
	            		if(this.alumnosProfe.get(dni) == null) {this.alumnosProfe.put(dni, new HashMap<String, String>(){{put("","");}});}
	            		HashMap<String, String> alreadyAlums = this.alumnosProfe.get(dni);
	            		for(int i = 0; i < jsonAsigs.length(); i++) {
	            			alreadyAlums.put(jsonAsigs.getJSONObject(i).getString("alumno"), "");
	            		}
	            		this.alumnosProfe.put(dni, alreadyAlums);
	            	}		
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
