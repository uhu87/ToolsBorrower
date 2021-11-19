<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>



<c:forEach items="${users}" var="u">

    ${u.firstName} <br>
    ${u.tools} <br>

</c:forEach>

<hr>
