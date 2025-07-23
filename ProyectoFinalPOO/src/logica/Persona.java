package logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Persona extends Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String apellidos;
	protected String sexo;
	protected Date fechaNacimiento;
	protected String cedula;
	protected boolean estadoEmpleado;
	protected ArrayList<Solicitud> misSolicitudes;

	public Persona(String codigo, String nombre, String passwd, String telefono, String correoElectronico,
			String provincia, String municipio, String direccion, boolean estado, String apellidos, String sexo,
			Date fechaNacimiento, String cedula, boolean estadoEmpleado, ArrayList<Solicitud> misSolicitudes) {
		super(codigo, nombre, passwd, telefono, correoElectronico, provincia, municipio, direccion, estado);
		this.apellidos = apellidos;
		this.sexo = sexo;
		this.fechaNacimiento = fechaNacimiento;
		this.cedula = cedula;
		this.estadoEmpleado = estadoEmpleado;
		this.misSolicitudes = misSolicitudes;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
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

	// Métodos aquí debajo
	public void insertarSolicitud(Solicitud soli) {
		// TODO Auto-generated method stub
		misSolicitudes.add(soli);
	}

	public void removerSolicitud(Solicitud soli) {
		misSolicitudes.remove(soli);
	}

}