package ar.edu.unlam.eventos;

import java.time.LocalDate;

import ar.edu.unlam.eventos.enums.Sala;

public class Conferencia extends Evento {
	
	private Integer CANTIDADmAXIMApARTICIPANTES=50;
	private Double PRECIO=15000.00;

	public Conferencia(String codigoEvento, LocalDate fechaEvento, String nombreEvento, Sala sala, Persona expositor) {
		super(codigoEvento, fechaEvento, nombreEvento, sala, expositor);
		
	}

	public Integer getCANTIDADmAXIMApARTICIPANTES() {
		return CANTIDADmAXIMApARTICIPANTES;
	}

	public void setCANTIDADmAXIMApARTICIPANTES(Integer cANTIDADmAXIMApARTICIPANTES) {
		CANTIDADmAXIMApARTICIPANTES = cANTIDADmAXIMApARTICIPANTES;
	}

	public Double getPRECIO() {
		return PRECIO;
	}

	public void setPRECIO(Double pRECIO) {
		PRECIO = pRECIO;
	}

	

}
