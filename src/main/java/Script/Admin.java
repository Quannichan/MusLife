package Script;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("MAIL") != null && request.getSession().getAttribute("PASS") != null) {
			if(request.getSession().getAttribute("MAIL").toString().equals("AdminPageMusLife@gmail.com") && request.getSession().getAttribute("PASS").toString().equals("876b97c826857505ab230e51a741c259") ){
				String url = request.getParameter("Admin_Behave");
				String req = request.getParameter("Admin_request");
				if(url.equals("AdAction")) {
					if(req.equals("") != true) {
						try {
							Class.forName("com.mysql.jdbc.Driver");
							String URL = "jdbc:mysql://localhost:3306/MusLife";
							String USER = "root"; 
							String PASS = "QuanG&C13082004";
							Connection conn = DriverManager.getConnection(URL, USER, PASS);
							Statement stmt = conn.createStatement();
							ResultSet rs;
							switch(req) {
							
							case"get_media":
								String data = "{\"error\":\"false\", \"media\":[";
								rs = stmt.executeQuery("SELECT * FROM Media order by id DESC");
								while(rs.next()) {
									data = data + "{\"id\":\""+rs.getInt(1)+"\", \"img\": \""+rs.getString(2)+"\", \"name\": \""+rs.getString(3)+"\", \"per\":\""+rs.getString(4)+"\", \"cate\": \""+rs.getString(6)+"\", \"year\": \""+rs.getString(7)+"\", \"types\": \""+rs.getString(8)+"\"}";
									if(!rs.isLast()) {
										data = data + ", ";
									}else {
										
									}
								}
								
								data = data +"], \"cate\":[";
								rs = stmt.executeQuery("SELECT * FROM med_cate order by id DESC");
								while(rs.next()) {
									data = data + "{\"id\":\""+rs.getInt(1)+"\", \"name\":\""+rs.getString(2)+"\"}";
									if(!rs.isLast()) {
										data = data + ", ";
									}else {
									}
								}
								
								data = data +"], \"artist\":[";
								rs = stmt.executeQuery("SELECT * FROM Performer ORDER BY per_id DESC");
								while(rs.next()) {
									data = data + "{\"id\":\""+rs.getInt(1)+"\", \"img\":\""+rs.getString(2)+"\", \"name\":\""+rs.getString(3)+"\"}";
									if(!rs.isLast()) {
										data = data + ", ";
									}else {
									}
								}
								
								data = data + "]}";
								
								System.out.println(data);
								response.setContentType("application/json");
					            response.setCharacterEncoding("UTF-8");
								response.getWriter().write(data);
								break;
								
							case "get_playlist":
								String data1 = "{\"error\":\"false\", \"playlist\":[";
								rs = stmt.executeQuery("SELECT * FROM playlist order by playlist_id DESC");
								while(rs.next()) {
									data1 = data1 + "{ \"id\": \""+rs.getInt(1)+"\" , \"img\": \""+rs.getString(2)+"\" , \"name\": \""+rs.getString(3)+"\", \"script\":\""+rs.getString(4)+"\", \"type\":\""+rs.getString(5)+"\"}";
									if(rs.isLast()) {
										
									}else {
										data1 = data1 + ", ";
									}
								}
								data1 = data1 + "]}";
								System.out.println(data1);
								response.setContentType("application/json");
					            response.setCharacterEncoding("UTF-8");
								response.getWriter().write(data1);
								break;
								
							case "get_artist":
								String data2 = "{\"error\":\"false\", \"artist\":[";
								rs = stmt.executeQuery("SELECT * FROM Performer order by per_id DESC");
								while(rs.next()) {
									data2 = data2 + "{\"id\":\""+rs.getInt(1)+"\", \"img\":\""+rs.getString(2)+"\", \"name\":\""+rs.getString(3)+"\", \"per_type\" :\""+rs.getString(4)+"\" }";
									if(!rs.isLast()) {
										data2 = data2 + ", ";
									}else {
										
									}
								}
								data2 = data2 + "]}";
								System.out.println(data2);
								response.setContentType("application/json");
					            response.setCharacterEncoding("UTF-8");
								response.getWriter().write(data2);
								break;
								
							case "get_cate":
								String data3 = "{\"error\":\"false\", \"cate\":[";
								rs = stmt.executeQuery("SELECT * FROM med_cate order by id DESC");
								while(rs.next()) {
									data3 = data3 + "{\"id\":\""+rs.getInt(1)+"\", \"name\":\""+rs.getString(2)+"\"}";
									if(!rs.isLast()) {
										data3 = data3 + ", ";
									}else {
									}
								}
								data3 = data3 + "]}";
								System.out.println(data3);
								response.setContentType("application/json");
					            response.setCharacterEncoding("UTF-8");
								response.getWriter().write(data3);
								break;
								
							case "get_user":
								String data4 = "{\"error\":\"false\", \"user\":[";
								rs = stmt.executeQuery("SELECT * FROM user WHERE user_id NOT IN ("+request.getSession().getAttribute("IDUS").toString()+")");
								while(rs.next()) {
									data4 = data4 + "{\"id\":\""+rs.getInt(1)+"\", \"name\":\""+rs.getString(2)+"\", \"email\":\""+rs.getString(3)+"\", \"img\" : \""+rs.getString(5)+"\"}";
									if(!rs.isLast()) {
										data4 = data4 + ", ";
									}else {
									}
								}
								data4 = data4 + "]}";
								System.out.println(data4);
								response.setContentType("application/json");
					            response.setCharacterEncoding("UTF-8");
								response.getWriter().write(data4);
								break;
								
							case "del_user":
								String data5 = "{\"error\":\"false\",";
								try {
									String getID = request.getParameter("id_user");
									stmt.execute("DELETE FROM user where user_id = '"+getID+"'");
									stmt.execute("DELETE FROM FavPlaylist where user_id = '"+getID+"'");
									stmt.execute("DELETE FROM CreatePlaylist where user_id = '"+getID+"'");
									stmt.execute("DELETE FROM mediaFav where user_id = '"+getID+"'");
									rs =stmt.executeQuery("SELECT count(user_id) from user");
									int count = 0;
									while(rs.next()) {
										count = rs.getInt(1);
									}
									count--;
									System.out.println(data5);
									data5 = data5 + "\"delStt\":\"true\",\"count\":\""+count+"\"}";
									response.setContentType("application/json");
						            response.setCharacterEncoding("UTF-8");
									response.getWriter().write(data5);
								}catch(Exception e) {
									e.printStackTrace();
									data5 = data5 + "\"delStt\":\"false\"}";
									response.setContentType("application/json");
						            response.setCharacterEncoding("UTF-8");
									response.getWriter().write(data5);
								}
								
								
								break;
								
							case "del_med":
								String data6 = "";
								try {
									
									String getID = request.getParameter("id_media");
									stmt.execute("DELETE FROM media where id = '"+getID+"'");
									stmt.execute("DELETE FROM mediaFav where media_id = '"+getID+"'");
									stmt.execute("DELETE FROM media_in_playlist where media_id = '"+getID+"'");
									data6 = "{\"error\":\"false\",";
									System.out.println(data6);
									data6 = data6 + "\"delStt\":\"true\"}";
									response.setContentType("application/json");
						            response.setCharacterEncoding("UTF-8");
									response.getWriter().write(data6);
								}catch(Exception e) {
									e.printStackTrace();
									data6 = "{\"error\":\"true\",";
									data6 = data6 + "\"delStt\":\"false\"}";
									response.setContentType("application/json");
						            response.setCharacterEncoding("UTF-8");
									response.getWriter().write(data6);
								}
								
								
								break;
								
								
							case "del_pll":
								String data8 = "";
								try {
									String getID = request.getParameter("id_pll");
									stmt.execute("DELETE FROM FavPlaylist where playlist_id = '"+getID+"'");
									stmt.execute("DELETE FROM playlist where playlist_id = '"+getID+"'");
									stmt.execute("DELETE FROM media_in_playlist where playlist_id = '"+getID+"'");
									data8 = "{\"error\":\"false\",";
									System.out.println(data8);
									data8 = data8 + "\"delStt\":\"true\"}";
									response.setContentType("application/json");
						            response.setCharacterEncoding("UTF-8");
									response.getWriter().write(data8);
								}catch(Exception e) {
									e.printStackTrace();
									data8 = "{\"error\":\"true\",";
									data8 = data8 + "\"delStt\":\"false\"}";
									response.setContentType("application/json");
						            response.setCharacterEncoding("UTF-8");
									response.getWriter().write(data8);
								}
								break;
								
							case "up_cate":
								String data9="";
								try {
									String id = request.getParameter("id");
									String name = request.getParameter("name");
									String old_name = request.getParameter("old_cate");
									stmt.execute("UPDATE med_cate set cate_name = '"+name+"' where id = '"+id+"';");
									stmt.execute("UPDATE media set media_song_categories = '"+name+"' where media_song_categories = '"+old_name+"'");
									data9 = "{\"error\":\"false\"}";
									response.setContentType("application/json");
						            response.setCharacterEncoding("UTF-8");
									response.getWriter().write(data9);
								}catch(Exception e) {
									e.printStackTrace();
									data9 = "{\"error\":\"true\"}";
									response.setContentType("application/json");
						            response.setCharacterEncoding("UTF-8");
									response.getWriter().write(data9);
								}
								
								break;
								
							case "new_cate":
								String data10="";
								try {
									data10 = "{\"error\":\"false\",";
									String name = request.getParameter("name_cate");
									stmt.execute("INSERT INTO med_cate (cate_name)VALUE ('"+name+"')");
									rs = stmt.executeQuery("SELECT * From med_cate ORDER BY id DESC LIMIT 1");
									while(rs.next()) {
										data10 = data10+ "\"id\":\""+rs.getInt(1)+"\", \"name\":\""+name+"\"";
									}
									data10 = data10 + "}";
									response.setContentType("application/json");
						            response.setCharacterEncoding("UTF-8");
									response.getWriter().write(data10);
								}catch(Exception e) {
									e.printStackTrace();
									data10 = "{\"error\":\"true\"}";
									response.setContentType("application/json");
						            response.setCharacterEncoding("UTF-8");
									response.getWriter().write(data10);
								}
								
								break;
								
							case "new_pll":
								String data11 = "";
								try {
									String name = request.getParameter("name");
									String type = request.getParameter("type");
									if(name.equals("") && name.length() < 1){
										data11 = "{\"error\":\"nullname\"}";
										response.setContentType("application/json");
							            response.setCharacterEncoding("UTF-8");
										response.getWriter().write(data11);
									}else {
										data11 = "{\"error\":\"false\"}";
										stmt.execute("INSERT INTO playlist (playlist_img_path, playlist_name, playlist_script, playlist_type )VALUE ('images/music.png', '"+name+"', 'new playlist', '"+type+"')");
										response.setContentType("application/json");
							            response.setCharacterEncoding("UTF-8");
										response.getWriter().write(data11);
									}
								}catch(Exception e) {
									e.printStackTrace();
									data11 = "{\"error\":\"true\"}";
									response.setContentType("application/json");
						            response.setCharacterEncoding("UTF-8");
									response.getWriter().write(data11);
								}
								break;
								
							case "up_pll":
								String data12 = "";
								try {
									String id_pll = request.getParameter("id");
									String type = request.getParameter("pll_type");
									ArrayList<Integer> id_arr = new ArrayList<Integer>();
									data12 = "{\"error\":\"false\",\"song\":[";
									rs = stmt.executeQuery("SELECT media.id, media.media_name, media.img_path, media.performer, media.media_song_categories, media.types from media_in_playlist right join media on media_in_playlist.media_id = media.id where media_in_playlist.playlist_id = '"+id_pll+"'");
									while(rs.next()) {
										id_arr.add(rs.getInt(1));
										data12 = data12 + "{\"id\":\""+rs.getInt(1)+"\", \"name\":\""+rs.getString(2)+"\", \"img\":\""+rs.getString(3)+"\", \"per\":\""+rs.getString(4)+"\", \"cate\":\""+rs.getString(5)+"\", \"type\":\""+rs.getString(6)+"\"}";
										if(!rs.isLast()) {
											data12 = data12 + ", ";
										}
									}
									data12 = data12 + "], ";
									String querry_exist = "\"med_search\":[";
									if(id_arr.size() > 0) {
										String id_str = id_arr.toString();
										id_str = id_str.substring(1, id_str.length()-1);
										String querry_get_search = "SELECT * FROM media where id NOT IN ("+id_str+") AND types = '"+type+"'";
										rs = stmt.executeQuery(querry_get_search);
										while(rs.next()) {
											querry_exist = querry_exist + "{\"id\":\""+rs.getInt(1)+"\", \"name\":\""+rs.getString(2)+"\", \"img\":\""+rs.getString(3)+"\", \"per\":\""+rs.getString(4)+"\", \"cate\":\""+rs.getString(5)+"\", \"type\":\""+rs.getString(6)+"\"}";
											if(!rs.isLast()) {
												querry_exist = querry_exist + ", ";
											}
										}
										querry_exist = querry_exist + "]";
									}else {
										rs = stmt.executeQuery("SELECT * FROM media");
										while(rs.next()) {
											querry_exist = querry_exist + "{\"id\":\""+rs.getInt(1)+"\", \"name\":\""+rs.getString(2)+"\", \"img\":\""+rs.getString(3)+"\", \"per\":\""+rs.getString(4)+"\", \"cate\":\""+rs.getString(5)+"\", \"type\":\""+rs.getString(6)+"\"}";
											if(!rs.isLast()) {
												querry_exist = querry_exist + ", ";
											}
										}
										querry_exist = querry_exist + "]";
									}
									data12 = data12 + querry_exist;
									data12 = data12 + "}";
									response.setContentType("application/json");
						            response.setCharacterEncoding("UTF-8");
									response.getWriter().write(data12);
								}catch(Exception e){
									data12 = "{\"error\":\"true\"}";
									response.setContentType("application/json");
						            response.setCharacterEncoding("UTF-8");
									response.getWriter().write(data12);
								}
																
								break;
								
							case "remove_med_from_pll":
								String data13 = "";
								try{
									data13 = "{\"error\":\"false\"}";
									String id_med = request.getParameter("id");
									String id_pll = request.getParameter("id_pll");
									stmt.execute("DELETE FROM media_in_playlist where media_id = '"+id_med+"' and playlist_id = '"+id_pll+"' ");
									response.setContentType("application/json");
						            response.setCharacterEncoding("UTF-8");
									response.getWriter().write(data13);
								}catch(Exception e) {
									data13 = "{\"error\":\"true\"}";
									response.setContentType("application/json");
						            response.setCharacterEncoding("UTF-8");
									response.getWriter().write(data13);
								}
								
								
								break;
								
							case "add_med_to_pll":
								String data14 = "";
								try{
									data14 = "{\"error\":\"false\"}";
									String id_med = request.getParameter("id");
									String id_pll = request.getParameter("id_pll");
									stmt.execute("INSERT INTO media_in_playlist VALUES ("+id_med+" , "+id_pll+" )");
									response.setContentType("application/json");
						            response.setCharacterEncoding("UTF-8");
									response.getWriter().write(data14);
								}catch(Exception e) {
									e.printStackTrace();
									data13 = "{\"error\":\"true\"}";
									response.setContentType("application/json");
						            response.setCharacterEncoding("UTF-8");
									response.getWriter().write(data14);
								}
								break;
								
							default:
								response.setContentType("application/json");
					            response.setCharacterEncoding("UTF-8");
								response.getWriter().write("{\"error\":\"true\"}");
								break;
							}
						}catch(Exception e){
							e.printStackTrace();
							response.setContentType("application/json");
				            response.setCharacterEncoding("UTF-8");
							response.getWriter().write("{\"error\":\"true\"}");
						}
						
					}else {
						
					}
				}else {
					response.setContentType("application/json");
		            response.setCharacterEncoding("UTF-8");
					response.getWriter().write("{\"error\":\"true\"}");
				}
			}else{
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
