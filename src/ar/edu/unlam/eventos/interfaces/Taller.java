package ar.edu.unlam.eventos.interfaces;

import ar.edu.unlam.eventos.Empresa;
import ar.edu.unlam.eventos.exceptions.ClienteYaExisteEnEventoException;
import ar.edu.unlam.eventos.exceptions.CupoLlenoException;
import ar.edu.unlam.eventos.exceptions.EventoDuplicadoException;
import ar.edu.unlam.eventos.exceptions.ParticipanteNoEsClienteException;
import ar.edu.unlam.eventos.exceptions.ParticipanteNoPerteneceAlEventoException;

public interface Taller {
	public Double calcularPrecioTaller();
	public void setCupoParticipantesTaller(Integer cupo);

	public boolean agregarParticipante(Participante participante,Empresa empresa)
			throws CupoLlenoException, ParticipanteNoEsClienteException, EventoDuplicadoException, ClienteYaExisteEnEventoException;

	public boolean buscarClienteEnEventoPorParticipante(Participante participante)
			throws ParticipanteNoEsClienteException, ParticipanteNoPerteneceAlEventoException;

}
