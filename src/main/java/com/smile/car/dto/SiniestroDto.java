/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smile.car.dto;


import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author lvalbuena
 */
public class SiniestroDto {
    
    private Date fecha;
    
    private BigDecimal valor;
    
    private String numero;
    
    private String estado;

    /**
     * 
     */
    public SiniestroDto() {
        
    }

//    /**
//     * 
//     * @param fecha
//     * @param valor 
//     */
//    public SiniestroDto(Date fecha, Long valor) {
//        this.fecha = fecha;
//        this.valor = valor;
//    }
    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
    
    
}
