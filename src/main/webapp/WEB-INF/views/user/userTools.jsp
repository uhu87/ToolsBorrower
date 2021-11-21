<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<strong><a href="/tool/all">ALL TOOLS</a></strong><br>
<strong><a href="/user/all">ALL USERS</a></strong><br>

<hr>
ALL TOOLS: <br>
<c:forEach items="${userTools}" var="uT">

    ${uT.tool} <br>

</c:forEach>

<hr>
AVAILABLE: <br>
<c:forEach items="${userToolsAvailable}" var="uTA">

    ${uTA.tool} <br>

</c:forEach>

<hr>
LENT TO OTHERS: <br>                                    <%--// mozna potem wykorzystywac na bazie borrowingsow//--%>
<c:forEach items="${userToolsBorrowed}" var="uTB">

    ${uTB.tool} <br>

</c:forEach>

<hr>
