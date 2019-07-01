<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
    <title>List of all books</title>
</head>
<body>
<table border="1" width="100%">
    <thead>
    <tr>
        <th>Book ID</th>
        <th>Title</th>
        <th>Author</th>
        <th>Description</th>
        <th>Genre</th>
        <th>ISBN</th>
        <th>Address</th>
        <th>Take day</th>
        <th>Expiration day</th>
        <th colspan="3">Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${books}" var="book">
        <tr>
            <td><c:out value="${book.id}" /></td>
            <td><c:out value="${book.title}" /></td>
            <td><c:out value="${book.author}" /></td>
            <td><c:out value="${book.description}" /></td>
            <td><c:out value="${book.genre}" /></td>
            <td><c:out value="${book.ISBN}" /></td>
            <td><c:out value="${book.address}" /></td>
            <td><c:out value="${book.takeDate}" /></td>
            <td><c:out value="${book.expirationDate}" /></td>
            <td><a href="BookController.do?action=edit&id=<c:out value="${book.id}"/>">Edit</a></td>
            <td><a href="BookController.do?action=delete&id=<c:out value="${book.id}"/>">Delete</a></td>
            <td><a href="BookController.do?action=add&id=<c:out value="${book.id}"/>">Add to user</a></td>
<%--            <input type="submit" value="Add to user">--%>
        </tr>
    </c:forEach>
    </tbody>
</table>

<c:if test="${currentPage != 1}">
    <td><a href="BookController.do?action&=${currentPage - 1}">Previous</a></td>
</c:if>

<table border="1">
    <tr>
        <c:forEach begin="1" end="${numberOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <td><a href="BookController.do?action&page=${i}">${i}</a></td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </tr>
</table>

<c:if test="${currentPage lt numberOfPages}">
    <td><a href="BookController.do?action&page=${currentPage + 1}">Next</a></td>
</c:if>

<p>
    <a href="BookController.do?action=create">Create book</a>
</p>
</body>
</html>