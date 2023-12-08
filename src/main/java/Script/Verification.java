package Script;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class Verification
 */
@WebServlet("/Verification")
public class Verification extends HttpServlet {
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
		String get = request.getParameter("sub");
		if(get != null) {
		switch(get){
			case "Next":
				if(request.getSession().getAttribute("tove") == null) {
						String site = "notfound.jsp" ;
						response.setStatus(response.SC_MOVED_TEMPORARILY);
						response.setHeader("Location", site); 
				}else{
				if(request.getSession().getAttribute("tove").toString().equals("natove")) {
					String inp1 = request.getParameter("inp1");
					String inp2 = request.getParameter("inp2");
					String inp3 = request.getParameter("inp3");
					String inp4 = request.getParameter("inp4");
					String code = request.getSession().getAttribute("ve").toString();
					String all = inp1 + inp2 + inp3 + inp4;
					int ver_code = Integer.parseInt(all);
					System.out.println(ver_code);
						if(ver_code == Integer.parseInt(code)) {
							try {
							System.out.println("right");
							String URL = "jdbc:mysql://localhost:3306/MusLife";
							String USER = "root"; 
							String PASS = "QuanG&C13082004";
							Class.forName("com.mysql.jdbc.Driver");
							Connection conn;
							conn = DriverManager.getConnection(URL, USER, PASS);
							Statement stmt=conn.createStatement();  
							stmt.executeUpdate("Insert into user(user_name,user_email,user_pass, user_img) Values('"+request.getSession().getAttribute("usname").toString()+"','"+request.getSession().getAttribute("mail").toString()+"','"+request.getSession().getAttribute("pass").toString()+"','images/backgroundimg/basic.jpg')");
							ResultSet rs = stmt.executeQuery("SELECT user_id from user where user_email = '"+request.getSession().getAttribute("mail").toString()+"'");
							int id = 0;
							while(rs.next()) {
								id = rs.getInt(1);
							}
							request.getSession().setAttribute("IDUS",id);
							request.getSession().setAttribute("NAME",request.getSession().getAttribute("usname").toString());
							request.getSession().setAttribute("MAIL",request.getSession().getAttribute("mail").toString());
							request.getSession().setAttribute("PASS",request.getSession().getAttribute("pass").toString());
							request.getSession().setAttribute("tove",null);
							request.getSession().setAttribute("ve",null);
							request.getSession().setAttribute("mail",null);
							request.getSession().setAttribute("pass",null);
							request.getSession().setAttribute("usname",null);
							request.getSession().setAttribute("upload","yes");
							Thread.sleep(2000);
							String site = "UploadImage.jsp" ;
							response.setStatus(response.SC_MOVED_PERMANENTLY);
							response.setHeader("Location", site); 
							}catch(Exception e) {
								e.printStackTrace();
							}
						}else{
							System.out.println("wrong");
							PrintWriter out = response.getWriter();
							request.getRequestDispatcher("Verification.jsp").include(request, response);
					        out.print("<script> timeout(); setTimeout(timein,3000);function timeout(){document.getElementById(\"mes\").innerText=\"Wrong verification code!\";document.getElementById(\"inp1\").style.borderColor=\"red\";document.getElementById(\"inp2\").style.borderColor=\"red\";document.getElementById(\"inp3\").style.borderColor=\"red\";document.getElementById(\"inp4\").style.borderColor=\"red\"};function timein(){document.getElementById(\"mes\").innerText=\"\";document.getElementById(\"inp1\").style.borderColor=\"black\";document.getElementById(\"inp2\").style.borderColor=\"black\";document.getElementById(\"inp3\").style.borderColor=\"black\";document.getElementById(\"inp4\").style.borderColor=\"black\"};</script>");
						}
					}else if(request.getSession().getAttribute("tove").toString().equals("fptove")){
						String inp1 = request.getParameter("inp1");
						String inp2 = request.getParameter("inp2");
						String inp3 = request.getParameter("inp3");
						String inp4 = request.getParameter("inp4");
						String code = request.getSession().getAttribute("ve").toString();
						String all = inp1 + inp2 + inp3 + inp4;
						int ver_code = Integer.parseInt(all);
						System.out.println(ver_code);
							if(ver_code == Integer.parseInt(code)) {
								try {
								System.out.println("right");
								request.getSession().setAttribute("tove", null);
								request.getSession().setAttribute("ve",null);
								request.getSession().setAttribute("CP","true");
								Thread.sleep(2000);
								String site = "Newpass.jsp" ;
								response.setStatus(response.SC_MOVED_PERMANENTLY);
								response.setHeader("Location", site); 
								}catch(Exception e) {
									e.printStackTrace();
								}
							}else{
								System.out.println("wrong");
								PrintWriter out = response.getWriter();
								request.getRequestDispatcher("Verification.jsp").include(request, response);
						        out.print("<script> timeout(); setTimeout(timein,3000);function timeout(){document.getElementById(\"mes\").innerText=\"Wrong verification code!\";document.getElementById(\"inp1\").style.borderColor=\"red\";document.getElementById(\"inp2\").style.borderColor=\"red\";document.getElementById(\"inp3\").style.borderColor=\"red\";document.getElementById(\"inp4\").style.borderColor=\"red\"};function timein(){document.getElementById(\"mes\").innerText=\"\";document.getElementById(\"inp1\").style.borderColor=\"black\";document.getElementById(\"inp2\").style.borderColor=\"black\";document.getElementById(\"inp3\").style.borderColor=\"black\";document.getElementById(\"inp4\").style.borderColor=\"black\"};</script>");
							}
					}
				}
				break;
		
			case "Cannot get code?":
				if(request.getSession().getAttribute("mail") != null) {
					String name = request.getSession().getAttribute("mail").toString();
					JavaMail code = new JavaMail(name);
					try {
					if(code.get == true) {
						request.getSession().setAttribute("ve",code.n);
						Thread.sleep(2000);
						PrintWriter out = response.getWriter();
						request.getRequestDispatcher("Verification.jsp").include(request, response);
				        out.print("<script> timein(); function timein(){document.getElementById(\"mes\").innerText = \"Code resent, check your mail\";}; setTimeout(timeout,5000); function timeout(){document.getElementById(\"mes\").innerText=\"\";} </script>");
					}else {
						PrintWriter out = response.getWriter();
						out.print("SOMETHING WENT WRONG, PLEASE TRY AGAIN!");
					}
					}catch(Exception e) {
						e.printStackTrace();
					}
				}else {
					String site = "notfound.jsp" ;
					response.setStatus(response.SC_MOVED_TEMPORARILY);
					response.setHeader("Location", site); 
				}
				break;
				
			default:
				String site = "notfound.jsp" ;
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location", site);
				break;
				
		}
		}else {
			String site1 = "notfound.jsp" ;
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", site1);
		}
	}

}
