<!--
		Name      `: Devansh Patel
		File       : Deliverable3.jsp 
		Date       :11 March 2020
		Description:This is the jsp for the web Development  (WEBD4201)
-->



<% String title = "WEBD4201"; %>
<%@ include file="./header.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
   	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
	<title>Welcome to DC Mark tracker</title>
	<meta name="description" content="website description" />
  <meta name="keywords" content="website keywords, website keywords" />
  <meta http-equiv="content-type" content="text/html; charset=windows-1252" />
	<%@ page import="webd4201.pateldev.*" %>
	<%@ page import="java.util.*" %>
</head>
<body>
	 <div id="main">
    <div id="header">
      <div id="logo">
    
	<center>
	<h1><em><font color="red">Welcome to DC Mark tracker</font></em></h1>
	<hr>
	<img src = "images/dc.jpg" height="60" width="90">
	<hr></center>
	</div>
    
	<p> Mark tracker is the website for the student of the durham college. This site is keeping track of marks, enrolled courses and GPA for all the studen who are registered in college. </p>
	<strong> DC mark tracker provides:</strong><br>

	 </div>
    </div>

	<table align="center" cellspacing="5" cellpadding="3">
		   <div id="menubar">
	<tr id="menu">
		<td><li class="selected">
			Login
		</li></td>
		<td><li>
			Registration
		</li></td>
		<td><li>
			Update
		</li></td>
		
	</tr>
	</div>
	</table>
	<center><br>If you are a student at Durham College, please log in.
	<table align="center" bgcolor="lightgoldenrodyellow">
	<tr>
		<td width="100" align="Center">
			<a href="login.jsp">
			<strong><font size="+1">Log In</font></strong></a>
		</td>
	</tr>
	</table>


<%@ include file="./footer.jsp" %>