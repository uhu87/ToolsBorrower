<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<strong><a href="/tool/all">ALL TOOLS</a></strong><br>
<strong><a href="/user/allButLogged">ALL USERS</a></strong><br>
<strong><a href="/user/dashboard">MY ACCOUNT</a></strong><br>


<%@ page contentType="text/html; charset=UTF-8" %>

<h2>Narzedzie, ktore probujesz pozyczyc musi byc zwrocone przed ${returnDate} gdyz inny uzytkownik ma rezerwacje</h2>
<br>

<a href="/borrowing/create?toolId=${userTool.id}">POPRAW DATE</a>
