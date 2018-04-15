/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smile.car.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lvalbuena
 */
public class RespuestaDto {        
    
    private VehiculoDto vehiculo;
    
    private PolizaDto poliza;
    
    private List<PropietarioDto> propietarioList;
    
    private List<SiniestroDto> siniestroList;
    
    private List<ComparendoDto> comparendoList;
    
    /**
     * 
     */
    public RespuestaDto() {        
        vehiculo = new VehiculoDto();
        poliza = new PolizaDto();
        propietarioList = new ArrayList<PropietarioDto>();
        siniestroList = new ArrayList<SiniestroDto>();
        comparendoList = new ArrayList<ComparendoDto>();    
    }

    public VehiculoDto getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(VehiculoDto vehiculo) {
        this.vehiculo = vehiculo;
    }

    public PolizaDto getPoliza() {
        return poliza;
    }

    public void setPoliza(PolizaDto poliza) {
        this.poliza = poliza;
    }

    public List<PropietarioDto> getPropietarioList() {
        return propietarioList;
    }

    public void setPropietarioList(List<PropietarioDto> propietarioList) {
        this.propietarioList = propietarioList;
    }

    public List<SiniestroDto> getSiniestroList() {
        return siniestroList;
    }

    public void setSiniestroList(List<SiniestroDto> siniestroList) {
        this.siniestroList = siniestroList;
    }

    public List<ComparendoDto> getComparendoList() {
        return comparendoList;
    }

    public void setComparendoList(List<ComparendoDto> comparendoList) {
        this.comparendoList = comparendoList;
    }
    
}
