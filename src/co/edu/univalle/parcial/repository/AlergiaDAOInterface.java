/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.univalle.parcial.repository;

import co.edu.univalle.parcial.models.Alergia;
import java.util.Map;

/**
 * 
 * @author Julian Puyo
 */
public interface AlergiaDAOInterface {
    public Map<Integer, Alergia> getAlergias();
    
    public Alergia getAlergia(int id);
    
    public boolean addAlergia(Alergia alergia);
    
    public boolean updateAlergia(int id, Alergia alergia);
    
    public boolean deleteAlergia(int id);
}
