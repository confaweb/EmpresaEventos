package ar.edu.unlam.eventos;

import java.util.Objects;

import ar.edu.unlam.eventos.interfaces.Cliente;
import ar.edu.unlam.eventos.interfaces.Expositor;
import ar.edu.unlam.eventos.interfaces.Participante;

public  class Persona implements Comparable<Persona>,Cliente,Expositor,Participante{
	private Integer dni;
	private String nombre;
	private String apellido;
	public Persona(Integer dni,String nombre,String apellido) {
		this.dni=dni;
		this.nombre=nombre;
		this.apellido=apellido;
	}
	public Integer getDni() {
		return dni;
	}
	public void setDni(Integer dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Boolean  add(Persona cliente) {
		return null;
	}
	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(dni, other.dni);
	}
	
	@Override
	public int compareTo(Persona o) {		
		return this.apellido.compareTo(o.getApellido());
	}
	@Override
	public String toString() {
		return "Persona [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + "]";
	}
	
	

}
