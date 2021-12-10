<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>EDIT</title>
</head>
<body>

<h1>EDYCJA DANYCH</h1>

<form:form modelAttribute="user">

    <form:hidden path="id"/>
    username: <form:input path="username"/>
    <form:errors path="username" /><br/>
    firstname:<form:input path="firstName"/>
    <form:errors path="firstName" /><br/>
    lastname:<form:input path="lastName"/>
    <form:errors path="lastName" /><br/>
    phone:<form:input path="phone"/><br/>
    email:<form:input path="email"/>
    <form:errors path="email" /><br/>
    password: <form:input path="password" type="password"/>
    <form:errors path="password" /><br/>

    <input type="submit" value="SAVE"><br/>

</form:form>

</body>
</html>
