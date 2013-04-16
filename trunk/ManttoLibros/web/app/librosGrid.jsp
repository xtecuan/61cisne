<%-- 
    Document   : librossGrid
    Created on : 04-12-2013, 08:49:55 AM
    Author     : jaimerojas
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de Libros</title>
        <%@include file="/WEB-INF/jspf/estilos.jspf" %>
    </head>
    <body>
        <h1>Listado de Libros</h1>


        <br>
        <br>
        <br>

        <c:if test="${not empty respuestaGrid}">
            <%@include  file="gridLibros.jsp" %>
        </c:if>
        <c:if test="${empty respuestaGrid}">
           <h1>Maloso</h1>
        </c:if>


    </body>
</html>
