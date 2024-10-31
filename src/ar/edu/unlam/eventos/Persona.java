package ar.edu.unlam.eventos;

import java.util.Objects;

public abstract class Persona implements Comparable<Integer>{
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
	public abstract Boolean  add(Persona cliente) ;
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
	public int compareTo(Integer o) {		
		return this.dni.compareTo(o);
	}
	@Override
	public String toString() {
		return "Persona [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + "]";
	}
	
	

}
