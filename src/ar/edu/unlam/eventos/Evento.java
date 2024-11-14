package ar.edu.unlam.eventos;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import ar.edu.unlam.eventos.enums.Sala;
import ar.edu.unlam.eventos.exceptions.CupoLlenoException;
import ar.edu.unlam.eventos.exceptions.ParticipanteNoEsClienteException;
import ar.edu.unlam.eventos.interfaces.Participante;

public abstract class Evento implements Comparable<Evento> {
	private String codigoEvento;
	private LocalDate fechaEvento;
	private String nombreEvento;
	private Sala sala;
	private Persona expositor;
	private Set <Participante>participantes;

	public Evento(String codigoEvento, LocalDate fechaEvento, String nombreEvento, Sala sala, Persona expositor) {
		this.codigoEvento=codigoEvento;
		this.fechaEvento=fechaEvento;
		this.nombreEvento=nombreEvento;
		this.sala=sala;
		this.expositor=expositor;
		this.participantes=new TreeSet<>();
	}

	public String getCodigoEvento() {
		return codigoEvento;
	}

	public void setCodigoEvento(String codigoEvento) {
		this.codigoEvento = codigoEvento;
	}

	public LocalDate getFechaEvento() {
		return fechaEvento;
	}

	public void setFechaEvento(LocalDate fechaEvento) {
		this.fechaEvento = fechaEvento;
	}

	public String getNombreEvento() {
		return nombreEvento;
	}

	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Persona getExpositor() {
		return expositor;
	}

	public void setExpositor(Persona expositor) {
		this.expositor = expositor;
	}

	public Set<Participante> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(Set<Participante> participantes) {
		this.participantes = participantes;
	}
	public abstract boolean agregarParticipante(Participante participante) throws CupoLlenoException, ParticipanteNoEsClienteException;

	@Override
	public int hashCode() {
		return Objects.hash(codigoEvento);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		return Objects.equals(codigoEvento, other.codigoEvento);
	}

	@Override
	public int compareTo(Evento evento) {
		// TODO Auto-generated method stub
		return this.getCodigoEvento().compareTo(evento.getCodigoEvento());
	}

	public abstract boolean buscarClienteEnEventoPorParticipante(Participante participante) throws ParticipanteNoEsClienteException;

	


}
