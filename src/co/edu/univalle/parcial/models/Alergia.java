/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.univalle.parcial.models;

/**
 * 
 * @author Julian Puyo
 */
public class Alergia {
    private int id;
    private String nombre;
    private int consecutivo = 0;

    public Alergia(String nombre) {
        this.nombre = nombre;
        id = consecutivo++;
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
}
