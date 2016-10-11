<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>S&amp;M</title>
<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>
<jsp:useBean id="b" scope="session" class="control.ControlloreLogin"/>
<%
String un = request.getParameter("username");
String pw = request.getParameter("password");
    StringBuffer d = request.getRequestURL();
	System.err.println("username: " + un + "\t\tpassword: " + pw + "\n\nrequest: " + d);


b.login(un, pw);
%>
	<div id="menu-wrapper">
		<div id="menu">
		</div>
		<!-- end #menu -->
	</div>

<div id="wrapper">
	<div id="header-wrapper">
		<div id="header">
			<div id="logo">
				<h1><a href="#"><% out.println(b.getTitle(un));%></a></h1>
				
			</div>
		</div>
	</div>
	<!-- end #header -->
	<div id="page">
		<div id="page-bgtop">
			<div id="page-bgbtm">
				<div id="content">
				
				<!--  Parte utente loggato -->							
				
					<% if(b.getLogged()) { %>
					<div class="post">
						<h2 class="title">SCEGLI COSA FARE!</h2>
						<table>
						<tr>
						<td>
						<br /><br />
						<h1 class="title">
						<strong>
						<a href="areaViaggiatore.jsp?username=<%=request.getParameter("username")%>">AREA VIAGGIATORE</a>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="areaProprietario.jsp?username=<%=request.getParameter("username")%>">AREA PROPRIETARIO</a>
						</strong>
						</h1>
						</td>
						</tr>
						<tr>
						<td>
						<br /><br /><br /><br />
						Puoi accedere alle due aree del sistema:<br /><br />
						Area Viaggiatore: Dove potrai cercare facilmente le locazioni pi&ugrave; adatte a te e gestire le tue prenotazioni!<br /><br />
						Area Proprietario: Dove potrai inserire nuovi annunci e gestire le tue locazioni!				
						</td>
						</tr>
						</table>
						
					</div>
					
					<% } %>
					
					<!--  Parte utente non loggato -->
					
					<% if (!b.getLogged()) {  %>
					<div class="post">
						<h2 class="title">Errore!</h2>
						
						<h1>L'utente non &egrave; presente nel sistema o la password inserita &egrave; errata!</h1>
						
						<br />
						
						<a href="index.html">Torna alla Home Page</a>
							
					</div>
					
					<% } %>
					
					
					<div style="clear: both;">&nbsp;</div>
				</div>
				
				<!-- end #content -->
				
				<!-- Menu -->
				
				<div id="sidebar">
					<% if (b.getLogged()) {  %>
					
					<ul>
						<li>
						<center>
						<h2><strong><a href="areaViaggiatore.jsp">Area viaggiatore</a></strong></h2>
						</center>
						</li>
					</ul>
					<ul>
						<li>
						<center>
						<h2><strong><a href="areaProprietario.jsp">Area proprietario</a></strong></h2>
						</center>
						</li>
					</ul>
					<ul>
						<li>
						<center>
						<h2><strong><a href="profiloUtente.jsp">Visualizza profilo</a></strong></h2>
						</center>
						</li>
					</ul>		
					<ul>
						<li>
						<center>
						<h2><strong><a href="posta.jsp">Posta</a></strong></h2>
						</center>
						</li>
					</ul>	
					<ul>
						<li>
						<center>
						<h2><strong><a href="logout.jsp">Logout</a></strong></h2>
						</center>
						</li>
					</ul>						
					
					<%  }  %>
				</div>
				<!-- end #sidebar -->
				<div style="clear: both;">&nbsp;</div>
			</div>
		</div>
	</div>
	<!-- end #page -->
</div>
<div id="footer">
	
</div>
<!-- end #footer -->
</body>
</html>
