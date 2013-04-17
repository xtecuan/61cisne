<%-- 
    Document   : librosCrear
    Created on : 04-12-2013, 10:10:18 AM
    Author     : Jaime
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Creación Libros</title>
        
        <%@include file="/WEB-INF/jspf/functions.jspf" %>
        <%@include file="/WEB-INF/jspf/estilos.jspf" %>
    </head>
    <body>
        <h1>Creación Libros</h1>
        <br>
         <a HREF="/ManttoLibracos/app/principal.jsp">Retornar a Opciones</a>
        <br>

        <%@include  file="/WEB-INF/jspf/mensajes.jspf" %>

        <%@include  file="/WEB-INF/jspf/errores.jspf" %>
     
        <form name="form1" action="${pageContext.servletContext.contextPath}/DMLLibros" method="POST" >

            <input type="hidden" name="action" value="create" />
            <input type="hidden" name="errorPage" value="/app/librosCrear.jsp" />
            <input type="hidden" name="viewPage" value="/app/librosGrid.jsp" />

            <table border="1">

                <tbody>
                    <!--tr>
                        <td>Id:</td>
                        <td></td>
                    </tr-->
                    <tr>
                        <td>Codigo</td>
                        <td><input type="text" name="codigo" value="" /></td>
                    </tr>
                    <tr>
                        <td>Nombre</td>
                        <td><input type="text" name="nombre" value="" /></td>
                    </tr>
                    <tr>
                        <td>Autor</td>
                        <td><input type="text" name="autor" value="" /></td>
                    </tr>
                    <tr>
                        <td>ISBN</td>
                        <td><input type="text" name="isbn" value="" /></td>
                    </tr>
                    <tr>
                        <td>Fecha Impresion (dd/MM/yyyy)</td>
                        <td><input type="text" name="fechaimpresion" value="" /></td>
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
