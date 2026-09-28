/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Cliente;
import modelo.ModeloPagarLuz;

/**
 *
 * @author Omar
 */
public class Controlador {
    
    private ModeloPagarLuz modelo;

    public Controlador(ModeloPagarLuz modelo) {
        this.modelo = modelo;
    }

    public void buscarClientes(String numServicio) {
        modelo.buscarClientes(numServicio);
    }

    public void seleccionarCliente(Cliente cliente) {
        modelo.seleccionarCliente(cliente);
    }

    public void pagarServicio(String tarjeta) {
        modelo.pagarServicio(tarjeta);
    }
    
}
