<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Update</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/edit" method="post">
    ID : <input type="number" name="id">
    Name : <input type="text" name="name"/>
    Login : <input type="text" name="login"/>
    Email : <input type="text" name="email">
    <input type="submit">
</form>
</body>
</html>
