<%-- 
    Document   : login
    Created on : 04-08-2013, 07:35:37 PM
    Author     : xtecuan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagina de ingreso al Sistema de Biblioteca</title>

        <script>


            function validarLogin(form1) {


                var user = document.forms[form1].elements['login'].value;

                var clave = document.forms[form1].elements['password'].value;


                if (user !== null && user !== '' && clave !== null && clave !== '') {

                    return true;

                } else {

                    alert('El Usuario y la clave son requeridos!!!');

                    return false;
                }

            }


        </script>
        <%@include file="/WEB-INF/jspf/estilos.jspf" %>
    </head>
    <body>
        <h1>Ingreso al sistema de biblioteca</h1>


        <p style="color: red">

            <%@include  file="/WEB-INF/jspf/errores.jspf" %>

        </p>


        <form name="form1" action="${pageContext.servletContext.contextPath}/login.ufg" method="POST" onsubmit="return validarLogin('form1');">

            <table border="1" cellspacing="1" cellpadding="1">

                <tbody>
                    <tr>
                        <td>Usuario:</td>
                        <td><input type="text" name="login" value="" /></td>
                    </tr>
                    <tr>
                        <td>Clave:</td>
                        <td><input type="password" name="password" value="" /></td>
                    </tr>
                    <tr>
                        <td><input type="reset" value="Limpiar" /></td>
                        <td><input type="submit" value="Entrar" /></td>
                    </tr>
                </tbody>
            </table>


        </form>

    </body>
</html>
