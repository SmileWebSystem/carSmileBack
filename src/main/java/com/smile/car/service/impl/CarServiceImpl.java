package com.smile.car.service.impl;

import org.springframework.stereotype.Service;

import com.smile.car.dto.RespuestaDto;
import com.smile.car.dto.VehiculoDto;
import com.smile.car.service.ICarService;

@Service
public class CarServiceImpl implements ICarService {

	/*
	 * (non-Javadoc)
	 * @see com.smile.car.service.ICarService#analizarPlaca(java.lang.String)
	 */
	@Override
	public RespuestaDto analizarPlaca(String placa) {
		
		RespuestaDto d = new RespuestaDto();
		VehiculoDto v = new VehiculoDto();
		v.setMarca("MAzda");
		d.setVehiculo(v);
		
		return d;
	}

}
