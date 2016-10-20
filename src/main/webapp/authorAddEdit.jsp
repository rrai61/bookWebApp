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
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link href="http://fonts.googleapis.com/css?family=Corben:bold" rel="stylesheet" type="text/css">
        <link href="http://fonts.googleapis.com/css?family=Nobile" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="custom.css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <title>Manage Authors</title>
    </head>
    <body>
        <br><br>
        <form method="POST" action="${pageContext.request.contextPath}/AuthorController?action=view">
            <div class="container container-narrow">
                <div class="row">
                    <h1 class="col-lg-12">Manage Authors</h1>
                </div><br><br>
                <div class="form-group">
                    <table class="table table-bordered">
                        <c:choose>
                            <c:when test="${not empty author}">
                            <tr>
                                <td><th>Author Id</th></td>
                                <td><input type="text" name="idObj" class="form-control" value="${author.authorId}" readonly/></td>
                            </tr>
                            </c:when>
                        </c:choose>
                            <tr>
                                <td><th>Author Name</th></td>
                                <td><input type="text" name="nameObj" class="form-control" value="${author.authorName}"/></td>
                            </tr>
                        <c:choose>
                            <c:when test="${not empty author}">
                            <tr>
                                <td><th>Date Added</th></td>
                                <td><input type="date" name="dateObj" class="form-control" value="${author.dateAdded}"/></td>
                            </tr>
                            </c:when>
                        </c:choose>
                    </table>
                    <c:choose>
                        <c:when test="${not empty author}">
                            <input type="submit" name="submit" class="btn btn-default okBtn" value="Save"/>
                        </c:when>
                    </c:choose>
                </div><br>
                <c:choose>
                    <c:when test="${empty author}">
                        <input type="submit" name="submit" class="btn btn-default okBtn" value="Create"/>&nbsp;
                    </c:when>
                </c:choose>
                <input type="submit" name="submit" class="btn btn-default cancelBtn" value="Cancel"/>
            </div>            
        </form>
        <c:if test="${errMsg != null}">
            <div>${requestScope.errMsg}<div><br>
        </c:if>
    </body>
</html>
