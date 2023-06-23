/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.univalle.parcial.controllers;

import co.edu.univalle.parcial.models.Alergia;
import co.edu.univalle.parcial.models.Paciente;
import co.edu.univalle.parcial.repository.AlergiaDAO;
import co.edu.univalle.parcial.repository.PacienteDAO;
import co.edu.univalle.parcial.util.SerializationUtil;
import co.edu.univalle.parcial.views.Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.ListModel;

/**
 * 
 * @author Julian Puyo
 */
public class VistaController {
    private Vista vista;
    private AlergiaDAO daoAlergia;
    private PacienteDAO daoPaciente;
    private DefaultListModel listModel;
    private Map mapaPacientes;
    private Map mapaAlergias;
    private final String filePathPacientes = "pacientes.bin";
    private final String filePathAlergias = "alergias.bin";
    
    public VistaController(Vista vista) {
        this.vista = vista;
        this.daoAlergia = new AlergiaDAO();
        this.daoPaciente = new PacienteDAO();
        listModel = new DefaultListModel();
        instanciarAlergias();
        popularJComboBox();        
        
        if(SerializationUtil.isSerializedObjectExists(filePathPacientes)) {
            cargarPacientes();
        }
        if(SerializationUtil.isSerializedObjectExists(filePathAlergias)) {
            cargarAlergias();
        }
        
        mapaPacientes = daoPaciente.getPacientes();
        mapaAlergias = daoAlergia.getAlergias();
                
        ActionsHandler manejadorDeActionEvents = new ActionsHandler();
        FocusHandler manejadorDeFocusEvents = new FocusHandler();
        KeyHandler manejadorDeKeyEvents = new KeyHandler();
        
        vista.getTxtIdentificacion().addFocusListener(manejadorDeFocusEvents);
        vista.getTxtIdentificacion().addKeyListener(manejadorDeKeyEvents);
        vista.getBtnAgregar().addActionListener(manejadorDeActionEvents);
        vista.getBtnGuardar().addActionListener(manejadorDeActionEvents);
        vista.getBtnCancelar().addActionListener(manejadorDeActionEvents);

        vista.addWindowListener(new WindowAdapter() {
             @Override
            public void windowClosing(WindowEvent evt) {
                cerrarJuego();
            }
        });
        
    }
    
    private void cerrarJuego(){
        int respuesta;

        respuesta = JOptionPane.showConfirmDialog(
                null,"¿Esta seguro que no desea terminar?", "Advertencia",
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.WARNING_MESSAGE);

        if(respuesta == JOptionPane.YES_OPTION){
            guardarPacientes();
            guardarAlergias();
            System.exit(0);
        }
    }

    public void guardarPacientes() {
        SerializationUtil.serializeObject(daoPaciente.getPacientes(), filePathPacientes);
    }

    public void guardarAlergias() {
        SerializationUtil.serializeObject(daoAlergia.getAlergias(), filePathAlergias);
    }

    public void cargarPacientes() {
        daoPaciente.setPacientes((Map) SerializationUtil.deserializeObject(filePathPacientes));
    }
    
    public void cargarAlergias() {
        daoAlergia.setAlergias((Map) SerializationUtil.deserializeObject(filePathAlergias));
    }
    
    private void instanciarAlergias() {
        daoAlergia.addAlergia(new Alergia("Al polen."));
        daoAlergia.addAlergia(new Alergia("A los ácaros."));
        daoAlergia.addAlergia(new Alergia("A los huevos."));
        daoAlergia.addAlergia(new Alergia("A los marizcos."));
        daoAlergia.addAlergia(new Alergia("Al látex."));
        daoAlergia.addAlergia(new Alergia("A los animales."));
        daoAlergia.addAlergia(new Alergia("Al polvo."));
        daoAlergia.addAlergia(new Alergia("A la penicilina."));
        daoAlergia.addAlergia(new Alergia("A la aspirina."));
        daoAlergia.addAlergia(new Alergia("A la picadura de abeja."));
    }
    
    private void popularJComboBox() {
        mapaAlergias = daoAlergia.getAlergias();
        Set<Map.Entry<Integer, Alergia>> entrySetMapa = mapaAlergias.entrySet();
        
        for (Map.Entry<Integer, Alergia> entry : entrySetMapa){
            System.out.println("111");
            vista.getjComboBox1().addItem(entry.getValue().getNombre());
        }        
    }
    
    private void popularJList(String identificacion) {
        List listaAlergias = daoPaciente.getPaciente(identificacion).getAlergias();
        listModel.removeAllElements();
        
        Iterator iterador = listaAlergias.iterator();
        while(iterador.hasNext()) {
            listModel.addElement((String) iterador.next());
            System.out.println("Alergia");
        }
        
        vista.getjList1().setModel(listModel);
    }
    
    private String verificarIndentificacion(String identificacion) {
        mapaPacientes = daoPaciente.getPacientes();
        Set<Map.Entry<String, Paciente>> entrySetMapa = mapaPacientes.entrySet();
        
        for (Map.Entry<String, Paciente> entry : entrySetMapa){
            if(entry.getKey().equals(identificacion)) {
                System.out.println("Paciente encontrado");
                System.out.println(identificacion);
                return identificacion;
            }
        }
        System.out.println("Paciente no encontrado");
        return "";
    }
    
    private void estadoInicialTxt() {
        vista.getTxtIdentificacion().setText("");
        vista.getTxtApellidos().setText("");
        vista.getTxtNombres().setText("");
        vista.getTxtTelefono().setText("");
        vista.getTxtDireccion().setText("");
        listModel.removeAllElements();
        vista.getjList1().setModel(listModel);
        deshabilitarCampos();
    }
    
    private void guardarAlergias(String identificacion) {
        ListModel<String> listModel = vista.getjList1().getModel();
        
        List<String> lista = new ArrayList<>();
        for (int i = 0; i < listModel.getSize(); i++) {
            String element = listModel.getElementAt(i);
            lista.add(element);
            System.out.println("Guardando Alergia...");
        }
        
        daoPaciente.getPaciente(identificacion).setAlergias(lista);
    }
    
    private boolean alergiaYaAgregada() {
        if(!listModel.isEmpty()) {
            for (int i = 0; i < listModel.getSize(); i++) {
                if(listModel.getElementAt(i).equals(vista.getjComboBox1().getSelectedItem())) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean esNumerico(String cadena) {
        return cadena.matches("\\d+");
    }
    
    
    private void habilitarCampos() {
        vista.getTxtApellidos().setEnabled(true);
        vista.getTxtNombres().setEnabled(true);
        vista.getTxtTelefono().setEnabled(true);
        vista.getTxtDireccion().setEnabled(true);
        vista.getjComboBox1().setEnabled(true);
        vista.getBtnAgregar().setEnabled(true);
        vista.getBtnCancelar().setEnabled(true);
        vista.getBtnGuardar().setEnabled(true);
    }
    
    private void deshabilitarCampos() {
        vista.getTxtApellidos().setEnabled(false);
        vista.getTxtNombres().setEnabled(false);
        vista.getTxtTelefono().setEnabled(false);
        vista.getTxtDireccion().setEnabled(false);
        vista.getjComboBox1().setEnabled(false);
        vista.getBtnAgregar().setEnabled(false);
        vista.getBtnCancelar().setEnabled(false);
        vista.getBtnGuardar().setEnabled(false);
    }
    
    public void mensajeTemporal(String mensaje, String titulo, int milisegundos) {
        JOptionPane msg = new JOptionPane(mensaje, JOptionPane.INFORMATION_MESSAGE);
        final JDialog dlg = msg.createDialog(titulo);
        dlg.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        new Thread(new Runnable() {
          @Override
          public void run() {
            try {
              Thread.sleep(milisegundos);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
            dlg.setVisible(false);
          }
        }).start();
        dlg.setVisible(true);
    }
    
    class ActionsHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == vista.getBtnAgregar()) {
                if(!alergiaYaAgregada()) {
                    listModel.addElement(vista.getjComboBox1().getSelectedItem());
                    vista.getjList1().setModel(listModel);
                }
                else {
                    mensajeTemporal("Esa alergia ya ha sido registrada.", "Error", 1100);
                }
            }
            else if(e.getSource() == vista.getBtnGuardar()) {
                if(!vista.getTxtIdentificacion().getText().isEmpty() && esNumerico(vista.getTxtIdentificacion().getText())) {
                    String busquedaID = verificarIndentificacion(String.valueOf(vista.getTxtIdentificacion().getText()));
                    if(!busquedaID.isEmpty()) {  
                        if(esNumerico(vista.getTxtTelefono().getText())) {
                            daoPaciente.getPaciente(busquedaID).setApellidos(vista.getTxtApellidos().getText());
                            daoPaciente.getPaciente(busquedaID).setNombres(vista.getTxtNombres().getText());
                            daoPaciente.getPaciente(busquedaID).setTelefono(vista.getTxtTelefono().getText());
                            daoPaciente.getPaciente(busquedaID).setDireccion(vista.getTxtDireccion().getText());
                            guardarAlergias(String.valueOf(vista.getTxtIdentificacion().getText()));
                            estadoInicialTxt();
                        }
                        else {
                            mensajeTemporal("El número de teléfono debe ser numérico.", "Error", 1100);
                        }
                    }
                    else {
                        if(esNumerico(vista.getTxtTelefono().getText())) {
                            daoPaciente.addPaciente(new Paciente(String.valueOf(vista.getTxtIdentificacion().getText()), 
                                vista.getTxtNombres().getText(), 
                                vista.getTxtApellidos().getText(),
                                vista.getTxtTelefono().getText(),
                                vista.getTxtDireccion().getText()));
                            guardarAlergias(String.valueOf(vista.getTxtIdentificacion().getText()));
                            estadoInicialTxt();
                        }
                        else {
                            mensajeTemporal("El número de teléfono debe ser numérico.", "Error", 1100);
                        }
                    }
                }
            }
            else if(e.getSource() == vista.getBtnCancelar()) {
                estadoInicialTxt();
            }
        }
    }
    
    class FocusHandler implements FocusListener {

        @Override
        public void focusGained(FocusEvent e) {
            if(e.getSource() == vista.getTxtIdentificacion()) {
                
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            if(e.getSource() == vista.getTxtIdentificacion()) {
                if(esNumerico(vista.getTxtIdentificacion().getText())) {
                    if(!vista.getTxtIdentificacion().getText().isEmpty()) {
                        habilitarCampos();
                        String busquedaID = verificarIndentificacion(String.valueOf(vista.getTxtIdentificacion().getText()));
                        if(!busquedaID.isEmpty()) {
                            vista.getBtnGuardar().setText("Actualizar");
                            vista.getTxtApellidos().setText(daoPaciente.getPaciente(busquedaID).getApellidos());
                            vista.getTxtNombres().setText(daoPaciente.getPaciente(busquedaID).getNombres());
                            vista.getTxtTelefono().setText(daoPaciente.getPaciente(busquedaID).getTelefono());
                            vista.getTxtDireccion().setText(daoPaciente.getPaciente(busquedaID).getDireccion());
                            popularJList(busquedaID);
                        }
                        else {
                            vista.getBtnGuardar().setText("Guardar");
                        }
                    }
                    else {
                        deshabilitarCampos();
                    }
                }
                else {
                    if(!vista.getTxtIdentificacion().getText().isEmpty()) {
                        mensajeTemporal("El número de identificación debe ser numérico.", "Error", 1100);
                        vista.getTxtIdentificacion().setText("");
                        estadoInicialTxt();
                    }
                }
            }
        }
    }
    
    class KeyHandler implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                vista.getJpDatosPersonales().requestFocus();
                if(!vista.getTxtIdentificacion().getText().isEmpty() && esNumerico(vista.getTxtIdentificacion().getText())) {
                    String busquedaID = verificarIndentificacion(String.valueOf(vista.getTxtIdentificacion().getText()));
                    if(!busquedaID.isEmpty()) {
                        vista.getTxtApellidos().requestFocus();
                    }
                }    
            }
            else if(e.getKeyCode() == KeyEvent.VK_TAB) {
                vista.getJpDatosPersonales().requestFocus();
                if(!vista.getTxtIdentificacion().getText().isEmpty() && esNumerico(vista.getTxtIdentificacion().getText())) {
                    String busquedaID = verificarIndentificacion(String.valueOf(vista.getTxtIdentificacion().getText()));
                    if(!busquedaID.isEmpty()) {
                        vista.getTxtApellidos().requestFocus();
                    }
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
        
    }
}
