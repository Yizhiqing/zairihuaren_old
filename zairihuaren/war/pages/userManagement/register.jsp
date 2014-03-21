<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<body>
	<h1>用户注册</h1>
	<form:form method="post" modelAttribute="user" >
		<table>
			<tr>
				<td>用户ID :</td>
				<td><form:input type="text" style="width: 185px;" maxlength="30"
					path="name" /></span></td>
			</tr>
		    <tr>
				<td>密码 :</td>
				<td><form:input type="password" style="width: 185px;" maxlength="30"
					path="password" /></span></td>
			</tr>
			<tr>
				<td>Email :</td>
				<td><form:input type="text" style="width: 185px;" maxlength="30"
					path="email" /></span></td>
			</tr>
		</table>
		<input type="submit" class="save" title="注册" value="注册" />
	</form:form>
</body>
</html>