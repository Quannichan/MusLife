package Script;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;

import org.apache.catalina.connector.RequestFacade;

/**
 * Servlet implementation class Upload
 */
@MultipartConfig
@WebServlet("/Upload")
public class Upload extends HttpServlet {
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
		 // Check that we have a file upload request
		String get = request.getParameter("submit");
		System.out.println(get);
		if(get != null) {
			switch(get) {
			case "Upload":
				try {
					String realPath = request.getServletContext().getRealPath("images/backgroundimg");
					Part filePath = request.getPart("image");
					String filename = Path.of(filePath.getSubmittedFileName()).getFileName().toString();
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
							filePath.write(realPath+"/"+filename);
							String site = "Home.jsp" ;
							response.setStatus(response.SC_MOVED_PERMANENTLY);
							response.setHeader("Location", site); 
						}
					}else {
						request.getRequestDispatcher("UploadImage.jsp").include(request, response);
						PrintWriter out = response.getWriter();
						out.print("<script>document.getElementById(\"mes\").innerText=\"Please choose image!\"</script>");
					}
					
				}catch(Exception e) {
					e.printStackTrace();
				}
				break;
			case "Skip":
				try {
				request.getSession().setAttribute("upload", null); 
				request.getSession().setAttribute("US_IMG", "images/backgroundimg/basic.jpg");
				String site = "Home.jsp" ;
				response.setStatus(response.SC_MOVED_PERMANENTLY);
				response.setHeader("Location", site); 
				}catch(Exception e) {
					
				}
				break;
				
//			case "Change":
//				try {
//					String realPath = request.getServletContext().getRealPath("images/backgroundimg");
//					Part filePath = request.getPart("image");
//					String filename = Path.of(filePath.getSubmittedFileName()).getFileName().toString();
//					if(filename!= "") {
//						if(!Files.exists(Path.of(realPath))) {
////							Files.createDirectory(Path.of(realPath));
//						}else {
////							String URL = "jdbc:mysql://localhost:3306/MusLife";
////							String USER = "root"; 
////							String PASS = "QuanG&C13082004";
////							Class.forName("com.mysql.jdbc.Driver");
////							Connection conn;
////							conn = DriverManager.getConnection(URL, USER, PASS);
////							Statement stmt=conn.createStatement();
////							stmt.executeUpdate("Update user set user_img='"+"images/backgroundimg/"+filename+"' WHERE user_email='"+request.getSession().getAttribute("MAIL")+"';");
////							request.getSession().setAttribute("US_IMG", "images/backgroundimg/"+filename);
////							request.getSession().setAttribute("upload", null);
////							filePath.write(realPath+"/"+filename);
//							PrintWriter out = response.getWriter();
//							out.print(realPath+"/"+filename);
//						}
//					}else {
//						PrintWriter out = response.getWriter();
//						out.print("<script>document.getElementById(\"mes\").innerText=\"Please choose image!\"</script>");
//					}
//					
//				}catch(Exception e) {
//					e.printStackTrace();
//				}
//				break;
//				
			default:
				String site = "notfound.jsp" ;
				response.setStatus(response.SC_MOVED_PERMANENTLY);
				response.setHeader("Location", site); 
				break;
			}
		}else {
			String site = "notfound.jsp" ;
			response.setStatus(response.SC_MOVED_PERMANENTLY);
			response.setHeader("Location", site); 
		}
	}
}


