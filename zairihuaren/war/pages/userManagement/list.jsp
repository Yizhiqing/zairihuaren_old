<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.zairihuaren.mvc.model.User" %>
<%@ page import="com.google.appengine.api.datastore.KeyFactory" %>
<html>
<body>
	<h1>用户一览</h1>
	<table border="1">
		<thead>
			<tr>
				<td>Name</td>
				<td>Email</td>
				<td>Created Date</td>
				<td>Action</td>
			</tr>
		</thead>
		
		<%
			
			if(request.getAttribute("userList")!=null){
				
				List<User> users = (List<User>)request.getAttribute("userList");
				
				if(!users.isEmpty()){
					 for(User user : users){
						 
		%>
					<tr>
					  <td><%=user.getName() %></td>
					  <td><%=user.getEmail() %></td>
					  <td><%=user.getDate() %></td>
					  <td><a href="update/<%=user.getName()%>">Update</a> 
		                   | <a href="delete/<%=KeyFactory.keyToString(user.getKey()) %>">Delete</a></td>
					</tr>
		<%	
			
					}
		    
				}
			
		   	}
		%>
         
        </tr>
     
	</table>

</body>
</html>