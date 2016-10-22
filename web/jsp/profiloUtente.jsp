<%--
  Created by IntelliJ IDEA.
  User: alfredo
  Date: 20/10/16
  Time: 15.41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html lang="it" xml:lang="it" xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <title>Perfect Places</title>
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700" rel="stylesheet" type="text/css" />
    <link href="../css/style.css" rel="stylesheet" type="text/css" media="screen" />
    <script type="text/javascript" src="../js/functions.js"></script>
</head>

<body>
<jsp:useBean id="c" scope="session" class="control.ControlloreLogin" />
<jsp:useBean id="cgp" scope="session" class="control.ControlloreGestioneProfilo" />
<jsp:useBean id="crl" scope="session" class="control.ControlloreRicercaLocazione" />
<%@page import="entity.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="constants.Constants" %>
<%@ page import="exception.DeserializzazioneException" %>
<%
    String username = c.getUser();
    Utente u = null;
    try {
        u = cgp.ottieniUtente(username);
    } catch (DeserializzazioneException e) {
        e.printStackTrace();
    }
    String indirizzoImmagine = Constants.IMGS_PATH_REL_S + u.getImmagine();
    ArrayList<Locazione> locazioni = null;
    try {
        locazioni = crl.ricercaLocPerUser(username);
    } catch (DeserializzazioneException e) {
        e.printStackTrace();
    }
%>

<div id="wrapper">
	<div id="header-wrapper">
		<div id="header">
			<div id="logo">
				<h1>Profilo Personale</h1>
				<h2> Sei registrato come: <% out.println(c.getUser()); %> </h2>
			</div>
		</div>
	</div>
	<!-- end #header -->
	<div id="page">
		<div id="page-bgtop">
            <!-- Menu -->
            <ul class="topnav" id=myTopnav">
                <li><a href="_it_utente.jsp">HOME</a></li>
                <li><a href="areaViaggiatore.jsp">Area Viaggiatore</a></li>
                <li><a href="areaProprietario.jsp">Area Proprietario</a></li>
                <li><a href="profiloUtente.jsp">Visualizza profilo</a></li>
                <li><a href="posta.jsp">Posta</a></li>
                <li><a href="_it_logout.jsp">Esci</a></li>
            </ul>

			<div id="page-bgbtm">
				<div id="content">
				
					<div class="post">
						<h2 class="title">IL TUO PROFILO</h2>

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
                                    <p><img src="<%= indirizzoImmagine%>" width="150" height="150"
                                            alt="Immagine Profilo"/></p>
                                </td>
                                <td>
                                    <h3 class="blackclass">
                                        <strong>Nome utente:</strong><%out.println(" " + u.getUsername());%> <br/>
                                        <strong>Nome:</strong><%out.println(" " + u.getNome());%> <br/>
                                        <strong>Cognome:</strong><%out.println(" " + u.getCognome());%> <br/>
                                        <strong>Email:</strong><%out.println(" " + u.getEmail());%> <br/>
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
                                            if (locazioni.size() == 0) {
                                                out.println("Per ora non hai alcuna locazione.");
                                            } else {
                                                int i;
                                                for (i = 0; i < locazioni.size() - 1; ++i) {
                                                    Locazione loc = locazioni.get(i);
                                                    out.println(loc.getNomeLocazione() + " / ");
                                                }
                                                out.println(locazioni.get(i).getNomeLocazione());
                                            }
                                        %>

                                    </h3>
                                    <br/>
                                    <br/>
                                    <h2>
                                        <strong><a href="modificaProfiloUtente.jsp">Modifica profilo</a></strong>
                                    </h2>
                                </td>
                            </tr>
                        </table>
					</div>
					<div style="clear: both;">&nbsp;</div>
				</div>
				<!-- end #content -->

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