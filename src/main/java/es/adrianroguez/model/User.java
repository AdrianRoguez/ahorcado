package es.adrianroguez.model;

import java.util.Objects;

public class User {
    private String usuario;
    private String email;
    private String contrasenia;
    private String nivel;

    public User() {
    }

    public User(String usuario, String email, String contrasenia, String nivel) {
        this.usuario = usuario;
        this.email = email;
        this.contrasenia = contrasenia;
        this.nivel = nivel;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenia() {
        return this.contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNivel() {
        return this.nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public User usuario(String usuario) {
        setUsuario(usuario);
        return this;
    }

    public User email(String email) {
        setEmail(email);
        return this;
    }

    public User contrasenia(String contrasenia) {
        setContrasenia(contrasenia);
        return this;
    }

    public User nivel(String nivel) {
        setNivel(nivel);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "{" +
                " usuario='" + getUsuario() + "'" +
                ", email='" + getEmail() + "'" +
                ", contrasenia='" + getContrasenia() + "'" +
                ", nivel='" + getNivel() + "'" +
                "}";
    }
}
