<%--
  Created by IntelliJ IDEA.
  User: alfredo
  Date: 20/10/16
  Time: 15.41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="exception.DeserializzazioneException" %>
<%@ page import="constants.Constants" %>
<jsp:useBean id="c" scope="session" class="control.ControlloreLogin"/>

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
<%
    String un = request.getParameter("username");
    String pw = request.getParameter("password");
    try {
        c.login(un, pw);
    } catch (DeserializzazioneException e) {
        e.printStackTrace();
    }
%>

<div id="wrapper">
	<div id="header-wrapper">
		<div id="header">
			<div id="logo">

                <%
                    if (c.getLogged()) {
                %>

				    <h1> Benvenuto! </h1>
                    <h2> Sei registrato come: <% out.println(c.getUser()); %> </h2>

                <%  } else {
                        String redirectURL = "http://" + Constants.HOST_PORT + "jsp/_it_index.jsp?errLog=1";
                        response.sendRedirect(redirectURL);
                    }
                %>

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

				<!--  Parte utente loggato -->
					<div class="language">
						<table>
							<tr>
								<td>
									<label for="select"> Lingua: </label>
								</td>
								<td>
									<select id="select" class="btn" onchange="changeLang(this, '<%= c.getUser()%>', '<%= c.getPsw() %>')">
										<option id="it" value="italian"> Italiano </option>
										<option id="en" value="english"> Inglese </option>
									</select>
								</td>
							</tr>
						</table>
					</div>

					<div class="post">
						<h2 class="title">SCEGLI COSA FARE!</h2>
						<table>
						<tr>
						<td>
						<br /><br />
						<h1 class="title">
						<strong>
							<a href="areaViaggiatore.jsp?username=<%=request.getParameter("username")%>">AREA VIAGGIATORE</a>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="areaProprietario.jsp?username=<%=request.getParameter("username")%>">AREA PROPRIETARIO</a>
						</strong>
						</h1>
						</td>
						</tr>
							<tr>
								<td>
									<br /><br /><br /><br />
									Puoi accedere alle due aree del sistema:<br /><br />
									Area Viaggiatore: Dove potrai cercare facilmente le locazioni pi&ugrave; adatte a te e gestire le tue prenotazioni!<br /><br />
									Area Proprietario: Dove potrai inserire nuovi annunci e gestire le tue locazioni!
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
