/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;

/**
 *
 * @author Omar
 */
public class Recibo {
    private double montoPagado;
    private String estatusPago;
    private String folio;
    private Date fecha;
    private String tarjeta;

    public Recibo() {
    }
    
    public Recibo(double montoPagado, String estatusPago, String folio, Date fecha, String tarjeta) {
        this.montoPagado = montoPagado;
        this.estatusPago = estatusPago;
        this.folio = folio;
        this.fecha = fecha;
        this.tarjeta = tarjeta;
    }

    public double getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(double montoPagado) {
        this.montoPagado = montoPagado;
    }

    public String getEstatusPago() {
        return estatusPago;
    }

    public void setEstatusPago(String estatusPago) {
        this.estatusPago = estatusPago;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }
    
    
}
