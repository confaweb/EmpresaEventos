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
import ar.edu.unlam.eventos.interfaces.Taller;

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

		assertTrue(empresa.agregarEvento((Evento) evento));

		// VALIDACION
		assertTrue(empresa.agregarEvento((Evento) evento));
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

		assertTrue(empresa.agregarEvento((Evento) evento));
		

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
		assertTrue(empresa.agregarEvento((Evento) evento));
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
		assertTrue(empresa.agregarEvento((Evento) evento));
		assertTrue (empresa.agregarCliente((Persona) cliente));
		assertTrue (evento.agregarParticipante(participante, empresa));

		// VALIDACION
		evento.agregarParticipante(participante, empresa);
	}


	

	@Test // #6
	public void dadoQueExisteUnaEmpresaConEventosCuandoAgregoUnClienteAUnTallerSinCupoDondeNoExisteElClienteObtengoUnResultadoFallido() throws EventoDuplicadoException, CupoLlenoException, ParticipanteNoEsClienteException, ClienteYaExisteEnEventoException {
		Empresa empresa;
		Cliente cliente;
		Participante participante,participante2,participante3,participante4,participante5;
		Taller evento;
		Integer cuit = 110202023, dni = 111111,dni2 = 222222,dni3 = 333333,dni4 = 444444,dni5 = 555555,cupoParticipantes=4;
		String nombreEmpresa = "Janos", nombre = "jose", apellido = "Lopez",apellido2 = "Perez" ,apellido3 = "DePaul",apellido4 = "Martinez",apellido5 = "Sosa",codigoEvento = "Conf001",
				nombreEvento = "Marcianos";
		LocalDate fechaEvento = LocalDate.of(2024, 12, 12);
		Sala sala = Sala.GRANDE;
		// PREPARACION
		cliente =new Persona (dni,nombre,apellido);
		participante = new Persona(dni, nombre, apellido);
		participante2 = new Persona(dni2, nombre, apellido2);
		participante3 = new Persona(dni3, nombre, apellido3);
		participante4 = new Persona(dni4, nombre, apellido4);
		participante5 = new Persona(dni5, nombre, apellido5);
		evento = new Evento(codigoEvento, fechaEvento, nombreEvento, sala, (Persona) participante);
		empresa = new Empresa(cuit, nombreEmpresa);
		
		evento.setCupoParticipantesTaller(cupoParticipantes);
		
		assertTrue(empresa.agregarEvento((Evento) evento));
		assertTrue (empresa.agregarCliente((Persona) participante));
		assertTrue (empresa.agregarCliente((Persona) participante2));
		assertTrue (empresa.agregarCliente((Persona) participante3));
		assertTrue (empresa.agregarCliente((Persona) participante4));
		assertTrue (empresa.agregarCliente((Persona) participante5));
		assertTrue (evento.agregarParticipante(participante, empresa));
		assertTrue (evento.agregarParticipante(participante2, empresa));
		assertTrue (evento.agregarParticipante(participante3, empresa));
		assertTrue (evento.agregarParticipante(participante4, empresa));

		// VALIDACION
		assertFalse(evento.agregarParticipante(participante5, empresa));

	}

	@Test // #7
	public void dadoQueExisteUnaEmpresaConEventosCuandoConsultoLaRecaudacionTodalDeUnEventoTallerCon10ParticipantesRecibo250000() throws EventoDuplicadoException, CupoLlenoException, ParticipanteNoEsClienteException, ClienteYaExisteEnEventoException {
		Empresa empresa;
		Cliente cliente;
		Participante participante,participante2,participante3,participante4,participante5,participante6,participante7,participante8,participante9,participante10;
		Taller evento;
		Integer cuit = 110202023, dni = 111111,dni2 = 222222,dni3 = 333333,dni4 = 444444,dni5 = 555555,
				 dni6 = 666666,dni7 = 777777,dni8 = 888888,dni9 = 999999,dni10 = 101010,cupoParticipantes=20;
		String nombreEmpresa = "Janos", nombre = "jose", apellido = "Lopez",apellido2 = "Perez" ,apellido3 = "DePaul",apellido4 = "Martinez",apellido5 = "Sosa",codigoEvento = "Conf001",
				nombreEvento = "Marcianos", apellido6 = "Marx",apellido7 = "Engels" ,apellido8 = "Castro",apellido9 = "Guevara",apellido10 = "Cienfuegos";
		LocalDate fechaEvento = LocalDate.of(2024, 12, 12);
		Sala sala = Sala.GRANDE;
		double precio = 25000;
		// PREPARACION
		
		cliente =new Persona (dni,nombre,apellido);
		
		participante = new Persona(dni, nombre, apellido);
		evento = new Evento(codigoEvento, fechaEvento, nombreEvento, sala, (Persona) participante);
		((Evento) evento).setPrecio(precio);
		participante2 = new Persona(dni2, nombre, apellido2);
		participante3 = new Persona(dni3, nombre, apellido3);
		participante4 = new Persona(dni4, nombre, apellido4);
		participante5 = new Persona(dni5, nombre, apellido5);
		participante6 = new Persona(dni6, nombre, apellido6);
		participante7 = new Persona(dni7, nombre, apellido7);
		participante8 = new Persona(dni8, nombre, apellido8);
		participante9 = new Persona(dni9, nombre, apellido9);
		participante10 = new Persona(dni10, nombre, apellido10);
		
		
		empresa = new Empresa(cuit, nombreEmpresa);
		
		evento.setCupoParticipantesTaller(cupoParticipantes);
		
		assertTrue(empresa.agregarEvento((Evento) evento));
		assertTrue (empresa.agregarCliente((Persona) participante));
		assertTrue (empresa.agregarCliente((Persona) participante2));
		assertTrue (empresa.agregarCliente((Persona) participante3));
		assertTrue (empresa.agregarCliente((Persona) participante4));
		assertTrue (empresa.agregarCliente((Persona) participante5));
		assertTrue (empresa.agregarCliente((Persona) participante6));
		assertTrue (empresa.agregarCliente((Persona) participante7));
		assertTrue (empresa.agregarCliente((Persona) participante8));
		assertTrue (empresa.agregarCliente((Persona) participante9));
		assertTrue (empresa.agregarCliente((Persona) participante10));
		assertTrue (evento.agregarParticipante(participante, empresa));
		assertTrue (evento.agregarParticipante(participante2, empresa));
		assertTrue (evento.agregarParticipante(participante3, empresa));
		assertTrue (evento.agregarParticipante(participante4, empresa));
		assertTrue (evento.agregarParticipante(participante5, empresa));
		assertTrue (evento.agregarParticipante(participante6, empresa));
		assertTrue (evento.agregarParticipante(participante7, empresa));
		assertTrue (evento.agregarParticipante(participante8, empresa));
		assertTrue (evento.agregarParticipante(participante9, empresa));
		assertTrue (evento.agregarParticipante(participante10, empresa));

		// VALIDACION
		Double ve=250000.00;
		Double vo=evento.calcularPrecioTaller();
		
		assertEquals(ve,vo,.01);
		

	}

	@Test // #8
	public void dadoQueExisteUnaEmpresaConEventos3ConferenciasObtengoUnaListaCon3Conferencias() {

	}

	@Test // #9
	public void dadoQueExisteUnaEmpresaConEventosCuandoConsultoLosParticipantesDeConferenciasObtengoUnMapaConLasConferenciasComoClaveYUnaColeccionDeParticipantesPorConferenciaOrdenadaPorApellido() {

	}

}
