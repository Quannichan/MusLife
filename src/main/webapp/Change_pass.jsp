<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% if(request.getSession().getAttribute("MAIL") == null && request.getSession().getAttribute("PASS") == null ) {
	request.getSession().setAttribute("tove",null);
	request.getSession().setAttribute("ve",null);
	request.getSession().setAttribute("mail",null);
	request.getSession().setAttribute("pass",null);
	request.getSession().setAttribute("usname",null); 
	request.getSession().setAttribute("upload",null);
	request.getSession().setAttribute("CP",null);
	response.sendRedirect("Login.jsp");
}else{
	request.getSession().setAttribute("tove",null);
	request.getSession().setAttribute("ve",null);
	request.getSession().setAttribute("mail",null);
	request.getSession().setAttribute("pass",null);
	request.getSession().setAttribute("usname",null); 
	request.getSession().setAttribute("upload",null);
	request.getSession().setAttribute("CP",null);
} %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="images/icon.png">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Ubuntu:wght@500&display=swap" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Change password | MusLife</title>
    <link rel="stylesheet" href="CSS/Change_pass.css">
</head>
<body>
    <div class="wrap">
        <div class="border">
            <form action="Changepass" method="POST" class="wrap_form" id="wrap_form"  onsubmit="return validateForm()">
                <div class="wrap_head_change_inp">
                    <h3 class="h3">Change your password</h3>
                </div>
                <div style="margin-bottom:20px;text-align:center;">
                    <p style="color:red;font-size:14px" id="mes"></p>
                </div>
                <div class="wrap_inp_pass">
                    <input type="password" name="inp_pass" id="inp_pass" class="inp_pass">
                    <lable for="inp_pass" class="la_pass" id="la_pass">Password</lable>
                    <br>
                </div>
                <div class="show_wrap">
                    <input type="checkbox" class="show_pass" id="show_pass"> Show password
                    </div>
                <div class="wrap_forpass">
                    <input id = "forpass" type="submit" class="forpass" name="sub" value="Forgot password?">
                </div>
                <div class="wrap_foot">
                    <div class="wrap_logo_img">
                        <img onclick="window.location.href='Home.jsp'" width="90px" src="images/logo.png" class="img_logo">
                    </div>      
                    <div class="wrap_sub_btn">
                        <input type="submit" name="sub" class="sub_btn" id="sub_btn" value="Next">
                    </div>
                </div>
            </form>
        </div>
    </div>
    <script>
        setInterval(()=>{
            if(document.getElementById('inp_pass').value.length == 0){
                if(document.activeElement === document.getElementById("inp_pass")){
                    document.getElementById("la_pass").style.transform="translateY(-10px) translateX(-230px)";
                    document.getElementById('inp_pass').style.transform="translateY(10px)"; 
                }else{
                    document.getElementById("la_pass").style.transform="translateX(-230px)";
                    document.getElementById('inp_pass').style.transform="translateY(0)";
                }
            }else{
                document.getElementById("la_pass").style.transform="translateY(-10px) translateX(-230px)";
                document.getElementById('inp_pass').style.transform="translateY(10px)"; 
            }

            if(document.getElementById("show_pass").checked == true){
                document.getElementById("inp_pass").type ="text";
            }else{
                document.getElementById("inp_pass").type ="password";
            }
       },5);
       function validateForm() {
        let pass = document.forms["wrap_form"]["inp_pass"].value;
        if (pass == "") {
          document.getElementById('mes').innerText = "Please enter password!";
          document.getElementById("inp_pass").style.borderColor="red";
          return false;
        }else{
            document.getElementById("inp_pass").style.borderColor="black";
            return true;
        }
        
      }
	   
   	document.getElementById("forpass").onclick = function(){
   		document.getElementById("wrap_form").onsubmit= true;
   	}
   	if(event.key === "Enter"){
    	
    }
    </script>
</body>
</html>