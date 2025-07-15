 package logica;

import java.util.ArrayList;
import java.util.Date;

public abstract class Persona {

	protected String codigo, nombre, apellido, sexo, passwd;
	protected Date fechaNacimiento;
	protected String cedula, telefono, correoElectronico, provincia, direccion, dispHorarios;
	protected boolean dispMudarse; // true: Si, false: No
	protected boolean licencia; // true: Si, false: NO
	protected String tipoEmpleado; // tiempo completo, medio tiempo, freelancer (contratista)
	protected String modalidad;
	protected boolean estadoEmpleado; // true: Empleado, false: desempleado
	protected ArrayList<Solicitud> misSolicitudes;
	public Persona(String codigo, String nombre, String apellido, String sexo, String passwd, Date fechaNacimiento,
			String cedula, String telefono, String correoElectronico, String provincia, String direccion,
			String dispHorarios, boolean dispMudarse, boolean licencia, String tipoEmpleado, String modalidad,
			boolean estadoEmpleado, ArrayList<Solicitud> misSolicitudes) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.passwd = passwd;
		this.fechaNacimiento = fechaNacimiento;
		this.cedula = cedula;
		this.telefono = telefono;
		this.correoElectronico = correoElectronico;
		this.provincia = provincia;
		this.direccion = direccion;
		this.dispHorarios = dispHorarios;
		this.dispMudarse = dispMudarse;
		this.licencia = licencia;
		this.tipoEmpleado = tipoEmpleado;
		this.modalidad = modalidad;
		this.estadoEmpleado = estadoEmpleado;
		this.misSolicitudes = new ArrayList<>();
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
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
	public String getTipoEmpleado() {
		return tipoEmpleado;
	}
	public void setTipoEmpleado(String tipoEmpleado) {
		this.tipoEmpleado = tipoEmpleado;
	}
	public String getModalidad() {
		return modalidad;
	}
	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}
	public boolean isEstadoEmpleado() {
		return estadoEmpleado;
	}
	public void setEstadoEmpleado(boolean estadoEmpleado) {
		this.estadoEmpleado = estadoEmpleado;
	}
	public ArrayList<Solicitud> getMisSolicitudes() {
		return misSolicitudes;
	}
	public void setMisSolicitudes(ArrayList<Solicitud> misSolicitudes) {
		this.misSolicitudes = misSolicitudes;
	}
	
	//metodos aqui debajo
	//insertar solicitud
	public void insertarSolicitud(Solicitud soli) {
		misSolicitudes.add(soli);
	}
	//validar que la persona no contenga una oferta previamente registrada en una solicitud
	public boolean contieneOferta(Oferta oferta) {
		boolean valido = true;
		if(!misSolicitudes.isEmpty()) {
			
		for (Solicitud solicitud : misSolicitudes) {
			if(solicitud.getOfertaInteres() == oferta) {
				valido = false;
			}
		}

		}
		return valido;
		
	}

}