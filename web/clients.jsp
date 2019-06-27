<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
    <title>List of all clients</title>
</head>
<body>
<table border="1" width="100%">
    <thead>
    <tr>
        <th>Client ID</th>
        <th>Name</th>
        <th>e-Mail</th>
        <th>Phone</th>
        <th>Book</th>
        <th colspan="2">Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${clients}" var="client">
        <tr>
            <td><c:out value="${client.id}" /></td>
            <td><c:out value="${client.name}" /></td>
            <td><c:out value="${client.email}" /></td>
            <td><c:out value="${client.phoneNumber}" /></td>
            <td><c:out value="${client.BookID}" /></td>
            <td><a href="ClientController.do?action=edit&id=<c:out value="${client.id}"/>">Edit</a></td>
            <td><a href="ClientController.do?action=delete&id=<c:out value="${client.id}"/>">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p>
    <a href="ClientController.do?action=create">Create client</a>
</p>
</body>
</html>