package com.getechnologiesmx.test.services;

import java.text.ParseException;
import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.getechnologiesmx.test.entities.Factura;
import com.getechnologiesmx.test.vo.FacturaVo;

public interface FacturaService {
	
	List<Factura> finFacturasByPersona(String identifiacion) throws NotFoundException;
	void storeFactura(FacturaVo factura) throws NotFoundException, ParseException;
}
