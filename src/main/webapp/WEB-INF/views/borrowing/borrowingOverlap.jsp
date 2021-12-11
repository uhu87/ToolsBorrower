<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<strong><a href="/tool/all">ALL TOOLS</a></strong> |
<strong><a href="/user/allButLogged">ALL USERS</a></strong> |
<strong><a href="/user/dashboard">MY ACCOUNT</a></strong> |
<strong><a href="/logout1">LOG OUT</a></strong><br>

<%@ page contentType="text/html; charset=UTF-8" %>

Narzędzie, które chcesz pożyczyć musi byc zwrócone przed ${returnDate} ponieważ inny użytkownik ma rezerwację;
<br>

<br>
<a href="/borrowing/create?toolId=${userTool.id}">POPRAW DATĘ</a>

