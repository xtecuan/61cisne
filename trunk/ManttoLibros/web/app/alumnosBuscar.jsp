<%-- 
    Document   : alumnosBuscar
    Created on : 04-12-2013, 10:44:27 AM
    Author     : xtecuan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Busqueda de alumnos</title>

        <%@include file="/WEB-INF/jspf/functions.jspf" %>
        <%@include file="/WEB-INF/jspf/estilos.jspf" %>
    </head>
    <body>
        <h1>Buscador Alumnos</h1>

        <form name="formBusqueda" action="${pageContext.servletContext.contextPath}/find.ufg" method="POST">

            <input type="hidden" name="errorPage" value="/app/alumnosBuscar.jsp" />
            <input type="hidden" name="viewPage" value="/app/alumnosBuscar.jsp" />

            <table border="1" cellspacing="1" cellpadding="1">

                <tbody>
                    <tr>
                        <td>Tipo de Busqueda</td>
                        <td>
                            <select name="find" onchange="enableTextSearch('formBusqueda', 'find');">
                                <option value="">Seleccione</option>
                                <option value="id">Busqueda por Id</option>
                                <option value="nombres">Busqueda por nombres</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Id:</td>
                        <td><input disabled="true" type="text" name="id" value="" /></td>
                    </tr>
                    <tr>
                        <td>Nombres:</td>
                        <td><input disabled="true" type="text" name="nombres" value="" /></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" value="Buscar" /></td>

                    </tr>
                </tbody>
            </table>



        </form>

        <br>
        <br>
        <br>
        <c:if test="${not empty respuestaGrid}">
            <%@include  file="grid.jsp" %>
        </c:if>

    </body>
</html>
