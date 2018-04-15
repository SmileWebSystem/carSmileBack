/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smile.car.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Contiene la informacion de la ultima poliza del vehiculo consultado
 * @author lvalbuena
 */
public class PolizaDto {
    
    private String compania;
    
    private String codCompania;
    
    private String numPoliza;
    
    private String vigente;//SI - NO
    
    private Date fechaVigencia;
    
    private Date fechaFinVigencia;
    
    private BigDecimal valorAsegurado;
    
    /**
     * 
     */
    public PolizaDto() {
        
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public String getCodCompania() {
        return codCompania;
    }

    public void setCodCompania(String codCompania) {
        this.codCompania = codCompania;
    }

    public String getNumPoliza() {
        return numPoliza;
    }

    public void setNumPoliza(String numPoliza) {
        this.numPoliza = numPoliza;
    }

    public String getVigente() {
        return vigente;
    }

    public void setVigente(String vigente) {
        this.vigente = vigente;
    }

    public Date getFechaVigencia() {
        return fechaVigencia;
    }

    public void setFechaVigencia(Date fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
    }

    public Date getFechaFinVigencia() {
        return fechaFinVigencia;
    }

    public void setFechaFinVigencia(Date fechaFinVigencia) {
        this.fechaFinVigencia = fechaFinVigencia;
    }

    public BigDecimal getValorAsegurado() {
        return valorAsegurado;
    }

    public void setValorAsegurado(BigDecimal valorAsegurado) {
        this.valorAsegurado = valorAsegurado;
    }
    
    
    
}
