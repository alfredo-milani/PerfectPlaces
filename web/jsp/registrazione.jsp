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
<jsp:useBean id="b" class="control.ControlloreRegistrazione" scope="session"/>

<%
    String un = request.getParameter("username_2");
    String pw = request.getParameter("password");
    String pw2 = request.getParameter("password2");
    String nome = request.getParameter("nome");
    String cognome = request.getParameter("cognome");
    String email = request.getParameter("email");
    int reg_control = b.registrazione(un,pw,pw2,nome,cognome,email);
%>

<div id="wrapper">
	<div id="header-wrapper">
		<div id="header">
			<div id="logo">
				<h1><a href="#">REGISTRAZIONE</a></h1>
			</div>
		</div>
	</div>
	<!-- end #header -->
	<div id="page">
		<div id="page-bgtop">
			<div id="page-bgbtm">
				<div id="content">
				
				<%if(reg_control==1){ %>
				
				<div class="post">
						<h2 class="title">ERRORE</h2>
						<table>
                            <tr>
						        <td>
						            <br /><br /><br /><br />
						            Non puoi lasciare campi del form vuoti! <br/>
						            <a href="../html/_0_index.html"> TORNA ALLA HOME PAGE E RIPROVA</a> <br/>
						        </td>
						    </tr>
						</table>
                </div>

                    <% } else if(reg_control==2){ %>

				<div class="post">
						<h2 class="title">ERRORE</h2>
						<table>
						<tr>
						<td>
						<br /><br /><br /><br />
						Le password di conferma deve essere uguale a quella inserita!<br />
						<a href="../html/_0_index.html"> TORNA ALLA HOME PAGE E RIPROVA</a><br />
						</td>
						</tr>
						</table>
						
					</div>
				
				<% } else if(reg_control == 3){ %>
				 	
				<div class="post">
						<h2 class="title">BENVENUTO!</h2>
						<table>
						<tr>
						<td>
						<br /><br /><br /><br />
						Lo Username inserito e' gia' stato utilizzato!<br />
						<a href="../html/_0_index.html"> REGISTRATI CON UN ALTRO USERNAME! </a><br />
						</td>
						</tr>
						</table>
						
					</div>

                    <% } else if(reg_control == 0){ %>
                    <div class="post">
                        <h2 class="title">BENVENUTO!</h2>
                        <table>
                            <tr>
                                <td>
                                    <br /><br /><br /><br />
                                    Registrazione effettuata con successo<br />
                                    <a href="../html/_0_index.html"> TORNA ALLA HOME PAGE ED EFFETTUA IL LOGIN!</a><br />
                                </td>
                            </tr>
                        </table>

                    </div>
                    <% } %>

                    <div style="clear: both;">&nbsp;</div>

                    <!-- end #content -->
                </div>

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