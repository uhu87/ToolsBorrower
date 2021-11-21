<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html; charset=UTF-8" %>

<h2>Borrowing details</h2>
<form:form  method="post">

    Czy chcesz oddac ${borrowing.userTool} pozyczona od ${borrowing.user}<br/>
    <br>
   <input type="hidden" path="toRemoveId" value="${borrowing.id}"> <br/>

    <button> <input type="submit" value="RETURN"> </button><br/>

</form:form>