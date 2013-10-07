<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
	<h2>Kids On Track User Management.</h2>

	<table border="1">
		<c:forEach var="user" items="${userList}">
			<tr>
				<td>${user.email}</td><td><input type="button" value="delete" onclick="window.location='usermgnt/delete?id=${user.id}'"/></td>
			</tr>
		</c:forEach>
	</table>	
</body>
</html>