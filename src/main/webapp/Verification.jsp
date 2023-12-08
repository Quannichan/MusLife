<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% if(request.getSession().getAttribute("tove") == null){
	String site = "notfound.jsp" ;
	response.setStatus(response.SC_MOVED_TEMPORARILY);
	response.setHeader("Location", site); 
} %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="images/icon.png">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Ubuntu:wght@500&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="CSS/Verification.css">
    <title>Authentication | MusLife</title>
</head>
<body>
    <div class="wrap">
        <div class="wrap_f">
        <div class="w_o">
            <form class="form" action="Verification" method="post">
                <div class="inpcode">
                <input id="inp1" type="text" onkeydown="del()" class="inp1" maxlength="1" name="inp1"><input id="inp2" onkeydown="del()" type="text" maxlength="1" class="inp1" name="inp2"><input id="inp3" onkeydown="del()" class="inp1" type="text" maxlength="1" name="inp3"><input onkeydown="del()" id="inp4" class="inp1"type="text" 
                maxlength="1" name="inp4">
                <div style="margin-top:10px; margin-bottom:10px">
                	<p id="mes" style="color:red; font-size:14px"></p>
                </div>
                </div>
                <div class="wrap_manual">
                <h4>Get verification code in your email</h4>
                </div>
                <div class="wrap_sub">
                    <div onclick="window.location.href='index.jsp'" class="w_logo">
                    <img width="85px" src="images/logo.png">
                    </div>
                    <input onclick="access()" name="sub" type="submit" id="sub_but" value="Next" class="sub_but">
					<div style="display:none" id="loader" class="custom-loader"></div>
                </div>
            </form>
            	<form action="Verification" method="post">
	            	<div class="wrap_resent">
	                    <input onclick="access()" id="resent" type="submit" name="sub" class="resent" value="Cannot get code?">
	                </div>
                </form>
            </div>
        </div>
    </div>
    <script>
        setInterval(()=>{
            if(inp1.value != ""){
                inp2.disabled= false;
                if(count1 === 0){
                    count1= 1;
                    inp2.focus();
                }
            }else{
                count1 = 0
                inp2.disabled = true;
            }
            if(inp2.value!=""){
                inp3.disabled = false;
                if(count2 === 0){
                    count2 = 1;
                    inp3.focus();
                }
            }else{
                count2 = 0;
                inp3.disabled = true;
            }
            if(inp3.value != ""){
                inp4.disabled = false;
                if(count3 ===0){
                    count3 = 1;
                    inp4.focus();
                }
                
            }else{
                count3 = 0;
                inp4.disabled = true;
            }

            if(Number.isNaN(parseInt(document.getElementById("inp1").value)) == true){
                document.getElementById("inp1").value="";
                if(count1 === 1){
                    count1= 0;
                    inp1.focus();
                }
            }else{
            	count1 = 1;
                if(Number.isNaN(parseInt(document.getElementById("inp2").value)) == true){
                    document.getElementById("inp2").value="";
                    if(count2 === 1){
                        count2= 0;
                        inp2.focus();
                    }
                }else{
                	count2 = 1;
                    if(Number.isNaN(parseInt(document.getElementById("inp3").value)) == true){
                        document.getElementById("inp3").value="";
                        if(count3 === 1){
                            count3= 0;
                            inp3.focus();
                        }
                    }else{
                    	count3 = 1;
                        if(Number.isNaN(parseInt(document.getElementById("inp4").value)) == true){
                            document.getElementById("inp4").value="";
                        }else{
                        }
                    }   
                }   
            }

            if(document.getElementById("inp1").value != "" && document.getElementById("inp2").value != "" && document.getElementById("inp3").value!= ""&& document.getElementById("inp4").value != ""){
                document.getElementById("sub_but").disabled = false;
            }else{
                document.getElementById("sub_but").disabled = true;
            }
        },100);
        function del(){
            if(event.key === "Delete" || event.key === "Backspace" ){
                inp1.value = "";
                inp2.value = "";
                inp3.value = "";
                inp4.value = "";
            }
        }
        if(event.key === "Enter"){
        	
        }
        function access(){
        	document.getElementById("loader").style.display = "block";
        	document.getElementById("sub_but").style.display = "none";
        	document.getElementById("resent").style.display = "none";
        }
    </script>
</body>
</html>