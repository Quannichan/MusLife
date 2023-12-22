package Script;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String get = request.getParameter("loo");
		if(get != null) {
			if(get.equals("logout")) {
				request.getSession().setAttribute("IDUS",null);
				request.getSession().setAttribute("NAME",null);
				request.getSession().setAttribute("MAIL",null);
				request.getSession().setAttribute("PASS",null);
				request.getSession().setAttribute("SufList",null);
				request.getSession().setAttribute("Podcast",null);
				request.getSession().setAttribute("Playlists",null);
				request.getSession().setAttribute("Med_fav",null);
				request.getSession().setAttribute("Pll_fav",null);
				request.getSession().setAttribute("Pod_fav",null);
				request.getSession().setAttribute("count", 0);
				response.sendRedirect("Login.jsp");
			}else {
				response.sendRedirect("notfound.jsp");
			}
		}else{
			response.sendRedirect("notfound.jsp");
		}
	}

}
