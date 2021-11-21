<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>


<strong><a href="/tool/all">ALL TOOLS</a></strong><br>
<strong><a href="/user/all">ALL USERS</a></strong><br>


<c:forEach items="${tools}" var="t">


    <a href="/tool/toolUsers/${t.id}">${t.name}</a><br>


</c:forEach>

<hr>
