<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html; charset=UTF-8" %>


<form:form  method="post">

    Czy chcesz oddać ${borrowing.userTool} pożyczoną od ${borrowing.userTool.user}<br/>
    <br>
   <input type="hidden" path="toReturnId" value="${borrowing.id}"> <br/>
    Komentarz/Uwagi:<input type="text" value="" name="comment"/><br/>

    <button> <input type="submit" value="RETURN"> </button><br/>

</form:form>