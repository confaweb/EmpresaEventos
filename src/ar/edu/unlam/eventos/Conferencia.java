package ar.edu.unlam.eventos;

import java.time.LocalDate;

import ar.edu.unlam.eventos.enums.Sala;
import ar.edu.unlam.eventos.exceptions.CupoLlenoException;
import ar.edu.unlam.eventos.exceptions.ParticipanteNoEsClienteException;
import ar.edu.unlam.eventos.interfaces.Cliente;
import ar.edu.unlam.eventos.interfaces.Participante;

public class Conferencia extends Evento {

	private Integer CANTIDADmAXIMApARTICIPANTES = 50;
	private Double PRECIO = 15000.00;

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

	@Override
	public boolean agregarParticipante(Participante participante)
			throws CupoLlenoException, ParticipanteNoEsClienteException {
		if (!(participante instanceof Cliente))
			throw new ParticipanteNoEsClienteException(
					"Debe hacer cliente al participante para poder agregarlo al evento");

		if (super.getParticipantes().size() < this.CANTIDADmAXIMApARTICIPANTES) {
			Boolean participanteAgregado = super.getParticipantes().add(participante);
			return participanteAgregado;
		}

		throw new CupoLlenoException("Cupo lleno");

	}

	@Override
	public boolean buscarClienteEnEventoPorParticipante(Participante participante)
			throws ParticipanteNoEsClienteException {
		Boolean clienteEncontrado = false;
		if (!(participante instanceof Cliente))
			throw new ParticipanteNoEsClienteException(
					"Debe hacer cliente al participante para poder agregarlo al evento");
		clienteEncontrado = true;

		return clienteEncontrado;
	}

}
