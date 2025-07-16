package logica;

import java.util.Date;

public class Solicitud {

	private String codigo;
	private Persona solicitante;
	private String dispHorarios;
	private boolean dispMudarse;
	private boolean licencia;
	private String tipoEmpleo;
	private String modalidad;
	private float salarioDeseado;
	private Date fechaSolicitud;
	private String estadoSolicitud;
	public Solicitud(String codigo, Persona solicitante, String dispHorarios, boolean dispMudarse, boolean licencia,
			String tipoEmpleado, String modalidad, float salarioDeseado, Date fechaSolicitud, String estadoSolicitud) {
		super();
		this.codigo = codigo;
		this.solicitante = solicitante;
		this.dispHorarios = dispHorarios;
		this.dispMudarse = dispMudarse;
		this.licencia = licencia;
		this.tipoEmpleo = tipoEmpleado;
		this.modalidad = modalidad;
		this.salarioDeseado = salarioDeseado;
		this.fechaSolicitud = fechaSolicitud;
		this.estadoSolicitud = estadoSolicitud;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Persona getSolicitante() {
		return solicitante;
	}
	public void setSolicitante(Persona solicitante) {
		this.solicitante = solicitante;
	}
	public String getDispHorarios() {
		return dispHorarios;
	}
	public void setDispHorarios(String dispHorarios) {
		this.dispHorarios = dispHorarios;
	}
	public boolean isDispMudarse() {
		return dispMudarse;
	}
	public void setDispMudarse(boolean dispMudarse) {
		this.dispMudarse = dispMudarse;
	}
	public boolean isLicencia() {
		return licencia;
	}
	public void setLicencia(boolean licencia) {
		this.licencia = licencia;
	}
	public String getTipoEmpleo() {
		return tipoEmpleo;
	}
	public void setTipoEmpleo(String tipoEmpleado) {
		this.tipoEmpleo = tipoEmpleado;
	}
	public String getModalidad() {
		return modalidad;
	}
	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}
	public float getSalarioDeseado() {
		return salarioDeseado;
	}
	public void setSalarioDeseado(float salarioDeseado) {
		this.salarioDeseado = salarioDeseado;
	}
	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}
	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	public String getEstadoSolicitud() {
		return estadoSolicitud;
	}
	public void setEstadoSolicitud(String estadoSolicitud) {
		this.estadoSolicitud = estadoSolicitud;
	}
	
	
}
