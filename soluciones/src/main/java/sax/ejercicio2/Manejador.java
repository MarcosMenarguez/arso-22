package sax.ejercicio2;

import java.time.LocalDate;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Manejador extends DefaultHandler {

	private int diligencias;

	public int getDiligencias() {
		return diligencias;
	}

	@Override
	public void startDocument() throws SAXException {
		
		// El manejador podría utilizarse con varios documentos.
		// Con este método se resetea el contador.
		
		diligencias = 0;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		if (qName.equals("diligencia")) {
			
			String fechaString = attributes.getValue("fecha");
			
			LocalDate fechaDiligencia = LocalDate.parse(fechaString);
			
			LocalDate fechaReferencia = LocalDate.now().minusMonths(1);
			
			if (fechaDiligencia.isAfter(fechaReferencia))
				diligencias++;
			
			
		}
			
	}

}
