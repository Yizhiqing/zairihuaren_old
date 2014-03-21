<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<body>
	<h1>用户注册</h1>

	<form method="post" action="add">
		<table>
			<tr>
				<td>用户ID :</td>
				<td><input type="text" style="width: 185px;" maxlength="30"
					name="name" id="name" /></span></td>
			</tr>
		    <tr>
				<td>密码 :</td>
				<td><input type="password" style="width: 185px;" maxlength="30"
					name="password" id="password" /></span></td>
			</tr>
			<tr>
				<td>Email :</td>
				<td><input type="text" style="width: 185px;" maxlength="30"
					name="email" id="email" /></span></td>
			</tr>
		</table>
		<input type="submit" class="save" title="注册" value="注册" />
	</form>

</body>
</html>