package ar.edu.unlam.pruebas;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
import ar.edu.unlam.eventos.interfaces.OrdenadosPorApellido;

import ar.edu.unlam.eventos.interfaces.Taller;

public class PruebaEventos {

	@Test // #1
	public void dadoQueExisteUnaEmpresaCuandoAgregoUnClienteObtengoUnResultadoExitoso()
			throws ClienteYaExisteEnEventoException {
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
	public void dadoQueExisteUnaEmpresaConEventosCuandoVerificoSiUnClienteSeEncuentraEntreLosParticipantesDeUnEventoPorClienteYExisteObtengoUnResultadoPositivo()
			throws EventoDuplicadoException, EventoInexistenteException, CupoLlenoException,
			ParticipanteNoEsClienteException, ParticipanteNoPerteneceAlEventoException,
			ClienteYaExisteEnEventoException {
		// INICIO
		Empresa empresa;
		Cliente cliente;
		Persona participante;
		Conferencia evento;
		Integer cuit = 110202023, dni = 111111, cupoParticipantes = 50;
		String nombreEmpresa = "Janos", nombre = "jose", apellido = "Lopez", codigoEvento = "Conf001",
				nombreEvento = "Marcianos";
		LocalDate fechaEvento = LocalDate.of(2024, 12, 12);
		Sala sala = Sala.GRANDE;
		// PREPARACION
		cliente = new Persona(dni, nombre, apellido);
		participante = new Persona(dni, nombre, apellido);
		evento = new Evento(codigoEvento, fechaEvento, nombreEvento, sala, (Persona) participante);
		empresa = new Empresa(cuit, nombreEmpresa);

		evento.setCupoParticipantesConferencia(cupoParticipantes);
		assertTrue(empresa.agregarEvento((Evento) evento));
		assertTrue(empresa.agregarCliente((Persona) cliente));
		assertTrue(evento.agregarParticipante(participante, empresa));

		// VALIDACION
		assertTrue(evento.buscarClienteEnEventoPorParticipante(participante));
		assertTrue(empresa.getClientes().contains(participante));
		assertTrue(((Evento) evento).getParticipantes().contains(cliente));
	}

	@Test // #5
	(expected = ClienteYaExisteEnEventoException.class)
	public void dadoQueExisteUnaEmpresaConEventosCuandoAgregoUnClienteAUnEventoDondeExisteElClienteObtengoUnaClienteExistenteEnEventoException()
			throws CupoLlenoException, ParticipanteNoEsClienteException, EventoDuplicadoException,
			ClienteYaExisteEnEventoException {
		Empresa empresa;
		Cliente cliente;
		Persona participante;
		Conferencia evento;
		Integer cuit = 110202023, dni = 111111, cupoParticipantes = 50;
		String nombreEmpresa = "Janos", nombre = "jose", apellido = "Lopez", codigoEvento = "Conf001",
				nombreEvento = "Marcianos";
		LocalDate fechaEvento = LocalDate.of(2024, 12, 12);
		Sala sala = Sala.GRANDE;
		// PREPARACION
		cliente = new Persona(dni, nombre, apellido);
		participante = new Persona(dni, nombre, apellido);
		evento = new Evento(codigoEvento, fechaEvento, nombreEvento, sala, (Persona) participante);
		empresa = new Empresa(cuit, nombreEmpresa);

		evento.setCupoParticipantesConferencia(cupoParticipantes);
		assertTrue(empresa.agregarEvento((Evento) evento));
		assertTrue(empresa.agregarCliente((Persona) cliente));
		assertTrue(evento.agregarParticipante(participante, empresa));

		// VALIDACION
		evento.agregarParticipante(participante, empresa);
	}

	@Test // #6
	public void dadoQueExisteUnaEmpresaConEventosCuandoAgregoUnClienteAUnTallerSinCupoDondeNoExisteElClienteObtengoUnResultadoFallido()
			throws EventoDuplicadoException, CupoLlenoException, ParticipanteNoEsClienteException,
			ClienteYaExisteEnEventoException {
		Empresa empresa;
		Cliente cliente;
		Persona participante, participante2, participante3, participante4, participante5;
		Taller evento;
		Integer cuit = 110202023, dni = 111111, dni2 = 222222, dni3 = 333333, dni4 = 444444, dni5 = 555555,
				cupoParticipantes = 4;
		String nombreEmpresa = "Janos", nombre = "jose", apellido = "Lopez", apellido2 = "Perez", apellido3 = "DePaul",
				apellido4 = "Martinez", apellido5 = "Sosa", codigoEvento = "Conf001", nombreEvento = "Marcianos";
		LocalDate fechaEvento = LocalDate.of(2024, 12, 12);
		Sala sala = Sala.GRANDE;
		// PREPARACION
		cliente = new Persona(dni, nombre, apellido);
		participante = new Persona(dni, nombre, apellido);
		participante2 = new Persona(dni2, nombre, apellido2);
		participante3 = new Persona(dni3, nombre, apellido3);
		participante4 = new Persona(dni4, nombre, apellido4);
		participante5 = new Persona(dni5, nombre, apellido5);
		evento = new Evento(codigoEvento, fechaEvento, nombreEvento, sala, (Persona) participante);
		empresa = new Empresa(cuit, nombreEmpresa);

		evento.setCupoParticipantesTaller(cupoParticipantes);

		assertTrue(empresa.agregarEvento((Evento) evento));
		assertTrue(empresa.agregarCliente((Persona) participante));
		assertTrue(empresa.agregarCliente((Persona) participante2));
		assertTrue(empresa.agregarCliente((Persona) participante3));
		assertTrue(empresa.agregarCliente((Persona) participante4));
		assertTrue(empresa.agregarCliente((Persona) participante5));
		assertTrue(evento.agregarParticipante(participante, empresa));
		assertTrue(evento.agregarParticipante(participante2, empresa));
		assertTrue(evento.agregarParticipante(participante3, empresa));
		assertTrue(evento.agregarParticipante(participante4, empresa));

		// VALIDACION
		assertFalse(evento.agregarParticipante(participante5, empresa));

	}

	@Test // #7
	public void dadoQueExisteUnaEmpresaConEventosCuandoConsultoLaRecaudacionTodalDeUnEventoTallerCon10ParticipantesRecibo250000()
			throws EventoDuplicadoException, CupoLlenoException, ParticipanteNoEsClienteException,
			ClienteYaExisteEnEventoException {
		Empresa empresa;
		Cliente cliente;
		Persona participante, participante2, participante3, participante4, participante5, participante6, participante7,
				participante8, participante9, participante10;
		Taller evento;
		Integer cuit = 110202023, dni = 111111, dni2 = 222222, dni3 = 333333, dni4 = 444444, dni5 = 555555,
				dni6 = 666666, dni7 = 777777, dni8 = 888888, dni9 = 999999, dni10 = 101010, cupoParticipantes = 20;
		String nombreEmpresa = "Janos", nombre = "jose", apellido = "Lopez", apellido2 = "Perez", apellido3 = "DePaul",
				apellido4 = "Martinez", apellido5 = "Sosa", codigoEvento = "Conf001", nombreEvento = "Marcianos",
				apellido6 = "Marx", apellido7 = "Engels", apellido8 = "Castro", apellido9 = "Guevara",
				apellido10 = "Cienfuegos";
		LocalDate fechaEvento = LocalDate.of(2024, 12, 12);
		Sala sala = Sala.GRANDE;
		double precio = 25000;
		// PREPARACION

		cliente = new Persona(dni, nombre, apellido);

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
		assertTrue(empresa.agregarCliente((Persona) participante));
		assertTrue(empresa.agregarCliente((Persona) participante2));
		assertTrue(empresa.agregarCliente((Persona) participante3));
		assertTrue(empresa.agregarCliente((Persona) participante4));
		assertTrue(empresa.agregarCliente((Persona) participante5));
		assertTrue(empresa.agregarCliente((Persona) participante6));
		assertTrue(empresa.agregarCliente((Persona) participante7));
		assertTrue(empresa.agregarCliente((Persona) participante8));
		assertTrue(empresa.agregarCliente((Persona) participante9));
		assertTrue(empresa.agregarCliente((Persona) participante10));
		assertTrue(evento.agregarParticipante(participante, empresa));
		assertTrue(evento.agregarParticipante(participante2, empresa));
		assertTrue(evento.agregarParticipante(participante3, empresa));
		assertTrue(evento.agregarParticipante(participante4, empresa));
		assertTrue(evento.agregarParticipante(participante5, empresa));
		assertTrue(evento.agregarParticipante(participante6, empresa));
		assertTrue(evento.agregarParticipante(participante7, empresa));
		assertTrue(evento.agregarParticipante(participante8, empresa));
		assertTrue(evento.agregarParticipante(participante9, empresa));
		assertTrue(evento.agregarParticipante(participante10, empresa));

		// VALIDACION
		Double ve = 250000.00;
		Double vo = evento.calcularPrecioTaller();

		assertEquals(ve, vo, .01);

	}

	@Test // #8
	public void dadoQueExisteUnaEmpresaConEventos3ConferenciasObtengoUnaListaCon3Conferencias()
			throws EventoDuplicadoException, CupoLlenoException, ParticipanteNoEsClienteException,
			ClienteYaExisteEnEventoException {
		Empresa empresa;
		Cliente cliente;
		Persona participante;
		Conferencia evento, evento2, evento3;
		Integer cuit = 110202023, dni = 111111, cupoParticipantes = 50;
		String nombreEmpresa = "Janos", nombre = "jose", apellido = "Lopez", codigoEvento = "Conf001",
				codigoEvento2 = "Conf002", codigoEvento3 = "Conf003", nombreEvento = "Marcianos",
				nombreEvento2 = "Marcianos2", nombreEvento3 = "Marcianos3";
		LocalDate fechaEvento = LocalDate.of(2024, 12, 12), fechaEvento2 = LocalDate.of(2024, 12, 15),
				fechaEvento3 = LocalDate.of(2024, 10, 17);
		Sala sala = Sala.GRANDE;
		// PREPARACION
		cliente = new Persona(dni, nombre, apellido);
		participante = new Persona(dni, nombre, apellido);
		evento = new Evento(codigoEvento, fechaEvento, nombreEvento, sala, (Persona) participante);
		evento2 = new Evento(codigoEvento2, fechaEvento2, nombreEvento2, sala, (Persona) participante);
		evento3 = new Evento(codigoEvento3, fechaEvento3, nombreEvento3, sala, (Persona) participante);
		empresa = new Empresa(cuit, nombreEmpresa);

		evento.setCupoParticipantesConferencia(cupoParticipantes);
		assertTrue(empresa.agregarEvento((Evento) evento));
		assertTrue(empresa.agregarEvento((Evento) evento2));
		assertTrue(empresa.agregarEvento((Evento) evento3));

		// VALIDACION
		assertEquals(3, empresa.getListadoEventos().size());

	}

	@Test // #9
	public void dadoQueExisteUnaEmpresaConEventosCuandoConsultoLosParticipantesDeConferenciasObtengoUnMapaConLasConferenciasComoClaveYUnaColeccionDeParticipantesPorConferenciaOrdenadaPorApellido()
			throws EventoDuplicadoException, CupoLlenoException, ParticipanteNoEsClienteException,
			ClienteYaExisteEnEventoException {
		Empresa empresa;
		Cliente cliente;
		Persona participante, participante2, participante3, participante4, participante5, participante6, participante7,
				participante8, participante9, participante10;
		Conferencia evento, evento2, evento3;
		Integer cuit = 110202023, dni = 111111, dni2 = 222222, dni3 = 333333, dni4 = 444444, dni5 = 555555,
				dni6 = 666666, dni7 = 777777, dni8 = 888888, dni9 = 999999, dni10 = 101010, cupoParticipantes = 20;
		String nombreEmpresa = "Janos", nombre = "jose", apellido = "Lopez", apellido2 = "Perez", apellido3 = "DePaul",
				apellido4 = "Martinez", apellido5 = "Sosa", apellido6 = "Marx", apellido7 = "Engels",
				apellido8 = "Castro", apellido9 = "Guevara", apellido10 = "Cienfuegos", codigoEvento = "Conf001",
				codigoEvento2 = "Conf002", codigoEvento3 = "Conf003", nombreEvento = "Marcianos",
				nombreEvento2 = "Marcianos2", nombreEvento3 = "Marcianos3";
		LocalDate fechaEvento = LocalDate.of(2024, 12, 12), fechaEvento2 = LocalDate.of(2024, 12, 15),
				fechaEvento3 = LocalDate.of(2024, 10, 17);
		Sala sala = Sala.GRANDE;
		// PREPARACION
		cliente = new Persona(dni, nombre, apellido);
		participante = new Persona(dni, nombre, apellido);
		evento = new Evento(codigoEvento, fechaEvento, nombreEvento, sala, (Persona) participante);
		evento2 = new Evento(codigoEvento2, fechaEvento2, nombreEvento2, sala, (Persona) participante);
		evento3 = new Evento(codigoEvento3, fechaEvento3, nombreEvento3, sala, (Persona) participante);
		empresa = new Empresa(cuit, nombreEmpresa);

		evento.setCupoParticipantesConferencia(cupoParticipantes);
		evento2.setCupoParticipantesConferencia(cupoParticipantes);
		evento3.setCupoParticipantesConferencia(cupoParticipantes);

		participante2 = new Persona(dni2, nombre, apellido2);
		participante3 = new Persona(dni3, nombre, apellido3);
		participante4 = new Persona(dni4, nombre, apellido4);
		participante5 = new Persona(dni5, nombre, apellido5);
		participante6 = new Persona(dni6, nombre, apellido6);
		participante7 = new Persona(dni7, nombre, apellido7);
		participante8 = new Persona(dni8, nombre, apellido8);
		participante9 = new Persona(dni9, nombre, apellido9);
		participante10 = new Persona(dni10, nombre, apellido10);
		assertTrue(empresa.agregarEvento((Evento) evento));
		assertTrue(empresa.agregarEvento((Evento) evento2));
		assertTrue(empresa.agregarEvento((Evento) evento3));

		assertTrue(empresa.agregarCliente((Persona) participante));
		assertTrue(empresa.agregarCliente((Persona) participante2));
		assertTrue(empresa.agregarCliente((Persona) participante3));
		assertTrue(empresa.agregarCliente((Persona) participante4));
		assertTrue(empresa.agregarCliente((Persona) participante5));
		assertTrue(empresa.agregarCliente((Persona) participante6));
		assertTrue(empresa.agregarCliente((Persona) participante7));
		assertTrue(empresa.agregarCliente((Persona) participante8));
		assertTrue(empresa.agregarCliente((Persona) participante9));
		assertTrue(empresa.agregarCliente((Persona) participante10));
		assertTrue(evento.agregarParticipante(participante, empresa));
		assertTrue(evento.agregarParticipante(participante2, empresa));
		assertTrue(evento.agregarParticipante(participante3, empresa));
		assertTrue(evento2.agregarParticipante(participante4, empresa));
		assertTrue(evento2.agregarParticipante(participante5, empresa));
		assertTrue(evento2.agregarParticipante(participante6, empresa));
		assertTrue(evento3.agregarParticipante(participante7, empresa));
		assertTrue(evento3.agregarParticipante(participante8, empresa));
		assertTrue(evento3.agregarParticipante(participante9, empresa));
		assertTrue(evento3.agregarParticipante(participante10, empresa));
		empresa.listarPrticipantesPorAperllidoPorEvento((Evento) evento);
		empresa.listarPrticipantesPorAperllidoPorEvento((Evento) evento2);
		empresa.listarPrticipantesPorAperllidoPorEvento((Evento) evento3);
		// Evento
		Set<Persona> listaOrdenadaPorApellido = new TreeSet<Persona>(new OrdenadosPorApellido());
		ordenarPorApellido((Evento) evento, listaOrdenadaPorApellido);

		List<Persona> arrayEvento = new ArrayList<Persona>();
		arrayEvento.addAll(listaOrdenadaPorApellido);
		
		// Evento2
		Set<Persona> listaOrdenadaPorApellido2 = new TreeSet<Persona>(new OrdenadosPorApellido());
		ordenarPorApellido((Evento) evento2, listaOrdenadaPorApellido2);

		List<Persona> arrayEvento2 = new ArrayList<Persona>();
		arrayEvento2.addAll(listaOrdenadaPorApellido2);

		// Evento3
		Set<Persona> listaOrdenadaPorApellido3 = new TreeSet<Persona>(new OrdenadosPorApellido());
		ordenarPorApellido((Evento) evento3, listaOrdenadaPorApellido3);

		List<Persona> arrayEvento3 = new ArrayList<Persona>();
		arrayEvento3.addAll(listaOrdenadaPorApellido3);

		// VALIDACION
		assertEquals(arrayEvento.get(0), participante3);// Apellidos:p1"Lopez",p2"Perez",p3"DePaul"
		assertEquals(3, arrayEvento.size());
		
		assertEquals(arrayEvento2.get(0), participante4);// Apellidos:p4"Martinez",p5"Sosa",p6"Marx"
		assertEquals(3, arrayEvento2.size());
		
		assertEquals(arrayEvento3.get(0), participante8);// Apellidos:p7"Engels",p8"Castro",p9"Guevara",p10"Cienfuegos"
		assertEquals(4, arrayEvento3.size());

	}

	private void ordenarPorApellido(Evento evento, Set<Persona> listaOrdenadaPorApellido) {
		listaOrdenadaPorApellido.addAll(evento.getParticipantes());

	}

	private void pasarParticipantesDeSetAArray(Evento evento, List<Persona> arrayEvento) {

	}

}
