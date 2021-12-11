<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>TOOLS_BORROWER - LOGIN</title>
</head>
<body>
<h1>LOGIN</h1>

<form method="post">
    <div><label> User Name : <input type="text" name="username"/> </label></div>
    <div><label> Password: <input type="password" name="password"/> </label></div>
    <div><input type="submit" value="Sign In"/></div>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

</body>
</html>






