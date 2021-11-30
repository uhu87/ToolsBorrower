<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html; charset=UTF-8" %>

<h2>ADD TOOL</h2>
<form:form modelAttribute="userTool">

    <form:hidden path="id"/><br/>

    Tool: <form:select path="tool.id" items="${tools}" itemLabel="name" itemValue="id"/><br/>

    Description:<form:input path="description"/><br/>
    <input type="submit" value="SAVE TOOL"><br/>

</form:form>
