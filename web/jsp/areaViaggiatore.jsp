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
<jsp:useBean id="c" scope="session" class="control.ControlloreLogin"/>
	<div id="menu-wrapper">
		<div id="menu">
		</div>
		<!-- end #menu -->
	</div>

<div id="wrapper">
	<div id="header-wrapper">
		<div id="header">
			<div id="logo">
				<h1><a href="#">Ricerca la tua locazione</a></h1>
				
			</div>
		</div>
	</div>
	<!-- end #header -->
	<div id="page">
		<div id="page-bgtop">
			<ul class="topnav" id=myTopnav">
				<li><a href="_it_utente.jsp">HOME</a></li>
				<li><a href="areaViaggiatore.jsp">Area Viaggiatore</a></li>
				<li><a href="areaProprietario.jsp">Area Proprietario</a></li>
				<li><a href="_it_profiloUtente.jsp">Visualizza profilo</a></li>
				<li><a href="_it_posta.jsp">Posta</a></li>
				<li><a href="_it_logout.jsp">Esci</a></li>
			</ul>
			<div class="post">
				<%
					if (!c.getLogged()) {
				%>

				<font size="4px" color="red"> Errore! Sessione scaduta. Accedi di nuovo per continuare. </font>

				<%
					}
				%>

				</h2>
			</div>
			<div id="page-bgbtm">
				<div id="content">
					<div class="post">
							<h2><strong>Clicca sulla locazione desiderata</strong></h2>

            <br><br><br>
    <div>
        <div style="float: left; width: 400px" >
            <h3>Albergo</h3>
            <input id ="albergo" type="radio" style="display: none" name="command" value="0" onclick="visualizza('albform','aptform','bebform','cvzform','ostform')" />
            <label class="drinkcard-cc albergo" for="albergo"></label>
        </div>
            <form method="get" action="prenotaLocazione.jsp" enctype="text/plain" id="albform" style="display:none">
        <div style="float: left">
            <br><br>
            <input type="hidden" name="command" id="command_1" value="0"/>
            Prezzo:<br /><input type="text" name="prezzo" value="" /><br />
            Provincia:<br /><input type="text" name="provincia" value="" /><br /><br />
            Data Inizio:<br /><input type="text" name="dataInizio" value="gg/mm/aaaa" /><br /><br />
            Data Fine:<br /><input type="text" name="dataFine" value="gg/mm/aaaa" /><br /><br />
            <input type="submit" class="btnBlue" value="Ricerca" /><br />
        </div>
        </form>
    </div>
    <div style="clear: both"></div>

    <div>
		<div style="float: left; width: 400px">
            <h3>Appartamento</h3>
        	<input id="appartamento" type="radio" style="display: none" name="command" value="1" onclick="visualizza('aptform','albform','bebform','cvzform','ostform')"/>
			<label class="drinkcard-cc appartamento" for="appartamento"></label>
		</div>
        	<form method="get" action="prenotaLocazione.jsp" enctype="text/plain" id="aptform" style="display:none">
        <div style="float: left">
            <br><br>
            <input type="hidden" name="command" id="command_2" value="1"/>
            Prezzo:<br /><input type="text" name="prezzo" value="" /><br /><br />
            Provincia:<br /><input type="text" name="provincia" value="" /><br /><br />
            Data Inizio:<br /><input type="text" name="dataInizio" value="gg/mm/aaaa" /><br />
            Data Fine:<br /><input type="text" name="dataFine" value="gg/mm/aaaa" /><br /><br />
            <input type="submit"class="btnBlue" value="Ricerca" /><br />
        </div>
            </form>
    </div>
	<div style="clear: both"></div>

	<div>
		<div style="float: left; width: 400px">
            <h3>Bed and Breakfast</h3>
			<input id="beb"type="radio" style="display:none" name="command" value="2" onclick="visualizza('bebform','aptform','albform','cvzform','ostform')"/>
			<label class=" drinkcard-cc beb" for="beb"></label>
		</div>
			<form method="get" action="prenotaLocazione.jsp" enctype="text/plain" id="bebform" style="display:none">
	
		<div style="float: left">
            <br><br>
            <input type="hidden" name="command" id="command_3" value="2"/>
            Prezzo:<br /><input type="text" name="prezzo" value="" /><br /><br />
            Provincia:<br /><input type="text" name="provincia" value ="" /><br /><br />
            Data Inizio:<br /><input type="text" name="dataInizio" value="gg/mm/aaaa" /><br/>
            Data Fine:<br /><input type="text" name="dataFine" value="gg/mm/aaaa" /><br /><br />
            <input type="submit" class="btnBlue" value="Ricerca" /><br />
		</div>
		
	        </form>
	</div>
    <div style="clear: both"></div>

	<div>
      <div style="float:left; width: 400px">
        <h3>Casa Vacanza</h3>
          <br>
        <input id="casaVacanza" type="radio" style="display:none" name="command" value="3" onclick="visualizza('cvzform','aptform','bebform','albform','ostform')"/>
        <label class="drinkcard-cc casaVacanza" for="casaVacanza"></label>
      </div>
        <form method="get" action="prenotaLocazione.jsp" enctype="text/plain" id="cvzform" style="display:none">

	    <div style="float:left">
            <br><br>
            <input type="hidden" name="command" id="command_4" value="3"/>
            Prezzo:<br /><input type="text" name="prezzo" value="" /><br /><br />
            Provincia:<br /><input type="text" name="provincia" value="" /><br /><br />
            Data Inizio:<br /><input type="text" name="dataInizio" value="gg/mm/aaaa" /><br/>
            Data Fine:<br /><input type="text" name="dataFine" value="gg/mm/aaaa" /><br /><br />
            <input type="submit" class="btnBlue" value="Ricerca" /><br />
        </div>

	    </form>
    </div>
    <div style="clear: both"></div>

	<div>
        <div style="float:left; width: 400px">
          <h3>Ostello</h3>
          <input id="ostello" type="radio" style="display:none" name="command" value="4" onclick="visualizza('ostform','aptform','bebform','cvzform','albform')"/>
          <label class="drinkcard-cc ostello" for="ostello" ></label>
        </div>
	      <form method="get" action="prenotaLocazione.jsp" enctype="text/plain" id="ostform" style="display:none">

	    <div style="float: left">
            <br><br>
            <input type="hidden" name="command" id="command" value="4"/>
            Prezzo:<br /><input type="text" name="prezzo" value="" /><br /><br />
            Provincia:<br /><input type="text" name="provincia" value="" /><br /><br />
            Data Inizio:<br /><input type="text" name="dataInizio" value="gg/mm/aaaa" /><br/>
            Data Fine:<br /><input type="text" name="dataFine" value="gg/mm/aaaa" /><br /><br />
            <input type="submit" class="btnBlue" value="Ricerca" /><br />
	    </div>
	     </form>
    </div>
    <div style="clear: both"></div>
		
</div>		

</div>
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
