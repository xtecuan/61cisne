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
public class LibroDTO {
    //Constants

    private static final String SELECT_BY_NAME = "select * from Libros where nombre like ?";
    private static final String SELECT_BY_ID = "select * from Libros where id=?";
    private static final String SELECT_ALL_COUNT = "select count(*) from Libros ";
    private static final String SELECT_ALL = "select * from Libros ";
    private static final String INSERT = "insert into Libros(codigo,nombre,autor,ISBN,fechaimpresion,estado) "
            + "values(?,?,?,?,?,?)";
    private static final String DELETE = "delete from Libros where id=?";
    private static final String UPDATE_BASE = "UPDATE libros SET ${0} WHERE id = ?";
    //Variable static
    public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    //Variables de instancia
    private Integer id_libro;
    private String codigo;
    private String nombre;
    private String autor;
    private String isbn;
    private Date fechaimpresion;
    private String estado;

    public LibroDTO() {
    }

    public LibroDTO(Integer id_libro) {
        this.id_libro = id_libro;
    }

    public LibroDTO(String codigo) {
        this.codigo = codigo;
    }

    public LibroDTO(String codigo, String nombre, String autor, String ISBN, Date fechaimpresion, String estado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.autor = autor;
        this.isbn = ISBN;
        this.fechaimpresion = fechaimpresion;
        this.estado = estado;
    }

    public static SimpleDateFormat getSdf() {
        return sdf;
    }

    public static void setSdf(SimpleDateFormat sdf) {
        LibroDTO.sdf = sdf;
    }

    public Integer getId_libro() {
        return id_libro;
    }

    public void setId_libro(Integer id_libro) {
        this.id_libro = id_libro;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getisbn() {
        return isbn;
    }

    public void setisbn(String ISBN) {
        this.isbn = ISBN;
    }

    public Date getFechaimpresion() {
        return fechaimpresion;
    }

    public void setFechaimpresion(Date fechaimpresion) {
        this.fechaimpresion = fechaimpresion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.id_libro != null ? this.id_libro.hashCode() : 0);
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
        final LibroDTO other = (LibroDTO) obj;
        if (this.id_libro != other.id_libro && (this.id_libro == null || !this.id_libro.equals(other.id_libro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LibroDTO{" + "id_libro=" + id_libro + ", codigo=" + codigo + ", nombre=" + nombre + ", autor=" + autor + ", isbn=" + isbn + ", fechaimpresion=" + fechaimpresion + ", estado=" + estado + '}';
    }

    public static String getSELECT_BY_NAME() {
        return SELECT_BY_NAME;
    }

    public static String getSELECT_BY_ID() {
        return SELECT_BY_ID;
    }

    public static String getSELECT_ALL_COUNT() {
        return SELECT_ALL_COUNT;
    }

    public static String getSELECT_ALL() {
        return SELECT_ALL;
    }

    public static String getUPDATE_BASE() {
        return UPDATE_BASE;
    }
    
      public static String getInsert(Map<String, Object> params) {
        StringBuilder cols = new StringBuilder("insert into Libros(");
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
        StringBuilder cols = new StringBuilder("UPDATE Libros SET ");
        String whereClause = "WHERE id = ?";

        String[] names = new String[params.keySet().size() - 1];


        int i = 0;
        for (String key : params.keySet()) {

            if (!key.endsWith("id_libro")) {

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

public static String getDELETE() {
        return DELETE;
    }
  public static String[] getNamesForUpdate(Map<String, Object> params) {
        String[] names = new String[params.keySet().size() - 1];
        int i = 0;
        for (String key : params.keySet()) {

            if (!key.endsWith("id_libro")) {

                names[i] = key;
                i++;
            }
        }

        return names;
    }    
}
