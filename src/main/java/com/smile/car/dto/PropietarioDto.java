/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smile.car.dto;

/**
 *
 * @author lvalbuena
 */
public class PropietarioDto {
    
    private String tipoDocumento;
    
    private String numeroDocumento;
    
    private String nombre;
    
    /**
     * 
     */
    public PropietarioDto() {
        
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public int hashCode() {
        return numeroDocumento.hashCode() ^ 3;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PropietarioDto))
            return false;

        PropietarioDto pro = (PropietarioDto) obj;
        return pro.getNumeroDocumento().equals(this.numeroDocumento);
    }
    
    
    
}
