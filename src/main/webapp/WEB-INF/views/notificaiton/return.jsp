<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html; charset=UTF-8" %>

<h2>Wiadomość do pożyczającego</h2>
<form:form  method="post">

    Wyślij wiadomość do użytkownika <br/>
    <br>
<%--   <input type="hidden" path="toReturnId" value="${borrowing.id}"> <br/>--%>
    <input type="text" >
    <button> <input type="submit" value="RETURN">WYŚLIJ</button><br/>

</form:form>