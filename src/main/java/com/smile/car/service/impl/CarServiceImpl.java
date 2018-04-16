package com.smile.car.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smile.car.dto.ComparendoDto;
import com.smile.car.dto.PolizaDto;
import com.smile.car.dto.PropietarioDto;
import com.smile.car.dto.RespuestaDto;
import com.smile.car.dto.SiniestroDto;
import com.smile.car.dto.VehiculoDto;
import com.smile.car.service.ICarService;
import com.smile.car.soap.client.FasecoldaClient;
import com.smile.clientWS.fasecolda.AmparoSisaType;
import com.smile.clientWS.fasecolda.ConsulExternaSIMITSisaOutType;
import com.smile.clientWS.fasecolda.ConsultaSisaRespType;
import com.smile.clientWS.fasecolda.HistoricoPolizaSisaOutType;
import com.smile.clientWS.fasecolda.HistoricoSiniestroSisaOutType;
import com.smile.clientWS.fasecolda.PolizasExceptionFault;

@Service
public class CarServiceImpl implements ICarService {

	@Autowired
	private FasecoldaClient fasecoldaClient;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.smile.car.service.ICarService#analizarPlaca(java.lang.String)
	 */
	@Override
	public RespuestaDto analizarPlaca(String placa) {

		RespuestaDto respuestaDto = new RespuestaDto();

		try {
			fasecoldaClient.init();
			ConsultaSisaRespType respType = fasecoldaClient.consultaSisa(placa);

			List<HistoricoPolizaSisaOutType> historicosPoliza = respType.getData().getHistoricoPolizasSisa();
			if (historicosPoliza != null && !historicosPoliza.isEmpty()) {
				HistoricoPolizaSisaOutType poliza = historicosPoliza.get(0);
				respuestaDto.setVehiculo(this.obtenerDatosVehiculo(poliza));
				respuestaDto.setPoliza(this.obtenerDatosPoliza(poliza));
				respuestaDto.setPropietarioList(this.obtenerPropietarios(historicosPoliza));
				respuestaDto.setSiniestroList(this.obtenerSiniestros(respType.getData().getHistoricoSiniestrosSisa()));
				respuestaDto.setComparendoList(this.obtenerComparendos(respType.getData().getSIMITSisa()));
			}
		} catch (PolizasExceptionFault ex) {
			Logger.getLogger(CarServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
		return respuestaDto;
	}

	/**
	 * Obtiene los posibles propietarios del vehiculo
	 * 
	 * @param polizaOut
	 * @return
	 */
	private List<PropietarioDto> obtenerPropietarios(List<HistoricoPolizaSisaOutType> polizasOut) {
		List<PropietarioDto> propietarios = new ArrayList<PropietarioDto>();

		for (HistoricoPolizaSisaOutType polizaOut : polizasOut) {
			PropietarioDto propietarioDto = new PropietarioDto();
			propietarioDto.setNombre(polizaOut.getAsegurado());
			propietarioDto.setNumeroDocumento(polizaOut.getNumeroDocumento());
			propietarioDto.setTipoDocumento(polizaOut.getTipoDocumentoAsegurado());
			propietarios.add(propietarioDto);
		}

		// se eliminan los repetidos (igual numero de documento)
		Set<PropietarioDto> uniqueElements = new HashSet<PropietarioDto>(propietarios);
		propietarios.clear();
		propietarios.addAll(uniqueElements);

		return propietarios;
	}

	/**
	 * Obtiene los datos basicos del vehiculo
	 * 
	 * @param sisaOutType
	 * @return
	 */
	private VehiculoDto obtenerDatosVehiculo(HistoricoPolizaSisaOutType historicoPolizaSisaOutType) {
		VehiculoDto vehiculoDto = new VehiculoDto();
		vehiculoDto.setChasis(historicoPolizaSisaOutType.getChasis());
		vehiculoDto.setClase(historicoPolizaSisaOutType.getClase());
		vehiculoDto.setMarca(historicoPolizaSisaOutType.getMarca());
		vehiculoDto.setModelo(String.valueOf(historicoPolizaSisaOutType.getModelo()));
		vehiculoDto.setMotor(historicoPolizaSisaOutType.getMotor());
		vehiculoDto.setPlaca(historicoPolizaSisaOutType.getPlaca());
		vehiculoDto.setTipo(historicoPolizaSisaOutType.getTipo());
		return vehiculoDto;
	}

	/**
	 * Obtiene la informacion de la ultima poliza
	 * 
	 * @param polizaSisaOutType
	 * @return
	 */
	private PolizaDto obtenerDatosPoliza(HistoricoPolizaSisaOutType polizaSisaOutType) {
		PolizaDto polizaDto = new PolizaDto();
		polizaDto.setCodCompania(String.valueOf(polizaSisaOutType.getCodigoCompania()));
		polizaDto.setCompania(polizaSisaOutType.getNombreCompania());
		polizaDto.setFechaFinVigencia(polizaSisaOutType.getFechaFinVigencia() != null
				? polizaSisaOutType.getFechaFinVigencia().toGregorianCalendar().getTime()
				: null);
		polizaDto.setFechaVigencia(polizaSisaOutType.getFechaVigencia() != null
				? polizaSisaOutType.getFechaVigencia().toGregorianCalendar().getTime()
				: null);
		polizaDto.setNumPoliza(polizaSisaOutType.getNumeroPoliza());
		polizaDto.setValorAsegurado(polizaSisaOutType.getValorAsegurado());
		polizaDto.setVigente(polizaSisaOutType.getVigente());
		return polizaDto;
	}

	/**
	 * Consulta los siniestros que pueda tener el vehiculo
	 * 
	 * @return
	 */
	private List<SiniestroDto> obtenerSiniestros(List<HistoricoSiniestroSisaOutType> siniestrosOut) {
		List<SiniestroDto> siniestros = new ArrayList<SiniestroDto>();
		if (siniestrosOut != null && !siniestrosOut.isEmpty()) {
			for (HistoricoSiniestroSisaOutType siniestroOut : siniestrosOut) {
				SiniestroDto siniestroDto = new SiniestroDto();
				siniestroDto.setNumero(siniestroOut.getNumeroSiniestro());
				siniestroDto.setFecha(siniestroOut.getFechaSiniestro() != null
						? siniestroOut.getFechaSiniestro().toGregorianCalendar().getTime()
						: null);

				if (siniestroOut.getAmparos() != null && !siniestroOut.getAmparos().isEmpty()) {
					AmparoSisaType amparo = siniestroOut.getAmparos().get(0);
					siniestroDto.setValor(new BigDecimal(amparo.getValorReclamaAmparo()));
					siniestroDto.setEstado(amparo.getEstado());
				}
				siniestros.add(siniestroDto);
			}
		}
		return siniestros;
	}

	/**
	 * COnsulta los compatrendos que pyueda tener el vehiculo
	 * 
	 * @param comparendosOut
	 * @return
	 */
	private List<ComparendoDto> obtenerComparendos(List<ConsulExternaSIMITSisaOutType> comparendosOut) {
		List<ComparendoDto> comparendos = new ArrayList<ComparendoDto>();

		if (comparendosOut != null && !comparendosOut.isEmpty()) {
			for (ConsulExternaSIMITSisaOutType comparendoOut : comparendosOut) {
				ComparendoDto comparendoDto = new ComparendoDto();
				comparendoDto.setEstado(comparendoOut.getEstado());
				comparendoDto.setFecha(comparendoOut.getFechaComparendo() != null
						? comparendoOut.getFechaComparendo().toGregorianCalendar().getTime()
						: null);
				comparendoDto.setValor(comparendoOut.getValorInfraccion());
				comparendoDto.setDescripcion(comparendoOut.getCodigoInfraccion());
				comparendos.add(comparendoDto);
			}
		}
		return comparendos;
	}

}
