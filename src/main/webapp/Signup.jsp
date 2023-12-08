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
    } request.getSession().setAttribute("tove",null);
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
    <link rel="shortcut icon" href="images/icon.png">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Ubuntu:wght@500&display=swap" rel="stylesheet">
    <title>MusLife | Sign in</title>
</head>
<body>
    <div class="wrap_log">
        <div class="wrap_form">
            <form class="form_log" method="post" action="Signup">
                <h1 style="text-align: left;" class="head_log">
                    Sign up
                </h1>
                <p class="info">Sign in to like your favourite song and create your own playlist !</p>
                <div class="usname_wrap">
                    <input type="text" class="usname_in" id="usname_in" name="usname">
                    <label for="usname" id="name_la" class="name_la">Username</label>
                </div>
                <div  style="transition:0.25s;color:red; font-size:12px; margin-top:-20px; margin-bottom:45px;">
                	<p style="transition:0.25s;" id="mes0"></p>
                	<p style="transition:0.25s;" id="mes01"></p>
                </div>
                <div class="email_wrap">
                    <input id="mail_in" type="text" name="email" class="mail_in">
                    <label id="mail_la" class="mail_la" for="email">Email</label>
                </div>
                <div  style="transition:0.25s;color:red; font-size:12px; margin-top:-20px; margin-bottom:50px;">
                	<p style="transition:0.25s;" id="mes1"></p>
                </div>
                <div class="pass_wrap">
                    <input id="pass_in" type="password" name="pass" class="pass_in">
                    <label id="pas_la" class="pas_la" for="pass">Password</label>
                    <br>
                </div>
                 <div  style="transition:0.25s;color:red; font-size:12px; margin-top:-20px; margin-bottom:50px;">
                	<p style="transition:0.25s;" id="mes2"></p>
                </div>
                 <div class="repass_wrap">
                    <input id="repass_in" type="password" name="pass" class="repass_in">
                    <label id="repas_la" class="repas_la" for="pass">Repassword</label>
                    <br>
                    <div class="wrap_check"><input  type="checkbox" id="showpass" class="showpass" ><p class="text_check">Show password</p></div>
                </div>
                <div  style="transition:0.25s;color:red; font-size:12px; margin-top:10px;margin-bottom:10px;">
                	<p style="transition:0.25s;" id="mes"></p>
                </div>
                <div  style="transition:0.25s;color:red; font-size:12px; margin-top:10px;margin-bottom:10px;">
                	<p style="transition:0.25s;" id="mes3"></p>
                </div>
                <div class="wrap_foot_form">
                    <a class="link" href="Login.jsp">Have account?</a>
                </div>
                <div class="wrap_sub">
					<a class="link" href="index.jsp" style="backdrop-filter:blur(30px);background-color:black;border-radius:10px"><img style="padding:5%;" src="images/logo.png" alt="logo web" width="120px"></a>
                    <input onclick="access()" type="submit" name="Login" value="Sign up" class="Log_in" id="Log_in">
                	<div style="display:none;" id="loader" class="loader"></div>
                </div>
            </form>
        </div>
    </div>
    <script>
        setInterval(()=>{
            if(document.getElementById('usname_in').value.length == 0){
                if(document.activeElement === document.getElementById("usname_in")){
                    document.getElementById("name_la").style.transform="translateY(-25px) translateX(-300px)";
                    document.getElementById('usname_in').style.transform="translateY(-7px)"; 
                }else{
                    document.getElementById("name_la").style.transform="translateY(-15px) translateX(-300px)";
                    document.getElementById('usname_in').style.transform="translateY(-17px)";
                }
            }else{
                document.getElementById("name_la").style.transform="translateY(-25px) translateX(-300px)";
                document.getElementById('usname_in').style.transform="translateY(-7px)"; 
            }

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

            if(document.getElementById('repass_in').value.length == 0){
                if(document.activeElement === document.getElementById("repass_in")){
                    document.getElementById("repas_la").style.transform="translateY(-25px) translateX(-300px)";
                    document.getElementById('repass_in').style.transform="translateY(-7px)"; 
                }else{
                    document.getElementById("repas_la").style.transform="translateY(-15px) translateX(-300px)";
                    document.getElementById('repass_in').style.transform="translateY(-17px)";
                }
            }else{
                document.getElementById("repas_la").style.transform="translateY(-25px) translateX(-300px)";
                document.getElementById('repass_in').style.transform="translateY(-7px)"; 
            }

            if(document.getElementById("showpass").checked == true){
                document.getElementById("repass_in").type ="text";
                document.getElementById("pass_in").type ="text";
            }else{
                document.getElementById("repass_in").type ="password";
                document.getElementById("pass_in").type ="password";
            }
            
            let mail = document.getElementById('mail_in').value;
            let name = document.getElementById('usname_in').value;
            if(name.length != 0){
            if(name.length < 10 || name.length > 20){
            	document.getElementById('mes01').innerText = "Username must at least 10 - 20 character";
            		if(name.match(/^[A-Za-z0-9]*$/)){
                		document.getElementById('usname_in').style.color="black";
                		document.getElementById('mes0').innerText = ""
    	            	if(mail.length == 0){
    	            		document.getElementById('mes1').innerText = "";
    	            		document.getElementById('Log_in').disabled = true;
    	            		if(document.getElementById('pass_in').value.length == 0){
    	            			document.getElementById('Log_in').disabled = true;
    	            		}else{
    	            			if(document.getElementById('pass_in').value.length > 8){
    	            				document.getElementById('mes2').innerText = "";
    		            			if(document.getElementById('repass_in').value.length == 0){
    		                			document.getElementById('Log_in').disabled = true;
    		                		}else{
    		                			if(document.getElementById('repass_in').value != document.getElementById('pass_in').value){
    		                				document.getElementById('mes').innerText = "Re-enter password not right";
    		                				document.getElementById('Log_in').disabled = true;
    		                    		}else{
    		                    			document.getElementById('mes').innerText = "";
    		                    			document.getElementById('Log_in').disabled = true;
    		                    		}
    		                		}
    	            			}else{
    	            				document.getElementById('mes2').innerText = "Your password is to short!";
    	    	            		document.getElementById('Log_in').disabled = true;
    	            			}
    	            		}
    	            	}else{
    	            		if(mail.match(/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|.(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/)){
    	            			document.getElementById('mes1').innerText = "";
    	            			document.getElementById('Log_in').disabled = true;
    	            			if(document.getElementById('pass_in').value.length == 0){
    		            			document.getElementById('Log_in').disabled = true;
    		            		}else{
    		            			if(document.getElementById('pass_in').value.length > 8){
    		            				document.getElementById('mes2').innerText = "";
    			            			if(document.getElementById('repass_in').value.length == 0){
    			                			document.getElementById('Log_in').disabled = true;
    			                		}else{
    			                			if(document.getElementById('repass_in').value != document.getElementById('pass_in').value){
    			                				document.getElementById('mes').innerText = "Re-enter password not right";
    			                				document.getElementById('Log_in').disabled = true;
    			                    		}else{
    			                    			document.getElementById('mes').innerText = "";
    			                    			document.getElementById('Log_in').disabled = true;
    			                    		}
    			                		}
    		            			}else{
    		            				document.getElementById('mes2').innerText = "Your password is to short!";
    		    	            		document.getElementById('Log_in').disabled = true;
    		            			}
    		            		}
    		            	}else{
    		            		document.getElementById('mes1').innerText = "Invalid email!";
    		            		document.getElementById('Log_in').disabled = true;
    		            		if(document.getElementById('pass_in').value.length == 0){
    		            			document.getElementById('Log_in').disabled = true;
    		            		}else{
    		            			if(document.getElementById('pass_in').value.length > 8){
    		            				document.getElementById('mes2').innerText = "";
    			            			if(document.getElementById('repass_in').value.length == 0){
    			                			document.getElementById('Log_in').disabled = true;
    			                		}else{
    			                			if(document.getElementById('repass_in').value != document.getElementById('pass_in').value){
    			                				document.getElementById('mes').innerText = "Re-enter password not right";
    			                				document.getElementById('Log_in').disabled = true;
    			                    		}else{
    			                    			document.getElementById('mes').innerText = "";
    			                    			document.getElementById('Log_in').disabled = true;
    			                    		}
    			                		}
    		            			}else{
    		            				document.getElementById('mes2').innerText = "Your password is to short!";
    		    	            		document.getElementById('Log_in').disabled = true;
    		            			}
    		            		}
    		            	}
    	            	}
                	}else{
                		document.getElementById('mes0').innerText = "Invalid username!"
                		document.getElementById('usname_in').style.color="red";
                		document.getElementById('Log_in').disabled = true;
                		if(mail.length == 0){
    	            		document.getElementById('mes1').innerText = "";
    	            		document.getElementById('Log_in').disabled = true;
    	            		if(document.getElementById('pass_in').value.length == 0){
    	            			document.getElementById('Log_in').disabled = true;
    	            		}else{
    	            			if(document.getElementById('pass_in').value.length > 8){
    	            				document.getElementById('mes2').innerText = "";
    		            			if(document.getElementById('repass_in').value.length == 0){
    		                			document.getElementById('Log_in').disabled = true;
    		                		}else{
    		                			if(document.getElementById('repass_in').value != document.getElementById('pass_in').value){
    		                				document.getElementById('mes').innerText = "Re-enter password not right";
    		                				document.getElementById('Log_in').disabled = true;
    		                    		}else{
    		                    			document.getElementById('mes').innerText = "";
    		                    			document.getElementById('Log_in').disabled = true;
    		                    		}
    		                		}
    	            			}else{
    	            				document.getElementById('mes2').innerText = "Your password is to short!";
    	    	            		document.getElementById('Log_in').disabled = true;
    	            			}
    	            		}
    	            	}else{
    	            		if(mail.match(/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|.(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/)){
    	            			document.getElementById('mes1').innerText = "";
    	            			document.getElementById('Log_in').disabled = true;
    	            			if(document.getElementById('pass_in').value.length == 0){
    		            			document.getElementById('Log_in').disabled = true;
    		            		}else{
    		            			if(document.getElementById('pass_in').value.length > 8){
    		            				document.getElementById('mes2').innerText = "";
    			            			if(document.getElementById('repass_in').value.length == 0){
    			                			document.getElementById('Log_in').disabled = true;
    			                		}else{
    			                			if(document.getElementById('repass_in').value != document.getElementById('pass_in').value){
    			                				document.getElementById('mes').innerText = "Re-enter password not right";
    			                				document.getElementById('Log_in').disabled = true;
    			                    		}else{
    			                    			document.getElementById('mes').innerText = "";
    			                    			document.getElementById('Log_in').disabled = true;
    			                    		}
    			                		}
    		            			}else{
    		            				document.getElementById('mes2').innerText = "Your password is to short!";
    		    	            		document.getElementById('Log_in').disabled = true;
    		            			}
    		            		}
    		            	}else{
    		            		document.getElementById('mes1').innerText = "Invalid email!";
    		            		document.getElementById('Log_in').disabled = true;
    		            		if(document.getElementById('pass_in').value.length == 0){
    		            			document.getElementById('Log_in').disabled = true;
    		            		}else{
    		            			if(document.getElementById('pass_in').value.length > 8){
    		            				document.getElementById('mes2').innerText = "";
    			            			if(document.getElementById('repass_in').value.length == 0){
    			                			document.getElementById('Log_in').disabled = true;
    			                		}else{
    			                			if(document.getElementById('repass_in').value != document.getElementById('pass_in').value){
    			                				document.getElementById('mes').innerText = "Re-enter password not right";
    			                				document.getElementById('Log_in').disabled = true;
    			                    		}else{
    			                    			document.getElementById('mes').innerText = "";
    			                    			document.getElementById('Log_in').disabled = true;
    			                    		}
    			                		}
    		            			}else{
    		            				document.getElementById('mes2').innerText = "Your password is to short!";
    		    	            		document.getElementById('Log_in').disabled = true;
    		            			}
    		            		}
    		            	}
    	            	}
                	}
            }else{
            	document.getElementById('mes01').innerText = "";
            	if(name.match(/^[A-Za-z0-9]*$/)){
            		document.getElementById('usname_in').style.color="black";
            		document.getElementById('mes0').innerText = ""
	            	if(mail.length == 0){
	            		document.getElementById('mes1').innerText = "";
	            		document.getElementById('Log_in').disabled = true;
	            		if(document.getElementById('pass_in').value.length == 0){
	            			document.getElementById('Log_in').disabled = true;
	            		}else{
	            			if(document.getElementById('pass_in').value.length > 8){
	            				document.getElementById('mes2').innerText = "";
		            			if(document.getElementById('repass_in').value.length == 0){
		                			document.getElementById('Log_in').disabled = true;
		                		}else{
		                			if(document.getElementById('repass_in').value != document.getElementById('pass_in').value){
		                				document.getElementById('mes').innerText = "Re-enter password not right";
		                				document.getElementById('Log_in').disabled = true;
		                    		}else{
		                    			document.getElementById('mes').innerText = "";
		                    			document.getElementById('Log_in').disabled = true;
		                    		}
		                		}
	            			}else{
	            				document.getElementById('mes2').innerText = "Your password is to short!";
	    	            		document.getElementById('Log_in').disabled = true;
	            			}
	            		}
	            	}else{
	            		if(mail.match(/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|.(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/)){
	            			document.getElementById('mes1').innerText = "";
	            			if(document.getElementById('pass_in').value.length == 0){
		            			document.getElementById('Log_in').disabled = true;
		            		}else{
		            			if(document.getElementById('pass_in').value.length > 8){
		            				document.getElementById('mes2').innerText = "";
			            			if(document.getElementById('repass_in').value.length == 0){
			                			document.getElementById('Log_in').disabled = true;
			                		}else{
			                			if(document.getElementById('repass_in').value != document.getElementById('pass_in').value){
			                				document.getElementById('mes').innerText = "Re-enter password not right";
			                				document.getElementById('Log_in').disabled = true;
			                    		}else{
			                    			document.getElementById('mes').innerText = "";
			                    			document.getElementById('Log_in').disabled = false;
			                    		}
			                		}
		            			}else{
		            				document.getElementById('mes2').innerText = "Your password is to short!";
		    	            		document.getElementById('Log_in').disabled = true;
		            			}
		            		}
		            	}else{
		            		document.getElementById('mes1').innerText = "Invalid email!";
		            		document.getElementById('Log_in').disabled = true;
		            		if(document.getElementById('pass_in').value.length == 0){
		            			document.getElementById('Log_in').disabled = true;
		            		}else{
		            			if(document.getElementById('pass_in').value.length > 8){
		            				document.getElementById('mes2').innerText = "";
			            			if(document.getElementById('repass_in').value.length == 0){
			                			document.getElementById('Log_in').disabled = true;
			                		}else{
			                			if(document.getElementById('repass_in').value != document.getElementById('pass_in').value){
			                				document.getElementById('mes').innerText = "Re-enter password not right";
			                				document.getElementById('Log_in').disabled = true;
			                    		}else{
			                    			document.getElementById('mes').innerText = "";
			                    			document.getElementById('Log_in').disabled = false;
			                    		}
			                		}
		            			}else{
		            				document.getElementById('mes2').innerText = "Your password is to short!";
		    	            		document.getElementById('Log_in').disabled = true;
		            			}
		            		}
		            	}
	            	}
            	}else{
            		document.getElementById('mes0').innerText = "Invalid username!";
            		document.getElementById('usname_in').style.color="red";
            		document.getElementById('Log_in').disabled = true;
            		if(mail.length == 0){
	            		document.getElementById('mes1').innerText = "";
	            		document.getElementById('Log_in').disabled = true;
	            		if(document.getElementById('pass_in').value.length == 0){
	            			document.getElementById('Log_in').disabled = true;
	            		}else{
	            			if(document.getElementById('pass_in').value.length > 8){
	            				document.getElementById('mes2').innerText = "";
		            			if(document.getElementById('repass_in').value.length == 0){
		                			document.getElementById('Log_in').disabled = true;
		                		}else{
		                			if(document.getElementById('repass_in').value != document.getElementById('pass_in').value){
		                				document.getElementById('mes').innerText = "Re-enter password not right";
		                				document.getElementById('Log_in').disabled = true;
		                    		}else{
		                    			document.getElementById('mes').innerText = "";
		                    			document.getElementById('Log_in').disabled = true;
		                    		}
		                		}
	            			}else{
	            				document.getElementById('mes2').innerText = "Your password is to short!";
	    	            		document.getElementById('Log_in').disabled = true;
	            			}
	            		}
	            	}else{
	            		if(mail.match(/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|.(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/)){
	            			document.getElementById('mes1').innerText = "";
	            			if(document.getElementById('pass_in').value.length == 0){
		            			document.getElementById('Log_in').disabled = true;
		            		}else{
		            			if(document.getElementById('pass_in').value.length > 8){
		            				document.getElementById('mes2').innerText = "";
			            			if(document.getElementById('repass_in').value.length == 0){
			                			document.getElementById('Log_in').disabled = true;
			                		}else{
			                			if(document.getElementById('repass_in').value != document.getElementById('pass_in').value){
			                				document.getElementById('mes').innerText = "Re-enter password not right";
			                				document.getElementById('Log_in').disabled = true;
			                    		}else{
			                    			document.getElementById('mes').innerText = "";
			                    			document.getElementById('Log_in').disabled = false;
			                    		}
			                		}
		            			}else{
		            				document.getElementById('mes2').innerText = "Your password is to short!";
		    	            		document.getElementById('Log_in').disabled = true;
		            			}
		            		}
		            	}else{
		            		document.getElementById('mes1').innerText = "Invalid email!";
		            		document.getElementById('Log_in').disabled = true;
		            		if(document.getElementById('pass_in').value.length == 0){
		            			document.getElementById('Log_in').disabled = true;
		            		}else{
		            			if(document.getElementById('pass_in').value.length > 8){
		            				document.getElementById('mes2').innerText = "";
			            			if(document.getElementById('repass_in').value.length == 0){
			                			document.getElementById('Log_in').disabled = true;
			                		}else{
			                			if(document.getElementById('repass_in').value != document.getElementById('pass_in').value){
			                				document.getElementById('mes').innerText = "Re-enter password not right";
			                				document.getElementById('Log_in').disabled = true;
			                    		}else{
			                    			document.getElementById('mes').innerText = "";
			                    			document.getElementById('Log_in').disabled = false;
			                    		}
			                		}
		            			}else{
		            				document.getElementById('mes2').innerText = "Your password is to short!";
		    	            		document.getElementById('Log_in').disabled = true;
		            			}
		            		}
		            	}
	            	}
            	}
            }
        }else{
        	document.getElementById('usname_in').style.color="black";
    		document.getElementById('mes0').innerText = ""
    			document.getElementById('mes01').innerText = "";
        	if(mail.length == 0){
        		document.getElementById('mes1').innerText = "";
        		document.getElementById('Log_in').disabled = true;
        		if(document.getElementById('pass_in').value.length == 0){
        			document.getElementById('Log_in').disabled = true;
        		}else{
        			if(document.getElementById('pass_in').value.length > 8){
        				document.getElementById('mes2').innerText = "";
            			if(document.getElementById('repass_in').value.length == 0){
                			document.getElementById('Log_in').disabled = true;
                		}else{
                			if(document.getElementById('repass_in').value != document.getElementById('pass_in').value){
                				document.getElementById('mes').innerText = "Re-enter password not right";
                				document.getElementById('Log_in').disabled = true;
                    		}else{
                    			document.getElementById('mes').innerText = "";
                    			document.getElementById('Log_in').disabled = true;
                    		}
                		}
        			}else{
        				document.getElementById('mes2').innerText = "Your password is to short!";
	            		document.getElementById('Log_in').disabled = true;
        			}
        		}
        	}else{
        		if(mail.match(/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|.(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/)){
        			document.getElementById('mes1').innerText = "";
        			document.getElementById('Log_in').disabled = true;
        			if(document.getElementById('pass_in').value.length == 0){
            			document.getElementById('Log_in').disabled = true;
            		}else{
            			if(document.getElementById('pass_in').value.length > 8){
            				document.getElementById('mes2').innerText = "";
	            			if(document.getElementById('repass_in').value.length == 0){
	                			document.getElementById('Log_in').disabled = true;
	                		}else{
	                			if(document.getElementById('repass_in').value != document.getElementById('pass_in').value){
	                				document.getElementById('mes').innerText = "Re-enter password not right";
	                				document.getElementById('Log_in').disabled = true;
	                    		}else{
	                    			document.getElementById('mes').innerText = "";
	                    			document.getElementById('Log_in').disabled = true;
	                    		}
	                		}
            			}else{
            				document.getElementById('mes2').innerText = "Your password is to short!";
    	            		document.getElementById('Log_in').disabled = true;
            			}
            		}
            	}else{
            		document.getElementById('mes1').innerText = "Invalid email!";
            		document.getElementById('Log_in').disabled = true;
            		if(document.getElementById('pass_in').value.length == 0){
            			document.getElementById('Log_in').disabled = true;
            		}else{
            			if(document.getElementById('pass_in').value.length > 8){
            				document.getElementById('mes2').innerText = "";
	            			if(document.getElementById('repass_in').value.length == 0){
	                			document.getElementById('Log_in').disabled = true;
	                		}else{
	                			if(document.getElementById('repass_in').value != document.getElementById('pass_in').value){
	                				document.getElementById('mes').innerText = "Re-enter password not right";
	                				document.getElementById('Log_in').disabled = true;
	                    		}else{
	                    			document.getElementById('mes').innerText = "";
	                    			document.getElementById('Log_in').disabled = true;
	                    		}
	                		}
            			}else{
            				document.getElementById('mes2').innerText = "Your password is to short!";
    	            		document.getElementById('Log_in').disabled = true;
            			}
            		}
            	}
        	}
        }
        },5);
        function access(){
        	document.getElementById("loader").style.display = "block";
        	document.getElementById("Log_in").style.display = "none";
        }
    </script>
</body>
</html>