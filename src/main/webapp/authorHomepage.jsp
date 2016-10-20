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
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link href="http://fonts.googleapis.com/css?family=Corben:bold" rel="stylesheet" type="text/css">
        <link href="http://fonts.googleapis.com/css?family=Nobile" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="custom.css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <title>Author Home Page</title>
    </head>
    <body>
        <br><br>
        <form method="POST" action="${pageContext.request.contextPath}/AuthorController?action=view">
            <div class="container container-narrow">
                <h1 id="authHomeHeader">Author Home Page</h1>
                <input type=submit name="submit" class="btn btn-default homeBtn" value="View Authors"/>
               
                <br><br><div class="form-group form-group-lg">
                  <label class="col-lg-2 control-label" for="lg">Enter Your Name: </label>
                  <div class="col-lg-5">
                    <input type="text" name="nameStr" class="form-control input-lg" id="lg" value=""/>
                  </div>
                  <div class="col-lg-5">
                    <input type=submit name="submit" class="btn btn-default okBtn" value="Start Session"/>
                    <input type=submit name="submit" class="btn btn-default cancelBtn" value="End Session"/>
                  </div>
                </div>
            </div>
        </form>
    </body>
</html>
