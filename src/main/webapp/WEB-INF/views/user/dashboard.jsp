<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>



<c:forEach items="${books}" var="b">
    <hr>
    <strong><c:out value="${b.title}, Publishers: ${b.publisher.name}: "/></strong><br>
    <c:out value="${b.description}"/><br>
    <c:forEach items="${b.authors}" var="a">
        ${a.firstName}  ${a.lastName}
    </c:forEach> <br>
    <a href="/book/form/edit/${b.id}">Edit book</a>
    <a href="/book/form/deleteConfirmation/${b.id}">Remove book</a>


</c:forEach>

<hr>

<a href="add">Add new book</a>