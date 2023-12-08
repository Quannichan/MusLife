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
 * Servlet implementation class update_add
 */
@MultipartConfig
@WebServlet("/update_add")
public class update_add extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("MAIL") != null && request.getSession().getAttribute("PASS") != null) {
			if(request.getSession().getAttribute("MAIL").toString().equals("AdminPageMusLife@gmail.com") && request.getSession().getAttribute("PASS").toString().equals("876b97c826857505ab230e51a741c259") ){
				try {
					request.setCharacterEncoding("UTF-8");
					String URL = "jdbc:mysql://localhost:3306/MusLife";
					String USER = "root"; 
					String PASS = "QuanG&C13082004";
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn;
					conn = DriverManager.getConnection(URL, USER, PASS);
					Statement stmt=conn.createStatement();
					String url = request.getParameter("Admin_Behave");
					String req = request.getParameter("Admin_request");
					if(url.equals("AdAction")) {
						if(!req.equals("")) {
							String img_pathSong = request.getServletContext().getRealPath("images/song");
							String img_pathPod = request.getServletContext().getRealPath("images/podcast");
							String img_pathArtist = request.getServletContext().getRealPath("images/performers");
							String img_pathPlaylist = request.getServletContext().getRealPath("images/playlist");
							
							String name = request.getParameter("name");
							switch(req) {
								case"up_med":
									String id = request.getParameter("id");
									String med_type =request.getParameter("Med_type");
									try{
										Part filePath = request.getPart("file");
										String filename = Path.of(filePath.getSubmittedFileName()).getFileName().toString();
										if(med_type.equals("song")) {
											if(!Files.exists(Path.of(img_pathSong))) {
												Files.createDirectory(Path.of(img_pathSong));
											}else {
												System.out.println(name + id);
												filePath.write(img_pathSong+"/"+filename);
												stmt.executeUpdate("UPDATE media set img_path = 'images/song/"+filename+"', media_name = '"+name+"' where id = '"+id+"' ");
												response.setContentType("application/json");
										        response.setCharacterEncoding("UTF-8");
										        response.getWriter().write("{\"error\":\"false\"}");
										        System.out.println("co anh");
											}
											
										}else if(med_type.equals("podcast")){
											if(!Files.exists(Path.of(img_pathPod))) {
												Files.createDirectory(Path.of(img_pathPod));
											}else {
												System.out.println(name + id);
												filePath.write(img_pathPod+"/"+filename);
												stmt.executeUpdate("UPDATE media set img_path = 'images/podacst/"+filename+"', media_name = '"+name+"' where id = '"+id+"' ");
												response.setContentType("application/json");
										        response.setCharacterEncoding("UTF-8");
										        response.getWriter().write("{\"error\":\"false\"}");
										        System.out.println("co anh");
											}
											
										}
										
									}catch(Exception e) {
										System.out.println(name + id);
										System.out.println(name);
										stmt.executeUpdate("UPDATE media set media_name = '"+name+"' where id = '"+id+"' ");
										response.setContentType("application/json");
										response.setCharacterEncoding("UTF-8");
										response.getWriter().write("{\"error\":\"false\"}");
										System.out.println("ko co anh");
									}
									break;
								
								case "up_art":
									id = request.getParameter("id");
									String old_art = request.getParameter("old_art");
									try{
										Part filePath = request.getPart("file");
										String filename = Path.of(filePath.getSubmittedFileName()).getFileName().toString();
										System.out.println(img_pathArtist+"/"+filename);
										if(!Files.exists(Path.of(img_pathArtist))) {
											Files.createDirectory(Path.of(img_pathArtist));
										}else {
											filePath.write(img_pathArtist+"/"+filename);
											stmt.executeUpdate("UPDATE Performer set per_img = 'images/performers/"+filename+"', per_name = '"+name+"' where per_id = '"+id+"' ");
											stmt.executeUpdate("UPDATE media set performer = '"+name+"' where performer = '"+old_art+"' ");
											response.setContentType("application/json");
									        response.setCharacterEncoding("UTF-8");
									        response.getWriter().write("{\"error\":\"false\"}");
									        System.out.println("co anh");
										}
										
									}catch(Exception e) {
										System.out.println(name + id);
										stmt.executeUpdate("UPDATE Performer set per_name = '"+name+"' where per_id = '"+id+"' ");
										stmt.executeUpdate("UPDATE media set performer = '"+name+"' where performer = '"+old_art+"' ");
										response.setContentType("application/json");
										response.setCharacterEncoding("UTF-8");
										response.getWriter().write("{\"error\":\"false\"}");
										System.out.println("ko co anh");
									}
									break;
									
								case "up_pll":
									id = request.getParameter("id");
									String script = request.getParameter("script");
									try{
										Part filePath = request.getPart("file");
										String filename = Path.of(filePath.getSubmittedFileName()).getFileName().toString();
										System.out.println(img_pathPlaylist+"/"+filename);
										if(!Files.exists(Path.of(img_pathPlaylist))) {
											Files.createDirectory(Path.of(img_pathPlaylist));
										}else {
											filePath.write(img_pathPlaylist+"/"+filename);
											stmt.executeUpdate("UPDATE playlist set playlist_img_path = 'images/playlist/"+filename+"', playlist_name = '"+name+"', playlist_script='"+script+"' where playlist_id = '"+id+"' ");
											response.setContentType("application/json");
									        response.setCharacterEncoding("UTF-8");
									        response.getWriter().write("{\"error\":\"false\"}");
									        System.out.println("co anh");
										}
										
									}catch(Exception e) {
										System.out.println(name + id);
										stmt.executeUpdate("UPDATE playlist set playlist_name = '"+name+"', playlist_script='"+script+"' where playlist_id = '"+id+"' ");
										response.setContentType("application/json");
										response.setCharacterEncoding("UTF-8");
										response.getWriter().write("{\"error\":\"false\"}");
										System.out.println("ko co anh");
									}
									break;
																		
								case "new_med":
									
									
									break;
									
								case "new_art":
									try {
										String type = request.getParameter("type");
										Part filePath = request.getPart("file");
										String filename = Path.of(filePath.getSubmittedFileName()).getFileName().toString();
										if(!Files.exists(Path.of(img_pathArtist))) {
											Files.createDirectory(Path.of(img_pathArtist));
										}else {
											filePath.write(img_pathArtist+"/"+filename);
											stmt.executeUpdate("INSERT INTO Performer (per_img, per_name, per_type) VALUES ('images/performers/"+filename+"', '"+name+"','"+type+"')");
											response.setContentType("application/json");
									        response.setCharacterEncoding("UTF-8");
									        response.getWriter().write("{\"error\":\"false\"}");
										}
									}catch(Exception e){
										e.printStackTrace();
										response.setContentType("application/json");
								        response.setCharacterEncoding("UTF-8");
								        response.getWriter().write("{\"error\":\"true\"}");
									}
									
									break;
									
								case "new_pll":
									
									break;
							}
						}else {
							response.setContentType("application/json");
				            response.setCharacterEncoding("UTF-8");
							response.getWriter().write("{\"error\":\"true\"}");
						}
					}else {
						response.setContentType("application/json");
			            response.setCharacterEncoding("UTF-8");
						response.getWriter().write("{\"error\":\"true\"}");
					}
				}catch(Exception e) {
					e.printStackTrace();
					response.setContentType("application/json");
		            response.setCharacterEncoding("UTF-8");
					response.getWriter().write("{\"error\":\"true\"}");
				}
			}else {
				response.setContentType("application/json");
	            response.setCharacterEncoding("UTF-8");
				response.getWriter().write("{\"error\":\"true\"}");
			}
		}else {
			response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
			response.getWriter().write("{\"error\":\"true\"}");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
