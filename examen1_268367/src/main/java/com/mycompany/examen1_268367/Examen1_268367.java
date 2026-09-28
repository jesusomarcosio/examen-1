/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.examen1_268367;

import controlador.Controlador;
import javax.swing.SwingUtilities;
import modelo.ModeloPagarLuz;
import vista.VistaPagarLuz;

/**
 *
 * @author Omar
 */
public class Examen1_268367 {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ModeloPagarLuz modelo = new ModeloPagarLuz();

            Controlador controlador = new Controlador(modelo);

            VistaPagarLuz vista = new VistaPagarLuz();
            
            vista.setControlador(controlador);

            modelo.agregarSubscriptor(vista);

            vista.setVisible(true);
        });
    }
}
