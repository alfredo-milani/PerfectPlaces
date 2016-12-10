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
<%@page import="entity.*"%>
<%@ page import="exception.DeserializzazioneException" %>
<%@ page import="exception.SerializzazioneException" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page import="control.ControlloreLingua" %>
<%@ page import="java.util.Locale" %>

<%
    Locale locale;
    try {
        locale = ControlloreLingua.getLang(c.getUser());
    } catch (DeserializzazioneException e) {
        locale = ControlloreLingua.getLang();
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
				<h1><%=bundle.getString("modificaProfiloUtente_modificaProfilo")%></h1>
				<h2> <%=bundle.getString("profiloUtente_registratoCome")%> <%=c.getUser()%> </h2>
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
						
						<h2 class="title"> <%=bundle.getString("modificaProfiloUtente_modificaTuoProfilo")%>

                            <%
                                String username = c.getUser();
                                Utente u = null;

                                String param = request.getParameter("Mpu");
                                if (!c.getLogged()) {
                            %>

                                    <font color="red"> <%=bundle.getString("modificaProfiloUtente_sessioneScaduta")%> </font>

                            <%
                                } else {
                                    try {
                                        u = cgp.ottieniUtente(username);
                                    } catch (DeserializzazioneException e) {
                                        e.printStackTrace();
                                    }
                                    if (param != null) {
                                        String nome = request.getParameter("nome");
                                        String cognome = request.getParameter("cognome");
                                        String email = request.getParameter("email");
                                        String sesso = request.getParameter("sesso");
                                        String nascita = request.getParameter("nascita");
                                        String vecchiaPassword = request.getParameter("vecchiaPassword");
                                        String nuovaPassword = request.getParameter("nuovaPassword");
                                        String confermaNuovaPassword = request.getParameter("confermaNuovaPassword");

                                        int controllo = 0;

                                        try {
                                            controllo = cgp.modificaProfilo(username, nome, cognome, email, sesso, nascita, vecchiaPassword, nuovaPassword, confermaNuovaPassword);
                                        } catch (DeserializzazioneException | SerializzazioneException e) {
                                            e.printStackTrace();
                                        }
                                        try {
                                            u = cgp.ottieniUtente(username);
                                        } catch (DeserializzazioneException e) {
                                            e.printStackTrace();
                                        }

                                        switch (controllo) {
                                            case 0:
                            %>

                                                <font color="green"> <%=bundle.getString("modificaProfiloUtente_modificaCorretta")%> </font>

                            <%
                                                break;

                                            case 1:
                            %>

                                                <font color="red"> <%=bundle.getString("modificaProfiloUtente_vecchiaPswErrore")%> </font>

                            <%
                                                break;

                                            case 2:
                            %>

                                                <font color="red"> <%=bundle.getString("modificaProfiloUtente_nuovaPswConferma")%> </font>

                            <%
                                                break;

                                            case 3:
                            %>

                                                <font color="red"> <%=bundle.getString("modificaProfiloUtente_nuovaPswMancante")%> </font>

                            <%
                                                break;

                                            case 4:
                            %>

                                                <font color="red"> <%=bundle.getString("modificaProfiloUtente_sessioneScaduta")%> </font>

                            <%
                                                break;

                                            case 5:
                            %>

                                                <font color="red"> <%=bundle.getString("modificaProfiloUtente_sessoErrore")%> </font>

                            <%
                                                break;

                                            case 6:
                            %>

                                                <font color="red"> <%=bundle.getString("modificaProfiloUtente_nascitaErrore")%> </font>

                            <%
                                                break;
                                        }
                                    }
                                }
                            %>

                        </h2>
                        <form action="modificaProfiloUtente.jsp?Mpu=1" enctype="application/x-www-form-urlencoded" method="post">
                            <table>
                                <tr>
                                    <td>
                                        <label for="nome"><%=bundle.getString("index_nome")%></label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input class="btn" id="nome" type="text" name="nome" size="30" value="<%=u == null ? "" : u.getNome()%>"/>
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                        <label for="cognome"><%=bundle.getString("index_cognome")%></label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input class="btn" id="cognome" type="text" name="cognome" size="30" value="<%=u == null ? "" : u.getCognome()%>"/>
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                        <label for="email"><%=bundle.getString("index_email")%></label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input class="btn" id="email" type="text" name="email" size="30" value="<%=u == null ? "" : u.getEmail()%>"/>
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                        <label for="sesso"><%=bundle.getString("profiloUtente_sesso")%></label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input class="btn" id="sesso" type="text" name="sesso" size="30" value="<%=u == null ? "" : (u.getSesso() == null ? "" : u.getSesso())%>"/>
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                        <label for="nascita"><%=bundle.getString("profiloUtente_nascita")%></label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input class="btn" id="nascita" type="text" name="nascita" size="30" value="<%=u == null ? "" : (u.getNascita() == null ? "" : u.getNascita())%>"/>
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                        <label for="vecchia_psw"><%=bundle.getString("modificaProfiloUtente_vecchiaPsw")%></label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input class="btn" id="vecchia_psw" type="password" name="vecchiaPassword" size="30" value=""/>
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                        <label for="nuova_psw"><%=bundle.getString("modificaProfiloUtente_nuovaPsw")%></label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input class="btn" id="nuova_psw" type="password" name="nuovaPassword" size="30" value=""/>
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                        <label for="conferma_new_psw"><%=bundle.getString("modificaProfiloUtente_confermaNuovaPsw")%></label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input class="btn" id="conferma_new_psw" type="password" name="confermaNuovaPassword" size="30" value=""/>
                                    </td>
                                </tr>

                                <tr class="break"><td colspan="2"></td></tr>

                                <tr>
                                    <td>
                                        <label><%=bundle.getString("modificaProfiloUtente_linguaCorrente")%> <%=bundle.getString("language")%> </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <h4>
                                            <%=bundle.getString("modificaProfiloUtente_nota")%><u><a href="utente.jsp"> <%=bundle.getString("utente_home")%> </a></u>
                                        </h4>
                                    </td>
                                </tr>

                            </table>
                            <center>
                                <br/><br/><br/>
                                <input class="btn_2" type="submit" value="<%=bundle.getString("modificaProfiloUtente_salva")%>"/>
                            </center>
                        </form>
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