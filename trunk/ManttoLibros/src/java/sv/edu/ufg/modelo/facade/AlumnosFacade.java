/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ufg.modelo.facade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import sv.edu.ufg.modelo.dto.AlumnoDTO;

/**
 *
 * @author xtecuan
 */
public class AlumnosFacade {

    public static List<AlumnoDTO> findLikeNames(String nombres, DataSource dataSource) {

        List<AlumnoDTO> respuesta = new ArrayList<AlumnoDTO>(0);

        Connection conn = null;

        try {
            conn = dataSource.getConnection();

            PreparedStatement psta = conn.prepareStatement(AlumnoDTO.getSELECT_BY_NAME());
            psta.setString(1, nombres);


            ResultSet rset = psta.executeQuery();

            while (rset.next()) {

                AlumnoDTO dto = new AlumnoDTO();

                dto.setId(rset.getInt("id"));
                dto.setCarnet(rset.getString("carnet"));
                dto.setNombres(rset.getString("nombres"));
                dto.setApellidos(rset.getString("apellidos"));

                String correo = rset.getString("correo");

                if (correo != null && correo.length() > 0) {
                    dto.setCorreo(correo);
                }

                java.sql.Date fechaNac = rset.getDate("fechanac");

                if (fechaNac != null) {
                    dto.setFechanac(new Date(fechaNac.getTime()));
                }

                respuesta.add(dto);

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

        return respuesta;
    }

    public static AlumnoDTO findById(String id, DataSource dataSource) {

        AlumnoDTO respuesta = null;

        Connection conn = null;

        try {
            conn = dataSource.getConnection();

            PreparedStatement psta = conn.prepareStatement(AlumnoDTO.getSELECT_BY_ID());
            psta.setInt(1, Integer.valueOf(id));


            ResultSet rset = psta.executeQuery();

            while (rset.next()) {

                respuesta = new AlumnoDTO();

                respuesta.setId(rset.getInt("id"));
                respuesta.setCarnet(rset.getString("carnet"));
                respuesta.setNombres(rset.getString("nombres"));
                respuesta.setApellidos(rset.getString("apellidos"));

                String correo = rset.getString("correo");

                if (correo != null && correo.length() > 0) {
                    respuesta.setCorreo(correo);
                }

                java.sql.Date fechaNac = rset.getDate("fechanac");

                if (fechaNac != null) {
                    respuesta.setFechanac(new Date(fechaNac.getTime()));
                }



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

        return respuesta;
    }

    public static List<AlumnoDTO> findAll(DataSource dataSource) {

        List<AlumnoDTO> respuesta = new ArrayList<AlumnoDTO>(0);

        Connection conn = null;

        try {
            conn = dataSource.getConnection();

            PreparedStatement psta = conn.prepareStatement(AlumnoDTO.getSELECT_ALL());


            ResultSet rset = psta.executeQuery();

            while (rset.next()) {

                AlumnoDTO dto = new AlumnoDTO();

                dto.setId(rset.getInt("id"));
                dto.setCarnet(rset.getString("carnet"));
                dto.setNombres(rset.getString("nombres"));
                dto.setApellidos(rset.getString("apellidos"));

                String correo = rset.getString("correo");

                if (correo != null && correo.length() > 0) {
                    dto.setCorreo(correo);
                }

                java.sql.Date fechaNac = rset.getDate("fechanac");

                if (fechaNac != null) {
                    dto.setFechanac(new Date(fechaNac.getTime()));
                }

                respuesta.add(dto);

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

        return respuesta;
    }

    public static AlumnoDTO fromMapToObject(Map<String, Object> params) {

        AlumnoDTO dto = new AlumnoDTO();

        for (String key : params.keySet()) {

            if (key.equals("id")) {
                dto.setId((Integer) params.get(key));
            }

            if (key.equals("carnet")) {
                dto.setCarnet((String) params.get(key));
            }

            if (key.equals("nombres")) {
                dto.setNombres((String) params.get(key));
            }

            if (key.equals("apellidos")) {
                dto.setApellidos((String) params.get(key));
            }

            if (key.equals("correo")) {
                dto.setCorreo((String) params.get(key));
            }

            if (key.equals("fechanac")) {
                Date fechanac = (Date) params.get(key);
                dto.setFechanac(fechanac);
            }
        }

        return dto;
    }

    public static void setPreparedStatementParameters(Map<String, Object> params, PreparedStatement psta) throws SQLException {

        int i = 1;
        for (String key : params.keySet()) {

            Object param = params.get(key);

            if (param instanceof Integer) {

                psta.setInt(i, (Integer) param);
            }

            if (param instanceof String) {
                psta.setString(i, (String) param);
            }

            if (param instanceof java.util.Date) {
                Date fecha = (Date) param;
                psta.setDate(i, new java.sql.Date(fecha.getTime()));
            }

            if (param instanceof Long) {
                psta.setLong(i, (Long) param);
            }

            i++;
        }
    }

    public static void setPreparedStatementParametersUp(Map<String, Object> params, PreparedStatement psta) throws SQLException {

        String[] names = AlumnoDTO.getNamesForUpdate(params);

        int j = 1;
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            Object param = params.get(name);
            if (param instanceof Integer) {

                psta.setInt(j, (Integer) param);
            }

            if (param instanceof String) {
                psta.setString(j, (String) param);
            }

            if (param instanceof java.util.Date) {
                Date fecha = (Date) param;
                psta.setDate(j, new java.sql.Date(fecha.getTime()));
            }

            if (param instanceof Long) {
                psta.setLong(j, (Long) param);
            }

            j++;

        }

        psta.setInt(j, (Integer) params.get("id"));

    }

    public static AlumnoDTO createAlumno(Map<String, Object> params, DataSource dataSource) {
        AlumnoDTO r = null;
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            String sql = AlumnoDTO.getInsert(params);
            PreparedStatement psta = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            setPreparedStatementParameters(params, psta);
            System.out.println("Ejecutando query: " + sql);
            int rrr = psta.executeUpdate();
            if (rrr == 1) {
                ResultSet rset = psta.getGeneratedKeys();

                while (rset.next()) {

                    r = fromMapToObject(params);
                    int id = rset.getInt(1);

                    r.setId(id);
                }

                rset.close();
            } else {

                System.err.println("No se ingreso ningun registro debido a un problema de persistencia!!!");
            }

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

        return r;
    }

    public static AlumnoDTO editAlumno(Map<String, Object> params, DataSource dataSource) {
        AlumnoDTO r = null;
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            String sql = AlumnoDTO.getUpdate(params);
            PreparedStatement psta = conn.prepareStatement(sql);
            setPreparedStatementParametersUp(params, psta);
            System.out.println("Ejecutando query: " + sql);
            int rrr = psta.executeUpdate();
            if (rrr == 1) {
                r = fromMapToObject(params);
            } else {

                System.err.println("No se ingreso ningun registro debido a un problema de persistencia!!!");
            }

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

        return r;
    }

    public static AlumnoDTO removeAlumno(Map<String, Object> params, DataSource dataSource) {
        AlumnoDTO r = null;
        Connection conn = null;
        try {

            conn = dataSource.getConnection();
            PreparedStatement psta = conn.prepareStatement(AlumnoDTO.getDELETE());
            psta.setInt(1, (Integer) params.get("id"));

            int rrr = psta.executeUpdate();

            if (rrr == 1) {

                r = fromMapToObject(params);
            }
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
        return r;
    }
}
