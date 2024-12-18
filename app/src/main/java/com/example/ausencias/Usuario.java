package com.example.ausencias;

import java.util.List;

public class Usuario {

    private String nombreUsuario;
    private List<String> modulos;

    public Usuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Usuario(String nombreUsuario, String contrasena) {
        this.nombreUsuario = nombreUsuario;
    }

    public Usuario(String nombreUsuario, List<String> modulos) {
        this.nombreUsuario = nombreUsuario;
        this.modulos = modulos;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public List<String> getModulos() {
        return modulos;
    }

    public void setModulos(List<String> modulos) {
        this.modulos = modulos;
    }
}
