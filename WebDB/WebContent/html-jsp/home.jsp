<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/Home.css">

<script src="html-jsp/jquery-1.9.1.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.2/jquery-ui.min.js"></script>




<script>

$("#menu").resizable({
    handles: 'e, w'
});



$(document).ready(function(){
	 $("#menu").load("html-jsp/menu.jsp");
	 var $xxx = 'df';
	 var $dtt = '<iframe id="menu2" style="position:absolute; height:100%; width:100%;"></iframe>';
	 $($dtt).appendTo($("#menu"));
	 
	 var menu2window = $("#menu2")[0].contentWindow.window;

	 $(menu2window).resize(function(){
	 	  var object = $(this);
	 	  var $menuwidth = $("#menu").width();
	 	  var $container = $("#container").width();
	 	  $("#content").width($container-$menuwidth);

	 	});
	 
});

$(document).ready(function() {
var menu2window = $("#menu2")[0].contentWindow.window;

$(menu2window).resize(function(){
	  var object = $(this);
	  var $menuwidth = $("#menu").width();
	  var $container = $("#container").width();
	  $("#content").width($container-$menuwidth);

	});

});


</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div id="container">

<div id="menu"  style="position:relative; height:100%;width:20%;float:left;" >

</div>
<div id="content" style="background-color:#EEEEEE;height:100%;width:75%;float:left;">
Content goes here</div>

</div>

</body>
</html>