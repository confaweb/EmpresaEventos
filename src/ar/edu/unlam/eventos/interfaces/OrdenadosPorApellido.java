package ar.edu.unlam.eventos.interfaces;

import java.util.Comparator;

import ar.edu.unlam.eventos.Persona;

public class OrdenadosPorApellido implements Comparator<Persona>{

	@Override
	public int compare(Persona cliente1, Persona cliente2) {
		// TODO Auto-generated method stub
		return cliente1.getApellido().compareTo(cliente2.getApellido());
	}

}
