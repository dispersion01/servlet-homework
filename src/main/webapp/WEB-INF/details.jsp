<%@ page import="ru.itpark.domain.Auto" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <%@ include file="bootstrap.jsp" %>
</head>
<body>
<div class="container">
    <div class="row">
        <% if (request.getAttribute("item") != null) { %>
        <% Auto item = (Auto) request.getAttribute("item"); %>
        <div class="col-sm-6 mt-3">
            <div class="card">
                <img src="<%= request.getContextPath() %>/images/<%= item.getImage() %>" class="card-img-top">
                <div class="card-body">
                    <h5 class="card-title"><%= item.getName() %></h5>
                    <p class="card-text"><%= item.getDescription()%></p>
                </div>
            </div>
        </div>
        <% } %>
    </div>
</div>


</body>
</html>