package com.getechnologiesmx.test.services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.getechnologiesmx.test.entities.Factura;
import com.getechnologiesmx.test.repositories.FacturaRepository;
import com.getechnologiesmx.test.repositories.PersonaRepository;
import com.getechnologiesmx.test.services.FacturaService;
import com.getechnologiesmx.test.vo.FacturaVo;

@Service
public class FacturaServiceImpl implements FacturaService{

	@Autowired
	private FacturaRepository repository;
	
	@Autowired
	private PersonaRepository personaRepository;
	
	@Override
	public List<Factura> finFacturasByPersona(String identifiacion) throws NotFoundException {
		var p = personaRepository.findByIdentificacion(identifiacion).orElseThrow(() -> new NotFoundException());
		
		var facturas = repository.finByPersonaIdentifacion(p.getId());
		
		if(facturas == null || facturas.isEmpty()) {
			throw new NotFoundException();
		}
		
		return facturas;
	}

	@Override
	public void storeFactura(FacturaVo factura) throws NotFoundException, ParseException {
		var p = personaRepository.findByIdentificacion(factura.getIdentifacionPersona()).orElseThrow(() -> new NotFoundException());		
		
		Factura f = new Factura();
		f.setFecha(convertStrToDate(factura.getFecha()));
		f.setMonto(factura.getMonto());
		f.setPersona(p);
		
		repository.save(f);
	}
	
	private static Date convertStrToDate(String str) throws ParseException {
		SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
		return sf.parse(str);
	}
}
