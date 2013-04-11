<%-- 
    Document   : index
    Created on : 04-08-2013, 06:23:17 PM
    Author     : xtecuan
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.math.BigDecimal"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.Date,java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagina de Inicio</title>
        <link rel="stylesheet" type="text/css"  href="${pageContext.servletContext.contextPath}/css/estilos.css">
    </head>
    <body>
        
        
        <%@include  file="/html/header.html" %>
        
        <h1>Pagina de inicio</h1>
        <h2>Pagina de inicio</h2>
        <h6>Pagina de inicio</h6>

        <!--Declaraciones JSP-->
        <%! private String usuario;
            public static final String USER_GEN = "usuario";
            private static int annio = 2013;
            public static final int ITERACIONES = 25;

            public static int getAnnio() {
                return annio++;
            }

            public String getUsuario() {
                return usuario;
            }

            public void setUsuario(String u) {
                this.usuario = u;
            }
            private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            private String user = "alumnos";
            private String clave = "welcome1";
            private String url = "jdbc:mysql://localhost:3306/alumnos?zeroDateTimeBehavior=convertToNull";
            private static String SELECT_ALL = "select * from alumnos ";

        %>

        <!--Notación de Scriptlet-->



        <ul>

            <%


                for (int i = 1; i <= ITERACIONES; i++) {

            %>

            <!--A continuación una expresión JSP-->

            <li><%=i%></li>


            <%                    }

            %>
        </ul>

        <p>

            La fecha actual es: <%=sdf.format(new Date())%>

        </p>


        <%

            BigDecimal x = new BigDecimal("2345.567");

            out.println(x);

        %>

        <table>

            <tr>

                <th>Id:</th>
                <th>Carnet:</th>
                <th>Nombres:</th>
                <th>Apellidos:</th>

            </tr>

            <%
                Connection conn = null;

                try {

                    DriverManager.registerDriver(new com.mysql.jdbc.Driver());

                    conn = DriverManager.getConnection(url, user, clave);


                    Statement sta = conn.createStatement();

                    ResultSet rset = sta.executeQuery(SELECT_ALL);


                    while (rset.next()) {

                        int id = rset.getInt("id");
                        String carnet = rset.getString("carnet");
                        String nombres = rset.getString("nombres");
                        String apellidos = rset.getString("apellidos");

            %>

            <tr>

                <td><%=id%></td>
                <td><%=carnet%></td>
                <td><%=nombres%></td>
                <td><%=apellidos%></td>
            </tr>

            <%
                    }






                } catch (Exception ex) {

                    
                    out.println("<tr><td colspan='4'>"+ex.getMessage()+"</td></tr>");
                    
                    ex.printStackTrace();
                } finally {

                    try {

                        if (conn != null) {
                            conn.close();
                        }

                    } catch (Exception ex) {

                        ex.printStackTrace();
                    }

                }

            %>
        </table>
        <%

            this.setUsuario("xtecuan");

            out.println("<h5>" + this.getUsuario() + "</h5>");

        %>
        
        <%@include  file="/html/footer.html" %>
        
    </body>
</html>
