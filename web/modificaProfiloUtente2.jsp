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
<%@page import="control.ControlloreGestioneProfilo"%>
<%
String username = b.getUser();
String nome = request.getParameter("nome");
String cognome = request.getParameter("cognome");
String email = request.getParameter("email");
String vecchiaPassword = request.getParameter("vecchiaPassword");
String nuovaPassword = request.getParameter("nuovaPassword");
String confermaNuovaPassword = request.getParameter("confermaNuovaPassword");
String indirizzoImmagine = request.getParameter("indirizzoImmagine");
int controllo;

ControlloreGestioneProfilo cgp = new ControlloreGestioneProfilo();

controllo = cgp.modificaProfilo(username, nome, cognome, email, vecchiaPassword, nuovaPassword, confermaNuovaPassword);

if(!indirizzoImmagine.equals(""))
	cgp.copiaImmagine(indirizzoImmagine, username);


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
				<h1><a href="#">Profilo Personale</a></h1>
				
			</div>
		</div>
	</div>
	<!-- end #header -->
	<div id="page">
		<div id="page-bgtop">
			<div id="page-bgbtm">
				<div id="content">
					
					<% if(controllo==0){ %>
					
					<div class="post">
						
						<h2>Profilo modificato correttamente!<br></br>
						<a href="profiloUtente.jsp">Torna al tuo profilo</a></h2>
				
					</div>
				
					<% }else if(controllo==1){ %>
					
					<div class="post">
						
						<h2>La vecchia password inserita non &egrave; corretta!<br></br>
						<a href="modificaProfiloUtente.jsp">Riprova</a></h2>
					</div>
					
					<%} else if(controllo == 2){ %>
					
					<div class="post">
						
						<h2>La nuova password e la conferma della nuova password sono diverse!<br></br>
						<a href="modificaProfiloUtente.jsp">Riprova</a></h2>
					</div>
					
					
					<%} else if(controllo == 3){ %>
					
					<div class="post">
						
						<h2>La nuova password non pu&ograve; essere vuota!<br></br>
						<a href="modificaProfiloUtente.jsp">Riprova</a></h2>
					</div>
					
					<% }%>
					
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