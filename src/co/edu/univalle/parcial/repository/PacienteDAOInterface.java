/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.univalle.parcial.repository;

import co.edu.univalle.parcial.models.Paciente;
import java.util.Map;

/**
 * 
 * @author Julian Puyo
 */
public interface PacienteDAOInterface {
    public Map<Integer, Paciente> getPacientes();
    
    public Paciente getPaciente(Integer identificacion);
    
    public boolean addPaciente(Paciente paciente);
    
    public boolean updatePaciente(Integer identificacion, Paciente paciente);
    
    public boolean deletePaciente(Integer identificacion);
}
