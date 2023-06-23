/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.univalle.parcial.repository;

import co.edu.univalle.parcial.models.Paciente;
import java.math.BigInteger;
import java.util.Map;

/**
 * 
 * @author Julian Puyo
 */
public interface PacienteDAOInterface {
    public Map<String, Paciente> getPacientes();
    
    public Paciente getPaciente(String identificacion);
    
    public boolean addPaciente(Paciente paciente);
    
    public boolean updatePaciente(String identificacion, Paciente paciente);
    
    public boolean deletePaciente(String identificacion);
}
