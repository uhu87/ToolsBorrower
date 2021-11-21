<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<strong><a href="/tool/all">ALL TOOLS</a></strong><br>
<strong><a href="/user/all">ALL USERS</a></strong><br>

<h2>USER'S VIEW</h2>

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
<c:forEach items="${userToolsLent}" var="uTL">

    ${uTL.tool} <br>

</c:forEach>

<hr>
BORROWED FROM OTHERS: <br>
<c:forEach items="${borrowings}" var="b">

   ${b.userTool}   <br>
    --->  from: ${b.userTool.user} <br>

</c:forEach>

<hr>