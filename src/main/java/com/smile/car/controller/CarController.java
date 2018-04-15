/**
 * 
 */
package com.smile.car.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smile.car.dto.RespuestaDto;
import com.smile.car.service.ICarService;

/**
 * @author leonardovalbuenacalderon
 *
 */
@RestController
@RequestMapping("/car")
@CrossOrigin(origins = "*")
public class CarController {
	
	
	@Autowired
	private ICarService carService;
	
	@GetMapping("/health")
    public ResponseEntity healthCheck() {
        return new ResponseEntity(HttpStatus.OK);
    }
	
	@GetMapping("/estudio")
	public ResponseEntity<RespuestaDto> carStudy(String placa) {
		return new ResponseEntity<RespuestaDto>(carService.analizarPlaca(placa), HttpStatus.OK);
	}
	
	
	

}
