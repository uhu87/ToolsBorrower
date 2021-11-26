<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<strong><a href="/tool/all">ALL TOOLS</a></strong><br>
<strong><a href="/user/all">ALL USERS</a></strong><br>

<h2>${user.firstName}'s TOOLS</h2>

<hr>
ALL TOOLS: <br>
<c:forEach items="${userTools}" var="uT">

    ${uT.tool} <br>

</c:forEach>

<hr>
AVAILABLE: <br>
<c:forEach items="${userToolsAvailable}" var="uTA">

    ${uTA.tool}   <a href="/borrowing/create?toolId=${uTA.id}"?>BORROW THIS TOOL</a>  <br>

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
    --->  from: ${b.userTool.user} <a href="/borrowing/return?toReturnId=${b.id}">RETURN</a> <br>

</c:forEach>

<hr>