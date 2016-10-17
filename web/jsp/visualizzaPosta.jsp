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
<jsp:useBean id="b" scope="session" class="control.ControlloreLogin"/>
<%@page import="control.ControlloreGestionePosta" %>
<%@page import="java.util.ArrayList" %>
<%@page import="entity.Messaggio" %>
<%

String username = b.getUser();
String oggetto;
String mittente;

ControlloreGestionePosta cgp = new ControlloreGestionePosta();
ArrayList<Messaggio> elencoMessaggiUser = new ArrayList<Messaggio>();


elencoMessaggiUser = cgp.ricercaMessaggiPerDestinatario(username);


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
				<h1><a href="#">Visualizza i tuoi messaggi</a></h1>
				
			</div>
		</div>
	</div>
	<!-- end #header -->
	<div id="page">
		<div id="page-bgtop">
			<div id="page-bgbtm">
				<div id="content">
				
					
					<div class="post">
							<h2><strong>Questi sono i tuoi messaggi</strong></h2>
							
					</div>
					
					<div class="post">
						<table width="100%">
							<tr>
								<td><strong>Mittente</strong></td>
								<td><strong>Oggetto</strong></td>
							</tr>
					
					
					<% for(int i=0;i<elencoMessaggiUser.size();i++){
						 oggetto = elencoMessaggiUser.get(i).getOggetto();
						 mittente = elencoMessaggiUser.get(i).getMittente();						 
					
					%>
							<tr>
								<td>
									<%out.println(elencoMessaggiUser.get(i).getMittente());%>
								</td>
								<td>
									<%out.println(elencoMessaggiUser.get(i).getOggetto());%>
								</td>
								<td>
									<form action="visualizzaPosta2.jsp">
									<div>
									<input type="hidden" name="codice" value="<%out.println(elencoMessaggiUser.get(i).getCodice());%>" /> 
									<input type="submit" value="Leggi"/>
									</div>
									</form>
								</td>		
							</tr>
						
					<% } %>
					
					</table>
					
					
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
