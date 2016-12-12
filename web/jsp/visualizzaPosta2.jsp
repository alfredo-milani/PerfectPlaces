<%--
  Created by IntelliJ IDEA.
  User: alfredo
  Date: 20/10/16
  Time: 15.41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="c" scope="session" class="control.ControlloreLogin"/>
<jsp:useBean id="cgp" scope="session" class="control.ControlloreGestioneProfilo"/>
<%@page import="control.ControlloreGestionePosta" %>
<%@page import="entity.Messaggio" %>
<%@ page import="exception.DeserializzazioneException" %>
<%@ page import="entity.Utente" %>
<%@ page import="java.util.Locale" %>
<%@ page import="control.ControlloreLingua" %>
<%@ page import="java.util.ResourceBundle" %>

<%
    ControlloreLingua controlloreLingua = new ControlloreLingua();
    Locale locale;
    try {
        locale = controlloreLingua.getLang(c.getUser());
    } catch (DeserializzazioneException e) {
        locale = controlloreLingua.getLang();
        e.printStackTrace();
    }
    ResourceBundle bundle = ControlloreLingua
            .getBundle(locale);
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
				<h1><%=bundle.getString("visualizzaPosta_msgs")%></h1>
				<h2> <%=bundle.getString("profiloUtente_registratoCome")%> <%=c.getUser() %> </h2>
			</div>
		</div>
	</div>
	<!-- end #header -->
	<div id="page">
		<div id="page-bgtop">
            <!-- Menu -->
            <ul class="topnav" id=myTopnav">
                <li><a href="utente.jsp"><%=bundle.getString("utente_home")%></a></li>
                <li><a href="areaViaggiatore.jsp"><%=bundle.getString("utente_areaViaggiatore")%></a></li>
                <li><a href="areaProprietario.jsp"><%=bundle.getString("utente_areaProprietario")%></a></li>
                <li><a href="profiloUtente.jsp"><%=bundle.getString("utente_profilo")%></a></li>
                <li><a href="posta.jsp"><%=bundle.getString("utente_posta")%></a></li>
                <li><a href="logout.jsp"><%=bundle.getString("utente_esci")%></a></li>
            </ul>

			<div id="page-bgbtm">
				<div id="content">
					
					<div class="post">
							<h2><strong><%=bundle.getString("visualizzaPosta_msgSelezionato")%></strong>

                                <%
                                    String strCodice = request.getParameter("Cod");
                                    Messaggio messaggio = null;
                                    if (c.getLogged()) {
                                        if (strCodice != null) {
                                            int codice = Integer.parseInt(strCodice);
                                            ControlloreGestionePosta cgposta = new ControlloreGestionePosta();
                                            try {
                                                messaggio = cgposta.ricercaMessaggioPerCodice(codice);
                                            } catch (DeserializzazioneException e) {
                                                e.printStackTrace();
                                            }
                                        }

                                        if (messaggio == null) {
                                %>

                                            <h4>
                                                <font color="red"> <%=bundle.getString("visualizzaPosta_erroreEliminato")%> </font>
                                            </h4>

                                <%
                                        }
                                    } else {
                                %>

                                        <font color="red"> <%=bundle.getString("visualizzaPosta_riaccediPerVisual")%> </font>

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

								        <td><%=messaggio.getMittente()%></td>

                                <% } %>

							</tr>

							<tr>
								<td><label>Oggetto:</label></td>

                                <%
                                    if (c.getLogged() && strCodice != null && messaggio != null) {
                                %>

								        <td><%=messaggio.getOggetto()%></td>

                                <% } %>

							</tr>
                        </table>

                        <br />
						<label>Contenuto:</label><br /><br />

                        <%
                            if (c.getLogged() && strCodice != null && messaggio != null) {
                        %>

                                <div class="break-word">
                                    <%=messaggio.getContenuto()%>
                                </div>

                        <% } %>

                        <br /><br />

                        <table align="center">
                            <tr>
                                <td>
                                    <form action="scriviMessaggio.jsp?Dest=<%= messaggio == null ? "" : messaggio.getMittente() %>" enctype="application/x-www-form-urlencoded" method="post">
                                        <div>
                                            <input class="btn_2" type="submit" value="<%=bundle.getString("visualizzaPosta_rispondi")%>"/>
                                        </div>
                                    </form>
                                </td>
                                <td>
                                    <%
                                        Utente des = null;
                                        if (messaggio != null) {
                                            try {
                                                des = cgp.ottieniUtente(messaggio.getMittente());
                                            } catch (DeserializzazioneException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    %>
                                    <form action="mailto:<%= des == null ? "" : des.getEmail() %>?subject=<%= messaggio == null ? "" : messaggio.getOggetto() %>" enctype="application/x-www-form-urlencoded" method="post">
                                        <div>
                                            <input style="width: 300px" class="btn_2" type="submit" value="<%=bundle.getString("visualizzaPosta_rispondiClientExt")%>" />
                                        </div>
                                    </form>
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
