package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.cookie.Cookie;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.fluent.Response;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class HolaMundo
 */
public class HolaMundo extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpSer vlet#HttpServlet()
     */
    public HolaMundo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
   		 throws IOException, ServletException
   		 {
    	HttpSession ses = request.getSession(false);
        String key = ses.getAttribute("key").toString();
    	BasicCookieStore cookieStore = new BasicCookieStore();
    	CloseableHttpClient httpclient = HttpClients.custom()
        	    .setDefaultCookieStore(cookieStore)
        	    .build();
    	
    	List<Cookie> cookies = (List<Cookie>) ses.getAttribute("cookie");
    	String dni = ses.getAttribute("dni").toString();
    	
    	if(request.isUserInRole("rolalu")) {
    		
        HttpGet httpGet = new HttpGet("http://dew-virodbri-2021.dsic.cloud:9090/CentroEducativo/alumnos/"+dni+"/asignaturas?key="+key);
        httpGet.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        cookieStore.addCookie(cookies.get(0));
        
        CloseableHttpResponse response1 = httpclient.execute(httpGet);	
        String content = "-1";
        HttpEntity entity1 = response1.getEntity();
        try {
        	content = EntityUtils.toString(entity1);
			
        }catch (ParseException e) {
		}
        
        EntityUtils.consume(entity1);
        response1.close();
        
        JSONArray o = new JSONArray(content);

		
   		 String preTituloHTML5 = "<!DOCTYPE html>\n<html>\n<head>\n"
   		 + "<meta http-equiv=\"Content-type\" content=\"text/html; charset=utf-8\" />";
   		 response.setContentType("text/html");
   		 PrintWriter out = response.getWriter();
   		 
   		 out.println(preTituloHTML5);
   		 out.println("<title>Hola Alumno!</title>");
   		 out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\n");
   		 out.println("<link crossorigin=\"anonymous\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" rel=\"stylesheet\">\n");
   		 out.println(" <script crossorigin=\"anonymous\" integrity=\"sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1\" src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\"></script>\n");
   		 out.println("<script crossorigin=\"anonymous\" integrity=\"sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM\" src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\"></script>\n");
   		 out.println("</head>");
   		 out.println("<body>");
   		 out.println("<h1>"+ses.getAttribute("dni")+"</h1>");
   		 out.println("<h1>"+ses.getAttribute("password")+"</h1>");
   		 out.println("<h1>"+ses.getAttribute("key")+"</h1>");
   		 out.println("<div class=\"list-group w-25 p-3 \">");
   		
   		 for (int i = 0; i < o.length(); i++) {
   			JSONObject json = o.getJSONObject(i);
   			out.println("<a href=\"#\" class=\"list-group-item list-group-item-action\">"+json.get("asignatura").toString()+"</a>");
		 }
   		 out.println("</div>");
   		 out.println("<h1>Hola Mundo!</h1>");
   		 out.println("</body>");
   		 
   		 }else if(request.isUserInRole("rolpro")){
   			HttpGet httpGet = new HttpGet("http://dew-virodbri-2021.dsic.cloud:9090/CentroEducativo/profesores/"+dni+"/asignaturas?key="+key);
   	        httpGet.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
   	        cookieStore.addCookie(cookies.get(0));
   	        CloseableHttpResponse response1 = httpclient.execute(httpGet);	
	   	     String content = "-1";
	         HttpEntity entity1 = response1.getEntity();
	         try {
	         	content = EntityUtils.toString(entity1);
	 			
	         }catch (ParseException e) {
	 		}
	         
	         EntityUtils.consume(entity1);
	         response1.close();
   	   		 String preTituloHTML5 = "<!DOCTYPE html>\n<html>\n<head>\n"
   	   		 + "<meta http-equiv=\"Content-type\" content=\"text/html; charset=utf-8\" />";
   	   		 response.setContentType("text/html");
   	   		 PrintWriter out = response.getWriter();
   	   		 out.println(preTituloHTML5);
   	   		 out.println("<title>Hola Profesor</title>");
   	   		 out.println("</head>");
   	   		 out.println("<body>");
   	   		 out.println("<h1>"+ses.getAttribute("dni")+"</h1>");
   	   		 out.println("<h1>"+ses.getAttribute("password")+"</h1>");
   	   		 out.println("<h1>"+ses.getAttribute("key")+"</h1>");
   	   		 out.println("<h1>"+content+"</h1>");
   	   		 out.println("<h1>Hola Mundo!</h1>");
   	   		 out.println("</body>");
   		 }
     		
   		}
    

}
