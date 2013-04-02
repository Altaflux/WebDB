<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/Menu.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="/WebDB/html-jsp/jquery-1.9.1.js"></script>
<script type="text/javascript">
 
function toggle(id) {
	var e = id;
	if (e.style.display == ''){
		e.style.display = 'none';
	}
	else {
		e.style.display = '';
	}	
}
function tableRetreive(databseid) {
	var isd = document.getElementById(databseid);
	if ($(isd).is(':empty')) {
	$(isd).empty();
	$.get('/WebDB/TableRetreive',{database :$(isd).attr('id')},function(responseJson) {
				$.each(responseJson,function(index, item) {
					var $dt = '<li><a href="#" onclick="columnRetreive(\'tb'+item+'\')\"><img border="0" src="images/table.png"  width="16" height="16"></a>'+ item + '<ul id="tb'+item+'" style="display:none;"></ul style="display:none;"></li>';

					$($dt).appendTo($(isd));
					});
		});
	
	toggle(isd);
	}
	else{
		toggle(isd);
	}
}

function columnRetreive(tablename) {
	var id = document.getElementById(tablename);
	if ($("#" + tablename).is(':empty')) {
	$("#" + tablename).empty();
	var database = $("#" + tablename).parent().parent().attr("id");
	$.get('/WebDB/ColumnRetreive',{database : database, tablename : tablename},function(responseJson) {
				$.each(responseJson,function(index, item) {
					var $dt = "<li><img border='0' src='images/table.png'  width='16' height='16'>"+ item + "</li>";
					$($dt).appendTo($("#" + tablename));
					});
		});
		toggle(id);
	}
		else{
			toggle(id);
		}
}

</script>
  <script type="text/javascript">
   $(document).ready(function () {
     $('ul.tree li:last-child').addClass('last');
   });
  </script>
  
 <script>
(function ($) {

jQuery.expr[":"].Contains = jQuery.expr.createPseudo(function(arg) {
    return function( elem ) {
        return jQuery(elem).text().toUpperCase().indexOf(arg.toUpperCase()) >= 0;
    };
});

	  function listFilter(header, list) {

	    var form = $("<form>").attr({"class":"filterform","action":"#"}),
	        input = $("<input>").attr({"class":"filterinput","type":"text"});
	    $(form).append(input).appendTo(header);
	    $(input)
	      .change( function () {
	        var filter = $(this).val();
	        if(filter) {
	          $(list).children().children("a:not(:Contains(" + filter + "))").parent().each(function(i,event){
	        	  this.style.display="none";
	          });
	          $(list).children().children("a:Contains(" + filter + ")").parent().each(function(i,event){
	        	  this.style.display="";
	          });
	        } else {
	          $(list).find("li").css("display", "" );
	        }
	        return false;
	      })
	    .keyup( function () {
	        $(this).change();
	    });
	  }
	  //ondomready
	  $(function () {
	    listFilter($("#header"), $("#database"));
	  });
	}(jQuery));
</script>
</head>
<body>

<div id="tree_navigation">
<% ArrayList<String> dbList =(ArrayList<String>)session.getAttribute("dbList"); %>

<h1 id="header">Database List</h1>
<ul id="database" class="tree">
<%
	for(int i=0;i<dbList.size();i++){
		%>
		<li>
		<a href="#" onclick="tableRetreive('db<%=dbList.get(i)%>')"><img border="0" src="images/plus.png"  width="16" height="16"></a><a href="#" onclick="tableRetreive('db<%=dbList.get(i)%>')"><%=dbList.get(i)%></a>
		<ul id="db<%=dbList.get(i)%>" style="display:none;"></ul></li>
		<%
	}

%>
</ul>
</div>



</body>
</html>