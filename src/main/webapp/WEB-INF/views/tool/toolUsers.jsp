<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<strong><a href="/tool/all">ALL TOOLS</a></strong><br>
<strong><a href="/user/all">ALL USERS</a></strong><br>

<c:forEach items="${userTools}" var="uT">

    ${uT.user} <br>

</c:forEach>

<hr>
