/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Omar
 */
public class Cliente {

    private String numeroServicio;
    private String nombre;
    private Consumo consumo;

    public Cliente() {
    }

    public Cliente(String numeroServicio, String nombre, Consumo consumo) {
        this.numeroServicio = numeroServicio;
        this.nombre = nombre;
        this.consumo = consumo;
    }

    public String getNumeroServicio() {
        return numeroServicio;
    }

    public void setNumeroServicio(String numeroServicio) {
        this.numeroServicio = numeroServicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Consumo getConsumo() {
        return consumo;
    }

    public void setConsumo(Consumo consumo) {
        this.consumo = consumo;
    }

    @Override
    public String toString() {
        return numeroServicio + " - " + nombre;
    }

}
