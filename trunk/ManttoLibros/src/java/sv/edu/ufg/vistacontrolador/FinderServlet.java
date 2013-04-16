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
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import sv.edu.ufg.modelo.dto.AlumnoDTO;
import sv.edu.ufg.modelo.facade.AlumnosFacade;

/**
 *
 * @author xtecuan
 */
public class FinderServlet extends HttpServlet {

    private static final String ID = "id";
    private static final String ALL = "all";
    private static final String NOMBRES = "nombres";
    private static final String RESPUESTA = "respuestaGrid";
    private static final String ERRORES = "errores";
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
        String find = request.getParameter("find");
        String id = request.getParameter("id");
        String nombres = request.getParameter("nombres");
        String errorPage = request.getParameter("errorPage");
        String viewPage = request.getParameter("viewPage");
        List<AlumnoDTO> respuesta = new ArrayList<AlumnoDTO>(0);
        AlumnoDTO dto = new AlumnoDTO();
        List<String> errores = new ArrayList<String>(0);
        String outcome = null;
        HttpSession session = request.getSession();

        boolean findVal = find != null && find.length() > 0;
        boolean idVal = id != null && id.length() > 0;
        boolean nombresVal = nombres != null && nombres.length() > 0;

        if (findVal) {

            if (find.equals(ALL)) {
                respuesta = AlumnosFacade.findAll(dataSource);
            }

            if (find.equals(ID) && idVal) {
                dto = AlumnosFacade.findById(id, dataSource);
                respuesta.add(dto);
            }

            if (find.equals(NOMBRES) && nombresVal) {
                respuesta = AlumnosFacade.findLikeNames(nombres, dataSource);
            }

            if (respuesta.isEmpty()) {
                errores.add("No se encontrarón registros!!!");
            } else {
                session.setAttribute(RESPUESTA, respuesta);
            }

            outcome = viewPage;

        } else {
            outcome = errorPage;
            errores.add("Se requiere el parametro find valores posibles " + ID + " y " + ALL);
        }

        if (!errores.isEmpty()) {
            session.setAttribute(ERRORES, errores);
        }

        String url = response.encodeRedirectURL(getServletContext().getContextPath() + outcome);

        response.sendRedirect(url);

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
