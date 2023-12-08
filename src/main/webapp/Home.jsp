<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, Script.Playlist_Create,Script.Playlist, Script.Performer" %>
<% if(request.getSession().getAttribute("MAIL") == null && request.getSession().getAttribute("PASS") == null){
	request.getSession().setAttribute("tove",null);
	request.getSession().setAttribute("ve",null);
	request.getSession().setAttribute("mail",null);
	request.getSession().setAttribute("pass",null);
	request.getSession().setAttribute("usname",null); 
	request.getSession().setAttribute("upload",null);
	request.getSession().setAttribute("CP",null);
	response.sendRedirect("Login.jsp");
}request.getSession().setAttribute("tove",null);
request.getSession().setAttribute("ve",null);
request.getSession().setAttribute("mail",null);
request.getSession().setAttribute("pass",null);
request.getSession().setAttribute("usname",null); 
request.getSession().setAttribute("upload",null);
request.getSession().setAttribute("CP",null);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="images/icon.png">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"
  integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
  crossorigin="anonymous"></script>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link rel="stylesheet" href="CSS/index.css">
    <link rel="stylesheet" href="CSS/uploadimage.css">
    <link href="https://fonts.googleapis.com/css2?family=Ubuntu:wght@500&display=swap" rel="stylesheet">
    <title>Home | Muslife</title>
</head>
<body>
<form style="display:none;"id="get" action="Get_playlist" method="post">
<input type="hidden" name="getPL" value="GETLO">
</form>
    <div class="wrap_ct1">
        <div class="Wrap_left">
            <div class="wrap_option">
                <div class="option">
                    <ul class="op_list">
                        <li class="list" style="cursor:pointer;" onclick="appearHome()"><img width="7%" style="margin-right: 20px;margin-bottom:-1px;" src="images/home.png"><a class="link">Home</a></li>
                        <li class="list" style="cursor:pointer;" onclick="openSearch()"><img width="7%" style="margin-right: 20px;margin-bottom:-1px;" src="images/search.png"><a class="link">Search</a></li>
                    </ul>
                </div>
            </div>
            <div class="wrap_your_lib">
                <div class="your_lib">
                    <div class="wrap_h">
                        <div class="wrap_head">
                            <img class="img_book" src="images/bookmark.png">
                            <h3 class="head">Library</h3>
                        </div>
                        <div class="wrap_pl_btn">
                            <button onclick="create_playlist()" class="plus_btn">
                                <img class="img_plus" src="images/plus.png">
                            </button>
                        </div>
                    </div>
                    <br>
                <div id="lis_lib_left" class="lis_lib">
                    <%if(request.getSession().getAttribute("ALL_PLL_LEFT")== null){ %>
                    	<div class="pl_left" onclick="open_playlist('Love_song_pll','images/song_playlist.png','Loved Songs','All songs you love in here')">
                            <div class="wrap_img_pl_left">
                                <img src="images/song_playlist.png" class="img_pl_left">
                            </div>
                            <div class="wrap_info_pl_list_left">
                                <p class="pl_name_left">Loved Songs</p>
                                <p class="pl_scr_left">All songs you love in here</p>
                            </div>
                       	</div>
                       	<div class="pl_left" onclick="open_playlist('Love_pod_pll','images/podcast_playlist.png','Loved Podcasts','All podcasts you love in here')"">
                            <div class="wrap_img_pl_left">
                                <img src="images/podcast_playlist.png" class="img_pl_left">
                            </div>
                            <div class="wrap_info_pl_list_left">
                                <p class="pl_name_left">Loved Podcasts</p>
                                <p class="pl_scr_left">All podcasts you love in here</p>
                            </div>
                       	</div>
                       	<%ArrayList<Playlist_Create> pll_cre = new ArrayList<Playlist_Create>(); 
                       	if(request.getSession().getAttribute("PLL_CRE") != null){
                       		pll_cre.addAll((ArrayList<Playlist_Create>)request.getSession().getAttribute("PLL_CRE"));
                       		for(int i = 0; i < pll_cre.size();i++){%>
                       			<div id="<%=pll_cre.get(i).getPl_id() %>" class="pl_left" onclick="open_cre_playlist(this.id, '<%=pll_cre.get(i).getPl_img_path() %>', '<%=pll_cre.get(i).getPl_name() %>', '<%=pll_cre.get(i).getPl_script() %>')"><div class="wrap_img_pl_left"><img src="<%=pll_cre.get(i).getPl_img_path() %>" class="img_pl_left"></div><div class="wrap_info_pl_list_left"><p class="pl_name_left"><%=pll_cre.get(i).getPl_name() %></p><p class="pl_scr_left"><%=pll_cre.get(i).getPl_script() %></p></div></div>
                      		<%}
                      		}%>
                      		
                    <%}else{%>
                    	<div class="pl_left" onclick="open_playlist('Love_song_pll','images/song_playlist.png','Loved Songs','All songs you love in here')">
	                          <div class="wrap_img_pl_left">
	                              <img src="images/song_playlist.png" class="img_pl_left">
	                          </div>
	                          <div class="wrap_info_pl_list_left">
	                              <p class="pl_name_left">Loved Songs</p>
	                              <p class="pl_scr_left">All songs you love in here</p>
	                          </div>
                      	</div>
                       	<div class="pl_left" onclick="open_playlist('Love_pod_pll','images/podcast_playlist.png','Loved Podcasts','All podcasts you love in here')">
                            <div class="wrap_img_pl_left">
                                <img src="images/podcast_playlist.png" class="img_pl_left">
                            </div>
                            <div class="wrap_info_pl_list_left">
                                <p class="pl_name_left">Loved Podcasts</p>
                                <p class="pl_scr_left">All podcasts you love in here</p>
                            </div>
                       	</div>
                       	<%ArrayList<Playlist_Create> pll_cre = new ArrayList<Playlist_Create>(); 
                       	if(request.getSession().getAttribute("PLL_CRE") != null){
                       		pll_cre.addAll((ArrayList<Playlist_Create>)request.getSession().getAttribute("PLL_CRE"));
                       		for(int i = 0; i < pll_cre.size();i++){%>
                       			<div id="<%=pll_cre.get(i).getPl_id() %>" class="pl_left" onclick="open_cre_playlist(this.id, '<%=pll_cre.get(i).getPl_img_path() %>', '<%=pll_cre.get(i).getPl_name() %>', '<%=pll_cre.get(i).getPl_script() %>')"><div class="wrap_img_pl_left"><img src="<%=pll_cre.get(i).getPl_img_path() %>" class="img_pl_left"></div><div class="wrap_info_pl_list_left"><p class="pl_name_left"><%=pll_cre.get(i).getPl_name() %></p><p class="pl_scr_left"><%=pll_cre.get(i).getPl_script() %></p></div></div>
                      		<%}
                      		}%>
                    	<%ArrayList<Playlist> pll = new ArrayList<Playlist>(); 
                    	pll.addAll((ArrayList<Playlist>)request.getSession().getAttribute("ALL_PLL_LEFT"));
                    	for(int i = 0; i < pll.size();i++){
                    		if(pll.get(i).getPl_type().equals("song")){%>
                            <div id="<%=pll.get(i).getPl_id()%>" class="pl_left" onclick="open_playlist(this.id,'<%=pll.get(i).getPl_img_path()%>','<%=pll.get(i).getPl_name()  %>', '<%=pll.get(i).getPl_script() %>')">
	                            <div class="wrap_img_pl_left">
	                                <img src="<%=pll.get(i).getPl_img_path()%>" class="img_pl_left">
	                            </div>
	                            <div class="wrap_info_pl_list_left">
	                                <p class="pl_name_left"><%=pll.get(i).getPl_name() %></p>
	                                <p class="pl_scr_left"><%=pll.get(i).getPl_script() %></p>
	                            </div>
                        	</div>
                   		<%}
                   		} %>
                   		<%
                    	for(int i = 0; i < pll.size();i++){
                    		if(pll.get(i).getPl_type().equals("podcast")){%>
                            <div id="<%=pll.get(i).getPl_id()%>" class="pl_left" onclick="open_playlist(this.id,'<%=pll.get(i).getPl_img_path()%>','<%=pll.get(i).getPl_name()  %>', '<%=pll.get(i).getPl_script() %>')">
	                            <div class="wrap_img_pl_left">
	                                <img src="<%=pll.get(i).getPl_img_path()%>" class="img_pl_left">
	                            </div>
	                            <div class="wrap_info_pl_list_left">
	                                <p class="pl_name_left"><%=pll.get(i).getPl_name() %></p>
	                                <p class="pl_scr_left"><%=pll.get(i).getPl_script() %></p>
	                            </div>
                        	</div>
                   		<%}
                   		} %>
                    <%}%>
                </div>
                <br>
                </div>
            </div>
            <div class="wrap_ct2">
                <div class="ct2">
                    <div class="media_play">
                    <audio id="song" src="">
                    </audio>
                        <div class="wrap_info_song_playing">
                            <img src="images/icon.png" id="img_song_play" class="img_song_play">
                            <div class="info_play">
                                <marquee scrollamount="2"  id="name_song_play" class="name_song_play">MusLife</marquee >
                                <div scrollamount="2" id="author_play" class="author_play">MusLife</div >
                            </div>
                        </div>
                        <div class="wrap_media_playing">
                            <div class="media">
                                <button onclick="previoussong()" class="pr_son_btn" id="pr_son_btn"><img class="pr_son" src="images/previous_but.png" alt=""></button>
                                <button onclick="playsong()" class="play_son_btn" id="play_son_btn"><img class="play_son" src="images/play-button-arrowhead.png" alt=""></button>
                                <button onclick="pausesong()" id="pause_son_btn" class="pause_son_btn" style="display:none;"><img class="pause_son" src="images/pause.png" alt=""></button>
                                <button onclick="nextsong()" class="next_son_btn" id="next_son_btn"><img class="next_son" src="images/next-button.png" alt=""></button>
                            </div>
                            <div class="progres">
                                <p id="start" class="start"></p>
                                <input type="range" id="pro_song" class="pro_song">
                                <p id="end" class="end"></p>
                            </div>
                        </div>
                        <div class="behavior">
                            <button onclick="loop('lon')" id="no_loop" class="loop_btn">
                            <img class="loop" src="images/mure.png">
                            </button>
                            <button onclick="loop('lall')" style="display:none" id="loop_one" class="loop_btn">
                            <img style="width:23px;height:23px;" src="images/music1.png">
                            </button>
                            <button onclick="loop('unlo')" style="display:none" id="loop_all" class="loop_btn">
                            <img class="loop" src="images/music_re.png">
                            </button>
                            <button onclick = "LikeonPlay('LIKE_ON', this.id, 'heart_btn')" style="display:none;" id="no_heart_btn" class="heart_btn">
                            <img class="heart" src="images/like.png">
                            </button>
                            <button onclick ="LikeonPlay('DISLIKE_ON',this.id,'no_heart_btn')" style="display:none;" id="heart_btn" class="heart_btn">
                            <img class="heart" src="images/like_gr.png">
                            </button>
                        </div>
                    </div>
                </div>
            </div>  
        </div>
            <div class="Wrap_right">   
                <div style="border-top-right-radius: 10px;border-top-left-radius: 10px;background-color: rgb(28, 25, 25);padding:2%; position:fixed;width:100%;box-shadow: 5px 10px 20px black">
                    <div class="wrap_menu">
                        <div class="menu">
                            <div class="logo_web">
                            	<img class="logo" onclick="appearHome()" src="images/logo.png" alt="logo web">
                            </div>
                            <div class="menu_but">
                                <div class="wrap_down">
                                	<form style="display:none;" id="loo" action="Logout" method="post"><input type="hidden" value="logout" name="loo"></form>
                                		<a href="#Account"><img id="img_acc_small" title="Your account" style="cursor:pointer" onclick="appearAcc()" class="img_acc_small" src="<%=request.getSession().getAttribute("US_IMG")%>" alt="account"></a>
                                </div>
                            </div>
                        </div>
                    </div> 
                </div>
                
                <div id="wrap_out_ct" class="wrap_out_ct">
                    <div class="wrap_ct">
                        <div class="ct">
                        	<div class="wrap_all_search" id="wrap_all_search" style="display:none">
                                    <div class="wrap_search">
                                            <input type="text" class="search" name="search" id="search">
                                        <div>
                                            <button onclick="Search()" class="search_btn"><img width="20px" src="images/search.png" alt="search"></button>
                                        </div>    
                                        <div style="padding : '30px;'" class="wrap_result" id="wrap_result"></div>
                                    </div>
                                    <div class="type_choo_wrap">
                                        <select class="op_type" id="op_type">
                                            <option class="se_type" value="All">All</option>
                                            <option class="se_type" value="Song">Song</option>
                                            <option class="se_type" value="Artist">Artist</option>
                                            <option class="se_type" value="Podcast">Podcast</option>
                                            <option class="se_type" value="Playlist">Playlist</option>
                                        </select>
                                    </div>
                                    <div id="result_search" class="result_search"></div>
                                </div>
                                
	                        <div id="see_more_pll_wrap" style="display:none;" class="wrap_header_pl">
	                        
	                        </div>
                        <div class="wrap_change_image" id="wrap_change_image" style="display:none">
                        <div class="wrap_back_home">
                           <a href="#Account"><button  style="cursor:pointer;" onclick="appearAcc()" class="home_btn" title="Home"><img class="img_back_home" src="images/left-arrow.png" alt="go home button"></button></a>
                       </div>
                        <div class="wrap_upload1">
							<div class="wrap_form_up1" style="background-color:rgb(71, 70, 70);">
								<div class="wrap_head_up">
									<h2  style="color:black">Upload your account image</h2>
								</div>
								<div class="wrap_img_pre">
									<img id="img_preview" class="img_preview">
								</div>
								<div style="margin:5px;text-align:center;">
									<p id="mes" style="color:red;font-size:12px;"></p>
								</div>
								<form id="form_change" class="form" >
									<div class="file_wrap">
										<input class="file1" type="file" name="images" id="img_upload" onchange="showPreview(event);">
									</div>
										<br>
									<div class="sub_wrap">
										<input onclick="access()" class="skip_btn1" id="skip_btn1" type="submit" name="submit1" value="Delete">
										<input onclick="access()" disabled id="sub_btn" class="sub_btn1" type="submit" name="submit" value="Change">
										<div id="circle_load" class="circle_load"></div>
									</div>
								</form>
							</div>
						</div>
                     </div>
                     <div class="wrap_cre_pll" id="wrap_cre_pll" style="display:none;">
                     	
                     </div>
               		 <div class="account" id="account" style="display:none;">
                           <div class="wrap_back_home">
                               <button style="cursor:pointer;" onclick="appearHome()" class="home_btn" title="Home"><img class="img_back_home" src="images/left-arrow.png" alt="go home button"></button>
                           </div>
                           <div class="wrap_acc">
                               <div class="acc_inf">
                                   <div class="wrap_inf_img">
                                       <img id="img_inf" class="img_inf" src="<%=request.getSession().getAttribute("US_IMG")%>">
                                   </div>
                                   <div class="wrap_inf_name">
                                       <p class="name_inf"><%=request.getSession().getAttribute("NAME")%></p>
                                   </div>
                               </div>
                               <div class="wrap_choose_op">
                                   <div class="choose_op">
                                       <div class="op_btn" onclick="appearChangeImage()">
                                           <p class="op">Change account image</p>
                                       </div>
                                       <div class="op_btn">
                                           <a href="Change_pass.jsp" style="text-decoration:none;color:white;"><p class="op">Change password</p></a>
                                       </div>
                                       <div class="op_btn">
                                           <p onclick="appearHome()" class="op">Listen to music!</p>
                                       </div>
                                   </div>
                               </div>
                               <div class="wrap_lo_btn">
                                   <button onclick="showNof()" class="lo_btn_acc">Log out</button>
                               </div>	
                           </div>
                       </div>
                       <div class="pl_inside" style="display:none;" id="pl_inside">
                       
                       </div>
                        <%if(request.getSession().getAttribute("Playlists")== null || request.getSession().getAttribute("Podcast")== null) {%>
                        <div class="wrap_loader">
                            <div class="custom-loader"></div>
                         </div>
                            <%}else{%>
                            	<div class="Home" id="Home">
								<%if(request.getSession().getAttribute("Pll_fav") != null){ 
								ArrayList<Playlist> Pll_fav = new ArrayList<Playlist>(); 
								Pll_fav = (ArrayList<Playlist>)request.getSession().getAttribute("Pll_fav");
								if(Pll_fav.size()>0){
								%>
                            	<div id="wrap_header_pll_fav" class="wrap_header_pl">
								    <div class="wrap_head_pll">
                            			<h2>MusLife Playlist</h2>
                            			<button id="see_more_pll" onclick="seeMore(this.id)" class="see_more">See more</button>
                            		</div>
                            		<div id="wrap_all_pll_fave" class="wrap_all_pl1">
                            		<% int count = 0;
                            		for(int i = 0; i < 4; i++){
                            			if(i <= Pll_fav.size()-1){		
                            		%> 
								        <div class="pl_list" id="<%=Pll_fav.get(i).getPl_id()%>" onclick="open_playlist(this.id,'<%=Pll_fav.get(i).getPl_img_path()%>','<%=Pll_fav.get(i).getPl_name()  %>', '<%=Pll_fav.get(i).getPl_script() %>')" >
								                <img class="img_pl" src="<%=Pll_fav.get(i).getPl_img_path()%>">
								            <div class="info_pl_l">
								                <h3 class="name_pl"><%=Pll_fav.get(i).getPl_name()%></h3>
								                <p class="descript_pl"><%=Pll_fav.get(i).getPl_script()%></p>
								            </div>
								            
								        </div>
									    <%
                            				}else{
                            					break;
                            				}
                            			}%>
                            		</div>
								</div>
									
                            	<%}else{%>
                            		<div style="display:none;" id="wrap_header_pll_fav" class="wrap_header_pl">
								    <div class="wrap_head_pll">
                            			<h2>MusLife Playlist</h2>
                            			<button id="see_more_pll" onclick="seeMore(this.id)" class="see_more">See more</button>
                            		</div>
                            		<div id="wrap_all_pll_fave" class="wrap_all_pl1">
                            		</div>
								</div>
                            	<%}
                            	}else{%>
                            		<div style="display:none;" id="wrap_header_pll_fav" class="wrap_header_pl">
								    <div class="wrap_head_pll">
                            			<h2>MusLife Playlist</h2>
                            			<button id="see_more_pll" onclick="seeMore(this.id)" class="see_more">See more</button>
                            		</div>
                            		<div id="wrap_all_pll_fave" class="wrap_all_pl1">
                            		</div>
								</div>
                            	<%}%>
                            	
                            	
                            	<%if(request.getSession().getAttribute("Pod_fav") != null){
                            	ArrayList<Playlist> Pod_fav = new ArrayList<Playlist>(); 
                            	Pod_fav = (ArrayList<Playlist>)request.getSession().getAttribute("Pod_fav");
                            		if(Pod_fav.size() > 0){
                            	%>
                            		<div id="wrap_header_pod_fav" class="wrap_header_pl">
								    <div class="wrap_head_pll">
                            			<h2>MusLife Podcast</h2>
                            			<button id="see_more_pod" onclick="seeMore(this.id)" class="see_more">See more</button>
                            		</div>
                            		<div id="wrap_all_pod_fave" class="wrap_all_pl1">
                            		<%
                            		for(int i= 0; i < 4; i++){
                            			if(i <= Pod_fav.size()-1){
                            		%> 
								        <div class="pl_list" id="<%=Pod_fav.get(i).getPl_id()%>" onclick="open_playlist(this.id,'<%=Pod_fav.get(i).getPl_img_path()%>','<%=Pod_fav.get(i).getPl_name()  %>', '<%=Pod_fav.get(i).getPl_script() %>')">
								                <img class="img_pl" src="<%=Pod_fav.get(i).getPl_img_path()%>">
								            <div class="info_pl_l">
								                <h3 class="name_pl"><%=Pod_fav.get(i).getPl_name()%></h3>
								                <p class="descript_pl"><%=Pod_fav.get(i).getPl_script()%></p>
								            </div>
								        </div>
									    <%}else{
									    	break;
									    }
									    }%>
                            		</div>
								</div>
                            	<%}else{%>
                            		<div style="display:none;" id="wrap_header_pod_fav" class="wrap_header_pl">
							    	<div class="wrap_head_pll">
                        				<h2>MusLife Podcast</h2>
                        				<button id="see_more_pod" onclick="seeMore(this.id)" class="see_more">See more</button>
                        			</div>
                        			<div id="wrap_all_pod_fave" class="wrap_all_pl1">
                        			</div>
								</div>
                            	<%}
                            	}else{%>
                            		<div style="display:none;" id="wrap_header_pod_fav" class="wrap_header_pl">
								    	<div class="wrap_head_pll">
                            				<h2>MusLife Podcast</h2>
                            				<button id="see_more_pod" onclick="seeMore(this.id)" class="see_more">See more</button>
                            			</div>
                            			<div id="wrap_all_pod_fave" class="wrap_all_pl1">
                            			</div>
									</div>
                            	<%}%>
                            	
                            	
                            	<div id="wrap_header_pl"  class="wrap_header_pl">
                            	<div class="wrap_head_pll">
                            		<h2>Recommend Playlist</h2>
                            		<button id="see_more_pll_re" onclick="seeMore(this.id)" class="see_more">See more</button>
                            	</div>
                                <div class="wrap_all_pl1">
                            		<%ArrayList<Playlist> pll = new ArrayList<Playlist>(); 
                            		pll = (ArrayList<Playlist>)request.getSession().getAttribute("Playlists");
                            		for(int i = 0; i < pll.size();i++){
                            		if(pll.get(i).getPl_type().equals("song")){%>
                            			<div class="pl_list" id="<%=pll.get(i).getPl_id()%>" onclick="open_playlist(this.id,'<%=pll.get(i).getPl_img_path()%>','<%=pll.get(i).getPl_name()  %>', '<%=pll.get(i).getPl_script() %>')">
                                            <img class="img_pl" src="<%=pll.get(i).getPl_img_path()%>">
                                        <div class="info_pl_l">
                                            <h3 class="name_pl"><%=pll.get(i).getPl_name() %></h3>
                                            <p class="descript_pl"><%=pll.get(i).getPl_script() %></p>
                                        </div>
                                       
                                    </div>
                            		<%}
                            	}%>
                                </div>
                                </div>
                                
                        
                                <div id="wrap_header_po" class="wrap_header_pl">
                                <div class="wrap_head_pll">
                            		<h2>Recommend Podcast</h2>
                            		<button id="see_more_pod_re" onclick="seeMore(this.id)" class="see_more">See more</button>
                            	</div>
                                <div class="wrap_all_pl1">
                            		<%ArrayList<Playlist> pod = new ArrayList<Playlist>(); 
                            		pod = (ArrayList<Playlist>)request.getSession().getAttribute("Podcast");
                            		for(int i = 0; i < pod.size();i++){
                            		if(pod.get(i).getPl_type().equals("podcast")){%>
                            			<div class="pl_list" id="<%=pod.get(i).getPl_id()%>" onclick="open_playlist(this.id,'<%=pod.get(i).getPl_img_path()%>','<%=pod.get(i).getPl_name()  %>', '<%=pod.get(i).getPl_script() %>')" >
                                            <img class="img_pl" src="<%=pod.get(i).getPl_img_path() %>">
                                        <div class="info_pl_l">
                                            <h3 class="name_pl"><%=pod.get(i).getPl_name() %></h3>
                                            <p class="descript_pl"><%=pod.get(i).getPl_script() %></p>
                                        </div>
                                    </div>
                            		<%}
                            	}%>
                                </div>
                                </div>
                                
                                <div id="wrap_header_pl"  class="wrap_header_pl">
                            	<div class="wrap_head_pll">
                            		<h2>Artist</h2>
                            		<button id="see_more_artist" onclick="seeMore(this.id)" class="see_more">See more</button>
                            	</div>
                                <div class="wrap_all_pl1">
                            		<%ArrayList<Performer> artist = new ArrayList<Performer>(); 
                            		artist = (ArrayList<Performer>)request.getSession().getAttribute("Artist");
                            		for(int i = 0; i < artist.size();i++){%>
                            		<div class="artist_list" id="<%=artist.get(i).getPer_id()%>" onclick="OpenArtist(this.id,'<%=artist.get(i).getImg()%>','<%=artist.get(i).getPer_name()%>')">
                                            <img class="img_artist" src="<%=artist.get(i).getImg()%>">
                                        <div class="info_artist">
                                            <h3 class="name_artist"><%=artist.get(i).getPer_name()%></h3>
                                        </div>
                                    </div>
                            		<%}%>
                                </div>
                                </div>
                            	</div>
                            <%} %>
                            <div class="line"></div>
                                <div class="Wrap_footer">
                                    <div class="footer1">
                                        <a class="link_footer" onclick="appearHome()">Home</a><br>
                                        <a class="link_footer">Privacy</a>
                                    </div>
                                    <div class="footer2">
                                        <a class="link_footer" target="_blank" href="https://www.facebook.com/NFS2019/"><img width="35px" src="images/facebook.png"></a>
                                        <a class="link_footer" target="_blank" href="https://www.instagram.com/_mey_mey_1308/"><img width="35px" src="images/instagram.png"></a>
                                    </div>
                                </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="wrap_out_nof" class="wrap_out_nof">
				    <div class="wrap_nof">
				        <div class="nof">
				            <div class="wrap_head_nof">
				            <h2 class="head_nof">Do you wanna sign out?</h2>
				            </div>
				            <div class="wrap_but_nof">
				                <button onclick="logout()" class="yes_nof">Yes</button>
				                <button onclick="turnoffNof()" class="no_nof">No</button>
				            </div>
				        </div>
				    </div>
				</div>
		<div id="wrap_out_change" class="wrap_out_nof">
		    <div class="wrap_nof">
		        <div class="nof">
		            <div class="wrap_head_nof">
		            <h2 class="head_nof">Change image success!</h2>
		            </div>
		            <div class="wrap_but_nof">
		                <button onclick="turnoffChange()" class="no_nof">Okey</button>
		            </div>
		        </div>
		    </div>
		</div>
		<div id="wrap_out_cre_pll" class="wrap_out_nof" >
			<div class="cre_pll">
			    <div class="create">
	                <div class="wrap_cre_head">
	                    <p class="head_cre">create your playlist</p>
	                </div>
	                <div class="wrap_cre_inp">
	                    <input type="text" id="cre_pll_name" class="pll_cre_name" placeholder="write your playlist name" name="pll_name_cre">
	                    <button onclick="CreatePlaylist()" id="acp_cre_btn" class="acp_cre" >create</button>
	                    <button onclick="turn_off_create_playlist()" id="unacp_cre_btn" class="unacp_cre">cancle</button>
	                </div>
	            </div>
            </div>
		</div>
		
		<div id="wrap_out_cre_nof" class="wrap_out_nof" >
			<div class="cre_pll">
			    <div class="create">
	                <div class="wrap_cre_head">
	                    <p id="head_cre_ctn" class="head_cre"></p>
	                </div>
	                <div class="wrap_cre_inp">
	                    <button onclick="turn_off_nof()" id="acp_cre_btn" class="acp_cre" >ok</button>
	                </div>
	            </div>
            </div>
		</div>
				
			<div id="wrap_out_mes_error" class="wrap_out_nof">
				    <div class="wrap_nof">
				        <div class="nof">
				            <div class="wrap_head_nof">
				            <h2 class="head_nof">Something went wrong, please refresh!</h2>
				            </div>
				            <div class="wrap_but_nof">
				                <button onclick="window.location.reload()" class="no_nof">Refresh</button>
				            </div>
				        </div>
				    </div>
				</div>
        <script >
        if (performance.navigation.type == performance.navigation.TYPE_RELOAD) {
        	document.getElementById("get").submit();
        } else {
        }
	    <%if(request.getSession().getAttribute("count") != null) {%>
	    <%if(Integer.parseInt(request.getSession().getAttribute("count").toString()) < 1 ) {%>
	    		setTimeout(7000);
		        document.getElementById("get").submit();
	    	<%}else{%>
		        
	    	<%} %>
	    <%}else{%>
		        document.getElementById("get").submit();
	    <%} %>	
		    function getVolumn(){
		        var vol = document.getElementById("vol").value
		        document.getElementById("song").volume = vol/100 ;
		    };
		    function loop(status){
		    	$.ajax({
		    		url:"Get_playlist",
		    		type:"POST",
		    		data:{
		    			getPL : "LOOP",
		    			loop: status,
		    		},
		    		success:function(response){
		    			if(response.error == "false"){
		    				if(response.Loop == "lon"){
		    					document.getElementById('song').loop = true;
		    			        document.getElementById('no_loop').style.display="none";
		    			        document.getElementById('loop_one').style.display="block";
		    			        document.getElementById('loop_all').style.display="none";
		    				}else if(response.Loop == "lall"){
		    					document.getElementById('song').loop = false;
		    			        document.getElementById('no_loop').style.display="none";
		    			        document.getElementById('loop_one').style.display="none";
		    			        document.getElementById('loop_all').style.display="block";
		    				}else if(response.Loop == "unlo"){
		    					document.getElementById('song').loop = false;
	    				        document.getElementById('no_loop').style.display="block";
	    				        document.getElementById('loop_one').style.display="none";
	    				        document.getElementById('loop_all').style.display="none";
		    				}
		    			}else if(response.error == "true"){
				  	  		document.getElementById("wrap_out_mes_error").style.display = "flex";
		    			}
		    		},
		    		error:function(){
			  	  		document.getElementById("wrap_out_mes_error").style.display = "flex";
		    		},
		    	});
		    	
		    };
		    
		    
		    function playsong(){
		        document.getElementById("song").play();
		        document.getElementById("play_son_btn").style.display="none";
		        document.getElementById("pause_son_btn").style.display="block";
		    };
		    function pausesong(){
		        document.getElementById("song").pause();
		        document.getElementById("play_son_btn").style.display="block";
		        document.getElementById("pause_son_btn").style.display="none";
		    };
		    //Bài tiếp theo
		    function nextsong(){
		    	 $.ajax({
		    		 url:"Get_playlist",
		    	 	type:"POST",
		    	 	data:{
		    	 		getPL : "NEXT",
		    	 	},
		    		success : function(response){
			            if(response.status === "true"){
				            if(response.love == "true"){
				            	document.getElementById("heart_btn").style.display = "block";
				            	document.getElementById("no_heart_btn").style.display = "none";
				            	
				            }else if(response.love === "false"){
				            	document.getElementById("heart_btn").style.display = "none";
				            	document.getElementById("no_heart_btn").style.display = "block";
				            }
				            document.getElementById('img_song_play').src = response.Img;
			  	  	    	document.getElementById('name_song_play').innerText = response.Name;
			  	  			document.getElementById('author_play').innerText = response.Per;
				            document.getElementById('song').setAttribute("src", response.path);
				            playsong();
			            }else if(response.status === "false"){
			            	document.getElementById("song").currentTime = 0;
			            	playsong();
			            }else if(response.status === "stop"){
			            	document.getElementById("song").currentTime = 0;
			            	pausesong();
			            }
			            console.log(response.path);
		    		},
		    		error: function(xhr, status, error) {
				  	  document.getElementById("wrap_out_mes_error").style.display = "flex";
			  	    }
		    	 });
		         
		        };
		    //Bài trước đó
		        function previoussong(){
		        	$.ajax({
			    		url:"Get_playlist",
			    	 	type:"POST",
			    	 	data:{
			    	 		getPL : "PREVIOUS",
			    	 	},
			    	 	success : function(response){
				            if(response.status === "true"){
					            if(response.love === "true"){
					            	document.getElementById("heart_btn").style.display = "block";
					            	document.getElementById("no_heart_btn").style.display = "none";
					            	
					            }else if(response.love === "false"){
					            	document.getElementById("heart_btn").style.display = "none";
					            	document.getElementById("no_heart_btn").style.display = "block";
					            }
					            document.getElementById('img_song_play').src = response.Img;
				  	  	    	document.getElementById('name_song_play').innerText = response.Name;
				  	  			document.getElementById('author_play').innerText = response.Per;
					            document.getElementById('song').setAttribute("src", response.path);
					            playsong();
				            }else if(response.status == "false"){
				            	document.getElementById("song").currentTime = 0;
				            	playsong();
				            }
				            console.log(response.path);
			    		},
			    		error: function(xhr, status, error) {
					  	  		document.getElementById("wrap_out_mes_error").style.display = "flex";
				  	    }
		        	});
		    };

		    	document.getElementById("song").onplaying = function() {
		    		console.log("playing");
		            document.getElementById("play_son_btn").style.display='none';
		       		document.getElementById("pause_son_btn").style.display='block';
		       		
		        };
		        
		        document.getElementById("song").onpause = function() {
		        	console.log("pause");
		    		document.getElementById("play_son_btn").style.display='block';
		       		document.getElementById("pause_son_btn").style.display='none';
		        };
		        document.getElementById("song").onended  = function() {
		        	console.log("next");
		           nextsong();
		        };
	                setInterval(()=>{
	                document.getElementById("pro_song").max = document.getElementById("song").duration;
	                document.getElementById("pro_song").value = document.getElementById("song").currentTime;
	                	if(isNaN(document.getElementById("song").duration) === false){
		                	let currentMin = Math.floor(document.getElementById("song").currentTime/60);
			                let currentSec = Math.floor(document.getElementById("song").currentTime - currentMin * 60);
			                let durationMin = Math.floor(document.getElementById("song").duration/60);
			                let durationSec = Math.floor(document.getElementById("song").duration - durationMin * 60);
			                if(currentSec < 10){currentSec = "0"+currentSec;}
			                if(durationSec < 10){durationSec = "0"+durationSec;}
			                if(currentMin < 10){currentMin = "0"+currentMin;}
			                if(durationMin < 10){durationMin = "0"+durationMin;}
			                document.getElementById("start").innerText = currentMin + ":"+currentSec;
			                document.getElementById("end").innerText = durationMin + ":"+durationSec;	
		                }else{
		                	document.getElementById("start").innerText = "00" + ":"+"00";
			                document.getElementById("end").innerText = "00" + ":"+"00";	
		                }
	                
	            },1000);
	        document.getElementById("pro_song").onchange = function(){
	            document.getElementById("song").currentTime = document.getElementById("pro_song").value;
	        }
	        function turn_off_nof(){
	        	document.getElementById("wrap_out_cre_nof").style.display="none";

	        }
	        function create_playlist(){
	        	document.getElementById("wrap_out_cre_pll").style.display="flex";
	        }
	        function turn_off_create_playlist(){
	        	document.getElementById("wrap_out_cre_pll").style.display="none";
	        }
	        function showNof(){
	        	document.getElementById('wrap_out_nof').style.display="flex";
	        }
	        function showChange(){
	        	document.getElementById('wrap_out_change').style.display="flex";
	        }
	        function turnoffNof(){
	            document.getElementById('wrap_out_nof').style.display="none";
	        }
	        function turnoffChange(){
	            document.getElementById('wrap_out_change').style.display="none";
	        }
	        function logout(){ 
	        	setTimeout(2000);
	        	document.getElementById("loo").submit();
	        }
	        function appearAcc(){
	        	document.getElementById("wrap_cre_pll").innerHTML = "";
	        	document.getElementById("wrap_all_search").style.display="none";
	        	document.getElementById("see_more_pll_wrap").style.display="none";
	        	document.getElementById("see_more_pll_wrap").innerHTML = "";
	        	document.getElementById("Home").style.display="none";
	        	document.getElementById("account").style.display="block";
	        	document.getElementById("wrap_out_ct").scrollTo(0,0);
	        	document.getElementById("wrap_change_image").style.display="none";
	        	document.getElementById("pl_inside").innerHTML = "";
	        	document.getElementById("pl_inside").style.display="none";
	        }
	        function appearHome(){
	        	document.getElementById("wrap_cre_pll").innerHTML = "";
	        	document.getElementById("wrap_all_search").style.display="none";
	        	document.getElementById("see_more_pll_wrap").style.display="none";
	        	document.getElementById("pl_inside").style.display="none";
	        	document.getElementById("see_more_pll_wrap").innerHTML = "";
	        	document.getElementById("pl_inside").innerHTML = "";
	        	document.getElementById("Home").style.display="block";
	        	document.getElementById("account").style.display="none";
	        	document.getElementById("wrap_change_image").style.display="none";
	        	document.getElementById("wrap_out_ct").scrollTo(0,0);
	        }
	        function openSearch(){
	        	document.getElementById("wrap_cre_pll").innerHTML = "";
	        	document.getElementById("see_more_pll_wrap").style.display="none";
	        	document.getElementById("pl_inside").style.display="none";
	        	document.getElementById("see_more_pll_wrap").innerHTML = "";
	        	document.getElementById("pl_inside").innerHTML = "";
	        	document.getElementById("Home").style.display="none";
	        	document.getElementById("account").style.display="none";
	        	document.getElementById("wrap_change_image").style.display="none";
	        	document.getElementById("wrap_all_search").style.display="block";
	        	document.getElementById("wrap_out_ct").scrollTo(0,0);
	        }
	        function appearChangeImage(){
	        	document.getElementById("wrap_cre_pll").innerHTML = "";
	        	document.getElementById("wrap_all_search").style.display="none";
	        	document.getElementById("see_more_pll_wrap").style.display="none";
	        	document.getElementById("pl_inside").style.display="none";
	        	document.getElementById("see_more_pll_wrap").innerHTML = "";
	        	document.getElementById("pl_inside").innerHTML = "";
	        	document.getElementById("Home").style.display="none";
	        	document.getElementById("account").style.display="none";
	        	document.getElementById("wrap_change_image").style.display="block";
	        	document.getElementById("wrap_out_ct").scrollTo(0,0);
	        }
	        function showPreview(event){
	  		  if(event.target.files.length > 0){
	  		    var src = URL.createObjectURL(event.target.files[0]);
	  		    var preview = document.getElementById("img_preview");
	  		    preview.src = src;
	  		  }else{
	  			  preview.src = "";
	  		  }
	  		}
	  	  const input = document.getElementById("img_upload");
	  	  input.addEventListener("change", updateImageDisplay);
	  	  
	  	  function updateImageDisplay() {
	  		  const curFiles = input.files;
	  		  if (curFiles.length === 0) {
	  			document.getElementById("img_preview").style.display="none";
	  			 document.getElementById("sub_btn").disabled = true;
	  		    document.getElementById("mes").innerText = "No files currently selected for upload";
	  		  } else {
	  		    for (const file of curFiles) {
	  		      if (validFileType(file)) {
	  		    	document.getElementById("mes").innerText = "";
	  		    	document.getElementById("img_preview").style.display="block";
	  		        document.getElementById("sub_btn").disabled = false;
	  		      } else {
	  		    	document.getElementById("img_preview").style.display="none";
	  		    	document.getElementById("mes").innerText ="Not a valid file type. Update your selection";
	  		        document.getElementById("sub_btn").disabled = true;
	  		      }
	  		    }
	  		  }
	  		}
	  	  
	  	  function validFileType(file) {
	  		  return fileTypes.includes(file.type);
	  		}
	  	  
	  	  const fileTypes = [
	  		  "image/apng",
	  		  "image/jpeg",
	  		  "image/pjpeg",
	  		  "image/png",
	  		  "image/jpg",
	  		  "image/png"
	  		];
	  	
	  	document.getElementById("sub_btn").addEventListener("click", function (e) {
	  	    e.preventDefault();

	  	    var formData = new FormData();
	  	    var fileInput = document.getElementById("img_upload");
	  	    var inp = document.getElementById("sub_btn").value;	  	    
	  	    formData.append("req",inp);
	  	    formData.append("file", fileInput.files[0]);

	  	    var xhr = new XMLHttpRequest();
	  	    xhr.open("POST", "changeimage", true);

	  	    xhr.onreadystatechange = function () {
	  	        if (xhr.readyState === 4 && xhr.status === 200) {
	  	        	var response = JSON.parse(xhr.responseText);
	  	        	console.log(response.path);
	  	          	document.getElementById("img_acc_small").src = response.path;
	  	          	document.getElementById("img_inf").src = response.path;
	  	          	$("#img_upload").val(null);
	  	          	document.getElementById("skip_btn1").style.display="block";
	  	          	document.getElementById("circle_load").style.display = "none";
	          		document.getElementById("sub_btn").style.display = "block";
	  	          	document.getElementById("sub_btn").disabled = true;
	  	          	document.getElementById("img_preview").style.display="none";
	  	          	showChange();
	  	        }else{
	  	        }
	  	    };
	  	    xhr.send(formData);
	  	});
	  	
	  	document.getElementById("skip_btn1").addEventListener("click", function (e) {
	  	    e.preventDefault(); 

	  	    var formData = new FormData();
	  	    var fileInput = document.getElementById("img_upload");
	  	    var inp = document.getElementById("skip_btn1").value;
	  	    
	  	    formData.append("req",inp);
	  	    var xhr = new XMLHttpRequest();
	  	    xhr.open("POST", "changeimage", true);

	  	    xhr.onreadystatechange = function () {
	  	        if (xhr.readyState === 4 && xhr.status === 200) {
	  	        	var response = JSON.parse(xhr.responseText);
		  	      	console.log(response.path);
	  	          	document.getElementById("img_acc_small").src = response.path;
	  	          	document.getElementById("img_inf").src = response.path;
	  	          	$("#img_upload").val(null);
	  	          	document.getElementById("skip_btn1").style.display="block";
	  	          	document.getElementById("circle_load").style.display = "none";
	          		document.getElementById("sub_btn").style.display = "block";
	  	          	document.getElementById("sub_btn").disabled = true;
	  	          	document.getElementById("img_preview").style.display="none";
	  	          	showChange();
	  	        }else{
		  	  		document.getElementById("wrap_out_mes_error").style.display = "flex";
	  	        }
	  	    };
	  	    xhr.send(formData);
	  	});
	  	
	  	function access(){
	        document.getElementById("skip_btn1").style.display="none";
	  		document.getElementById("circle_load").style.display = "block";
      		document.getElementById("sub_btn").style.display = "none";
	  	}
	  	
	  	function open_playlist(id, img , n, s){
	  		var getpl = "GETPLAYLIST";
	  		var Data = id;
	  	    $.ajax({
	  	        url: "Get_playlist",
	  	        type: "POST",
	  	        data: {
	  	        	getPL: getpl,
	  	        	ID_playlist: Data,
	  	        },
	  	        success: function(response) {
	  	        	var count = 0;
	  	        	var suf = response.suff;
	  	        	var like = response.like;
	  	        	var like_but = "";
	  	        	var suf_but = "";
	  	        	console.log(suf);
	  	        	for (var key in response.song) {
	  	        	if (response.song.hasOwnProperty(key)) {
	  	        	        count++;
	  	        	    }
	  	        	}
	  	        	if(like == "true"){
	  	        		like_but = "<button style='display:none' id='like' onclick=\"Like(this.id,'"+id+"','"+img+"','"+n+"','"+s+"')\" style='display:none' class='like_btn_pll'><img width='20px' src='images/like.png'></button>  <button id='dislike' onclick=\"Like(this.id,'"+id+"' ,'"+img+"','"+n+"','"+s+"')\" class='like_btn_pll'><img width='20px' src='images/like_gr.png'></button>";
	  	        	}else if(like == "false"){
	  	        		like_but = "<button id='like' onclick=\"Like(this.id,'"+id+"','"+img+"','"+n+"','"+s+"')\" class='like_btn_pll'><img width='20px' src='images/like.png'></button>  <button id='dislike' onclick=\"Like(this.id,'"+id+"' ,'"+img+"','"+n+"','"+s+"')\" style='display:none' class='like_btn_pll'><img width='20px' src='images/like_gr.png'></button>";
	  	        	}else if(like == "none"){
	  	        		
	  	        	}
	  	        	if(suf === "true"){
	  	        		suf_but = "<button id='suf' onclick=\"Shuffle(this.id,'"+id+"')\" style='display:none' class='shff_song_pll'><img width='20px' src='images/shuffle_unactive.png'></button><button id='unsuf' onclick=\"Shuffle(this.id,'"+id+"')\" style='display:block' class='shff_song_pll'><img width='20px' src='images/shuffle.png'></button>";
	  	        	}else if(suf === "false"){
	  	        		suf_but = "<button id='suf' onclick=\"Shuffle(this.id,'"+id+"')\" style='display:block' class='shff_song_pll'><img width='20px' src='images/shuffle_unactive.png'></button><button id='unsuf' onclick=\"Shuffle(this.id,'"+id+"')\" style='display:none' class='shff_song_pll' ><img width='20px' src='images/shuffle.png'></button>";
	  	        	}
		        	document.getElementById("wrap_all_search").style.display="none";
	  	        	document.getElementById("see_more_pll_wrap").style.display="none";
	  	        	document.getElementById("pl_inside").style.display="block";
	  	        	document.getElementById("see_more_pll_wrap").innerHTML ="";
	  	        	document.getElementById("wrap_cre_pll").innerHTML = "";
	  	        	document.getElementById("Home").style.display="none";
	  	        	document.getElementById("account").style.display="none";
	  	        	document.getElementById("wrap_change_image").style.display="none";
	  		  		document.getElementById("pl_inside").innerHTML = "<div class='wrap_pl_inf_head'><div class='pl_img_head'><img class='img_head' src='"+img+"'><div class='pl_inf_head'><p class='pl_name_head'>"+n+"</p><p class='pl_script_head'>"+s+"</p></div></div><div class='wrap_all_be_pll'><button class='play_pl_but'><img style='margin-left: 3px; margin-top:3px' width='23px' src='images/play-button-arrowhead.png' alt=''></button> "+like_but+" "+suf_but+"</div></div><div style='background-color: white; height:2px; margin:2%;'></div>";
	  		  		for(var i = 0; i < count; i++){
	  		  			var c = i + 1;
	  		  			if(response.song[i].like === "true"){
	  		  				document.getElementById("pl_inside").innerHTML = document.getElementById("pl_inside").innerHTML + "<div class='song' id='med"+response.song[i].ID+"' ><div id='"+response.song[i].ID+"' onclick=\"playSongchoo(this.id,'"+id+"','"+response.song[i].img+"','"+response.song[i].name+"','"+response.song[i].per+"', 'playlist')\" class='img_name_song'><div class='num'><p>"+c+"</p></div><img class='img_song' src='"+response.song[i].img+"' alt=''><div class='info'><p class='name_song'>"+response.song[i].name+"</p><p class='author'>"+response.song[i].per+"</p></div></div><button id='DLSID_"+response.song[i].ID+"' onclick=\"Likesong(this.id, 'LSID_"+response.song[i].ID+"','dislike','"+response.song[i].ID+"')\" style='display:block;' class='like_btn'><img style='width:23px;height:23px;' src='images/like_gr.png'></button><button id='LSID_"+response.song[i].ID+"' onclick=\"Likesong(this.id, 'DLSID_"+response.song[i].ID+"','like','"+response.song[i].ID+"')\" style='display:none;' class='like_btn'><img style='width:23px;height:23px;' src='images/like.png'></button></div></div>"
	  		  			}else if(response.song[i].like === "false"){
	  		  				document.getElementById("pl_inside").innerHTML = document.getElementById("pl_inside").innerHTML + "<div class='song' id='med"+response.song[i].ID+"' ><div id='"+response.song[i].ID+"' onclick=\"playSongchoo(this.id,'"+id+"','"+response.song[i].img+"','"+response.song[i].name+"','"+response.song[i].per+"', 'playlist')\" class='img_name_song'><div class='num'><p>"+c+"</p></div><img class='img_song' src='"+response.song[i].img+"' alt=''><div class='info'><p class='name_song'>"+response.song[i].name+"</p><p class='author'>"+response.song[i].per+"</p></div></div><button id='DLSID_"+response.song[i].ID+"' onclick=\"Likesong(this.id,'LSID_"+response.song[i].ID+"', 'dislike','"+response.song[i].ID+"')\" style='display:none;'  class='like_btn'><img style='width:23px;height:23px;' src='images/like_gr.png'></button><button id='LSID_"+response.song[i].ID+"' onclick=\"Likesong(this.id, 'DLSID_"+response.song[i].ID+"', 'like','"+response.song[i].ID+"')\" style='display:block;' class='like_btn'><img style='width:23px;height:23px;' src='images/like.png'></button></div></div>"
	  		  			}
	  		  		};
	  		  		document.getElementById("wrap_out_ct").scrollTo(0,0);
	  	        },
	  	        error: function(xhr, status, error) {
		  	  		document.getElementById("wrap_out_mes_error").style.display = "flex";
	  	        }
	  	    });
	  	};
	  	
	  	function playSongchoo(id, ID_PLL, img, n, p, place){
	  		var song_id = id;
	  		var pll_id = ID_PLL;
	  		var req = "PLAYSONG";
	  		pllid = ID_PLL;
	  		$.ajax({
  				url: "Get_playlist",
  	  	        type: "POST",
  	  	        data: {
  	  	        	getPL: req,
  	  	        	ID_playlist: ID_PLL,
  	  	        	ID_SONG: song_id,
  	  	        	PLACE : place,
  	  	        },
	  	  	    success: function(response){
	  	  	    	document.getElementById('img_song_play').src = img;
	  	  	    	document.getElementById('name_song_play').innerText = n;
	  	  			document.getElementById('author_play').innerText = p;
		            document.getElementById('song').setAttribute("src", response.path);
		            if(response.love == "true"){
		            	document.getElementById("heart_btn").style.display = "block";
		            	document.getElementById("no_heart_btn").style.display = "none";
		            }else if(response.love == "false"){
		            	document.getElementById("heart_btn").style.display = "none";
		            	document.getElementById("no_heart_btn").style.display = "block";
		            }
		            playsong();
		            console.log(response.path);
	  	  	    },
		  	  	error: function(xhr, status, error){
		  	  		document.getElementById("wrap_out_mes_error").style.display = "flex";
		  	  	}
	  		});
	  	};
	  	
	  	function Shuffle(stt, ID_PLL){
	  		$.ajax({
	  			url: "Get_playlist",
	  			type: "POST",
	  			data :{
	  				getPL : "shuffle",
	  				ID : ID_PLL,
	  				stat : stt,
	  			},
	  			success : function(response){
	  				if(response.stt == "true"){
	  		  			document.getElementById("suf").style.display= "none";
	  			  		document.getElementById("unsuf").style.display= "block";
	  		  		}else if(response.stt == "false"){
	  		  			document.getElementById("suf").style.display= "block";
	  			  		document.getElementById("unsuf").style.display= "none";
	  		  		}
	  			},
	  			error: function(xhr, status, error){
		  	  		document.getElementById("wrap_out_mes_error").style.display = "flex";
		  	  	},
	  		});
	  		
	  		
	  	};
	  	
	  	function Like(stt, ID, img , name, script){
	  		$.ajax({
	  			url: "Get_playlist",
	  			type: "POST",
	  			data :{
	  				getPL : "love",
	  				ID : ID,
	  				stat : stt,
	  			},
	  			success : function(response){
	  				if(response.stt == "like"){
	  		  			document.getElementById("like").style.display= "none";
	  			  		document.getElementById("dislike").style.display= "block";
	  			  		var lis_lib = document.getElementById("lis_lib_left");
	  			  		lis_lib.innerHTML = lis_lib.innerHTML + "<div id='"+ID+"' class='pl_left' onclick=\"open_playlist(this.id,'"+img+"','"+name+"','"+script+"')\"><div class='wrap_img_pl_left'><img src=\""+img+"\" class='img_pl_left'></div><div class='wrap_info_pl_list_left'><p class='pl_name_left'>"+name+"</p><p class='pl_scr_left'>"+script+"</p></div></div>";
	  			  		if(response.type == "song"){
	  			  			if(response.size == 0){
	  			  				console.log("ahihi like");
	  			  				document.getElementById("wrap_header_pll_fav").style.display= "none";
	  			  			}else{
	  			  				document.getElementById("wrap_header_pll_fav").style.display= "block";
	  			  				var pll_div = document.getElementById("wrap_all_pll_fave");
	  			  				pll_div.innerHTML =  pll_div.innerHTML + "<div class='pl_list' id='"+ID+"' onclick=\"open_playlist(this.id,'"+img+"','"+name+"', '"+script+"')\"> <img class='img_pl' src=\""+img+"\"><div class='info_pl_l'><h3 class='name_pl'>"+name+"<p class='descript_pl'>"+script+"</p></div><div class='wrap_play_pl_btn'><button class='play_pl_btn'><img class='play_pl_img' src='images/play-button-arrowhead.png' alt=''></button></div></div>";
	  			  				pll_div.style.display="none";
	  			  				pll_div.style.display="flex";
	  			  			}
	  			  		}else if(response.type == "podcast"){
		  			  		if(response.size == 0){
	  			  				document.getElementById("wrap_header_pod_fav").style.display= "none";
	  			  			}else{
	  			  				document.getElementById("wrap_header_pod_fav").style.display= "block";
	  			  				var pod_div = document.getElementById("wrap_all_pod_fave");
	  			  				pod_div.innerHTML =  pod_div.innerHTML + "<div class='pl_list' id='"+ID+"' onclick=\"open_playlist(this.id,'"+img+"','"+name+"', '"+script+"')\"><img class='img_pl' src=\""+img+"\"><div class='info_pl_l'><h3 class='name_pl'>"+name+"<p class='descript_pl'>"+script+"</p></div><div class='wrap_play_pl_btn'><button class='play_pl_btn'><img class='play_pl_img' src='images/play-button-arrowhead.png' alt=''></button></div></div>";
		  			  			pod_div.style.display="none";
		  			  			pod_div.style.display="flex";
	  			  			}
	  			  		}
	  		  		}else if(response.stt == "dislike"){
	  		  			document.getElementById("like").style.display= "block";
	  			  		document.getElementById("dislike").style.display= "none";
	  			  		var lis_lib = document.getElementById("lis_lib_left");
		  			  	for (var i = 0; i < lis_lib.children.length; i++) {
	  			  			if(lis_lib.children[i].id === ID){
	  			  				lis_lib.removeChild(lis_lib.children[i]);
	  			  				break;
	  			  			}
				  		   
			  		  	}
		  			  	if(response.type == "song"){
	  			  			if(response.size == 0){
			  			  		var wrap_pll = document.getElementById("wrap_all_pll_fave");
		  			  			for (var i = 0; i < wrap_pll.children.length; i++) {
			  			  			if(wrap_pll.children[i].id === ID){
			  			  				wrap_pll.removeChild(wrap_pll.children[i]);
			  			  				break;
			  			  			}
		  			  		   
		  			  		  	}
	  			  				document.getElementById("wrap_header_pll_fav").style.display= "none";
	  			  			}else{
	  			  				var wrap_pll = document.getElementById("wrap_all_pll_fave");
		  			  			for (var i = 0; i < wrap_pll.children.length; i++) {
			  			  			if(wrap_pll.children[i].id === ID){
			  			  				wrap_pll.removeChild(wrap_pll.children[i]);
			  			  				break;
			  			  			}
		  			  		   
		  			  		  	}
	  			  			}
	  			  		}else if(response.type == "podcast"){
		  			  		if(response.size == 0){
	  			  				var wrap_pod = document.getElementById("wrap_all_pod_fave");
		  			  			for (var i = 0; i < wrap_pod.children.length; i++) {
			  			  			if(wrap_pod.children[i].id === ID){
			  			  				wrap_pod.removeChild(wrap_pod.children[i]);
			  			  				break;
			  			  			}
		  			  		   
		  			  		  	}
	  			  				document.getElementById("wrap_header_pod_fav").style.display= "none";
	  			  			}else{
	  			  				var wrap_pod = document.getElementById("wrap_all_pod_fave");
		  			  			for (var i = 0; i < wrap_pod.children.length; i++) {
			  			  			if(wrap_pod.children[i].id === ID){
			  			  				wrap_pod.removeChild(wrap_pod.children[i]);
			  			  				break;
			  			  			}
		  			  		   
		  			  		  	}
	  			  			}
	  			  		}
	  		  		}else if(response.stt =="error"){
			  	  		document.getElementById("wrap_out_mes_error").style.display = "flex";
	  		  		}
	  			},
	  			error: function(xhr, status, error){
		  	  		document.getElementById("wrap_out_mes_error").style.display = "flex";
		  	  	},
	  		});
	  	};
	  	
	  	//đang làm dở phần tìm kiếm.
	  	function Search(){
	  		var cate = document.getElementById("op_type").value;
	  		var search_text = document.getElementById("search").value;
	  		$.ajax({
	  			url:"Get_playlist",
	  			type:"POST",
	  			data: {
	  				getPL : "Search",
	  				Cate : cate,
	  				search : search_text,
	  			},
	  			success : function(response){
	  				if(response.error === "true"){
			  	  		document.getElementById("wrap_out_mes_error").style.display = "flex";
	  				}else if(response.error === "false"){
	  					if(response.status === "Song"){
	  						var count = 0;
	  						for (var key in response.song) {
	  			  	        	if (response.song.hasOwnProperty(key)) {
	  			  	        	        count++;
	  			  	        	}
	  			  	        }
	  						if(count > 0){
	  							document.getElementById("result_search").innerHTML = "<h2>Song</h2><br>";
	  							for(var i = 0 ; i < count ; i++){
	  								if(response.song[i].love === "true"){
	  									document.getElementById("result_search").innerHTML = document.getElementById("result_search").innerHTML + "<div class='song'><div id='"+response.song[i].ID+"' onclick=\"playSongchoo(this.id,'0','"+response.song[i].img+"','"+response.song[i].name+"','"+response.song[i].per+"', 'songout')\" class='img_name_song'><div class='num'></div><img class='img_song' src='"+response.song[i].img+"' alt=''><div class='info'><p class='name_song'>"+response.song[i].name+"</p><p class='author'>"+response.song[i].per+"</p></div></div><button id='DLSID_"+response.song[i].ID+"' onclick=\"Likesong(this.id, 'LSID_"+response.song[i].ID+"','dislike','"+response.song[i].ID+"')\" style='display:block;' class='like_btn'><img style='width:23px;height:23px;' src='images/like_gr.png'></button><button id='LSID_"+response.song[i].ID+"' onclick=\"Likesong(this.id, 'DLSID_"+response.song[i].ID+"','like','"+response.song[i].ID+"')\" style='display:none;' class='like_btn'><img style='width:23px;height:23px;' src='images/like.png'></button></div></div>";
	  								}else if(response.song[i].love === "false"){
	  			  		  				document.getElementById("result_search").innerHTML = document.getElementById("result_search").innerHTML + "<div class='song'><div id='"+response.song[i].ID+"' onclick=\"playSongchoo(this.id,'0','"+response.song[i].img+"','"+response.song[i].name+"','"+response.song[i].per+"','songout')\" class='img_name_song'><div class='num'></div><img class='img_song' src='"+response.song[i].img+"' alt=''><div class='info'><p class='name_song'>"+response.song[i].name+"</p><p class='author'>"+response.song[i].per+"</p></div></div><button id='DLSID_"+response.song[i].ID+"' onclick=\"Likesong(this.id,'LSID_"+response.song[i].ID+"', 'dislike','"+response.song[i].ID+"')\" style='display:none;'  class='like_btn'><img style='width:23px;height:23px;' src='images/like_gr.png'></button><button id='LSID_"+response.song[i].ID+"' onclick=\"Likesong(this.id, 'DLSID_"+response.song[i].ID+"', 'like','"+response.song[i].ID+"')\" style='display:block;' class='like_btn'><img style='width:23px;height:23px;' src='images/like.png'></button></div></div>";
	  								}
	  							}
	  						}else{
	  							document.getElementById("result_search").innerHTML = "<div style='margin-top:70px;margin-bottom: 50px; text-align:center; '>Cannot find!</div>";
	  						}
		  	        	}else if(response.status === "Podcast"){
		  	        		var count = 0;
	  						for (var key in response.podcast) {
	  			  	        	if (response.podcast.hasOwnProperty(key)) {
	  			  	        	        count++;
	  			  	        	}
	  			  	        }
	  						if(count > 0){
	  							document.getElementById("result_search").innerHTML = "<h2>Poscast</h2>";
	  							for(var i = 0 ; i < count ; i++){
	  								if(response.podcast[i].love === "true"){
	  									document.getElementById("result_search").innerHTML = document.getElementById("result_search").innerHTML + "<div class='song'><div id='"+response.podcast[i].ID+"' onclick=\"playSongchoo(this.id,'0','"+response.podcast[i].img+"','"+response.podcast[i].name+"','"+response.podcast[i].per+"', 'podout')\" class='img_name_song'><div class='num'></div><img class='img_song' src='"+response.podcast[i].img+"' alt=''><div class='info'><p class='name_song'>"+response.podcast[i].name+"</p><p class='author'>"+response.podcast[i].per+"</p></div></div><button id='DLSID_"+response.podcast[i].ID+"' onclick=\"Likesong(this.id, 'LSID_"+response.podcast[i].ID+"','dislike','"+response.podcast[i].ID+"')\" style='display:block;' class='like_btn'><img style='width:23px;height:23px;' src='images/like_gr.png'></button><button id='LSID_"+response.podcast[i].ID+"' onclick=\"Likesong(this.id, 'DLSID_"+response.podcast[i].ID+"','like','"+response.podcast[i].ID+"')\" style='display:none;' class='like_btn'><img style='width:23px;height:23px;' src='images/like.png'></button></div></div>";
	  								}else if(response.podcast[i].love === "false"){
	  			  		  				document.getElementById("result_search").innerHTML = document.getElementById("result_search").innerHTML + "<div class='song'><div id='"+response.podcast[i].ID+"' onclick=\"playSongchoo(this.id,'0','"+response.podcast[i].img+"','"+response.podcast[i].name+"','"+response.podcast[i].per+"','podout')\" class='img_name_song'><div class='num'></div><img class='img_song' src='"+response.podcast[i].img+"' alt=''><div class='info'><p class='name_song'>"+response.podcast[i].name+"</p><p class='author'>"+response.podcast[i].per+"</p></div></div><button id='DLSID_"+response.podcast[i].ID+"' onclick=\"Likesong(this.id,'LSID_"+response.podcast[i].ID+"', 'dislike','"+response.podcast[i].ID+"')\" style='display:none;'  class='like_btn'><img style='width:23px;height:23px;' src='images/like_gr.png'></button><button id='LSID_"+response.podcast[i].ID+"' onclick=\"Likesong(this.id, 'DLSID_"+response.podcast[i].ID+"', 'like','"+response.podcast[i].ID+"')\" style='display:block;' class='like_btn'><img style='width:23px;height:23px;' src='images/like.png'></button></div></div>";
	  								}
	  							}
	  						}else{
	  							document.getElementById("result_search").innerHTML = "<div style='margin-top:70px;margin-bottom: 50px; text-align:center; '>Cannot find!</div>";
	  						}
		  	        	}else if(response.status === "Artist"){
		  	        		var count = 0;
	  						for (var key in response.perfomers) {
	  			  	        	if (response.perfomers.hasOwnProperty(key)) {
	  			  	        	        count++;
	  			  	        	}
	  			  	        }
	  						if(count > 0){
	  							document.getElementById("result_search").innerHTML = "<h2>Artist</h2>";
	  							for(var i = 0 ; i < count ; i++){
	  								document.getElementById("result_search").innerHTML = document.getElementById("result_search").innerHTML + "<div class='song'><div id='"+response.perfomers[i].id+"' onclick=\"OpenArtist(this.id,'"+response.perfomers[i].img+"','"+response.perfomers[i].name+"')\" class='img_name_song'><img class='img_song' src='"+response.perfomers[i].img+"' alt=''><div class='info'><p class='name_song'>"+response.perfomers[i].name+"</p></div></div></div></div>";
	  							}
	  						}else{
	  							document.getElementById("result_search").innerHTML = "<div style='margin-top:70px;margin-bottom: 50px; text-align:center; '>Cannot find!</div>";
	  						}
		  	        	}else if(response.status === "All"){
		  	        		document.getElementById("result_search").innerHTML = "";
		  	        		var count_per = 0;
	  						for (var key in response.perfomers) {
	  			  	        	if (response.perfomers.hasOwnProperty(key)) {
	  			  	        		count_per++;
	  			  	        	}
	  			  	        }
	  						var count_song = 0;
	  						for (var key in response.song) {
	  			  	        	if (response.song.hasOwnProperty(key)) {
	  			  	        		count_song++;
	  			  	        	}
	  			  	        }
	  						var count_pod = 0;
	  						for (var key in response.podcast) {
	  			  	        	if (response.podcast.hasOwnProperty(key)) {
	  			  	        		count_pod++;
	  			  	        	}
	  			  	        }
	  						var count_song_pll = 0;
	  						for (var key in response.songPll) {
	  			  	        	if (response.songPll.hasOwnProperty(key)) {
	  			  	        		count_song_pll++;
	  			  	        	}
	  			  	        }
	  						var count_pod_pll = 0;
	  						for (var key in response.podPll) {
	  			  	        	if (response.podPll.hasOwnProperty(key)) {
	  			  	        		count_pod_pll++;
	  			  	        	}
	  			  	        }
	  						var sum_all = count_per + count_song + count_pod + count_song_pll + count_pod_pll;
	  						console.log(sum_all);
	  						if(sum_all > 0){
	  							var inner_result = document.getElementById("result_search");
	  							if(count_per > 0){
	  								inner_result.innerHTML = inner_result.innerHTML + "<h2>Artist</h2>";
		  							for(var i = 0 ; i < count_per ; i++){
		  								inner_result.innerHTML = inner_result.innerHTML + "<div class='song'><div id='"+response.perfomers[i].id+"' onclick=\"OpenArtist(this.id,'"+response.perfomers[i].img+"','"+response.perfomers[i].name+"')\" class='img_name_song'><img class='img_song' src='"+response.perfomers[i].img+"' alt=''><div class='info'><p class='name_song'>"+response.perfomers[i].name+"</p></div></div></div></div>";
		  							}
		  						}
	  							if(count_song > 0){
	  								inner_result.innerHTML = inner_result.innerHTML + "<h2>Song</h2>";
	  								for(var i = 0 ; i < count_song ; i++){
		  								if(response.song[i].love === "true"){
		  									inner_result.innerHTML = inner_result.innerHTML + "<div class='song'><div id='"+response.song[i].ID+"' onclick=\"playSongchoo(this.id,'0','"+response.song[i].img+"','"+response.song[i].name+"','"+response.song[i].per+"', 'songout')\" class='img_name_song'><div class='num'></div><img class='img_song' src='"+response.song[i].img+"' alt=''><div class='info'><p class='name_song'>"+response.song[i].name+"</p><p class='author'>"+response.song[i].per+"</p></div></div><button id='DLSID_"+response.song[i].ID+"' onclick=\"Likesong(this.id, 'LSID_"+response.song[i].ID+"','dislike','"+response.song[i].ID+"')\" style='display:block;' class='like_btn'><img style='width:23px;height:23px;' src='images/like_gr.png'></button><button id='LSID_"+response.song[i].ID+"' onclick=\"Likesong(this.id, 'DLSID_"+response.song[i].ID+"','like','"+response.song[i].ID+"')\" style='display:none;' class='like_btn'><img style='width:23px;height:23px;' src='images/like.png'></button></div></div>";
		  								}else if(response.song[i].love === "false"){
		  									inner_result.innerHTML = inner_result.innerHTML + "<div class='song'><div id='"+response.song[i].ID+"' onclick=\"playSongchoo(this.id,'0','"+response.song[i].img+"','"+response.song[i].name+"','"+response.song[i].per+"','songout')\" class='img_name_song'><div class='num'></div><img class='img_song' src='"+response.song[i].img+"' alt=''><div class='info'><p class='name_song'>"+response.song[i].name+"</p><p class='author'>"+response.song[i].per+"</p></div></div><button id='DLSID_"+response.song[i].ID+"' onclick=\"Likesong(this.id,'LSID_"+response.song[i].ID+"', 'dislike','"+response.song[i].ID+"')\" style='display:none;'  class='like_btn'><img style='width:23px;height:23px;' src='images/like_gr.png'></button><button id='LSID_"+response.song[i].ID+"' onclick=\"Likesong(this.id, 'DLSID_"+response.song[i].ID+"', 'like','"+response.song[i].ID+"')\" style='display:block;' class='like_btn'><img style='width:23px;height:23px;' src='images/like.png'></button></div></div>";
		  								}
		  							}
		  						}
	  							if(count_pod > 0){
	  								inner_result.innerHTML = inner_result.innerHTML + "<h2>Podcast</h2>";
	  								for(var i = 0 ; i < count_pod ; i++){
		  								if(response.podcast[i].love === "true"){
		  									inner_result.innerHTML = inner_result.innerHTML + "<div class='song'><div id='"+response.podcast[i].ID+"' onclick=\"playSongchoo(this.id,'0','"+response.podcast[i].img+"','"+response.podcast[i].name+"','"+response.podcast[i].per+"', 'podout')\" class='img_name_song'><div class='num'></div><img class='img_song' src='"+response.podcast[i].img+"' alt=''><div class='info'><p class='name_song'>"+response.podcast[i].name+"</p><p class='author'>"+response.podcast[i].per+"</p></div></div><button id='DLSID_"+response.podcast[i].ID+"' onclick=\"Likesong(this.id, 'LSID_"+response.podcast[i].ID+"','dislike','"+response.podcast[i].ID+"')\" style='display:block;' class='like_btn'><img style='width:23px;height:23px;' src='images/like_gr.png'></button><button id='LSID_"+response.podcast[i].ID+"' onclick=\"Likesong(this.id, 'DLSID_"+response.podcast[i].ID+"','like','"+response.podcast[i].ID+"')\" style='display:none;' class='like_btn'><img style='width:23px;height:23px;' src='images/like.png'></button></div></div>";
		  								}else if(response.podcast[i].love === "false"){
		  									inner_result.innerHTML = inner_result.innerHTML + "<div class='song'><div id='"+response.podcast[i].ID+"' onclick=\"playSongchoo(this.id,'0','"+response.podcast[i].img+"','"+response.podcast[i].name+"','"+response.podcast[i].per+"','podout')\" class='img_name_song'><div class='num'></div><img class='img_song' src='"+response.podcast[i].img+"' alt=''><div class='info'><p class='name_song'>"+response.podcast[i].name+"</p><p class='author'>"+response.podcast[i].per+"</p></div></div><button id='DLSID_"+response.podcast[i].ID+"' onclick=\"Likesong(this.id,'LSID_"+response.podcast[i].ID+"', 'dislike','"+response.podcast[i].ID+"')\" style='display:none;'  class='like_btn'><img style='width:23px;height:23px;' src='images/like_gr.png'></button><button id='LSID_"+response.podcast[i].ID+"' onclick=\"Likesong(this.id, 'DLSID_"+response.podcast[i].ID+"', 'like','"+response.podcast[i].ID+"')\" style='display:block;' class='like_btn'><img style='width:23px;height:23px;' src='images/like.png'></button></div></div>";
		  								}
		  							}
		  						}
	  							if(count_song_pll > 0){
	  								inner_result.innerHTML = inner_result.innerHTML + "<h2>Song Playlist</h2>";
		  							for(var i = 0 ; i < count_song_pll ; i++){
		  								inner_result.innerHTML = inner_result.innerHTML + "<div class='song' id='"+response.songPll[i].id+"' onclick=\"open_playlist(this.id,'"+response.songPll[i].img+"','"+response.songPll[i].name+"','"+response.songPll[i].script+"')\"><img class='img_song' src=\""+response.songPll[i].img+"\"><div class='info'><h3 class='name_song'>"+response.songPll[i].name+"<p class='descript_pl'>"+response.songPll[i].script+"</p></div><div class='wrap_play_pl_btn'><button class='play_pl_btn'><img class='play_pl_img' src='images/play-button-arrowhead.png' alt=''></button></div></div>";
		  				  			}
		  						}
	  							if(count_pod_pll > 0){
	  								inner_result.innerHTML = inner_result.innerHTML + "<h2>Podcast Playlist</h2>";
		  							for(var i = 0 ; i < count_pod_pll ; i++){ 
		  								inner_result.innerHTML = inner_result.innerHTML + "<div class='song' id='"+response.podPll[i].id+"' onclick=\"open_playlist(this.id,'"+response.podPll[i].img+"','"+response.podPll[i].name+"','"+response.podPll[i].script+"')\"><img class='img_song' src=\""+response.podPll[i].img+"\"><div class='info'><h3 class='name_song'>"+response.podPll[i].name+"<p class='descript_pl'>"+response.podPll[i].script+"</p></div><div class='wrap_play_pl_btn'><button class='play_pl_btn'><img class='play_pl_img' src='images/play-button-arrowhead.png' alt=''></button></div></div>";
		  				  			}
		  						}
	  						}else{
	  							document.getElementById("result_search").innerHTML = "<div style='margin-top:70px;margin-bottom: 50px; text-align:center; '>Cannot find!</div>";
	  						}
		  	        	}else if(response.status === "Playlist"){
		  	        		document.getElementById("result_search").innerHTML = "";
		  	        		var count_song_pll = 0;
	  						for (var key in response.songPll) {
	  			  	        	if (response.songPll.hasOwnProperty(key)) {
	  			  	        		count_song_pll++;
	  			  	        	}
	  			  	        }
	  						var count_pod_pll = 0;
	  						for (var key in response.podPll) {
	  			  	        	if (response.podPll.hasOwnProperty(key)) {
	  			  	        		count_pod_pll++;
	  			  	        	}
	  			  	        }
	  						var sum_all =  count_song_pll + count_pod_pll;
	  						if(sum_all > 0){
	  							var inner_result = document.getElementById("result_search");
		  						if(count_song_pll > 0){
	  								inner_result.innerHTML = inner_result.innerHTML + "<h2>Song Playlist</h2>";
		  							for(var i = 0 ; i < count_song_pll ; i++){
		  								inner_result.innerHTML = inner_result.innerHTML + "<div class='song' id='"+response.songPll[i].id+"' onclick=\"open_playlist(this.id,'"+response.songPll[i].img+"','"+response.songPll[i].name+"','"+response.songPll[i].script+"')\"><img class='img_song' src=\""+response.songPll[i].img+"\"><div class='info'><h3 class='name_song'>"+response.songPll[i].name+"<p class='descript_pl'>"+response.songPll[i].script+"</p></div><div class='wrap_play_pl_btn'><button class='play_pl_btn'><img class='play_pl_img' src='images/play-button-arrowhead.png' alt=''></button></div></div>";
		  				  			}
		  						}
	  							if(count_pod_pll > 0){
	  								inner_result.innerHTML = inner_result.innerHTML + "<h2>Podcast Playlist</h2>";
		  							for(var i = 0 ; i < count_pod_pll ; i++){ 
		  								inner_result.innerHTML = inner_result.innerHTML + "<div class='song' id='"+response.podPll[i].id+"' onclick=\"open_playlist(this.id,'"+response.podPll[i].img+"','"+response.podPll[i].name+"','"+response.podPll[i].script+"')\"><img class='img_song' src=\""+response.podPll[i].img+"\"><div class='info'><h3 class='name_song'>"+response.podPll[i].name+"<p class='descript_pl'>"+response.podPll[i].script+"</p></div><div class='wrap_play_pl_btn'><button class='play_pl_btn'><img class='play_pl_img' src='images/play-button-arrowhead.png' alt=''></button></div></div>";
		  				  			}
		  						}
	  						}else{
	  							document.getElementById("result_search").innerHTML = "<div style='margin-top:70px;margin-bottom: 50px; text-align:center; '>Cannot find!</div>";
	  						}
		  	        	}
		  			}else if(response.error === "none"){
		  				console.log("");
		  				document.getElementById("result_search").innerHTML = "";
		  			}
	  			},
	  			error : function(xhr, status, error){
		  	  		document.getElementById("wrap_out_mes_error").style.display = "flex";
	  			},
	  		});
	  	};
	  	function OpenArtist(ID, img, name){
	  		var getpl = "openartist";
	  		var id = ID;
	  	    $.ajax({
	  	        url: "Get_playlist",
	  	        type: "POST",
	  	        data: {
	  	        	getPL: getpl,
	  	        	ID_art : id,
	  	        },
	  	        success: function(response) {
	  	        	var count = 0;
	  	        	var suf = response.suff;
	  	        	var like = response.like;
	  	        	for (var key in response.song) {
	  	        	if (response.song.hasOwnProperty(key)) {
	  	        	        count++;
	  	        	    }
	  	        	}
		        	document.getElementById("wrap_all_search").style.display="none";
	  	        	document.getElementById("see_more_pll_wrap").style.display="none";
	  	        	document.getElementById("pl_inside").style.display="block";
	  	        	document.getElementById("see_more_pll_wrap").innerHTML ="";
	  	        	document.getElementById("wrap_cre_pll").innerHTML = "";
	  	        	document.getElementById("Home").style.display="none";
	  	        	document.getElementById("account").style.display="none";
	  	        	document.getElementById("wrap_change_image").style.display="none";
	  		  		document.getElementById("pl_inside").innerHTML = "<div class='wrap_pl_inf_head'><div class='pl_img_head'><img class='img_head' src='"+img+"'><div class='pl_inf_head'><p class='pl_name_head'>"+name+"</p></div></div><div class='wrap_all_be_pll'><button class='play_pl_but'><img style='margin-left: 3px; margin-top:3px' width='23px' src='images/play-button-arrowhead.png' alt=''></button></div></div><div style='background-color: white; height:2px; margin:2%;'></div>";
	  		  		for(var i = 0; i < count; i++){
		  		  		var c = i + 1;
	  		  			if(response.song[i].like === "true"){
	  		  				document.getElementById("pl_inside").innerHTML = document.getElementById("pl_inside").innerHTML + "<div class='song'><div id='"+response.song[i].ID+"' onclick=\"playSongchoo(this.id,'"+id+"','"+response.song[i].img+"','"+response.song[i].name+"','"+response.song[i].per+"', 'artist')\" class='img_name_song'><div class='num'><p>"+c+"</p></div><img class='img_song' src='"+response.song[i].img+"' alt=''><div class='info'><p class='name_song'>"+response.song[i].name+"</p><p class='author'>"+response.song[i].per+"</p></div></div><button id='DLSID_"+response.song[i].ID+"' onclick=\"Likesong(this.id, 'LSID_"+response.song[i].ID+"','dislike','"+response.song[i].ID+"')\" style='display:block;' class='like_btn'><img style='width:23px;height:23px;' src='images/like_gr.png'></button><button id='LSID_"+response.song[i].ID+"' onclick=\"Likesong(this.id, 'DLSID_"+response.song[i].ID+"','like','"+response.song[i].ID+"')\" style='display:none;' class='like_btn'><img style='width:23px;height:23px;' src='images/like.png'></button></div></div>";
	  		  			}else if(response.song[i].like === "false"){
	  		  				document.getElementById("pl_inside").innerHTML = document.getElementById("pl_inside").innerHTML + "<div class='song'><div id='"+response.song[i].ID+"' onclick=\"playSongchoo(this.id,'"+id+"','"+response.song[i].img+"','"+response.song[i].name+"','"+response.song[i].per+"','artist')\" class='img_name_song'><div class='num'><p>"+c+"</p></div><img class='img_song' src='"+response.song[i].img+"' alt=''><div class='info'><p class='name_song'>"+response.song[i].name+"</p><p class='author'>"+response.song[i].per+"</p></div></div><button id='DLSID_"+response.song[i].ID+"' onclick=\"Likesong(this.id,'LSID_"+response.song[i].ID+"', 'dislike','"+response.song[i].ID+"')\" style='display:none;'  class='like_btn'><img style='width:23px;height:23px;' src='images/like_gr.png'></button><button id='LSID_"+response.song[i].ID+"' onclick=\"Likesong(this.id, 'DLSID_"+response.song[i].ID+"', 'like','"+response.song[i].ID+"')\" style='display:block;' class='like_btn'><img style='width:23px;height:23px;' src='images/like.png'></button></div></div>";
	  		  			}
	  		  		};
	  		  		document.getElementById("wrap_out_ct").scrollTo(0,0);
	  	        },
	  	        error: function(xhr, status, error) {
		  	  		document.getElementById("wrap_out_mes_error").style.display = "flex";
	  	        }
	  	    });
	  	};
	  	
	  	function Likesong(ID, ID_chng, stt, song_ID ){
	  		var SONG = ""+song_ID;
	  		var btn = document.getElementById(ID);
	  		var btn_chang = document.getElementById(ID_chng);
	  		$.ajax({
	  			url:"Get_playlist",
	  			type:"POST",
	  			data:{
	  				getPL : "love_med",
	  				ID : SONG,
					stat : stt,
	  			},
	  			success : function(response){
	  				btn.style.display= "none";
	  				btn_chang.style.display="block";
	  				console.log(response.ispl);
	  				if(response.ispl == "true"){
	  					if(response.stat_song == "like"){
	  						document.getElementById("heart_btn").style.display = "block";
		            		document.getElementById("no_heart_btn").style.display = "none";
	  					}else if(response.stat_song == "dislike"){
	  						document.getElementById("heart_btn").style.display = "none";
			            	document.getElementById("no_heart_btn").style.display = "block";
	  					}
	  					
	  				}
	  			},
	  			error: function(xhr, status, error){
		  	  		document.getElementById("wrap_out_mes_error").style.display = "flex";
		  	  	},
	  		});
	  	}
	  	
	  	function LikeonPlay(status, this_elem_dis , elem_app){
	  		$.ajax({
	  			url:"Get_playlist",
	  			type:"POST",
	  			data:{
	  				getPL : "BehaOnPlay",
	  				status: status,
	  			},
	  			success: function(response){
	  				if(response.error === "true"){
	  					document.getElementById("wrap_out_mes_error").style.display = "flex";
	  				}else if(response.error === "false"){
	  					try{
	  					document.getElementById(elem_app).style.display= "block";
	  					document.getElementById(this_elem_dis).style.display = "none";
	  					document.getElementById(response.App).style.display = "block";
	  					document.getElementById(response.DisApp).style.display = "none";
	  					}
	  					catch(err) {
	  					}
	  				}
	  			},
	  			error: function(xhr, status, error){
		  	  		document.getElementById("wrap_out_mes_error").style.display = "flex";
		  	  	},
	  		});
	  	}
	  	
	  	function seeMore(type){
	  		var wrap_see_more = document.getElementById("see_more_pll_wrap");
	  		var sty = type;
	  		$.ajax({
	  			url:"Get_playlist",
	  			type:"POST",
	  			data:{
	  				getPL : "SeeMore",
	  				TYPE:type,
	  			},
	  			success: function (response){
		  			var count = 0;
		        	document.getElementById("wrap_all_search").style.display="none";
		  			document.getElementById("see_more_pll_wrap").style.display="block";
	  	        	document.getElementById("pl_inside").style.display="none";
		  			document.getElementById("pl_inside").innerHTML = "";
		  			document.getElementById("wrap_cre_pll").innerHTML = "";
		        	document.getElementById("Home").style.display="none";
		        	document.getElementById("account").style.display="none";
		        	document.getElementById("wrap_change_image").style.display="none";
		        	if(response.pll === "Artist"){
		  				for (var key in response.artist) {
			  	        	if (response.artist.hasOwnProperty(key)) {
			  	        	        count++;
			  	        	    }
			  	        }
		  				wrap_see_more.innerHTML = wrap_see_more.innerHTML + "<h2>"+response.pll+"</h2><div id = 'wrap_all_pp_seemore' class='wrap_all_pp_seemore'><div></div>";
		  				var wrap_pll_see = document.getElementById("wrap_all_pp_seemore");
		  				for(var i = 0; i < count; i++){
		  					wrap_pll_see.innerHTML = wrap_pll_see.innerHTML + "<div class='artist_list' id='"+response.artist[i].ID+"' onclick=\"OpenArtist(this.id,'"+response.artist[i].img+"','"+response.artist[i].name+"')\"><img class='img_artist' src=\""+response.artist[i].img+"\"><div class='info_artist'><h3 class='name_artist'>"+response.artist[i].name+"</div></div>";
		  				}														
					}else{
						for (var key in response.playlist) {
			  	        	if (response.playlist.hasOwnProperty(key)) {
			  	        	        count++;
			  	        	    }
			  	        }
		  				wrap_see_more.innerHTML = wrap_see_more.innerHTML + "<h2>"+response.pll+"</h2><div id = 'wrap_all_pp_seemore' class='wrap_all_pp_seemore'><div></div>";
		  				var wrap_pll_see = document.getElementById("wrap_all_pp_seemore");
		  				for(var i = 0; i < count; i++){
		  					wrap_pll_see.innerHTML = wrap_pll_see.innerHTML + "<div class='pl_list' id='"+response.playlist[i].ID+"' onclick=\"open_playlist(this.id,'"+response.playlist[i].img+"','"+response.playlist[i].name+"','"+response.playlist[i].script+"')\"><img class='img_pl' src=\""+response.playlist[i].img+"\"><div class='info_pl_l'><h3 class='name_pl'>"+response.playlist[i].name+"<p class='descript_pl'>"+response.playlist[i].script+"</p></div><div class='wrap_play_pl_btn'><button class='play_pl_btn'><img class='play_pl_img' src='images/play-button-arrowhead.png' alt=''></button></div></div>";
		  				}
		        	};
		  			document.getElementById("wrap_out_ct").scrollTo(0,0);
	  			},
	  			error: function(xhr, status, error){
		  	  		document.getElementById("wrap_out_mes_error").style.display = "flex";
		  	  	},
	  		});
	  	};
	  	document.addEventListener('keydown', (event) => {
            var name = event.key;
            if(name === "MediaTrackNext"){
            	nextsong();
            }else if(name === "MediaTrackPrevious"){
            	previoussong();
            }
        }, false);
		
	  	function CreatePlaylist(){
	  		var Playlist_name = document.getElementById("cre_pll_name").value;
	  		$.ajax({
	  			url:"Get_playlist",
	  			type:"POST",
	  			data:{
	  				getPL : "Create_playlist",
	  				PLL_name : Playlist_name
	  			},
	  			success: function(response){
	  				if(response.error === "none"){
	  					document.getElementById("cre_pll_name").value = "";
	  					if(response.stt === "success"){
	  		  	        	document.getElementById("wrap_cre_pll").style.display = "block";
	  						var inside_cre_pll = document.getElementById("wrap_cre_pll");
	  		  	        	inside_cre_pll.innerHTML = "";
	  						document.getElementById("cre_pll_name").value = "";
		  					document.getElementById("wrap_out_cre_pll").style.display="none";
		  					document.getElementById("head_cre_ctn").innerText = "create playlist successful!";
		  					document.getElementById("wrap_out_cre_nof").style.display="flex";
	  						var lis_lib = document.getElementById("lis_lib_left");
		  			  		lis_lib.innerHTML = lis_lib.innerHTML + "<div id='"+response.ID_CRE+"' class='pl_left' onclick=\"open_cre_playlist(this.id, '"+response.img_path+"', '"+response.Pll_name+"', '"+response.Pll_num+"')\"><div class='wrap_img_pl_left'><img src=\""+response.img_path+"\" class='img_pl_left'></div><div class='wrap_info_pl_list_left'><p class='pl_name_left'>"+response.Pll_name+"</p><p class='pl_scr_left'>"+response.Pll_num+"</p></div></div>";
	  						document.getElementById("wrap_all_search").style.display="none";
	  		  	        	document.getElementById("see_more_pll_wrap").style.display="none";
	  		  	        	document.getElementById("pl_inside").style.display="none";
	  		  	        	document.getElementById("see_more_pll_wrap").innerHTML ="";
	  		  	        	document.getElementById("Home").style.display="none";
	  		  	        	document.getElementById("account").style.display="none";
	  		  	        	document.getElementById("wrap_change_image").style.display="none";
	  		  	        	inside_cre_pll.innerHTML = inside_cre_pll.innerHTML + "<div class=\"wrap_pl_inf_head\"><div class=\"pl_img_head\"><img class=\"img_head\" src=\""+ response.img_path +"\"><div class=\"pl_inf_head\"><p class=\"pl_name_head\">"+ response.Pll_name +"</p><p class=\"pl_script_head\">"+ response.Pll_num +"</p></div></div><div class=\"wrap_all_be_pll\"><button class=\"play_pl_but\"><img style=\"margin-left: 3px; margin-top:3px\" width=\"23px\" src=\"images/play-button-arrowhead.png\" alt=''></button> <button class=\"del_crepll_btn\" onclick=\"DEL_CRE_PLL('"+response.ID_CRE+"')\" id=\"del_crepll_btn\"><img class=\"del_cre_pll_img\" src=\"images/delete.png\" alt=''></button></div></div>";
	  		  	        	inside_cre_pll.innerHTML = inside_cre_pll.innerHTML + "<div class=\"op_behave\"><button id=\"behave_playlist\" onclick=\"display_cre_behave('PLL_CRE')\" class=\"behave_playlist\">Playlist</button><button id=\"behave_add\" onclick=\"display_cre_behave('ADD_CRE')\" class=\"behave_add\">Add</button></div><div style=\"background-color: white; height:2px; margin-bottom:2%;\"></div><div class=\"wrap_list_cre\" id=\"wrap_list_cre\" style=\"display:none\"><div class=\"wrap_med_cre\" id=\"wrap_med_cre\">       </div></div>";
	  		  	        	inside_cre_pll.innerHTML = inside_cre_pll.innerHTML + "<div class=\"wrap_add_cre\" id=\"wrap_add_cre\"><div class=\"wrap_search_field_cre\"><input id=\"search_field_cre\" class=\"search_field_cre\" type=\"text\" placeholder=\"Find song you want to add!\" ><button class=\"search_btn_cre\" onclick=\"SEARCH_TO_CRE('"+response.ID_CRE+"')\" id=\"search_btn_cre\">Find</button></div><div class=\"wrap_res_search_cre\" id=\"wrap_res_search_cre\">    </div></div>";
	  						
	  					}else if(response.stt === "unsuccess"){
	  						document.getElementById("cre_pll_name").value = "";
		  					document.getElementById("wrap_out_cre_pll").style.display="none";
		  					document.getElementById("head_cre_ctn").innerText = "Cannot create playlist!";
		  					document.getElementById("wrap_out_cre_nof").style.display="flex";
	  					}
	  				}else if(response.error === "true"){
			  	  		document.getElementById("wrap_out_mes_error").style.display = "flex";
	  				}else if(response.error === "caution1"){
	  					document.getElementById("cre_pll_name").value = "";
	  					document.getElementById("wrap_out_cre_pll").style.display="none";
	  					document.getElementById("head_cre_ctn").innerText = "Playlist's name cannot be empty!";
	  					document.getElementById("wrap_out_cre_nof").style.display="flex";
	  				}else if(response.error === "caution2"){
	  					document.getElementById("cre_pll_name").value = "";
	  					document.getElementById("wrap_out_cre_pll").style.display="none";
	  					document.getElementById("head_cre_ctn").innerText = "Playlist's name too short!";
	  					document.getElementById("wrap_out_cre_nof").style.display="flex";
	  				}else if(response.error === "caution3"){
	  					document.getElementById("cre_pll_name").value = "";
	  					document.getElementById("wrap_out_cre_pll").style.display="none";
	  					document.getElementById("head_cre_ctn").innerText = "Playlist's name too long!";
	  					document.getElementById("wrap_out_cre_nof").style.display="flex";
	  				}
	  			},
	  			error: function(){
		  	  		document.getElementById("wrap_out_mes_error").style.display = "flex";
		  	  	},
	  		});
	  	}
	  	function open_cre_playlist(ID_CRE, IMG, NAME, SCRIPT){
			var inside_cre_pll = document.getElementById("wrap_cre_pll");
	  		$.ajax({
	  			url:"Get_playlist",
	  			type:"POST",
	  			data:{
	  				getPL : "GETPLAYLIST",
	  				ID_playlist: ID_CRE
	  			},
	  			success:function(response){
	  				var count = 0;
	  	        	for (var key in response.song) {
	  	        	if (response.song.hasOwnProperty(key)) {
	  	        	        count++;
	  	        	    }
	  	        	}
	  	        	inside_cre_pll.innerHTML = "";
	  	        	inside_cre_pll.style.display ="block";
					document.getElementById("cre_pll_name").value = "";
  					document.getElementById("wrap_out_cre_pll").style.display="none";
					document.getElementById("wrap_all_search").style.display="none";
	  	        	document.getElementById("see_more_pll_wrap").style.display="none";
	  	        	document.getElementById("pl_inside").style.display="none";
	  	        	document.getElementById("see_more_pll_wrap").innerHTML ="";
	  	        	document.getElementById("Home").style.display="none";
	  	        	document.getElementById("account").style.display="none";
	  	        	document.getElementById("wrap_change_image").style.display="none";
	  	        	inside_cre_pll.innerHTML = inside_cre_pll.innerHTML + "<div class=\"wrap_pl_inf_head\"><div class=\"pl_img_head\"><img class=\"img_head\" src=\""+ IMG +"\"><div class=\"pl_inf_head\"><p class=\"pl_name_head\">"+ NAME +"</p><p class=\"pl_script_head\">"+ SCRIPT +"</p></div></div><div class=\"wrap_all_be_pll\"><button class=\"play_pl_but\"><img style=\"margin-left: 3px; margin-top:3px\" width=\"23px\" src=\"images/play-button-arrowhead.png\" alt=''></button> <button class=\"del_crepll_btn\" onclick=\"DEL_CRE_PLL('"+ID_CRE+"')\" id=\"del_crepll_btn\"><img class=\"del_cre_pll_img\" src=\"images/delete.png\" alt=''></button></div></div>";
	  	        	inside_cre_pll.innerHTML = inside_cre_pll.innerHTML + "<div class=\"op_behave\"><button id=\"behave_playlist\" onclick=\"display_cre_behave('PLL_CRE')\" class=\"behave_playlist\">Playlist</button><button id=\"behave_add\" onclick=\"display_cre_behave('ADD_CRE')\" class=\"behave_add\">Add</button></div><div style=\"background-color: white; height:2px; margin-bottom:2%;\"></div><div class=\"wrap_list_cre\" id=\"wrap_list_cre\" style=\"display:block\"><div class=\"wrap_med_cre\" id=\"wrap_med_cre\">       </div></div>";
	  	        	inside_cre_pll.innerHTML = inside_cre_pll.innerHTML + "<div class=\"wrap_add_cre\" style=\"display:none\" id=\"wrap_add_cre\"><div class=\"wrap_search_field_cre\"><input id=\"search_field_cre\" class=\"search_field_cre\" type=\"text\" placeholder=\"Find song you want to add!\" ><button class=\"search_btn_cre\" onclick=\"SEARCH_TO_CRE('"+ID_CRE+"')\" id=\"search_btn_cre\">Find</button></div><div class=\"wrap_res_search_cre\" id=\"wrap_res_search_cre\">    </div></div>";
					var list_song_in_cre = document.getElementById("wrap_med_cre");
	  	        	for(var i = 0; i < count; i++){
	  		  			var c = i + 1;
	  		  			list_song_in_cre.innerHTML = list_song_in_cre.innerHTML + "<div id='"+response.song[i].ID+"'  class=\"song\"><div class=\"img_name_song\"><div class='num'><p>"+c+"</p></div><img class=\"img_song\" src=\""+response.song[i].img+"\" alt=''><div class=\"info\" id='"+response.song[i].ID+"' onclick = \"playSongchoo(this.id,'"+ID_CRE+"','"+response.song[i].img+"','"+response.song[i].name+"','"+response.song[i].per+"', 'playlist')\"><p class=\"name_song\">"+ response.song[i].name +"</p><p class=\"author\">"+ response.song[i].per +"</p></div></div><button width=\"100px\" id=\"remove_btn_cre\" class=\"remove_btn_cre\" onclick = \"Custom_cre('"+response.song[i].ID+"','"+ID_CRE+"','DEL')\" ><img class=\"remove_cre_img\" src=\"images/remove.png\" alt=''></button></div>";
	  		  		};
	  		  		document.getElementById("wrap_out_ct").scrollTo(0,0);
	  			},
	  			error:function(){
		  	  		document.getElementById("wrap_out_mes_error").style.display = "flex";
	  			}
	  		});
	  	}
	  	
	  	function DEL_CRE_PLL(ID_CRE){
	  		$.ajax({
	  			url: "Get_playlist",
	  			type: "POST",
	  			data:{
	  				getPL : "DEL_CRE_PLL",
	  				ID_TO_DEL : ID_CRE
	  			},
	  			success : function (response){
	  				if(response.error === "true"){
	  					document.getElementById("cre_pll_name").value = "";
	  					document.getElementById("wrap_out_cre_pll").style.display="none";
	  					document.getElementById("head_cre_ctn").innerText = "Cannot delete playlist!";
	  					document.getElementById("wrap_out_cre_nof").style.display="flex";
	  				}else if(response.error === "false"){
	  					var lis_lib = document.getElementById("lis_lib_left");
		  			  	for (var i = 0; i < lis_lib.children.length; i++) {
	  			  			if(lis_lib.children[i].id === ID_CRE){
	  			  				lis_lib.removeChild(lis_lib.children[i]);
	  			  				break;
	  			  			}
			  		  	}
		  			  	appearHome();
	  					document.getElementById("cre_pll_name").value = "";
	  					document.getElementById("wrap_out_cre_pll").style.display="none";
	  					document.getElementById("head_cre_ctn").innerText = "Delete success!";
	  					document.getElementById("wrap_out_cre_nof").style.display="flex";
	  				}
	  			},
	  			error : function(){
		  	  		document.getElementById("wrap_out_mes_error").style.display = "flex";
	  			}
	  		});
	  	}
	  	
	  	function SEARCH_TO_CRE(ID_CRE){
	  		var req = document.getElementById("search_field_cre").value;
	  		var res_wrap = document.getElementById("wrap_res_search_cre");
	  		$.ajax({
	  			url : "Get_playlist",
	  			type : "POST",
	  			data : {
	  				getPL : "SEARCH_TO_CRE",
	  				ID_TO_ADD :  ID_CRE,
	  				SEARCH_REQ : req
	  			},
	  			success : function(response){
	  				if(response.error === "false"){
	  					var count = 0;
		  	        	for (var key in response.song) {
		  	        	if (response.song.hasOwnProperty(key)) {
		  	        	        count++;
		  	        	    }
		  	        	}
	  					if(count > 0){
	  						res_wrap.innerHTML = "";
	  						var i = 0;
	  						for(i = 0 ; i < count; i++){
	  							res_wrap.innerHTML = res_wrap.innerHTML + "<div class='song' id='"+response.song[i].ID+"'><div  class='img_name_song'><div class='num'></div><img class='img_song' src='"+response.song[i].img+"' alt=''><div class='info'><p class='name_song'>"+response.song[i].name+"</p><p class='author'>"+response.song[i].per+"</p></div></div>     <button onclick=\"Custom_cre('"+response.song[i].ID+"', '"+ID_CRE+"' ,'ADD')\" width=\"100px\" class=\"add_btn_cre\"><img class=\"add_cre_img\" src=\"images/plus.png\" alt=''></button>      </div></div>";
	  						}
	  					}else{
	  						res_wrap.innerHTML = "";
	  						res_wrap.innerHTML = res_wrap.innerHTML + "<div style=\"display:flex; justify-content:center;margin:5%;\"><p style=\"font-size: 16px; color:white;; text-align:center;\">Cannot find any result</p> ";
	  					}
	  				}else if(response.error === "true"){
	  					document.getElementById("wrap_out_mes_error").style.display = "flex";
	  				}
	  				else if(response.error === "caution"){
	  					res_wrap.innerHTML = "";
	  				}
	  			},
	  			error : function(error){
  					document.getElementById("wrap_out_mes_error").style.display = "flex";
	  			}
	  		});
	  	}
	  	
	  	function Custom_cre(id_song, id_cre_pll, option){
	  		$.ajax ({
	  			url : "Get_playlist",
	  			type : "POST",
	  			data : {
	  				getPL : "CUSTOM_CRE",
	  				SongID : id_song,
	  				CreID : id_cre_pll,
	  				Option : option
	  			},
	  			success : function(response){
	  				if(response.error === "false"){
  						var cre = document.getElementById("wrap_med_cre");
	  					if(response.status === "DEL"){
			  			  	for (var i = 0; i < cre.children.length; i++) {
		  			  			if(cre.children[i].id === id_song){
		  			  				cre.removeChild(cre.children[i]);
		  			  				break;
		  			  			}
				  		  	}
	  					}else if(response.status === "ADD"){
	  				  		var res_wrap = document.getElementById("wrap_res_search_cre");
	  						for (var i = 0; i < res_wrap.children.length; i++) {
		  			  			if(res_wrap.children[i].id === id_song){
		  			  				res_wrap.removeChild(res_wrap.children[i]);
		  			  				break;
		  			  			}
				  		  	}
	  						var count = cre.children.length+1;
		  		  			cre.innerHTML = cre.innerHTML + "<div id='"+response.med_id+"' class=\"song\"><div class=\"img_name_song\"><div class='num'><p>"+count+"</p></div><img class=\"img_song\" src=\""+response.med_img+"\" alt=''><div class=\"info\" id='"+response.med_id+"' onclick = \"playSongchoo(this.id,'"+id_cre_pll+"','"+response.med_img+"','"+response.med_name+"','"+response.performer+"', 'playlist')\"><p class=\"name_song\">"+ response.med_name +"</p><p class=\"author\">"+ response.performer +"</p></div></div><button width=\"100px\" id=\"remove_btn_cre\" class=\"remove_btn_cre\" onclick = \"Custom_cre('"+response.med_id+"','"+id_cre_pll+"','DEL')\"><img class=\"remove_cre_img\" src=\"images/remove.png\" alt=''></button></div>";
	  					}
	  				}else if(response.error === "true"){
	  	  				document.getElementById("wrap_out_mes_error").style.display = "flex";
	  				}
	  			},
	  			error : function(){
		  	  		document.getElementById("wrap_out_mes_error").style.display = "flex";
	  			}
	  		});
	  	}
	  	
  	 	function display_cre_behave(request){
               var add = document.getElementById('wrap_list_cre');
               var pll = document.getElementById('wrap_add_cre');
               switch (request){
                   case "PLL_CRE":
                   	   add.style.display = "block";
                       pll.style.display = "none"; 
                       break;
                   
                   case "ADD_CRE":
	                   add.style.display = "none";
	                   pll.style.display = "block"; 
                       break;

                   default :

                       break;
               }
          }
    	</script>
    	
</body>
</html>