package main;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Servlet implementation class TheCat
 */
public class TheCat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TheCat() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String t = Request.get("https://api.thecatapi.com/v1/images/search").execute().returnContent().toString();

		JSONArray o = new JSONArray(t);
		JSONObject json = o.getJSONObject(0);
		String dato = json.get("url").toString();
		
		String preTituloHTML5 = "<!DOCTYPE html>\n<html>\n<head>\n"
		 + "<meta http-equiv=\"Content-type\" content=\"text/html; charset=utf-8\" />";
		 response.setContentType("text/html");
		 PrintWriter out = response.getWriter();
		 out.println(preTituloHTML5);
		 out.println("<title>TheCat</title>");
		 out.println("</head>");
		 out.println("<body>");
		 out.println("<img src=" + dato + ">");
		 out.println("</body>");
		 out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
