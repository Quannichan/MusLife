package Script;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Script.JavaMail;

/**
 * Servlet implementation class Signup
 */
@WebServlet("/Signup")
public class Signup extends HttpServlet {
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
		String usname = request.getParameter("usname");
		String mail = request.getParameter("email");
		String pass = request.getParameter("pass");
		if(usname != null && mail != null && pass != null) {
		String pass_encrypt = getMd5(pass);
		int count = 0;
		try {
			String URL = "jdbc:mysql://localhost:3306/MusLife";
			String USER = "root"; 
			String PASS = "QuanG&C13082004";
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn;
			conn = DriverManager.getConnection(URL, USER, PASS);
			Statement stmt=conn.createStatement();  
			ResultSet rs=stmt.executeQuery("Select count(user_email) from user where user_email = '"+mail+"';");  
			while(rs.next()) {
				count = rs.getInt(1);
			}
			if(count == 0) {
				System.out.print(count);
				JavaMail code = new JavaMail(mail);
				Thread.sleep(3000);
				if(code.get == true) {
					request.getSession().setAttribute("ve",code.n);
					request.getSession().setAttribute("tove","natove");
					request.getSession().setAttribute("mail",mail);
					request.getSession().setAttribute("pass",pass_encrypt);
					request.getSession().setAttribute("usname",usname);
					response.sendRedirect("Verification.jsp");
				}else{
					PrintWriter out = response.getWriter();
					out.print("SOMETHING WENT WRONG, PLEASE TRY AGAIN!");
				}
				
			}else {
				request.getRequestDispatcher("Signup.jsp").include(request, response);
				PrintWriter out = response.getWriter();
				out.print("<script> document.getElementById(\"mes3\").innerText = \"Email is in used, try other!\"; </script>");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		}else {
			String site1 = "notfound.jsp" ;
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", site1);
		}
	}
	public static String getMd5(String input)
    {
        try {
 
            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
 
            // digest() method is called to calculate message digest
            // of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());
 
            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);
 
            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
 
        // For specifying wrong message digest algorithms
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

