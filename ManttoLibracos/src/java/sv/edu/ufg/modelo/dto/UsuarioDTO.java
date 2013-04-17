/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ufg.modelo.dto;

import java.io.Serializable;

/**
 *
 * @author xtecuan
 */
public class UsuarioDTO implements Serializable {

    public static final String QUERY_LOGIN = "select * from usuario where login=? and password=?";
    public static final String QUERY_ALL = "select * from usuario";
    private Integer id;
    private String login;
    private String password;
    private String queryLogin;
    private String queryAll;

    public UsuarioDTO() {
        this.queryAll = QUERY_ALL;
        this.queryLogin = QUERY_LOGIN;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final UsuarioDTO other = (UsuarioDTO) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" + "id=" + id + ", login=" + login + ", password=" + password + '}';
    }

    public String getQueryLogin() {

        return queryLogin;
    }

    public String getQueryAll() {
        return queryAll;
    }
}
