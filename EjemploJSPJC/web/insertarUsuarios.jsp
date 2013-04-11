<%-- 
    Document   : insertarUsuarios
    Created on : 04-05-2013, 11:13:51 PM
    Author     : javaee
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insertar Usuarios del Sistema</title>

        <script>


            function validarLogin(form1) {


                var user = document.forms[form1].elements['usuario'].value;

                var clave = document.forms[form1].elements['clave'].value;

                var clave2 = document.forms[form1].elements['clave2'].value;
                
                if (clave!=clave2) {
                    
                    alert('Claves digitadas no son iguales!!');
                    
                    return false;

                }
                
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
        <h1>Digitar Usuarios del Sistema</h1>


        <p style="color: red">

            <%

                if (session.getAttribute("error") != null) {

                    out.println(session.getAttribute("error"));

                    session.removeAttribute("error");
                }
            %>

        </p>


        <form name="form1" action="insertarLogin.jsp" method="POST" onsubmit="return validarLogin('form1');">

            <table border="1" cellspacing="1" cellpadding="1">

                <tbody>
                    <tr>
                        <td>Digite Usuario:</td>
                        <td><input type="text" name="usuario" value="" /></td>
                    </tr>
                    <tr>
                        <td>Digite Clave:</td>
                        <td><input type="password" name="clave" value="" /></td>
                    </tr>
                    <tr>
                        <td>Digite de Nuevo Clave:</td>
                        <td><input type="password" name="clave2" value="" /></td>
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
