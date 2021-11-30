<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html; charset=UTF-8" %>

<h2>ADD TOOL</h2>
<form:form modelAttribute="userTool">

    <form:hidden path="id"/><br/>

    Czy chcesz usunac ${userTool} | ${userTool.description}
    <button type="submit" value="delete" name="confirmed">delete</button>
    <button type="submit" value="cancel" name="confirmed">cancel</button>

</form:form>
