<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<strong><a href="/tool/all">ALL TOOLS</a></strong><br>
<strong><a href="/user/allButLogged">ALL USERS</a></strong><br>
<strong><a href="/logout1">LOG OUT</a></strong><br>
<strong><a href="/tool/addUserTool">ADD TOOL</a></strong><br>
<strong><a href="/user/edit">EDYTUJ DANE</a></strong><br>

<h2>WITAJ <sec:authentication property="principal.username"/></h2>

<hr>
<h2>WSZYSTKIE NARZĘDZIA:</h2><br>
<c:forEach items="${userTools}" var="uT">

    ${uT.tool} | ${uT.description} | <a href="/tool/editUserTool?idToEdit=${uT.id}">EDIT</a>
    | <a href="/tool/delete?idToDelete=${uT.id}">DELETE</a>
    | <a href="/borrowing/history?toolId=${uT.id}">BORROWING HISTORY</a>
    | <a href="/reservation/reservationList?toolId=${uT.id}">CHECK RESERVATIONS</a>

    <br>

</c:forEach>

<hr>
<%--AVAILABLE: <br>
<c:forEach items="${userToolsAvailable}" var="uTA">

    ${uTA.tool} | ${uTA.description}  &lt;%&ndash;<a href="/borrowing/create?toolId=${uTA.id}"?>BORROW THIS TOOL</a>&ndash;%&gt;  <br>

</c:forEach>

<hr>--%>
POŻYCZONE INNYM: <br>                                    <%--// mozna potem wykorzystywac na bazie borrowingsow//--%>
<c:forEach items="${lendings}" var="l">

    ${l.userTool} | ${l.userTool.description}    ---> DLA: ${l.user} <a href="/notification/return?toReturnId=${l.id}">WYSLIJ INFO</a> <br>

</c:forEach>

<hr>
POŻYCZONE OD INNYCH: <br>
<c:forEach items="${borrowings}" var="b">

    ${b.userTool} | ${b.userTool.description}      ---> OD: ${b.userTool.user}
    | DO: ${b.end} <a href="/borrowing/return?toReturnId=${b.id}">ODDAJ</a>
    <c:if test="${b.notification != null}">
        |  ${b.notification}
    </c:if>

    <br>

</c:forEach>

<hr>

<h2>MOJE REZERWACJE</h2>

<c:forEach items="${reservations}" var="r">

    ${r.userTool} | ${r.start} | ${r.end}  | od: <a href="/user/userTools/${r.userTool.user.id}">${r.userTool.user}</a>  |
    <a href="/reservation/cancel?reservationId=${r.id}">ANULUJ</a>
    <c:if test="${r.notification != null}">
        |  ${r.notification}
    </c:if>

    <c:if test="${r.reservationDayOn == true}">
        |  <a href="/borrowing/createFromReservation/${r.id}">POŻYCZ</a>
    </c:if>
    <br>
    <c:if test="${r.ownerInfo != null}">
        |  ${r.ownerInfo}
    </c:if>
    <br>
</c:forEach>


<c:if test="${deletedToolsReservations.size()>0}">
    <h2>REZERWACJE DO ANULOWANIA</h2>

    <c:forEach items="${deletedToolsReservations}" var="r">

        ${r.userTool} | ${r.start} | ${r.end}  | od: <a href="/user/userTools/${r.userTool.user.id}">${r.userTool.user}</a>  |
        <a href="/reservation/cancel?reservationId=${r.id}">ANULUJ</a>
        <c:if test="${r.notification != null}">
            |  ${r.notification}
        </c:if>

        <br>
        <c:if test="${r.ownerInfo != null}">
            |  ${r.ownerInfo}
        </c:if>
        <br>
    </c:forEach>

</c:if>

