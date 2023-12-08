package Script;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String get = request.getParameter("Login");
		try {
			String URL = "jdbc:mysql://localhost:3306/MusLife";
			String USER = "root";
			String PASS = "QuanG&C13082004";
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn;
			conn = DriverManager.getConnection(URL, USER, PASS);
			Statement stmt=conn.createStatement(); 
		switch(get) {
			case "Log in":
				String mail = request.getParameter("email");
				String pass = request.getParameter("pass");
				
				if(pass != null && mail != null) {
					if(pass.equals("AdminPage2004") && mail.equals("AdminPageMusLife@gmail.com")) {
						try {
							ResultSet rs1 = stmt.executeQuery("Select user_id, user_img, user_name from user where user_email = '"+mail+"' and user_pass = '"+getMd5(pass)+"';");  
							String name = null;
							String img = null;
							int id = 0;
							while(rs1.next()) {
								id = rs1.getInt(1);
								img = rs1.getString(2);
								name = rs1.getString(3);
							}
							request.getSession().setAttribute("IDUS", id);
							request.getSession().setAttribute("US_IMG", img);
							request.getSession().setAttribute("NAME", name);
							request.getSession().setAttribute("MAIL", mail);
							request.getSession().setAttribute("PASS", getMd5(pass));
							request.getSession().setAttribute("count", 0);
							
							Thread.sleep(1000);
							response.sendRedirect("Admin.jsp");
						}catch(Exception e) {
							
						}
						
					}else {
						int count= 0;
						try {
							ResultSet rs=stmt.executeQuery("Select count(user_email) from user where user_email = '"+mail+"' and user_pass = '"+getMd5(pass)+"';");  
							while(rs.next()) {
								count = rs.getInt(1);
							}
							if(count == 0) {
								PrintWriter out = response.getWriter();
								request.getRequestDispatcher("Login.jsp").include(request, response);
						        out.print("<script>document.getElementById(\"mes\").innerHTML=\"Wrong information!\"</script>");
							}else {
								ResultSet rs1 = stmt.executeQuery("Select user_id, user_img, user_name from user where user_email = '"+mail+"' and user_pass = '"+getMd5(pass)+"';");  
								String name = null;
								String img = null;
								int id = 0;
								while(rs1.next()) {
									id = rs1.getInt(1);
									img = rs1.getString(2);
									name = rs1.getString(3);
								}
								request.getSession().setAttribute("IDUS", id);
								request.getSession().setAttribute("US_IMG", img);
								request.getSession().setAttribute("NAME", name);
								request.getSession().setAttribute("MAIL", mail);
								request.getSession().setAttribute("PASS", getMd5(pass));
								request.getSession().setAttribute("count", 0);
								Thread.sleep(2000);
								response.sendRedirect("Home.jsp");
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}						
						System.out.print(mail+" "+getMd5(pass));
					}
				
				}else {
					String site1 = "notfound.jsp" ;
					response.setStatus(response.SC_MOVED_TEMPORARILY);
					response.setHeader("Location", site1);
				}
			break;
			
			case "Forget password ?":
				String site1 = "Email.jsp" ;
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location", site1);
				break;
			
			case "Send":
				String getMail = request.getParameter("email_ver");
				if(getMail !="") {
					ResultSet rs=stmt.executeQuery("SELECT count(user_email) from user where user_email = '"+getMail+"';");
					int count = 0;
					while(rs.next()) {
						count = rs.getInt(1);
					}
					if(count == 1) {
						request.getSession().setAttribute("mail", getMail);
						JavaMail mailve = new JavaMail(getMail);
						try {
							Thread.sleep(2000);
							if(mailve.get == true) {
								request.getSession().setAttribute("tove","fptove");
								request.getSession().setAttribute("ve",mailve.n);
								String site = "Verification.jsp" ;
								response.setStatus(response.SC_MOVED_PERMANENTLY);
								response.setHeader("Location", site);
							}else {
								PrintWriter out = response.getWriter();
								request.getRequestDispatcher("Email.jsp").include(request, response);
						        out.print("<script>document.getElementById(\"mes\").innerHTML=\"Cannot send code!\"</script>");
							}
						}catch(Exception e) {
							PrintWriter out = response.getWriter();
							request.getRequestDispatcher("Email.jsp").include(request, response);
					        out.print("<script>document.getElementById(\"mes\").innerHTML=\"Canoot send code!\"</script>");
						}
					}else {
						PrintWriter out = response.getWriter();
						request.getRequestDispatcher("Email.jsp").include(request, response);
				        out.print("<script>document.getElementById(\"mes\").innerHTML=\"Email not exist!\"</script>");
					}
				}else {
					PrintWriter out = response.getWriter();
					request.getRequestDispatcher("Email.jsp").include(request, response);
			        out.print("<script>document.getElementById(\"mes\").innerHTML=\"Please input email!\"</script>");
				}
				break;
				
			default:
				String site2 = "notfound.jsp" ;
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location", site2);
				break;
		}
		}catch(Exception e) {
			
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
