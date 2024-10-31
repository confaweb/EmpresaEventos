package ar.edu.unlam.pruebas;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.edu.unlam.eventos.Cliente;
import ar.edu.unlam.eventos.Empresa;

public class PruebaEventos {

	@Test
	public void dadoQueExisteUnaEmpresaCuandoAgregoUnClienteObtengoUnResultadoExitoso(){
		//INICIO
		Empresa empresa;
		Cliente cliente;
		Integer cuit =110202023,dni = 111111;
		String nombreEmpresa="Janos",nombre="jose",apellido="Lopez";
		//PREPARACION
		cliente= new Cliente(dni,nombre,apellido);
		empresa =new Empresa(cuit,nombreEmpresa);
		empresa.agregarCliente((Persona)cliente);
		//VALIDACION
		assertTrue(empresa.agregarCliente((Persona)cliente));
	}

}
