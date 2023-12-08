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
 * Servlet implementation class test1
 */
@MultipartConfig
@WebServlet("/test1")
public class test1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String realPath = request.getServletContext().getRealPath("images/song");
			Part filePath = request.getPart("file");
			String name = request.getParameter("req");
			System.out.println(name);
			try{
				String filename = Path.of(filePath.getSubmittedFileName()).getFileName().toString();
				System.out.println(realPath+"/"+filename);
				response.setContentType("application/json");
		        response.setCharacterEncoding("UTF-8");
		        response.getWriter().write("{\"path\": \"hello\"}");
		        System.out.println("co anh");
			}catch(Exception e) {
				response.setContentType("application/json");
		        response.setCharacterEncoding("UTF-8");
		        response.getWriter().write("{\"path\": \"hello\"}");
				System.out.println("ko co anh");
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
