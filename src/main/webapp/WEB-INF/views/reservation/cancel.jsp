<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<strong><a href="/tool/all">ALL TOOLS</a></strong><br>
<strong><a href="/user/allButLogged">ALL USERS</a></strong><br>
<strong><a href="/user/dashboard">MY ACCOUNT</a></strong><br>
<strong><a href="/logout1">LOG OUT</a></strong><br>

<%@ page contentType="text/html; charset=UTF-8" %>

<h2>Czy chcesz zrezygnować z poniższej rezerwacji?</h2>
<form:form modelAttribute="reservation">
<%--<form:hidden path="id" value="${reservation.id}" /><br/>--%>

    ${reservation.userTool} | ${reservation.start} | ${reservation.end}  from ---> ${reservation.userTool.user} <br>
    <button type="submit" value="yes" name="confirmed">Tak, rezygnuję z rezerwacji</button>
    <button type="submit" value="no" name="confirmed">Nie</button>

</form:form>