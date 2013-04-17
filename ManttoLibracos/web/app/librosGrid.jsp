<%-- 
    Document   : librossGrid
    Created on : 04-12-2013, 08:49:55 AM
    Author     : jaimerojas
<% session.getAttribute("clavereg"); %>
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
         <a HREF="/ManttoLibracos/app/principal.jsp">Retornar a Opciones</a>
          <br>

        <c:if test="${not empty respuestaGrid}">
            <%@include  file="gridLibros.jsp" %>
        </c:if>
    </body>
</html>
