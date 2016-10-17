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
<jsp:useBean id="c" scope="session" class="control.ControlloreLogin"/>
<%
    String un = request.getParameter("username");
    String pw = request.getParameter("password");
    c.login(un, pw);
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
                <%  } else { %>
                    <h1> Attenzione! </h1>
                <%  } %>
			</div>
		</div>
	</div>

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
	<!-- end #header -->

	<div id="page">
		<div id="page-bgtop">
			<div id="page-bgbtm">
				<div id="content">

				<!--  Parte utente loggato -->
					<% if(c.getLogged()) { %>
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
					<% } %>
					
					<!--  Parte utente non loggato -->
					<% if (!c.getLogged()) {  %>
					<div class="post">
						<h2 class="title">Errore!</h2>
						
						<h1>L'utente non &egrave; presente nel sistema o la password inserita &egrave; errata!</h1>
						
						<br />
						
						<a href="../html/_it_index.html">Torna alla Home Page</a>
					</div>
					<% } %>
					
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
						<h2><strong><a href="_it_logout.jsp">Esci</a></strong></h2>
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
