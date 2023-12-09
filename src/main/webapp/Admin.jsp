<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*, Script.Playlist_Create,Script.Playlist, Script.Performer" %>
<%if(request.getSession().getAttribute("MAIL") != null && request.getSession().getAttribute("PASS") != null){
    if(request.getSession().getAttribute("MAIL").toString().equals("AdminPageMusLife@gmail.com") && request.getSession().getAttribute("PASS").toString().equals("876b97c826857505ab230e51a741c259") ){
    
    }else{
    	response.sendRedirect("Login.jsp");
    }
}else{
	response.sendRedirect("Login.jsp");
}%>
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
    <link rel="shortcut icon" href="images/icon.png">
    <link rel="stylesheet" href="CSS/admin.css">
    <link rel="stylesheet" href="CSS/index.css">
    <title>MusLife | Admin</title>
</head>
<body>
    <!-- menu -->
    <div class="wrap_admin">
        <div class="wrap_admin_menu">
            <div class="wrap_adminlogo">
                <img onclick="Apprear('HOME')" class="admin_logo" src="images/logo.png" alt="">
            </div>
            <div class="wrap_choose">
                <ul class="wrap_ul">
                    <li class="choose" onclick="Apprear('MEDIA')">Medias</li>
                    <li class="choose" onclick="Apprear('PLL')">Playlists</li>
                    <li class="choose" onclick="Apprear('ART')">Artists</li>
                    <li class="choose" onclick="Apprear('CATE')">Categories</li>
                    <li class="choose" onclick="Apprear('USER')">Users</li>
                    <li class="choose" id="logout" onclick="logout()">Log Out</li>
                </ul>
            </div>
        </div>
    

        <!-- content_home -->
        <div class="wrap_home_admin" id="HOME">
            <div class="wrap_ct_homeAdmin">
                <h1 class="head_ct_home_ad">Welcome To MusLife Admin</h1>
                <p class="text_ct_home_ad">Welcome back to MusLife manage place!</p>
                <div class="wrap_btn_home_ad">
                    <button onclick="Apprear('MEDIA')" class="btn_home">Medias</button>
                    <button onclick="Apprear('PLL')"  class="btn_home">Playlists</button>
                    <button onclick="Apprear('ART')"  class="btn_home">Artists</button>
                    <button onclick="Apprear('CATE')"  class="btn_home">Categories</button>
                    <button onclick="Apprear('CATE')"  class="btn_home">Users</button>
                </div>
            </div>
            <div class="wrap_home_img_ad">
                <div class="wrap_home_img_3d">
                    <img class="home_img_ad" src="images/logo.png" alt="">
                </div>
            </div>
            
        </div>



        <!-- content Song -->
        <div class="wrap_song_behave" id="MEDIA" style="display: none;">
            <div class="song_behave">
                <div class="wrap_song_list_left">

                    <div class="song_list_left_head">
                        <h2 class="head_list_left">Media</h2>
                    </div>
                    <div class="wrap_search_song">
                        <input type="text" class="search_song" id="search_song" placeholder="Search here!">
                        <button class="search_song_btn">Find</button>
                    </div>
                    <div class="wrap_list_song" id="wrap_list_med">

                    </div>
                </div>
                
                <div class="wrap_song_right">
                     <div class="song_right">
                        <div class="wrap_head_song_right">
                            <h1 class="head_song_right">New Media</h1>
                        </div>
                        <div class="wrap_add_song_right">
                            <div class="wrap_name_inp">
                                <input type="text" class="name_song"  id="new_name_med" placeholder="Name">
                            </div>
                            <div class="wrap_year_inp">
                                <p class="head_file">Year</p>
                                <input type="number" class="year_song"  id="new_year_med" placeholder="Year">
                            </div>
                            <div class="wrap_file_inp">
                                <p class="head_file">Image</p>
                                <input type="file" class="file_img_song" id="new_file_img_song" onchange="validate('inp_artist_new')">
                            </div>
                            <div class="wrap_file_inp">
                                <p class="head_file">Path</p>
                                <input type="file" class="file_path_song"  id="new_file_path_song" onchange="validateMedia('new_file_path_song')" >
                            </div>

                            <div class="wrap_file_inp">
                                <p class="head_file">Artist</p>
                                <div class="preview_art">
                                    <p id="text_art_pre" class="text_art_pre"></p>
                                </div>
                                <div class="wrap_artist" id="wrap_artist_med">
                                    
                                </div>
                            </div>
                            
                            <p class="cate_head">Categories</p>
                            <div class="wrap_cate_inp" id="wrap_cate_med">
                                
                            </div>

                            <div class="wrap_type_med">
                                    <div class="wrap_head_tpe_med">
                                        <p class="type_head_med">Type</p>
                                    </div>
                                <div class="type_med_ct_wrap">
                                    <div class="wrap_check_type">
                                        <input type="radio" name="type_new_med" id="type_new_med" value="song">
                                        <p class="name_check_type">Song</p>
                                    </div>
                                    <div class="wrap_check_type">
                                        <input type="radio" name="type_new_med" id="type_new_med" value="podcast">
                                        <p class="name_check_type">Podcast</p>
                                    </div>
                                </div>
                            </div>

                            <div class="wrap_addsong_btn">
                                <input disabled type="button" onclick="Add_new('media')" id="new_med_btn" class="addsong_btn" value="Add">
                            </div>
                            <br><br><br>
                        </div>
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
	                    <div class="wrap_type_med">
	                        <div class="type_med_ct_wrap">
	                            <div class="wrap_check_type">
	                                <input type="radio" name="type_new_playlist" id="type_new_playlist" value="song">
	                                <p class="name_check_type">Song</p>
	                            </div>
	                            <div class="wrap_check_type">
	                                <input type="radio" name="type_new_playlist" id="type_new_playlist" value="podcast">
	                                <p class="name_check_type">Podcast</p>
	                            </div>
	                        </div>
                    	</div>
	                    <input type ="button" onclick="Add_new('playlist')" disabled id="acp_cre_btn" class="acp_cre" value="create">
	                    <button onclick="turn_off_create_playlist()" id="unacp_cre_btn" class="unacp_cre">cancle</button>
	                </div>
	            </div>
            </div>
		</div>
		
		<style>
			.acp_cre:disabled{
				background-color:gray;
			}
			.acp_cre{
				text-align:center;
			}
		</style>

        <!-- content playlist -->
        <div class="wrap_playlist_behave" id="PLL" style="display: none;">
            <div class="playlist_behave" style="display: none;">
                <div class="wrap_song_list_left">

                    <div class="song_list_left_head">
                        <h2 class="head_list_left">Media</h2>
                    </div>
                    <div class="wrap_search_song">
                        <input type="text" class="search_song" id="search_song" placeholder="Search here!">
                        <button class="search_song_btn">Find</button>
                    </div>
                    <div class="wrap_list_song" id="wrap_list_song_playlist">


                        <div class="song">
                            <div class="song_info">
                                <div class="wrap_song_img">
                                    <img class="song_img" src="/images/golden_hour.jpg" alt="">
                                </div>
                                <div class="wrap_song_info">
                                    <p type="text" class="song_name" id="NAME_id_song">Name song</p>
                                    <p class="song_per" id="ART_id_song">Artist name</p>
                                </div>
                            </div>
                            <div class="wrap_beahave">
                                <button class="btn_beahave_song"><img class="img_beahave_song" src="/images/plus.png" alt=""></button>
                            </div>
                        </div>

                        

                        
                    </div>
                </div>

                <div class="wrap_pll_right" >
                    <div class="wrap_pll_info">
                        <img class="img_pll_add" src="/images/Loi-bai-hat-Co-Em-–-Madihu-x-Low-G-780x470.jpg" alt="">
                        <div class="pll_info">
                            <p class="info_name_pll">playlist name</p>
                            <p class="info_sript_pll">script playlist</p>
                            <p class="type_pll">Type: song</p>
                        </div>
                    </div>
                    <div class="wrap_all_med_pll">

                        <div class="song" id="song">
                            <div class="song_info">
                                <div class="wrap_song_img">
                                    <img class="song_img" src="/images/golden_hour.jpg" alt="">
                                </div>
                                <div class="wrap_song_info" >
                                    <p type="text" class="song_name" id="NAME_id_song">Name song</p>
                                    <p class="song_per" id="ART_id_song">Artist name</p>
                                </div>
                            </div>
                            <div class="wrap_beahave">
                                <button class="btn_beahave_song"><img class="img_beahave_song" src="/images/remove.png" alt=""></button>
                            </div>
                        </div>

                        <div class="song" id="song">
                            <div class="song_info">
                                <div class="wrap_song_img">
                                    <img class="song_img" src="/images/golden_hour.jpg" alt="">
                                </div>
                                <div class="wrap_song_info" >
                                    <p type="text" class="song_name" id="NAME_id_song">Name song</p>
                                    <p class="song_per" id="ART_id_song">Artist name</p>
                                </div>
                            </div>
                            <div class="wrap_beahave">
                                <button class="btn_beahave_song"><img class="img_beahave_song" src="images/remove.png" alt=""></button>
                            </div>
                        </div>

                        <div class="song" id="song">
                            <div class="song_info">
                                <div class="wrap_song_img">
                                    <img class="song_img" src="/images/golden_hour.jpg" alt="">
                                </div>
                                <div class="wrap_song_info" >
                                    <p type="text" class="song_name" id="NAME_id_song">Name song</p>
                                    <p class="song_per" id="ART_id_song">Artist name</p>
                                </div>
                            </div>
                            <div class="wrap_beahave">
                                <button class="btn_beahave_song"><img class="img_beahave_song" src="/images/remove.png" alt=""></button>
                            </div>
                        </div>

                        <div class="song" id="song">
                            <div class="song_info">
                                <div class="wrap_song_img">
                                    <img class="song_img" src="/images/golden_hour.jpg" alt="">
                                </div>
                                <div class="wrap_song_info" >
                                    <p type="text" class="song_name" id="NAME_id_song">Name song</p>
                                    <p class="song_per" id="ART_id_song">Artist name</p>
                                </div>
                            </div>
                            <div class="wrap_beahave">
                                <button class="btn_beahave_song"><img class="img_beahave_song" src="/images/remove.png" alt=""></button>
                            </div>
                        </div>

                        <div class="song" id="song">
                            <div class="song_info">
                                <div class="wrap_song_img">
                                    <img class="song_img" src="/images/golden_hour.jpg" alt="">
                                </div>
                                <div class="wrap_song_info" >
                                    <p type="text" class="song_name" id="NAME_id_song">Name song</p>
                                    <p class="song_per" id="ART_id_song">Artist name</p>
                                </div>
                            </div>
                            <div class="wrap_beahave">
                                <button class="btn_beahave_song"><img class="img_beahave_song" src="/images/remove.png" alt=""></button>
                            </div>
                        </div>

                        <div class="song" id="song">
                            <div class="song_info">
                                <div class="wrap_song_img">
                                    <img class="song_img" src="images/golden_hour.jpg" alt="">
                                </div>
                                <div class="wrap_song_info" >
                                    <p type="text" class="song_name" id="NAME_id_song">Name song</p>
                                    <p class="song_per" id="ART_id_song">Artist name</p>
                                </div>
                            </div>
                            <div class="wrap_beahave">
                                <button class="btn_beahave_song"><img class="img_beahave_song" src="/images/remove.png" alt=""></button>
                            </div>
                        </div>

                        <div class="song" id="song">
                            <div class="song_info">
                                <div class="wrap_song_img">
                                    <img class="song_img" src="/images/golden_hour.jpg" alt="">
                                </div>
                                <div class="wrap_song_info" >
                                    <p type="text" class="song_name" id="NAME_id_song">Name song</p>
                                    <p class="song_per" id="ART_id_song">Artist name</p>
                                </div>
                            </div>
                            <div class="wrap_beahave">
                                <button class="btn_beahave_song"><img class="img_beahave_song" src="/images/remove.png" alt=""></button>
                            </div>
                        </div>

                        <div class="song" id="song">
                            <div class="song_info">
                                <div class="wrap_song_img">
                                    <img class="song_img" src="/images/golden_hour.jpg" alt="">
                                </div>
                                <div class="wrap_song_info" >
                                    <p type="text" class="song_name" id="NAME_id_song">Name song</p>
                                    <p class="song_per" id="ART_id_song">Artist name</p>
                                </div>
                            </div>
                            <div class="wrap_beahave">
                                <button class="btn_beahave_song"><img class="img_beahave_song" src="/images/remove.png" alt=""></button>
                            </div>
                        </div>

                    </div>
                </div>

            </div>

            <div class="wrap_pll_display" >
                <div class="wrap_btn_behave_pll">
                    <button class="new_pll_btn" onclick="create_playlist()">New</button>
                </div>
                <div class="pll_display" id="pll_display">

                </div>
            </div>
        </div>
    


        <!-- content artist -->
        <div class="wrap_artist_behave" id="ART" style="display: none;">
            <div class="song_behave">
                <div class="wrap_song_list_left">

                    <div class="song_list_left_head">
                        <h2 class="head_list_left">Artists</h2>
                    </div>
                    <div class="wrap_search_song">
                        <input type="text" class="search_song" id="search_song" placeholder="Search here!">
                        <button class="search_song_btn">Find</button>
                    </div>
                    <div class="wrap_list_song" id="wrap_list_artist">
                        
                    </div>
                </div>
                <div class="wrap_song_right" >
                     <div class="song_right">
                        <div class="wrap_head_song_right">
                            <h1 class="head_song_right">New Artist</h1>
                        </div>
                        <div class="wrap_add_song_right">
                            <div class="wrap_name_inp">
                                <input type="text" class="name_song" id="new_name_artist" placeholder="Name">
                            </div>
                            <div class="wrap_file_inp">
                                <p class="head_file">Image</p>
                                <input type="file" class="file_img_song" id="inp_artist_new" onchange="validate('inp_artist_new')">
                            </div>

                            <div class="wrap_type_med">
                                    <div class="wrap_head_tpe_med">
                                        <p class="type_head_med">Type</p>
                                    </div>
                                <div class="type_med_ct_wrap">
                                    <div class="wrap_check_type">
                                        <input type="radio" name="check_artist_type" id="check_artist_type" value="Singer">
                                        <p class="name_check_type">Singer</p>
                                    </div>
                                    <div class="wrap_check_type">
                                        <input type="radio" name="check_artist_type" id="check_artist_type" value="Podcaster">
                                        <p class="name_check_type">Podcaster</p>
                                    </div>
                                </div>
                            </div>

                            <div class="wrap_addsong_btn">
                                <input disabled onclick="Add_new('artist')" id = "new_artist_btn" type = "button" class="addsong_btn" value = "Add">
                            </div>
                            <br><br><br>
                            <style>
                            .addsong_btn:disabled{
                            	background-color:gray;
                            }
                            </style>
                        </div>
                     </div>
                     
                </div>
            </div>
        </div>

        <!-- content of categories -->
        <div class="wrap_cate_behave" id="CATE" style="display: none;">
            <div class="song_behave">
                <div class="wrap_song_list_left">

                    <div class="song_list_left_head">
                        <h2 class="head_list_left">Categories</h2>
                    </div>
                    <div class="wrap_search_song">
                        <input type="text" class="search_song" id="search_song" placeholder="Search here!">
                        <button class="search_song_btn">Find</button>
                    </div>
                    <div class="wrap_list_song" id="wrap_list_cate">

                    </div>
                </div>
                <div class="wrap_song_right">
                     <div class="song_right">
                        <div class="wrap_head_song_right">
                            <h1 class="head_song_right">New Category</h1>
                        </div>
                        <div class="wrap_add_song_right">
                            <div class="wrap_name_inp">
                                <input type="text" class="name_song" id="inp_cate_new" placeholder="Name">
                            </div>

                            <div class="wrap_addsong_btn">
                                <input disabled type="button" id="new_cate_btn" onclick="Add_new('cate')" class="addsong_btn" value="Add">
                            </div>
                            <br><br><br>
                        </div>
                     </div>
                     
                </div>
            </div>
        </div>
        
        <!-- content of user -->
        <div class="wrap_user_behave" id="USER" style="display:none">
            <div class="user_cate">
                <div class="wrap_count_user">
                    <p class="count_user">User: </p> <p class="wrap_number_count" id="number_count">1</p>
                </div>
                <div class="wrap_user" id = "wrap_user">
                   
                </div>
            </div>
        </div>
	<form style="display:none;" id="loo" action="Logout" method="post"><input type="hidden" value="logout" name="loo"></form>

    </div>

    <script>
    	var interval = setInterval(()=>{},5000);
    	function create_playlist(){
        	document.getElementById("wrap_out_cre_pll").style.display="flex";
        }
    	function turn_off_create_playlist(){
        	document.getElementById("wrap_out_cre_pll").style.display="none";
        }
        function logout(){ 
        	document.getElementById("loo").submit();
        }
        function Apprear(ID){
            var HOME  = document.getElementById("HOME");
            var MEDIA = document.getElementById("MEDIA");
            var PLL = document.getElementById("PLL");
            var ART = document.getElementById("ART");
            var CATE = document.getElementById("CATE");
            switch (ID) {
                case "HOME":
                    HOME.style.display = "flex";
                    MEDIA.style.display = "none";
                    PLL.style.display = "none";
                    ART.style.display = "none";
                    CATE.style.display = "none";
                    USER.style.display = "none";
					
                    break;

                case "MEDIA":
                    HOME.style.display = "none";
                    MEDIA.style.display = "block";
                    PLL.style.display = "none";
                    ART.style.display = "none";
                    CATE.style.display = "none";
                    USER.style.display = "none";
                    clearInterval(interval);
                    interval = setInterval(check_new_media, 500);
                    $.ajax({
                		url : "Admin",
                		type: "POST",
                		data:{
                			Admin_Behave : "AdAction",
                			Admin_request: "get_media"
                		},
                		success: function(response){
                			console.log(response);
                			if(response.error === "false"){
                				var count_med = 0;
        		  	        	for (var key in response.media) {
        		  	        		if (response.media.hasOwnProperty(key)) {
        		  	        			count_med++;
        		  	        	    }
        		  	        	}
        		  	        	var count_cate = 0;
        		  	        	for (var key in response.cate) {
        		  	        		if (response.cate.hasOwnProperty(key)) {
        		  	        			count_cate++;
        		  	        	    }
        		  	        	}
        		  	        	var count_artist = 0;
        		  	        	for (var key in response.artist) {
        		  	        		if (response.artist.hasOwnProperty(key)) {
        		  	        			count_artist++;
        		  	        	    }
        		  	        	}
        		  	        	var MedList  = document.getElementById("wrap_list_med");
        		  	        	var CateList = document.getElementById("wrap_cate_med");
        		  	        	var ArtList = document.getElementById("wrap_artist_med");
        		  	        	
        		  	        	MedList.innerHTML = "";
        		  	        	if(count_med > 0){
        		  	        		for(var i = 0 ; i < count_med; i++){
        		  	        			MedList.innerHTML = MedList.innerHTML + "<div class='song' id='"+response.media[i].id+"'><div class='song_info'><div class='wrap_song_img'><img class='song_img' id='idMed_img_"+response.media[i].id+"' src='"+response.media[i].img+"' alt=''></div><div class='wrap_song_info'><input type='text' class='song_name' value='"+response.media[i].name+"' id='idMed_name_"+response.media[i].id+"'><p class='song_per'>"+response.media[i].per+"</p><p class='song_per'>"+response.media[i].types+", "+response.media[i].cate+", "+response.media[i].year+"</p></div></div><div class='wrap_beahave'><button class='btn_beahave_song' onclick='deleteMedia(\""+response.media[i].id+"\")'><img class='img_beahave_song' src='images/delete.png' alt=''></button><input type='file' class='ipn_file' style='display:none;' id='inp_img_med_"+response.media[i].id+"' onchange=\"showPreview(this.id,'idMed_img_"+response.media[i].id+"', '"+response.media[i].img+"')\"><label class='btn_beahave_song' style='display:flex; justify-content: center; align-item: center;' for='inp_img_med_"+response.media[i].id+"'><img class='img_beahave_song' src='images/image.png' alt=''></label><button class='btn_beahave_song' onclick = \"Save('"+response.media[i].id+"', 'inp_img_med_"+response.media[i].id+"', 'idMed_name_"+response.media[i].id+"', '', 'idMed_img_"+response.media[i].id+"', 'media', '', '"+response.media[i].types+"')\"><img class='img_beahave_song' src='images/upload.png' alt=''></button><button class='btn_beahave_song' onclick = \"reloadInfo('media', 'idMed_img_"+response.media[i].id+"', 'idMed_name_"+response.media[i].id+"', '', '"+response.media[i].img+"', '"+response.media[i].name+"', '', 'inp_img_med_"+response.media[i].id+"' )\"><img class='img_beahave_song' src='images/reload.png' alt=''></button></div></div>";
        		  	        		}
        		  	        	}
        		  	        	CateList.innerHTML = "";
        		  	        	if(count_cate > 0){
        		  	        		for(var i = 0 ; i < count_cate; i++){
        		  	        			CateList.innerHTML = CateList.innerHTML + "<div class='wrap_cate'><input type='radio' name='cate_new_med' id='cate_new_med' value='"+response.cate[i].name+"'><p class='cate_name'>"+response.cate[i].name+"</p></div>";
        		  	        		}
        		  	        	}
        		  	        	ArtList.innerHTML = "";
        		  	        	if(count_artist > 0){
        		  	        		for(var i = 0 ; i < count_cate; i++){
        		  	        			ArtList.innerHTML = ArtList.innerHTML + "<div class='wrap_art_inp'><input type='radio' name='Name_artist_med' id='"+response.artist[i].id+"' value='"+response.artist[i].name+"'><p class='inp_name_art'>"+response.artist[i].name+"</p></div>";
        		  	        		}
        		  	        	}
                			}else if(response.error === "true"){
                				
                			}
                		},
                		error: function(response){
                			
                		}
                	});
                    break;

                case "PLL":
                    HOME.style.display = "none";
                    MEDIA.style.display = "none";
                    PLL.style.display = "block";
                    ART.style.display = "none";
                    USER.style.display = "none";
                    CATE.style.display = "none";
                    clearInterval(interval);
                    interval = setInterval(check_new_playlist, 500);
					$.ajax({
						url : "Admin",
						type : "POST",
						data: {
							Admin_Behave : "AdAction",
                			Admin_request: "get_playlist"
						},
						success : function(response){
							console.log(response);
							var wrap_pll = document.getElementById("pll_display");
							wrap_pll.innerHTML = "";
                			if(response.error === "false"){
                				if(response.playlist.length > 0){
                					for(var i = 0; i < response.playlist.length; i++){
                						wrap_pll.innerHTML = wrap_pll.innerHTML + "<div class='pll' id='"+response.playlist[i].id+"'><img class='img_pll' id = 'idPll_img_"+response.playlist[i].id+"' src='"+response.playlist[i].img+"' alt=''><input type='text' class='name_pll' id='idPll_name_"+response.playlist[i].id+"' value='"+response.playlist[i].name+"'><input type='text' class='script_pll' id='idPll_scr_"+response.playlist[i].id+"' value='"+response.playlist[i].script+"'><div class='wrap_DEL_RE_UP'><button class='btn_beahave_song' onclick=''><img class='img_beahave_song' src='images/edit_pll.png' alt=''></button><button class='btn_beahave_song' onclick='deletePlaylist(\""+response.playlist[i].id+"\")'><img class='img_beahave_song' src='images/delete.png' alt=''></button><input type='file' class='ipn_file' style='display:none;' id='inp_img_pll_"+response.playlist[i].id+"' onchange=\"showPreview(this.id,'idPll_img_"+response.playlist[i].id+"', '"+response.playlist[i].img+"')\"><label class='btn_beahave_song' for='inp_img_pll_"+response.playlist[i].id+"'><img class='img_beahave_song' src='images/image.png' alt=''></label><button class='btn_beahave_song' onclick = \" Save( '"+response.playlist[i].id+"', 'inp_img_pll_"+response.playlist[i].id+"', 'idPll_name_"+response.playlist[i].id+"', 'idPll_scr_"+response.playlist[i].id+"', 'idPll_img_"+response.playlist[i].id+"', 'playlist', '', '')\" ><img class='img_beahave_song' src='images/upload.png' alt=''></button><button class='btn_beahave_song' onclick = \" reloadInfo('playlist', 'idPll_img_"+response.playlist[i].id+"', 'idPll_name_"+response.playlist[i].id+"', 'idPll_scr_"+response.playlist[i].id+"', '"+response.playlist[i].img+"', '"+response.playlist[i].name+"', '"+response.playlist[i].script+"', 'inp_img_pll_"+response.playlist[i].id+"')\"><img class='img_beahave_song' src='images/reload.png' alt=''></button></div></div>";
                					}
                				}else{
                					
                				}
							}else if(response.error === "true"){
	            				
	            			}
						},

						error: function(response){
							
						}
					});
                    break;

                case "ART":
                    HOME.style.display = "none";
                    MEDIA.style.display = "none";
                    PLL.style.display = "none";
                    ART.style.display = "block";
                    USER.style.display = "none";
                    CATE.style.display = "none";
                    clearInterval(interval);
                    interval = setInterval(check_new_artist, 500);
                    $.ajax({
						url : "Admin",
						type : "POST",
						data: {
							Admin_Behave : "AdAction",
                			Admin_request: "get_artist"
						},
						success : function(response){
							console.log(response);
							var wrap_artist = document.getElementById("wrap_list_artist");
							wrap_artist.innerHTML = "";
                			if(response.error === "false"){
                				if(response.artist.length > 0){
                					for(var i = 0; i < response.artist.length; i++){
                						wrap_artist.innerHTML = wrap_artist.innerHTML + "<div class='song' id='"+response.artist[i].id+"'><div class='song_info'><div class='wrap_song_img'><img class='song_img' id = 'idArt_img_"+response.artist[i].id+"' src='"+response.artist[i].img+"' alt=''></div><div class='wrap_song_info'><input type='text' class='song_name' value='"+response.artist[i].name+"' id='idArt_name_"+response.artist[i].id+"'><p class='song_per'>"+response.artist[i].per_type+"</p></div></div><div class='wrap_beahave'><input type='file' class='ipn_file' style='display:none;' id='inp_img_art_"+response.artist[i].id+"' onchange=\"showPreview(this.id,'idArt_img_"+response.artist[i].id+"', '"+response.artist[i].img+"')\"><label class='btn_beahave_song' for='inp_img_art_"+response.artist[i].id+"' style='display:flex; justify-content: center; align-item: center;'><img class='img_beahave_song' src='images/image.png' alt=''></label><button class='btn_beahave_song' onclick = \"Save('"+response.artist[i].id+"', 'inp_img_art_"+response.artist[i].id+"', 'idArt_name_"+response.artist[i].id+"', '', 'idArt_img_"+response.artist[i].id+"', 'artist', '"+response.artist[i].name+"', '')\"><img class='img_beahave_song' src='images/upload.png'  alt=''></button><button class='btn_beahave_song' onclick = \"reloadInfo('artist', 'idArt_img_"+response.artist[i].id+"', 'idArt_name_"+response.artist[i].id+"', '', '"+response.artist[i].img+"', '"+response.artist[i].name+"', '', 'inp_img_art_"+response.artist[i].id+"')\"><img class='img_beahave_song' src='images/reload.png' alt=''></button></div></div>";
                					}
                				}else{
                					wrap_artist.innerHTML = "";
                				}
							}else if(response.error === "true"){
	            				
	            			}
						},

						error: function(response){
							
						}
					});
                    break;
                
                case "CATE":
                    HOME.style.display = "none";
                    MEDIA.style.display = "none";
                    PLL.style.display = "none";
                    ART.style.display = "none";
                    USER.style.display = "none";
                    CATE.style.display = "block";
                    clearInterval(interval);
                    interval = setInterval(check_cate, 500);
                    $.ajax({
						url : "Admin",
						type : "POST",
						data: {
							Admin_Behave : "AdAction",
                			Admin_request: "get_cate"
						},
						success : function(response){
							console.log(response);
							var wrap_cate = document.getElementById("wrap_list_cate");
							wrap_cate.innerHTML = "";
                			if(response.error === "false"){
                				if(response.cate.length > 0){
                					for(var i = 0; i < response.cate.length; i++){
                						wrap_cate.innerHTML = wrap_cate.innerHTML + " <div class='song'><div class='song_info'><div class='wrap_song_info'><input type='text' class='song_name' value='"+response.cate[i].name+"' id='idCate_name_"+response.cate[i].id+"'></div></div><div class='wrap_beahave'><button class='btn_beahave_song' onclick = \"Save('"+response.cate[i].id+"', '', 'idCate_name_"+response.cate[i].id+"', '', '', 'cate', '"+response.cate[i].name+"', '')\"><img class='img_beahave_song' src='images/upload.png' alt=''></button><button class='btn_beahave_song' onclick = \"reloadInfo('cate', '', 'idCate_name_"+response.cate[i].id+"', '', '', '"+response.cate[i].name+"', '', '')\" ><img class='img_beahave_song' src='images/reload.png' alt=''></button></div></div> ";
                					}
                				}else{
                					wrap_cate.innerHTML = "";
                				}
							}else if(response.error === "true"){
	            				
	            			}
						},

						error: function(response){
							
						}
					});
                    break;
                   
                case "USER":
                    HOME.style.display = "none";
                    MEDIA.style.display = "none";
                    PLL.style.display = "none";
                    ART.style.display = "none";
                    CATE.style.display = "none";
                    USER.style.display = "block";
                    
                    $.ajax({
						url : "Admin",
						type : "POST",
						data: {
							Admin_Behave : "AdAction",
                			Admin_request: "get_user"
						},
						success : function(response){
							console.log(response);
							var wrap_user = document.getElementById("wrap_user");
							var number_count = document.getElementById("number_count");
							wrap_user.innerHTML = "";
                			if(response.error === "false"){
                				if(response.user.length > 0){
                					number_count.innerText = response.user.length;
                					for(var i = 0; i < response.user.length; i++){
                						wrap_user.innerHTML = wrap_user.innerHTML + " <div id='"+response.user[i].id+"' class='user'><div class='wrap_user_info'><div class='wrap_user_id'><p class='user_id'>"+response.user[i].id+"</p></div><div class='wrap_user_img'><img class='user_img' src='"+response.user[i].img+"' alt=''></div><div class='wrap_name_mail'><p class='name_user'>"+response.user[i].name+"</p><p class='mail_user'>"+response.user[i].email+"</p> </div></div><div class='wrap_beahave'><button class='btn_beahave_song' onclick='deleteUser(\""+response.user[i].id+"\")'><img class='img_beahave_song' src='images/delete.png' alt=''></button></div></div>";
                					}
                				}else{
                					number_count.innerText = response.user.length;
                					wrap_user.innerHTML = "";
                				}
							}else if(response.error === "true"){
	            				
	            			}
						},

						error: function(response){
							
						}
					});
                    break;
            };
        }
        
        function deleteUser(userID){
        	$.ajax({
        		url: "Admin",
        		type: "POST",
        		data: {
        			Admin_Behave : "AdAction",
        			Admin_request: "del_user",
        			id_user : userID
        		},
        		success: function (response){
        			if(response.error === "false"){
        				console.log(response);
						var number_count = document.getElementById("number_count");
        				if(response.delStt === "true"){
        					number_count.innerText = response.count;
        					var wrap_user = document.getElementById("wrap_user");
        					for (var i = 0; i < wrap_user.children.length; i++) {
    	  			  			if(wrap_user.children[i].id === userID){
    	  			  				wrap_user.removeChild(wrap_user.children[i]);
    	  			  				break;
    	  			  			}
    			  		  	}
        				}else if(response.delStt === "false"){
        					
        				}
        			}else if(response.error === "true"){
        				
        			}
        		},
        		error: function (response){
        			
        		},
        	});
        }
        
        function deleteMedia(id_media){
        	$.ajax({
        		url: "Admin",
        		type: "POST",
        		data: {
        			Admin_Behave : "AdAction",
        			Admin_request: "del_med",
        			id_media : id_media
        		},
        		success: function (response){
        			if(response.error === "false"){
        				console.log(response);
        				if(response.delStt === "true"){
        					var wrap_med = document.getElementById("wrap_list_med");
        					for (var i = 0; i < wrap_med.children.length; i++) {
    	  			  			if(wrap_med.children[i].id === id_media){
    	  			  				wrap_med.removeChild(wrap_med.children[i]);
    	  			  				break;
    	  			  			}
    			  		  	}
        				}else if(response.delStt === "false"){
        					
        				}
        			}else if(response.error === "true"){
        				
        			}
        		},
        		error: function (response){
        			
        		},
        	});
        }

        
        function deletePlaylist(id_playlist){
        	$.ajax({
        		url: "Admin",
        		type: "POST",
        		data: {
        			Admin_Behave : "AdAction",
        			Admin_request: "del_pll",
        			id_pll : id_playlist
        		},
        		success: function (response){
        			if(response.error === "false"){
        				console.log(response);
        				if(response.delStt === "true"){
        					var wrap_pll = document.getElementById("pll_display");
        					for (var i = 0; i < wrap_pll.children.length; i++) {
    	  			  			if(wrap_pll.children[i].id === id_playlist){
    	  			  				wrap_pll.removeChild(wrap_pll.children[i]);
    	  			  				break;
    	  			  			}
    			  		  	}
        				}else if(response.delStt === "false"){
        					
        				}
        			}else if(response.error === "true"){
        				
        			}
        		},
        		error: function (response){
        			
        		},
        	});
        }
        
        function reloadInfo(type, id_img_old, id_name_old, id_script_old, data_img, data_name, data_script, input_id){
        	if(type === "cate"){
        		document.getElementById(id_name_old).value = data_name;
        	}else if(type === "media"){
        		document.getElementById(id_img_old).src = data_img;
        		document.getElementById(id_name_old).value = data_name;
                document.getElementById(input_id).value = null;
                console.log(document.getElementById(input_id).files[0]);
        	}else if(type === "artist"){
        		document.getElementById(id_img_old).src = data_img;
        		document.getElementById(id_name_old).value = data_name;
        		document.getElementById(input_id).value = null;
        		console.log(document.getElementById(input_id).files[0]);
        	}else if(type === "playlist"){
        		document.getElementById(id_img_old).src = data_img;
        		document.getElementById(id_name_old).value = data_name;
        		document.getElementById(id_script_old).value = data_script;
        		document.getElementById(input_id).value = null;
        		console.log(document.getElementById(input_id).files[0]);
        	}
        }
        
        function Save( id, id_input, id_name, id_script, id_img, type, old, med_type){
        	const file_img = document.getElementById(id_input);
    		var name_med = document.getElementById(id_name);
    		var behave = "AdAction";		
        	if(type === "cate"){
        		var request = "up_cate";
    	  	  $.ajax({
  				url : "Admin",
  				type: "POST",
					data: {
						id : id,
		    	  	    old_cate : old,
		    	  	    name : name_med.value,
		    	  	  	Admin_Behave : behave,
		    	  	    Admin_request: request
					},
					success: function(response){
						if(response.error === "false"){
							Apprear("CATE");
						}else if(response.error === "true"){
							
						}
					},
					error: function(){
						
					},
  				});
        	}else if(type === "media"){
        		var request = "up_med";
        		var formData = new FormData();
    	  	    formData.append("id",id);
    	  	  	formData.append("name",name_med.value);
    	  	  	formData.append("Admin_Behave",behave);
    	  	  	formData.append("Admin_request",request);
    	  	  	formData.append("Med_type",med_type);
    	  	    formData.append("file", file_img.files[0]);

    	  	    var xhr = new XMLHttpRequest();
    	  	    xhr.open("POST", "update_add", true);
    	  	    xhr.onreadystatechange = function () {
    	  	        if (xhr.readyState === 4 && xhr.status === 200) {
    	  	        	var response = JSON.parse(xhr.responseText);
    	  	        	if(response.error === "false"){
    	  	        		Apprear("MEDIA");
    	  	        	}else{
    	  	        		
    	  	        	}
    	  	        	
    	  	        }else{
    	  	        	
    	  	        }
    	  	    };
    	  	    xhr.send(formData);
        	}else if(type === "artist"){
        		var request = "up_art";
        		var formData = new FormData();
    	  	    formData.append("id",id);
    	  	  	formData.append("name",name_med.value);
    	  	  	formData.append("Admin_Behave",behave);
    	  	  	formData.append("Admin_request",request);
    	  	  	formData.append("old_art",old);
    	  	    formData.append("file", file_img.files[0]);

    	  	    var xhr = new XMLHttpRequest();
    	  	    xhr.open("POST", "update_add", true);
    	  	    xhr.onreadystatechange = function () {
    	  	        if (xhr.readyState === 4 && xhr.status === 200) {
    	  	        	var response = JSON.parse(xhr.responseText);
    	  	        	if(response.error === "false"){
    	  	        		Apprear("ART");
    	  	        	}else{
    	  	        		
    	  	        	}
    	  	        	
    	  	        }else{
    	  	        }
    	  	    };
    	  	    xhr.send(formData);
        	}else if(type === "playlist"){
        		var script = document.getElementById(id_script);
        		var request = "up_pll";
        		var formData = new FormData();
    	  	    formData.append("id",id);
    	  	  	formData.append("name",name_med.value);
    	  	  	formData.append("Admin_Behave",behave);
    	  	  	formData.append("Admin_request",request);
    	  	  	formData.append("script",script.value);
    	  	    formData.append("file", file_img.files[0]);

    	  	    var xhr = new XMLHttpRequest();
    	  	    xhr.open("POST", "update_add", true);
    	  	    xhr.onreadystatechange = function () {
    	  	        if (xhr.readyState === 4 && xhr.status === 200) {
    	  	        	var response = JSON.parse(xhr.responseText);
    	  	        	if(response.error === "false"){
        	  	        	Apprear("PLL");
    	  	        	}else{
    	  	        		
    	  	        	}
    	  	        }else{
    	  	        }
    	  	    };
    	  	    xhr.send(formData);
        	}
        }
        
        
        
        function Add_new(type){
        	switch (type){
        		case "media":
        			var behave = "AdAction";
					var request = "new_med";
					var radio_art = document.getElementsByName("Name_artist_med");
		        	var radio_cate = document.getElementsByName("cate_new_med");
		        	var radio_type = document.getElementsByName("type_new_med");
		        	var year = document.getElementById("new_year_med");
		        	var file_img = document.getElementById("new_file_img_song");
		        	var file_path = document.getElementById("new_file_path_song")
		            var count = 0;
		        	var id_art;
		            var type_value;
		            var art_value ;
		            var cate_value;
		            while(count < radio_art.length){
		            	if(radio_art[count].checked){
		            		art_value = radio_art[count].value;
		            		id_art= radio_art[count].id;
		                }
		                count++;
		            }
		            count = 0;
		            while(count < radio_cate.length){
		            	if(radio_cate[count].checked){
		            		cate_value = radio_cate[count].value;
		                }
		                count++;
		            }
		            count = 0;
		            while(count < radio_type.length){
		            	if(radio_type[count].checked){
		            		type_value = radio_type[count].value;
		                }
		                count++;
		            }
					var formData = new FormData();
					var name = document.getElementById("new_name_med");
					var file = document.getElementById("inp_artist_new");
	    	  	  	formData.append("name",name.value);
	    	  	  	formData.append("type",type_value);
	    	  	  	formData.append("cate",cate_value);
	    	  	  	formData.append("year",year.value);
	    	  	  	formData.append("per",art_value);
	    	  	  	formData.append("id_per",id_art);
	    	  	  	formData.append("file", file_img.files[0]);
	    	  	  	formData.append("file_path", file_path.files[0]);
	    	  	  	formData.append("Admin_Behave",behave);
	    	  	  	formData.append("Admin_request",request);

	    	  	    var xhr = new XMLHttpRequest();
	    	  	    xhr.open("POST", "update_add", true);
	    	  	    xhr.onreadystatechange = function () {
	    	  	        if (xhr.readyState === 4 && xhr.status === 200) {
	    	  	        	var response = JSON.parse(xhr.responseText);
	    	  	        	if(response.error === "false"){
	    	  	        		Apprear("MEDIA");
	    	  	        	}else{
	    	  	        		
	    	  	        	}
	    	  	        }else{
	    	  	        }
	    	  	    };
	    	  	    xhr.send(formData);
        			
        			break;
        			
				case "playlist":
					var name = document.getElementById("cre_pll_name");
					var type;
					var radio_type = document.getElementsByName("type_new_playlist");
		        	var count = 0;
		             while(count < radio_type.length){
		             	if(radio_type[count].checked){
		                 	type = radio_type[count].value;
		                 }
		                 count++;
		             }
        			$.ajax({
        				url: "Admin",
        				type: "POST",
        				data: {
        					Admin_Behave : "AdAction",
							Admin_request: "new_pll",
							name: name.value,
							type: type
        				},
        				success: function (response){
        					if(response.error === "false"){
        						Apprear("PLL");
        						turn_off_create_playlist();
        					}else if(respnse.error === "true"){
        						turn_off_create_playlist();
        					}else if(respnse.error === "nullname"){
        						turn_off_create_playlist();
        					}
        					
        				},
        				error: function (response){
        					
        				} 
        			});
        			
        			break;
        			
				case "cate":
        			var new_name_cate = document.getElementById("inp_cate_new");
        			$.ajax({
        				url : "Admin",
        				type: "POST",
						data: {
							Admin_Behave : "AdAction",
							Admin_request: "new_cate",
							name_cate : new_name_cate.value
						},
						success: function(response){
							if(response.error === "false"){
								var wrap_cate = document.getElementById("wrap_list_cate");
	                			wrap_cate.innerHTML =  " <div class='song'><div class='song_info'><div class='wrap_song_info'><input type='text' class='song_name' value='"+response.name+"' id='idCate_name_"+response.id+"'></div></div><div class='wrap_beahave'><button class='btn_beahave_song' onclick = \"Save('"+response.id+"', '', 'idCate_name_"+response.id+"', '', '', 'cate', '"+response.name+"', '')\"><img class='img_beahave_song' src='images/upload.png' alt=''></button><button class='btn_beahave_song' onclick = \"reloadInfo('cate', '', 'idCate_name_"+response.id+"', '', '', '"+response.name+"', '', '')\" ><img class='img_beahave_song' src='images/reload.png' alt=''></button></div></div> " + wrap_cate.innerHTML ;	                				
							}else if(response.error === "true"){
								
							}
						},
						error: function(){
							
						},
        			});
        			break;
        			
				case "artist":
					var behave = "AdAction";
					var request = "new_art";
					var radio = document.getElementsByName("check_artist_type");
		            var count = 0;
		            var type;
		            while(count < radio.length){
		            	if(radio[count].checked){
		            		type = radio[count].value;
		                }
		                count++;
		            }
					var formData = new FormData();
					var name = document.getElementById("new_name_artist");
					var file = document.getElementById("inp_artist_new");
	    	  	  	formData.append("name",name.value);
	    	  	  	formData.append("type",type);
	    	  	  	formData.append("file", file.files[0]);
	    	  	  	formData.append("Admin_Behave",behave);
	    	  	  	formData.append("Admin_request",request);

	    	  	    var xhr = new XMLHttpRequest();
	    	  	    xhr.open("POST", "update_add", true);
	    	  	    xhr.onreadystatechange = function () {
	    	  	        if (xhr.readyState === 4 && xhr.status === 200) {
	    	  	        	var response = JSON.parse(xhr.responseText);
	    	  	        	if(response.error === "false"){
	    	  	        		Apprear("ART");
	    	  	        	}else{
	    	  	        		
	    	  	        	}
	    	  	        }else{
	    	  	        }
	    	  	    };
	    	  	    xhr.send(formData);
        			
        			break;
        			
        			
        	};
        }
        
        
        function check_new_artist(){
        	var radio = document.getElementsByName("check_artist_type");
            var count = 0;
            var check = false;
            while(count < radio.length){
            	if(radio[count].checked){
                	check = true;
                }
                count++;
            }
            if(check == true){
            	if(document.getElementById("inp_artist_new").files.length === 0){
            		document.getElementById("new_artist_btn").disabled = true;
            	}else{
            		if(document.getElementById("new_name_artist").value === ""){
                 	   	document.getElementById("new_artist_btn").disabled = true;

            		}else{
                 	   	document.getElementById("new_artist_btn").disabled = false;
            		}
            	}
            }else{
         	   	document.getElementById("new_artist_btn").disabled = true;
            }
        }
        
        function check_new_media(){
        	var radio_art = document.getElementsByName("Name_artist_med");
        	var radio_cate = document.getElementsByName("cate_new_med");
        	var radio_type = document.getElementsByName("type_new_med");
            var count = 0;
            var check_type = false;
            var check_art = false;
            var check_cate = false;
            while(count < radio_art.length){
            	if(radio_art[count].checked){
            		check_art = true;
                }
                count++;
            }
            count = 0;
            while(count < radio_cate.length){
            	if(radio_cate[count].checked){
            		check_cate = true;
                }
                count++;
            }
            count = 0;
            while(count < radio_type.length){
            	if(radio_type[count].checked){
            		check_type = true;
                }
                count++;
            }
           if(check_art){
        	   if(check_cate){
        		   if(check_type){
        			   if(document.getElementById("new_file_img_song").files.length === 0){
                    	   document.getElementById("new_med_btn").disabled = true;
        			   }else{
        				   if(document.getElementById("new_file_path_song").files.length === 0){
                        	   document.getElementById("new_med_btn").disabled = true;
            			   }else{
            				   if(document.getElementById("new_name_med").value === ""){
                            	   document.getElementById("new_med_btn").disabled = true;
                			   }else{
                				   if(document.getElementById("new_year_med").value === ""){
                                	   document.getElementById("new_med_btn").disabled = true;
                    			   }else{
                    				   document.getElementById("new_med_btn").disabled = false;
                    			   }
                			   }
            			   }
        			   }
                   }else{
                	   document.getElementById("new_med_btn").disabled = true;
                   }
               }else{
            	   document.getElementById("new_med_btn").disabled = true;
               }
           }else{
        	   document.getElementById("new_med_btn").disabled = true;
           }
        }
        
        function check_cate(){
        	 if(document.getElementById("inp_cate_new").value === ""){
          	   		document.getElementById("new_cate_btn").disabled = true;
			   }else{
	          	   	document.getElementById("new_cate_btn").disabled = false;
			   }
        }
        
        function check_new_playlist(){
        	var name = document.getElementById("cre_pll_name");
        	var radio_type = document.getElementsByName("type_new_playlist");
        	 var count = 0;
             var check = false;
             while(count < radio_type.length){
             	if(radio_type[count].checked){
                 	check = true;
                 }
                 count++;
             }
             if(check){
            	 if(name.value === "" && name.value.length < 1){
            		 document.getElementById("acp_cre_btn").disabled = true;
            	 }else{
            		 document.getElementById("acp_cre_btn").disabled = false;
            	 }
             }else{
        		 document.getElementById("acp_cre_btn").disabled = true;
             }
        }
        
        function validateMedia(inp_id){
        	if(event.target.files.length > 0){
                const inp = document.getElementById(inp_id);
  	  		      if (inp.files[0].type === "audio/mpeg") {
  	                console.log(inp.files[0]);
  	  		      } else {
  	  		    	document.getElementById(inp_id).value = null;
  	                console.log(inp.files[0]);
  	  		      }
               
            }else{
                var inp = document.getElementById(inp_id);
                console.log(inp.files[0]);
            }
        }
        
        function showPreview(inp_id, id_img, img_old_data){
            if(event.target.files.length > 0){
                const inp = document.getElementById(inp_id);
  	  		      if (validFileType(inp.files[0])) {
  	  		    	var src = URL.createObjectURL(inp.files[0]);
  	                var preview = document.getElementById(id_img);
  	                preview.src = src;
  	                console.log(inp.files[0]);
  	  		      } else {
  	  		    	document.getElementById(inp_id).value = null;
  	                console.log(inp.files[0]);
  	                document.getElementById(id_img).src = img_old_data;
  	  		      }
               
            }else{
                var inp = document.getElementById(inp_id);
                console.log(inp.files[0]);
                document.getElementById(id_img).src = img_old_data;
            }
          }
        
        function validate(inp_id){
        	if(event.target.files.length > 0){
                const inp = document.getElementById(inp_id);
  	  		      if (validFileType(inp.files[0])) {
  	                console.log(inp.files[0]);
  	  		      } else {
  	  		    	document.getElementById(inp_id).value = null;
  	                console.log(inp.files[0]);
  	  		      }
               
            }else{
                var inp = document.getElementById(inp_id);
                console.log(inp.files[0]);
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
        
    </script>
</body>
</html>