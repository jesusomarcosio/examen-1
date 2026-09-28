/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Omar
 */
public class Consumo {
    private String periodo;
    private int kwhConsumidos;
    private double totalAPagar;

    public Consumo() {
    }

    public Consumo(String periodo, int kwhConsumidos, double totalAPagar) {
        this.periodo = periodo;
        this.kwhConsumidos = kwhConsumidos;
        this.totalAPagar = totalAPagar;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public int getKwhConsumidos() {
        return kwhConsumidos;
    }

    public void setKwhConsumidos(int kwhConsumidos) {
        this.kwhConsumidos = kwhConsumidos;
    }

    public double getTotalAPagar() {
        return totalAPagar;
    }

    public void setTotalAPagar(double totalAPagar) {
        this.totalAPagar = totalAPagar;
    }
    
    
}
