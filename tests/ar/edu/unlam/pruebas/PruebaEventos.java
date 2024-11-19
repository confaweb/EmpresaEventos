package ar.edu.unlam.pruebas;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import ar.edu.unlam.eventos.Empresa;
import ar.edu.unlam.eventos.Evento;
import ar.edu.unlam.eventos.Persona;
import ar.edu.unlam.eventos.enums.Sala;
import ar.edu.unlam.eventos.exceptions.ClienteYaExisteEnEventoException;
import ar.edu.unlam.eventos.exceptions.CupoLlenoException;
import ar.edu.unlam.eventos.exceptions.EventoDuplicadoException;
import ar.edu.unlam.eventos.exceptions.EventoInexistenteException;
import ar.edu.unlam.eventos.exceptions.ParticipanteNoEsClienteException;
import ar.edu.unlam.eventos.exceptions.ParticipanteNoPerteneceAlEventoException;
import ar.edu.unlam.eventos.interfaces.Cliente;
import ar.edu.unlam.eventos.interfaces.Conferencia;
import ar.edu.unlam.eventos.interfaces.Expositor;
import ar.edu.unlam.eventos.interfaces.Participante;

public class PruebaEventos {

	@Test // #1
	public void dadoQueExisteUnaEmpresaCuandoAgregoUnClienteObtengoUnResultadoExitoso() throws ClienteYaExisteEnEventoException {
		// INICIO
		Empresa empresa;
		Cliente cliente;
		Integer cuit = 110202023, dni = 111111;
		String nombreEmpresa = "Janos", nombre = "jose", apellido = "Lopez";
		// PREPARACION
		cliente = new Persona(dni, nombre, apellido);
		empresa = new Empresa(cuit, nombreEmpresa);

		// VALIDACION
		assertTrue(empresa.agregarCliente((Persona) cliente));
	}

	@Test // #2
	(expected = EventoDuplicadoException.class)
	public void dadoQueExisteUnaEmpresaCuandoAgregoUnEventoExistenteObtengoUnaEventoDuplicadoException()
			throws EventoDuplicadoException {
		// INICIO
		Empresa empresa;
		Expositor expositor;
		Conferencia evento;
		Integer cuit = 110202023, dni = 111111;
		String nombreEmpresa = "Janos", nombre = "jose", apellido = "Lopez", codigoEvento = "Conf001",
				nombreEvento = "Marcianos";
		LocalDate fechaEvento = LocalDate.of(2024, 12, 12);
		Sala sala = Sala.GRANDE;
		// PREPARACION
		expositor = new Persona(dni, nombre, apellido);
		evento = new Evento(codigoEvento, fechaEvento, nombreEvento, sala, (Persona) expositor);
		empresa = new Empresa(cuit, nombreEmpresa);

		assertTrue(empresa.agregarEvento(evento));

		// VALIDACION
		assertTrue(empresa.agregarEvento(evento));
	}

	@Test // #3
	public void dadoQueExisteUnaEmpresaConEventosCuandoBuscoUnEventoExistentePorSuCodigoObtengoElEvento()
			throws EventoDuplicadoException, EventoInexistenteException {
		// INICIO
		Empresa empresa;
		Expositor expositor;
		Conferencia evento;
		Integer cuit = 110202023, dni = 111111;
		String nombreEmpresa = "Janos", nombre = "jose", apellido = "Lopez", codigoEvento = "Conf001",
				nombreEvento = "Marcianos";
		LocalDate fechaEvento = LocalDate.of(2024, 12, 12);
		Sala sala = Sala.GRANDE;
		// PREPARACION
		expositor = new Persona(dni, nombre, apellido);
		evento = new Evento(codigoEvento, fechaEvento, nombreEvento, sala, (Persona) expositor);
		empresa = new Empresa(cuit, nombreEmpresa);

		assertTrue(empresa.agregarEvento(evento));
		

		// VALIDACION
		assertEquals(empresa.buscarEventoPorCodigo(codigoEvento), evento);
	}

	@Test // #4
	public void dadoQueExisteUnaEmpresaConEventosCuandoVerificoSiUnClienteSeEncuentraEntreLosParticipantesDeUnEventoPorClienteYExisteObtengoUnResultadoPositivo() throws EventoDuplicadoException, EventoInexistenteException,CupoLlenoException, ParticipanteNoEsClienteException, ParticipanteNoPerteneceAlEventoException, ClienteYaExisteEnEventoException {
		// INICIO
		Empresa empresa;
		Cliente cliente;
		Participante participante;
		Conferencia evento;
		Integer cuit = 110202023, dni = 111111,cupoParticipantes=50;
		String nombreEmpresa = "Janos", nombre = "jose", apellido = "Lopez", codigoEvento = "Conf001",
				nombreEvento = "Marcianos";
		LocalDate fechaEvento = LocalDate.of(2024, 12, 12);
		Sala sala = Sala.GRANDE;
		// PREPARACION
		cliente =new Persona (dni,nombre,apellido);
		participante = new Persona(dni, nombre, apellido);
		evento = new Evento(codigoEvento, fechaEvento, nombreEvento, sala, (Persona) participante);
		empresa = new Empresa(cuit, nombreEmpresa);
		
		evento.setCupoParticipantesConferencia(cupoParticipantes);
		assertTrue(empresa.agregarEvento(evento));
		assertTrue (empresa.agregarCliente((Persona) cliente));
		assertTrue (evento.agregarParticipante(participante, empresa));

		// VALIDACION
		assertTrue(evento.buscarClienteEnEventoPorParticipante(participante));
		assertTrue(empresa.getClientes().contains(participante));
		assertTrue(((Evento) evento).getParticipantes().contains(cliente));
	}

	@Test // #5
	(expected= ClienteYaExisteEnEventoException.class)
	public void dadoQueExisteUnaEmpresaConEventosCuandoAgregoUnClienteAUnEventoDondeExisteElClienteObtengoUnaClienteExistenteEnEventoException() throws CupoLlenoException, ParticipanteNoEsClienteException, EventoDuplicadoException, ClienteYaExisteEnEventoException {
		Empresa empresa;
		Cliente cliente;
		Participante participante;
		Conferencia evento;
		Integer cuit = 110202023, dni = 111111,cupoParticipantes=50;
		String nombreEmpresa = "Janos", nombre = "jose", apellido = "Lopez", codigoEvento = "Conf001",
				nombreEvento = "Marcianos";
		LocalDate fechaEvento = LocalDate.of(2024, 12, 12);
		Sala sala = Sala.GRANDE;
		// PREPARACION
		cliente =new Persona (dni,nombre,apellido);
		participante = new Persona(dni, nombre, apellido);
		evento = new Evento(codigoEvento, fechaEvento, nombreEvento, sala, (Persona) participante);
		empresa = new Empresa(cuit, nombreEmpresa);
		
		evento.setCupoParticipantesConferencia(cupoParticipantes);
		assertTrue(empresa.agregarEvento(evento));
		assertTrue (empresa.agregarCliente((Persona) cliente));
		assertTrue (evento.agregarParticipante(participante, empresa));

		// VALIDACION
		evento.agregarParticipante(participante, empresa);
	}


	

	@Test // #6
	public void dadoQueExisteUnaEmpresaConEventosCuandoAgregoUnClienteAUnTallerSinCupoDondeNoExisteElClienteObtengoUnResultadoFallido() {

	}

	@Test // #7
	public void dadoQueExisteUnaEmpresaConEventosCuandoConsultoLaRecaudacionTodalDeUnEventoTallerCon10ParticipantesRecibo250000() {

	}

	@Test // #8
	public void dadoQueExisteUnaEmpresaConEventos3ConferenciasObtengoUnaListaCon3Conferencias() {

	}

	@Test // #9
	public void dadoQueExisteUnaEmpresaConEventosCuandoConsultoLosParticipantesDeConferenciasObtengoUnMapaConLasConferenciasComoClaveYUnaColeccionDeParticipantesPorConferenciaOrdenadaPorApellido() {

	}

}
