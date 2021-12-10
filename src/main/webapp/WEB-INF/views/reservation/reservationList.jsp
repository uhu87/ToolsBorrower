<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<strong><a href="/tool/all">ALL TOOLS</a></strong><br>
<strong><a href="/user/allButLogged">ALL USERS</a></strong><br>
<strong><a href="/user/dashboard">MY ACCOUNT</a></strong><br>


<%@ page contentType="text/html; charset=UTF-8" %>

<h2> Aktualnie pożyczona do: </h2>
<c:forEach items="${borrowing}" var="b">
    ${b.end}
</c:forEach>


<h2>Reservation list of ${userTool}</h2>
<c:forEach items="${reservations}" var="r">

    <a href="/user/userTools/${r.user.id}">${r.user}</a> | ${r.start} | ${r.end} | <a href="/notification/information?reservationId=${r.id}">SEND INFO</a> <br>
    <br>


</c:forEach>