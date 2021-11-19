<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>



<c:forEach items="${tools}" var="t">

    ${t.name} <br>
    ${t.users} <br>

</c:forEach>

<hr>
