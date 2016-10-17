<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html lang="en" xml:lang="en" xmlns="http://www.w3.org/1999/xhtml">

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
				<h1><a href="#">SIGN UP</a></h1>
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
						<h2 class="title">ERROR</h2>
						<table>
                            <tr>
						        <td>
						            <br /><br /><br /><br />
									You can not leave empty form fields! <br/>
						            <a href="../html/_en_index.html"> BACK TO HOME PAGE AND TRY AGAIN</a> <br/>
						        </td>
						    </tr>
						</table>
                </div>

                    <% } else if(reg_control==2){ %>

				<div class="post">
						<h2 class="title">ERROR</h2>
						<table>
						<tr>
						<td>
						<br /><br /><br /><br />
							The confirmation password must be the same as inserted!<br />
						<a href="../html/_en_index.html"> BACK TO HOME PAGE AND TRY AGAIN</a><br />
						</td>
						</tr>
						</table>
						
					</div>
				
				<% } else if(reg_control == 3){ %>
				 	
				<div class="post">
						<h2 class="title">ATTENTION!</h2>
						<table>
						<tr>
						<td>
						<br /><br /><br /><br />
							The username entered is already used!<br />
						<a href="../html/_en_index.html"> YOU MUST REGISTER WITH ANOTHER USERNAME! </a><br />
						</td>
						</tr>
						</table>
						
					</div>

                    <% } else if(reg_control == 0){ %>
                    <div class="post">
                        <h2 class="title">WELCOME!</h2>
                        <table>
                            <tr>
                                <td>
                                    <br /><br /><br /><br />
									Registered successfully<br />
                                    <a href="../html/_en_index.html"> BACK TO HOME PAGE AND PLEASE LOGIN!</a><br />
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