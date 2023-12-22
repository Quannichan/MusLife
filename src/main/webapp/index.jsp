<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, Script.Media , Script.Playlist" %>
<% if(request.getSession().getAttribute("MAIL") != null && request.getSession().getAttribute("PASS") != null){
	request.getSession().setAttribute("tove",null);
	request.getSession().setAttribute("ve",null);
	request.getSession().setAttribute("mail",null);
	request.getSession().setAttribute("pass",null);
	request.getSession().setAttribute("usname",null); 
	request.getSession().setAttribute("upload",null);
	request.getSession().setAttribute("CP",null);
	response.sendRedirect("Home.jsp");
}
request.getSession().setAttribute("tove",null);
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
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link rel="stylesheet" href="CSS/index.css">
    <link href="https://fonts.googleapis.com/css2?family=Ubuntu:wght@500&display=swap" rel="stylesheet">
    <title>Home | Muslife</title>
</head>
<body>
        <form style="display:none;"id="get" action="Get_playlist" method="post">
		<input type="hidden" name="getPL" value="GET">
		</form>
    <div class="wrap_ct1">
        <div class="Wrap_left">
            <div class="wrap_option">
                <div class="option">
                    <ul class="op_list">
                        <li class="list"><img width="20px" style="margin-right: 20px;margin-bottom:-1px;" src="images/home.png"><a class="link" href="index.jsp">Home</a></li>
                        <li class="list"><img width="20px" style="margin-right: 20px;margin-bottom:-1px;" src="images/search.png"><a class="link" style="cursor:pointer;" onclick="window.location.href='Login.jsp'">Search</a></li>
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
                            
                        </div>
                    </div>
                    <br>
                <div class="lis_lib">
                    <div class="wrap_cr_playlist">
                        <div class="cr_playlist">
                            <div>
                                <h4 style="font-size: 20px;margin-bottom: 5px;">Create your playlist!</h4>
                                <p style="font-size: 14px;margin-bottom: 15px;">Log in to create your own playlist</p>
                            </div>
                            <div>
                                <button onclick="window.location.href='Login.jsp'" class="cr_pl_btn">Create Playlist</button>
                            </div>
                        </div>
                    </div>
                    <div class="wrap_find_podcast">
                        <div class="find_podcast">
                            <div>
                                <h4 style="font-size: 20px;margin-bottom: 5px;">Follow your favourite podcast!</h4>
                                <p style="font-size: 14px;margin-bottom: 15px;">Log in to follow your your favourite podcast</p>
                            </div>
                            <div>
                                <button onclick="window.location.href='Login.jsp'" class="fl_pc_btn">
                                    Log in
                                </button>
                            </div>
                        </div>
                    </div>
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
                                <p id="name_song_play" class="name_song_play"></p>
                                <p id="author_play" class="author_play"></p>
                            </div>
                        </div>
                        <div class="wrap_media_playing">
                            <div class="media">
                                <button class="pr_son_btn" id="pr_son_btn"><img class="pr_son" src="images/previous_but.png" alt=""></button>
                                <button class="play_son_btn" id="play_son_btn"><img class="play_son" src="images/play-button-arrowhead.png" alt=""></button>
                                <button id="pause_son_btn" class="pause_son_btn" style="display:none;"><img class="pause_son" src="images/pause.png" alt=""></button>
                                <button class="next_son_btn" id="next_son_btn"><img class="next_son" src="images/next-button.png" alt=""></button>
                            </div>
                            <div class="progres">
                                <p id="start" class="start">00:00</p>
                                <input min="0" max="100" value="0" disabled type="range" id="pro_song" class="pro_song" >
                                <p id="end" class="end">00:00</p>
                            </div>
                        </div>
                        <div class="behavior">
                            <button id="heart_btn" class="heart_btn">
                            <img class="heart" src="images/like.png">
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
                            	<img class="logo" onclick="window.location='index.jsp'" src="images/logo.png" alt="logo web">
                            </div>
                            <div class="menu_but">
                                <ul class="list_wrap">
                                    <li class="Signup"><a href="Signup.jsp" class="link_sign">Sign up</a></li>
                                    <li class="Login"><a href="Login.jsp"class="link_log">Log in</a></li>
                                </ul>
                            </div>
                        </div>
                    </div> 
                </div>
                <div class="wrap_out_ct">
                    <div class="wrap_ct">
                        <div class="ct">
                        <%if(request.getSession().getAttribute("Playlists")== null) {%>
                        <div class="wrap_loader">
                            <div class="custom-loader"></div>
                         </div>
                            <%}else{%>
                            	<div>
                            	<div class="wrap_header_pl">
                                <h2>Muslife Playlist</h2>
                                <div class="wrap_all_pl1">
                            		<%ArrayList<Playlist> pll = new ArrayList<Playlist>(); 
                            		pll = (ArrayList<Playlist>)request.getSession().getAttribute("Playlists");
                            		int count = 0;
                            		for(int i = 0; i < pll.size(); i++){
                            			if(pll.get(i).getPl_type().equals("song")){
                            			count+=1;%>
                            			<div class="pl_list" onclick="window.location.href='Login.jsp'">
                                            <img class="img_pl" src="<%=pll.get(i).getPl_img_path()%>">
                                        <div class="info_pl_l">
                                            <h3 class="name_pl"><%=pll.get(i).getPl_name()%></h3>
                                            <p class="descript_pl"><%=pll.get(i).getPl_script()%></p>
                                        </div>
                                    </div>
                            			<%if(count == 4){
                            				break;
	                            		}else{
	                            			
	                            		}
                            		}
                            	}%>
                                </div>
                                </div>
                                <div class="wrap_header_pl">
                                <h2>Muslife Podcast</h2>
                                <div class="wrap_all_pl1">
                            		<%int count1  =0;
                            		for(int i = 0; i < pll.size();i++){
                            			if(pll.get(i).getPl_type().equals("podcast")){
                            			count1+=1;%>
                            			<div class="pl_list" onclick="window.location.href='Login.jsp'">
                                            <img class="img_pl" src="<%=pll.get(i).getPl_img_path() %>">
                                        <div class="info_pl_l">
                                            <h3 class="name_pl"><%=pll.get(i).getPl_name() %></h3>
                                            <p class="descript_pl"><%=pll.get(i).getPl_script() %></p>
                                        </div>
                                    </div>
	                            		<%	if(count1 ==4){
	                            				break;
		                            		}else{
		                            			
		                            		}
                            			}
                            		}%>
                                </div>
                                </div>
                            	</div>
                            <%} %>
                            <div class="line"></div>
                                <div class="Wrap_footer">
                                    <div class="footer1">
                                        <a class="link_footer" href="index.jsp">Home</a><br>
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

        <script>
        
        if (performance.navigation.type == performance.navigation.TYPE_RELOAD) {
        	document.getElementById("get").submit();
        	
        } else {
				console.log("");
        }
	    <%if(request.getSession().getAttribute("count") != null) {%>
	    <%if(Integer.parseInt(request.getSession().getAttribute("count").toString()) < 1 ) {%>
	    		setTimeout(7000);
		        document.getElementById("get").submit();
	    	<%}else{%>
		        
	    	<%} %>
	    <%}else{%>
		        setTimeout(7000);
		        document.getElementById("get").submit();
	    <%} %>	
    	</script>
    	
</body>
</html>