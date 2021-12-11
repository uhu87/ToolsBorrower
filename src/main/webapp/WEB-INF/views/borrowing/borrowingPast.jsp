<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<strong><a href="/tool/all">ALL TOOLS</a></strong> |
<strong><a href="/user/allButLogged">ALL USERS</a></strong> |
<strong><a href="/user/dashboard">MY ACCOUNT</a></strong><br>


<%@ page contentType="text/html; charset=UTF-8" %>

Data oddania nie może byc w przeszłości
<br>

<a href="/borrowing/create?toolId=${userTool.id}">POPRAW DATE</a>

