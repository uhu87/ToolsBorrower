<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html; charset=UTF-8" %>

<h2>POŻYCZENIE</h2>
<form:form modelAttribute="reservation">

    Pożyczasz:  ${reservation.userTool} <br>

    Właściciel: ${reservation.userTool.user} <br>

    Data rezerwacji: od: ${reservation.start} do: ${reservation.end}

    <input type="submit" value="BORROW"><br/>

</form:form>