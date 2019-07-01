<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
    <title>List of all users</title>
</head>
<body>
<table border="1" width="100%">
    <thead>
    <tr>
        <th>User ID</th>
        <th>Name</th>
        <th>e-Mail</th>
        <th>Phone</th>
        <th colspan="2">Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.id}" /></td>
            <td><c:out value="${user.name}" /></td>
            <td><c:out value="${user.email}" /></td>
            <td><c:out value="${user.phoneNumber}" /></td>
            <td><a href="UserController.do?action=edit&id=<c:out value="${user.id}"/>">Edit</a></td>
            <td><a href="UserController.do?action=delete&id=<c:out value="${user.id}"/>">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p>
    <a href="UserController.do?action=create">Create user</a>
</p>
</body>
</html>