/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package co.edu.univalle.parcial;

import co.edu.univalle.parcial.controllers.VistaController;
import co.edu.univalle.parcial.views.Vista;

/**
 * 
 * @author Julian Puyo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Vista vista = new Vista();
        vista.setVisible(true);
        VistaController vistaController = new VistaController(vista);
    }
    
}
