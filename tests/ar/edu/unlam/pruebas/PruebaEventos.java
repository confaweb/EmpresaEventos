package ar.edu.unlam.pruebas;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import ar.edu.unlam.eventos.Conferencia;
import ar.edu.unlam.eventos.Empresa;
import ar.edu.unlam.eventos.Evento;
import ar.edu.unlam.eventos.Persona;
import ar.edu.unlam.eventos.enums.Sala;
import ar.edu.unlam.eventos.exceptions.EventoDuplicadoException;
import ar.edu.unlam.eventos.exceptions.EventoInexistenteException;
import ar.edu.unlam.eventos.interfaces.Cliente;
import ar.edu.unlam.eventos.interfaces.Expositor;

public class PruebaEventos {

	@Test // #1
	public void dadoQueExisteUnaEmpresaCuandoAgregoUnClienteObtengoUnResultadoExitoso() {
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
		Evento evento;
		Integer cuit = 110202023, dni = 111111;
		String nombreEmpresa = "Janos", nombre = "jose", apellido = "Lopez", codigoEvento = "Conf001",
				nombreEvento = "Marcianos";
		LocalDate fechaEvento = LocalDate.of(2024, 12, 12);
		Sala sala = Sala.GRANDE;
		// PREPARACION
		expositor = new Persona(dni, nombre, apellido);
		evento = new Conferencia(codigoEvento, fechaEvento, nombreEvento, sala, (Persona) expositor);
		empresa = new Empresa(cuit, nombreEmpresa);

		assertTrue(empresa.agregarEvento(evento));

		// VALIDACION
		assertTrue(empresa.agregarEvento(evento));
	}

	@Test // #3
	public void dadoQueExisteUnaEmpresaConEventosCuandoBuscoUnEventoExistentePorSuCodigoObtengoElEvento() throws EventoDuplicadoException,EventoInexistenteException  {
		// INICIO
		Empresa empresa;
		Expositor expositor;
		Evento evento;
		Integer cuit = 110202023, dni = 111111;
		String nombreEmpresa = "Janos", nombre = "jose", apellido = "Lopez", codigoEvento = "Conf001",
				nombreEvento = "Marcianos";
		LocalDate fechaEvento = LocalDate.of(2024, 12, 12);
		Sala sala = Sala.GRANDE;
		// PREPARACION
		expositor = new Persona(dni, nombre, apellido);
		evento = new Conferencia(codigoEvento, fechaEvento, nombreEvento, sala, (Persona) expositor);
		empresa = new Empresa(cuit, nombreEmpresa);

		assertTrue(empresa.agregarEvento(evento));

		// VALIDACION
		assertEquals(empresa.buscarEventoPorCodigo(codigoEvento),evento);
	}

	@Test // #4
	public void dadoQueExisteUnaEmpresaConEventosCuandoVerificoSiUnClienteSeEncuentraEntreLosParticipantesDeUnEventoPorClienteYExisteObtengoUnResultadoPositivo() {

	}

	@Test // #5
	public void dadoQueExisteUnaEmpresaConEventosCuandoAgregoUnClienteAUnEventoDondeExisteElClienteObtengoUnaClienteExistenteEnEventoException() {

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
