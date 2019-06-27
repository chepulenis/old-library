<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Library</title>
    </head>
    <body>
        <h3>Welcome to our library!</h3>

        <form action='BookController' method='post'>
            <input type="submit" value="Catalog" /> — here you can view all books and search for those that you need
        </form>

        <form action ='createoreditbook.jsp'>
            <input type="submit" value="Add Book"> — here you can add a new book
        </form>

        <form action ='ClientController' method='post'>
            <input type="submit" value="Clients"> — list of clients with borrowed books
        </form>

<%--        <form action ='ClientController' method='post'>--%>
<%--            <input type="submit" value="Sign up/Log in"> — Create client or login if existed--%>
<%--        </form>--%>



    </body>
</html>
