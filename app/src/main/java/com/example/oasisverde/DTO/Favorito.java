package com.example.oasisverde.DTO;

import java.io.Serializable;
import java.util.ArrayList;

public class Favorito implements Serializable {

    private int id;
    private String nombre;
    private String nombreTipico;
    private String descripcion;

    public Favorito(){}

    public Favorito(String nombre, String nombreTipico, String descripcion){
        this.nombre=nombre;
        this.nombreTipico=nombreTipico;
        this.descripcion = descripcion;
    }
    public Favorito(int id, String nombre, String nombreTipico, String descripcion){
        this.id=id;
        this.nombre=nombre;
        this.nombreTipico=nombreTipico;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreTipico() {
        return nombreTipico;
    }

    public void setNombreTipico(String nombreTipico) {
        this.nombreTipico = nombreTipico;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    @Override
    public  String toString(){
        return (this.getNombre() +" || "+this.getNombreTipico()+" || "+this.getDescripcion());
    }


}
