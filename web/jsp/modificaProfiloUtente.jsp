<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>S&amp;M</title>
<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700" rel="stylesheet" type="text/css" />
<link href="../css/style.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>
<jsp:useBean id="b" scope="session" class="control.ControlloreLogin"/>
<%@page import="entity.*"%>
<%@page import="control.ControlloreGestioneProfilo"%>
<%

	String username = b.getUser();
	ControlloreGestioneProfilo cgp = new ControlloreGestioneProfilo();

	Utente u = cgp.ottieniUtente(username);
	
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
				<h1><a href="#">Modifica Profilo</a></h1>
				
			</div>
		</div>
	</div>
	<!-- end #header -->
	<div id="page">
		<div id="page-bgtop">
			<div id="page-bgbtm">
				<div id="content">
					<div class="post">
						
						<h2 class="title">MODIFICA IL TUO PROFILO</h2>
					
							<form action="modificaProfiloUtente2.jsp">
							<table>
							<tr>
							<td>Nome:</td>
							</tr>
							<tr>	
							<td><input type="text" name="nome" size="30" value="<%out.println(u.getNome());%>"/></td>
							</tr>
							<tr>
							<td>Cognome:</td>
							</tr>
							<tr>
							<td><input type="text" name="cognome" size="30" value="<%out.println(u.getCognome());%>"/></td>
							</tr>
							<tr>
							<td>Email:</td>
							</tr>
							<tr>
							<td><input type="text" name="email" size="30" value="<%out.println(u.getEmail());%>"/></td>
							</tr>
							<tr>
							<td>Vecchia Password:</td>
							</tr>
							<tr>
							<td><input type="password" name="vecchiaPassword" size="30" value=""/></td>
							</tr>	
							<tr>
							<td>Nuova Password:</td>
							</tr>
							<tr>
							<td><input type="password" name="nuovaPassword" size="30" value=""/></td>
							</tr>
							<tr>
							<td>Conferma Nuova Password:</td>
							</tr>
							<tr>
							<td><input type="password" name="confermaNuovaPassword" size="30" value=""/></td>
							</tr>
							<tr>
							<td>Cambia Immagine:</td>
							</tr>
							<tr>
							<td><input type="file" name="indirizzoImmagine" size="30" /></td>
							</tr>
							</table>				
							<center>
							<br /><br /><br />
							<input type="submit" value="Salva modifiche" />
							</center>
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
						<h2><strong><a href="_it_logout.jsp">Logout</a></strong></h2>
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