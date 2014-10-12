<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>
<P>Input Username: ${iUsername}</P>
<P>Input Password: ${iPassword}</P>

<P>Username: ${vUsername}</P>
<P>Password: ${vPassword}</P>
<P>Email: ${vEmail}</P>
<P>Phone: ${vPhone}</P>
<P>Address: ${vAddress}</P>
<P>First Name: ${vFirstName}</P>
<P>Last Name: ${vLastName}</P>
</body>
</html>