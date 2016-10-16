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
<%@page import="control.ControlloreRicercaLocazione" %>
<%@page import="java.util.ArrayList" %>
<%@page import="entity.Locazione" %>
<%

ControlloreRicercaLocazione crl = new ControlloreRicercaLocazione();

String username = b.getUser();
ArrayList<Locazione> locazioni = new ArrayList<Locazione>();
locazioni = crl.ricercaLocPerUser(username);

String nomeLocazione;
String indirizzo;
String prezzo; 
String descrizione;


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
				<h1><a href="#">Visualizza le tue locazioni</a></h1>
				
			</div>
		</div>
	</div>
	<!-- end #header -->
	<div id="page">
		<div id="page-bgtop">
			<div id="page-bgbtm">
				<div id="content">
				
					
					<div class="post">
							<h2><strong>Queste sono le tue locazioni</strong></h2>
							
					</div>
					
					
					<% for(int i=0;i<locazioni.size();i++){
						 nomeLocazione = locazioni.get(i).getNomeLocazione();
						 indirizzo = locazioni.get(i).getIndirizzo();
						 prezzo = locazioni.get(i).getPrezzo();
						 descrizione = locazioni.get(i).getDescrizione();
						 
					
					%>
					
					<div class="post">
						<h2>Nome Locazione:<%out.println(nomeLocazione);%></h2>
						<h1>Indirizzo: <%out.println(indirizzo); %></h1>
						<h1>Prezzo: <%out.println(prezzo); %></h1>
						<h1>Descrizione: <%out.println(descrizione); %></h1>
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
