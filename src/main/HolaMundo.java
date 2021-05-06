package main;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.json.JSONArray;

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
    	HttpGet httpGet = new HttpGet("http://httpbin.org/get");
    	JSONArray jArr = new JSONArray();
   		 String preTituloHTML5 = "<!DOCTYPE html>\n<html>\n<head>\n"
   		 + "<meta http-equiv=\"Content-type\" content=\"text/html; charset=utf-8\" />";
   		 response.setContentType("text/html");
   		 PrintWriter out = response.getWriter();
   		 out.println(preTituloHTML5);
   		 out.println("<title>Hola Mundo</title>");
   		 out.println("</head>");
   		 out.println("<body>");
   		 out.println("<h1>Hola Mundo!</h1>");
   		 out.println("</body>");
   		 out.println("</html>");
   		 //cambio hola
   		 }

}
