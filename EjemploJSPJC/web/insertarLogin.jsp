<%-- 
    Document   : insertarLogin.jsp
    Created on : 04-05-2013, 11:27:53 PM
    Author     : javaee
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Procesar Login</title>
    </head>
    <body>

        <%! 
            private String userDB = "alumnos";
            private String claveDB = "welcome1";
            private String url = "jdbc:mysql://localhost:3306/alumnos?zeroDateTimeBehavior=convertToNull";
            private static String UPDATE_ONE  ;       
            private static String SELEC_MAX = "Select max(id) as maxuser from usuario "  ; 
            int cuentaONE=0;
            
        %>


        <%

            String outcome = "login.jsp";
            int resultado = 0;
            int maxUser = 0;
            String user = request.getParameter("usuario");
            String clave = request.getParameter("clave");
             out.println("usuario ");
             out.println(user);
             out.println("clave") ;
             out.println(clave);
            
             
             Connection conn1 = null;
                try {

                    DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                    conn1 = DriverManager.getConnection(url, userDB, claveDB);
                    Statement sta = conn1.createStatement();
                    ResultSet rset1 = sta.executeQuery(SELEC_MAX);
                    while (rset1.next()) {

                        maxUser = rset1.getInt("maxuser");
                    }                    
                    maxUser++;
                    
                } catch (Exception ex) {
                    out.println("<tr><td colspan='4'>"+ex.getMessage()+"</td></tr>");
                    ex.printStackTrace();
                } finally {
                    try {
                        if (conn1 != null) {
                            conn1.close();
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }                                 
                     
                     
                UPDATE_ONE =  "insert into usuario (id,login,password) values("+maxUser+",'"+ user+ "','"+ clave +"')" ;
                Connection conn = null;

                try {

                    DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                    conn = DriverManager.getConnection(url, userDB, claveDB);
                    Statement sta = conn.createStatement();
                    resultado = sta.executeUpdate(UPDATE_ONE);
                    
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
             
            out.println("");
            out.println("Gracias por su preferencia resultado ");
            session.setAttribute("error", "Felicidades Almaceno Correctamente el Usuario "+user);
        %>

          <jsp:forward page="<%=outcome%>"/>

    </body>
</html>
