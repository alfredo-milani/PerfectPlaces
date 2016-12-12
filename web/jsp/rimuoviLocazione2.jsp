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
</head>
<jsp:useBean id="c" scope="session" class="control.ControlloreLogin"/>
<%@page import="java.util.ArrayList" %>
<%@page import="entity.Locazione" %>
<%@ page import="boundary.BoundaryGestioneLocazioni" %>
<%@ page import="exception.SerializzazioneException" %>
<%@ page import="exception.DeserializzazioneException" %>
<%
    BoundaryGestioneLocazioni bgl = new BoundaryGestioneLocazioni();

    String id = request.getParameter("id");
    id = id.substring(0,1);


    int idInt = Integer.parseInt(id);

    ArrayList<Locazione> elencoLocazioni= (ArrayList<Locazione>) request.getSession().getAttribute("loc");
    try {
        bgl.avvioRimozione(elencoLocazioni.get(idInt));
    } catch (SerializzazioneException | DeserializzazioneException e) {
        e.printStackTrace();
    }
    request.getSession().removeAttribute("loc");


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
				<h1><a href="#">Rimozione Locazioni</a></h1>
				
			</div>
		</div>
	</div>
	<!-- end #header -->
	<div id="page">
		<div id="page-bgtop">
			<ul class="topnav" id=myTopnav">
				<li><a href="utente.jsp">HOME</a></li>
				<li><a href="areaViaggiatore.jsp">Area Viaggiatore</a></li>
				<li><a href="areaProprietario.jsp">Area Proprietario</a></li>
				<li><a href="profiloUtente.jsp">Visualizza profilo</a></li>
				<li><a href="posta.jsp">Posta</a></li>
				<li><a href="logout.jsp">Esci</a></li>
			</ul>
			<div class="post">
				<%
					if (!c.getLogged()) {
				%>

				<font size="4px" color="red"> Errore! Sessione scaduta. Accedi di nuovo per continuare. </font>

				<%
					}
				%>

			</div>

			<div id="page-bgbtm">
				<div id="content">
				
					
					<div class="post">
							<h2><strong>La tua locazione è stata rimossa con successo</strong></h2>
							
					</div>
					
					<div style="clear: both;">&nbsp;</div>
				</div>
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