<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html; charset=UTF-8" %>

<h2>Borrowing details</h2>
<form:form modelAttribute="userTool">

    Czy chcesz pozyczyc:  ${userTool.tool} <br>

    Od: ${userTool.user}? <br>

    <input type="submit" value="BORROW"><br/>

</form:form>