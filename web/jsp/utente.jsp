    <%--
  Created by IntelliJ IDEA.
  User: alfredo
  Date: 20/10/16
  Time: 15.41
  To change this template use File | Settings | File Templates.
--%>
    <jsp:useBean id="blingua" scope="session" class="boundary.BoundaryLingua"/>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="constants.Constants" %>
<%@ page import="exception.DeserializzazioneException" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.ResourceBundle" %>
<jsp:useBean id="bl" scope="session" class="boundary.BoundaryLogin"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

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
    String lang = request.getParameter("lang");

    if (un != null && pw != null) {
        try {
            bl.login(un, pw);
        } catch (DeserializzazioneException e) {
            e.printStackTrace();
        }
    }

    Locale currentLocale;
    if (lang != null) {
        currentLocale = blingua.riceviLocaleDaString(lang);
    } else {
        lang = Constants.LANG_DEFAULT;
        currentLocale = blingua.riceviLinguaDefault();
    }

    if (bl.controlloAccesso()) {
        String pref = request.getParameter("pref");
        if (pref != null && Integer.parseInt(pref) == 1) {
            currentLocale = blingua
                    .aggiornaPreferenze(bl.ritornaUsername(), lang);
        } else {
            currentLocale = blingua
                    .riceviLingua(bl.ritornaUsername());
        }
    }

    ResourceBundle bundle = blingua
			.riceviBundle(currentLocale);
%>

<div id="wrapper">
	<div id="header-wrapper">
		<div id="header">
			<div id="logo">

                <%
                    if (bl.controlloAccesso()) {
                %>

				    <h1> <%=bundle.getString("utente_benvenuto")%> </h1>
                    <h2> <%=bundle.getString("utente_loggedAs")%> <%=bl.ritornaUsername()%> </h2>

                <%  } else {
                        String redirectURL = "http://" + Constants.HOST_PORT +
                                "jsp/index.jsp?errLog=1&lang=" + lang;
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
                <li><a href="utente.jsp"><%=bundle.getString("utente_home")%></a></li>
                <li><a href="areaViaggiatore.jsp"><%=bundle.getString("utente_areaViaggiatore")%></a></li>
                <li><a href="areaProprietario.jsp"><%=bundle.getString("utente_areaProprietario")%></a></li>
                <li><a href="profiloUtente.jsp"><%=bundle.getString("utente_profilo")%></a></li>
                <li><a href="posta.jsp"><%=bundle.getString("utente_posta")%></a></li>
                <li><a href="areaFaq.jsp"><%=bundle.getString("index_faq")%></a></li>
                <li><a href="logout.jsp"><%=bundle.getString("utente_esci")%></a></li>
            </ul>

			<div id="page-bgbtm">
				<div id="content">

				<!--  Parte getUtente loggato -->
					<div class="language">
						<table>
							<tr>
								<td>
									<label for="select"> <%=bundle.getString("index_lingua")%> </label>
								</td>
                                <td>

                                    <%
                                        if (currentLocale.getDisplayLanguage()
                                                .equals(Locale.ENGLISH.getDisplayLanguage())) {
                                    %>

                                            <select id="select" class="btn" onchange="changeLang(this, '<%= bl.ritornaUsername()%>', '<%= bl.ritornaPassword() %>', 1)">
                                                <option value="<%=Constants.EN%>"><%=bundle.getString("index_inglese")%></option>
                                                <option value="<%=Constants.IT%>"><%=bundle.getString("index_italiano")%></option>
                                            </select>

                                    <%
                                        } else if (currentLocale.getDisplayLanguage()
                                                .equals(Locale.ITALIAN.getDisplayLanguage())) {
                                    %>

                                            <select id="select" class="btn" onchange="changeLang(this, '<%= bl.ritornaUsername()%>', '<%= bl.ritornaPassword() %>', 1)">
                                                <option value="<%=Constants.IT%>"><%=bundle.getString("index_italiano")%></option>
                                                <option value="<%=Constants.EN%>"><%=bundle.getString("index_inglese")%></option>
                                            </select>

                                    <%
                                        } else {
                                    %>

                                            <select id="select" class="btn" onchange="changeLang(this, '<%= bl.ritornaUsername()%>', '<%= bl.ritornaPassword() %>', 1)">
                                                <option value="<%=Constants.LANG_DEFAULT%>"><%=bundle.getString("index_italiano")%></option>
                                                <option value="<%=Constants.EN%>"><%=bundle.getString("index_inglese")%></option>
                                            </select>

                                    <%
                                        }
                                    %>

                                </td>

							</tr>
						</table>
					</div>

					<div class="post">
						<h2 class="title"><%=bundle.getString("utente_cliccaImg")%></h2>
						<table>
						<tr>
						<td>
						<br /><br />
						<h1 class="title">
						<strong>
							<a href="areaViaggiatore.jsp" ><%=bundle.getString("utente_viaggiatore")%></a>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="areaProprietario.jsp"><%=bundle.getString("utente_proprietario")%></a>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="profiloUtente.jsp"><%=bundle.getString("utente_profilo")%> </a>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="posta.jsp"><%=bundle.getString("utente_posta")%></a>
						</strong>
						</h1>
						</td>
						</tr>
							<tr>
								<td>
									<br /><br /><br /><br />
									<br /><br />

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
