/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.univalle.parcial.repository;

import co.edu.univalle.parcial.models.Alergia;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Julian Puyo
 */
public class AlergiaDAO implements AlergiaDAOInterface {
    private Map<Integer, Alergia> mapaAlergias = new HashMap<>();    

    @Override
    public Map<Integer, Alergia> getAlergias() {
        return mapaAlergias;
    }

    @Override
    public Alergia getAlergia(int id) {
        return mapaAlergias.get(id);
    }

    @Override
    public boolean addAlergia(Alergia alergia) {
        mapaAlergias.put(alergia.getId(), alergia);
        return true;
    }

    @Override
    public boolean updateAlergia(int id, Alergia alergia) {
        mapaAlergias.put(id, alergia);
        return true;
    }

    @Override
    public boolean deleteAlergia(int id) {
        mapaAlergias.remove(id);
        return true;
    }
    
}
