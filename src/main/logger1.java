package main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class logger1
 */
public class logger1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public logger1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		File file1 = new File("/var/log/log-NOL-dew.log");
		/*los logs normalmente se guardan en el directorio /var/log de linux! para esto
		 * ejecuta el startup con sudo sudo ./startup
		 * */
		try {
			file1.createNewFile();
		}catch(Exception e) {
			System.out.println("No se pudo crear el fichero");
		}
		PrintWriter pw2 = new PrintWriter(new FileOutputStream(new File("/var/log/log-NOL-dew.log"),true));
		pw2.println(LocalDateTime.now().toString() + " " + request.getQueryString() + " " + request.getRemoteAddr() + " " + getServletName() + " " + request.getRequestURI() + " " + request.getMethod());
		pw2.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
