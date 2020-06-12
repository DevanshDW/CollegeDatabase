<!--
		Name      `: Devansh Patel
		File       : Deliverable3.jsp 
		Date       :11 March 2020
		Description:This is the jsp for the web Development  (WEBD4201)
-->


<% String title = "WEBD4201"; %>
<%@ include file="./header.jsp" %>


<%

Student xSt = (Student)session.getAttribute("student");

if( xSt != null)
{
	session.setAttribute("a", "");
	out.println("Student not null" + xSt.getFirstName());
}

else {
	session.setAttribute("a", "Here you go Enter details to get dashboard.");
		response.sendRedirect("./login.jsp");
}

%>

<div>
<p>
<%= a %>
</p>
</div>
<%@ include file="./footer.jsp" %>