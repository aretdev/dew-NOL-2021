package main;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class HolaMundo extends HttpServlet {
 private static final long serialVersionUID = 1L;
 public void doGet(HttpServletRequest request, HttpServletResponse response)
 throws IOException, ServletException
 {
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
 }
}