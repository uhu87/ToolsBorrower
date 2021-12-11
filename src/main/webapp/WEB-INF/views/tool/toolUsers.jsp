<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<strong><a href="/tool/all">ALL TOOLS</a></strong> |
<strong><a href="/user/allButLogged">ALL USERS</a></strong> |
<strong><a href="/logout1">LOG OUT</a></strong> |
<strong><a href="/user/dashboard">MY ACCOUNT</a></strong> |

<c:forEach items="${toolUsers}" var="tU">

    <a href="/user/userTools?username=${tU}">${tU}</a>
   <br>

</c:forEach>

<hr>
