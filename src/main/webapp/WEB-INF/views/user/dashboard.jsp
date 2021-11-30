<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<strong><a href="/tool/all">ALL TOOLS</a></strong><br>
<strong><a href="/user/allButLogged">ALL USERS</a></strong><br>
<strong><a href="/logout1">LOG OUT</a></strong><br>
<strong><a href="/tool/addUserTool">ADD TOOL</a></strong><br>

<h2>WITAJ <sec:authentication property="principal.username"/></h2>

<hr>
ALL TOOLS: <br>
<c:forEach items="${userTools}" var="uT">

    ${uT.tool} | ${uT.description} | <a href="/tool/editUserTool?idToEdit=${uT.id}">EDIT</a>
    | <a href="/tool/delete?idToDelete=${uT.id}">DELETE</a><br>

</c:forEach>

<hr>
AVAILABLE: <br>
<c:forEach items="${userToolsAvailable}" var="uTA">

    ${uTA.tool} | ${uTA.description}  <%--<a href="/borrowing/create?toolId=${uTA.id}"?>BORROW THIS TOOL</a>--%>  <br>

</c:forEach>

<hr>
LENT TO OTHERS: <br>                                    <%--// mozna potem wykorzystywac na bazie borrowingsow//--%>
<c:forEach items="${lendings}" var="l">

    ${l.userTool} | ${l.userTool.description}    --->  to: ${l.user} <a href="/borrowing/return?toReturnId=${l.id}">SEND REQUEST TO RETURN</a> <br>

</c:forEach>

<hr>
BORROWED FROM OTHERS: <br>
<c:forEach items="${borrowings}" var="b">

    ${b.userTool} | ${b.userTool.description}      --->  from: ${b.userTool.user} <a href="/borrowing/return?toReturnId=${b.id}">RETURN</a> <br>

</c:forEach>

<hr>