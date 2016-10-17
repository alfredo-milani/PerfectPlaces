<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>S&amp;M</title>
<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700" rel="stylesheet" type="text/css" />
<link href="css/css/style.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<jsp:useBean id="b" scope="session" class="control.ControlloreLogin"/>
<%@page import="control.ControlloreGestionePosta"%>

<%
ControlloreGestionePosta cgp = new ControlloreGestionePosta();
String mittente = b.getUser();
String oggetto = request.getParameter("oggetto");
String destinatario = request.getParameter("destinatario");
String contenuto = request.getParameter("contenuto");
int controllo;
controllo = cgp.scriviMessaggio(oggetto, mittente, destinatario, contenuto);

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
				
					
					<%if(controllo==1){%>
					<div class="post">
							<h2>
							<strong>Devi indicare un destinatario!</strong><br /><br />
							<a href="scriviMessaggio.jsp?mittente=">Torna al messaggio</a>
							</h2>
					</div>
					<%} else if(controllo == 2){ %>
					<div class="post">
							<h2>
							<strong>Devi scrivere un oggetto!</strong><br /><br />
							<a href="scriviMessaggio.jsp?mittente=">Torna al messaggio</a>
							</h2>
					</div>
					<%} else if(controllo == 3){ %>
					<div class="post">
							<h2>
							<strong>Devi scrivere un contenuto!</strong><br /><br />
							<a href="scriviMessaggio.jsp?mittente=">Torna al messaggio</a>
							</h2>
					</div>
					<%} else if(controllo == 4){ %>
					<div class="post">
							<h2>
							<strong>Il destinatario indicato non esiste!</strong><br /><br />
							<a href="scriviMessaggio.jsp?mittente=">Torna al messaggio</a>
							</h2>
					</div>
					<%} else if(controllo == 0){ %>
					<div class="post">
							<h2>
							<strong>Messaggio inviato correttamente!</strong><br /><br />
							<a href="_0_utente.jsp">Torna alla pagina Utente</a>
							</h2>
					</div>
					<%} %>
					
					
					
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
