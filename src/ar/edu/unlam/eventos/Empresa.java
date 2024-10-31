package ar.edu.unlam.eventos;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Empresa {

	private Integer cuit;
	private String nommbreEmpresa;
	private Set<Persona> clientes;

	public Empresa(Integer cuit, String nombreEmpresa) {
		this.cuit=cuit;
		this.nommbreEmpresa=nombreEmpresa;
		clientes =new TreeSet<Persona>();
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

	public Boolean agregarCliente(Persona cliente) {
		Boolean clienteAgregado=false;
		
		clienteAgregado=clientes.add(cliente);
		
		return clienteAgregado;
	}

}
