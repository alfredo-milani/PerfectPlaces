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
	<div id="menu-wrapper">
		<div id="menu">
		</div>
		<!-- end #menu -->
	</div>

<div id="wrapper">
	<div id="header-wrapper">
		<div id="header">
			<div id="logo">
				<h1><a href="#">Ricerca la tua locazione!</a></h1>
				
			</div>
		</div>
	</div>
	<!-- end #header -->
	<div id="page">
		<div id="page-bgtop">
			<div id="page-bgbtm">
				<div id="content">

					<div class="post">
						<h2 class="title">Seleziona la locazione adatta a te!</h2>
					
						<table style="width:100%">
							<tr>
								<td>
									<center>
									<h1 style="color:black">ALBERGHI</h1>
									</center>
								</td>
								<td>
									<center>
									<h1 style="color:black">APPARTAMENTI</h1>
									</center>
								</td>
								<td>
									<center>
									<h1 style="color:black">B&amp;B</h1>
									</center>
								</td>
								<td>
									<center>
									<h1 style="color:black">CASE VACANZA</h1>
									</center>
								</td>
								<td>
									<center>
									<h1 style="color:black">OSTELLI</h1>
									</center>
								</td>
							</tr>
							<tr>
								<td>
									<center>
									<img src="images/albergo.jpg" width="150" height="150"/>
									</center>
								</td>
								<td>
									<center>
									<img src="images/appartamento.jpg" width="150" height="150"/>
									</center>
								</td>
								<td>
									<center>
									<img src="images/beb.jpg"  width="150" height="150"/>
									</center>
								</td>
								<td>
									<center>
									<img src="images/vacanza.jpg"  width="150" height="150"/>
									</center>
								</td>
								<td>
									<center>
									<img src="images/ostello.jpg"  width="150" height="150"/>
									</center>
								</td>
							</tr>
						</table>
					</div>
					
					<div class="post">
							<h2><strong>Ricerca la tua locazione!</strong></h2>
							
							<!-- Script JavaScript per far vedere solo i parametri di ricerca del tipo di locazione cliccato -->
							<script type="text/javascript" src="../js/visualizza_scelte.js" ></script>
							
	<input type="radio" name="command" value="0" onclick="visualizza('albform','aptform','bebform','cvzform','ostform')"/>Albergo<br />

	<form method="get" action="prenotaLocazione.jsp" enctype="text/plain" id="albform" style="display:none">
	
	<div>
		<input type="hidden" name="command" id="command_1" value="0"/>
        Prezzo:<br /><input type="text" name="prezzo" value="" />&euro;<br /><br />
		Data Inizio:<br /><input type="text" name="dataInizio" value="gg/mm/aaaa" /><br /><br />
		Data Fine:<br /><input type="text" name="dataFine" value="gg/mm/aaaa" /><br /><br />
		<input type="submit" value="Ricerca" /><br />
	</div>

	</form>

	<input type="radio" name="command" value="1" onclick="visualizza('aptform','albform','bebform','cvzform','ostform')"/>Appartamento<br />

	<form method="get" action="prenotaLocazione.jsp" enctype="text/plain" id="aptform" style="display:none">

	<div>
	
		<input type="hidden" name="command" id="command_2" value="1"/>
		Prezzo:<br /><input type="text" name="prezzo" value="" />&euro;<br /><br />
		Data Inizio:<br /><input type="text" name="dataInizio" value="gg/mm/aaaa" /><br /><br />
		Data Fine:<br /><input type="text" name="dataFine" value="gg/mm/aaaa" /><br /><br />
		<input type="submit" value="Ricerca" /><br />
	
	</div>
	
	</form>			
	
	<input type="radio" name="command" value="2" onclick="visualizza('bebform','aptform','albform','cvzform','ostform')"/>Bed&amp;Breakfast<br />

	<form method="get" action="prenotaLocazione.jsp" enctype="text/plain" id="bebform" style="display:none">
	
		<div>

		<input type="hidden" name="command" id="command_3" value="2"/>
		Prezzo:<br /><input type="text" name="prezzo" value="" />&euro;<br /><br />
		Data Inizio:<br /><input type="text" name="dataInizio" value="gg/mm/aaaa" /><br /><br />
		Data Fine:<br /><input type="text" name="dataFine" value="gg/mm/aaaa" /><br /><br />
		<input type="submit" value="Ricerca" /><br />
		
		</div>
		
	</form>			
	
	<input type="radio" name="command" value="3" onclick="visualizza('cvzform','aptform','bebform','albform','ostform')"/>Casa Vacanza<br />

	<form method="get" action="prenotaLocazione.jsp" enctype="text/plain" id="cvzform" style="display:none">
	
	<div>

		<input type="hidden" name="command" id="command_4" value="3"/>
		Prezzo:<br /><input type="text" name="prezzo" value="" />&euro;<br /><br />
		Data Inizio:<br /><input type="text" name="dataInizio" value="gg/mm/aaaa" /><br /><br />
		Data Fine:<br /><input type="text" name="dataFine" value="gg/mm/aaaa" /><br /><br />
		<input type="submit" value="Ricerca" /><br />
		
		</div>

	</form>			
	
	<input type="radio" name="command" value="4" onclick="visualizza('ostform','aptform','bebform','cvzform','albform')"/>Ostello<br />

	<form method="get" action="prenotaLocazione.jsp" enctype="text/plain" id="ostform" style="display:none">
	
	<div>

		<input type="hidden" name="command" id="command" value="4"/>
		Prezzo:<br /><input type="text" name="prezzo" value="" />&euro;<br /><br />
		Data Inizio:<br /><input type="text" name="dataInizio" value="gg/mm/aaaa" /><br /><br />
		Data Fine:<br /><input type="text" name="dataFine" value="gg/mm/aaaa" /><br /><br />
		<input type="submit" value="Ricerca" /><br />

	</div>

	</form>			
		
</div>		

</div>
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
				<!-- end #sidebar -->
				<div style="clear: both;">&nbsp;</div>
			</div>
		</div>
	</div>
	<!-- end #page -->
<div id="footer">
	
</div>
<!-- end #footer -->
</body>
</html>
