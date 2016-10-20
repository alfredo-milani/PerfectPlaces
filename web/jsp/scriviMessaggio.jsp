<%--
  Created by IntelliJ IDEA.
  User: alfredo
  Date: 20/10/16
  Time: 15.41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="c" scope="session" class="control.ControlloreLogin"/>
<%@ page import="control.ControlloreGestionePosta" %>
<%
    ControlloreGestionePosta cgp = new ControlloreGestionePosta();
    String mittente = c.getUser();
    String oggetto = request.getParameter("oggetto");
    String destinatario = request.getParameter("destinatario");
    String contenuto = request.getParameter("contenuto");
    int controllo = cgp.scriviMessaggio(oggetto, mittente, destinatario, contenuto);
%>

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
				<h1>Scrivi il tuo messaggio</h1>
			</div>
		</div>
	</div>
	<!-- end #header -->

	<div id="page">
		<div id="page-bgtop">
			<div id="page-bgbtm">
				<div id="content">

					<div class="post"><h2><strong>Messaggio:</strong></h2></div>
					
					<div class="post">
					
						<form action="scriviMessaggio.jsp?Msg=1" method="post">
						
							<table width="100%">
                                <tr>
                                    <td>
                                        <label> Mittente: </label>

                                            <%
                                                String mittenteDefault = c.getUser();
                                                if (mittente != null) {
                                                    out.println(mittenteDefault);
                                                }
                                            %>
                                        
                                    </td>
                                </tr>

								<tr>
                                    <td>
                                        <label for="destinatario">Destinatario:</label>
                                    </td>
								</tr>
								<tr>
									<td>
                                        <input id="destinatario" type="text" name="destinatario"/>
                                    </td>
								</tr>

								<tr>
									<td>
                                        <label for="oggetto">Oggetto:</label>
                                    </td>
								</tr>
								<tr>	
									<td>
                                        <input id="oggetto" type="text" name="oggetto" value=""/>
                                    </td>
								</tr>

								<tr>
								    <td>
                                        <label for="contenuto">Contenuto:</label>
                                    </td>
								</tr>
								<tr>
									<td>
                                        <textarea id="contenuto" name="contenuto" rows="10" cols="100%"></textarea>
                                    </td>
								</tr>

								<tr>
									<center>
									    <td style="text-align: center">
									        <input class="btn_2" type="submit" value="Invia messaggio" />
									    </td>
                                    </center>
								</tr>
							</table>
						
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

    <div id="footer"></div>
    <!-- end #footer -->

</body>
</html>
