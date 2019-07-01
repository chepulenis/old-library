<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Library</title>
    </head>
    <body>
        <h3>Welcome to our library!</h3>
        <form>
            <p>Please login:</p>
            <input type="text" name="login" placeholder="Username"/><br>
            <input type="password" name="password" placeholder="Password"/>
            <input type="submit" value="submit">
        </form>
        <p><a href="">Or register new account</a></p>


        <form action="BookController">
            <input type="submit" name="action" value="Catalog" /> — here you can view all books and search for those that you need
        </form>

        <form action ="BookController">
            <input type="submit" name='action' value="Create"> — here you can add a new book
        </form>

        <form action ='UserController'>
            <input type="submit" name="action" value="Users"> — list of users with borrowed books
        </form>
<%--        <button type="button" disabled>Click Me!</button>--%>

<%--        <form action ='UserController' method='post'>--%>
<%--            <input type="submit" value="Sign up/Log in"> — Create user or login if existed--%>
<%--        </form>--%>



    </body>
</html>
