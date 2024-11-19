package ar.edu.unlam.eventos.interfaces;

import java.time.LocalDate;

import ar.edu.unlam.eventos.Empresa;
import ar.edu.unlam.eventos.exceptions.ClienteYaExisteEnEventoException;
import ar.edu.unlam.eventos.exceptions.CupoLlenoException;
import ar.edu.unlam.eventos.exceptions.EventoDuplicadoException;
import ar.edu.unlam.eventos.exceptions.ParticipanteNoEsClienteException;
import ar.edu.unlam.eventos.exceptions.ParticipanteNoPerteneceAlEventoException;

public interface Conferencia  {	
	

	public void setPrecioconferencia(Double precioConferencia);
	public void setCupoParticipantesConferencia(Integer cupo);

	public boolean agregarParticipante(Participante participante,Empresa empresa)
			throws CupoLlenoException, ParticipanteNoEsClienteException, EventoDuplicadoException, ClienteYaExisteEnEventoException;

	public boolean buscarClienteEnEventoPorParticipante(Participante participante)
			throws ParticipanteNoEsClienteException, ParticipanteNoPerteneceAlEventoException;
	

}
