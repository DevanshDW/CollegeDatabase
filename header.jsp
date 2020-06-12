<!--
		Name      `: Devansh Patel
		File       : Deliverable3.jsp 
		Date       :11 March 2020
		Description:This is the jsp for the web Development  (WEBD4201)
-->


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
   	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en"> 

<%

	String h1 = "login.jsp";
	String h2 = "register.jsp";
	String l1 = "Login";
	String l2 = "Register";
	if ((Student) session.getAttribute("aStudent") != null){ 
	h1 = "./logout.jsp";
	h2 = "./update.jsp";
	l1 = "Logout";
	l2 = "Update";
		
	}


%>
<head>	

	<link href="./css/myCss.css" rel="stylesheet" type="text/css" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title><%= title %></title>

	<%@ page import="webd4201.pateldev.*" %>
	<%@ page import="java.util.*" %>

	<% String a = (String)session.getAttribute("a");
		if (a != null)
		{
			session.removeAttribute("a");

		}
		else 
		{
			a="";
		}
			//  
	%>
	
</head>
<body>

<div style="background-color:lightblue">

<a href="<%= h1 %>" ><%= l1 %><a/>
<a href="<%= h2 %>" ><%= l2 %><a/>

</div>

<!-- end of header.jsp --> 
