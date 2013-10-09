<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
	<h2>Sign-up Kids On Track Management System</h2>

		<form action="signup" method="post">
			<input type="hidden" name="id">
			<div>
				<label for="name">User Name</label>
				<input type="text" id="name" name="name"/>
			</div>
				<label for="email">User Email</label>
				<input type="text" id="email" name="email"/>
			<div>
			</div>
			<div>
				<label for="password">Password</label>
			<input type="text" id="password" name="password"/>
			</div>
			<div>
				<input type="submit" value="Submit"/>
			</div>			
		</form>

</body>
</html>