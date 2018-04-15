/**
 * 
 */
package com.smile.car.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leonardovalbuenacalderon
 *
 */
@RestController
@RequestMapping("/car")
@CrossOrigin(origins = "*")
public class CarController {
	
	@GetMapping("/health")
    public ResponseEntity healthCheck() {
        return new ResponseEntity(HttpStatus.OK);
    }
	
	
	

}
