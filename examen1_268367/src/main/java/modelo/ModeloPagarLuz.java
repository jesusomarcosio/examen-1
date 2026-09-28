/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import observer.ISubscriptor;

/**
 *
 * @author Omar
 */
public class ModeloPagarLuz {

    // Lista de subscriptores (Observadores)
    private List<ISubscriptor> subscriptores;

    // Datos del estado actual del modelo
    private List<Cliente> listaClientes; // Simulación de base de datos
    private List<Cliente> clientesCoincidentes;
    private Cliente clienteSeleccionado;
    private Recibo reciboActual;

    public ModeloPagarLuz() {
        this.subscriptores = new ArrayList<>();
        this.clientesCoincidentes = new ArrayList<>();
        this.listaClientes = new ArrayList<>();

        // Cargar algunos clientes ficticios de prueba
        inicializarDatosPrueba();
    }

    private void inicializarDatosPrueba() {
        listaClientes.add(new Cliente("12345", "Luis Andres", new Consumo("Ene-Feb 2026", 150, 450.0)));
        listaClientes.add(new Cliente("12399", "Jose Jose", new Consumo("Ene-Feb 2026", 200, 620.50)));
        listaClientes.add(new Cliente("98765", "Jose Miguel", new Consumo("Ene-Feb 2026", 90, 280.0)));
        listaClientes.add(new Cliente("45672", "Jesus Omar", new Consumo("Ene-Feb 2026", 300, 300.0)));
        listaClientes.add(new Cliente("84650", "Jesus Daniel", new Consumo("Ene-Feb 2026", 90, 280.0)));
        listaClientes.add(new Cliente("09354", "Miguel Medina", new Consumo("Ene-Feb 2026", 90, 280.0)));
        listaClientes.add(new Cliente("23451", "Juan Jose", new Consumo("Ene-Feb 2026", 90, 280.0)));
        listaClientes.add(new Cliente("38564", "Julio Urias", new Consumo("Ene-Feb 2026", 90, 280.0)));

    }

    public void agregarSubscriptor(ISubscriptor subscriptor) {
        subscriptores.add(subscriptor);
    }

    public void notificarSubscriptores() {
        for (ISubscriptor sub : subscriptores) {
            sub.update(this);
        }
    }

    public void buscarClientes(String numServicio) {
        clientesCoincidentes.clear();
        if (numServicio != null && !numServicio.trim().isEmpty()) {
            for (Cliente c : listaClientes) {
                if (c.getNumeroServicio().contains(numServicio)) {
                    clientesCoincidentes.add(c);
                }
            }
        }
        notificarSubscriptores();
    }

    public void seleccionarCliente(Cliente cliente) {
        this.clienteSeleccionado = cliente;
        notificarSubscriptores();
    }

    public void pagarServicio(String tarjeta) {
        if (clienteSeleccionado != null) {
            double total = clienteSeleccionado.getConsumo().getTotalAPagar();
            String folio = "REC-" + System.currentTimeMillis() % 100000;

            this.reciboActual = new Recibo(total, "PAGADO", folio, new Date(), tarjeta);

            notificarSubscriptores();
        }
    }

    public List<Cliente> getClientesCoincidentes() {
        return clientesCoincidentes;
    }

    public Cliente getClienteSeleccionado() {
        return clienteSeleccionado;
    }

    public Recibo getReciboActual() {
        return reciboActual;
    }
}
