<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
	<h2>Login Kids On Track Management System</h2>

		<form action="login" method="post">
			<input type="hidden" name="id">
			<label for="email">User Email</label>
			<input type="text" id="email" name="email"/>
			<label for="password">Password</label>
			<input type="text" id="password" name="password"/>
			<input type="submit" value="Submit"/>
		</form>

</body>
</html>