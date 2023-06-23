/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.univalle.parcial.repository;

import co.edu.univalle.parcial.models.Paciente;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Julian Puyo
 */
public class PacienteDAO implements PacienteDAOInterface{
   private  Map<Integer, Paciente> mapaPacientes= new HashMap<>();
    
    @Override
    public Map<Integer, Paciente> getPacientes() {
        return mapaPacientes;
    }

    @Override
    public Paciente getPaciente(Integer identificacion) {
        return mapaPacientes.get(identificacion);
    }

    @Override
    public boolean addPaciente(Paciente paciente) {
        mapaPacientes.put(paciente.getIdentificacion(), paciente);
        return true;
    }

    @Override
    public boolean updatePaciente(Integer identificacion, Paciente paciente) {
        mapaPacientes.put(identificacion, paciente);
        return true;
    }

    @Override
    public boolean deletePaciente(Integer identificacion) {
        mapaPacientes.remove(identificacion);
        return true;
    }
    
}
