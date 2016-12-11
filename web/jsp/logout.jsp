<%@ page import="java.util.Locale" %>
<%@ page import="control.ControlloreLingua" %>
<%@ page import="exception.DeserializzazioneException" %>
<%@ page import="java.util.ResourceBundle" %><%--
  Created by IntelliJ IDEA.
  User: alfredo
  Date: 20/10/16
  Time: 15.41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="c" scope="session" class="control.ControlloreLogin"/>

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
    ControlloreLingua controlloreLingua = new ControlloreLingua();
	String username = c.getUser();
	Locale locale;
    try {
        locale = controlloreLingua.getLang(username);
    } catch (DeserializzazioneException e) {
        locale = controlloreLingua.getLang();
        e.printStackTrace();
    }
    ResourceBundle bundle = ControlloreLingua
            .getBundle(locale);

    c.logout();
%>

<div id="wrapper">
	<div id="header-wrapper">
		<div id="header">
			<div id="logo"></div>
		</div>
	</div>
</div>
	<!-- end #header -->
	<div id="page">
		<div id="page-bgtop">
			<div id="page-bgbtm">
				<div id="content">

					<div class="post">
						<h2 class="title"><%=bundle.getString("logout_effettuato")%></h2>
						<br /><br /><br />
						<h2 class="title">
                            <a href="index.jsp">
                                <font color="#4b7091"><%=bundle.getString("logout_home")%></font>
                            </a>
                        </h2>
				    </div>
				    <!-- end #content -->
					<br/>

		        </div>
	        </div>

	        <!-- end #page -->
        </div>

        <div id="footer"></div>
        <!-- end #footer -->
	
    </div>
<!-- end #footer -->
</body>
</html>