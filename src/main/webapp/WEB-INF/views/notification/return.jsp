<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html; charset=UTF-8" %>

<h2>Wiadomość do pożyczającego</h2>
<form:form  modelAttribute="toReturnId">


    Wyślij wiadomość do użytkownika <br/>
    <br>

    Wiadomość:<input type="text" value="" name="notification"/><br/>
    <button> <input type="submit" value="SEND"></button><br/>

</form:form>