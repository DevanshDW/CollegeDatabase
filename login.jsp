<!--
		Name      `: Devansh Patel
		File       : Deliverable3.jsp 
		Date       :11 March 2020
		Description:This is the jsp for the web Development  (WEBD4201)
-->

<% String title = "WEBD4201"; %>
<%@ include file="./header.jsp" %>

<html>
<head>
	<title>Log In to DC Mark tracker</title>
	  <meta name="description" content="website description" />
  <meta name="keywords" content="website keywords, website keywords" />
  <meta http-equiv="content-type" content="text/html; charset=windows-1252" />
	<%@ page import="webd4201.pateldev.*" %>
	<%@ page import="java.util.*" %>

</head>
</head>

<%   
	String pswrd = (String)session.getAttribute("pswrd"); 
	 String login = (String)session.getAttribute("login");

	
	if(login == null)
		{login = "";}
	if(pswrd == null)
		{pswrd = "";}

%>
<body>
	<center>
	<hr>
	<img src = "images/dc.jpg" height="60" width="90">
	<hr>
	<h2>Please log in</h2>
	<p>Enter your login information below.<br>
	   If you are not a student of DC, please return to the
	   <a href="index.jsp">Durham college</a> home page.</p>
	<p>
	   If you want to become a student on DC system, please <a href="register.jsp">register</a>.</p>
	<form name="Input" method="post" action="./Login" >
		<!-- action="http://localhost:81/Bradshaw/LoginServlet" > -->
		<table border=0 bgcolor="lightgoldenrodyellow" cellpadding=10 >
		<tr><td colspan="2" align="center"><%= a %></td></tr>
		<tr>
			<td><strong>Login Id</strong></td>
			<td><input class="search" type="text" name="Login" value="<%= login %>" size=20></td>
		</tr>
		<tr>
			<td><strong>Password</strong></td>
			<td><input type="password" class="search" name="Password" value="<%= pswrd %>" size=20></td>
		</tr>
		</table>
		<table border=0 cellspacing=15 >
		<tr>
			<td><input type="submit" value = "Log In"></td>
			<td><input type="reset" value = "Clear"></td>
		</tr>
		</table>
	</form>
	Please wait after pressing <strong>Log in</strong>
	while we retrieve your records from our database.<br>
	<em>(This may take a few moments)</em>
	</center>


<%@ include file="./footer.jsp" %>
