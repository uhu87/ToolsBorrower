<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>TOOLS_BORROWER - REGISTRATION</title>
</head>
<body>

<h1>REGISTRATION</h1>
<strong><a href="/hello">STRONA GŁÓWNA</a></strong><br>
<br>
<form:form modelAttribute="user">

    <form:hidden path="id"/>
    Nazwa użytkownika: <form:input path="username"/>
    <form:errors path="username" /><br/>
    Imię:<form:input path="firstName"/>
    <form:errors path="firstName" /><br/>
    Nazwisko:<form:input path="lastName"/>
    <form:errors path="lastName" /><br/>
    Telefon:<form:input path="phone"/><br/>
    Email:<form:input path="email"/>
    <form:errors path="email" /><br/>
    hasło: <form:input path="password" type="password"/>
    <form:errors path="password" /><br/>

    <input type="submit" value="REGISTER"><br/>

</form:form>

</body>
</html>
