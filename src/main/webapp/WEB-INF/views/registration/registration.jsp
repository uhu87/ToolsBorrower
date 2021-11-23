<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>REGISTRATION</title>
</head>
<body>

<form:form modelAttribute="user">

    <form:hidden path="id"/><br/>
    username: <form:input path="username"/><br/>
    firstname:<form:input path="firstName"/><br/>
    lastname:<form:input path="lastName"/><br/>
    password: <form:input path="password"/><br/>

    <input type="submit" value="REGISTER"><br/>

</form:form>

</body>
</html>
