package logica;

 
import java.util.Date;

public class Oferta {

	private String codigo;
	private String puestoTrab;
	private String descripcion;
	private String modalidad;
	private String tipo;
	private String horario;
	private boolean requiereLicencia; //true: si, false: no requiere
	private boolean requiereMovilidad; //true: si, false: no requiere
	private int aniosExp;
	private String conocimientos;
	
	private String formacion; //si es universitario, tecnico o obrero
	private float salarioEstimado;
	private String provincia;
	private Date fecha;
	private boolean estadoOferta; // True: disponible, False: Ocupada
	private Empresa empReclutadora;
	public Oferta(String codigo, String puestoTrab, String descripcion, String modalidad, String tipo, String horario,
			boolean requiereLicencia, boolean requiereMovilidad, int aniosExp, String conocimientos, String formacion,
			float salarioEstimado, String provincia, Date fecha, boolean estadoOferta, Empresa empReclutadora) {
		super();
		this.codigo = codigo;
		this.puestoTrab = puestoTrab;
		this.descripcion = descripcion;
		this.modalidad = modalidad;
		this.tipo = tipo;
		this.horario = horario;
		this.requiereLicencia = requiereLicencia;
		this.requiereMovilidad = requiereMovilidad;
		this.aniosExp = aniosExp;
		this.conocimientos = conocimientos;
		this.formacion = formacion;
		this.salarioEstimado = salarioEstimado;
		this.provincia = provincia;
		this.fecha = fecha;
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
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
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
	public int getAniosExp() {
		return aniosExp;
	}
	public void setAniosExp(int aniosExp) {
		this.aniosExp = aniosExp;
	}
	public String getConocimientos() {
		return conocimientos;
	}
	public void setConocimientos(String conocimientos) {
		this.conocimientos = conocimientos;
	}
	public String getFormacion() {
		return formacion;
	}
	public void setFormacion(String formacion) {
		this.formacion = formacion;
	}
	public float getSalarioEstimado() {
		return salarioEstimado;
	}
	public void setSalarioEstimado(float salarioEstimado) {
		this.salarioEstimado = salarioEstimado;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
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
	
	
	
	
 
}
