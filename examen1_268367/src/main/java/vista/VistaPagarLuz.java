/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.Controlador;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import modelo.Cliente;
import modelo.ModeloPagarLuz;
import modelo.Recibo;
import observer.ISubscriptor;

/**
 *
 * @author Omar
 */
public class VistaPagarLuz extends JFrame implements ISubscriptor{
    
    private Controlador controlador;

    private JTextField txtNumServicio;
    private DefaultListModel<Cliente> listModelClientes;
    private JList<Cliente> listClientes;
    private JTextArea txtConsumoYRecibo;
    private JTextField txtTarjeta;
    private JButton btnPagar;

    public VistaPagarLuz() {
        initUI();
    }

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    private void initUI() {
        setTitle("Pagar servicio de luz");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));

        JPanel panelSuperior = new JPanel(new GridLayout(1, 2, 15, 15));

        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBusqueda.add(new JLabel("Número de servicio:"));
        txtNumServicio = new JTextField(12);
        panelBusqueda.add(txtNumServicio);

        JPanel panelClientes = new JPanel(new BorderLayout());
        panelClientes.add(new JLabel("Clientes:"), BorderLayout.NORTH);
        listModelClientes = new DefaultListModel<>();
        listClientes = new JList<>(listModelClientes);
        panelClientes.add(new JScrollPane(listClientes), BorderLayout.CENTER);

        panelSuperior.add(panelBusqueda);
        panelSuperior.add(panelClientes);

        txtConsumoYRecibo = new JTextArea(10, 40);
        txtConsumoYRecibo.setEditable(false);
        txtConsumoYRecibo.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JScrollPane scrollCentral = new JScrollPane(txtConsumoYRecibo);

        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelInferior.add(new JLabel("Número de tarjeta:"));
        txtTarjeta = new JTextField(16);
        panelInferior.add(txtTarjeta);

        btnPagar = new JButton("Pagar");
        btnPagar.setBackground(new Color(76, 175, 80)); // Color verde tipo Figma
        btnPagar.setForeground(Color.WHITE);
        btnPagar.setFocusPainted(false);
        panelInferior.add(btnPagar);
        
        add(panelSuperior, BorderLayout.NORTH);
        add(scrollCentral, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

       

        // buscar
        txtNumServicio.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { buscar(); }
            public void removeUpdate(DocumentEvent e) { buscar(); }
            public void changedUpdate(DocumentEvent e) { buscar(); }

            private void buscar() {
                if (controlador != null) {
                    controlador.buscarClientes(txtNumServicio.getText());
                }
            }
        });

        // seleccionar cliente
        listClientes.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && controlador != null) {
                Cliente seleccionado = listClientes.getSelectedValue();
                if (seleccionado != null) {
                    controlador.seleccionarCliente(seleccionado);
                }
            }
        });

        // boton pagar
        btnPagar.addActionListener(e -> {
            if (controlador != null) {
                String tarjeta = txtTarjeta.getText().trim();
                if (tarjeta.length() == 16) {
                    controlador.pagarServicio(tarjeta);
                } else {
                    JOptionPane.showMessageDialog(this, 
                        "Ingrese un número de tarjeta válido (16 dígitos).", 
                        "Validación de Tarjeta", 
                        JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    // Observer
    @Override
    public void update(ModeloPagarLuz modelo) {
        listModelClientes.clear();
        for (Cliente c : modelo.getClientesCoincidentes()) {
            listModelClientes.addElement(c);
        }

        Recibo recibo = modelo.getReciboActual();
        if (recibo != null) {
            mostrarDatosRecibo(recibo);
            return;
        }

        Cliente seleccionado = modelo.getClienteSeleccionado();
        if (seleccionado != null) {
            mostrarDatosConsumo(seleccionado);
        } else {
            txtConsumoYRecibo.setText("");
        }
    }

    private void mostrarDatosConsumo(Cliente cliente) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== DATOS DE CONSUMO ===\n\n");
        sb.append("Titular: ").append(cliente.getNombre()).append("\n");
        sb.append("No. Servicio: ").append(cliente.getNumeroServicio()).append("\n");
        sb.append("Periodo: ").append(cliente.getConsumo().getPeriodo()).append("\n");
        sb.append("Consumo Registrado: ").append(cliente.getConsumo().getKwhConsumidos()).append(" kWh\n");
        sb.append("Total a Pagar: $").append(String.format("%.2f", cliente.getConsumo().getTotalAPagar())).append("\n");
        txtConsumoYRecibo.setText(sb.toString());
    }

    private void mostrarDatosRecibo(Recibo recibo) {
        StringBuilder sb = new StringBuilder();
        sb.append("===============================================\n");
        sb.append("               RECIBO DE PAGO                  \n");
        sb.append("===============================================\n\n");
        sb.append("Folio de Transacción: ").append(recibo.getFolio()).append("\n");
        sb.append("Fecha de Pago: ").append(recibo.getFecha()).append("\n");
        sb.append("Estatus: ").append(recibo.getEstatusPago()).append("\n");
        sb.append("Monto Cobrado: $").append(String.format("%.2f", recibo.getMontoPagado())).append("\n");
        sb.append("Tarjeta Usada: **** **** **** ").append(recibo.getTarjeta().substring(12)).append("\n\n");
        sb.append("===============================================\n");
        sb.append("         ¡Gracias por realizar su pago!        \n");
        txtConsumoYRecibo.setText(sb.toString());
    }
    
}
