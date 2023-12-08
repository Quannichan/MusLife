<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% if(request.getSession().getAttribute("MAIL") == null && request.getSession().getAttribute("PASS") == null){
	request.getSession().setAttribute("tove",null);
	request.getSession().setAttribute("ve",null);
	request.getSession().setAttribute("mail",null);
	request.getSession().setAttribute("pass",null);
	request.getSession().setAttribute("usname",null); 
	request.getSession().setAttribute("upload",null);
	request.getSession().setAttribute("CP",null);
}else{request.getSession().setAttribute("tove",null);
request.getSession().setAttribute("ve",null);
request.getSession().setAttribute("mail",null);
request.getSession().setAttribute("pass",null);
request.getSession().setAttribute("usname",null); 
request.getSession().setAttribute("upload",null);
request.getSession().setAttribute("CP",null);
response.sendRedirect("Home.jsp");
}
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="images/icon.png">
    <link rel="stylesheet" href="CSS/Login.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Ubuntu:wght@500&display=swap" rel="stylesheet">
    <title>Email | MusLife</title>
</head>
<body>
    <div class="wrap">
        <div class="wrap_mail">
            <form class="form_mail" method="post" action="Login" >
                <div class="wrap_head">
                    <h2 class="head">Enter your email</h2>
                </div>
                <div class="wrap_script">
                    <p class="script">Enter email to send verification code</p>
                </div>
                <div style="margin-bottom:30px;">
                	<p id="mes" style="color:red;"></p>
                </div>
                <div class="email_wrap">
                    <input id="mail_in" type="text" name="email_ver" class="mail_in">
                    <label id="mail_la" class="mail_la" for="email">Email</label>
                </div>
                <div class="wrap_sub">
                    <a class="link" href="index.jsp" style="backdrop-filter:blur(30px);background-color:black;border-radius:10px"><img style="padding:5%;" src="images/logo.png" alt="logo web" width="120px"></a>
                    <input  type="submit" name="Login" value="Send" class="Log_in">
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
    },5);
    </script>
</body>
</html>