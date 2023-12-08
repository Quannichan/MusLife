<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.getSession().setAttribute("tove",null);
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
    <link rel="stylesheet" href="CSS/notfound.css">
    <title>404 NOT FOUND | MusLife</title>
        <link rel="shortcut icon" href="images/icon.png">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Ubuntu:wght@500&display=swap" rel="stylesheet">
</head>
<body>
    <div class="wrap">
        <div class="wrap_404">
            <p class="text_404">404 NOT FOUND</p>
            <p class="text">Look like your directory is not right =))</p>
        </div>
    </div>
</body>
</html>