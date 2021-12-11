<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html; charset=UTF-8" %>

<h2>ADD TOOL</h2>
<form:form modelAttribute="userTool">

    <form:hidden path="id"/><br/>

    Narzędzie niedostępne do
    <button type="submit" value="delete" name="confirmed">SCHOWAJ</button>

</form:form>
