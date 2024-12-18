package com.example.ausencias;

public class Modulo {

    private int imagen;
    private String nombre;
    private String grupo;

    public Modulo(int imagen, String nombre, String grupo) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.grupo = grupo;
    }

    public int getImagen() {
        return imagen;
    }


    public String getNombre() {
        return nombre;
    }

    public String getGrupo() {
        return grupo;
    }
}
