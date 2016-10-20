<%--
  Created by IntelliJ IDEA.
  User: alfredo
  Date: 20/10/16
  Time: 15.41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <title>Perfect Places</title>
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700" rel="stylesheet" type="text/css" />
    <link href="../css/style.css" rel="stylesheet" type="text/css" media="screen" />
    <script type="text/javascript" src="../js/functions.js" ></script>
</head>

<body>
<jsp:useBean id="b" scope="session" class="control.ControlloreLogin"/>

	<div id="menu-wrapper">
		<div id="menu">
			
		</div>
		<!-- end #menu -->
	</div>

<div id="wrapper">
	<div id="header-wrapper">
		<div id="header">
			<div id="logo">
				<h1><a href="#">Inserisci la tua Locazione</a></h1>
				
			</div>
		</div>
	</div>
	<!-- end #header -->
	<div id="page">
		<div id="page-bgtop">
			<div id="page-bgbtm">
				<div id="content">
				
					
					<div class="post">
							<h2><strong>Compila il seguente modulo</strong></h2>
							
							
	<input type="radio" name="command" value="0" onclick="visualizza('albform','aptform','bebform','cvzform','ostform')"/>Albergo<br />

	<form method="get" action="riceviLocazione.jsp" enctype="text/plain" id="albform" style="display:none">
	<div>
		<input type="hidden" name="command" id="command_1" value="0"/>
		Nome Albergo:<br /><input type="text" name="nomeLocazione" value="" /><br /><br />
		Indirizzo:<br /><input type="text" name="indirizzo" value="" /><br /><br />
		Prezzo:<br /><input type="text" name="prezzo" value="" /><br /><br />
		Descrizione:<br /><input type="text" name="descrizione" value="" /><br /><br />
		Parcheggio:<br />
		S&igrave;<input type="radio" name="parcheggio" value="true" checked="checked" />
		No<input type="radio" name="parcheggio" value="false" /><br /><br />
		Wifi:<br />
		S&igrave;<input type="radio" name="wifi" value="true"  checked="checked" />
		No<input type="radio" name="wifi" value="false" /><br /><br />
		Animali ammessi:<br />
		S&igrave;<input type="radio" name="pet" value="true" checked="checked" />
		No<input type="radio" name="pet" value="false" /><br /><br />
		Camere Totali:<br /><input type="text" name="camereTotali" value="" /><br /><br />
		Tipo Pensione:<br /><input type="text" name="tipoPensione" value="" /><br /><br />
		Orario Colazione:<br /><input type="text" name="orarioColazione" value="" /><br /><br />
		Orario Pranzo:<br /><input type="text" name="orarioPranzo" value="" /><br /><br />
		Orario Cena:<br /><input type="text" name="orarioCena" value="" /><br /><br /> 

	    <input type="submit" value="Inserisci" /><br />
	</div>
	</form>									
							
	<input type="radio" name="command" value="1" onclick="visualizza('aptform','albform','bebform','cvzform','ostform')"/>Appartamento<br />

	<form method="get" action="riceviLocazione.jsp" enctype="text/plain" id="aptform" style="display:none">
	
	<div>
		<input type="hidden" name="command" id="command_2" value="1"/>
		Nome Appartamento:<br /> <input type="text" name="nomeLocazione" value="" /><br /><br />
		Indirizzo:<br /><input type="text" name="indirizzo" value="" /><br /><br />
		Prezzo:<br /><input type="text" name="prezzo" value="" /><br /><br />
		Descrizione:<br /><input type="text" name="descrizione" value="" /><br /><br />
		Parcheggio:<br />
		S&igrave;<input type="radio" name="parcheggio" value="true" checked="checked" />
		No<input type="radio" name="parcheggio" value="false" /><br /><br />
		Wifi:<br />
		S&igrave;<input type="radio" name="wifi" value="true" checked="checked" />
		No<input type="radio" name="wifi" value="false" /><br /><br />
		Animali ammessi:<br />
		S&igrave;<input type="radio" name="pet" value="true" checked="checked" />
		No<input type="radio" name="pet" value="false" /><br /><br />
		Numero Stanze:<br /><input type="text" name="numeroStanze" value="" /><br /><br />
		Numero Bagni:<br /><input type="text" name="numeroBagni" value="" /><br /><br />
		Giardino:<br />
		S&igrave;<input type="radio" name="giardino" value="true" checked="checked" />
		No<input type="radio" name="giardino" value="false" /><br /><br />
		Numero Letti:<br /><input type="text" name="numeroLetti" value="" /><br /><br />

	<input type="submit" VALUE="Inserisci" /><br />
	
	</div>


	</form>			
	
	
	<input type="radio" name="command" value="2" onclick="visualizza('bebform','aptform','albform','cvzform','ostform')"/>Bed&amp;Breakfast<br />

	<form method="get" action="riceviLocazione.jsp" enctype="text/plain" id="bebform" style="display:none">
	
	<div>

		<input type="hidden" name="command" id="command_3" value="2"/>
		Nome B&amp;B:<br /><input type="text" name="nomeLocazione" value="" /><br /><br />
		Indirizzo:<br /><input type="text" name="indirizzo" value="" /><br /><br />
		Prezzo:<br /><input type="text" name="prezzo" value="" /><br /><br />
		Descrizione:<br /><input type="text" name="descrizione" value="" /><br /><br />
		Parcheggio:<br />
		S&igrave;<input type="radio" name="parcheggio" value="true" checked="checked" />
		No<input type="radio" name="parcheggio" value="false" /><br /><br />
		Wifi:<br />
		S&igrave;<input type="radio" name="wifi" value="true" checked="checked" />
		No<input type="radio" name="wifi" value="false" /><br /><br />
		Animali ammessi:<br />
		S&igrave;<input type="radio" name="pet" value="true" checked="checked" />
		No<input type="radio" name="pet" value="false" /><br /><br />
		Camere Totali:<br /><input type="text" name="camereTotali" value="" /><br /><br />
		Orario Colazione:<br /><input type="text" name="orarioColazione" value="" /><br /><br />
		
		

	<input type="submit" value="Inserisci" /><br />
	
	</div>

	</form>			
	
	<input type="radio" name="command" value="3" onclick="visualizza('cvzform','aptform','bebform','albform','ostform')"/>Casa Vacanza<br />

	<form method="get" action="riceviLocazione.jsp" enctype="text/plain" id="cvzform" style="display:none">
	
	<div>

		<input type="hidden" name="command" id="command_4" value="3"/>
		Nome Casa Vacanza:<br /><input type="text" name="nomeLocazione" value="" /><br /><br />
		Indirizzo:<br /><input type="text" name="indirizzo" value="" /><br /><br />
		Prezzo:<br /><input type="text" name="prezzo" value="" /><br /><br />
		Descrizione:<br /><input type="text" name="descrizione" value="" /><br /><br />
		Parcheggio:<br />
		S&igrave;<input type="radio" name="parcheggio" value="true" checked="checked" />
		No<input type="radio" name="parcheggio" value="false" /><br /><br />
		Wifi:<br />
		S&igrave;<input type="radio" name="wifi" value="true" checked="checked" />
		No<input type="radio" name="wifi" value="false" /><br /><br />
		Animali ammessi:<br />
		S&igrave;<input type="radio" name="pet" value="true" checked="checked" />
		No<input type="radio" name="pet" value="false" /><br /><br />
		Numero Camere:<br /><input type="text" name="numeroCamere" value="" /><br /><br />
		Numero Bagni:<br /><input type="text" name="numeroBagni" value="" /><br /><br />
		Giardino:<br />
		S&igrave;<input type="radio" name="giardino" value="true" checked="checked" />
		No<input type="radio" name="giardino" value="false" /><br /><br />
		Numero Letti:<br /><input type="text" name="numeroLetti" value="" /><br /><br />

	<input type="submit" value="Inserisci" /><br />
	
	</div>


	</form>			
	
	<input type="radio" name="command" value="4" onclick="visualizza('ostform','aptform','bebform','cvzform','albform')"/>Ostello<br />

	<form method="get" action="riceviLocazione.jsp" enctype="text/plain" id="ostform" style="display:none">
	
	<div>
	
		<input type="hidden" name="command" id="command" value="4"/>
		Nome Ostello:<br /><input type="text" name="nomeLocazione" value="" /><br /><br />
		Indirizzo:<br /><input type="text" name="indirizzo" value="" /><br /><br />
		Prezzo:<br /><input type="text" name="prezzo" value="" /><br /><br />
		Descrizione:<br /><input type="text" name="descrizione" value="" /><br /><br />
		Parcheggio:<br />
		S&igrave;<input type="radio" name="parcheggio" value="true" checked="checked" />
		No<input type="radio" name="parcheggio" value="false" /><br /><br />
		Wifi:<br />
		S&igrave;<input type="radio" name="wifi" value="true" checked="checked" />
		No<input type="radio" name="wifi" value="false" /><br /><br />
		Animali ammessi:<br />
		S&igrave;<input type="radio" name="pet" value="true" checked="checked" />
		No<input type="radio" name="pet" value="false" /><br /><br />
		Numero Letti:<br /><input type="text" name="numeroLetti" value="" /><br /><br />

	<input type="submit" value="Inserisci" /><br />

	</div>

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