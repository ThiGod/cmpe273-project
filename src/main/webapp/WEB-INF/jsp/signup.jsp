<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
	<h2>Sign-up Kids On Track Management System</h2>

		<form action="signup" method="post">
			<input type="hidden" name="id">
			<label for="name">User Name</label>
			<input type="text" id="name" name="name"/>
			<label for="email">User Email</label>
			<input type="text" id="email" name="email"/>
			<label for="password">Password</label>
			<input type="text" id="password" name="password"/>
			<input type="submit" value="Submit"/>
		</form>

</body>
</html>