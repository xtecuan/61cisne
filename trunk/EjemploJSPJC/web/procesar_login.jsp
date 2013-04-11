<%-- 
    Document   : procesar_login
    Created on : 04-08-2013, 07:36:47 PM
    Author     : xtecuan
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

        <%!            private String userSys = "admin";
            private String passwdSys = "adminadmin";
            
            private String userDB = "alumnos";
            private String claveDB = "welcome1";
            private String url = "jdbc:mysql://localhost:3306/alumnos?zeroDateTimeBehavior=convertToNull";
            private static String SELECT_ONE  ;       
            int cuentaONE=0;
            
        %>


        <%

            String outcome = "login.jsp";

            String user = request.getParameter("usuario");
            String clave = request.getParameter("clave");
            SELECT_ONE =  "select count(*) as cuentaONE from usuario where login = '"+ user+ "' and  password = '"+ clave +"'" ;
                Connection conn = null;

                try {

                    DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                    conn = DriverManager.getConnection(url, userDB, claveDB);
                    Statement sta = conn.createStatement();
                    ResultSet rset = sta.executeQuery(SELECT_ONE);

                    while (rset.next()) {

                        cuentaONE = rset.getInt("cuentaONE");
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
            
            
            if (user != null && user.length() > 0 && clave != null && clave.length() > 0) {

                boolean result = false ; //user.equals(userSys) && clave.equals(passwdSys);
                if  (cuentaONE > 0 )
                        {
                    result = true;  
                        }
                        
                if (result) {
                    outcome = "index.jsp";
                } else {

                    session.setAttribute("error", "Usuario y/o clave invalidos!!! ");
                }
            }


        %>

        <jsp:forward page="<%=outcome%>"/>

    </body>
</html>
