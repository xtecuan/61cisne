<%-- 
    Document   : alumnosCrear
    Created on : 04-12-2013, 10:10:18 AM
    Author     : xtecuan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Creación Alumno</title>
        <%@include file="/WEB-INF/jspf/functions.jspf" %>
        <%@include file="/WEB-INF/jspf/estilos.jspf" %>
    </head>
    <body>
        <h1>Creación Alumno</h1>

        <%@include  file="/WEB-INF/jspf/mensajes.jspf" %>

        <%@include  file="/WEB-INF/jspf/errores.jspf" %>


        <form name="form1" action="${pageContext.servletContext.contextPath}/dml.ufg" method="POST" onsubmit="return validarFormularioCreate('form1', 'carnet,nombres,apellidos');">

            <input type="hidden" name="action" value="edit" />
            <input type="hidden" name="errorPage" value="/app/alumnosEditar.jsp" />
            <input type="hidden" name="viewPage" value="/app/alumnosGrid.jsp" />
            <input type="hidden" name="id" value="${param.id}" />

            <table border="1">

                <tbody>
                    <!--tr>
                        <td>Id:</td>
                        <td></td>
                    </tr-->
                    <tr>
                        <td>Carnet</td>
                        <td><input type="text" name="carnet" value="${param.carnet}" /></td>
                    </tr>
                    <tr>
                        <td>Nombres</td>
                        <td><input type="text" name="nombres" value="${param.nombres}" /></td>
                    </tr>
                    <tr>
                        <td>Apellidos</td>
                        <td><input type="text" name="apellidos" value="${param.apellidos}" /></td>
                    </tr>
                    <tr>
                        <td>Correo</td>
                        <td><input type="text" name="correo" value="${param.correo}" /></td>
                    </tr>
                    <tr>
                        <td>Fecha Nacimiento (dd/MM/yyyy)</td>
                        <td><input type="text" name="fechanac" value="${param.fechanac}" /></td>
                    </tr>
                    <tr>
                        <td><input type="reset" value="Limpiar" /></td>
                        <td><input type="submit" value="Enviar" /></td>
                    </tr>
                </tbody>
            </table>


        </form>


    </body>
</html>
