/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ufg.vistacontrolador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import sv.edu.ufg.modelo.dto.UsuarioDTO;

/**
 *
 * @author xtecuan
 */
public class LoginServlet extends HttpServlet {

    private static String RUTA_ERROR = "/index.jsp";
    private static String RUTA_EXITO = "/app/principal.jsp";
    @Resource(name = "dataSource")
    private DataSource dataSource;

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        List<String> errores = new ArrayList<String>(0);

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String outcome = RUTA_ERROR;

        try {

            boolean valLogin = login != null && login.length() > 0;
            boolean valPassword = password != null && password.length() > 0;

            if (valLogin && valPassword) {

                UsuarioDTO resultado = autenticarUsuario(login, password);

                if (resultado != null) {

                    outcome = RUTA_EXITO;

                    session.setAttribute("usuariologin", resultado);

                } else {

                    errores.add("Usuario/Clave invalida intente de nuevo");

//                    session.setAttribute("errores", errores);
                }

            } else {

                if (!valLogin) {
                    errores.add("El usuario es requerido");
                }

                if (!valPassword) {
                    errores.add("La clave es requerida");
                }

//                session.setAttribute("errores", errores);

            }

            if (!errores.isEmpty()) {
                session.setAttribute("errores", errores);
            }


        } catch (Exception e) {

            e.printStackTrace();
        }

        String url = response.encodeRedirectURL(this.getServletContext().getContextPath() + outcome);
        response.sendRedirect(url);

    }

    private UsuarioDTO autenticarUsuario(String login, String password) {

        UsuarioDTO result = null;

        Connection conn = null;

        try {
            conn = dataSource.getConnection();

            PreparedStatement psta = conn.prepareStatement(UsuarioDTO.QUERY_LOGIN);
            psta.setString(1, login);
            psta.setString(2, password);

            ResultSet rset = psta.executeQuery();

            while (rset.next()) {

                result = new UsuarioDTO();
                result.setId(rset.getInt("id"));
                result.setLogin(login);
                result.setPassword(password);

            }

            rset.close();
            psta.close();

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
