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
public class ComparendoDto {
    
    private BigDecimal valor;
    
    private Date fecha;
    
    private String estado;
    
    private String descripcion;
    
    /**
     * 
     */
    public ComparendoDto() {
        
    }

    /**
     * 
     * @param valor
     * @param fecha
     * @param estado 
     */
    public ComparendoDto(BigDecimal valor, Date fecha, String estado) {
        this.valor = valor;
        this.fecha = fecha;
        this.estado = estado;
    }
    
    

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    
    
}
