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
<jsp:useBean id="b" scope="session" class="control.ControlloreLogin"/>
<%

String mittente = request.getParameter("mittente");

%>

<body>
	<div id="menu-wrapper">
		<div id="menu">			
		</div>
		<!-- end #menu -->
	</div>

<div id="wrapper">
	<div id="header-wrapper">
		<div id="header">
			<div id="logo">
				<h1><a href="#">Scrivi il tuo messaggio</a></h1>
				
			</div>
		</div>
	</div>
	<!-- end #header -->
	<div id="page">
		<div id="page-bgtop">
			<div id="page-bgbtm">
				<div id="content">
				
					
					<div class="post">
							<h2><strong>Messaggio:</strong></h2>
					</div>
					
					<div class="post">
					
						<form action="scriviMessaggio2.jsp">
						
							<table width="100%">
								<tr>
									<td>Destinatario:</td>
								</tr>
								<tr>	
									<td><input type="text" name="destinatario" value="<%out.println(mittente);%>"></input></td>
								</tr>
								<tr>
									<td>Oggetto:</td>
								</tr>
								<tr>	
									<td><input type="text" name="oggetto" value=""></input></td>
								</tr>
								<tr>
								<td>Contenuto:</td>
								</tr>
								<tr>
									<td><textarea name="contenuto" rows="10" cols="100%" maxlength="5000"></textarea></td>
								</tr>
								<tr>
									<center>
									<td>
									<input type="submit" value="Invia messaggio!" />
									</td>	
									</center>
								</tr>
							</table>
						
						</form>
					
					
					</div>
					
					
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
