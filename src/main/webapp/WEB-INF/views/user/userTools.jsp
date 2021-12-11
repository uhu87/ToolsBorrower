<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<strong><a href="/tool/all">ALL TOOLS</a></strong> |
<strong><a href="/user/allButLogged">ALL USERS</a></strong> |
<strong><a href="/user/dashboard">MY ACCOUNT</a></strong> |
<strong><a href="/logout1">LOG OUT</a></strong> |

<h2>${user.firstName}'s TOOLS</h2>
<c:if test="${user.phone !=null}">
Telefon: ${user.phone}
</c:if> |
<c:if test="${user.email !=null}">
Email: ${user.email}
</c:if>

<hr>
WSZYSTKIE NARZĘDZIA: <br>
<c:forEach items="${userTools}" var="uT">

    ${uT.tool} | ${uT.description} | <a href="/reservation/make?toolId=${uT.id}">REZERWUJ</a>
    | <a href="/reservation/userReservationList?toolId=${uT.id}">SPRAWDŹ REZERWACJE</a>
    <br>

</c:forEach>

<hr>
DOSTĘPNE: <br>
<c:forEach items="${userToolsAvailable}" var="uTA">

    ${uTA.tool} | ${uTA.description}   <a href="/borrowing/create?toolId=${uTA.id}"?>POŻYCZ</a>  <br>

</c:forEach>


<hr>