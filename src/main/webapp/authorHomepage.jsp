<%-- 
    Document   : authorHomepage
    Created on : Sep 19, 2016, 2:44:42 PM
    Author     : ritu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="main.css">
        <title>Author Home Page</title>
    </head>
    <body>
        <form method="POST" action="${pageContext.request.contextPath}/AuthorController?action=view">
            <div id="headContainer">
                <h1 id="heading">Author Home Page</h1>
            </div>
            <img class="shape" src="http://bestanimations.com/Books/writing/calligraphy-pen-writing-book-words-close-up-inspiration-animated-gif.gif" alt="WRITING GIF"/>
            <br><input type=submit name="submit" id="homeBtn" class="btn" value="View Authors"/>
        </form>
    </body>
</html>
