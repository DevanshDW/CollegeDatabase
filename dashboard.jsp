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


<%

Student xSt = (Student)session.getAttribute("student");

if( xSt != null)
{
	session.setAttribute("a", "");
	

}

else {
	session.setAttribute("a", "Here you go Enter details to get dashboard.");
		response.sendRedirect("./login.jsp");
		return;

}

%>


<html>
<head>
	<title>Welcome to DC Mark tracker</title>
		<%@ page import="webd4201.pateldev.*" %>
		<%@ page import="java.util.*" %>
</head>
<body>
	<center>
	<h1><em><font color="red">Welcome to DC Mark tracker</font></em></h1>
	<hr>
	<img src = "images/smallimage10.gif">
	<hr></center>
	<p> Hello <%= xSt.getFirstName() %> <%= xSt.getLastName() %>! , welcome to dashboard. This contains all the information about your educational performance.</p>



<%@ include file="./footer.jsp" %>