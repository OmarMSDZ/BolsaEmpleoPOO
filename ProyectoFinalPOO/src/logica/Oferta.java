package logica;

import java.io.Serializable;
import java.util.Date;

public class Oferta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigo, puestoTrab, descripcion, modalidad, tipoEmpleo, horario, nivelEducacion;
	private String area;
	private int aniosExp;
	private float salarioEstimado;
	private boolean requiereLicencia;
	private boolean requiereMovilidad;
	private Date fechaOferta;
	private int cantVacantes;
	private boolean estadoOferta;
	private Empresa empReclutadora;

	public Oferta(String codigo, String puestoTrab, String descripcion, String modalidad, String tipo, String horario,
			String nivelEducacion, String area, int aniosExp, float salarioEstimado, boolean requiereLicencia,
			boolean requiereMovilidad, Date fechaOferta, int cantVacantes, boolean estadoOferta,
			Empresa empReclutadora) {
		super();
		this.codigo = codigo;
		this.puestoTrab = puestoTrab;
		this.descripcion = descripcion;
		this.modalidad = modalidad;
		this.tipoEmpleo = tipo;
		this.horario = horario;
		this.nivelEducacion = nivelEducacion;
		this.area = area;
		this.aniosExp = aniosExp;
		this.salarioEstimado = salarioEstimado;
		this.requiereLicencia = requiereLicencia;
		this.requiereMovilidad = requiereMovilidad;
		this.fechaOferta = fechaOferta;
		this.cantVacantes = cantVacantes;
		this.estadoOferta = estadoOferta;
		this.empReclutadora = empReclutadora;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getPuestoTrab() {
		return puestoTrab;
	}

	public void setPuestoTrab(String puestoTrab) {
		this.puestoTrab = puestoTrab;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	public String getTipo() {
		return tipoEmpleo;
	}

	public void setTipo(String tipo) {
		this.tipoEmpleo = tipo;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getNivelEducacion() {
		return nivelEducacion;
	}

	public void setNivelEducacion(String nivelEducacion) {
		this.nivelEducacion = nivelEducacion;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getAniosExp() {
		return aniosExp;
	}

	public void setAniosExp(int aniosExp) {
		this.aniosExp = aniosExp;
	}

	public float getSalarioEstimado() {
		return salarioEstimado;
	}

	public void setSalarioEstimado(float salarioEstimado) {
		this.salarioEstimado = salarioEstimado;
	}

	public boolean isRequiereLicencia() {
		return requiereLicencia;
	}

	public void setRequiereLicencia(boolean requiereLicencia) {
		this.requiereLicencia = requiereLicencia;
	}

	public boolean isRequiereMovilidad() {
		return requiereMovilidad;
	}

	public void setRequiereMovilidad(boolean requiereMovilidad) {
		this.requiereMovilidad = requiereMovilidad;
	}

	public Date getFechaOferta() {
		return fechaOferta;
	}

	public void setFechaOferta(Date fechaOferta) {
		this.fechaOferta = fechaOferta;
	}

	public int getCantVacantes() {
		return cantVacantes;
	}

	public void setCantVacantes(int cantVacantes) {
		this.cantVacantes = cantVacantes;
	}

	public boolean isEstadoOferta() {
		return estadoOferta;
	}

	public void setEstadoOferta(boolean estadoOferta) {
		this.estadoOferta = estadoOferta;
	}

	public Empresa getEmpReclutadora() {
		return empReclutadora;
	}

	public void setEmpReclutadora(Empresa empReclutadora) {
		this.empReclutadora = empReclutadora;
	}

	public void disminuirVacantesDisp() {
		cantVacantes--;
	}

}
