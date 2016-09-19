<%-- 
    Document   : authorTablePage
    Created on : Sep 19, 2016, 2:48:28 PM
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
        <title>Author Table</title>
    </head>
    <body>
        <div id="headContainer">
            <h1 id="heading">Table of Authors</h1>
        </div>
        <div class="container">
            <div class="inside">
                <table>
                <th>Author ID</th>
                <th>Name</th>
                <th>Date Added</th>
                <c:forEach var="item" items="${authorList}">
                    <tr>
                        <td>${item.authorId}</td>
                        <td>${item.authorName}</td>
                        <td><fmt:formatDate type="date" value="${item.dateAdded}"/></td>
                    </tr>
                </c:forEach>
                </table>
            </div>
        </div><br>
        
        <a href="authorHomepage.jsp"><button type=button class="btn">Back to Home Page</button></a>
    </body>
</html>
