<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>


<strong><a href="/tool/all">ALL TOOLS</a></strong> |
<strong><a href="/user/all">ALL USERS</a></strong> |
<strong><a href="/user/dashboard">MY ACCOUNT</a></strong> |
<strong><a href="/logout1">LOG OUT</a></strong> <br>

<c:forEach items="${users}" var="u">


    <a href="userTools/${u.id}">${u.username}</a><br>


</c:forEach>

<hr>
