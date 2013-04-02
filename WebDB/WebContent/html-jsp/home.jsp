
<!-- saved from url=(0028)http://localhost/index2.html -->
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="/WebDB/javascript/jquery-1.9.1.js"></script>
		<script type="text/javascript" src="/WebDB/javascript/jquery-ui-1.10.2.custom.js"></script>
		<link href="/WebDB/javascript/jquery-ui-1.10.2.custom.min.css" rel="stylesheet" type="text/css">
		<link rel="stylesheet" type="text/css" href="/WebDB/css/Home.css">
		<script type="text/javascript">
				
			$(document).ready(function(){
				$(".rightcol").css("margin-top",$("#floating_menubar").height());
				$(".leftcol").resizable({
					resize: function(e, ui){
						$(".rightcol").css("margin-left",ui.size.width + 10);
						$("#floating_menubar").css("margin-left",ui.size.width + 10);
						$(".leftcol .ui-resizable-e").css("left",ui.size.width);
					}
				});
				
				$.get('html-jsp/menu.jsp', function (data) {
				    $('.leftcol').append(data);
				});
				
			});
		</script>
	</head>
	<body style="cursor: auto;">
		<div class="colwrapper">
			<div class="leftcol" style="width:300px;">
			</div>
			
			<div id="floating_menubar" style="margin-left: 310px; left: 0px; position: fixed; top: 0px; width: 100%; z-index: 500;"><div id="serverinfo">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam ligula arcu, gravida in tincidunt sit amet, feugiat nec tellus. Ut tristique sodales massa non lacinia. Fusce in erat mauris. Nulla ornare vulputate turpis, in luctus justo gravida eget. Suspendisse porta eros in tellus semper varius. Nam sit amet placerat risus. Proin dictum, augue vitae aliquet scelerisque, enim nisl tristique libero, nec placerat neque elit vitae metus.<div class="clearfloat"></div></div><div id="topmenucontainer" class="menucontainer">
			<div class="clearfloat"></div></div></div>
			
			<div class="rightcol" style="margin-left:310px;">
				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam ligula arcu, gravida in tincidunt sit amet, feugiat nec tellus. Ut tristique sodales massa non lacinia. Fusce in erat mauris. Nulla ornare vulputate turpis, in luctus justo gravida eget. Suspendisse porta eros in tellus semper varius. Nam sit amet placerat risus. Proin dictum, augue vitae aliquet scelerisque, enim nisl tristique libero, nec placerat neque elit vitae metus.</p>
				<p>Cras nec urna ante. Cras enim turpis, scelerisque et sodales a, tincidunt ac urna. Morbi non auctor risus. Etiam sollicitudin dui at risus venenatis fermentum. Sed porttitor volutpat elit sed placerat. Nulla posuere malesuada nisi euismod vehicula. Cras neque arcu, rutrum ut laoreet a, mattis et enim. Maecenas et risus nulla, vitae semper tortor.</p>
				<p>In augue mauris, cursus quis euismod at, bibendum id metus. Integer auctor tellus ut orci accumsan rhoncus. Suspendisse potenti. Nulla id urna urna. Vivamus placerat fringilla risus in bibendum. Nulla vulputate rhoncus nisl, non lacinia arcu eleifend non. Praesent imperdiet pharetra sem, quis eleifend quam pellentesque eget. Maecenas congue nulla non quam accumsan at consectetur metus consequat. Sed rutrum tempus diam in convallis. Etiam vel lorem ut turpis pellentesque volutpat. Proin consequat, quam id pretium interdum, lacus ligula bibendum lacus, tincidunt interdum velit enim sed est.</p>
				<p>Sed et sapien sit amet lectus dignissim volutpat dignissim vitae nunc. Morbi rhoncus semper gravida. Integer vitae ante metus. Aenean commodo orci non enim pharetra sollicitudin. Phasellus sit amet laoreet tortor. Quisque mi sapien, aliquet vel viverra ac, dignissim non orci. Aenean pulvinar odio in massa malesuada nec rutrum turpis luctus. Integer a adipiscing felis. Ut ut ante eget lacus facilisis luctus vel non arcu. Pellentesque laoreet ipsum metus. Sed sit amet scelerisque mauris.</p>
				<p>Nulla commodo tempus velit, sed facilisis nulla semper ut. Suspendisse pulvinar tempor condimentum. Quisque mattis cursus mi, ut fermentum urna elementum in. Maecenas vestibulum elit bibendum arcu volutpat sed fringilla nunc consequat. Morbi sed nisi nec sem ornare mollis. Integer vitae ipsum quam. Morbi vitae lectus sed ante accumsan euismod sit amet eget erat. Vestibulum nec urna in risus lobortis ornare et eget quam. Aliquam gravida ullamcorper sem, nec imperdiet sapien eleifend a. Vivamus vitae tortor quam, non vehicula tortor.</p>
			</div>
		</div>
	
</body></html>
