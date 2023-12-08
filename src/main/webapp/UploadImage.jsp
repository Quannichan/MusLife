<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% if(request.getSession().getAttribute("upload") == null){
	String site = "notfound.jsp" ;
	response.setStatus(response.SC_MOVED_PERMANENTLY);
	response.setHeader("Location", site); 
}%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="shortcut icon" href="images/icon.png">
<link rel="stylesheet" href="CSS/uploadimage.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Ubuntu:wght@500&display=swap" rel="stylesheet">
<title>Upload Image</title>
</head>
<body>
<div id="wrap_out" class="wrap_out">
	<div class="wrap_upload">
		<div class="wrap_form_up">
			<div class="wrap_head_up">
				<p class="head_Up">Upload your account image</p>
			</div>
			<div class="wrap_img_pre">
				<img id="img_preview" class="img_preview">
			</div>
			<div style="margin:5px;text-align:center;">
				<p id="mes" style="color:red;font-size:12px;"></p>
			</div>
			<form class="form" action = "Upload" method = "post" enctype = "multipart/form-data">
				<div class="file_wrap">
				<input class="file" type="file" name="image" id="img_upload"  onchange="showPreview(event);">
				</div>
				<br>
				<div class="sub_wrap">
					<input class="skip_btn" type="submit" name="submit" value="Skip">
					<input disabled id="sub_btn" class="sub_btn"  type = "submit" name="submit" value="Upload">
				</div>
			</form>
		</div>
	</div>
</div>

	<script>
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
	 </script>
</body>
</html>