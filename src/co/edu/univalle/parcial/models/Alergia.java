/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.univalle.parcial.models;

import java.io.Serializable;

/**
 * 
 * @author Julian Puyo
 */
public class Alergia implements Serializable{
    private int id;
    private String nombre;
    private static int consecutivo = 0;

    public Alergia(String nombre) {
        this.id = consecutivo++;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }
    
    @Override
    public String toString() {
        return nombre;
    }
}
