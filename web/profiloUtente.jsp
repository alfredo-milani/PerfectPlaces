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
<%@page import="entity.*"%>
<%@page import="control.ControlloreGestioneProfilo"%>
<%@page import="control.ControlloreRicercaLocazione"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="constants.Constants" %>
<%

	String username = b.getUser();
	
	
	ControlloreGestioneProfilo cgp = new ControlloreGestioneProfilo();

	Utente u = cgp.ottieniUtente(username);
	
	String indirizzoImmagine = Constants.ABS_PATH.concat("web/images/img"+u.getUsername()+".jpg");
	
	ControlloreRicercaLocazione crl = new ControlloreRicercaLocazione();
	ArrayList<Locazione> locazioni = new ArrayList<Locazione>();
	
	locazioni = crl.ricercaLocPerUser(username);
	
	

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
				
					<div class="post">
						<h2 class="title"><a href="#">IL TUO PROFILO</a></h2>
						
							<table width="100%">
								<tr>
									<td>
										<h2 class="title"><strong>I tuoi dati</strong></h2>
									</td>
									<td>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
								</tr>
								<tr>
									<td>
										<p><img src="<%out.println(indirizzoImmagine);%>" width="150" height="150" alt="" /></p>
									</td>
									<td>
										<h3 class="blackclass">
										<strong>Username:</strong><%out.println(" "+u.getUsername());%> <br />
										<strong>Nome:</strong><%out.println(" "+u.getNome());%> <br />
										<strong>Cognome:</strong><%out.println(" "+u.getCognome());%> <br />
										<strong>Email:</strong><%out.println(" "+u.getEmail());%> <br />
										</h3>
									</td>
								</tr>
							</table>
							<table>	
								<tr>
									<td>	
										<h1 class="title"><strong>Locazioni:</strong></h1>
										<h3 class="blackclass">
										<%
											for(int i = 0; i<locazioni.size();i++){
												out.println(locazioni.get(i).getNomeLocazione()+" |");
											}
										%></h3>
										<br></br>
										<br></br>
										<h2><strong><a href="modificaProfiloUtente.jsp">Modifica profilo</a></strong></h2>
									</td>
								</tr>
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