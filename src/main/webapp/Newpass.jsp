<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% if(request.getSession().getAttribute("CP")== null){
	response.sendRedirect("Home.jsp");
}%>
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
                    <p style="color:red;font-size:12px" id="mes"></p>
                </div>
                <div class="wrap_inp_pass">
                    <input type="password" name="inp_pass" id="inp_pass" class="inp_pass">
                    <lable for="inp_pass" class="la_pass" id="la_pass">Password</lable>
                    <br>
                </div>
                <div class="wrap_inp_pass">
                    <input type="password" name="re_inp_pass" id="re_inp_pass" class="inp_pass">
                    <lable for="inp_pass" class="la_pass" id="re_la_pass">Re-password</lable>
                    <br>
                </div>
                <div class="show_wrap">
                    <input type="checkbox" class="show_pass" id="show_pass"> Show password
                    </div>
                <div style="margin-bottom:30px" class="wrap_forpass">
                </div>
                <div class="wrap_foot">
                    <div class="wrap_logo_img">
                        <img onclick="window.location.href='Home.jsp'" width="90px" src="images/logo.png" class="img_logo">
                    </div>      
                    <div class="wrap_sub_btn">
                        <input type="submit" name="sub" class="sub_btn" id="sub_btn" value="Change">
						<div style="display:none" id="loader" class="custom-loader"></div>
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

            if(document.getElementById('re_inp_pass').value.length == 0){
                if(document.activeElement === document.getElementById("re_inp_pass")){
                    document.getElementById("re_la_pass").style.transform="translateY(-10px) translateX(-230px)";
                    document.getElementById('re_inp_pass').style.transform="translateY(10px)"; 
                }else{
                    document.getElementById("re_la_pass").style.transform="translateX(-230px)";
                    document.getElementById('re_inp_pass').style.transform="translateY(0)";
                }
            }else{
                document.getElementById("re_la_pass").style.transform="translateY(-10px) translateX(-230px)";
                document.getElementById('re_inp_pass').style.transform="translateY(10px)"; 
            }

            if(document.getElementById("show_pass").checked == true){
                document.getElementById("inp_pass").type ="text";
                document.getElementById("re_inp_pass").type ="text";
            }else{
                document.getElementById("inp_pass").type ="password";
                document.getElementById("re_inp_pass").type ="password";
            }
       },5);
       function validateForm() {
        let repass = document.forms["wrap_form"]["re_inp_pass"].value;
        let pass = document.forms["wrap_form"]["inp_pass"].value;
        if (pass == "" && repass == "") {
            document.getElementById("inp_pass").style.borderColor="red";
            document.getElementById("re_inp_pass").style.borderColor="red";
          document.getElementById('mes').innerText = "Please input all field!";
          return false;
        }else{
          if(pass.length < 9){
              document.getElementById('mes').innerText = "Password is weak, try another!";
              document.getElementById("inp_pass").style.borderColor="red";
              return false;
          }else if(repass.length == 0){
              document.getElementById('mes').innerText = "Please input re-password field!";
              document.getElementById("inp_pass").style.borderColor="black";
              document.getElementById("re_inp_pass").style.borderColor="red";
              return false;
          }else if(repass !== pass){
            document.getElementById('mes').innerText = "Re-password and new password is not same";
            document.getElementById("inp_pass").style.borderColor="black";
            document.getElementById("re_inp_pass").style.borderColor="red";
            return false;
          }else{
            document.getElementById("inp_pass").style.borderColor="black";
            document.getElementById("re_inp_pass").style.visibility="";
            access();
            return true;
          }
        }
      }
       function access(){
    	   document.getElementById("sub_btn").style.display = "none";
    	   document.getElementById("loader").style.display = "block";
       }
    </script>
</body>
</html>