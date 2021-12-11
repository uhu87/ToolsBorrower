<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<strong><a href="/tool/all">ALL TOOLS</a></strong><br>
<strong><a href="/user/allButLogged">ALL USERS</a></strong><br>
<strong><a href="/user/dashboard">MY ACCOUNT</a></strong><br>
<strong><a href="/logout1">LOG OUT</a></strong><br>

<%@ page contentType="text/html; charset=UTF-8" %>

<h3>
<c:forEach items="${borrowing}" var="b">
    Aktualnie pożyczona do ${b.end}
</c:forEach>
</h3>

<c:if test="${reservations.size()==0}">
    BRAK REZERWACJI
</c:if>
<c:if test="${reservations.size()!=0}">

    <h3>Reservation list of ${userTool}</h3>
    <c:forEach items="${reservations}" var="r">
        <a href="/user/userTools/${r.user.id}">${r.user}</a> | ${r.start} | ${r.end} | <a href="/notification/ownerInfo?reservationId=${r.id}">SEND INFO</a> <br>
        <br>
    </c:forEach>


</c:if>

