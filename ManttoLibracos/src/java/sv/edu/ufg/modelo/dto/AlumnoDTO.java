/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ufg.modelo.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author javaee
 */
public class AlumnoDTO {
    //Constants

    private static final String SELECT_BY_NAME = "select * from alumnos where nombres like ?";
    private static final String SELECT_BY_ID = "select * from alumnos where id=?";
    private static final String SELECT_ALL_COUNT = "select count(*) from alumnos ";
    private static final String SELECT_ALL = "select * from alumnos ";
    private static final String INSERT = "insert into alumnos(carnet,nombres,apellidos,${0}) "
            + "values(?,?,?,${1})";
    private static final String DELETE = "delete from alumnos where id=?";
    private static final String UPDATE_BASE = "UPDATE alumnos SET ${0} WHERE id = ?";
    //Variable static
    public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    //Variables de instancia
    private Integer id;
    private String carnet;
    private String nombres;
    private String apellidos;
    private String correo;
    private Date fechanac;

    //construtores
    public AlumnoDTO() {
    }

    public AlumnoDTO(String carnet) {
        this.carnet = carnet;
    }

    public AlumnoDTO(String carnet, String nombres, String apellidos, String correo, Date fechanac) {
        this.carnet = carnet;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.fechanac = fechanac;
    }

    //Getters y Setters
    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Date getFechanac() {
        return fechanac;
    }

    public void setFechanac(Date fechanac) {
        this.fechanac = fechanac;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AlumnoDTO other = (AlumnoDTO) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    private String generarTexto() {
        StringBuilder sb = new StringBuilder();

        sb.append("\tid: ").append(id).append("\n");
        sb.append("\tcarnet: ").append(carnet).append("\n");
        sb.append("\tnombres:").append(nombres).append("\n");
        sb.append("\tapellidos:").append(apellidos).append("\n");
        sb.append("\tcorreo:").append(correo).append("\n");
        sb.append("\tFecha Nacimiento:").append(sdf.format(fechanac)).append("\n");
        return sb.toString();
    }

    private String generarTextoWeb() {
        StringBuilder sb = new StringBuilder();

        sb.append("<ul>");
        sb.append("<li>id: ").append(id).append("</li>");
        sb.append("<li>carnet: ").append(carnet).append("</li>");
        sb.append("<li>nombres:").append(nombres).append("</li>");
        sb.append("<li>apellidos:").append(apellidos).append("</li>");
        sb.append("<li>correo:").append(correo).append("</li>");
        sb.append("<li>Fecha Nacimiento:").append(sdf.format(fechanac)).append("</li>");
        sb.append("</ul>");
        return sb.toString();
    }

    @Override
    public String toString() {
        //return "Alumno{" + "carnet=" + carnet + ", nombres=" + nombres + ", apellidos=" + apellidos + ", correo=" + correo + ", fechanac=" + fechanac + '}';
        return generarTexto();
    }

    public String toWebString() {
        return generarTextoWeb();
    }

    public void imprimirInfoAlumno() {

        System.out.println(generarTexto() + " \n VERSION MODIFICADA");
    }

    public static String getInsert(StringBuilder cols, StringBuilder marks) {

        if (cols.length() == 0 && marks.length() == 0) {

            return INSERT.replace(",${0}", "").replace(",${1}", "");
        } else {

            return INSERT.replace("${0}", cols.toString()).replace("${1}", marks.toString());
        }

    }

    public static String getUpdate(StringBuilder colsAndMarks) {

        if (colsAndMarks.length() == 0) {

            return UPDATE_BASE;
        } else {

            return UPDATE_BASE.replace("${0}", colsAndMarks.toString());
        }

    }

    public static String getSELECT_ALL() {
        return SELECT_ALL;
    }

    public static String getSELECT_ALL_COUNT() {
        return SELECT_ALL_COUNT;
    }

    public static String getDELETE() {
        return DELETE;
    }

    public static String getSELECT_BY_ID() {
        return SELECT_BY_ID;
    }

    public static String getSELECT_BY_NAME() {
        return SELECT_BY_NAME;
    }

    public static String[] getNamesForUpdate(Map<String, Object> params) {
        String[] names = new String[params.keySet().size() - 1];
        int i = 0;
        for (String key : params.keySet()) {

            if (!key.endsWith("id")) {

                names[i] = key;
                i++;
            }
        }

        return names;
    }

    public static String getInsert(Map<String, Object> params) {
        StringBuilder cols = new StringBuilder("insert into alumnos(");
        StringBuilder vals = new StringBuilder("values(");

        String[] names = new String[params.keySet().size()];


        int i = 0;
        for (String key : params.keySet()) {
            names[i] = key;
            i++;
        }

        for (int j = 0; j < names.length; j++) {
            if (j == names.length - 1) {
                cols.append(names[j]).append(")");
                vals.append("?").append(")");
            } else {
                cols.append(names[j]).append(",");
                vals.append("?").append(",");
            }
        }

        cols.append(" ").append(vals);

        return cols.toString();
    }

    public static String getUpdate(Map<String, Object> params) {
        StringBuilder cols = new StringBuilder("UPDATE alumnos SET ");
        String whereClause = "WHERE id = ?";

        String[] names = new String[params.keySet().size() - 1];


        int i = 0;
        for (String key : params.keySet()) {

            if (!key.endsWith("id")) {

                names[i] = key;
                i++;
            }
        }

        for (int j = 0; j < names.length; j++) {
            if (j == names.length - 1) {
                cols.append(names[j]).append("=").append("?");

            } else {
                cols.append(names[j]).append("=").append("?,");

            }
        }

        cols.append(" ").append(whereClause);

        return cols.toString();
    }
}
