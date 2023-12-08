<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% if(request.getSession().getAttribute("MAIL") != null && request.getSession().getAttribute("PASS") != null){
	request.getSession().setAttribute("tove",null);
	request.getSession().setAttribute("ve",null);
	request.getSession().setAttribute("mail",null);
	request.getSession().setAttribute("pass",null);
	request.getSession().setAttribute("usname",null);
	request.getSession().setAttribute("upload",null);
	request.getSession().setAttribute("CP",null);

	String site = "Home.jsp" ;
	response.setStatus(response.SC_MOVED_TEMPORARILY);
	response.setHeader("Location", site); 
}  	request.getSession().setAttribute("tove",null);
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
    <link rel="stylesheet" href="CSS/Login.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="shortcut icon" href="images/icon.png">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <script type="text/javascript" src="script/index.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Ubuntu:wght@500&display=swap" rel="stylesheet">
    <title>MusLife | Login</title>
</head>
<body>
    <div class="wrap_log">
        <div class="wrap_form">
            <form onsubmit="return validateForm()" name="form_log" id="form_log" class="form_log" method="POST" action="Login">
                <h1 style="text-align: left;" class="head_log">
                    Login
                </h1>
                <p class="info">Login to like your favourite song and create your own playlist !</p>
                <div style="margin-top:-20px;margin-bottom:50px"><p id="mes" style="color:red; font-size:16px;"></p></div>
                <div class="email_wrap">
                    <input id="mail_in" type="text" name="email" class="mail_in">
                    <label id="mail_la" class="mail_la" for="email">Email</label>
                </div>
                <div class="pass_wrap">
                    <input id="pass_in" type="password" name="pass" class="pass_in">
                    <label id="pas_la" class="pas_la" for="pass">Password</label>
                </div>
                <br>
                <div class="wrap_check_log"><input  type="checkbox" id="showpass" class="showpass" ><p class="text_check">Show password</p></div>
                <div class="wrap_foot_form">
                    <a class="link" href="Signup.jsp">Don't have account?</a><br>
                    <input type="submit" name="Login" value="Forget password ?" class="link" id="forpass">
                </div>
                <div class="wrap_sub">
                    <a class="link1" href="index.jsp" style="backdrop-filter:blur(30px);background-color:black;border-radius:10px"><img style="padding:5%;" src="images/logo.png" alt="logo web" width="120px"></a>
                    <input type="submit" name="Login" value="Log in" class="Log_in" id="Log_in">
  					<div style="display:none;" id="loader" class="loader"></div>
                </div>
            </form>
        </div>
    </div>
    <script>
        setInterval(()=>{
        	 if(document.getElementById('mail_in').value.length == 0){
                 if(document.activeElement === document.getElementById("mail_in")){
                     document.getElementById("mail_la").style.transform="translateY(-25px) translateX(-300px)";
                     document.getElementById('mail_in').style.transform="translateY(-7px)"; 
                 }else{
                     document.getElementById("mail_la").style.transform="translateY(-15px) translateX(-300px)";
                     document.getElementById('mail_in').style.transform="translateY(-17px)";
                 }
             }else{
                 document.getElementById("mail_la").style.transform="translateY(-25px) translateX(-300px)";
                 document.getElementById('mail_in').style.transform="translateY(-7px)"; 
             }

             if(document.getElementById('pass_in').value.length == 0){
                 if(document.activeElement === document.getElementById("pass_in")){
                     document.getElementById("pas_la").style.transform="translateY(-25px) translateX(-300px)";
                     document.getElementById('pass_in').style.transform="translateY(-7px)"; 
                 }else{
                     document.getElementById("pas_la").style.transform="translateY(-15px) translateX(-300px)";
                     document.getElementById('pass_in').style.transform="translateY(-17px)";
                 }
             }else{
                 document.getElementById("pas_la").style.transform="translateY(-25px) translateX(-300px)";
                 document.getElementById('pass_in').style.transform="translateY(-7px)"; 
             }
             if(document.getElementById("showpass").checked == true){
                 document.getElementById("pass_in").type ="text";
             }else{
                 document.getElementById("pass_in").type ="password";
             }
        },5);
        function validateForm() {
        	  let email = document.forms["form_log"]["email"].value;
        	  let pass = document.forms["form_log"]["pass"].value;
        	  if (email == "" && pass == "") {
        	    document.getElementById('mes').innerText = "Please input all field!";
        	    return false;
        	  }else{
				if(email == ""){
					document.getElementById("loader").style.display = "none";
					document.getElementById('mes').innerText = "Please input email field!";
	        	    return false;
				}else if(pass == ""){
					document.getElementById("loader").style.display = "none";
					document.getElementById('mes').innerText = "Please input password field!";
	        	    return false;
				}else{
					email.toLowerCase();
					if(email.match(/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|.(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/)){
						document.getElementById('mes').innerText = "";
						document.getElementById("loader").style.display = "block";
			        	document.getElementById("Log_in").style.display = "none";
						return true;
					}else{
						document.getElementById("loader").style.display = "none";
						document.getElementById('mes').innerText = "Invalid email!";
		        	    return false;
					}
				}
        	  }
        	}
        document.getElementById('forpass').onclick = function(){
        	document.getElementById("form_log").onsubmit = true;
        }
        var input = document.getElementById("pass_in");
        var inp2 = document.getElementById("mail_in");
        input.addEventListener("keypress", function(event) {
          if (event.key === "Enter") {
            event.preventDefault();
            document.getElementById("Log_in").click();
          }
        });
        inp2.addEventListener("keypress", function(event) {
            if (event.key === "Enter") {
              event.preventDefault();
              document.getElementById("Log_in").click();
            }
          });
    </script>
</body>
</html>