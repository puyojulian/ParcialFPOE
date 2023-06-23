/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.univalle.parcial.models;

import java.math.BigInteger;
import java.util.List;
import java.util.ArrayList;

/**
 * 
 * @author Julian Puyo
 */
public class Paciente {
    private String identificacion;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String direccion;
    private List<Alergia> alergias;

    public Paciente(String identificacion, String nombres, String apellidos, String telefono, String direccion) {
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.direccion = direccion;
        this.alergias = new ArrayList();
        System.out.println("Paciente Instanciado");
    }

//    public Paciente(Integer identificacion) {
//        this.identificacion = identificacion;
//        this.nombres = "";
//        this.apellidos = "";
//        this.telefono = "";
//        this.direccion = "";
//        this.alergias = new ArrayList();
//    }
    
    public void addAlergia(Alergia alergia) {
        alergias.add(alergia);
    }

    public List<Alergia> getAlergias() {
        return alergias;
    }

    public void setAlergias(List alergias) {
        this.alergias = alergias;
    }
    
    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
