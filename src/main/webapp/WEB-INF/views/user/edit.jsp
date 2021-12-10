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
    phone:<form:input path="phone"/><br/>
    email:<form:input path="email"/><br/>
    password: <input type="password" name="password"/><br/>


    <input type="submit" value="SAVE"><br/>

</form:form>

</body>
</html>
