<%-- 
    Document   : authorTablePage
    Created on : Sep 19, 2016, 2:48:28 PM
    Author     : ritu
--%>

<%@page import="edu.wctc.rr.model.MySqlDBStrategy"%>
<%@page import="edu.wctc.rr.model.AuthorDao"%>
<%@page import="edu.wctc.rr.model.AuthorDaoStrategy"%>
<%@page import="edu.wctc.rr.model.AuthorService"%>
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
        <form method="POST" action="${pageContext.request.contextPath}/AuthorController?action=manage">
            <div id="headContainer">
                <h1 id="heading">Table of Authors</h1>
            </div>
            <input type="submit" name="submit" value="delete"/>&nbsp;
            <input type="submit" name="submit" value="add/edit"/>
            <div class="container">
                <div class="inside">
                    <table>
                        <th></th>
                        <th>Author ID</th>
                        <th>Name</th>
                        <th>Date Added</th>
                        <c:forEach var="item" items="${authorList}">
                            <tr>
                                <td><input type="checkbox" name="authorCheck" value="${item.authorId}"/>&nbsp;</td>
                                <td>${item.authorId}</td>
                                <td>${item.authorName}</td>
                                <td><fmt:formatDate type="date" value="${item.dateAdded}"/></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div><br>
        </form>
        <a href="authorHomepage.jsp"><button type=button class="btn">Back to Home Page</button></a>
    </body>
</html>
