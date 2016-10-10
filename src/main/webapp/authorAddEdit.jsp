<%-- 
    Document   : authorAddEdit
    Created on : Oct 5, 2016, 1:13:56 AM
    Author     : ritu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="main.css">
        <title>Author Table</title>
    </head>
    <body>
        <form method="POST" action="${pageContext.request.contextPath}/AuthorController?action=view">
            <div id="headContainer">
                <h1 id="heading">Manage Authors</h1>
            </div>
            <div class="container">
                <div class="inside">
                    <table>
                        <c:choose>
                            <c:when test="${not empty author}">
                            <tr>
                                <td><th>Author Id</th></td>
                                <td><input type="text" name="idObj" value="${author.authorId}" readonly/></td>
                            </tr>
                            </c:when>
                        </c:choose>
                            <tr>
                                <td><th>Author Name</th></td>
                                <td><input type="text" name="nameObj" value="${author.authorName}"/></td>
                            </tr>
                        <c:choose>
                            <c:when test="${not empty author}">
                            <tr>
                                <td><th>Date Added</th></td>
                                <td><input type="date" name="dateObj" value="${author.dateAdded}"/></td>
                            </tr>
                            <input type="submit" name="submit" value="Save"/>
                            </c:when>
                        </c:choose>
                    </table>
                </div>
            </div><br>
            <c:choose>
                <c:when test="${empty author}">
                    <input type="submit" name="submit" value="Create"/>&nbsp;
                </c:when>
            </c:choose>
            <input type="submit" name="submit" value="Cancel"/>
        </form>
    </body>
</html>
