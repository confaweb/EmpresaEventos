package ar.edu.unlam.eventos;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import ar.edu.unlam.eventos.exceptions.EventoDuplicadoException;
import ar.edu.unlam.eventos.exceptions.EventoInexistenteException;
import ar.edu.unlam.eventos.interfaces.Cliente;

public class Empresa {

	private Integer cuit;
	private String nommbreEmpresa;
	private Set<Persona> clientes;
	private Set<Evento> listadoEventos;

	public Empresa(Integer cuit, String nombreEmpresa) {
		this.cuit = cuit;
		this.nommbreEmpresa = nombreEmpresa;
		clientes = new TreeSet<Persona>();
		listadoEventos=new HashSet<>();
	}

	public Integer getCuit() {
		return cuit;
	}

	public void setCuit(Integer cuit) {
		this.cuit = cuit;
	}

	public String getNommbreEmpresa() {
		return nommbreEmpresa;
	}

	public void setNommbreEmpresa(String nommbreEmpresa) {
		this.nommbreEmpresa = nommbreEmpresa;
	}

	public Boolean agregarCliente(Persona cliente) {
		Boolean clienteAgregado = false;

		clienteAgregado = clientes.add(cliente);

		return clienteAgregado;
	}
	public Evento buscarEventoPorCodigo(String codigoEvento) throws EventoInexistenteException {
		for (Evento evento:listadoEventos) {
			if (evento.getCodigoEvento().equals(codigoEvento))
				return evento;
		}
		throw new EventoInexistenteException("Codigo Inexistente");
	}

	@Override
	public int hashCode() {
		return Objects.hash(cuit);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empresa other = (Empresa) obj;
		return Objects.equals(cuit, other.cuit);
	}

	@Override
	public String toString() {
		return "Empresa [cuit=" + cuit + ", nommbreEmpresa=" + nommbreEmpresa + "]";
	}

	public boolean agregarEvento(Evento evento) throws EventoDuplicadoException {
		
		Boolean eventoAgregado=listadoEventos.add(evento);
		if(!eventoAgregado)
			throw new EventoDuplicadoException("El Evento ya existe");
		return eventoAgregado;
	}

}
