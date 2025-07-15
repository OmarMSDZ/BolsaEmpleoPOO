package logica;

import java.util.ArrayList;
import java.util.Date;

public class Obrero extends Persona {

	private boolean ventas;
	private boolean mecanica;
	private boolean ofimatica;
	private boolean seguridad;
	private boolean mantenimiento;
	private boolean conduccion;
	private boolean limpieza;
	public Obrero(String codigo, String nombre, String passwd, String telefono, String correoElectronico,
			String provincia, String municipio, String direccion, String apellidos, String sexo, Date fechaNacimiento,
			String cedula, boolean estadoEmpleado, ArrayList<Solicitud> misSolicitudes, boolean ventas,
			boolean mecanica, boolean ofimatica, boolean seguridad, boolean mantenimiento, boolean conduccion,
			boolean limpieza) {
		super(codigo, nombre, passwd, telefono, correoElectronico, provincia, municipio, direccion, apellidos, sexo,
				fechaNacimiento, cedula, estadoEmpleado, misSolicitudes);
		this.ventas = ventas;
		this.mecanica = mecanica;
		this.ofimatica = ofimatica;
		this.seguridad = seguridad;
		this.mantenimiento = mantenimiento;
		this.conduccion = conduccion;
		this.limpieza = limpieza;
	}
	public boolean isVentas() {
		return ventas;
	}
	public void setVentas(boolean ventas) {
		this.ventas = ventas;
	}
	public boolean isMecanica() {
		return mecanica;
	}
	public void setMecanica(boolean mecanica) {
		this.mecanica = mecanica;
	}
	public boolean isOfimatica() {
		return ofimatica;
	}
	public void setOfimatica(boolean ofimatica) {
		this.ofimatica = ofimatica;
	}
	public boolean isSeguridad() {
		return seguridad;
	}
	public void setSeguridad(boolean seguridad) {
		this.seguridad = seguridad;
	}
	public boolean isMantenimiento() {
		return mantenimiento;
	}
	public void setMantenimiento(boolean mantenimiento) {
		this.mantenimiento = mantenimiento;
	}
	public boolean isConduccion() {
		return conduccion;
	}
	public void setConduccion(boolean conduccion) {
		this.conduccion = conduccion;
	}
	public boolean isLimpieza() {
		return limpieza;
	}
	public void setLimpieza(boolean limpieza) {
		this.limpieza = limpieza;
	}
	
	

	 

}
