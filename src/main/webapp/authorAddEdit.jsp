<%-- 
    Document   : authorAddEdit
    Created on : Oct 5, 2016, 1:13:56 AM
    Author     : ritu
--%>

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
                        <th></th>
                        <th>Author ID</th>
                        <th>Name</th>
                        <th>Date Added</th>
                        <c:forEach var="item" items="${editList}">
                            <tr>
                                <td>${item.authorId}</td>
                                <td>${item.authorName}</td>
                                <td><fmt:formatDate type="date" value=${item.dateAdded}/></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div><br>
            <input type="submit" name="submit" value="save"/>&nbsp;
            <input type="submit" name="submit" value="cancel"/>
        </form>
    </body>
</html>
