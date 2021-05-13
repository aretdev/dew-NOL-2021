package main;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.fluent.Response;
import org.apache.hc.core5.http.HttpHeaders;

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
    	Response requestCentro = Request.get("http://dew-virodbri-2021.dsic.cloud:9090/CentroEducativo/profesores?key="+key)
                .setHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .execute();
   		 String preTituloHTML5 = "<!DOCTYPE html>\n<html>\n<head>\n"
   		 + "<meta http-equiv=\"Content-type\" content=\"text/html; charset=utf-8\" />";
   		 response.setContentType("text/html");
   		 PrintWriter out = response.getWriter();
   		 out.println(preTituloHTML5);
   		 out.println("<title>Hola Mundo</title>");
   		 out.println("</head>");
   		 out.println("<body>");
   		 out.println("<h1>"+ses.getAttribute("dni")+"</h1>");
   		 out.println("<h1>"+ses.getAttribute("password")+"</h1>");
   		 out.println("<h1>"+ses.getAttribute("key")+"</h1>");
   		 out.println("<h1>"+requestCentro.returnContent().toString()+"</h1>");
   		 out.println("<h1>Hola Mundo!</h1>");
   		 out.println("</body>");
   		 out.println("</html>");
   		 // cambio hola
   		 // comentario
   		 // jnsadononjkdsa
   		 }

}
