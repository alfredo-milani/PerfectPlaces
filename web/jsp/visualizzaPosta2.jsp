<%--
  Created by IntelliJ IDEA.
  User: alfredo
  Date: 20/10/16
  Time: 15.41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="c" scope="session" class="control.ControlloreLogin"/>
<%@page import="control.ControlloreGestionePosta" %>
<%@page import="entity.Messaggio" %>

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

<body>
<div id="wrapper">
	<div id="header-wrapper">
		<div id="header">
			<div id="logo">
				<h1>Visualizza i tuoi messaggi</h1>
				<h2> Sei registrato come: <% out.println(c.getUser()); %> </h2>
			</div>
		</div>
	</div>
	<!-- end #header -->
	<div id="page">
		<div id="page-bgtop">
			<div id="page-bgbtm">
				<div id="content">
					
					<div class="post">
							<h2><strong>Messaggio selezionato:</strong>

                                <%
                                    String strCodice = request.getParameter("Cod");
                                    Messaggio messaggio = null;
                                    if (c.getLogged()) {
                                        if (strCodice != null) {
                                            int codice = Integer.parseInt(strCodice);
                                            ControlloreGestionePosta cgp = new ControlloreGestionePosta();
                                            messaggio = cgp.ricercaMessaggioPerCodice(codice);
                                        }
                                    } else {
                                %>

                                        <font color="red"> Errore! Effettua di nuovo l'accesso per visualizzare i messaggi </font>

                                <% } %>

                            </h2>
					</div>
					
					<div class="post">
						<table width="100%">
							<tr>
								<td><label>Mittente:</label></td>

                                <%
                                    if (c.getLogged() && strCodice != null && messaggio != null) {
                                %>

								        <td><%out.println(messaggio.getMittente());%></td>

                                <% } %>

							</tr>

							<tr>
								<td><label>Oggetto:</label></td>

                                <%
                                    if (c.getLogged() && strCodice != null && messaggio != null) {
                                %>

								        <td><%out.println(messaggio.getOggetto());%></td>

                                <% } %>

							</tr>
                        </table>

                        <br />
						<label>Contenuto:</label><br /><br />

                        <%
                            if (c.getLogged() && strCodice != null && messaggio != null) {
                        %>

                                <div class="break-word">
                                    <%out.println(messaggio.getContenuto());%>
                                </div>

                        <% } %>

                        <br /><br />

                        <form action="scriviMessaggio.jsp">
                            <div style="text-align: center">
                                <input class="btn_2" type="submit" value="Rispondi"/>
                            </div>
                        </form>
                    </div>
					
					
					<div style="clear: both;">&nbsp;</div>
				</div>
				<!-- end #content -->
				<!-- Menu -->
				
				<div id="sidebar">
					<% if (c.getLogged()) {  %>
					
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
