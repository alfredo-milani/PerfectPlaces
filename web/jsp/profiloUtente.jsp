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
<%
    String username = c.getUser();
	Utente u = cgp.ottieniUtente(username);
	String indirizzoImmagine = Constants.IMGS_PATH_REL_S + u.getImmagine();
	ArrayList<Locazione> locazioni = crl.ricercaLocPerUser(username);
%>

<div id="wrapper">
	<div id="header-wrapper">
		<div id="header">
			<div id="logo">
				<h1>Profilo Personale</h1>
			</div>
		</div>
	</div>
	<!-- end #header -->
	<div id="page">
		<div id="page-bgtop">
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
										<p><img src="<%= indirizzoImmagine%>" width="150" height="150" alt="Immagine Profilo" /></p>
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
											    if (locazioni.size() == 0) {
                                                    out.println("Per ora non hai alcuna locazione.");
                                                } else {
                                                    for (Locazione aLocazioni : locazioni) {
                                                        out.println(aLocazioni.getNomeLocazione() + " |");
                                                    }
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