<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
    <title>User Editor</title>
</head>
<body>
<form action='UserController.do' method='post'>
    <table>
        <tr><td></td><td><input type='hidden' name='id' value="${user.id}"/></td></tr>
        <tr><td>Name:</td><td><input type='text' name='name' value="${user.name}"/></td></tr>
        <tr><td>email:</td><td><input type='text' name='email' value="${user.email}"/></td></tr>
        <tr><td>Phone:</td><td><input type='text' name='phoneNumber' value="${user.phoneNumber}"/></td></tr>
        <tr><td>BookID:</td><td><input type='text' name='bookID' value="${user.book_id}"/></td></tr>
        <tr><td colspan='2'><input type='submit' value='Save'/></td></tr>
    </table>
</form>
</body>
</html>