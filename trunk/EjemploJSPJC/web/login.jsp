<%-- 
    Document   : login
    Created on : 04-08-2013, 07:35:37 PM
    Author     : xtecuan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagina de ingreso al sistema</title>

        <script>


            function validarLogin(form1) {


                var user = document.forms[form1].elements['usuario'].value;

                var clave = document.forms[form1].elements['clave'].value;


                if (user !== null && user !== '' && clave !== null && clave !== '') {

                    return true;

                } else {

                    alert('El Usuario y la clave son requeridos!!!');

                    return false;
                }

            }


        </script>

    </head>
    <body>
        <h1>Ingreso al sistema</h1>


        <p style="color: red">

            <%

                if (session.getAttribute("error") != null) {

                    out.println(session.getAttribute("error"));

                    session.removeAttribute("error");
                }
            %>

        </p>


        <form name="form1" action="procesar_login.jsp" method="POST" onsubmit="return validarLogin('form1');">

            <table border="1" cellspacing="1" cellpadding="1">

                <tbody>
                    <tr>
                        <td>Usuario:</td>
                        <td><input type="text" name="usuario" value="" /></td>
                    </tr>
                    <tr>
                        <td>Clave:</td>
                        <td><input type="password" name="clave" value="" /></td>
                    </tr>
                    <tr>
                        <td><input type="reset" value="Limpiar" /></td>
                        <td><input type="submit" value="Entrar" /></td>
                    </tr>
                </tbody>
            </table>
<% out.println("");
out.println("");
%>
        <a href="${pageContext.servletContext.contextPath}/insertarUsuarios.jsp">Insertar Nuevo Usuario?</a>
        </form>

    </body>
</html>
