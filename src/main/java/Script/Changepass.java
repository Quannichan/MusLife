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

/**
 * Servlet implementation class Changepass
 */
@WebServlet("/Changepass")
public class Changepass extends HttpServlet {
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
		if(request.getSession().getAttribute("MAIL") != null && request.getSession().getAttribute("PASS") != null) {
		String get = request.getParameter("sub");
		System.out.println(get);
		if(get != null) {
		switch(get) {
			case "Next":
				String pas = request.getParameter("inp_pass");
				String pas_encrpt = getMd5(pas);
				if(pas == null) {
					request.getRequestDispatcher("Change_pass.jsp").include(request, response);
					PrintWriter out = response.getWriter();
					out.print("<script>document.getElementById(\"mes\").innerText = \"Please enter password\"</script>");
				}else if(pas_encrpt.equals(request.getSession().getAttribute("PASS"))){
					request.getSession().setAttribute("CP","true");
					response.sendRedirect("Newpass.jsp");
				}else {
					request.getRequestDispatcher("Change_pass.jsp").include(request, response);
					PrintWriter out = response.getWriter();
					out.print("<script>document.getElementById(\"mes\").innerText = \"Wrong password!\"</script>");
				}
				break;
			
			case "Forgot password?":
				JavaMail mail = new JavaMail(request.getSession().getAttribute("MAIL").toString());
				request.getSession().setAttribute("ve", mail.n);
				request.getSession().setAttribute("tove", "fptove");
				String site = "Verification.jsp" ;
				response.setStatus(response.SC_MOVED_PERMANENTLY);
				response.setHeader("Location", site);
				break;
			
			case"Change":
				String pas_change = request.getParameter("inp_pass");
				String pas_change_encrpt = getMd5(pas_change);
				if(pas_change == null) {
					request.getRequestDispatcher("Change_pass.jsp").include(request, response);
					PrintWriter out = response.getWriter();
					out.print("<script>document.getElementById(\"mes\").innerText = \"Please enter password\"</script>");
				}else {
					try {
						String URL = "jdbc:mysql://localhost:3306/MusLife";
						String USER = "root"; 
						String PASS = "QuanG&C13082004";
						Class.forName("com.mysql.jdbc.Driver");
						Connection conn;
						conn = DriverManager.getConnection(URL, USER, PASS);
						Statement stmt=conn.createStatement();  
						stmt.executeUpdate("UPDATE user set user_pass='"+pas_change_encrpt+"' WHERE user_email = '"+request.getSession().getAttribute("MAIL")+"'");
						Thread.sleep(3000);
						request.getSession().setAttribute("CP", null);
						request.getSession().setAttribute("PASS",pas_change_encrpt);
						response.sendRedirect("Home.jsp");
					}catch(Exception e) {
						
					}
				}
				break;
		}
		}else {
			String site = "notfound.jsp" ;
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", site); 
		}
		}else if(request.getSession().getAttribute("mail") != null) {
			try {
			String get = request.getParameter("sub");
				if(get.equals("Change")) {
					String pas_change = request.getParameter("inp_pass");
					String pas_change_encrpt = getMd5(pas_change);
					String URL = "jdbc:mysql://localhost:3306/MusLife";
					String USER = "root"; 
					String PASS = "QuanG&C13082004";
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn;
					conn = DriverManager.getConnection(URL, USER, PASS);
					Statement stmt=conn.createStatement();  
					stmt.executeUpdate("UPDATE user set user_pass='"+pas_change_encrpt+"' WHERE user_email = '"+request.getSession().getAttribute("mail")+"'");
					Thread.sleep(3000);
					request.getSession().setAttribute("mail", null);
					request.getSession().setAttribute("CP", null);
					String site = "Login.jsp" ;
					response.setStatus(response.SC_MOVED_TEMPORARILY);
					response.setHeader("Location", site); 
				}else {
					String site = "notfound.jsp" ;
					response.setStatus(response.SC_MOVED_TEMPORARILY);
					response.setHeader("Location", site); 
				}
			}catch(Exception e) {
				
			}			
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
