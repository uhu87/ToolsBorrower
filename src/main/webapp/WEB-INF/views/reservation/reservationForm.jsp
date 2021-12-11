<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html; charset=UTF-8" %>

<h2>SZCZEGÓŁY REZERWACJI</h2>
<strong><a href="/user/dashboard">MY ACCOUNT</a></strong> <br>
<form:form modelAttribute="userTool">

    Zarezerwuj:  ${userTool.tool} <br>

    Od: ${userTool.user} <br>

        Podaj date pozyczenia: <input type="date" path="start" value="start" name="start"> <br>
        Podaj date oddania: <input type="date" path="end" value="end" name="end">
<br>
    <input type="submit" value="BOOK"><br/>

</form:form>