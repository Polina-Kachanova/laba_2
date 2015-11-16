<anyxmlelement xmlns:c="http://java.sun.com/jsp/jstl/core" prefix="c" >
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Recipes List</title>
</head>
<body>
<h1>Recipes</h1>

<c:url var="addUrl" value="/LazyCook/src/main/java/com/lazycook/mvc/Recipe.java" />
<table style="border: 1px solid; width: 500px; text-align:center">
    <thead style="background:#fcf">
    <tr>
        <th>Recipe Name</th>
        <th>Description</th>
        <th>Ingredients</th>
        <th colspan="3"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${recipes}" var="recipe">
        <c:url var="editUrl" value="/krams/main/persons/edit?id=${recipe.id}" />
        <c:url var="deleteUrl" value="/krams/main/persons/delete?id=${recipe.id}" />
        <tr>
            <td><c:out value="${recipe.Name}" /></td>
            <td><c:out value="${recipe.Description}" /></td>
            <td><c:out value="${recipe.Ingredients}" /></td>
            <td><a href="${editUrl}">Edit</a></td>
            <td><a href="${deleteUrl}">Delete</a></td>
            <td><a href="${addUrl}">Add</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<c:if test="${empty recipes}">
    There are currently no recipes in the list. <a href="${addUrl}">Add</a> a recipe.
</c:if>

</body>
</html>
</anyxmlelement>