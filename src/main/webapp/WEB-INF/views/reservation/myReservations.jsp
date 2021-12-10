<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<strong><a href="/tool/all">ALL TOOLS</a></strong><br>
<strong><a href="/user/allButLogged">ALL USERS</a></strong><br>
<strong><a href="/user/dashboard">MY ACCOUNT</a></strong><br>


<%@ page contentType="text/html; charset=UTF-8" %>

<h2>My reservations </h2>

<c:forEach items="${reservations}" var="r">

    ${r.userTool} | ${r.start} | ${r.end}  | od: <a href="/user/userTools/${r.userTool.user.id}">${r.userTool.user}</a>  |
    <a href="/reservation/cancel?reservationId=${r.id}">ANULUJ</a>
    <c:if test="${r.notification != null}">
        |  ${r.notification}
    </c:if>

    <br>


</c:forEach>