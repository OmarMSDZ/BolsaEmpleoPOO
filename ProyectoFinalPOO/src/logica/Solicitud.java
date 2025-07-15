package logica;

import java.util.Date;

public class Solicitud {

	private String codigo;
	private Date FechaSolicitud;
	private String estadoSolicitud; // Activa, aceptada, pausada, cancelada
	private Persona solicitante;
	private Oferta ofertaInteres;

	public Solicitud(String codigo, Date fechaSolicitud, String estadoSolicitud, Persona solicitante,
			Oferta ofertaInteres) {
		super();
		this.codigo = codigo;
		FechaSolicitud = fechaSolicitud;
		this.estadoSolicitud = estadoSolicitud; 
		this.solicitante = solicitante;
		this.ofertaInteres = ofertaInteres;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Date getFechaSolicitud() {
		return FechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
		FechaSolicitud = fechaSolicitud;
	}

	public String getEstadoSolicitud() {
		return estadoSolicitud;
	}

	public void setEstadoSolicitud(String estadoSolicitud) {
		this.estadoSolicitud = estadoSolicitud;
	}

	public Persona getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Persona solicitante) {
		this.solicitante = solicitante;
	}

	public Oferta getOfertaInteres() {
		return ofertaInteres;
	}

	public void setOfertaInteres(Oferta ofertaInteres) {
		this.ofertaInteres = ofertaInteres;
	}

 

}
