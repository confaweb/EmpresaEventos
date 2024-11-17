package ar.edu.unlam.eventos.interfaces;

import java.time.LocalDate;

import ar.edu.unlam.eventos.enums.Sala;
import ar.edu.unlam.eventos.exceptions.CupoLlenoException;
import ar.edu.unlam.eventos.exceptions.ParticipanteNoEsClienteException;

public interface Conferencia  {	
	

	public void setPrecioconferencia(Double precioConferencia);
	public void setCupoParticipantesConferencia(Integer cupo);

	public boolean agregarParticipante(Participante participante)
			throws CupoLlenoException, ParticipanteNoEsClienteException;

	public boolean buscarClienteEnEventoPorParticipante(Participante participante)
			throws ParticipanteNoEsClienteException;
	

}
