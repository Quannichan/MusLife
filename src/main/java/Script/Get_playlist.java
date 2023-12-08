package Script;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Get_playlist")
public class Get_playlist extends HttpServlet {
	String[] Char_arr = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "a", "b", "c", "d", "e", "f", "g", "h", "i",
			"j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "s", "y", "z", "A", "B", "C", "D",
			"E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "S", "Y",
			"Z" };

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String URL = "jdbc:mysql://localhost:3306/MusLife";
			String USER = "root";
			String PASS = "QuanG&C13082004";
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			Statement stmt = conn.createStatement();
			String GET = request.getParameter("getPL");
			if (GET != null) {
				switch (GET) {
					case "GET":
						try {
							ArrayList<Playlist> Plls = new ArrayList<Playlist>();
							ResultSet rs = stmt.executeQuery(
									"Select playlist_id, playlist_img_path, playlist_name, playlist_script, playlist_type from playlist");
							while (rs.next()) {
								Playlist pl = new Playlist();
								pl.setPl_id(rs.getInt(1));
								pl.setPl_img_path(rs.getString(2));
								pl.setPl_name(rs.getString(3));
								pl.setPl_script(rs.getString(4));
								pl.setPl_type(rs.getString(5));
								Plls.add(pl);
							}
							request.getSession().setAttribute("count", 1);
							request.getSession().setAttribute("Playlists", Plls);
							response.sendRedirect("index.jsp");
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
					case "GETPLAYLIST":
						String id = request.getParameter("ID_playlist");
						if (id != null) {
							try {
								if (id.equals("Love_song_pll")) {
									boolean suf_love_song = true;
									if (request.getSession().getAttribute("suf_song_love") != null) {
										suf_love_song = (boolean) request.getSession().getAttribute("suf_song_love");
									}
									String data = "{\"like\":\"none\",\"suff\":\"" + suf_love_song + "\",\"song\":[";
									ArrayList<Integer> ID_fav = new ArrayList<>();
									if (request.getSession().getAttribute("Med_fav") != null) {
										ID_fav.addAll(
												(ArrayList<Integer>) request.getSession().getAttribute("Med_fav"));
									}
									String result = "";
									ResultSet rs = stmt.executeQuery(
											"SELECT id, img_path, media_name, performer, media_song_categories, year, types from media where types = 'song';");
									while (rs.next()) {
										if (ID_fav.contains(rs.getInt(1))) {
											result = result + "{\"ID\":\"" + rs.getInt(1)
													+ "\",\"like\":\"true\",\"img\":\"" + rs.getString(2)
													+ "\",\"name\": \"" + rs.getString(3) + "\", \"per\": \""
													+ rs.getString(4) + "\", \"cate\":\"" + rs.getString(5)
													+ "\", \"year\": \"" + rs.getString(6) + "\",\"type\": \""
													+ rs.getString(7) + "\"}";
										}
									}
									ArrayList<Integer> index = new ArrayList<>();
									StringBuffer Text = new StringBuffer(result);
									for (int i = 0; i < Text.length(); i++) {
										if (Text.charAt(i) == '}') {
											index.add(i);
										}
									}
									result = Text.toString();
									String last_res = "";
									last_res = insertString(result, ",", index);
									data = data + last_res + "]}";
									System.out.println(data);
									response.setContentType("application/json");
									response.setCharacterEncoding("UTF-8");
									response.getWriter().write(data);
								} else if (id.equals("Love_pod_pll")) {
									boolean suf_love_pod = true;
									if (request.getSession().getAttribute("suf_pod_love") != null) {
										suf_love_pod = (boolean) request.getSession().getAttribute("suf_pod_love");
									}
									String data = "{\"like\":\"none\",\"suff\":\"" + suf_love_pod + "\",\"song\":[";
									ArrayList<Integer> ID_fav = new ArrayList<>();
									if (request.getSession().getAttribute("Med_fav") != null) {
										ID_fav.addAll(
												(ArrayList<Integer>) request.getSession().getAttribute("Med_fav"));
									}
									String result = "";
									ResultSet rs = stmt.executeQuery(
											"SELECT id, img_path, media_name, performer, media_song_categories, year, types from media where types = 'podcast';");
									while (rs.next()) {
										if (ID_fav.contains(rs.getInt(1))) {
											result = result + "{\"ID\":\"" + rs.getInt(1)
													+ "\",\"like\":\"true\",\"img\":\"" + rs.getString(2)
													+ "\",\"name\": \"" + rs.getString(3) + "\", \"per\": \""
													+ rs.getString(4) + "\", \"cate\":\"" + rs.getString(5)
													+ "\", \"year\": \"" + rs.getString(6) + "\",\"type\": \""
													+ rs.getString(7) + "\"}";
										}
									}
									ArrayList<Integer> index = new ArrayList<>();
									StringBuffer Text = new StringBuffer(result);
									for (int i = 0; i < Text.length(); i++) {
										if (Text.charAt(i) == '}') {
											index.add(i);
										}
									}
									result = Text.toString();
									String last_res = "";
									last_res = insertString(result, ",", index);
									data = data + last_res + "]}";
									System.out.println(data);
									response.setContentType("application/json");
									response.setCharacterEncoding("UTF-8");
									response.getWriter().write(data);
								} else {
									ArrayList<Playlist> ID_pl_fav = new ArrayList<Playlist>();
									if (request.getSession().getAttribute("Pll_fav") != null) {
										ID_pl_fav.addAll(
												(ArrayList<Playlist>) request.getSession().getAttribute("Pll_fav"));
										if (request.getSession().getAttribute("Pod_fav") != null) {
											ID_pl_fav.addAll(
													(ArrayList<Playlist>) request.getSession().getAttribute("Pod_fav"));
										}
									} else {
										if (request.getSession().getAttribute("Pod_fav") != null) {
											ID_pl_fav.addAll(
													(ArrayList<Playlist>) request.getSession().getAttribute("Pod_fav"));
										}
									}
									boolean fav = false;
									if (ID_pl_fav.size() > 0) {
										for (int i = 0; i < ID_pl_fav.size(); i++) {
											String compare = ID_pl_fav.get(i).getPl_id() + "";
											if (compare.equals(id)) {
												fav = true;
												break;
											}
										}
									} else {
										fav = false;
									}
									ArrayList<Integer> ID_lo_med = new ArrayList<Integer>();
									if (request.getSession().getAttribute("Med_fav") != null) {
										ID_lo_med = (ArrayList<Integer>) request.getSession().getAttribute("Med_fav");
									}
									System.out.println(id);
									ResultSet rs = stmt.executeQuery(
											"SELECT media_id, img_path, media_name, performer,media_song_categories,year, types FROM media_in_playlist RIGHT JOIN media ON media_in_playlist.media_id = media.id WHERE media_in_playlist.playlist_id = '"
													+ id + "' ;");
									if (request.getSession().getAttribute("SufList") != null) {
										ArrayList<SufOrUnsuf> getsuf = new ArrayList<SufOrUnsuf>();
										getsuf = (ArrayList<SufOrUnsuf>) request.getSession().getAttribute("SufList");
										boolean check = false;
										int index = 0;
										for (int i = 0; i < getsuf.size(); i++) {
											if (getsuf.get(i).id.equals(id)) {
												check = true;
												index = i;
												break;
											}
										}
										if (check == true) {
											boolean suf = getsuf.get(index).stat;
											String data = "";
											if (fav == true) {
												data = "{\"like\":\"true\",\"suff\":\"" + suf + "\",\"song\":[";
											} else {
												data = "{\"like\":\"false\",\"suff\":\"" + suf + "\",\"song\":[";
											}

											while (rs.next()) {
												if (ID_lo_med.contains(rs.getInt(1))) {
													data = data + "{\"ID\":\"" + rs.getInt(1)
															+ "\",\"like\":\"true\",\"img\":\"" + rs.getString(2)
															+ "\",\"name\": \"" + rs.getString(3) + "\", \"per\": \""
															+ rs.getString(4) + "\", \"cate\":\"" + rs.getString(5)
															+ "\", \"year\": \"" + rs.getString(6) + "\",\"type\": \""
															+ rs.getString(7) + "\"}";
													if (rs.isLast() != true) {
														data = data + ",";
													} else {

													}
												} else {
													data = data + "{\"ID\":\"" + rs.getInt(1)
															+ "\",\"like\":\"false\",\"img\":\"" + rs.getString(2)
															+ "\",\"name\": \"" + rs.getString(3) + "\", \"per\": \""
															+ rs.getString(4) + "\", \"cate\":\"" + rs.getString(5)
															+ "\", \"year\": \"" + rs.getString(6) + "\",\"type\": \""
															+ rs.getString(7) + "\"}";
													if (rs.isLast() != true) {
														data = data + ",";
													} else {

													}
												}
											}
											data = data + "]}";
											System.out.println(data);
											response.setContentType("application/json");
											response.setCharacterEncoding("UTF-8");
											response.getWriter().write(data);
										} else {
											SufOrUnsuf med = new SufOrUnsuf();
											med.id = id;
											med.stat = false;
											getsuf.add(med);
											String data = "";
											if (fav == true) {
												data = "{\"like\":\"true\",\"suff\":\"false\",\"song\":[";
											} else {
												data = "{\"like\":\"false\",\"suff\":\"false\",\"song\":[";
											}
											while (rs.next()) {
												if (ID_lo_med.contains(rs.getInt(1))) {
													data = data + "{\"ID\":\"" + rs.getInt(1)
															+ "\",\"like\":\"true\",\"img\":\"" + rs.getString(2)
															+ "\",\"name\": \"" + rs.getString(3) + "\", \"per\": \""
															+ rs.getString(4) + "\", \"cate\":\"" + rs.getString(5)
															+ "\", \"year\": \"" + rs.getString(6) + "\",\"type\": \""
															+ rs.getString(7) + "\"}";
													if (rs.isLast() != true) {
														data = data + ",";
													} else {

													}
												} else {
													data = data + "{\"ID\":\"" + rs.getInt(1)
															+ "\",\"like\":\"false\",\"img\":\"" + rs.getString(2)
															+ "\",\"name\": \"" + rs.getString(3) + "\", \"per\": \""
															+ rs.getString(4) + "\", \"cate\":\"" + rs.getString(5)
															+ "\", \"year\": \"" + rs.getString(6) + "\",\"type\": \""
															+ rs.getString(7) + "\"}";
													if (rs.isLast() != true) {
														data = data + ",";
													} else {

													}
												}
											}
											data = data + "]}";
											request.getSession().setAttribute("SufList", getsuf);
											System.out.println(data);
											response.setContentType("application/json");
											response.setCharacterEncoding("UTF-8");
											response.getWriter().write(data);
										}

									} else {
										ArrayList<SufOrUnsuf> getsuf = new ArrayList<SufOrUnsuf>();
										SufOrUnsuf med = new SufOrUnsuf();
										med.id = id;
										med.stat = false;
										getsuf.add(med);
										String data = "";
										if (fav == true) {
											data = "{\"like\":\"true\",\"suff\":\"false\",\"song\":[";
										} else {
											data = "{\"like\":\"false\",\"suff\":\"false\",\"song\":[";
										}
										while (rs.next()) {
											if (ID_lo_med.contains(rs.getInt(1))) {
												data = data + "{\"ID\":\"" + rs.getInt(1)
														+ "\",\"like\":\"true\",\"img\":\"" + rs.getString(2)
														+ "\",\"name\": \"" + rs.getString(3) + "\", \"per\": \""
														+ rs.getString(4) + "\", \"cate\":\"" + rs.getString(5)
														+ "\", \"year\": \"" + rs.getString(6) + "\",\"type\": \""
														+ rs.getString(7) + "\"}";
												if (rs.isLast() != true) {
													data = data + ",";
												} else {

												}
											} else {
												data = data + "{\"ID\":\"" + rs.getInt(1)
														+ "\",\"like\":\"false\",\"img\":\"" + rs.getString(2)
														+ "\",\"name\": \"" + rs.getString(3) + "\", \"per\": \""
														+ rs.getString(4) + "\", \"cate\":\"" + rs.getString(5)
														+ "\", \"year\": \"" + rs.getString(6) + "\",\"type\": \""
														+ rs.getString(7) + "\"}";
												if (rs.isLast() != true) {
													data = data + ",";
												} else {

												}
											}
										}
										data = data + "]}";
										request.getSession().setAttribute("SufList", getsuf);
										System.out.println(data);
										response.setContentType("application/json");
										response.setCharacterEncoding("UTF-8");
										response.getWriter().write(data);
									}
								}
							} catch (Exception e) {
								e.printStackTrace();
							}

						} else {
							String site = "notfound.jsp";
							response.setStatus(response.SC_MOVED_TEMPORARILY);
							response.setHeader("Location", site);
						}
						break;
					case "PLAYSONG":
						String ID_song = request.getParameter("ID_SONG");
						String ID_playlist = request.getParameter("ID_playlist");
						// CHÚ Ý!!!
						if (ID_song != null && ID_playlist != null) {
							if (request.getSession().getAttribute("MAIL") != null
									&& request.getSession().getAttribute("PASS") != null) {
								String place = request.getParameter("PLACE");
								String getPrePlace = "none";
								if (request.getSession().getAttribute("place") != null) {
									getPrePlace = (String) request.getSession().getAttribute("place");
								}
								if (request.getSession().getAttribute("place") != null
										&& place.equals(getPrePlace) == true) {
									switch (place) {
										case "playlist":
											String id_isplay_old = "";
											if (request.getSession().getAttribute("id_isplay").toString()
													.equals("none") != true) {
												id_isplay_old = request.getSession().getAttribute("id_isplay")
														.toString();
											}
											request.getSession().setAttribute("CATE_TYPE", null);
											if (id_isplay_old.equals(ID_playlist)) {
												try {
													if (ID_playlist.equals("Love_song_pll")) {
														ResultSet rs = stmt.executeQuery(
																"SELECT id from media where types = 'song' ");
														ArrayList<Integer> id_med_isplay = new ArrayList<Integer>();
														ArrayList<Integer> luv = new ArrayList<Integer>();
														if (request.getSession().getAttribute("Med_fav") != null) {
															luv = (ArrayList<Integer>) request.getSession()
																	.getAttribute("Med_fav");
														}
														while (rs.next()) {
															if (luv.contains(rs.getInt(1))) {
																id_med_isplay.add(rs.getInt(1));
															}

														}
														request.getSession().setAttribute("id_med_his", id_med_isplay);
														request.getSession().setAttribute("id_isplay", ID_playlist);
													} else if (ID_playlist.equals("Love_pod_pll")) {
														ResultSet rs = stmt.executeQuery(
																"SELECT id from media where types = 'podcast' ");
														ArrayList<Integer> id_med_isplay = new ArrayList<Integer>();
														ArrayList<Integer> luv = new ArrayList<Integer>();
														if (request.getSession().getAttribute("Med_fav") != null) {
															luv = (ArrayList<Integer>) request.getSession()
																	.getAttribute("Med_fav");
														}
														while (rs.next()) {
															if (luv.contains(rs.getInt(1))) {
																id_med_isplay.add(rs.getInt(1));
															}

														}
														request.getSession().setAttribute("id_med_his", id_med_isplay);
														request.getSession().setAttribute("id_isplay", ID_playlist);
													} else {
														ResultSet rs = stmt.executeQuery(
																"SELECT media_id FROM media_in_playlist RIGHT JOIN media ON media_in_playlist.media_id = media.id WHERE media_in_playlist.playlist_id ='"
																		+ ID_playlist + "' ; ");
														ArrayList<Integer> id_med_isplay = new ArrayList<Integer>();
														while (rs.next()) {
															id_med_isplay.add(rs.getInt(1));
														}
														request.getSession().setAttribute("id_med_his", id_med_isplay);
														request.getSession().setAttribute("id_isplay", ID_playlist);
													}
												} catch (Exception e) {
													e.printStackTrace();
												}
											} else {
												try {
													if (ID_playlist.equals("Love_song_pll")) {
														ResultSet rs = stmt.executeQuery(
																"SELECT id from media where types = 'song' ");
														ArrayList<Integer> id_med_isplay = new ArrayList<Integer>();
														ArrayList<Integer> luv = new ArrayList<Integer>();
														if (request.getSession().getAttribute("Med_fav") != null) {
															luv = (ArrayList<Integer>) request.getSession()
																	.getAttribute("Med_fav");
														}
														while (rs.next()) {
															if (luv.contains(rs.getInt(1))) {
																id_med_isplay.add(rs.getInt(1));
															}

														}
														request.getSession().setAttribute("id_med_his", id_med_isplay);
														request.getSession().setAttribute("id_isplay", ID_playlist);
													} else if (ID_playlist.equals("Love_pod_pll")) {
														ResultSet rs = stmt.executeQuery(
																"SELECT id from media where types = 'podcast' ");
														ArrayList<Integer> id_med_isplay = new ArrayList<Integer>();
														ArrayList<Integer> luv = new ArrayList<Integer>();
														if (request.getSession().getAttribute("Med_fav") != null) {
															luv = (ArrayList<Integer>) request.getSession()
																	.getAttribute("Med_fav");
														}
														while (rs.next()) {
															if (luv.contains(rs.getInt(1))) {
																id_med_isplay.add(rs.getInt(1));
															}

														}
														request.getSession().setAttribute("id_med_his", id_med_isplay);
														request.getSession().setAttribute("id_isplay", ID_playlist);
													} else {
														ResultSet rs = stmt.executeQuery(
																"SELECT media_id FROM media_in_playlist RIGHT JOIN media ON media_in_playlist.media_id = media.id WHERE media_in_playlist.playlist_id ='"
																		+ ID_playlist + "' ; ");
														ArrayList<Integer> id_med_isplay = new ArrayList<Integer>();
														while (rs.next()) {
															id_med_isplay.add(rs.getInt(1));
														}
														request.getSession().setAttribute("id_med_his", id_med_isplay);
														request.getSession().setAttribute("id_isplay", ID_playlist);
													}
												} catch (Exception e) {
													e.printStackTrace();
												}

											}
											break;

										case "artist":
											request.getSession().setAttribute("CATE_TYPE", null);
											String id_isplay_old2 = "";
											if (request.getSession().getAttribute("id_isplay").toString()
													.equals("none") != true) {
												id_isplay_old2 = request.getSession().getAttribute("id_isplay")
														.toString();
											}
											if (id_isplay_old2.equals(ID_playlist)) {
												try {
													ResultSet rs = stmt.executeQuery(
															"SELECT media.id FROM Per_med right join media on Per_med.media_id = media.id where Per_med.per_id = "
																	+ ID_playlist + " ;");
													ArrayList<Integer> id_med_isplay = new ArrayList<Integer>();
													while (rs.next()) {
														id_med_isplay.add(rs.getInt(1));
													}
													request.getSession().setAttribute("id_med_his", id_med_isplay);
													request.getSession().setAttribute("id_isplay", ID_playlist);
												} catch (Exception e) {

												}
											} else {
												try {
													ResultSet rs = stmt.executeQuery(
															"SELECT media.id FROM Per_med right join media on Per_med.media_id = media.id where Per_med.per_id = "
																	+ ID_playlist + " ;");
													ArrayList<Integer> id_med_isplay = new ArrayList<Integer>();
													while (rs.next()) {
														id_med_isplay.add(rs.getInt(1));
													}
													request.getSession().setAttribute("id_med_his", id_med_isplay);

												} catch (Exception e) {

												}
											}
											break;

										case "songout":
											try {
												type_cate medtype = new type_cate();
												ResultSet rs = stmt.executeQuery(
														"SELECT media_song_categories, year from media where id = "
																+ ID_song + ";");
												while (rs.next()) {
													medtype.type = "song";
													medtype.cate = rs.getString(1);
													medtype.year = rs.getInt(2);
												}
												request.getSession().setAttribute("CATE_TYPE", medtype);
												request.getSession().setAttribute("id_isplay", 0);
												ArrayList<Integer> id_med_his = new ArrayList<Integer>();
												id_med_his.add(Integer.parseInt(ID_song));
												request.getSession().setAttribute("id_med_his", id_med_his);
											} catch (Exception e) {

											}
											break;

										case "podout":
											try {
												type_cate medtype = new type_cate();
												ResultSet rs = stmt.executeQuery(
														"SELECT media_song_categories, year from media where id = "
																+ ID_song + ";");
												while (rs.next()) {
													medtype.type = "song";
													medtype.cate = rs.getString(1);
													medtype.year = rs.getInt(2);
												}
												request.getSession().setAttribute("CATE_TYPE", medtype);
												request.getSession().setAttribute("id_isplay", 0);
												ArrayList<Integer> id_med_his1 = new ArrayList<Integer>();
												id_med_his1.add(Integer.parseInt(ID_song));
												request.getSession().setAttribute("id_med_his", id_med_his1);
											} catch (Exception e) {

											}
											break;

										default:
											break;
									}
								} else {
									request.getSession().setAttribute("place", place);
									switch (place) {
										case "playlist":
											try {
												System.out.println("khac place khac id");
												request.getSession().setAttribute("CATE_TYPE", null);
												request.getSession().setAttribute("id_isplay", ID_playlist);
												if (ID_playlist.equals("Love_song_pll")) {
													ResultSet rs = stmt
															.executeQuery("SELECT id from media where types = 'song' ");
													ArrayList<Integer> id_med_isplay = new ArrayList<Integer>();
													ArrayList<Integer> luv = new ArrayList<Integer>();
													if (request.getSession().getAttribute("Med_fav") != null) {
														luv = (ArrayList<Integer>) request.getSession()
																.getAttribute("Med_fav");
													}
													while (rs.next()) {
														if (luv.contains(rs.getInt(1))) {
															id_med_isplay.add(rs.getInt(1));
														}

													}
													request.getSession().setAttribute("id_med_his", id_med_isplay);
													request.getSession().setAttribute("id_isplay", ID_playlist);
												} else if (ID_playlist.equals("Love_pod_pll")) {
													ResultSet rs = stmt.executeQuery(
															"SELECT id from media where types = 'podcast' ");
													ArrayList<Integer> id_med_isplay = new ArrayList<Integer>();
													ArrayList<Integer> luv = new ArrayList<Integer>();
													if (request.getSession().getAttribute("Med_fav") != null) {
														luv = (ArrayList<Integer>) request.getSession()
																.getAttribute("Med_fav");
													}
													while (rs.next()) {
														if (luv.contains(rs.getInt(1))) {
															id_med_isplay.add(rs.getInt(1));
														}

													}
													request.getSession().setAttribute("id_med_his", id_med_isplay);
													request.getSession().setAttribute("id_isplay", ID_playlist);
												} else {
													ResultSet rs = stmt.executeQuery(
															"SELECT media_id FROM media_in_playlist RIGHT JOIN media ON media_in_playlist.media_id = media.id WHERE media_in_playlist.playlist_id ="
																	+ ID_playlist + " ; ");
													ArrayList<Integer> id_med_isplay = new ArrayList<Integer>();
													while (rs.next()) {
														id_med_isplay.add(rs.getInt(1));
													}
													request.getSession().setAttribute("id_med_his", id_med_isplay);
													request.getSession().setAttribute("id_isplay", ID_playlist);
												}
											} catch (Exception e) {

											}
											break;

										case "artist":
											try {
												request.getSession().setAttribute("CATE_TYPE", null);
												request.getSession().setAttribute("id_isplay", ID_playlist);
												ResultSet rs = stmt.executeQuery(
														"SELECT media.id FROM Per_med right join media on Per_med.media_id = media.id where Per_med.per_id = "
																+ ID_playlist + " ;");
												ArrayList<Integer> id_med_isplay = new ArrayList<Integer>();
												while (rs.next()) {
													id_med_isplay.add(rs.getInt(1));
												}
												request.getSession().setAttribute("id_med_his", id_med_isplay);
											} catch (Exception e) {

											}

											break;

										case "songout":
											try {
												type_cate medtype = new type_cate();
												ResultSet rs = stmt.executeQuery(
														"SELECT media_song_categories, year from media where id = "
																+ ID_song + ";");
												while (rs.next()) {
													medtype.type = "song";
													medtype.cate = rs.getString(1);
													medtype.year = rs.getInt(2);
												}
												request.getSession().setAttribute("CATE_TYPE", medtype);
												request.getSession().setAttribute("id_isplay", 0);
												ArrayList<Integer> id_med_his = new ArrayList<Integer>();
												id_med_his.add(Integer.parseInt(ID_song));
												request.getSession().setAttribute("id_med_his", id_med_his);
											} catch (Exception e) {

											}

											break;

										case "podout":
											try {
												type_cate medtype = new type_cate();
												ResultSet rs = stmt.executeQuery(
														"SELECT media_song_categories, year from media where id = "
																+ ID_song + ";");
												while (rs.next()) {
													medtype.type = "song";
													medtype.cate = rs.getString(1);
													medtype.year = rs.getInt(2);
												}
												request.getSession().setAttribute("CATE_TYPE", medtype);
												request.getSession().setAttribute("id_isplay", 0);
												ArrayList<Integer> id_med_his = new ArrayList<Integer>();
												id_med_his.add(Integer.parseInt(ID_song));
												request.getSession().setAttribute("id_med_his", id_med_his);
											} catch (Exception e) {

											}
											break;

										default:

											break;
									}
								}

								try {
									ResultSet rs = stmt
											.executeQuery("SELECT file_path from media where id = '" + ID_song + "'");
									String res = null;
									while (rs.next()) {
										res = rs.getString(1);
									}
									ArrayList<Integer> luv = new ArrayList<Integer>();
									if (request.getSession().getAttribute("Med_fav") != null) {
										luv = (ArrayList<Integer>) request.getSession().getAttribute("Med_fav");
									}
									boolean love = false;
									if (luv.contains(Integer.parseInt(ID_song))) {
										love = true;
									}
									response.setContentType("application/json");
									response.setCharacterEncoding("UTF-8");
									request.getSession().setAttribute("song_isplay", ID_song);
									response.getWriter().write("{\"path\":\"" + res + "\", \"love\":\"" + love + "\"}");
								} catch (Exception e) {
									e.printStackTrace();
								}
							} else {
								String site = "Login.jsp";
								response.setStatus(response.SC_MOVED_TEMPORARILY);
								response.setHeader("Location", site);
							}
						} else {
							String site = "notfound.jsp";
							response.setStatus(response.SC_MOVED_TEMPORARILY);
							response.setHeader("Location", site);
						}
						break;

					case "GETLO":
						if (request.getSession().getAttribute("NAME") != null
								&& request.getSession().getAttribute("PASS") != null) {
							try {

								ArrayList<Playlist> Pll_re = new ArrayList<Playlist>();
								ArrayList<Playlist> Pod_re = new ArrayList<Playlist>();
								ResultSet rs = stmt.executeQuery(
										"Select playlist_id, playlist_img_path, playlist_name, playlist_script, playlist_type from playlist WHERE playlist_type = 'song' LIMIT 4");
								while (rs.next()) {
									Playlist pl = new Playlist();
									pl.setPl_id(rs.getInt(1));
									pl.setPl_img_path(rs.getString(2));
									pl.setPl_name(rs.getString(3));
									pl.setPl_script(rs.getString(4));
									pl.setPl_type(rs.getString(5));
									Pll_re.add(pl);
								}
								rs = stmt.executeQuery(
										"Select playlist_id, playlist_img_path, playlist_name, playlist_script, playlist_type from playlist WHERE playlist_type = 'podcast' LIMIT 4");
								while (rs.next()) {
									Playlist pl = new Playlist();
									pl.setPl_id(rs.getInt(1));
									pl.setPl_img_path(rs.getString(2));
									pl.setPl_name(rs.getString(3));
									pl.setPl_script(rs.getString(4));
									pl.setPl_type(rs.getString(5));
									Pod_re.add(pl);
								}
								ArrayList<Playlist> Pll_fav = new ArrayList<Playlist>();
								ArrayList<Playlist> Pod_fav = new ArrayList<Playlist>();
								ArrayList<Playlist> ALL_PLL_fav = new ArrayList<Playlist>();
								rs = stmt.executeQuery(
										"SELECT playlist.playlist_id, playlist_img_path,playlist_name,playlist_script,playlist_type from playlist right join FavPlaylist on FavPlaylist.playlist_id = playlist.playlist_id where user_id ='"
												+ request.getSession().getAttribute("IDUS")
												+ "'AND playlist.playlist_type = 'song' LIMIT 4;");
								while (rs.next()) {
									Playlist pl_all = new Playlist();
									pl_all.setPl_id(rs.getInt(1));
									pl_all.setPl_img_path(rs.getString(2));
									pl_all.setPl_name(rs.getString(3));
									pl_all.setPl_script(rs.getString(4));
									pl_all.setPl_type(rs.getString(5));
									Pll_fav.add(pl_all);
								}
								rs = stmt.executeQuery(
										"SELECT playlist.playlist_id, playlist_img_path,playlist_name,playlist_script,playlist_type from playlist right join FavPlaylist on FavPlaylist.playlist_id = playlist.playlist_id where user_id ='"
												+ request.getSession().getAttribute("IDUS")
												+ "'AND playlist.playlist_type = 'podcast' LIMIT 4;");
								while (rs.next()) {
									Playlist pl_all = new Playlist();
									pl_all.setPl_id(rs.getInt(1));
									pl_all.setPl_img_path(rs.getString(2));
									pl_all.setPl_name(rs.getString(3));
									pl_all.setPl_script(rs.getString(4));
									pl_all.setPl_type(rs.getString(5));
									Pod_fav.add(pl_all);
								}
								rs = stmt.executeQuery(
										"SELECT playlist.playlist_id, playlist_img_path,playlist_name,playlist_script,playlist_type from playlist right join FavPlaylist on FavPlaylist.playlist_id = playlist.playlist_id where user_id ='"
												+ request.getSession().getAttribute("IDUS") + "';");
								while (rs.next()) {
									Playlist pl_all = new Playlist();
									pl_all.setPl_id(rs.getInt(1));
									pl_all.setPl_img_path(rs.getString(2));
									pl_all.setPl_name(rs.getString(3));
									pl_all.setPl_script(rs.getString(4));
									pl_all.setPl_type(rs.getString(5));
									ALL_PLL_fav.add(pl_all);
								}
								rs = stmt.executeQuery("SELECT media_id from mediaFav where user_id = '"
										+ request.getSession().getAttribute("IDUS") + "'");
								ArrayList<Integer> ID_fav = new ArrayList<Integer>();
								while (rs.next()) {
									ID_fav.add(rs.getInt(1));
								}
								rs = stmt.executeQuery("Select * From Performer LIMIT 4");
								ArrayList<Performer> per = new ArrayList<Performer>();
								while (rs.next()) {
									Performer p = new Performer();
									p.setPer_id(rs.getInt(1));
									p.setImg(rs.getString(2));
									p.setPer_name(rs.getString(3));
									per.add(p);
								}
								rs = stmt.executeQuery("Select * From CreatePlaylist WHERE user_id = '"
										+ request.getSession().getAttribute("IDUS") + "'");
								ArrayList<Playlist_Create> Cre_pll = new ArrayList<Playlist_Create>();
								while (rs.next()) {
									Playlist_Create pl_all = new Playlist_Create();
									pl_all.setPl_id(rs.getString(1));
									pl_all.setPl_img_path("images/music.png");
									pl_all.setPl_name(rs.getString(2));
									pl_all.setPl_script(rs.getString(3));
									Cre_pll.add(pl_all);
								}
								if (Cre_pll.size() > 0) {
									request.getSession().setAttribute("PLL_CRE", Cre_pll);
								} else {
									request.getSession().setAttribute("PLL_CRE", null);
								}
								;
								if (ALL_PLL_fav.size() > 0) {
									request.getSession().setAttribute("ALL_PLL_LEFT", ALL_PLL_fav);
								} else {
									request.getSession().setAttribute("ALL_PLL_LEFT", null);
								}
								;
								if (per.size() > 0) {
									request.getSession().setAttribute("Artist", per);
								} else {
									request.getSession().setAttribute("Artist", null);
								}
								;
								if (ID_fav.size() > 0) {
									request.getSession().setAttribute("Med_fav", ID_fav);
								} else {
									request.getSession().setAttribute("Med_fav", null);
								}
								if (Pll_fav.size() > 0) {
									request.getSession().setAttribute("Pll_fav", Pll_fav);
								} else {
									request.getSession().setAttribute("Pll_fav", null);
								}
								if (Pod_fav.size() > 0) {
									request.getSession().setAttribute("Pod_fav", Pod_fav);
								} else {
									request.getSession().setAttribute("Pod_fav", null);
								}
								request.getSession().setAttribute("count", 1);
								// place có: playlist; artist; songout; podout;
								request.getSession().setAttribute("place", null);
								request.getSession().setAttribute("SufList", null);
								request.getSession().setAttribute("id_isplay", 0);
								request.getSession().setAttribute("loop", "unlo");
								request.getSession().setAttribute("CATE_TYPE", null);
								request.getSession().setAttribute("id_med_his", null);
								request.getSession().setAttribute("song_isplay", null);
								request.getSession().setAttribute("Podcast", Pod_re);
								request.getSession().setAttribute("Playlists", Pll_re);
								request.getSession().setAttribute("suf_song_love", true);
								request.getSession().setAttribute("suf_pod_love", true);
								response.sendRedirect("index.jsp");
							} catch (Exception e) {
								e.printStackTrace();
							}
						} else {
							String site = "notfound.jsp";
							response.setStatus(response.SC_MOVED_TEMPORARILY);
							response.setHeader("Location", site);
						}
						break;

					case "shuffle":
						if (request.getSession().getAttribute("MAIL") != null
								&& request.getSession().getAttribute("PASS") != null) {
							String ID_PLL = request.getParameter("ID");
							String status = request.getParameter("stat");
							if (request.getSession().getAttribute("SufList") != null) {
								if (ID_PLL.equals("Love_song_pll")) {
									boolean suf_song_love = false;
									if (status.equals("suf")) {
										suf_song_love = true;
									} else if (status.equals("unsuf")) {
										suf_song_love = false;
									}
									request.getSession().setAttribute("suf_song_love", suf_song_love);
									response.setContentType("application/json");
									response.setCharacterEncoding("UTF-8");
									response.getWriter().write("{\"stt\":\"" + suf_song_love + "\"}");
								} else if (ID_PLL.equals("Love_pod_pll")) {
									boolean suf_pod_love = false;
									if (status.equals("suf")) {
										suf_pod_love = true;
									} else if (status.equals("unsuf")) {
										suf_pod_love = false;
									}
									request.getSession().setAttribute("suf_pod_love", suf_pod_love);
									response.setContentType("application/json");
									response.setCharacterEncoding("UTF-8");
									response.getWriter().write("{\"stt\":\"" + suf_pod_love + "\"}");
								} else {
									ArrayList<SufOrUnsuf> suf = new ArrayList<SufOrUnsuf>();
									suf = (ArrayList<SufOrUnsuf>) request.getSession().getAttribute("SufList");
									int c = 0;
									int index = 0;
									for (int i = 0; i < suf.size(); i++) {
										if (suf.get(i).id.equals(ID_PLL)) {
											c += 1;
											index = i;
											break;
										}
									}
									if (c > 0) {
										if (status.equals("suf")) {
											suf.get(index).stat = true;
										} else if (status.equals("unsuf")) {
											suf.get(index).stat = false;
										}
										request.getSession().setAttribute("SufList", suf);
										response.setContentType("application/json");
										response.setCharacterEncoding("UTF-8");
										response.getWriter().write("{\"stt\":\"" + suf.get(index).stat + "\"}");
									} else {
										SufOrUnsuf sufmed = new SufOrUnsuf();
										if (status.equals("suf")) {
											sufmed.id = ID_PLL;
											sufmed.stat = true;
										} else if (status.equals("unsuf")) {
											sufmed.id = ID_PLL;
											sufmed.stat = false;
										}
										suf.add(sufmed);
										request.getSession().setAttribute("SufList", suf);
										response.setContentType("application/json");
										response.setCharacterEncoding("UTF-8");
										response.getWriter().write("{\"stt\":\"" + sufmed.stat + "\"}");
									}
								}
							} else {
								if (ID_PLL.equals("Love_song_pll")) {
									boolean suf_song_love = false;
									if (status.equals("suf")) {
										suf_song_love = true;
									} else if (status.equals("unsuf")) {
										suf_song_love = false;
									}
									request.getSession().setAttribute("suf_song_love", suf_song_love);
									response.setContentType("application/json");
									response.setCharacterEncoding("UTF-8");
									response.getWriter().write("{\"stt\":\"" + suf_song_love + "\"}");
								} else if (ID_PLL.equals("Love_pod_pll")) {
									boolean suf_pod_love = false;
									if (status.equals("suf")) {
										suf_pod_love = true;
									} else if (status.equals("unsuf")) {
										suf_pod_love = false;
									}
									request.getSession().setAttribute("suf_pod_love", suf_pod_love);
									response.setContentType("application/json");
									response.setCharacterEncoding("UTF-8");
									response.getWriter().write("{\"stt\":\"" + suf_pod_love + "\"}");
								} else {
									ArrayList<SufOrUnsuf> suf = new ArrayList<SufOrUnsuf>();
									SufOrUnsuf med = new SufOrUnsuf();
									if (status.equals("suf")) {
										med.id = ID_PLL;
										med.stat = true;
									} else if (status.equals("unsuf")) {
										med.id = ID_PLL;
										med.stat = true;
									}
									suf.add(med);
									request.getSession().setAttribute("SufList", suf);
									response.setContentType("application/json");
									response.setCharacterEncoding("UTF-8");
									response.getWriter().write("{\"stt\":\"" + med.stat + "\"}");
								}
							}
						} else {
							String site = "notfound.jsp";
							response.setStatus(response.SC_MOVED_TEMPORARILY);
							response.setHeader("Location", site);
						}
						break;

					case "love":
						try {
							if (request.getSession().getAttribute("MAIL") != null
									&& request.getSession().getAttribute("PASS") != null) {
								String ID_Pll = request.getParameter("ID");
								String stat = request.getParameter("stat");
								System.out.println(ID_Pll + " " + stat);
								ArrayList<Playlist> pll = new ArrayList<Playlist>();
								ArrayList<Playlist> pod = new ArrayList<Playlist>();
								if (request.getSession().getAttribute("Pll_fav") != null) {
									pll = (ArrayList<Playlist>) request.getSession().getAttribute("Pll_fav");
									if (request.getSession().getAttribute("Pod_fav") != null) {
										pod = (ArrayList<Playlist>) request.getSession().getAttribute("Pod_fav");
									} else {

									}
								} else {
									if (request.getSession().getAttribute("Pod_fav") != null) {
										pod = (ArrayList<Playlist>) request.getSession().getAttribute("Pod_fav");
									} else {

									}
								}
								ResultSet rs = stmt.executeQuery(
										"Select playlist_id, playlist_img_path, playlist_name, playlist_script, playlist_type from playlist WHERE playlist_id = '"
												+ ID_Pll + "' ");
								Playlist pl = new Playlist();
								while (rs.next()) {
									pl.Pl_id = rs.getInt(1);
									pl.Pl_img_path = rs.getString(2);
									pl.Pl_name = rs.getString(3);
									pl.Pl_script = rs.getString(4);
									pl.Pl_type = rs.getString(5);
								}
								if (pl.Pl_id == 0) {
									response.setContentType("application/json");
									response.setCharacterEncoding("UTF-8");
									response.getWriter().write("{\"like\":\"error\"}");
								} else {
									if (pl.getPl_type().equals("song")) {
										if (stat.equals("like")) {
											pll.add(pl);
											stmt.execute("INSERT INTO FavPlaylist VALUES ('" + ID_Pll + "', '"
													+ request.getSession().getAttribute("IDUS") + "');");
											request.getSession().setAttribute("Pll_fav", pll);
											response.setContentType("application/json");
											response.setCharacterEncoding("UTF-8");
											response.getWriter().write("{\"stt\":\"like\",\"type\":\"" + pl.getPl_type()
													+ "\",\"size\":" + pll.size() + "}");
										} else if (stat.equals("dislike")) {
											for (int i = 0; i < pll.size(); i++) {
												if (pll.get(i).getPl_id() == Integer.parseInt(ID_Pll)) {
													pll.remove(i);
													break;
												}
											}
											stmt.execute("DELETE FROM FavPlaylist WHERE playlist_id = '" + ID_Pll
													+ "' AND user_id = '" + request.getSession().getAttribute("IDUS")
													+ "'");
											request.getSession().setAttribute("Pll_fav", pll);
											response.setContentType("application/json");
											response.setCharacterEncoding("UTF-8");
											response.getWriter().write("{\"stt\":\"dislike\",\"type\":\""
													+ pl.getPl_type() + "\",\"size\":" + pll.size() + "}");
										}
									} else if (pl.getPl_type().equals("podcast")) {
										if (stat.equals("like")) {
											pod.add(pl);
											stmt.execute("INSERT INTO FavPlaylist VALUES ('" + ID_Pll + "', '"
													+ request.getSession().getAttribute("IDUS") + "');");
											request.getSession().setAttribute("Pod_fav", pod);
											response.setContentType("application/json");
											response.setCharacterEncoding("UTF-8");
											response.getWriter().write("{\"stt\":\"like\",\"type\":\"" + pl.getPl_type()
													+ "\",\"size\":" + pod.size() + "}");
										} else if (stat.equals("dislike")) {
											for (int i = 0; i < pod.size(); i++) {
												if (pod.get(i).getPl_id() == Integer.parseInt(ID_Pll)) {
													System.out.println(pod.get(i).getPl_id());
													pod.remove(i);
													break;
												}
											}
											stmt.execute("DELETE FROM FavPlaylist WHERE playlist_id = '" + ID_Pll
													+ "' AND user_id = '" + request.getSession().getAttribute("IDUS")
													+ "'");
											request.getSession().setAttribute("Pod_fav", pod);
											response.setContentType("application/json");
											response.setCharacterEncoding("UTF-8");
											response.getWriter().write("{\"stt\":\"dislike\",\"type\":\""
													+ pl.getPl_type() + "\",\"size\":" + pod.size() + "}");

										}
									}
								}
							} else {
								String site = "notfound.jsp";
								response.setStatus(response.SC_MOVED_TEMPORARILY);
								response.setHeader("Location", site);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;

					case "SeeMore":
						if (request.getSession().getAttribute("MAIL") != null
								&& request.getSession().getAttribute("PASS") != null) {
							String type = request.getParameter("TYPE");
							try {
								ResultSet rs;
								switch (type) {
									// playlist nhạc yêu thích
									case "see_more_pll":
										String data = "{\"playlist\":[";
										rs = stmt.executeQuery(
												"SELECT playlist.playlist_id, playlist_img_path,playlist_name,playlist_script,playlist_type from playlist right join FavPlaylist on"
														+ "FavPlaylist.playlist_id = playlist.playlist_id where user_id ='"
														+ request.getSession().getAttribute("IDUS")
														+ "'AND playlist.playlist_type = 'song'");
										while (rs.next()) {
											data = data + "{\"ID\":\"" + rs.getString(1) + "\",\"img\":\""
													+ rs.getString(2) + "\",\"name\": \"" + rs.getString(3)
													+ "\", \"script\": \"" + rs.getString(4) + "\", \"type\":\""
													+ rs.getString(5) + "\"}";
											if (rs.isLast() != true) {
												data = data + ",";
											} else {

											}
										}
										data = data + "], \"pll\":\"MusLife Playlist\"}";
										System.out.println(data);
										response.setContentType("application/json");
										response.setCharacterEncoding("UTF-8");
										response.getWriter().write(data);
										break;
									// playlist podcast yêu thích
									case "see_more_pod":
										String datapll2 = "{\"playlist\":[";
										rs = stmt.executeQuery(
												"SELECT playlist.playlist_id, playlist_img_path,playlist_name,playlist_script,playlist_type"
														+ " from playlist right join FavPlaylist on FavPlaylist.playlist_id = playlist.playlist_id where user_id ='"
														+ request.getSession().getAttribute("IDUS")
														+ "'AND playlist.playlist_type = 'podcast';");
										while (rs.next()) {
											datapll2 = datapll2 + "{\"ID\":\"" + rs.getString(1) + "\",\"img\":\""
													+ rs.getString(2) + "\",\"name\": \"" + rs.getString(3)
													+ "\", \"script\": \"" + rs.getString(4) + "\", \"type\":\""
													+ rs.getString(5) + "\"}";
											if (rs.isLast() != true) {
												datapll2 = datapll2 + ",";
											} else {

											}
										}
										datapll2 = datapll2 + "], \"pll\":\"MusLife Podcast\"}";
										System.out.println(datapll2);
										response.setContentType("application/json");
										response.setCharacterEncoding("UTF-8");
										response.getWriter().write(datapll2);
										break;
									// playlist nhạc đề xuất
									case "see_more_pll_re":
										String datapll3 = "{\"playlist\":[";
										rs = stmt.executeQuery(
												"Select playlist_id, playlist_img_path, playlist_name, playlist_script, playlist_type from playlist"
														+ " WHERE playlist_type = 'song';");
										while (rs.next()) {
											datapll3 = datapll3 + "{\"ID\":\"" + rs.getString(1) + "\",\"img\":\""
													+ rs.getString(2) + "\",\"name\": \"" + rs.getString(3)
													+ "\", \"script\": \"" + rs.getString(4) + "\", \"type\":\""
													+ rs.getString(5) + "\"}";
											if (rs.isLast() != true) {
												datapll3 = datapll3 + ",";
											} else {

											}
										}
										datapll3 = datapll3 + "], \"pll\":\"Recommend Playlist\"}";
										System.out.println(datapll3);
										response.setContentType("application/json");
										response.setCharacterEncoding("UTF-8");
										response.getWriter().write(datapll3);
										break;
									// playlist podcast đề xuất
									case "see_more_pod_re":
										String datapll4 = "{\"playlist\":[";
										rs = stmt.executeQuery(
												"Select playlist_id, playlist_img_path, playlist_name, playlist_script, playlist_type from playlist WHERE playlist_type = 'podcast';");
										while (rs.next()) {
											datapll4 = datapll4 + "{\"ID\":\"" + rs.getString(1) + "\",\"img\":\""
													+ rs.getString(2) + "\",\"name\": \"" + rs.getString(3)
													+ "\", \"script\": \"" + rs.getString(4) + "\", \"type\":\""
													+ rs.getString(5) + "\"}";
											if (rs.isLast() != true) {
												datapll4 = datapll4 + ",";
											} else {

											}
										}
										datapll4 = datapll4 + "], \"pll\":\"Recommend Podcast\"}";
										System.out.println(datapll4);
										response.setContentType("application/json");
										response.setCharacterEncoding("UTF-8");
										response.getWriter().write(datapll4);
										break;

									case "see_more_artist":
										String artist = "{\"artist\":[";
										rs = stmt.executeQuery("Select * from Performer;");
										while (rs.next()) {
											artist = artist + "{\"ID\":\"" + rs.getString(1) + "\",\"img\":\""
													+ rs.getString(2) + "\",\"name\": \"" + rs.getString(3) + "\"}";
											if (rs.isLast() != true) {
												artist = artist + ",";
											} else {

											}
										}
										artist = artist + "], \"pll\":\"Artist\"}";
										System.out.println(artist);
										response.setContentType("application/json");
										response.setCharacterEncoding("UTF-8");
										response.getWriter().write(artist);
										break;

								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						} else {
							String site = "notfound.jsp";
							response.setStatus(response.SC_MOVED_TEMPORARILY);
							response.setHeader("Location", site);
						}
						break;

					case "love_med":
						if (request.getSession().getAttribute("MAIL") != null
								&& request.getSession().getAttribute("PASS") != null) {
							String getstat = request.getParameter("stat");
							String getID = request.getParameter("ID");
							int Idsong_isplay = 0;
							ArrayList<Integer> like = new ArrayList<Integer>();
							if (request.getSession().getAttribute("Med_fav") != null) {
								like.addAll((ArrayList<Integer>) request.getSession().getAttribute("Med_fav"));
							}
							if (request.getSession().getAttribute("song_isplay") != null) {
								Idsong_isplay = Integer
										.parseInt(request.getSession().getAttribute("song_isplay").toString());
							}
							switch (getstat) {
								case "dislike":
									if (like.size() > 0) {
										if (like.contains(Integer.parseInt(getID)) == true) {
											try {
												for (int i = 0; i < like.size(); i++) {
													if (like.get(i) == Integer.parseInt(getID)) {
														like.remove(i);
														break;
													}
												}
												stmt.executeUpdate("DELETE FROM mediaFav where user_id = '"
														+ request.getSession().getAttribute("IDUS")
														+ "' AND media_id = '" + getID + "'");
												request.getSession().setAttribute("Med_fav", like);
												response.setContentType("application/json");
												response.setCharacterEncoding("UTF-8");
												if (Idsong_isplay == 0) {
													response.getWriter()
															.write("{\"stat_song\":\"dislike\",\"ispl\":\"none\"}");
												} else {
													if (Integer.parseInt(getID) == Idsong_isplay) {
														response.getWriter()
																.write("{\"stat_song\":\"dislike\",\"ispl\":\"true\"}");
													} else if (Integer.parseInt(getID) != Idsong_isplay) {
														response.getWriter().write(
																"{\"stat_song\":\"dislike\",\"ispl\":\"false\"}");
													}
												}
											} catch (Exception e) {
												e.printStackTrace();
											}
										} else {
											try {
												for (int i = 0; i < like.size(); i++) {
													if (like.get(i) == Integer.parseInt(getID)) {
														like.remove(i);
														break;
													}
												}
												stmt.executeUpdate("DELETE FROM mediaFav where user_id = '"
														+ request.getSession().getAttribute("IDUS")
														+ "' AND media_id = '" + getID + "'");
												request.getSession().setAttribute("Med_fav", like);
												response.setContentType("application/json");
												response.setCharacterEncoding("UTF-8");
												if (Idsong_isplay == 0) {
													response.getWriter()
															.write("{\"stat_song\":\"dislike\",\"ispl\":\"none\"}");
												} else {
													if (Integer.parseInt(getID) == Idsong_isplay) {
														response.getWriter()
																.write("{\"stat_song\":\"dislike\",\"ispl\":\"true\"}");
													} else if (Integer.parseInt(getID) != Idsong_isplay) {
														response.getWriter().write(
																"{\"stat_song\":\"dislike\",\"ispl\":\"false\"}");
													}
												}
											} catch (Exception e) {
												e.printStackTrace();
											}
										}
									}

									break;

								case "like":
									if (like.size() > 0) {
										if (like.contains(Integer.parseInt(getID)) == true) {
											try {
												like.add(Integer.parseInt(getID));
												stmt.executeUpdate("Insert INTO mediaFav VALUES (" + getID + ",'"
														+ request.getSession().getAttribute("IDUS") + "')");
												request.getSession().setAttribute("Med_fav", like);
												response.setContentType("application/json");
												response.setCharacterEncoding("UTF-8");
												if (Idsong_isplay == 0) {
													response.getWriter()
															.write("{\"stat_song\":\"like\",\"ispl\":\"none\"}");
												} else {
													if (Integer.parseInt(getID) == Idsong_isplay) {
														response.getWriter()
																.write("{\"stat_song\":\"like\",\"ispl\":\"true\"}");
													} else if (Integer.parseInt(getID) != Idsong_isplay) {
														response.getWriter()
																.write("{\"stat_song\":\"like\",\"ispl\":\"false\"}");
													}
												}
											} catch (Exception e) {
												e.printStackTrace();
											}
										} else {
											try {
												like.add(Integer.parseInt(getID));
												stmt.executeUpdate("Insert INTO mediaFav VALUES ('" + getID + "','"
														+ request.getSession().getAttribute("IDUS") + "')");
												request.getSession().setAttribute("Med_fav", like);
												response.setContentType("application/json");
												response.setCharacterEncoding("UTF-8");
												if (Idsong_isplay == 0) {
													response.getWriter()
															.write("{\"stat_song\":\"like\",\"ispl\":\"none\"}");
												} else {
													if (Integer.parseInt(getID) == Idsong_isplay) {
														response.getWriter()
																.write("{\"stat_song\":\"like\",\"ispl\":\"true\"}");
													} else if (Integer.parseInt(getID) != Idsong_isplay) {
														response.getWriter()
																.write("{\"stat_song\":\"like\",\"ispl\":\"false\"}");
													}
												}
											} catch (Exception e) {
												e.printStackTrace();
											}
										}
									} else {
										try {
											like.add(Integer.parseInt(getID));
											stmt.executeUpdate("Insert INTO mediaFav VALUES (" + getID + ",'"
													+ request.getSession().getAttribute("IDUS") + "')");
											request.getSession().setAttribute("Med_fav", like);
											response.setContentType("application/json");
											response.setCharacterEncoding("UTF-8");
											if (Idsong_isplay == 0) {
												response.getWriter()
														.write("{\"stat_song\":\"like\",\"ispl\":\"none\"}");
											} else {
												if (Integer.parseInt(getID) == Idsong_isplay) {
													response.getWriter()
															.write("{\"stat_song\":\"like\",\"ispl\":\"true\"}");
												} else if (Integer.parseInt(getID) != Idsong_isplay) {
													response.getWriter()
															.write("{\"stat_song\":\"like\",\"ispl\":\"false\"}");
												}
											}
										} catch (Exception e) {
											e.printStackTrace();
										}
									}

									break;
							}
						} else {
							String site = "notfound.jsp";
							response.setStatus(response.SC_MOVED_TEMPORARILY);
							response.setHeader("Location", site);
						}

						break;
					// đang làm!!!
					case "Search":
						if (request.getSession().getAttribute("MAIL") != null
								&& request.getSession().getAttribute("PASS") != null) {
							String reque = request.getParameter("search");
							String cate = request.getParameter("Cate");
							try {
								ArrayList<Integer> ID_lo_med = new ArrayList<Integer>();
								if (request.getSession().getAttribute("Med_fav") != null) {
									ID_lo_med = (ArrayList<Integer>) request.getSession().getAttribute("Med_fav");
								}
								ResultSet rs;
								String data = "{\"error\":\"false\",";
								switch (cate) {
									case "All":
										if (reque == "") {
											response.setContentType("application/json");
											response.setCharacterEncoding("UTF-8");
											response.getWriter().write("{\"error\":\"none\"}");
										} else {
											data = data + "\"status\":\"All\",";
											data = data + "\"song\":[";
											rs = stmt.executeQuery(
													"SELECT * FROM media where types = 'song' and media_name like '%"
															+ reque + "%'");
											while (rs.next()) {
												if (ID_lo_med.contains(rs.getInt(1)) == true) {
													data = data + "{\"love\":\"true\",\"ID\":\"" + rs.getInt(1)
															+ "\", \"img\":\"" + rs.getString(2) + "\", \"name\": \""
															+ rs.getString(3) + "\",\"per\":\"" + rs.getString(4)
															+ "\"}";
												} else {
													data = data + "{\"love\":\"false\",\"ID\":\"" + rs.getInt(1)
															+ "\", \"img\":\"" + rs.getString(2) + "\", \"name\": \""
															+ rs.getString(3) + "\",\"per\":\"" + rs.getString(4)
															+ "\"}";
												}
												if (rs.isLast() != true) {
													data = data + ",";
												}
											}
											data = data + "],";

											data = data + "\"podcast\":[";
											rs = stmt.executeQuery(
													"SELECT * FROM media where types = 'podcast' and media_name like '%"
															+ reque + "%'");
											while (rs.next()) {
												if (ID_lo_med.contains(rs.getInt(1)) == true) {
													data = data + "{\"love\":\"true\",\"ID\":\"" + rs.getInt(1)
															+ "\", \"img\":\"" + rs.getString(2) + "\", \"name\": \""
															+ rs.getString(3) + "\",\"per\":\"" + rs.getString(4)
															+ "\"}";
												} else {
													data = data + "{\"love\":\"false\",\"ID\":\"" + rs.getInt(1)
															+ "\", \"img\":\"" + rs.getString(2) + "\", \"name\": \""
															+ rs.getString(3) + "\",\"per\":\"" + rs.getString(4)
															+ "\"}";
												}
												if (rs.isLast() != true) {
													data = data + ",";
												}
											}
											data = data + "],";

											data = data + "\"songPll\":[";
											rs = stmt.executeQuery(
													"SELECT * FROM playlist where playlist_type = 'song' and playlist_name like '%"
															+ reque + "%'");
											while (rs.next()) {
												data = data + "{\"id\": \"" + rs.getInt(1) + "\", \"img\": \""
														+ rs.getString(2) + "\",\"name\": \"" + rs.getString(3)
														+ "\",\"script\":\"" + rs.getString(4) + "\"}";
												if (rs.isLast() != true) {
													data = data + ",";
												} else {
												}
											}
											data = data + "],";

											data = data + "\"podPll\":[";
											rs = stmt.executeQuery(
													"SELECT * FROM playlist where playlist_type = 'podcast' and playlist_name like '%"
															+ reque + "%'");
											while (rs.next()) {
												data = data + "{\"id\": \"" + rs.getInt(1) + "\", \"img\": \""
														+ rs.getString(2) + "\",\"name\": \"" + rs.getString(3)
														+ "\",\"script\":\"" + rs.getString(4) + "\"}";
												if (rs.isLast() != true) {
													data = data + ",";
												} else {

												}
											}
											data = data + "],";
											data = data + "\"perfomers\": [";
											rs = stmt.executeQuery(
													"SELECT * FROM Performer where per_name like '%" + reque + "%'");
											while (rs.next()) {
												data = data + "{\"id\": \"" + rs.getInt(1) + "\", \"img\": \""
														+ rs.getString(2) + "\",\"name\": \"" + rs.getString(3) + "\"}";
												if (rs.isLast() != true) {
													data = data + ",";
												} else {

												}
											}
											data = data + "]}";
											System.out.println(data);
											response.setContentType("application/json");
											response.setCharacterEncoding("UTF-8");
											response.getWriter().write(data);
										}

										break;
									case "Song":
										if (reque == "") {
											response.setContentType("application/json");
											response.setCharacterEncoding("UTF-8");
											response.getWriter().write("{\"error\":\"none\"}");
										} else {
											data = data + "\"status\":\"Song\",";
											String song = "\"song\":[";
											rs = stmt.executeQuery(
													"SELECT * FROM media where types = 'song' and media_name like '%"
															+ reque + "%'");
											while (rs.next()) {
												if (ID_lo_med.contains(rs.getInt(1)) == true) {
													song = song + "{\"love\":\"true\",\"ID\":\"" + rs.getInt(1)
															+ "\", \"img\":\"" + rs.getString(2) + "\", \"name\": \""
															+ rs.getString(3) + "\",\"per\":\"" + rs.getString(4)
															+ "\"}";
												} else {
													song = song + "{\"love\":\"false\",\"ID\":\"" + rs.getInt(1)
															+ "\", \"img\":\"" + rs.getString(2) + "\", \"name\": \""
															+ rs.getString(3) + "\",\"per\":\"" + rs.getString(4)
															+ "\"}";
												}
												if (rs.isLast() != true) {
													song = song + ",";
												}
											}
											song = song + "]";
											data = data + song + "}";
											response.setContentType("application/json");
											response.setCharacterEncoding("UTF-8");
											response.getWriter().write(data);

										}
										break;

									case "Podcast":
										if (reque == "") {
											response.setContentType("application/json");
											response.setCharacterEncoding("UTF-8");
											response.getWriter().write("{\"error\":\"none\"}");
										} else {
											data = data + "\"status\":\"Podcast\",";
											String Podcast = "\"podcast\":[";
											rs = stmt.executeQuery(
													"SELECT * FROM media where types = 'podcast' and media_name like '%"
															+ reque + "%'");
											while (rs.next()) {
												if (ID_lo_med.contains(rs.getInt(1)) == true) {
													Podcast = Podcast + "{\"love\":\"true\",\"ID\":\"" + rs.getInt(1)
															+ "\", \"img\":\"" + rs.getString(2) + "\", \"name\": \""
															+ rs.getString(3) + "\",\"per\":\"" + rs.getString(4)
															+ "\"}";
												} else {
													Podcast = Podcast + "{\"love\":\"false\",\"ID\":\"" + rs.getInt(1)
															+ "\", \"img\":\"" + rs.getString(2) + "\", \"name\": \""
															+ rs.getString(3) + "\",\"per\":\"" + rs.getString(4)
															+ "\"}";
												}
												if (rs.isLast() != true) {
													Podcast = Podcast + ",";
												}
											}
											Podcast = Podcast + "]";
											data = data + Podcast + "}";
											response.setContentType("application/json");
											response.setCharacterEncoding("UTF-8");
											response.getWriter().write(data);

										}
										break;

									case "Playlist":
										if (reque == "") {
											response.setContentType("application/json");
											response.setCharacterEncoding("UTF-8");
											response.getWriter().write("{\"error\":\"none\"}");
										} else {
											data = data + "\"status\":\"Playlist\",";
											String SongPpl = "\"songPll\":[";
											rs = stmt.executeQuery(
													"SELECT * FROM playlist where playlist_type = 'song' and playlist_name like '%"
															+ reque + "%'");
											while (rs.next()) {
												SongPpl = SongPpl + "{\"id\": \"" + rs.getInt(1) + "\", \"img\": \""
														+ rs.getString(2) + "\",\"name\": \"" + rs.getString(3)
														+ "\",\"script\":\"" + rs.getString(4) + "\"}";
												if (rs.isLast() != true) {
													SongPpl = SongPpl + ",";
												} else {
												}
											}

											SongPpl = SongPpl + "],";
											String PodPpl = "\"podPll\":[";
											rs = stmt.executeQuery(
													"SELECT * FROM playlist where playlist_type = 'podcast' and playlist_name like '%"
															+ reque + "%'");
											while (rs.next()) {
												PodPpl = PodPpl + "{\"id\": \"" + rs.getInt(1) + "\", \"img\": \""
														+ rs.getString(2) + "\",\"name\": \"" + rs.getString(3)
														+ "\",\"script\":\"" + rs.getString(4) + "\"}";
												if (rs.isLast() != true) {
													PodPpl = PodPpl + ",";
												} else {

												}
											}
											PodPpl = PodPpl + "]";
											data = data + SongPpl + PodPpl + "}";
											response.setContentType("application/json");
											response.setCharacterEncoding("UTF-8");
											response.getWriter().write(data);

										}
										break;

									case "Artist":
										if (reque == "") {
											response.setContentType("application/json");
											response.setCharacterEncoding("UTF-8");
											response.getWriter().write("{\"error\":\"none\"}");
										} else {
											data = data + "\"status\":\"Artist\",";
											String Performer = "\"perfomers\": [";
											rs = stmt.executeQuery(
													"SELECT * FROM Performer where per_name like '%" + reque + "%';");
											while (rs.next()) {
												Performer = Performer + "{\"id\": \"" + rs.getInt(1) + "\", \"img\": \""
														+ rs.getString(2) + "\",\"name\": \"" + rs.getString(3) + "\"}";
												if (rs.isLast() != true) {
													Performer = Performer + ",";
												} else {

												}
											}
											Performer = Performer + "]";
											data = data + Performer + "}";
											System.out.println(data);
											response.setContentType("application/json");
											response.setCharacterEncoding("UTF-8");
											response.getWriter().write(data);

										}
										break;

									default:
										response.setContentType("application/json");
										response.setCharacterEncoding("UTF-8");
										response.getWriter().write("{\"error\":\"true\"}");
										break;
								}
							} catch (Exception e) {
								e.printStackTrace();
							}

						} else {
							response.setContentType("application/json");
							response.setCharacterEncoding("UTF-8");
							response.getWriter().write("{\"error\":\"true\"}");
						}
						break;

					case "openartist":
						if (request.getSession().getAttribute("MAIL") != null
								&& request.getSession().getAttribute("PASS") != null) {
							try {
								int getId = Integer.parseInt(request.getParameter("ID_art"));
								ArrayList<Integer> ID_lo_med = new ArrayList<Integer>();
								if (request.getSession().getAttribute("Med_fav") != null) {
									ID_lo_med = (ArrayList<Integer>) request.getSession().getAttribute("Med_fav");
								}
								ResultSet rs = stmt.executeQuery(
										"SELECT media.id , media.img_path, media.media_name, media.performer, media.media_song_categories, media.year, media.types FROM Per_med right join media on Per_med.media_id = media.id where Per_med.per_id = "
												+ getId + " ;");
								String data = "{\"song\":[";
								while (rs.next()) {
									if (ID_lo_med.contains(rs.getInt(1))) {
										data = data + "{\"ID\":\"" + rs.getInt(1) + "\",\"like\":\"true\",\"img\":\""
												+ rs.getString(2) + "\",\"name\": \"" + rs.getString(3)
												+ "\", \"per\": \"" + rs.getString(4) + "\", \"cate\":\""
												+ rs.getString(5) + "\", \"year\": \"" + rs.getString(6)
												+ "\",\"type\": \"" + rs.getString(7) + "\"}";
										if (rs.isLast() != true) {
											data = data + ",";
										} else {

										}
									} else {
										data = data + "{\"ID\":\"" + rs.getInt(1) + "\",\"like\":\"false\",\"img\":\""
												+ rs.getString(2) + "\",\"name\": \"" + rs.getString(3)
												+ "\", \"per\": \"" + rs.getString(4) + "\", \"cate\":\""
												+ rs.getString(5) + "\", \"year\": \"" + rs.getString(6)
												+ "\",\"type\": \"" + rs.getString(7) + "\"}";
										if (rs.isLast() != true) {
											data = data + ",";
										} else {

										}
									}
								}
								data = data + "]}";
								System.out.println(data);
								response.setContentType("application/json");
								response.setCharacterEncoding("UTF-8");
								response.getWriter().write(data);
							} catch (Exception e) {

							}
						} else {
							String site = "notfound.jsp";
							response.setStatus(response.SC_MOVED_TEMPORARILY);
							response.setHeader("Location", site);
						}
						break;
					case "NEXT":
						if (request.getSession().getAttribute("MAIL") != null
								&& request.getSession().getAttribute("PASS") != null) {
							if (request.getSession().getAttribute("song_isplay") != null) {
								int med_play_id = 0;
								String place = null;
								String id_ispl = "";
								String id_string = "";
								boolean check_id_string = false;
								ArrayList<Integer> listmed_is_playing = new ArrayList<Integer>();
								String loop = "unlo";
								ArrayList<SufOrUnsuf> suflist = new ArrayList<SufOrUnsuf>();
								if (request.getSession().getAttribute("SufList") != null) {
									suflist.addAll(
											(ArrayList<SufOrUnsuf>) request.getSession().getAttribute("SufList"));
								}
								if (request.getSession().getAttribute("id_med_his") != null) {
									listmed_is_playing.addAll(
											(ArrayList<Integer>) request.getSession().getAttribute("id_med_his"));
								}

								if (request.getSession().getAttribute("loop") != null) {
									loop = request.getSession().getAttribute("loop").toString();
								}
								if (request.getSession().getAttribute("place") != null) {
									place = request.getSession().getAttribute("place").toString();
								}
								if (request.getSession().getAttribute("id_isplay") != null) {
									String get = request.getSession().getAttribute("id_isplay").toString();
									if (get.equals("Love_song_pll")) {
										id_string = "Love_song_pll";
										check_id_string = true;
									} else if (get.equals("Love_pod_pll")) {
										id_string = "Love_pod_pll";
										check_id_string = true;
									} else {
										id_ispl = get;
									}
								}
								if (request.getSession().getAttribute("song_isplay") != null) {
									med_play_id = Integer
											.parseInt(request.getSession().getAttribute("song_isplay").toString());
								}
								System.out.println(med_play_id);
								switch (place) {

									case "playlist":
										if (check_id_string == false) {
											int index_med = 0;
											for (int i = 0; i < listmed_is_playing.size(); i++) {
												if (listmed_is_playing.get(i) == med_play_id) {
													index_med = i;
													System.out.println(index_med);
													break;
												}
											}
											boolean suf = false;
											if (suflist.size() > 0) {
												for (int i = 0; i < suflist.size(); i++) {
													if (suflist.get(i).id.equals("" + id_ispl)
															&& suflist.get(i).stat == true) {
														suf = true;
														System.out.println("yes");
														break;
													}
													System.out.println("no");
												}
											} else {
												SufOrUnsuf newpll = new SufOrUnsuf();
												newpll.id = "" + id_ispl;
												newpll.stat = false;
												suflist.add(newpll);
												request.getSession().setAttribute("SufList", suflist);
											}

											if (suf == true) {
												if (loop.equals("unlo")) {
													if (index_med + 1 == listmed_is_playing.size()) {
														System.out.println("un loop shuffle pll end list");
														Random random = new Random();
														int newId = listmed_is_playing.get(index_med);
														if (listmed_is_playing.size() > 1) {
															while (newId == listmed_is_playing.get(index_med)) {
																int ran_index = random
																		.nextInt(listmed_is_playing.size());
																newId = listmed_is_playing.get(ran_index);
															}
														} else {
															newId = listmed_is_playing.get(index_med);
														}
														request.getSession().setAttribute("song_isplay", newId);
														try {
															ResultSet rs = stmt.executeQuery(
																	"SELECT img_path,media_name,performer, file_path from media where id = '"
																			+ newId + "'");
															String resImg = null;
															String resName = null;
															String resPer = null;
															String resFile = null;
															while (rs.next()) {
																resImg = rs.getString(1);
																resName = rs.getString(2);
																resPer = rs.getString(3);
																resFile = rs.getString(4);
															}
															ArrayList<Integer> luv = new ArrayList<Integer>();
															if (request.getSession().getAttribute("Med_fav") != null) {
																luv = (ArrayList<Integer>) request.getSession()
																		.getAttribute("Med_fav");
															}
															boolean love = false;
															if (luv.contains(newId)) {
																love = true;
															}
															response.setContentType("application/json");
															response.setCharacterEncoding("UTF-8");
															response.getWriter().write("{\"status\":\"true\",\"Img\":\""
																	+ resImg + "\",\"Name\":\"" + resName
																	+ "\",\"Per\":\"" + resPer + "\",\"path\":\""
																	+ resFile + "\", \"love\":\"" + love + "\"}");
														} catch (Exception e) {
															e.printStackTrace();
														}
													} else {
														System.out.println("un loop shuffle pll in list");
														Random random = new Random();
														int newId = listmed_is_playing.get(index_med);
														if (listmed_is_playing.size() > 1) {
															while (newId == listmed_is_playing.get(index_med)) {
																int ran_index = random
																		.nextInt(listmed_is_playing.size());
																newId = listmed_is_playing.get(ran_index);
															}
														} else {
															newId = random.nextInt(listmed_is_playing.size());
														}
														request.getSession().setAttribute("song_isplay", newId);
														try {
															ResultSet rs = stmt.executeQuery(
																	"SELECT img_path,media_name,performer, file_path from media where id = '"
																			+ newId + "';");
															String resImg = null;
															String resName = null;
															String resPer = null;
															String resFile = null;
															while (rs.next()) {
																resImg = rs.getString(1);
																resName = rs.getString(2);
																resPer = rs.getString(3);
																resFile = rs.getString(4);
															}
															ArrayList<Integer> luv = new ArrayList<Integer>();
															if (request.getSession().getAttribute("Med_fav") != null) {
																luv = (ArrayList<Integer>) request.getSession()
																		.getAttribute("Med_fav");
															}
															boolean love = false;
															if (luv.contains(newId)) {
																love = true;
															}
															response.setContentType("application/json");
															response.setCharacterEncoding("UTF-8");
															response.getWriter().write("{\"status\":\"true\",\"Img\":\""
																	+ resImg + "\",\"Name\":\"" + resName
																	+ "\",\"Per\":\"" + resPer + "\",\"path\":\""
																	+ resFile + "\", \"love\":\"" + love + "\"}");
														} catch (Exception e) {
															e.printStackTrace();
														}
													}
												} else if (loop.equals("lall")) {
													if (index_med + 1 == listmed_is_playing.size()) {
														System.out.println("loop all shuffle end list");
														Random random = new Random();
														int new_id = listmed_is_playing.get(index_med);
														if (listmed_is_playing.size() > 1) {
															while (new_id == listmed_is_playing.get(index_med)) {
																int ran_index = random
																		.nextInt(listmed_is_playing.size());
																new_id = listmed_is_playing.get(ran_index);
															}
														} else {
															new_id = random.nextInt(listmed_is_playing.size());
														}
														request.getSession().setAttribute("song_isplay", new_id);
														try {
															ResultSet rs = stmt.executeQuery(
																	"SELECT img_path,media_name,performer, file_path from media where id = '"
																			+ new_id + "'");
															String resImg = null;
															String resName = null;
															String resPer = null;
															String resFile = null;
															while (rs.next()) {
																resImg = rs.getString(1);
																resName = rs.getString(2);
																resPer = rs.getString(3);
																resFile = rs.getString(4);
															}
															ArrayList<Integer> luv = new ArrayList<Integer>();
															if (request.getSession().getAttribute("Med_fav") != null) {
																luv = (ArrayList<Integer>) request.getSession()
																		.getAttribute("Med_fav");
															}
															boolean love = false;
															if (luv.contains(new_id)) {
																love = true;
															}
															response.setContentType("application/json");
															response.setCharacterEncoding("UTF-8");
															response.getWriter().write("{\"status\":\"true\",\"Img\":\""
																	+ resImg + "\",\"Name\":\"" + resName
																	+ "\",\"Per\":\"" + resPer + "\",\"path\":\""
																	+ resFile + "\", \"love\":\"" + love + "\"}");
														} catch (Exception e) {
															e.printStackTrace();
														}

													} else {
														System.out.println("loop all shuffle in list");
														Random random = new Random();
														int newId = listmed_is_playing.get(index_med);
														if (listmed_is_playing.size() > 1) {
															while (newId == listmed_is_playing.get(index_med)) {
																int ran_index = random
																		.nextInt(listmed_is_playing.size());
																newId = listmed_is_playing.get(ran_index);
															}
														} else {
															newId = random.nextInt(listmed_is_playing.size());
														}

														request.getSession().setAttribute("song_isplay", newId);
														try {
															ResultSet rs = stmt.executeQuery(
																	"SELECT img_path,media_name,performer, file_path from media where id = '"
																			+ newId + "'");
															String resImg = null;
															String resName = null;
															String resPer = null;
															String resFile = null;
															while (rs.next()) {
																resImg = rs.getString(1);
																resName = rs.getString(2);
																resPer = rs.getString(3);
																resFile = rs.getString(4);
															}
															ArrayList<Integer> luv = new ArrayList<Integer>();
															if (request.getSession().getAttribute("Med_fav") != null) {
																luv = (ArrayList<Integer>) request.getSession()
																		.getAttribute("Med_fav");
															}
															boolean love = false;
															if (luv.contains(newId)) {
																love = true;
															}
															response.setContentType("application/json");
															response.setCharacterEncoding("UTF-8");
															response.getWriter().write("{\"status\":\"true\",\"Img\":\""
																	+ resImg + "\",\"Name\":\"" + resName
																	+ "\",\"Per\":\"" + resPer + "\",\"path\":\""
																	+ resFile + "\", \"love\":\"" + love + "\"}");
														} catch (Exception e) {
															e.printStackTrace();
														}
													}

												} else if (loop.equals("lon")) {
													System.out.println("loop one shuffle in list");
													response.setContentType("application/json");
													response.setCharacterEncoding("UTF-8");
													response.getWriter().write("{\"status\":\"false\"}");
												}

											} else {
												if (loop.equals("unlo")) {
													if (index_med + 1 == listmed_is_playing.size()) {
														System.out.println("unloop not shuffle end pll");
														response.setContentType("application/json");
														response.setCharacterEncoding("UTF-8");
														response.getWriter().write("{\"status\":\"stop\"}");
													} else {
														int newId1 = listmed_is_playing.get(index_med + 1);
														System.out.println("unloop not shuffle in pll");
														System.out.println("New id: " + newId1);
														request.getSession().setAttribute("song_isplay", newId1);
														try {
															ResultSet rs = stmt.executeQuery(
																	"SELECT img_path,media_name,performer, file_path from media where id = '"
																			+ newId1 + "';");
															String resImg = null;
															String resName = null;
															String resPer = null;
															String resFile = null;
															while (rs.next()) {
																resImg = rs.getString(1);
																resName = rs.getString(2);
																resPer = rs.getString(3);
																resFile = rs.getString(4);
															}
															ArrayList<Integer> luv = new ArrayList<Integer>();
															if (request.getSession().getAttribute("Med_fav") != null) {
																luv = (ArrayList<Integer>) request.getSession()
																		.getAttribute("Med_fav");
															}
															boolean love = false;
															if (luv.contains(newId1)) {
																love = true;
															}
															response.setContentType("application/json");
															response.setCharacterEncoding("UTF-8");
															response.getWriter().write("{\"status\":\"true\",\"Img\":\""
																	+ resImg + "\",\"Name\":\"" + resName
																	+ "\",\"Per\":\"" + resPer + "\",\"path\":\""
																	+ resFile + "\", \"love\":\"" + love + "\"}");
														} catch (Exception e) {
															e.printStackTrace();
														}
													}
												} else if (loop.equals("lall")) {
													if (index_med + 1 == listmed_is_playing.size()) {
														int new_id = listmed_is_playing.get(0);
														request.getSession().setAttribute("song_isplay", new_id);
														try {
															ResultSet rs = stmt.executeQuery(
																	"SELECT img_path,media_name,performer, file_path from media where id = '"
																			+ new_id + "'");
															String resImg = null;
															String resName = null;
															String resPer = null;
															String resFile = null;
															while (rs.next()) {
																resImg = rs.getString(1);
																resName = rs.getString(2);
																resPer = rs.getString(3);
																resFile = rs.getString(4);
															}
															ArrayList<Integer> luv = new ArrayList<Integer>();
															if (request.getSession().getAttribute("Med_fav") != null) {
																luv = (ArrayList<Integer>) request.getSession()
																		.getAttribute("Med_fav");
															}
															boolean love = false;
															if (luv.contains(new_id)) {
																love = true;
															}
															response.setContentType("application/json");
															response.setCharacterEncoding("UTF-8");
															response.getWriter().write("{\"status\":\"true\",\"Img\":\""
																	+ resImg + "\",\"Name\":\"" + resName
																	+ "\",\"Per\":\"" + resPer + "\",\"path\":\""
																	+ resFile + "\", \"love\":\"" + love + "\"}");
														} catch (Exception e) {
															e.printStackTrace();
														}

													} else {
														int newId = listmed_is_playing.get(index_med + 1);
														request.getSession().setAttribute("song_isplay", newId);
														try {
															ResultSet rs = stmt.executeQuery(
																	"SELECT img_path,media_name,performer, file_path from media where id = '"
																			+ newId + "'");
															String resImg = null;
															String resName = null;
															String resPer = null;
															String resFile = null;
															while (rs.next()) {
																resImg = rs.getString(1);
																resName = rs.getString(2);
																resPer = rs.getString(3);
																resFile = rs.getString(4);
															}
															ArrayList<Integer> luv = new ArrayList<Integer>();
															if (request.getSession().getAttribute("Med_fav") != null) {
																luv = (ArrayList<Integer>) request.getSession()
																		.getAttribute("Med_fav");
															}
															boolean love = false;
															if (luv.contains(newId)) {
																love = true;
															}
															response.setContentType("application/json");
															response.setCharacterEncoding("UTF-8");
															response.getWriter().write("{\"status\":\"true\",\"Img\":\""
																	+ resImg + "\",\"Name\":\"" + resName
																	+ "\",\"Per\":\"" + resPer + "\",\"path\":\""
																	+ resFile + "\", \"love\":\"" + love + "\"}");
														} catch (Exception e) {
															e.printStackTrace();
														}
													}
												} else if (loop.equals("lon")) {
													response.setContentType("application/json");
													response.setCharacterEncoding("UTF-8");
													response.getWriter().write("{\"status\":\"false\"}");
												}
											}
										} else {
											if (id_string.equals("Love_song_pll")) {
												boolean suf = false;
												if (request.getSession().getAttribute("suf_song_love") != null) {
													suf = (boolean) request.getSession().getAttribute("suf_song_love");
												}
												if (suf == true) {
													int index_med = 0;
													for (int i = 0; i < listmed_is_playing.size(); i++) {
														if (listmed_is_playing.get(i) == med_play_id) {
															index_med = i;
															System.out.println(index_med);
															break;
														}
													}
													if (loop.equals("unlo")) {
														if (index_med + 1 == listmed_is_playing.size()) {
															System.out.println("un loop shuffle pll end list");
															Random random = new Random();
															int newId = listmed_is_playing.get(index_med);
															if (listmed_is_playing.size() > 1) {
																while (newId == listmed_is_playing.get(index_med)) {
																	int ran_index = random
																			.nextInt(listmed_is_playing.size());
																	newId = listmed_is_playing.get(ran_index);
																}
															} else {
																newId = random.nextInt(listmed_is_playing.size());
															}
															request.getSession().setAttribute("song_isplay", newId);
															try {
																ResultSet rs = stmt.executeQuery(
																		"SELECT img_path,media_name,performer, file_path from media where id = '"
																				+ newId + "'");
																String resImg = null;
																String resName = null;
																String resPer = null;
																String resFile = null;
																while (rs.next()) {
																	resImg = rs.getString(1);
																	resName = rs.getString(2);
																	resPer = rs.getString(3);
																	resFile = rs.getString(4);
																}
																ArrayList<Integer> luv = new ArrayList<Integer>();
																if (request.getSession()
																		.getAttribute("Med_fav") != null) {
																	luv = (ArrayList<Integer>) request.getSession()
																			.getAttribute("Med_fav");
																}
																boolean love = false;
																if (luv.contains(newId)) {
																	love = true;
																}
																response.setContentType("application/json");
																response.setCharacterEncoding("UTF-8");
																response.getWriter()
																		.write("{\"status\":\"true\",\"Img\":\""
																				+ resImg + "\",\"Name\":\"" + resName
																				+ "\",\"Per\":\"" + resPer
																				+ "\",\"path\":\"" + resFile
																				+ "\", \"love\":\"" + love + "\"}");
															} catch (Exception e) {
																e.printStackTrace();
															}
														} else {
															System.out.println("un loop shuffle pll in list");
															Random random = new Random();
															int newId = listmed_is_playing.get(index_med);
															if (listmed_is_playing.size() > 1) {
																while (newId == listmed_is_playing.get(index_med)) {
																	int ran_index = random
																			.nextInt(listmed_is_playing.size());
																	newId = listmed_is_playing.get(ran_index);
																}
															} else {
																newId = random.nextInt(listmed_is_playing.size());
															}
															request.getSession().setAttribute("song_isplay", newId);
															try {
																ResultSet rs = stmt.executeQuery(
																		"SELECT img_path,media_name,performer, file_path from media where id = '"
																				+ newId + "';");
																String resImg = null;
																String resName = null;
																String resPer = null;
																String resFile = null;
																while (rs.next()) {
																	resImg = rs.getString(1);
																	resName = rs.getString(2);
																	resPer = rs.getString(3);
																	resFile = rs.getString(4);
																}
																ArrayList<Integer> luv = new ArrayList<Integer>();
																if (request.getSession()
																		.getAttribute("Med_fav") != null) {
																	luv = (ArrayList<Integer>) request.getSession()
																			.getAttribute("Med_fav");
																}
																boolean love = false;
																if (luv.contains(newId)) {
																	love = true;
																}
																response.setContentType("application/json");
																response.setCharacterEncoding("UTF-8");
																response.getWriter()
																		.write("{\"status\":\"true\",\"Img\":\""
																				+ resImg + "\",\"Name\":\"" + resName
																				+ "\",\"Per\":\"" + resPer
																				+ "\",\"path\":\"" + resFile
																				+ "\", \"love\":\"" + love + "\"}");
															} catch (Exception e) {
																e.printStackTrace();
															}
														}
													} else if (loop.equals("lall")) {
														if (index_med + 1 == listmed_is_playing.size()) {
															System.out.println("loop all shuffle end list");
															Random random = new Random();
															int new_id = listmed_is_playing.get(index_med);
															if (listmed_is_playing.size() > 1) {
																while (new_id == listmed_is_playing.get(index_med)) {
																	int ran_index = random
																			.nextInt(listmed_is_playing.size());
																	new_id = listmed_is_playing.get(ran_index);
																}
															} else {
																new_id = random.nextInt(listmed_is_playing.size());
															}
															request.getSession().setAttribute("song_isplay", new_id);
															try {
																ResultSet rs = stmt.executeQuery(
																		"SELECT img_path,media_name,performer, file_path from media where id = '"
																				+ new_id + "'");
																String resImg = null;
																String resName = null;
																String resPer = null;
																String resFile = null;
																while (rs.next()) {
																	resImg = rs.getString(1);
																	resName = rs.getString(2);
																	resPer = rs.getString(3);
																	resFile = rs.getString(4);
																}
																ArrayList<Integer> luv = new ArrayList<Integer>();
																if (request.getSession()
																		.getAttribute("Med_fav") != null) {
																	luv = (ArrayList<Integer>) request.getSession()
																			.getAttribute("Med_fav");
																}
																boolean love = false;
																if (luv.contains(new_id)) {
																	love = true;
																}
																response.setContentType("application/json");
																response.setCharacterEncoding("UTF-8");
																response.getWriter()
																		.write("{\"status\":\"true\",\"Img\":\""
																				+ resImg + "\",\"Name\":\"" + resName
																				+ "\",\"Per\":\"" + resPer
																				+ "\",\"path\":\"" + resFile
																				+ "\", \"love\":\"" + love + "\"}");
															} catch (Exception e) {
																e.printStackTrace();
															}

														} else {
															System.out.println("loop all shuffle in list");
															Random random = new Random();
															int newId = listmed_is_playing.get(index_med);
															if (listmed_is_playing.size() > 1) {
																while (newId == listmed_is_playing.get(index_med)) {
																	int ran_index = random
																			.nextInt(listmed_is_playing.size());
																	newId = listmed_is_playing.get(ran_index);
																}
															} else {
																newId = random.nextInt(listmed_is_playing.size());
															}

															request.getSession().setAttribute("song_isplay", newId);
															try {
																ResultSet rs = stmt.executeQuery(
																		"SELECT img_path,media_name,performer, file_path from media where id = '"
																				+ newId + "'");
																String resImg = null;
																String resName = null;
																String resPer = null;
																String resFile = null;
																while (rs.next()) {
																	resImg = rs.getString(1);
																	resName = rs.getString(2);
																	resPer = rs.getString(3);
																	resFile = rs.getString(4);
																}
																ArrayList<Integer> luv = new ArrayList<Integer>();
																if (request.getSession()
																		.getAttribute("Med_fav") != null) {
																	luv = (ArrayList<Integer>) request.getSession()
																			.getAttribute("Med_fav");
																}
																boolean love = false;
																if (luv.contains(newId)) {
																	love = true;
																}
																response.setContentType("application/json");
																response.setCharacterEncoding("UTF-8");
																response.getWriter()
																		.write("{\"status\":\"true\",\"Img\":\""
																				+ resImg + "\",\"Name\":\"" + resName
																				+ "\",\"Per\":\"" + resPer
																				+ "\",\"path\":\"" + resFile
																				+ "\", \"love\":\"" + love + "\"}");
															} catch (Exception e) {
																e.printStackTrace();
															}
														}
													} else if (loop.equals("lon")) {
														System.out.println("loop one shuffle in list");
														response.setContentType("application/json");
														response.setCharacterEncoding("UTF-8");
														response.getWriter().write("{\"status\":\"false\"}");
													}
												} else {
													int index_med = 0;
													for (int i = 0; i < listmed_is_playing.size(); i++) {
														if (listmed_is_playing.get(i) == med_play_id) {
															index_med = i;
															System.out.println(index_med);
															break;
														}
													}
													if (loop.equals("unlo")) {
														if (index_med + 1 == listmed_is_playing.size()) {
															System.out.println("unloop not shuffle end pll");
															response.setContentType("application/json");
															response.setCharacterEncoding("UTF-8");
															response.getWriter().write("{\"status\":\"stop\"}");
														} else {
															int newId1 = listmed_is_playing.get(index_med + 1);
															System.out.println("unloop not shuffle in pll");
															System.out.println("New id: " + newId1);
															request.getSession().setAttribute("song_isplay", newId1);
															try {
																ResultSet rs = stmt.executeQuery(
																		"SELECT img_path,media_name,performer, file_path from media where id = '"
																				+ newId1 + "';");
																String resImg = null;
																String resName = null;
																String resPer = null;
																String resFile = null;
																while (rs.next()) {
																	resImg = rs.getString(1);
																	resName = rs.getString(2);
																	resPer = rs.getString(3);
																	resFile = rs.getString(4);
																}
																ArrayList<Integer> luv = new ArrayList<Integer>();
																if (request.getSession()
																		.getAttribute("Med_fav") != null) {
																	luv = (ArrayList<Integer>) request.getSession()
																			.getAttribute("Med_fav");
																}
																boolean love = false;
																if (luv.contains(newId1)) {
																	love = true;
																}
																response.setContentType("application/json");
																response.setCharacterEncoding("UTF-8");
																response.getWriter()
																		.write("{\"status\":\"true\",\"Img\":\""
																				+ resImg + "\",\"Name\":\"" + resName
																				+ "\",\"Per\":\"" + resPer
																				+ "\",\"path\":\"" + resFile
																				+ "\", \"love\":\"" + love + "\"}");
															} catch (Exception e) {
																e.printStackTrace();
															}
														}
													} else if (loop.equals("lall")) {
														if (index_med + 1 == listmed_is_playing.size()) {
															int new_id = listmed_is_playing.get(0);
															request.getSession().setAttribute("song_isplay", new_id);
															try {
																ResultSet rs = stmt.executeQuery(
																		"SELECT img_path,media_name,performer, file_path from media where id = '"
																				+ new_id + "'");
																String resImg = null;
																String resName = null;
																String resPer = null;
																String resFile = null;
																while (rs.next()) {
																	resImg = rs.getString(1);
																	resName = rs.getString(2);
																	resPer = rs.getString(3);
																	resFile = rs.getString(4);
																}
																ArrayList<Integer> luv = new ArrayList<Integer>();
																if (request.getSession()
																		.getAttribute("Med_fav") != null) {
																	luv = (ArrayList<Integer>) request.getSession()
																			.getAttribute("Med_fav");
																}
																boolean love = false;
																if (luv.contains(new_id)) {
																	love = true;
																}
																response.setContentType("application/json");
																response.setCharacterEncoding("UTF-8");
																response.getWriter()
																		.write("{\"status\":\"true\",\"Img\":\""
																				+ resImg + "\",\"Name\":\"" + resName
																				+ "\",\"Per\":\"" + resPer
																				+ "\",\"path\":\"" + resFile
																				+ "\", \"love\":\"" + love + "\"}");
															} catch (Exception e) {
																e.printStackTrace();
															}

														} else {
															int newId = listmed_is_playing.get(index_med + 1);
															request.getSession().setAttribute("song_isplay", newId);
															try {
																ResultSet rs = stmt.executeQuery(
																		"SELECT img_path,media_name,performer, file_path from media where id = '"
																				+ newId + "'");
																String resImg = null;
																String resName = null;
																String resPer = null;
																String resFile = null;
																while (rs.next()) {
																	resImg = rs.getString(1);
																	resName = rs.getString(2);
																	resPer = rs.getString(3);
																	resFile = rs.getString(4);
																}
																ArrayList<Integer> luv = new ArrayList<Integer>();
																if (request.getSession()
																		.getAttribute("Med_fav") != null) {
																	luv = (ArrayList<Integer>) request.getSession()
																			.getAttribute("Med_fav");
																}
																boolean love = false;
																if (luv.contains(newId)) {
																	love = true;
																}
																response.setContentType("application/json");
																response.setCharacterEncoding("UTF-8");
																response.getWriter()
																		.write("{\"status\":\"true\",\"Img\":\""
																				+ resImg + "\",\"Name\":\"" + resName
																				+ "\",\"Per\":\"" + resPer
																				+ "\",\"path\":\"" + resFile
																				+ "\", \"love\":\"" + love + "\"}");
															} catch (Exception e) {
																e.printStackTrace();
															}
														}
													} else if (loop.equals("lon")) {
														response.setContentType("application/json");
														response.setCharacterEncoding("UTF-8");
														response.getWriter().write("{\"status\":\"false\"}");
													}
												}
											} else if (id_string.equals("Love_pod_pll")) {
												boolean suf = false;
												if (request.getSession().getAttribute("suf_pod_love") != null) {
													suf = (boolean) request.getSession().getAttribute("suf_pod_love");
												}
												if (suf == true) {
													int index_med = 0;
													for (int i = 0; i < listmed_is_playing.size(); i++) {
														if (listmed_is_playing.get(i) == med_play_id) {
															index_med = i;
															System.out.println(index_med);
															break;
														}
													}
													if (loop.equals("unlo")) {
														if (index_med + 1 == listmed_is_playing.size()) {
															System.out.println("un loop shuffle pll end list");
															Random random = new Random();
															int newId = listmed_is_playing.get(index_med);
															if (listmed_is_playing.size() > 1) {
																while (newId == listmed_is_playing.get(index_med)) {
																	int ran_index = random
																			.nextInt(listmed_is_playing.size());
																	newId = listmed_is_playing.get(ran_index);
																}
															} else {
																newId = random.nextInt(listmed_is_playing.size());
															}
															request.getSession().setAttribute("song_isplay", newId);
															try {
																ResultSet rs = stmt.executeQuery(
																		"SELECT img_path,media_name,performer, file_path from media where id = '"
																				+ newId + "'");
																String resImg = null;
																String resName = null;
																String resPer = null;
																String resFile = null;
																while (rs.next()) {
																	resImg = rs.getString(1);
																	resName = rs.getString(2);
																	resPer = rs.getString(3);
																	resFile = rs.getString(4);
																}
																ArrayList<Integer> luv = new ArrayList<Integer>();
																if (request.getSession()
																		.getAttribute("Med_fav") != null) {
																	luv = (ArrayList<Integer>) request.getSession()
																			.getAttribute("Med_fav");
																}
																boolean love = false;
																if (luv.contains(newId)) {
																	love = true;
																}
																response.setContentType("application/json");
																response.setCharacterEncoding("UTF-8");
																response.getWriter()
																		.write("{\"status\":\"true\",\"Img\":\""
																				+ resImg + "\",\"Name\":\"" + resName
																				+ "\",\"Per\":\"" + resPer
																				+ "\",\"path\":\"" + resFile
																				+ "\", \"love\":\"" + love + "\"}");
															} catch (Exception e) {
																e.printStackTrace();
															}
														} else {
															System.out.println("un loop shuffle pll in list");
															Random random = new Random();
															int newId = listmed_is_playing.get(index_med);
															if (listmed_is_playing.size() > 1) {
																while (newId == listmed_is_playing.get(index_med)) {
																	int ran_index = random
																			.nextInt(listmed_is_playing.size());
																	newId = listmed_is_playing.get(ran_index);
																}
															} else {
																newId = random.nextInt(listmed_is_playing.size());
															}
															request.getSession().setAttribute("song_isplay", newId);
															try {
																ResultSet rs = stmt.executeQuery(
																		"SELECT img_path,media_name,performer, file_path from media where id = '"
																				+ newId + "';");
																String resImg = null;
																String resName = null;
																String resPer = null;
																String resFile = null;
																while (rs.next()) {
																	resImg = rs.getString(1);
																	resName = rs.getString(2);
																	resPer = rs.getString(3);
																	resFile = rs.getString(4);
																}
																ArrayList<Integer> luv = new ArrayList<Integer>();
																if (request.getSession()
																		.getAttribute("Med_fav") != null) {
																	luv = (ArrayList<Integer>) request.getSession()
																			.getAttribute("Med_fav");
																}
																boolean love = false;
																if (luv.contains(newId)) {
																	love = true;
																}
																response.setContentType("application/json");
																response.setCharacterEncoding("UTF-8");
																response.getWriter()
																		.write("{\"status\":\"true\",\"Img\":\""
																				+ resImg + "\",\"Name\":\"" + resName
																				+ "\",\"Per\":\"" + resPer
																				+ "\",\"path\":\"" + resFile
																				+ "\", \"love\":\"" + love + "\"}");
															} catch (Exception e) {
																e.printStackTrace();
															}
														}
													} else if (loop.equals("lall")) {
														if (index_med + 1 == listmed_is_playing.size()) {
															System.out.println("loop all shuffle end list");
															Random random = new Random();
															int new_id = listmed_is_playing.get(index_med);
															if (listmed_is_playing.size() > 1) {
																while (new_id == listmed_is_playing.get(index_med)) {
																	int ran_index = random
																			.nextInt(listmed_is_playing.size());
																	new_id = listmed_is_playing.get(ran_index);
																}
															} else {
																new_id = random.nextInt(listmed_is_playing.size());
															}
															request.getSession().setAttribute("song_isplay", new_id);
															try {
																ResultSet rs = stmt.executeQuery(
																		"SELECT img_path,media_name,performer, file_path from media where id = '"
																				+ new_id + "'");
																String resImg = null;
																String resName = null;
																String resPer = null;
																String resFile = null;
																while (rs.next()) {
																	resImg = rs.getString(1);
																	resName = rs.getString(2);
																	resPer = rs.getString(3);
																	resFile = rs.getString(4);
																}
																ArrayList<Integer> luv = new ArrayList<Integer>();
																if (request.getSession()
																		.getAttribute("Med_fav") != null) {
																	luv = (ArrayList<Integer>) request.getSession()
																			.getAttribute("Med_fav");
																}
																boolean love = false;
																if (luv.contains(new_id)) {
																	love = true;
																}
																response.setContentType("application/json");
																response.setCharacterEncoding("UTF-8");
																response.getWriter()
																		.write("{\"status\":\"true\",\"Img\":\""
																				+ resImg + "\",\"Name\":\"" + resName
																				+ "\",\"Per\":\"" + resPer
																				+ "\",\"path\":\"" + resFile
																				+ "\", \"love\":\"" + love + "\"}");
															} catch (Exception e) {
																e.printStackTrace();
															}
														} else {
															System.out.println("loop all shuffle in list");
															Random random = new Random();
															int newId = listmed_is_playing.get(index_med);
															if (listmed_is_playing.size() > 1) {
																while (newId == listmed_is_playing.get(index_med)) {
																	int ran_index = random
																			.nextInt(listmed_is_playing.size());
																	newId = listmed_is_playing.get(ran_index);
																}
															} else {
																newId = random.nextInt(listmed_is_playing.size());
															}

															request.getSession().setAttribute("song_isplay", newId);
															try {
																ResultSet rs = stmt.executeQuery(
																		"SELECT img_path,media_name,performer, file_path from media where id = '"
																				+ newId + "'");
																String resImg = null;
																String resName = null;
																String resPer = null;
																String resFile = null;
																while (rs.next()) {
																	resImg = rs.getString(1);
																	resName = rs.getString(2);
																	resPer = rs.getString(3);
																	resFile = rs.getString(4);
																}
																ArrayList<Integer> luv = new ArrayList<Integer>();
																if (request.getSession()
																		.getAttribute("Med_fav") != null) {
																	luv = (ArrayList<Integer>) request.getSession()
																			.getAttribute("Med_fav");
																}
																boolean love = false;
																if (luv.contains(newId)) {
																	love = true;
																}
																response.setContentType("application/json");
																response.setCharacterEncoding("UTF-8");
																response.getWriter()
																		.write("{\"status\":\"true\",\"Img\":\""
																				+ resImg + "\",\"Name\":\"" + resName
																				+ "\",\"Per\":\"" + resPer
																				+ "\",\"path\":\"" + resFile
																				+ "\", \"love\":\"" + love + "\"}");
															} catch (Exception e) {
																e.printStackTrace();
															}
														}
													} else if (loop.equals("lon")) {
														System.out.println("loop one shuffle in list");
														response.setContentType("application/json");
														response.setCharacterEncoding("UTF-8");
														response.getWriter().write("{\"status\":\"false\"}");
													}
												} else {
													int index_med = 0;
													for (int i = 0; i < listmed_is_playing.size(); i++) {
														if (listmed_is_playing.get(i) == med_play_id) {
															index_med = i;
															System.out.println(index_med);
															break;
														}
													}
													if (loop.equals("unlo")) {
														if (index_med + 1 == listmed_is_playing.size()) {
															System.out.println("unloop not shuffle end pll");
															response.setContentType("application/json");
															response.setCharacterEncoding("UTF-8");
															response.getWriter().write("{\"status\":\"stop\"}");
														} else {
															int newId1 = listmed_is_playing.get(index_med + 1);
															System.out.println("unloop not shuffle in pll");
															System.out.println("New id: " + newId1);
															request.getSession().setAttribute("song_isplay", newId1);
															try {
																ResultSet rs = stmt.executeQuery(
																		"SELECT img_path,media_name,performer, file_path from media where id = '"
																				+ newId1 + "';");
																String resImg = null;
																String resName = null;
																String resPer = null;
																String resFile = null;
																while (rs.next()) {
																	resImg = rs.getString(1);
																	resName = rs.getString(2);
																	resPer = rs.getString(3);
																	resFile = rs.getString(4);
																}
																ArrayList<Integer> luv = new ArrayList<Integer>();
																if (request.getSession()
																		.getAttribute("Med_fav") != null) {
																	luv = (ArrayList<Integer>) request.getSession()
																			.getAttribute("Med_fav");
																}
																boolean love = false;
																if (luv.contains(newId1)) {
																	love = true;
																}
																response.setContentType("application/json");
																response.setCharacterEncoding("UTF-8");
																response.getWriter()
																		.write("{\"status\":\"true\",\"Img\":\""
																				+ resImg + "\",\"Name\":\"" + resName
																				+ "\",\"Per\":\"" + resPer
																				+ "\",\"path\":\"" + resFile
																				+ "\", \"love\":\"" + love + "\"}");
															} catch (Exception e) {
																e.printStackTrace();
															}
														}
													} else if (loop.equals("lall")) {
														if (index_med + 1 == listmed_is_playing.size()) {
															int new_id = listmed_is_playing.get(0);
															request.getSession().setAttribute("song_isplay", new_id);
															try {
																ResultSet rs = stmt.executeQuery(
																		"SELECT img_path,media_name,performer, file_path from media where id = '"
																				+ new_id + "'");
																String resImg = null;
																String resName = null;
																String resPer = null;
																String resFile = null;
																while (rs.next()) {
																	resImg = rs.getString(1);
																	resName = rs.getString(2);
																	resPer = rs.getString(3);
																	resFile = rs.getString(4);
																}
																ArrayList<Integer> luv = new ArrayList<Integer>();
																if (request.getSession()
																		.getAttribute("Med_fav") != null) {
																	luv = (ArrayList<Integer>) request.getSession()
																			.getAttribute("Med_fav");
																}
																boolean love = false;
																if (luv.contains(new_id)) {
																	love = true;
																}
																response.setContentType("application/json");
																response.setCharacterEncoding("UTF-8");
																response.getWriter()
																		.write("{\"status\":\"true\",\"Img\":\""
																				+ resImg + "\",\"Name\":\"" + resName
																				+ "\",\"Per\":\"" + resPer
																				+ "\",\"path\":\"" + resFile
																				+ "\", \"love\":\"" + love + "\"}");
															} catch (Exception e) {
																e.printStackTrace();
															}

														} else {
															int newId = listmed_is_playing.get(index_med + 1);
															request.getSession().setAttribute("song_isplay", newId);
															try {
																ResultSet rs = stmt.executeQuery(
																		"SELECT img_path,media_name,performer, file_path from media where id = '"
																				+ newId + "'");
																String resImg = null;
																String resName = null;
																String resPer = null;
																String resFile = null;
																while (rs.next()) {
																	resImg = rs.getString(1);
																	resName = rs.getString(2);
																	resPer = rs.getString(3);
																	resFile = rs.getString(4);
																}
																ArrayList<Integer> luv = new ArrayList<Integer>();
																if (request.getSession()
																		.getAttribute("Med_fav") != null) {
																	luv = (ArrayList<Integer>) request.getSession()
																			.getAttribute("Med_fav");
																}
																boolean love = false;
																if (luv.contains(newId)) {
																	love = true;
																}
																response.setContentType("application/json");
																response.setCharacterEncoding("UTF-8");
																response.getWriter()
																		.write("{\"status\":\"true\",\"Img\":\""
																				+ resImg + "\",\"Name\":\"" + resName
																				+ "\",\"Per\":\"" + resPer
																				+ "\",\"path\":\"" + resFile
																				+ "\", \"love\":\"" + love + "\"}");
															} catch (Exception e) {
																e.printStackTrace();
															}
														}
													} else if (loop.equals("lon")) {
														response.setContentType("application/json");
														response.setCharacterEncoding("UTF-8");
														response.getWriter().write("{\"status\":\"false\"}");
													}
												}
											}
										}

										break;
									case "artist":
										int index_med1 = 0;
										for (int i = 0; i < listmed_is_playing.size(); i++) {
											if (listmed_is_playing.get(i) == med_play_id) {
												index_med1 = i;
												break;
											}
										}
										boolean suf1 = false;
										if (suflist.size() > 0) {
											for (int i = 0; i < suflist.size(); i++) {
												if (suflist.get(i).id.equals("" + id_ispl)
														&& suflist.get(i).stat == true) {
													suf1 = true;
													break;
												}
											}
										} else {
											SufOrUnsuf newpll = new SufOrUnsuf();
											newpll.id = "" + id_ispl;
											newpll.stat = false;
											suflist.add(newpll);
											request.getSession().setAttribute("SufList", suflist);
										}

										if (suf1 == true) {
											if (loop.equals("unlo")) {
												if (index_med1 + 1 == listmed_is_playing.size()) {
													response.setContentType("application/json");
													response.setCharacterEncoding("UTF-8");
													response.getWriter().write("{\"status\":\"stop\"}");
												} else {
													Random random = new Random();
													int newId = listmed_is_playing.get(index_med1);
													if (listmed_is_playing.size() > 1) {
														while (newId == listmed_is_playing.get(index_med1)) {
															int ran_index = random.nextInt(listmed_is_playing.size());
															newId = listmed_is_playing.get(ran_index);
														}
													} else {
														newId = random.nextInt(listmed_is_playing.size());
													}
													request.getSession().setAttribute("song_isplay", newId);
													try {
														ResultSet rs = stmt.executeQuery(
																"SELECT img_path,media_name,performer, file_path from media where id = '"
																		+ newId + "'");
														String resImg = null;
														String resName = null;
														String resPer = null;
														String resFile = null;
														while (rs.next()) {
															resImg = rs.getString(1);
															resName = rs.getString(2);
															resPer = rs.getString(3);
															resFile = rs.getString(4);
														}
														ArrayList<Integer> luv = new ArrayList<Integer>();
														if (request.getSession().getAttribute("Med_fav") != null) {
															luv = (ArrayList<Integer>) request.getSession()
																	.getAttribute("Med_fav");
														}
														boolean love = false;
														if (luv.contains(newId)) {
															love = true;
														}
														response.setContentType("application/json");
														response.setCharacterEncoding("UTF-8");
														response.getWriter()
																.write("{\"status\":\"true\",\"Img\":\"" + resImg
																		+ "\",\"Name\":\"" + resName + "\",\"Per\":\""
																		+ resPer + "\",\"path\":\"" + resFile
																		+ "\", \"love\":\"" + love + "\"}");
													} catch (Exception e) {
														e.printStackTrace();
													}
												}
											} else if (loop.equals("lall")) {
												if (index_med1 + 1 == listmed_is_playing.size()) {
													int new_id = listmed_is_playing.get(index_med1);
													Random random = new Random();
													if (listmed_is_playing.size() > 1) {
														while (new_id == listmed_is_playing.get(index_med1)) {
															int ran_index = random.nextInt(listmed_is_playing.size());
															new_id = listmed_is_playing.get(ran_index);
														}
													} else {
														new_id = random.nextInt(listmed_is_playing.size());
													}
													request.getSession().setAttribute("song_isplay", new_id);
													try {
														ResultSet rs = stmt.executeQuery(
																"SELECT img_path,media_name,performer, file_path from media where id = '"
																		+ new_id + "'");
														String resImg = null;
														String resName = null;
														String resPer = null;
														String resFile = null;
														while (rs.next()) {
															resImg = rs.getString(1);
															resName = rs.getString(2);
															resPer = rs.getString(3);
															resFile = rs.getString(4);
														}
														ArrayList<Integer> luv = new ArrayList<Integer>();
														if (request.getSession().getAttribute("Med_fav") != null) {
															luv = (ArrayList<Integer>) request.getSession()
																	.getAttribute("Med_fav");
														}
														boolean love = false;
														if (luv.contains(new_id)) {
															love = true;
														}
														response.setContentType("application/json");
														response.setCharacterEncoding("UTF-8");
														response.getWriter()
																.write("{\"status\":\"true\",\"Img\":\"" + resImg
																		+ "\",\"Name\":\"" + resName + "\",\"Per\":\""
																		+ resPer + "\",\"path\":\"" + resFile
																		+ "\", \"love\":\"" + love + "\"}");
													} catch (Exception e) {
														e.printStackTrace();
													}

												} else {
													Random random = new Random();
													int newId = listmed_is_playing.get(index_med1);
													if (listmed_is_playing.size() > 1) {
														while (newId == listmed_is_playing.get(index_med1)) {
															int ran_index = random.nextInt(listmed_is_playing.size());
															newId = listmed_is_playing.get(ran_index);
														}
													} else {
														newId = random.nextInt(listmed_is_playing.size());
													}
													request.getSession().setAttribute("song_isplay", newId);
													try {
														ResultSet rs = stmt.executeQuery(
																"SELECT img_path,media_name,performer, file_path from media where id = '"
																		+ newId + "'");
														String resImg = null;
														String resName = null;
														String resPer = null;
														String resFile = null;
														while (rs.next()) {
															resImg = rs.getString(1);
															resName = rs.getString(2);
															resPer = rs.getString(3);
															resFile = rs.getString(4);
														}
														ArrayList<Integer> luv = new ArrayList<Integer>();
														if (request.getSession().getAttribute("Med_fav") != null) {
															luv = (ArrayList<Integer>) request.getSession()
																	.getAttribute("Med_fav");
														}
														boolean love = false;
														if (luv.contains(newId)) {
															love = true;
														}
														response.setContentType("application/json");
														response.setCharacterEncoding("UTF-8");
														response.getWriter()
																.write("{\"status\":\"true\",\"Img\":\"" + resImg
																		+ "\",\"Name\":\"" + resName + "\",\"Per\":\""
																		+ resPer + "\",\"path\":\"" + resFile
																		+ "\", \"love\":\"" + love + "\"}");
													} catch (Exception e) {
														e.printStackTrace();
													}
												}
											} else if (loop.equals("lon")) {
												System.out.println("Loop one artist shuffle");
												response.setContentType("application/json");
												response.setCharacterEncoding("UTF-8");
												response.getWriter().write("{\"status\":\"false\"}");
											}

										} else {
											if (loop.equals("unlo")) {
												if (index_med1 + 1 == listmed_is_playing.size()) {
													System.out.println("no loop artist end list no shuffle");
													response.setContentType("application/json");
													response.setCharacterEncoding("UTF-8");
													response.getWriter().write("{\"status\":\"stop\"}");
												} else {
													int newId = listmed_is_playing.get(index_med1 + 1);
													request.getSession().setAttribute("song_isplay", newId);
													try {
														ResultSet rs = stmt.executeQuery(
																"SELECT img_path,media_name,performer, file_path from media where id = '"
																		+ newId + "'");
														String resImg = null;
														String resName = null;
														String resPer = null;
														String resFile = null;
														while (rs.next()) {
															resImg = rs.getString(1);
															resName = rs.getString(2);
															resPer = rs.getString(3);
															resFile = rs.getString(4);
														}
														ArrayList<Integer> luv = new ArrayList<Integer>();
														if (request.getSession().getAttribute("Med_fav") != null) {
															luv = (ArrayList<Integer>) request.getSession()
																	.getAttribute("Med_fav");
														}
														boolean love = false;
														if (luv.contains(newId)) {
															love = true;
														}
														System.out.println("no loop artist no shuffle");
														response.setContentType("application/json");
														response.setCharacterEncoding("UTF-8");
														response.getWriter()
																.write("{\"status\":\"true\",\"Img\":\"" + resImg
																		+ "\",\"Name\":\"" + resName + "\",\"Per\":\""
																		+ resPer + "\",\"path\":\"" + resFile
																		+ "\", \"love\":\"" + love + "\"}");
													} catch (Exception e) {
														e.printStackTrace();
													}
												}
											} else if (loop.equals("lall")) {
												if (index_med1 + 1 == listmed_is_playing.size()) {
													int new_id = listmed_is_playing.get(0);
													request.getSession().setAttribute("song_isplay", new_id);
													try {
														ResultSet rs = stmt.executeQuery(
																"SELECT img_path,media_name,performer, file_path from media where id = '"
																		+ new_id + "'");
														String resImg = null;
														String resName = null;
														String resPer = null;
														String resFile = null;
														while (rs.next()) {
															resImg = rs.getString(1);
															resName = rs.getString(2);
															resPer = rs.getString(3);
															resFile = rs.getString(4);
														}
														ArrayList<Integer> luv = new ArrayList<Integer>();
														if (request.getSession().getAttribute("Med_fav") != null) {
															luv = (ArrayList<Integer>) request.getSession()
																	.getAttribute("Med_fav");
														}
														boolean love = false;
														if (luv.contains(new_id)) {
															love = true;
														}
														System.out.println("Loop all artist shuffle");
														response.setContentType("application/json");
														response.setCharacterEncoding("UTF-8");
														response.getWriter()
																.write("{\"status\":\"true\",\"Img\":\"" + resImg
																		+ "\",\"Name\":\"" + resName + "\",\"Per\":\""
																		+ resPer + "\",\"path\":\"" + resFile
																		+ "\", \"love\":\"" + love + "\"}");
													} catch (Exception e) {
														e.printStackTrace();
													}

												} else {
													int newId = listmed_is_playing.get(index_med1 + 1);
													request.getSession().setAttribute("song_isplay", newId);
													try {
														ResultSet rs = stmt.executeQuery(
																"SELECT img_path,media_name,performer, file_path from media where id = '"
																		+ newId + "'");
														String resImg = null;
														String resName = null;
														String resPer = null;
														String resFile = null;
														while (rs.next()) {
															resImg = rs.getString(1);
															resName = rs.getString(2);
															resPer = rs.getString(3);
															resFile = rs.getString(4);
														}
														ArrayList<Integer> luv = new ArrayList<Integer>();
														if (request.getSession().getAttribute("Med_fav") != null) {
															luv = (ArrayList<Integer>) request.getSession()
																	.getAttribute("Med_fav");
														}
														boolean love = false;
														if (luv.contains(newId)) {
															love = true;
														}
														System.out.println("Loop all artist no shuffle");
														response.setContentType("application/json");
														response.setCharacterEncoding("UTF-8");
														response.getWriter()
																.write("{\"status\":\"true\",\"Img\":\"" + resImg
																		+ "\",\"Name\":\"" + resName + "\",\"Per\":\""
																		+ resPer + "\",\"path\":\"" + resFile
																		+ "\", \"love\":\"" + love + "\"}");
													} catch (Exception e) {
														e.printStackTrace();
													}
												}
											} else if (loop.equals("lon")) {
												System.out.println("Loop one artist no shuffle");
												response.setContentType("application/json");
												response.setCharacterEncoding("UTF-8");
												response.getWriter().write("{\"status\":\"false\"}");
											}
										}
										break;

									case "songout":
										int oldID = med_play_id;
										type_cate songIspl_type = new type_cate();
										if (request.getSession().getAttribute("CATE_TYPE") != null) {
											songIspl_type = (type_cate) request.getSession().getAttribute("CATE_TYPE");
										}
										String type = songIspl_type.type;
										System.out.println(type);
										String cate = songIspl_type.cate;
										System.out.println(cate);
										try {
											ResultSet rs = stmt.executeQuery("SELECT * FROM media WHERE types = '"
													+ type + "' AND media_song_categories = '" + cate + "' ");
											ArrayList<Media> id_list = new ArrayList<Media>();
											while (rs.next()) {
												Media med = new Media();
												med.setMedia_id(rs.getInt(1));
												med.setMedia_img_path(rs.getString(2));
												med.setMedia_name(rs.getString(3));
												med.setMedia_performer(rs.getString(4));
												med.setMedia_file_path(rs.getString(5));
												id_list.add(med);
											}
											int index = 0;
											int newID = 0;
											if (loop.equals("lon")) {
												response.setContentType("application/json");
												response.setCharacterEncoding("UTF-8");
												response.getWriter().write("{\"status\":\"false\"}");
											} else {
												if (listmed_is_playing.size() == id_list.size()) {
													newID = listmed_is_playing.get(0);
													listmed_is_playing.clear();
													listmed_is_playing.add(newID);
													request.getSession().setAttribute("id_med_his", listmed_is_playing);
													request.getSession().setAttribute("song_isplay", newID);
													for (int i = 0; i < id_list.size(); i++) {
														if (id_list.get(i).getID() == newID) {
															index = i;
															break;
														}
													}
												} else {
													Random random = new Random();
													newID = oldID;
													while (listmed_is_playing.contains(newID) == true) {
														newID = id_list.get(random.nextInt(id_list.size())).getID();
													}
													request.getSession().setAttribute("song_isplay", newID);
													listmed_is_playing.add(newID);
													request.getSession().setAttribute("id_med_his", listmed_is_playing);
													for (int i = 0; i < id_list.size(); i++) {
														if (id_list.get(i).getID() == newID) {
															index = i;
															break;
														}
													}
												}

												ArrayList<Integer> luv = new ArrayList<Integer>();
												if (request.getSession().getAttribute("Med_fav") != null) {
													luv = (ArrayList<Integer>) request.getSession()
															.getAttribute("Med_fav");
												}
												boolean love = false;
												if (luv.contains(newID)) {
													love = true;
												}

												response.setContentType("application/json");
												response.setCharacterEncoding("UTF-8");
												response.getWriter().write("{\"status\":\"true\",\"Img\":\""
														+ id_list.get(index).getPL_path_img() + "\",\"Name\":\""
														+ id_list.get(index).getMedia_name() + "\",\"Per\":\""
														+ id_list.get(index).getMedia_perform() + "\",\"path\":\""
														+ id_list.get(index).getMedia_file_path() + "\", \"love\":\""
														+ love + "\"}");
											}

										} catch (Exception e) {
											e.printStackTrace();
										}
										break;

									case "podout":
										int oldID1 = med_play_id;
										type_cate songIspl_type1 = new type_cate();
										if (request.getSession().getAttribute("CATE_TYPE") != null) {
											songIspl_type = (type_cate) request.getSession().getAttribute("CATE_TYPE");
										}
										String type1 = songIspl_type1.type;
										System.out.println(type1);
										String cate1 = songIspl_type1.cate;
										System.out.println(cate1);
										try {
											ResultSet rs = stmt.executeQuery("SELECT * FROM media WHERE types = '"
													+ type1 + "' AND media_song_categories = '" + cate1 + "' ");
											ArrayList<Media> id_list = new ArrayList<Media>();
											while (rs.next()) {
												Media med = new Media();
												med.setMedia_id(rs.getInt(1));
												med.setMedia_img_path(rs.getString(2));
												med.setMedia_name(rs.getString(3));
												med.setMedia_performer(rs.getString(4));
												med.setMedia_file_path(rs.getString(5));
												id_list.add(med);
											}
											int index = 0;
											int newID = 0;
											if (loop.equals("lon")) {
												response.setContentType("application/json");
												response.setCharacterEncoding("UTF-8");
												response.getWriter().write("{\"status\":\"false\"}");
											} else {
												if (listmed_is_playing.size() == id_list.size()) {
													newID = listmed_is_playing.get(0);
													listmed_is_playing.clear();
													listmed_is_playing.add(newID);
													request.getSession().setAttribute("id_med_his", listmed_is_playing);
													request.getSession().setAttribute("song_isplay", newID);
													for (int i = 0; i < id_list.size(); i++) {
														if (id_list.get(i).getID() == newID) {
															index = i;
															break;
														}
													}
												} else {
													Random random = new Random();
													newID = oldID1;
													while (listmed_is_playing.contains(newID)) {
														newID = id_list.get(random.nextInt(id_list.size())).getID();
													}
													request.getSession().setAttribute("song_isplay", newID);
													listmed_is_playing.add(newID);
													request.getSession().setAttribute("id_med_his", listmed_is_playing);
													for (int i = 0; i < id_list.size(); i++) {
														if (id_list.get(i).getID() == newID) {
															index = i;
															break;
														}
													}
												}
											}
											ArrayList<Integer> luv = new ArrayList<Integer>();
											if (request.getSession().getAttribute("Med_fav") != null) {
												luv = (ArrayList<Integer>) request.getSession().getAttribute("Med_fav");
											}
											boolean love = false;
											if (luv.contains(newID)) {
												love = true;
											}
											response.setContentType("application/json");
											response.setCharacterEncoding("UTF-8");
											response.getWriter()
													.write("{\"status\":\"true\",\"Img\":\""
															+ id_list.get(index).getPL_path_img() + "\",\"Name\":\""
															+ id_list.get(index).getMedia_name() + "\",\"Per\":\""
															+ id_list.get(index).getMedia_perform() + "\",\"path\":\""
															+ id_list.get(index).getMedia_file_path()
															+ "\", \"love\":\"" + love + "\"}");

										} catch (Exception e) {
											e.printStackTrace();
										}
										break;

									default:
										break;
								}
							} else {

							}
						} else {
							String site = "notfound.jsp";
							response.setStatus(response.SC_MOVED_TEMPORARILY);
							response.setHeader("Location", site);
						}
						break;
					case "PREVIOUS":
						if (request.getSession().getAttribute("MAIL") != null
								&& request.getSession().getAttribute("PASS") != null) {
							if (request.getSession().getAttribute("song_isplay") != null) {
								int med_play_id = 0;
								String place = null;
								String id_ispl = "";
								String id_ispl_string = "";
								boolean check_id_string = false;
								type_cate type_isplay = new type_cate();
								ArrayList<Integer> listmed_is_playing = new ArrayList<Integer>();
								String loop = "unlo";
								ArrayList<SufOrUnsuf> suflist = new ArrayList<SufOrUnsuf>();
								if (request.getSession().getAttribute("SufList") != null) {
									suflist.addAll(
											(ArrayList<SufOrUnsuf>) request.getSession().getAttribute("SufList"));
								}
								if (request.getSession().getAttribute("id_med_his") != null) {
									listmed_is_playing.addAll(
											(ArrayList<Integer>) request.getSession().getAttribute("id_med_his"));
								}

								if (request.getSession().getAttribute("loop") != null) {
									loop = request.getSession().getAttribute("loop").toString();
								}
								if (request.getSession().getAttribute("CATE_TYPE") != null) {
									type_isplay = (type_cate) request.getSession().getAttribute("CATE_TYPE");
								}
								if (request.getSession().getAttribute("place") != null) {
									place = request.getSession().getAttribute("place").toString();
								}
								if (request.getSession().getAttribute("id_isplay") != null) {
									String get = request.getSession().getAttribute("id_isplay").toString();
									if (get.equals("Love_song_pll")) {
										id_ispl_string = "Love_song_pll";
										check_id_string = true;
									} else if (get.equals("Love_pod_pll")) {
										id_ispl_string = "Love_pod_pll";
										check_id_string = true;
									} else {
										id_ispl = get;
									}
								}
								if (request.getSession().getAttribute("song_isplay") != null) {
									med_play_id = Integer
											.parseInt(request.getSession().getAttribute("song_isplay").toString());
								}
								System.out.println(med_play_id);
								switch (place) {

									case "playlist":
										if (check_id_string == false) {
											int index_med = 0;
											for (int i = 0; i < listmed_is_playing.size(); i++) {
												if (listmed_is_playing.get(i) == med_play_id) {
													index_med = i;
													System.out.println(index_med);
													break;
												}
											}
											boolean suf = false;
											if (suflist.size() > 0) {
												for (int i = 0; i < suflist.size(); i++) {
													if (suflist.get(i).id.equals("" + id_ispl)
															&& suflist.get(i).stat == true) {
														suf = true;
														System.out.println("yes");
														break;
													}
													System.out.println("no");
												}
											} else {
												SufOrUnsuf newpll = new SufOrUnsuf();
												newpll.id = "" + id_ispl;
												newpll.stat = false;
												suflist.add(newpll);
												request.getSession().setAttribute("SufList", suflist);
											}

											if (suf == true) {
												if (loop.equals("unlo")) {
													if (index_med == 0) {
														System.out.println("un loop shuffle pll end list");
														Random random = new Random();
														int newId = listmed_is_playing.get(index_med);
														if (listmed_is_playing.size() > 1) {
															while (newId == listmed_is_playing.get(index_med)) {
																int ran_index = random
																		.nextInt(listmed_is_playing.size());
																newId = listmed_is_playing.get(ran_index);
															}
														} else {
															newId = random.nextInt(listmed_is_playing.size());
														}
														request.getSession().setAttribute("song_isplay", newId);
														try {
															ResultSet rs = stmt.executeQuery(
																	"SELECT img_path,media_name,performer, file_path from media where id = '"
																			+ newId + "'");
															String resImg = null;
															String resName = null;
															String resPer = null;
															String resFile = null;
															while (rs.next()) {
																resImg = rs.getString(1);
																resName = rs.getString(2);
																resPer = rs.getString(3);
																resFile = rs.getString(4);
															}
															ArrayList<Integer> luv = new ArrayList<Integer>();
															if (request.getSession().getAttribute("Med_fav") != null) {
																luv = (ArrayList<Integer>) request.getSession()
																		.getAttribute("Med_fav");
															}
															boolean love = false;
															if (luv.contains(newId)) {
																love = true;
															}
															response.setContentType("application/json");
															response.setCharacterEncoding("UTF-8");
															response.getWriter().write("{\"status\":\"true\",\"Img\":\""
																	+ resImg + "\",\"Name\":\"" + resName
																	+ "\",\"Per\":\"" + resPer + "\",\"path\":\""
																	+ resFile + "\", \"love\":\"" + love + "\"}");
														} catch (Exception e) {
															e.printStackTrace();
														}
													} else {
														System.out.println("un loop shuffle pll in list");
														Random random = new Random();
														int newId = listmed_is_playing.get(index_med);
														if (listmed_is_playing.size() > 1) {
															while (newId == listmed_is_playing.get(index_med)) {
																int ran_index = random
																		.nextInt(listmed_is_playing.size());
																newId = listmed_is_playing.get(ran_index);
															}
														} else {
															newId = random.nextInt(listmed_is_playing.size());
														}
														request.getSession().setAttribute("song_isplay", newId);
														try {
															ResultSet rs = stmt.executeQuery(
																	"SELECT img_path,media_name,performer, file_path from media where id = '"
																			+ newId + "';");
															String resImg = null;
															String resName = null;
															String resPer = null;
															String resFile = null;
															while (rs.next()) {
																resImg = rs.getString(1);
																resName = rs.getString(2);
																resPer = rs.getString(3);
																resFile = rs.getString(4);
															}
															ArrayList<Integer> luv = new ArrayList<Integer>();
															if (request.getSession().getAttribute("Med_fav") != null) {
																luv = (ArrayList<Integer>) request.getSession()
																		.getAttribute("Med_fav");
															}
															boolean love = false;
															if (luv.contains(newId)) {
																love = true;
															}
															response.setContentType("application/json");
															response.setCharacterEncoding("UTF-8");
															response.getWriter().write("{\"status\":\"true\",\"Img\":\""
																	+ resImg + "\",\"Name\":\"" + resName
																	+ "\",\"Per\":\"" + resPer + "\",\"path\":\""
																	+ resFile + "\", \"love\":\"" + love + "\"}");
														} catch (Exception e) {
															e.printStackTrace();
														}
													}
												} else if (loop.equals("lall")) {
													if (index_med == 0) {
														System.out.println("loop all shuffle end list");
														Random random = new Random();
														int new_id = listmed_is_playing.get(index_med);
														if (listmed_is_playing.size() > 1) {
															while (new_id == listmed_is_playing.get(index_med)) {
																int ran_index = random
																		.nextInt(listmed_is_playing.size());
																new_id = listmed_is_playing.get(ran_index);
															}
														} else {
															new_id = random.nextInt(listmed_is_playing.size());
														}
														request.getSession().setAttribute("song_isplay", new_id);
														try {
															ResultSet rs = stmt.executeQuery(
																	"SELECT img_path,media_name,performer, file_path from media where id = '"
																			+ new_id + "'");
															String resImg = null;
															String resName = null;
															String resPer = null;
															String resFile = null;
															while (rs.next()) {
																resImg = rs.getString(1);
																resName = rs.getString(2);
																resPer = rs.getString(3);
																resFile = rs.getString(4);
															}
															ArrayList<Integer> luv = new ArrayList<Integer>();
															if (request.getSession().getAttribute("Med_fav") != null) {
																luv = (ArrayList<Integer>) request.getSession()
																		.getAttribute("Med_fav");
															}
															boolean love = false;
															if (luv.contains(new_id)) {
																love = true;
															}
															response.setContentType("application/json");
															response.setCharacterEncoding("UTF-8");
															response.getWriter().write("{\"status\":\"true\",\"Img\":\""
																	+ resImg + "\",\"Name\":\"" + resName
																	+ "\",\"Per\":\"" + resPer + "\",\"path\":\""
																	+ resFile + "\", \"love\":\"" + love + "\"}");
														} catch (Exception e) {
															e.printStackTrace();
														}

													} else {
														System.out.println("loop all shuffle in list");
														Random random = new Random();
														int newId = listmed_is_playing.get(index_med);
														if (listmed_is_playing.size() > 1) {
															while (newId == listmed_is_playing.get(index_med)) {
																int ran_index = random
																		.nextInt(listmed_is_playing.size());
																newId = listmed_is_playing.get(ran_index);
															}
														} else {
															newId = random.nextInt(listmed_is_playing.size());
														}

														request.getSession().setAttribute("song_isplay", newId);
														try {
															ResultSet rs = stmt.executeQuery(
																	"SELECT img_path,media_name,performer, file_path from media where id = '"
																			+ newId + "'");
															String resImg = null;
															String resName = null;
															String resPer = null;
															String resFile = null;
															while (rs.next()) {
																resImg = rs.getString(1);
																resName = rs.getString(2);
																resPer = rs.getString(3);
																resFile = rs.getString(4);
															}
															ArrayList<Integer> luv = new ArrayList<Integer>();
															if (request.getSession().getAttribute("Med_fav") != null) {
																luv = (ArrayList<Integer>) request.getSession()
																		.getAttribute("Med_fav");
															}
															boolean love = false;
															if (luv.contains(newId)) {
																love = true;
															}
															response.setContentType("application/json");
															response.setCharacterEncoding("UTF-8");
															response.getWriter().write("{\"status\":\"true\",\"Img\":\""
																	+ resImg + "\",\"Name\":\"" + resName
																	+ "\",\"Per\":\"" + resPer + "\",\"path\":\""
																	+ resFile + "\", \"love\":\"" + love + "\"}");
														} catch (Exception e) {
															e.printStackTrace();
														}
													}
												} else if (loop.equals("lon")) {
													System.out.println("loop one shuffle in list");
													response.setContentType("application/json");
													response.setCharacterEncoding("UTF-8");
													response.getWriter().write("{\"status\":\"false\"}");
												}

											} else {
												if (loop.equals("unlo")) {
													if (index_med == 0) {
														int new_id = listmed_is_playing.get(index_med);
														request.getSession().setAttribute("song_isplay", new_id);
														try {
															ResultSet rs = stmt.executeQuery(
																	"SELECT img_path,media_name,performer, file_path from media where id = '"
																			+ new_id + "'");
															String resImg = null;
															String resName = null;
															String resPer = null;
															String resFile = null;
															while (rs.next()) {
																resImg = rs.getString(1);
																resName = rs.getString(2);
																resPer = rs.getString(3);
																resFile = rs.getString(4);
															}
															ArrayList<Integer> luv = new ArrayList<Integer>();
															if (request.getSession().getAttribute("Med_fav") != null) {
																luv = (ArrayList<Integer>) request.getSession()
																		.getAttribute("Med_fav");
															}
															boolean love = false;
															if (luv.contains(new_id)) {
																love = true;
															}
															response.setContentType("application/json");
															response.setCharacterEncoding("UTF-8");
															response.getWriter().write("{\"status\":\"true\",\"Img\":\""
																	+ resImg + "\",\"Name\":\"" + resName
																	+ "\",\"Per\":\"" + resPer + "\",\"path\":\""
																	+ resFile + "\", \"love\":\"" + love + "\"}");
														} catch (Exception e) {
															e.printStackTrace();
														}
													} else {
														int newId1 = listmed_is_playing.get(index_med - 1);
														System.out.println("unloop not shuffle in pll");
														System.out.println("New id: " + newId1);
														request.getSession().setAttribute("song_isplay", newId1);
														try {
															ResultSet rs = stmt.executeQuery(
																	"SELECT img_path,media_name,performer, file_path from media where id = '"
																			+ newId1 + "';");
															String resImg = null;
															String resName = null;
															String resPer = null;
															String resFile = null;
															while (rs.next()) {
																resImg = rs.getString(1);
																resName = rs.getString(2);
																resPer = rs.getString(3);
																resFile = rs.getString(4);
															}
															ArrayList<Integer> luv = new ArrayList<Integer>();
															if (request.getSession().getAttribute("Med_fav") != null) {
																luv = (ArrayList<Integer>) request.getSession()
																		.getAttribute("Med_fav");
															}
															boolean love = false;
															if (luv.contains(newId1)) {
																love = true;
															}
															response.setContentType("application/json");
															response.setCharacterEncoding("UTF-8");
															response.getWriter().write("{\"status\":\"true\",\"Img\":\""
																	+ resImg + "\",\"Name\":\"" + resName
																	+ "\",\"Per\":\"" + resPer + "\",\"path\":\""
																	+ resFile + "\", \"love\":\"" + love + "\"}");
														} catch (Exception e) {
															e.printStackTrace();
														}
													}
												} else if (loop.equals("lall")) {
													if (index_med == 0) {
														int new_id = listmed_is_playing
																.get(listmed_is_playing.size() - 1);
														request.getSession().setAttribute("song_isplay", new_id);
														try {
															ResultSet rs = stmt.executeQuery(
																	"SELECT img_path,media_name,performer, file_path from media where id = '"
																			+ new_id + "'");
															String resImg = null;
															String resName = null;
															String resPer = null;
															String resFile = null;
															while (rs.next()) {
																resImg = rs.getString(1);
																resName = rs.getString(2);
																resPer = rs.getString(3);
																resFile = rs.getString(4);
															}
															ArrayList<Integer> luv = new ArrayList<Integer>();
															if (request.getSession().getAttribute("Med_fav") != null) {
																luv = (ArrayList<Integer>) request.getSession()
																		.getAttribute("Med_fav");
															}
															boolean love = false;
															if (luv.contains(new_id)) {
																love = true;
															}
															response.setContentType("application/json");
															response.setCharacterEncoding("UTF-8");
															response.getWriter().write("{\"status\":\"true\",\"Img\":\""
																	+ resImg + "\",\"Name\":\"" + resName
																	+ "\",\"Per\":\"" + resPer + "\",\"path\":\""
																	+ resFile + "\", \"love\":\"" + love + "\"}");
														} catch (Exception e) {
															e.printStackTrace();
														}

													} else {
														int newId = listmed_is_playing.get(index_med - 1);
														request.getSession().setAttribute("song_isplay", newId);
														try {
															ResultSet rs = stmt.executeQuery(
																	"SELECT img_path,media_name,performer, file_path from media where id = '"
																			+ newId + "'");
															String resImg = null;
															String resName = null;
															String resPer = null;
															String resFile = null;
															while (rs.next()) {
																resImg = rs.getString(1);
																resName = rs.getString(2);
																resPer = rs.getString(3);
																resFile = rs.getString(4);
															}
															ArrayList<Integer> luv = new ArrayList<Integer>();
															if (request.getSession().getAttribute("Med_fav") != null) {
																luv = (ArrayList<Integer>) request.getSession()
																		.getAttribute("Med_fav");
															}
															boolean love = false;
															if (luv.contains(newId)) {
																love = true;
															}
															response.setContentType("application/json");
															response.setCharacterEncoding("UTF-8");
															response.getWriter().write("{\"status\":\"true\",\"Img\":\""
																	+ resImg + "\",\"Name\":\"" + resName
																	+ "\",\"Per\":\"" + resPer + "\",\"path\":\""
																	+ resFile + "\", \"love\":\"" + love + "\"}");
														} catch (Exception e) {
															e.printStackTrace();
														}
													}

												} else if (loop.equals("lon")) {
													response.setContentType("application/json");
													response.setCharacterEncoding("UTF-8");
													response.getWriter().write("{\"status\":\"false\"}");
												}
											}
										} else {
											if (id_ispl_string.equals("Love_song_pll")) {
												boolean suf = false;
												if (request.getSession().getAttribute("suf_song_love") != null) {
													suf = (boolean) request.getSession().getAttribute("suf_song_love");
												}
												int index_med = 0;
												for (int i = 0; i < listmed_is_playing.size(); i++) {
													if (listmed_is_playing.get(i) == med_play_id) {
														index_med = i;
														System.out.println(index_med);
														break;
													}
												}
												if (suf == true) {
													if (loop.equals("unlo")) {

														if (index_med == 0) {
															System.out.println("un loop shuffle pll end list");
															Random random = new Random();
															int newId = listmed_is_playing.get(index_med);
															if (listmed_is_playing.size() > 1) {
																while (newId == listmed_is_playing.get(index_med)) {
																	int ran_index = random
																			.nextInt(listmed_is_playing.size());
																	newId = listmed_is_playing.get(ran_index);
																}
															} else {
																newId = random.nextInt(listmed_is_playing.size());
															}
															request.getSession().setAttribute("song_isplay", newId);
															try {
																ResultSet rs = stmt.executeQuery(
																		"SELECT img_path,media_name,performer, file_path from media where id = '"
																				+ newId + "'");
																String resImg = null;
																String resName = null;
																String resPer = null;
																String resFile = null;
																while (rs.next()) {
																	resImg = rs.getString(1);
																	resName = rs.getString(2);
																	resPer = rs.getString(3);
																	resFile = rs.getString(4);
																}
																ArrayList<Integer> luv = new ArrayList<Integer>();
																if (request.getSession()
																		.getAttribute("Med_fav") != null) {
																	luv = (ArrayList<Integer>) request.getSession()
																			.getAttribute("Med_fav");
																}
																boolean love = false;
																if (luv.contains(newId)) {
																	love = true;
																}
																response.setContentType("application/json");
																response.setCharacterEncoding("UTF-8");
																response.getWriter()
																		.write("{\"status\":\"true\",\"Img\":\""
																				+ resImg + "\",\"Name\":\"" + resName
																				+ "\",\"Per\":\"" + resPer
																				+ "\",\"path\":\"" + resFile
																				+ "\", \"love\":\"" + love + "\"}");
															} catch (Exception e) {
																e.printStackTrace();
															}
														} else {
															System.out.println("un loop shuffle pll in list");
															Random random = new Random();
															int newId = listmed_is_playing.get(index_med);
															if (listmed_is_playing.size() > 1) {
																while (newId == listmed_is_playing.get(index_med)) {
																	int ran_index = random
																			.nextInt(listmed_is_playing.size());
																	newId = listmed_is_playing.get(ran_index);
																}
															} else {
																newId = random.nextInt(listmed_is_playing.size());
															}
															request.getSession().setAttribute("song_isplay", newId);
															try {
																ResultSet rs = stmt.executeQuery(
																		"SELECT img_path,media_name,performer, file_path from media where id = '"
																				+ newId + "';");
																String resImg = null;
																String resName = null;
																String resPer = null;
																String resFile = null;
																while (rs.next()) {
																	resImg = rs.getString(1);
																	resName = rs.getString(2);
																	resPer = rs.getString(3);
																	resFile = rs.getString(4);
																}
																ArrayList<Integer> luv = new ArrayList<Integer>();
																if (request.getSession()
																		.getAttribute("Med_fav") != null) {
																	luv = (ArrayList<Integer>) request.getSession()
																			.getAttribute("Med_fav");
																}
																boolean love = false;
																if (luv.contains(newId)) {
																	love = true;
																}
																response.setContentType("application/json");
																response.setCharacterEncoding("UTF-8");
																response.getWriter()
																		.write("{\"status\":\"true\",\"Img\":\""
																				+ resImg + "\",\"Name\":\"" + resName
																				+ "\",\"Per\":\"" + resPer
																				+ "\",\"path\":\"" + resFile
																				+ "\", \"love\":\"" + love + "\"}");
															} catch (Exception e) {
																e.printStackTrace();
															}
														}
													} else if (loop.equals("lall")) {
														if (index_med == 0) {
															System.out.println("loop all shuffle end list");
															Random random = new Random();
															int new_id = listmed_is_playing.get(index_med);
															if (listmed_is_playing.size() > 1) {
																while (new_id == listmed_is_playing.get(index_med)) {
																	int ran_index = random
																			.nextInt(listmed_is_playing.size());
																	new_id = listmed_is_playing.get(ran_index);
																}
															} else {
																new_id = random.nextInt(listmed_is_playing.size());
															}
															request.getSession().setAttribute("song_isplay", new_id);
															try {
																ResultSet rs = stmt.executeQuery(
																		"SELECT img_path,media_name,performer, file_path from media where id = '"
																				+ new_id + "'");
																String resImg = null;
																String resName = null;
																String resPer = null;
																String resFile = null;
																while (rs.next()) {
																	resImg = rs.getString(1);
																	resName = rs.getString(2);
																	resPer = rs.getString(3);
																	resFile = rs.getString(4);
																}
																ArrayList<Integer> luv = new ArrayList<Integer>();
																if (request.getSession()
																		.getAttribute("Med_fav") != null) {
																	luv = (ArrayList<Integer>) request.getSession()
																			.getAttribute("Med_fav");
																}
																boolean love = false;
																if (luv.contains(new_id)) {
																	love = true;
																}
																response.setContentType("application/json");
																response.setCharacterEncoding("UTF-8");
																response.getWriter()
																		.write("{\"status\":\"true\",\"Img\":\""
																				+ resImg + "\",\"Name\":\"" + resName
																				+ "\",\"Per\":\"" + resPer
																				+ "\",\"path\":\"" + resFile
																				+ "\", \"love\":\"" + love + "\"}");
															} catch (Exception e) {
																e.printStackTrace();
															}

														} else {
															System.out.println("loop all shuffle in list");
															Random random = new Random();
															int newId = listmed_is_playing.get(index_med);
															if (listmed_is_playing.size() > 1) {
																while (newId == listmed_is_playing.get(index_med)) {
																	int ran_index = random
																			.nextInt(listmed_is_playing.size());
																	newId = listmed_is_playing.get(ran_index);
																}
															} else {
																newId = random.nextInt(listmed_is_playing.size());
															}

															request.getSession().setAttribute("song_isplay", newId);
															try {
																ResultSet rs = stmt.executeQuery(
																		"SELECT img_path,media_name,performer, file_path from media where id = '"
																				+ newId + "'");
																String resImg = null;
																String resName = null;
																String resPer = null;
																String resFile = null;
																while (rs.next()) {
																	resImg = rs.getString(1);
																	resName = rs.getString(2);
																	resPer = rs.getString(3);
																	resFile = rs.getString(4);
																}
																ArrayList<Integer> luv = new ArrayList<Integer>();
																if (request.getSession()
																		.getAttribute("Med_fav") != null) {
																	luv = (ArrayList<Integer>) request.getSession()
																			.getAttribute("Med_fav");
																}
																boolean love = false;
																if (luv.contains(newId)) {
																	love = true;
																}
																response.setContentType("application/json");
																response.setCharacterEncoding("UTF-8");
																response.getWriter()
																		.write("{\"status\":\"true\",\"Img\":\""
																				+ resImg + "\",\"Name\":\"" + resName
																				+ "\",\"Per\":\"" + resPer
																				+ "\",\"path\":\"" + resFile
																				+ "\", \"love\":\"" + love + "\"}");
															} catch (Exception e) {
																e.printStackTrace();
															}
														}

													} else if (loop.equals("lon")) {
														System.out.println("loop one shuffle in list");
														response.setContentType("application/json");
														response.setCharacterEncoding("UTF-8");
														response.getWriter().write("{\"status\":\"false\"}");
													}

												} else {
													if (loop.equals("unlo")) {
														if (index_med == 0) {
															int new_id = listmed_is_playing.get(index_med);
															request.getSession().setAttribute("song_isplay", new_id);
															try {
																ResultSet rs = stmt.executeQuery(
																		"SELECT img_path,media_name,performer, file_path from media where id = '"
																				+ new_id + "'");
																String resImg = null;
																String resName = null;
																String resPer = null;
																String resFile = null;
																while (rs.next()) {
																	resImg = rs.getString(1);
																	resName = rs.getString(2);
																	resPer = rs.getString(3);
																	resFile = rs.getString(4);
																}
																ArrayList<Integer> luv = new ArrayList<Integer>();
																if (request.getSession()
																		.getAttribute("Med_fav") != null) {
																	luv = (ArrayList<Integer>) request.getSession()
																			.getAttribute("Med_fav");
																}
																boolean love = false;
																if (luv.contains(new_id)) {
																	love = true;
																}
																response.setContentType("application/json");
																response.setCharacterEncoding("UTF-8");
																response.getWriter()
																		.write("{\"status\":\"true\",\"Img\":\""
																				+ resImg + "\",\"Name\":\"" + resName
																				+ "\",\"Per\":\"" + resPer
																				+ "\",\"path\":\"" + resFile
																				+ "\", \"love\":\"" + love + "\"}");
															} catch (Exception e) {
																e.printStackTrace();
															}
														} else {
															int newId1 = listmed_is_playing.get(index_med - 1);
															System.out.println("unloop not shuffle in pll");
															System.out.println("New id: " + newId1);
															request.getSession().setAttribute("song_isplay", newId1);
															try {
																ResultSet rs = stmt.executeQuery(
																		"SELECT img_path,media_name,performer, file_path from media where id = '"
																				+ newId1 + "';");
																String resImg = null;
																String resName = null;
																String resPer = null;
																String resFile = null;
																while (rs.next()) {
																	resImg = rs.getString(1);
																	resName = rs.getString(2);
																	resPer = rs.getString(3);
																	resFile = rs.getString(4);
																}
																ArrayList<Integer> luv = new ArrayList<Integer>();
																if (request.getSession()
																		.getAttribute("Med_fav") != null) {
																	luv = (ArrayList<Integer>) request.getSession()
																			.getAttribute("Med_fav");
																}
																boolean love = false;
																if (luv.contains(newId1)) {
																	love = true;
																}
																response.setContentType("application/json");
																response.setCharacterEncoding("UTF-8");
																response.getWriter()
																		.write("{\"status\":\"true\",\"Img\":\""
																				+ resImg + "\",\"Name\":\"" + resName
																				+ "\",\"Per\":\"" + resPer
																				+ "\",\"path\":\"" + resFile
																				+ "\", \"love\":\"" + love + "\"}");
															} catch (Exception e) {
																e.printStackTrace();
															}
														}
													} else if (loop.equals("lall")) {
														if (index_med == 0) {
															int new_id = listmed_is_playing
																	.get(listmed_is_playing.size() - 1);
															request.getSession().setAttribute("song_isplay", new_id);
															try {
																ResultSet rs = stmt.executeQuery(
																		"SELECT img_path,media_name,performer, file_path from media where id = '"
																				+ new_id + "'");
																String resImg = null;
																String resName = null;
																String resPer = null;
																String resFile = null;
																while (rs.next()) {
																	resImg = rs.getString(1);
																	resName = rs.getString(2);
																	resPer = rs.getString(3);
																	resFile = rs.getString(4);
																}
																ArrayList<Integer> luv = new ArrayList<Integer>();
																if (request.getSession()
																		.getAttribute("Med_fav") != null) {
																	luv = (ArrayList<Integer>) request.getSession()
																			.getAttribute("Med_fav");
																}
																boolean love = false;
																if (luv.contains(new_id)) {
																	love = true;
																}
																response.setContentType("application/json");
																response.setCharacterEncoding("UTF-8");
																response.getWriter()
																		.write("{\"status\":\"true\",\"Img\":\""
																				+ resImg + "\",\"Name\":\"" + resName
																				+ "\",\"Per\":\"" + resPer
																				+ "\",\"path\":\"" + resFile
																				+ "\", \"love\":\"" + love + "\"}");
															} catch (Exception e) {
																e.printStackTrace();
															}

														} else {
															int newId = listmed_is_playing.get(index_med - 1);
															request.getSession().setAttribute("song_isplay", newId);
															try {
																ResultSet rs = stmt.executeQuery(
																		"SELECT img_path,media_name,performer, file_path from media where id = '"
																				+ newId + "'");
																String resImg = null;
																String resName = null;
																String resPer = null;
																String resFile = null;
																while (rs.next()) {
																	resImg = rs.getString(1);
																	resName = rs.getString(2);
																	resPer = rs.getString(3);
																	resFile = rs.getString(4);
																}
																ArrayList<Integer> luv = new ArrayList<Integer>();
																if (request.getSession()
																		.getAttribute("Med_fav") != null) {
																	luv = (ArrayList<Integer>) request.getSession()
																			.getAttribute("Med_fav");
																}
																boolean love = false;
																if (luv.contains(newId)) {
																	love = true;
																}
																response.setContentType("application/json");
																response.setCharacterEncoding("UTF-8");
																response.getWriter()
																		.write("{\"status\":\"true\",\"Img\":\""
																				+ resImg + "\",\"Name\":\"" + resName
																				+ "\",\"Per\":\"" + resPer
																				+ "\",\"path\":\"" + resFile
																				+ "\", \"love\":\"" + love + "\"}");
															} catch (Exception e) {
																e.printStackTrace();
															}
														}

													} else if (loop.equals("lon")) {
														response.setContentType("application/json");
														response.setCharacterEncoding("UTF-8");
														response.getWriter().write("{\"status\":\"false\"}");
													}
												}
											} else if (id_ispl_string.equals("Love_pod_pll")) {
												boolean suf = false;
												if (request.getSession().getAttribute("suf_song_love") != null) {
													suf = (boolean) request.getSession().getAttribute("suf_song_love");
												}
												int index_med = 0;
												for (int i = 0; i < listmed_is_playing.size(); i++) {
													if (listmed_is_playing.get(i) == med_play_id) {
														index_med = i;
														System.out.println(index_med);
														break;
													}
												}
												if (suf == true) {
													if (loop.equals("unlo")) {
														if (index_med == 0) {
															System.out.println("un loop shuffle pll end list");
															Random random = new Random();
															int newId = listmed_is_playing.get(index_med);
															if (listmed_is_playing.size() > 1) {
																while (newId == listmed_is_playing.get(index_med)) {
																	int ran_index = random
																			.nextInt(listmed_is_playing.size());
																	newId = listmed_is_playing.get(ran_index);
																}
															} else {
																newId = random.nextInt(listmed_is_playing.size());
															}
															request.getSession().setAttribute("song_isplay", newId);
															try {
																ResultSet rs = stmt.executeQuery(
																		"SELECT img_path,media_name,performer, file_path from media where id = '"
																				+ newId + "'");
																String resImg = null;
																String resName = null;
																String resPer = null;
																String resFile = null;
																while (rs.next()) {
																	resImg = rs.getString(1);
																	resName = rs.getString(2);
																	resPer = rs.getString(3);
																	resFile = rs.getString(4);
																}
																ArrayList<Integer> luv = new ArrayList<Integer>();
																if (request.getSession()
																		.getAttribute("Med_fav") != null) {
																	luv = (ArrayList<Integer>) request.getSession()
																			.getAttribute("Med_fav");
																}
																boolean love = false;
																if (luv.contains(newId)) {
																	love = true;
																}
																response.setContentType("application/json");
																response.setCharacterEncoding("UTF-8");
																response.getWriter()
																		.write("{\"status\":\"true\",\"Img\":\""
																				+ resImg + "\",\"Name\":\"" + resName
																				+ "\",\"Per\":\"" + resPer
																				+ "\",\"path\":\"" + resFile
																				+ "\", \"love\":\"" + love + "\"}");
															} catch (Exception e) {
																e.printStackTrace();
															}
														} else {
															System.out.println("un loop shuffle pll in list");
															Random random = new Random();
															int newId = listmed_is_playing.get(index_med);
															if (listmed_is_playing.size() > 1) {
																while (newId == listmed_is_playing.get(index_med)) {
																	int ran_index = random
																			.nextInt(listmed_is_playing.size());
																	newId = listmed_is_playing.get(ran_index);
																}
															} else {
																newId = random.nextInt(listmed_is_playing.size());
															}
															request.getSession().setAttribute("song_isplay", newId);
															try {
																ResultSet rs = stmt.executeQuery(
																		"SELECT img_path,media_name,performer, file_path from media where id = '"
																				+ newId + "';");
																String resImg = null;
																String resName = null;
																String resPer = null;
																String resFile = null;
																while (rs.next()) {
																	resImg = rs.getString(1);
																	resName = rs.getString(2);
																	resPer = rs.getString(3);
																	resFile = rs.getString(4);
																}
																ArrayList<Integer> luv = new ArrayList<Integer>();
																if (request.getSession()
																		.getAttribute("Med_fav") != null) {
																	luv = (ArrayList<Integer>) request.getSession()
																			.getAttribute("Med_fav");
																}
																boolean love = false;
																if (luv.contains(newId)) {
																	love = true;
																}
																response.setContentType("application/json");
																response.setCharacterEncoding("UTF-8");
																response.getWriter()
																		.write("{\"status\":\"true\",\"Img\":\""
																				+ resImg + "\",\"Name\":\"" + resName
																				+ "\",\"Per\":\"" + resPer
																				+ "\",\"path\":\"" + resFile
																				+ "\", \"love\":\"" + love + "\"}");
															} catch (Exception e) {
																e.printStackTrace();
															}
														}
													} else if (loop.equals("lall")) {
														if (index_med == 0) {
															System.out.println("loop all shuffle end list");
															Random random = new Random();
															int new_id = listmed_is_playing.get(index_med);
															if (listmed_is_playing.size() > 1) {
																while (new_id == listmed_is_playing.get(index_med)) {
																	int ran_index = random
																			.nextInt(listmed_is_playing.size());
																	new_id = listmed_is_playing.get(ran_index);
																}
															} else {
																new_id = random.nextInt(listmed_is_playing.size());
															}
															request.getSession().setAttribute("song_isplay", new_id);
															try {
																ResultSet rs = stmt.executeQuery(
																		"SELECT img_path,media_name,performer, file_path from media where id = '"
																				+ new_id + "'");
																String resImg = null;
																String resName = null;
																String resPer = null;
																String resFile = null;
																while (rs.next()) {
																	resImg = rs.getString(1);
																	resName = rs.getString(2);
																	resPer = rs.getString(3);
																	resFile = rs.getString(4);
																}
																ArrayList<Integer> luv = new ArrayList<Integer>();
																if (request.getSession()
																		.getAttribute("Med_fav") != null) {
																	luv = (ArrayList<Integer>) request.getSession()
																			.getAttribute("Med_fav");
																}
																boolean love = false;
																if (luv.contains(new_id)) {
																	love = true;
																}
																response.setContentType("application/json");
																response.setCharacterEncoding("UTF-8");
																response.getWriter()
																		.write("{\"status\":\"true\",\"Img\":\""
																				+ resImg + "\",\"Name\":\"" + resName
																				+ "\",\"Per\":\"" + resPer
																				+ "\",\"path\":\"" + resFile
																				+ "\", \"love\":\"" + love + "\"}");
															} catch (Exception e) {
																e.printStackTrace();
															}

														} else {
															System.out.println("loop all shuffle in list");
															Random random = new Random();
															int newId = listmed_is_playing.get(index_med);
															if (listmed_is_playing.size() > 1) {
																while (newId == listmed_is_playing.get(index_med)) {
																	int ran_index = random
																			.nextInt(listmed_is_playing.size());
																	newId = listmed_is_playing.get(ran_index);
																}
															} else {
																newId = random.nextInt(listmed_is_playing.size());
															}

															request.getSession().setAttribute("song_isplay", newId);
															try {
																ResultSet rs = stmt.executeQuery(
																		"SELECT img_path,media_name,performer, file_path from media where id = '"
																				+ newId + "'");
																String resImg = null;
																String resName = null;
																String resPer = null;
																String resFile = null;
																while (rs.next()) {
																	resImg = rs.getString(1);
																	resName = rs.getString(2);
																	resPer = rs.getString(3);
																	resFile = rs.getString(4);
																}
																ArrayList<Integer> luv = new ArrayList<Integer>();
																if (request.getSession()
																		.getAttribute("Med_fav") != null) {
																	luv = (ArrayList<Integer>) request.getSession()
																			.getAttribute("Med_fav");
																}
																boolean love = false;
																if (luv.contains(newId)) {
																	love = true;
																}
																response.setContentType("application/json");
																response.setCharacterEncoding("UTF-8");
																response.getWriter()
																		.write("{\"status\":\"true\",\"Img\":\""
																				+ resImg + "\",\"Name\":\"" + resName
																				+ "\",\"Per\":\"" + resPer
																				+ "\",\"path\":\"" + resFile
																				+ "\", \"love\":\"" + love + "\"}");
															} catch (Exception e) {
																e.printStackTrace();
															}
														}

													} else if (loop.equals("lon")) {
														System.out.println("loop one shuffle in list");
														response.setContentType("application/json");
														response.setCharacterEncoding("UTF-8");
														response.getWriter().write("{\"status\":\"false\"}");
													}

												} else {
													if (loop.equals("unlo")) {
														if (index_med == 0) {
															int new_id = listmed_is_playing.get(index_med);
															request.getSession().setAttribute("song_isplay", new_id);
															try {
																ResultSet rs = stmt.executeQuery(
																		"SELECT img_path,media_name,performer, file_path from media where id = '"
																				+ new_id + "'");
																String resImg = null;
																String resName = null;
																String resPer = null;
																String resFile = null;
																while (rs.next()) {
																	resImg = rs.getString(1);
																	resName = rs.getString(2);
																	resPer = rs.getString(3);
																	resFile = rs.getString(4);
																}
																ArrayList<Integer> luv = new ArrayList<Integer>();
																if (request.getSession()
																		.getAttribute("Med_fav") != null) {
																	luv = (ArrayList<Integer>) request.getSession()
																			.getAttribute("Med_fav");
																}
																boolean love = false;
																if (luv.contains(new_id)) {
																	love = true;
																}
																response.setContentType("application/json");
																response.setCharacterEncoding("UTF-8");
																response.getWriter()
																		.write("{\"status\":\"true\",\"Img\":\""
																				+ resImg + "\",\"Name\":\"" + resName
																				+ "\",\"Per\":\"" + resPer
																				+ "\",\"path\":\"" + resFile
																				+ "\", \"love\":\"" + love + "\"}");
															} catch (Exception e) {
																e.printStackTrace();
															}
														} else {
															int newId1 = listmed_is_playing.get(index_med - 1);
															System.out.println("unloop not shuffle in pll");
															System.out.println("New id: " + newId1);
															request.getSession().setAttribute("song_isplay", newId1);
															try {
																ResultSet rs = stmt.executeQuery(
																		"SELECT img_path,media_name,performer, file_path from media where id = '"
																				+ newId1 + "';");
																String resImg = null;
																String resName = null;
																String resPer = null;
																String resFile = null;
																while (rs.next()) {
																	resImg = rs.getString(1);
																	resName = rs.getString(2);
																	resPer = rs.getString(3);
																	resFile = rs.getString(4);
																}
																ArrayList<Integer> luv = new ArrayList<Integer>();
																if (request.getSession()
																		.getAttribute("Med_fav") != null) {
																	luv = (ArrayList<Integer>) request.getSession()
																			.getAttribute("Med_fav");
																}
																boolean love = false;
																if (luv.contains(newId1)) {
																	love = true;
																}
																response.setContentType("application/json");
																response.setCharacterEncoding("UTF-8");
																response.getWriter()
																		.write("{\"status\":\"true\",\"Img\":\""
																				+ resImg + "\",\"Name\":\"" + resName
																				+ "\",\"Per\":\"" + resPer
																				+ "\",\"path\":\"" + resFile
																				+ "\", \"love\":\"" + love + "\"}");
															} catch (Exception e) {
																e.printStackTrace();
															}
														}
													} else if (loop.equals("lall")) {
														if (index_med == 0) {
															int new_id = listmed_is_playing
																	.get(listmed_is_playing.size() - 1);
															request.getSession().setAttribute("song_isplay", new_id);
															try {
																ResultSet rs = stmt.executeQuery(
																		"SELECT img_path,media_name,performer, file_path from media where id = '"
																				+ new_id + "'");
																String resImg = null;
																String resName = null;
																String resPer = null;
																String resFile = null;
																while (rs.next()) {
																	resImg = rs.getString(1);
																	resName = rs.getString(2);
																	resPer = rs.getString(3);
																	resFile = rs.getString(4);
																}
																ArrayList<Integer> luv = new ArrayList<Integer>();
																if (request.getSession()
																		.getAttribute("Med_fav") != null) {
																	luv = (ArrayList<Integer>) request.getSession()
																			.getAttribute("Med_fav");
																}
																boolean love = false;
																if (luv.contains(new_id)) {
																	love = true;
																}
																response.setContentType("application/json");
																response.setCharacterEncoding("UTF-8");
																response.getWriter()
																		.write("{\"status\":\"true\",\"Img\":\""
																				+ resImg + "\",\"Name\":\"" + resName
																				+ "\",\"Per\":\"" + resPer
																				+ "\",\"path\":\"" + resFile
																				+ "\", \"love\":\"" + love + "\"}");
															} catch (Exception e) {
																e.printStackTrace();
															}

														} else {
															int newId = listmed_is_playing.get(index_med - 1);
															request.getSession().setAttribute("song_isplay", newId);
															try {
																ResultSet rs = stmt.executeQuery(
																		"SELECT img_path,media_name,performer, file_path from media where id = '"
																				+ newId + "'");
																String resImg = null;
																String resName = null;
																String resPer = null;
																String resFile = null;
																while (rs.next()) {
																	resImg = rs.getString(1);
																	resName = rs.getString(2);
																	resPer = rs.getString(3);
																	resFile = rs.getString(4);
																}
																ArrayList<Integer> luv = new ArrayList<Integer>();
																if (request.getSession()
																		.getAttribute("Med_fav") != null) {
																	luv = (ArrayList<Integer>) request.getSession()
																			.getAttribute("Med_fav");
																}
																boolean love = false;
																if (luv.contains(newId)) {
																	love = true;
																}
																response.setContentType("application/json");
																response.setCharacterEncoding("UTF-8");
																response.getWriter()
																		.write("{\"status\":\"true\",\"Img\":\""
																				+ resImg + "\",\"Name\":\"" + resName
																				+ "\",\"Per\":\"" + resPer
																				+ "\",\"path\":\"" + resFile
																				+ "\", \"love\":\"" + love + "\"}");
															} catch (Exception e) {
																e.printStackTrace();
															}
														}

													} else if (loop.equals("lon")) {
														response.setContentType("application/json");
														response.setCharacterEncoding("UTF-8");
														response.getWriter().write("{\"status\":\"false\"}");
													}
												}
											}
										}

										break;

									case "artist":
										int index_med1 = 0;
										for (int i = 0; i < listmed_is_playing.size(); i++) {
											if (listmed_is_playing.get(i) == med_play_id) {
												index_med1 = i;
												System.out.println(index_med1);
												break;
											}
										}
										boolean suf1 = false;
										if (suflist.size() > 0) {
											for (int i = 0; i < suflist.size(); i++) {
												if (suflist.get(i).id.equals("" + id_ispl)
														&& suflist.get(i).stat == true) {
													suf1 = true;
													System.out.println("yes");
													break;
												}
												System.out.println("no");
											}
										} else {
											SufOrUnsuf newpll = new SufOrUnsuf();
											newpll.id = "" + id_ispl;
											newpll.stat = false;
											suflist.add(newpll);
											request.getSession().setAttribute("SufList", suflist);
										}

										if (suf1 == true) {
											if (loop.equals("unlo")) {
												if (index_med1 == 0) {
													System.out.println("un loop shuffle pll end list");
													Random random = new Random();
													int newId = listmed_is_playing.get(index_med1);
													if (listmed_is_playing.size() > 1) {
														while (newId == listmed_is_playing.get(index_med1)) {
															int ran_index = random.nextInt(listmed_is_playing.size());
															newId = listmed_is_playing.get(ran_index);
														}
													} else {
														newId = random.nextInt(listmed_is_playing.size());
													}
													request.getSession().setAttribute("song_isplay", newId);
													try {
														ResultSet rs = stmt.executeQuery(
																"SELECT img_path,media_name,performer, file_path from media where id = '"
																		+ newId + "'");
														String resImg = null;
														String resName = null;
														String resPer = null;
														String resFile = null;
														while (rs.next()) {
															resImg = rs.getString(1);
															resName = rs.getString(2);
															resPer = rs.getString(3);
															resFile = rs.getString(4);
														}
														ArrayList<Integer> luv = new ArrayList<Integer>();
														if (request.getSession().getAttribute("Med_fav") != null) {
															luv = (ArrayList<Integer>) request.getSession()
																	.getAttribute("Med_fav");
														}
														boolean love = false;
														if (luv.contains(newId)) {
															love = true;
														}
														response.setContentType("application/json");
														response.setCharacterEncoding("UTF-8");
														response.getWriter()
																.write("{\"status\":\"true\",\"Img\":\"" + resImg
																		+ "\",\"Name\":\"" + resName + "\",\"Per\":\""
																		+ resPer + "\",\"path\":\"" + resFile
																		+ "\", \"love\":\"" + love + "\"}");
													} catch (Exception e) {
														e.printStackTrace();
													}
												} else {
													System.out.println("un loop shuffle pll in list");
													Random random = new Random();
													int newId = listmed_is_playing.get(index_med1);
													if (listmed_is_playing.size() > 1) {
														while (newId == listmed_is_playing.get(index_med1)) {
															int ran_index = random.nextInt(listmed_is_playing.size());
															newId = listmed_is_playing.get(ran_index);
														}
													} else {
														newId = random.nextInt(listmed_is_playing.size());
													}
													request.getSession().setAttribute("song_isplay", newId);
													try {
														ResultSet rs = stmt.executeQuery(
																"SELECT img_path,media_name,performer, file_path from media where id = '"
																		+ newId + "';");
														String resImg = null;
														String resName = null;
														String resPer = null;
														String resFile = null;
														while (rs.next()) {
															resImg = rs.getString(1);
															resName = rs.getString(2);
															resPer = rs.getString(3);
															resFile = rs.getString(4);
														}
														ArrayList<Integer> luv = new ArrayList<Integer>();
														if (request.getSession().getAttribute("Med_fav") != null) {
															luv = (ArrayList<Integer>) request.getSession()
																	.getAttribute("Med_fav");
														}
														boolean love = false;
														if (luv.contains(newId)) {
															love = true;
														}
														response.setContentType("application/json");
														response.setCharacterEncoding("UTF-8");
														response.getWriter()
																.write("{\"status\":\"true\",\"Img\":\"" + resImg
																		+ "\",\"Name\":\"" + resName + "\",\"Per\":\""
																		+ resPer + "\",\"path\":\"" + resFile
																		+ "\", \"love\":\"" + love + "\"}");
													} catch (Exception e) {
														e.printStackTrace();
													}
												}
											} else if (loop.equals("lall")) {
												if (index_med1 == 0) {
													System.out.println("loop all shuffle end list");
													Random random = new Random();
													int new_id = listmed_is_playing.get(index_med1);
													if (listmed_is_playing.size() > 1) {
														while (new_id == listmed_is_playing.get(index_med1)) {
															int ran_index = random.nextInt(listmed_is_playing.size());
															new_id = listmed_is_playing.get(ran_index);
														}
													} else {
														new_id = random.nextInt(listmed_is_playing.size());
													}
													request.getSession().setAttribute("song_isplay", new_id);
													try {
														ResultSet rs = stmt.executeQuery(
																"SELECT img_path,media_name,performer, file_path from media where id = '"
																		+ new_id + "'");
														String resImg = null;
														String resName = null;
														String resPer = null;
														String resFile = null;
														while (rs.next()) {
															resImg = rs.getString(1);
															resName = rs.getString(2);
															resPer = rs.getString(3);
															resFile = rs.getString(4);
														}
														ArrayList<Integer> luv = new ArrayList<Integer>();
														if (request.getSession().getAttribute("Med_fav") != null) {
															luv = (ArrayList<Integer>) request.getSession()
																	.getAttribute("Med_fav");
														}
														boolean love = false;
														if (luv.contains(new_id)) {
															love = true;
														}
														response.setContentType("application/json");
														response.setCharacterEncoding("UTF-8");
														response.getWriter()
																.write("{\"status\":\"true\",\"Img\":\"" + resImg
																		+ "\",\"Name\":\"" + resName + "\",\"Per\":\""
																		+ resPer + "\",\"path\":\"" + resFile
																		+ "\", \"love\":\"" + love + "\"}");
													} catch (Exception e) {
														e.printStackTrace();
													}

												} else {
													System.out.println("loop all shuffle in list");
													Random random = new Random();
													int newId = listmed_is_playing.get(index_med1);
													if (listmed_is_playing.size() > 1) {
														while (newId == listmed_is_playing.get(index_med1)) {
															int ran_index = random.nextInt(listmed_is_playing.size());
															newId = listmed_is_playing.get(ran_index);
														}
													} else {
														newId = random.nextInt(listmed_is_playing.size());
													}

													request.getSession().setAttribute("song_isplay", newId);
													try {
														ResultSet rs = stmt.executeQuery(
																"SELECT img_path,media_name,performer, file_path from media where id = '"
																		+ newId + "'");
														String resImg = null;
														String resName = null;
														String resPer = null;
														String resFile = null;
														while (rs.next()) {
															resImg = rs.getString(1);
															resName = rs.getString(2);
															resPer = rs.getString(3);
															resFile = rs.getString(4);
														}
														ArrayList<Integer> luv = new ArrayList<Integer>();
														if (request.getSession().getAttribute("Med_fav") != null) {
															luv = (ArrayList<Integer>) request.getSession()
																	.getAttribute("Med_fav");
														}
														boolean love = false;
														if (luv.contains(newId)) {
															love = true;
														}
														response.setContentType("application/json");
														response.setCharacterEncoding("UTF-8");
														response.getWriter()
																.write("{\"status\":\"true\",\"Img\":\"" + resImg
																		+ "\",\"Name\":\"" + resName + "\",\"Per\":\""
																		+ resPer + "\",\"path\":\"" + resFile
																		+ "\", \"love\":\"" + love + "\"}");
													} catch (Exception e) {
														e.printStackTrace();
													}
												}

											} else if (loop.equals("lon")) {
												System.out.println("loop one shuffle in list");
												response.setContentType("application/json");
												response.setCharacterEncoding("UTF-8");
												response.getWriter().write("{\"status\":\"false\"}");
											}

										} else {
											if (loop.equals("unlo")) {
												if (index_med1 == 0) {
													int new_id = listmed_is_playing.get(index_med1);
													request.getSession().setAttribute("song_isplay", new_id);
													try {
														ResultSet rs = stmt.executeQuery(
																"SELECT img_path,media_name,performer, file_path from media where id = '"
																		+ new_id + "'");
														String resImg = null;
														String resName = null;
														String resPer = null;
														String resFile = null;
														while (rs.next()) {
															resImg = rs.getString(1);
															resName = rs.getString(2);
															resPer = rs.getString(3);
															resFile = rs.getString(4);
														}
														ArrayList<Integer> luv = new ArrayList<Integer>();
														if (request.getSession().getAttribute("Med_fav") != null) {
															luv = (ArrayList<Integer>) request.getSession()
																	.getAttribute("Med_fav");
														}
														boolean love = false;
														if (luv.contains(new_id)) {
															love = true;
														}
														response.setContentType("application/json");
														response.setCharacterEncoding("UTF-8");
														response.getWriter()
																.write("{\"status\":\"true\",\"Img\":\"" + resImg
																		+ "\",\"Name\":\"" + resName + "\",\"Per\":\""
																		+ resPer + "\",\"path\":\"" + resFile
																		+ "\", \"love\":\"" + love + "\"}");
													} catch (Exception e) {
														e.printStackTrace();
													}
												} else {
													int newId1 = listmed_is_playing.get(index_med1 - 1);
													System.out.println("unloop not shuffle in pll");
													System.out.println("New id: " + newId1);
													request.getSession().setAttribute("song_isplay", newId1);
													try {
														ResultSet rs = stmt.executeQuery(
																"SELECT img_path,media_name,performer, file_path from media where id = '"
																		+ newId1 + "';");
														String resImg = null;
														String resName = null;
														String resPer = null;
														String resFile = null;
														while (rs.next()) {
															resImg = rs.getString(1);
															resName = rs.getString(2);
															resPer = rs.getString(3);
															resFile = rs.getString(4);
														}
														ArrayList<Integer> luv = new ArrayList<Integer>();
														if (request.getSession().getAttribute("Med_fav") != null) {
															luv = (ArrayList<Integer>) request.getSession()
																	.getAttribute("Med_fav");
														}
														boolean love = false;
														if (luv.contains(newId1)) {
															love = true;
														}
														response.setContentType("application/json");
														response.setCharacterEncoding("UTF-8");
														response.getWriter()
																.write("{\"status\":\"true\",\"Img\":\"" + resImg
																		+ "\",\"Name\":\"" + resName + "\",\"Per\":\""
																		+ resPer + "\",\"path\":\"" + resFile
																		+ "\", \"love\":\"" + love + "\"}");
													} catch (Exception e) {
														e.printStackTrace();
													}
												}
											} else if (loop.equals("lall")) {
												if (index_med1 == 0) {
													int new_id = listmed_is_playing.get(listmed_is_playing.size() - 1);
													request.getSession().setAttribute("song_isplay", new_id);
													try {
														ResultSet rs = stmt.executeQuery(
																"SELECT img_path,media_name,performer, file_path from media where id = '"
																		+ new_id + "'");
														String resImg = null;
														String resName = null;
														String resPer = null;
														String resFile = null;
														while (rs.next()) {
															resImg = rs.getString(1);
															resName = rs.getString(2);
															resPer = rs.getString(3);
															resFile = rs.getString(4);
														}
														ArrayList<Integer> luv = new ArrayList<Integer>();
														if (request.getSession().getAttribute("Med_fav") != null) {
															luv = (ArrayList<Integer>) request.getSession()
																	.getAttribute("Med_fav");
														}
														boolean love = false;
														if (luv.contains(new_id)) {
															love = true;
														}
														response.setContentType("application/json");
														response.setCharacterEncoding("UTF-8");
														response.getWriter()
																.write("{\"status\":\"true\",\"Img\":\"" + resImg
																		+ "\",\"Name\":\"" + resName + "\",\"Per\":\""
																		+ resPer + "\",\"path\":\"" + resFile
																		+ "\", \"love\":\"" + love + "\"}");
													} catch (Exception e) {
														e.printStackTrace();
													}

												} else {
													int newId = listmed_is_playing.get(index_med1 - 1);
													request.getSession().setAttribute("song_isplay", newId);
													try {
														ResultSet rs = stmt.executeQuery(
																"SELECT img_path,media_name,performer, file_path from media where id = '"
																		+ newId + "'");
														String resImg = null;
														String resName = null;
														String resPer = null;
														String resFile = null;
														while (rs.next()) {
															resImg = rs.getString(1);
															resName = rs.getString(2);
															resPer = rs.getString(3);
															resFile = rs.getString(4);
														}
														ArrayList<Integer> luv = new ArrayList<Integer>();
														if (request.getSession().getAttribute("Med_fav") != null) {
															luv = (ArrayList<Integer>) request.getSession()
																	.getAttribute("Med_fav");
														}
														boolean love = false;
														if (luv.contains(newId)) {
															love = true;
														}
														response.setContentType("application/json");
														response.setCharacterEncoding("UTF-8");
														response.getWriter()
																.write("{\"status\":\"true\",\"Img\":\"" + resImg
																		+ "\",\"Name\":\"" + resName + "\",\"Per\":\""
																		+ resPer + "\",\"path\":\"" + resFile
																		+ "\", \"love\":\"" + love + "\"}");
													} catch (Exception e) {
														e.printStackTrace();
													}
												}
											} else if (loop.equals("lon")) {
												response.setContentType("application/json");
												response.setCharacterEncoding("UTF-8");
												response.getWriter().write("{\"status\":\"false\"}");
											}
										}

										break;

									case "songout":
										int oldID = med_play_id;
										int index_isplay = 0;
										for (int i = 0; i < listmed_is_playing.size(); i++) {
											if (listmed_is_playing.get(i) == oldID) {
												index_isplay = i;
												break;
											}
										}
										type_cate songIspl_type = new type_cate();
										if (request.getSession().getAttribute("CATE_TYPE") != null) {
											songIspl_type = (type_cate) request.getSession().getAttribute("CATE_TYPE");
										}
										String type = songIspl_type.type;
										System.out.println(type);
										String cate = songIspl_type.cate;
										System.out.println(cate);
										try {
											ResultSet rs = stmt.executeQuery("SELECT * FROM media WHERE types = '"
													+ type + "' AND media_song_categories = '" + cate + "' ");
											ArrayList<Media> id_list = new ArrayList<Media>();
											while (rs.next()) {
												Media med = new Media();
												med.setMedia_id(rs.getInt(1));
												med.setMedia_img_path(rs.getString(2));
												med.setMedia_name(rs.getString(3));
												med.setMedia_performer(rs.getString(4));
												med.setMedia_file_path(rs.getString(5));
												id_list.add(med);
											}
											int index = 0;
											int newID = 0;
											if (loop.equals("lon")) {
												response.setContentType("application/json");
												response.setCharacterEncoding("UTF-8");
												response.getWriter().write("{\"status\":\"false\"}");
											} else {
												if (index_isplay == 0) {
													newID = oldID;
													request.getSession().setAttribute("song_isplay", newID);
													ArrayList<Integer> luv = new ArrayList<Integer>();
													if (request.getSession().getAttribute("Med_fav") != null) {
														luv = (ArrayList<Integer>) request.getSession()
																.getAttribute("Med_fav");
													}
													for (int i = 0; i < id_list.size(); i++) {
														if (id_list.get(i).getID() == newID) {
															index = i;
															break;
														}
													}
													boolean love = false;
													if (luv.contains(newID)) {
														love = true;
													}
													response.setContentType("application/json");
													response.setCharacterEncoding("UTF-8");
													response.getWriter().write("{\"status\":\"false\",\"Img\":\""
															+ id_list.get(index).getPL_path_img() + "\",\"Name\":\""
															+ id_list.get(index).getMedia_name() + "\",\"Per\":\""
															+ id_list.get(index).getMedia_perform() + "\",\"path\":\""
															+ id_list.get(index).getMedia_file_path()
															+ "\", \"love\":\"" + love + "\"}");
												} else {
													newID = listmed_is_playing.get(index_isplay - 1);
													listmed_is_playing.remove(index_isplay);
													request.getSession().setAttribute("song_isplay", newID);
													request.getSession().setAttribute("id_med_his", listmed_is_playing);
													for (int i = 0; i < id_list.size(); i++) {
														if (id_list.get(i).getID() == newID) {
															index = i;
															break;
														}
													}
													request.getSession().setAttribute("song_isplay", newID);
													ArrayList<Integer> luv = new ArrayList<Integer>();
													if (request.getSession().getAttribute("Med_fav") != null) {
														luv = (ArrayList<Integer>) request.getSession()
																.getAttribute("Med_fav");
													}
													boolean love = false;
													if (luv.contains(newID)) {
														love = true;
													}
													response.setContentType("application/json");
													response.setCharacterEncoding("UTF-8");
													response.getWriter().write("{\"status\":\"true\",\"Img\":\""
															+ id_list.get(index).getPL_path_img() + "\",\"Name\":\""
															+ id_list.get(index).getMedia_name() + "\",\"Per\":\""
															+ id_list.get(index).getMedia_perform() + "\",\"path\":\""
															+ id_list.get(index).getMedia_file_path()
															+ "\", \"love\":\"" + love + "\"}");
												}
											}

										} catch (Exception e) {
											e.printStackTrace();
										}
										break;

									case "podout":
										int oldID1 = med_play_id;
										int index_isplay1 = 0;
										for (int i = 0; i < listmed_is_playing.size(); i++) {
											if (listmed_is_playing.get(i) == oldID1) {
												index_isplay1 = i;
												break;
											}
										}
										type_cate songIspl_type1 = new type_cate();
										if (request.getSession().getAttribute("CATE_TYPE") != null) {
											songIspl_type1 = (type_cate) request.getSession().getAttribute("CATE_TYPE");
										}
										String type1 = songIspl_type1.type;
										System.out.println(type1);
										String cate1 = songIspl_type1.cate;
										System.out.println(cate1);
										try {
											ResultSet rs = stmt.executeQuery("SELECT * FROM media WHERE types = '"
													+ type1 + "' AND media_song_categories = '" + cate1 + "' ");
											ArrayList<Media> id_list = new ArrayList<Media>();
											while (rs.next()) {
												Media med = new Media();
												med.setMedia_id(rs.getInt(1));
												med.setMedia_img_path(rs.getString(2));
												med.setMedia_name(rs.getString(3));
												med.setMedia_performer(rs.getString(4));
												med.setMedia_file_path(rs.getString(5));
												id_list.add(med);
											}
											int index = 0;
											int newID = 0;
											if (loop.equals("lon")) {
												response.setContentType("application/json");
												response.setCharacterEncoding("UTF-8");
												response.getWriter().write("{\"status\":\"false\"}");
											} else {
												if (index_isplay1 == 0) {
													newID = oldID1;
													request.getSession().setAttribute("song_isplay", newID);
													ArrayList<Integer> luv = new ArrayList<Integer>();
													if (request.getSession().getAttribute("Med_fav") != null) {
														luv = (ArrayList<Integer>) request.getSession()
																.getAttribute("Med_fav");
													}
													for (int i = 0; i < id_list.size(); i++) {
														if (id_list.get(i).getID() == newID) {
															index = i;
															break;
														}
													}
													boolean love = false;
													if (luv.contains(newID)) {
														love = true;
													}
													response.setContentType("application/json");
													response.setCharacterEncoding("UTF-8");
													response.getWriter().write("{\"status\":\"false\",\"Img\":\""
															+ id_list.get(index).getPL_path_img() + "\",\"Name\":\""
															+ id_list.get(index).getMedia_name() + "\",\"Per\":\""
															+ id_list.get(index).getMedia_perform() + "\",\"path\":\""
															+ id_list.get(index).getMedia_file_path()
															+ "\", \"love\":\"" + love + "\"}");
												} else {
													newID = listmed_is_playing.get(index_isplay1 - 1);
													listmed_is_playing.remove(index_isplay1);
													request.getSession().setAttribute("song_isplay", newID);
													request.getSession().setAttribute("id_med_his", listmed_is_playing);
													for (int i = 0; i < id_list.size(); i++) {
														if (id_list.get(i).getID() == newID) {
															index = i;
															break;
														}
													}
													request.getSession().setAttribute("song_isplay", newID);
													ArrayList<Integer> luv = new ArrayList<Integer>();
													if (request.getSession().getAttribute("Med_fav") != null) {
														luv = (ArrayList<Integer>) request.getSession()
																.getAttribute("Med_fav");
													}
													boolean love = false;
													if (luv.contains(newID)) {
														love = true;
													}
													response.setContentType("application/json");
													response.setCharacterEncoding("UTF-8");
													response.getWriter().write("{\"status\":\"true\",\"Img\":\""
															+ id_list.get(index).getPL_path_img() + "\",\"Name\":\""
															+ id_list.get(index).getMedia_name() + "\",\"Per\":\""
															+ id_list.get(index).getMedia_perform() + "\",\"path\":\""
															+ id_list.get(index).getMedia_file_path()
															+ "\", \"love\":\"" + love + "\"}");
												}
											}

										} catch (Exception e) {
											e.printStackTrace();
										}
										break;

									default:
										break;
								}
							} else {

							}
						} else {
							String site = "notfound.jsp";
							response.setStatus(response.SC_MOVED_TEMPORARILY);
							response.setHeader("Location", site);
						}
						break;

					case "BehaOnPlay":
						if (request.getSession().getAttribute("MAIL") != null
								&& request.getSession().getAttribute("PASS") != null) {
							if (request.getSession().getAttribute("song_isplay") == null) {
								response.setContentType("application/json");
								response.setCharacterEncoding("UTF-8");
								response.getWriter().write("{\"error\":\"true\"}");
							} else {
								try {
									int id_ispl = Integer
											.parseInt(request.getSession().getAttribute("song_isplay").toString());
									String getStatus = request.getParameter("status");
									ArrayList<Integer> likepll = new ArrayList<Integer>();
									switch (getStatus) {
										case "LIKE_ON":
											if (request.getSession().getAttribute("Med_fav") != null) {
												likepll.addAll((ArrayList<Integer>) request.getSession()
														.getAttribute("Med_fav"));
												if (likepll.contains(id_ispl) != true) {
													likepll.add(id_ispl);
													request.getSession().setAttribute("Med_fav", likepll);
													stmt.executeUpdate("Insert into mediaFav values ('" + id_ispl
															+ "','"
															+ request.getSession().getAttribute("IDUS").toString()
															+ "')");
												}
											} else {
												likepll.add(id_ispl);
												stmt.executeUpdate("Insert into mediaFav values ('" + id_ispl + "','"
														+ request.getSession().getAttribute("IDUS").toString() + "')");
												request.getSession().setAttribute("Med_fav", likepll);
											}
											response.setContentType("application/json");
											response.setCharacterEncoding("UTF-8");
											response.getWriter().write("{\"error\":\"false\",\"DisApp\":\"LSID_"
													+ id_ispl + "\",\"App\":\"DLSID_" + id_ispl + "\"}");
											break;

										case "DISLIKE_ON":
											if (request.getSession().getAttribute("Med_fav") != null) {
												likepll.addAll((ArrayList<Integer>) request.getSession()
														.getAttribute("Med_fav"));
												if (likepll.contains(id_ispl) == true) {
													int index = 0;
													boolean check = false;
													for (int i = 0; i < likepll.size(); i++) {
														if (likepll.get(i) == id_ispl) {
															index = i;
															check = true;
															break;
														}
													}
													if (check == true) {
														likepll.remove(index);
														stmt.executeUpdate("DELETE from mediaFav where media_id = '"
																+ id_ispl + "' and user_id = '"
																+ request.getSession().getAttribute("IDUS").toString()
																+ "'");
													}
													request.getSession().setAttribute("Med_fav", likepll);
													response.setContentType("application/json");
													response.setCharacterEncoding("UTF-8");
													response.getWriter().write("{\"error\":\"false\",\"App\":\"LSID_"
															+ id_ispl + "\",\"DisApp\":\"DLSID_" + id_ispl + "\"}");
												}
											}
											break;
									}
								} catch (Exception e) {

								}
							}

						} else {
							response.setContentType("application/json");
							response.setCharacterEncoding("UTF-8");
							response.getWriter().write("{\"error\":\"true\"}");
						}
						break;

					case "LOOP":
						if (request.getSession().getAttribute("MAIL") != null
								&& request.getSession().getAttribute("PASS") != null) {
							String getLoop = request.getParameter("loop");
							switch (getLoop) {

								case "lon":
									request.getSession().setAttribute("loop", "lon");
									response.setContentType("application/json");
									response.setCharacterEncoding("UTF-8");
									response.getWriter().write("{\"Loop\":\"lon\", \"error\":\"false\"}");
									break;

								case "lall":
									request.getSession().setAttribute("loop", "lall");
									response.setContentType("application/json");
									response.setCharacterEncoding("UTF-8");
									response.getWriter().write("{\"Loop\":\"lall\", \"error\":\"false\"}");
									break;

								case "unlo":
									request.getSession().setAttribute("loop", "unlo");
									response.setContentType("application/json");
									response.setCharacterEncoding("UTF-8");
									response.getWriter().write("{\"Loop\":\"unlo\", \"error\":\"false\"}");
									break;
							}
						} else {
							response.setContentType("application/json");
							response.setCharacterEncoding("UTF-8");
							response.getWriter().write("{\"Loop\":\"false\", \"error\":\"true\"}");
						}
						break;

					case "Create_playlist":
						if (request.getSession().getAttribute("MAIL") != null
								&& request.getSession().getAttribute("PASS") != null) {
							String name_new_playlist = request.getParameter("PLL_name");
							if (name_new_playlist.equals("")) {
								response.setContentType("application/json");
								response.setCharacterEncoding("UTF-8");
								response.getWriter().write("{\"error\":\"caution1\"}");
							} else if (name_new_playlist.length() < 7) {
								response.setContentType("application/json");
								response.setCharacterEncoding("UTF-8");
								response.getWriter().write("{\"error\":\"caution2\"}");
							} else if (name_new_playlist.length() > 40) {
								response.setContentType("application/json");
								response.setCharacterEncoding("UTF-8");
								response.getWriter().write("{\"error\":\"caution3\"}");
							} else {
								try {
									ResultSet rs = stmt
											.executeQuery("SELECT Pll_cre_id FROM CreatePlaylist WHERE user_id = '"
													+ request.getSession().getAttribute("IDUS").toString() + "'");
									ArrayList<String> ID_PLL_CRE = new ArrayList<>();
									while (rs.next()) {
										ID_PLL_CRE.add(rs.getString(1));
									}
									String newID = "";
									String Pll_number = "";
									if (ID_PLL_CRE.size() > 0) {
										Pll_number = "#" + (ID_PLL_CRE.size() + 1);
										newID = RandNewId() + Pll_number + "_"
												+ request.getSession().getAttribute("NAME").toString() + "_"
												+ request.getSession().getAttribute("IDUS").toString();
									} else {
										Pll_number = "#1";
										newID = RandNewId() + "#1" + "_"
												+ request.getSession().getAttribute("NAME").toString() + "_"
												+ request.getSession().getAttribute("IDUS").toString();
									}
									stmt.execute(
											"INSERT INTO CreatePlaylist (Pll_cre_id, PLL_name , PLL_number, user_id) VALUE ('"
													+ newID + "', '" + name_new_playlist + "', '" + Pll_number + "','"
													+ request.getSession().getAttribute("IDUS").toString() + "')");
									response.setContentType("application/json");
									response.setCharacterEncoding("UTF-8");
									response.getWriter()
											.write("{\"error\":\"none\", \"stt\":\"success\", \"ID_CRE\":\"" + newID
													+ "\", \"Pll_name\":\"" + name_new_playlist + "\", \"Pll_num\":\""
													+ Pll_number + "\",\"img_path\":\"images/music.png\"}");
								} catch (Exception e) {
									response.setContentType("application/json");
									response.setCharacterEncoding("UTF-8");
									response.getWriter().write("{\"error\":\"none\", \"stt\":\"unsuccess\"}");
								}
							}
						} else {
							response.setContentType("application/json");
							response.setCharacterEncoding("UTF-8");
							response.getWriter().write("{\"error\":\"true\"}");
						}
						break;

					case "DEL_CRE_PLL":
						if (request.getSession().getAttribute("MAIL") != null
								&& request.getSession().getAttribute("PASS") != null) {
							String ID_TO_DEL = request.getParameter("ID_TO_DEL");
							if (ID_TO_DEL.equals("")) {
								response.setContentType("application/json");
								response.setCharacterEncoding("UTF-8");
								response.getWriter().write("{\"error\":\"true\"}");
							} else {
								stmt.execute("DELETE FROM CreatePlaylist WHERE Pll_cre_id = '" + ID_TO_DEL + "'");
								stmt.execute("DELETE FROM media_in_playlist WHERE playlist_id = '" + ID_TO_DEL + "'");
								response.setContentType("application/json");
								response.setCharacterEncoding("UTF-8");
								response.getWriter().write("{\"error\":\"false\"}");
							}

						} else {
							response.setContentType("application/json");
							response.setCharacterEncoding("UTF-8");
							response.getWriter().write("{\"error\":\"true\"}");
						}
						break;

					case "SEARCH_TO_CRE":
						if (request.getSession().getAttribute("MAIL") != null
								&& request.getSession().getAttribute("PASS") != null) {
							String text_search = request.getParameter("SEARCH_REQ");
							String ID_ADD = request.getParameter("ID_TO_ADD");
							if (ID_ADD.equals("")) {
								System.out.println("hello");
								response.setContentType("application/json");
								response.setCharacterEncoding("UTF-8");
								response.getWriter().write("{\"error\":\"true\"}");
							} else if (text_search.equals("")) {
								response.setContentType("application/json");
								response.setCharacterEncoding("UTF-8");
								response.getWriter().write("{\"error\":\"caution\"}");
							} else {
								try {
									ArrayList<Media> Med_list = new ArrayList<Media>();
									ArrayList<Integer> Med_in_CRE = new ArrayList<Integer>();
									ResultSet rs = stmt.executeQuery(
											"SELECT * FROM media WHERE media_name LIKE '%" + text_search + "%'");
									while (rs.next()) {
										Media med = new Media();
										med.setMedia_id(rs.getInt(1));
										med.setMedia_img_path(rs.getString(2));
										med.setMedia_name(rs.getString(3));
										med.setMedia_performer(rs.getString(4));
										Med_list.add(med);
									}
									rs = stmt
											.executeQuery("SELECT media_id FROM media_in_playlist WHERE playlist_id = '"
													+ ID_ADD + "'");
									while (rs.next()) {
										Med_in_CRE.add(rs.getInt(1));
									}
									String data = "{\"error\":\"false\",\"song\":[";
									String querry = "";
									for (int i = 0; i < Med_list.size(); i++) {
										if (Med_in_CRE.contains(Med_list.get(i).getID()) != true) {
											querry = querry + "{\"ID\":\"" + Med_list.get(i).getID() + "\",\"img\":\""
													+ Med_list.get(i).getPL_path_img() + "\",\"name\": \""
													+ Med_list.get(i).getMedia_name() + "\", \"per\": \""
													+ Med_list.get(i).getMedia_perform() + "\"} ";
										}
									}
									ArrayList<Integer> index = new ArrayList<>();
									StringBuffer Text = new StringBuffer(querry);
									for (int i = 0; i < Text.length(); i++) {
										if (Text.charAt(i) == '}') {
											index.add(i);
										}
									}
									querry = Text.toString();
									String last_res = "";
									last_res = insertString(querry, ",", index);
									data = data + last_res + "]}";

									response.setContentType("application/json");
									response.setCharacterEncoding("UTF-8");
									response.getWriter().write(data);
								} catch (Exception e) {
									e.printStackTrace();
								}

							}

						} else {
							response.setContentType("application/json");
							response.setCharacterEncoding("UTF-8");
							response.getWriter().write("{\"error\":\"true\"}");
						}
						break;

					case "CUSTOM_CRE":
						if (request.getSession().getAttribute("MAIL") != null
								&& request.getSession().getAttribute("PASS") != null) {
							String IDSONG = request.getParameter("SongID");
							String IDCRE = request.getParameter("CreID");
							String OPTION = request.getParameter("Option");
							if (OPTION.equals("ADD")) {
								stmt.execute("INSERT INTO media_in_playlist (media_id, playlist_id) values ('" + IDSONG
										+ "','" + IDCRE + "')");
								ResultSet rs = stmt.executeQuery("SELECT * FROM media WHERE id = '" + IDSONG + "' ");
								String data = "";
								while (rs.next()) {
									data = " \"med_id\" : \"" + IDSONG + "\", \"med_img\" : \"" + rs.getString(2)
											+ "\", \"med_name\" : \"" + rs.getString(3) + "\" , \"performer\" : \""
											+ rs.getString(4) + "\" ";
								}
								response.setContentType("application/json");
								response.setCharacterEncoding("UTF-8");
								response.getWriter().write("{\"error\":\"false\",\"status\":\"ADD\", " + data + " }");
							} else if (OPTION.equals("DEL")) {
								stmt.execute("DELETE FROM media_in_playlist WHERE playlist_id = '" + IDCRE
										+ "' AND media_id = '" + IDSONG + "' ");
								response.setContentType("application/json");
								response.setCharacterEncoding("UTF-8");
								response.getWriter().write("{\"error\":\"false\",\"status\":\"DEL\"}");
							}
						} else {
							response.setContentType("application/json");
							response.setCharacterEncoding("UTF-8");
							response.getWriter().write("{\"error\":\"true\"}");
						}
						break;

					default:
						String site = "notfound.jsp";
						response.setStatus(response.SC_MOVED_TEMPORARILY);
						response.setHeader("Location", site);
						break;
				}
			} else {
				String site = "notfound.jsp";
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location", site);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	class SufOrUnsuf {
		String id;
		boolean stat;
	}

	class type_cate {
		String type;
		int year;
		String cate;

	}

	public static String insertString(
			String originalString,
			String stringToBeInserted,
			ArrayList<Integer> index) {

		// Create a new string
		String newString = "";
		int count = 0;
		for (int i = 0; i < index.size(); i++) {
			if (i == index.size() - 1) {
				newString = newString + originalString.substring(count, index.get(i) + 1)
						+ originalString.substring(index.get(i) + 1);
			} else {
				newString = newString + originalString.substring(count, index.get(i) + 1) + stringToBeInserted;
			}
			count = index.get(i) + 1;

		}
		return newString;
	}

	public String RandNewId() {
		Random randChar = new Random();
		String NewID = "";
		for (int i = 0; i < Char_arr.length; i++) {
			NewID = NewID + Char_arr[randChar.nextInt(Char_arr.length)];
		}
		return NewID;
	}

}
