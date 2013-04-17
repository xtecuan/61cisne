/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ufg.vistacontrolador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class DMLServlet extends HttpServlet {

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static final String ID = "id";
    private static final String ACTION_NEW = "create";
    private static final String ACTION_EDIT = "edit";
    private static final String ACTION_REMOVE = "remove";
    private static final String RESPUESTA = "respuesta";
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
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        String carnet = request.getParameter("carnet");
        String nombres = request.getParameter("nombres");
        String apellidos = request.getParameter("apellidos");
        String correo = request.getParameter("correo");
        String fechanac = request.getParameter("fechanac");
        String errorPage = request.getParameter("errorPage");
        String viewPage = request.getParameter("viewPage");
        AlumnoDTO respuesta = null;
        List<String> errores = new ArrayList<String>(0);
        String outcome = null;
        HttpSession session = request.getSession();

        boolean actionVal = action != null && action.length() > 0;
        boolean idVal = id != null && id.length() > 0;
        boolean carnetVal = carnet != null && carnet.length() == 10;
        boolean nombresVal = nombres != null && nombres.length() > 0;
        boolean apellidosVal = apellidos != null && apellidos.length() > 0;
        boolean correoVal = correo != null && correo.length() > 0;
        boolean fechanacVal = fechanac != null && fechanac.length() == "dd/MM/yyyy".length();
        Map<String, Object> params = new HashMap<String, Object>(0);

        if (actionVal) {


            if (idVal) {
                params.put("id", Integer.valueOf(id));
            }

            if (carnetVal) {
                params.put("carnet", carnet);
            }

            if (nombresVal) {
                params.put("nombres", nombres);
            }

            if (apellidosVal) {
                params.put("apellidos", apellidos);
            }

            if (correoVal) {
                params.put("correo", correo);
            }

            if (fechanacVal) {
                try {
                    params.put("fechanac", sdf.parse(fechanac));
                } catch (Exception ex) {
                    errores.add("Error convirtiendo el texto: " + fechanac + " a fecha !!!");
                }
            }

            if (action.equals(ACTION_NEW)) {
                respuesta = AlumnosFacade.createAlumno(params, dataSource);
                List<AlumnoDTO> respuestaGrid = AlumnosFacade.findAll(dataSource);
                session.setAttribute("respuestaGrid", respuestaGrid);
            }

            if (action.equals(ACTION_EDIT) && idVal) {
                respuesta = AlumnosFacade.editAlumno(params, dataSource);
                List<AlumnoDTO> respuestaGrid = AlumnosFacade.findAll(dataSource);
                session.setAttribute("respuestaGrid", respuestaGrid);
            }

            if (action.equals(ACTION_REMOVE) && idVal) {
                respuesta = AlumnosFacade.removeAlumno(params, dataSource);

                List<AlumnoDTO> respuestaGrid = AlumnosFacade.findAll(dataSource);
                session.setAttribute("respuestaGrid", respuestaGrid);

            }

            if (respuesta == null) {
                errores.add("Error: De persistencia al ejecutar la acción: " + action);
            } else {
                session.setAttribute(RESPUESTA, respuesta);
            }

            outcome = viewPage;

        } else {
            outcome = errorPage;
            errores.add("Se requiere el parametro action valores posibles " + ACTION_NEW + " , " + ACTION_EDIT + " y " + ACTION_REMOVE);
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
