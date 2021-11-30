<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>


<strong><a href="/tool/all">ALL TOOLS</a></strong><br>
<strong><a href="/user/allButLogged">ALL USERS</a></strong><br>
<strong><a href="/user/dashboard">MY ACCOUNT</a></strong><br>

<c:forEach items="${users}" var="u">


    <a href="userTools/${u.id}">${u.firstName} ${u.lastName}</a><br>


</c:forEach>

<hr>
