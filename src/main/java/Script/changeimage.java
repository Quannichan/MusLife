package Script;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class test1
 */
@MultipartConfig
@WebServlet("/changeimage")
public class changeimage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String get = request.getParameter("req");
			if(get != null) {
			String realPath = request.getServletContext().getRealPath("images/backgroundimg");
				switch(get) {
					case "Change":
						Part filePart = request.getPart("file");
						String filename = Path.of(filePart.getSubmittedFileName()).getFileName().toString();
						System.out.println(get);
						if(filename!= "") {
							if(!Files.exists(Path.of(realPath))) {
								Files.createDirectory(Path.of(realPath));
							}else {
								String URL = "jdbc:mysql://localhost:3306/MusLife";
								String USER = "root"; 
								String PASS = "QuanG&C13082004";
								Class.forName("com.mysql.jdbc.Driver");
								Connection conn;
								conn = DriverManager.getConnection(URL, USER, PASS);
								Statement stmt=conn.createStatement();
								stmt.executeUpdate("Update user set user_img='"+"images/backgroundimg/"+filename+"' WHERE user_email='"+request.getSession().getAttribute("MAIL")+"';");
								request.getSession().setAttribute("US_IMG", "images/backgroundimg/"+filename);
								request.getSession().setAttribute("upload", null);
								filePart.write(realPath+"/"+filename);
								Thread.sleep(3000);
								response.setContentType("application/json");
					            response.setCharacterEncoding("UTF-8");
					            response.getWriter().write("{\"path\": \"" + "images/backgroundimg/"+filename + "\"}");
							}
						}else {
							response.setContentType("application/json");
				            response.setCharacterEncoding("UTF-8");
				            response.getWriter().write("{\"message\": \"" + "please choose image" + "\"}");
						}
						
						break;
					case "Delete":
						String URL = "jdbc:mysql://localhost:3306/MusLife";
						String USER = "root"; 
						String PASS = "QuanG&C13082004";
						Class.forName("com.mysql.jdbc.Driver");
						Connection conn;
						conn = DriverManager.getConnection(URL, USER, PASS);
						Statement stmt=conn.createStatement();
						stmt.executeUpdate("Update user set user_img='images/backgroundimg/basic.jpg' WHERE user_email='"+request.getSession().getAttribute("MAIL")+"';");
						request.getSession().setAttribute("US_IMG", "images/backgroundimg/basic.jpg");
						request.getSession().setAttribute("upload", null);
						Thread.sleep(3000);
						response.setContentType("application/json");
			            response.setCharacterEncoding("UTF-8");
			            response.getWriter().write("{\"path\": \"" + "images/backgroundimg/basic.jpg\"}");
						break;
					default:
						String site = "notfound.jsp" ;
						response.setStatus(response.SC_MOVED_TEMPORARILY);
						response.setHeader("Location", site); 
						break;
				}
			}else {
				String site = "notfound.jsp" ;
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location", site); 
			}
		}catch(Exception e) {
			response.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");
		}
		
		
	}
}
