<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
    <title>Book Editor</title>
</head>
<body>
<form action='BookController.do' method='post'>
    <table>
        <tr><td></td><td><input type='hidden' name='id' value="${book.id}"/></td></tr>
        <tr><td>Name:</td><td><input type='text' name='name' value="${book.name}"/></td></tr>
        <tr><td>Description:</td><td><input type='text' name='description' value="${book.description}"/></td></tr>
        <tr><td>Genre:</td><td><input type='text' name='genre' value="${book.genre}"/></td></tr>
        <tr><td>ISBN:</td><td><input type='text' name='ISBN' value="${book.ISBN}"/></td></tr>
        <tr><td>Book:</td><td><input type='text' name='address' value="${book.address}"/></td></tr>
        <tr><td colspan='2'><input type='submit' value='Save'/></td></tr>
    </table>
</form>
</body>
</html>