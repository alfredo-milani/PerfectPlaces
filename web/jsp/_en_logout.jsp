<%--
  Created by IntelliJ IDEA.
  User: alfredo
  Date: 20/10/16
  Time: 15.41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="c" scope="session" class="control.ControlloreLogin"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html lang="en" xml:lang="en" xmlns="http://www.w3.org/1999/xhtml">

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
                    <h2 class="title">LOGOUT DONE WITH SUCCESS!</h2>
                    <br /><br /><br />
                    <h2 class="title">
                        <a href="../jsp/_en_index.jsp">
                            <font color="#4b7091">BACK TO HOME PAGE</font>
                        </a>
                    </h2>
                </div>
                <!-- end #content -->

                <div id="sidebar"></div>

            </div>
        </div>
        <!-- end #page -->
    </div>

    <div id="footer"></div>
    <!-- end #footer -->

</div>

</body>
</html>