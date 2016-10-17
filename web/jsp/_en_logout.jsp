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
<jsp:useBean id="c" scope="session" class="control.ControlloreLogin"/>
<%
    c.logout();
%>

<div id="wrapper">
    <div id="header-wrapper">
        <div id="header">
            <div id="logo">

            </div>
        </div>
    </div>

    <div class="language">
        <table>
            <tr>
                <td>
                    <label for="select"> Language: </label>
                </td>
                <td>
                    <select id="select" class="btn" onchange="changeLang(this)">
                        <option id="en" value="english">English</option>
                        <option id="it" value="italian">Italian</option>
                    </select>
                </td>
            </tr>
        </table>
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

                    <h2><a href="../html/_en_index.html">BACK TO HOME PAGE</a></h2>

                </div>
                <!-- end #content -->

                <div id="sidebar">
                    <!-- end #sidebar -->
                    <div style="clear: both;">&nbsp;</div>
                </div>
            </div>
        </div>
        <!-- end #page -->
    </div>
    <div id="footer">
    </div>

</div>
<!-- end #footer -->
</body>
</html>