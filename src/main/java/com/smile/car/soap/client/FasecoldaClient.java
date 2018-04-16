/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smile.car.soap.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.ws.BindingProvider;


import org.springframework.stereotype.Service;

import com.smile.clientWS.fasecolda.ConsultaSisaRequestType;
import com.smile.clientWS.fasecolda.ConsultaSisaRespType;
import com.smile.clientWS.fasecolda.DataHeaderRequestType;
import com.smile.clientWS.fasecolda.FasecoldaService;
import com.smile.clientWS.fasecolda.FasecoldaService_Service;
import com.smile.clientWS.fasecolda.PolizasExceptionFault;

/**
 * consume las operaciones de Fasecolda
 * 
 * @author martin
 */
@Service
public class FasecoldaClient {

	/**
	 * wsdl del servicio a consumir
	 */
	private static final String WSDL = "http://ambientepruebas.segurosbolivar.com/SimonWS/FasecoldaService";

	/**
	 * enpoint servicio
	 */
	private FasecoldaService_Service fasecoldaService_Service;
	private FasecoldaService fasecoldaService;

	public void init() {

		try {
			fasecoldaService_Service = new FasecoldaService_Service(new URL(WSDL));
			fasecoldaService = fasecoldaService_Service.getFasecoldaServicePort();
		} catch (MalformedURLException ex) {
//			Logger.getLogger(FasecoldaClient.class.getName()).log(Level.SEVERE, null, ex);
		}

//		fasecoldaService = new FasecoldaService_Service().getFasecoldaServicePort();
//		BindingProvider bp = (BindingProvider) fasecoldaService;
//		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, WSDL);
//		SOAPRequestResponseInterceptor.addToPort(bp.getBinding());
//		System.out.println("al pelo");

	}

	/**
	 * Retorna el DataHeader para el consumo de los servicios
	 * 
	 * @return
	 */
	private DataHeaderRequestType getHeader() {
		DataHeaderRequestType header = new DataHeaderRequestType();
		header.setModulo("2");
		header.setProceso(2000);
		header.setSubProceso(240);
		header.setCodCia(3);
		header.setCodSecc(1);
		header.setCodProducto(250);
		header.setSubProducto(1);
		header.setCodUrs("51938035");
		header.setEntidadColocadora(0);
		header.setCanal(1);
		header.setSistemaOrigen(104);
		header.setPais(1);
		header.setDireccionIp("127.0.0.1");
		header.setVersionServicio("1.0");
		return header;
	}

	/**
	 * 
	 * @param placa
	 * @return
	 * @throws PolizasExceptionFault
	 */
	public ConsultaSisaRespType consultaSisa(String placa) throws PolizasExceptionFault {

		ConsultaSisaRequestType sisaRequest = new ConsultaSisaRequestType();
		sisaRequest.setDataHeader(this.getHeader());
		sisaRequest.setPlaca(placa);

		ConsultaSisaRespType resp = fasecoldaService.consultaSisa(sisaRequest);
		return resp;
	}

}
