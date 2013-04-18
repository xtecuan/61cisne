/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ufg.modelo.facade;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import sv.edu.ufg.modelo.dto.LibroDTO;
import static sv.edu.ufg.modelo.facade.AlumnosFacade.fromMapToObject;
import static sv.edu.ufg.modelo.facade.AlumnosFacade.setPreparedStatementParameters;
import static sv.edu.ufg.modelo.facade.AlumnosFacade.setPreparedStatementParametersUp;


/**
 *
 * @author javaee
 */
public class LibrosFacade { 
    private static String user = "alumnos";
    private static String clave = "welcome1";
    private static String url = "jdbc:mysql://localhost:3306/alumnos?zeroDateTimeBehavior=convertToNull";
    
    private static String SELECT_ALL = "select * from Libros ";
    
       public static List<LibroDTO> findLikeNames(String nombres, DataSource dataSource) {

        List<LibroDTO> respuesta = new ArrayList<LibroDTO>(0);

        Connection conn = null;

        try {
            conn = dataSource.getConnection();

            PreparedStatement psta = conn.prepareStatement(LibroDTO.getSELECT_BY_NAME());
            psta.setString(1, nombres);


            ResultSet rset = psta.executeQuery();

            while (rset.next()) {

                LibroDTO dto = new LibroDTO();

                dto.setId_libro(rset.getInt("id"));
                dto.setCodigo(rset.getString("codigo"));
                dto.setNombre(rset.getString("nombre"));
                dto.setAutor(rset.getString("autor"));

                String correo = rset.getString("isbn");

                if (correo != null && correo.length() > 0) {
                    dto.setisbn(correo);
                }

                java.sql.Date fechaNac = rset.getDate("fechaimpresion");

                if (fechaNac != null) {
                    dto.setFechaimpresion(new Date(fechaNac.getTime()));
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

    public static LibroDTO findById(String id, DataSource dataSource) {

        LibroDTO respuesta = null;

        Connection conn = null;

        try {
            conn = dataSource.getConnection();

            PreparedStatement psta = conn.prepareStatement(LibroDTO.getSELECT_BY_ID());
            psta.setInt(1, Integer.valueOf(id));


            ResultSet rset = psta.executeQuery();

            while (rset.next()) {

                respuesta = new LibroDTO();

                respuesta.setId_libro(rset.getInt("id"));
                respuesta.setCodigo(rset.getString("codigo"));
                respuesta.setNombre(rset.getString("nombre"));
                respuesta.setAutor(rset.getString("autor"));

                String correo = rset.getString("isbn");

                if (correo != null && correo.length() > 0) {
                    respuesta.setisbn(correo);
                }

                java.sql.Date fechaNac = rset.getDate("fechaimpresion");

                if (fechaNac != null) {
                    respuesta.setFechaimpresion(new Date(fechaNac.getTime()));
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

      public static LibroDTO findByCodigo(String id, DataSource dataSource) {

        LibroDTO respuesta = null;

        Connection conn = null;

        try {
            conn = dataSource.getConnection();

            PreparedStatement psta = conn.prepareStatement(LibroDTO.getSELECT_BY_CODIGO());
             psta.setString(1,id);


            ResultSet rset = psta.executeQuery();

            while (rset.next()) {

                respuesta = new LibroDTO();

                respuesta.setId_libro(rset.getInt("id"));
                respuesta.setCodigo(rset.getString("codigo"));
                respuesta.setNombre(rset.getString("nombre"));
                respuesta.setAutor(rset.getString("autor"));

                String correo = rset.getString("isbn");

                if (correo != null && correo.length() > 0) {
                    respuesta.setisbn(correo);
                }

                java.sql.Date fechaNac = rset.getDate("fechaimpresion");

                if (fechaNac != null) {
                    respuesta.setFechaimpresion(new Date(fechaNac.getTime()));
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

    public static List<LibroDTO> findAll(DataSource dataSource) {

        List<LibroDTO> respuesta = new ArrayList<LibroDTO>(0);

        Connection conn = null;

        try {
            //conn = dataSource.getConnection();
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            conn = DriverManager.getConnection(url, user, clave);
            
            String sql  =  LibroDTO.getSELECT_ALL();          
            //PreparedStatement psta = conn.prepareStatement(LibroDTO.getSELECT_ALL());
            PreparedStatement psta = conn.prepareStatement(SELECT_ALL);


            ResultSet rset = psta.executeQuery();

            while (rset.next()) {

                LibroDTO dto = new LibroDTO();

                dto.setId_libro(rset.getInt("id"));
                dto.setCodigo(rset.getString("codigo"));
                dto.setNombre(rset.getString("nombre"));
                dto.setAutor(rset.getString("autor"));
                dto.setisbn(rset.getString("isbn"));

                java.sql.Date fechaNac = rset.getDate("fechaimpresion");

                if (fechaNac != null) {
                    dto.setFechaimpresion(new Date(fechaNac.getTime()));
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

    public static LibroDTO fromMapToObject(Map<String, Object> params) {

        LibroDTO dto = new LibroDTO();

        for (String key : params.keySet()) {

            if (key.equals("id_libro")) {
                dto.setId_libro((Integer) params.get(key));
            }

            if (key.equals("codigo")) {
                dto.setCodigo((String) params.get(key));
            }

            if (key.equals("nombre")) {
                dto.setNombre((String) params.get(key));
            }

            if (key.equals("autor")) {
                dto.setAutor((String) params.get(key));
            }

            if (key.equals("isbn")) {
                dto.setisbn((String) params.get(key));
            }

            if (key.equals("fechaimpresion")) {
                Date fechanac = (Date) params.get(key);
                dto.setFechaimpresion(fechanac);
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

        String[] names = LibroDTO.getNamesForUpdate(params);

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

        psta.setInt(j, (Integer) params.get("id_libro"));

    }

    public static LibroDTO createLibro(Map<String, Object> params, DataSource dataSource) {
        LibroDTO r = null;
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            String sql = LibroDTO.getInsert(params);
            System.out.println(sql);
            PreparedStatement psta = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            setPreparedStatementParameters(params, psta);
            System.out.println("Ejecutando query: " + sql);
            int rrr = psta.executeUpdate();
            if (rrr == 1) {
                ResultSet rset = psta.getGeneratedKeys();

                while (rset.next()) {

                    r = fromMapToObject(params);
                    int id = rset.getInt(1);

                    r.setId_libro(id);
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

    public static LibroDTO editLibro(Map<String, Object> params, DataSource dataSource) {
        LibroDTO r = null;
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            String sql = LibroDTO.getUpdate(params);
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

    public static LibroDTO removeLibro(Map<String, Object> params, DataSource dataSource) {
        LibroDTO r = null;
        Connection conn = null;
        try {

            conn = dataSource.getConnection();
            PreparedStatement psta = conn.prepareStatement(LibroDTO.getDELETE());
            psta.setInt(1, (Integer) params.get("id_libro"));

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
