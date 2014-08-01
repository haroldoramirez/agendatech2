package models;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by haroldo on 01/08/14.
 */

@Entity
public class Usuario {

    @Id
    private String email;
    private String senha;

    @Deprecated
    public Usuario() {
        // TODO Auto-generated constructor stub
    }

    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
