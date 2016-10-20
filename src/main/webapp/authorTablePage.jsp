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
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link href="http://fonts.googleapis.com/css?family=Corben:bold" rel="stylesheet" type="text/css">
        <link href="http://fonts.googleapis.com/css?family=Nobile" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="custom.css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <title>${name} Author Table</title>
    </head>
    <body>
        <br><br>
        <form method="POST" action="${pageContext.request.contextPath}/AuthorController?action=manage">
            <div class="container container-narrow">
                <div class="row">
                    <h1 class="col-lg-12">${name} Table of Authors</h1>
                </div><br><br>
                <div class="leftDiv">
                    <input type="submit" name="submit" class="btn btn-default okBtn" value="add/edit"/>&nbsp;
                    <input type="submit" name="submit" class="btn btn-default cancelBtn" value="delete"/><br><br>
                    <table class="table table-hover">
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
                    <input type="submit" name="submit" class="btn btn-default okBtn" value="add/edit"/>&nbsp;
                    <input type="submit" name="submit" class="btn btn-default cancelBtn" value="delete"/><br><br>
                </div>
                <a href="authorHomepage.jsp"><button type=button class="btn btn-default homeBtn">Back to Home Page</button></a>
            </div>
        </form><br>
        <c:if test="${errMsg != null}">
            <div>${errMsg}<div><br>
        </c:if>
    </body>
</html>
