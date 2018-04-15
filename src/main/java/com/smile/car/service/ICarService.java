/**
 * 
 */
package com.smile.car.service;

import com.smile.car.dto.RespuestaDto;

/**
 * @author leonardovalbuenacalderon
 *
 */
public interface ICarService {
	
	/**
	 * a partir de la placa realiza la consulta en fasecolda
	 * @param placa
	 * @return
	 */
	RespuestaDto analizarPlaca(String placa);

}
