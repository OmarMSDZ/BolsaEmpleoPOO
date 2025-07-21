package logica;

import java.util.Date;

public class Obrero extends Persona {

	private boolean conduccion;
	private boolean electricidad;
	private boolean limpieza;
	private boolean mantenimiento;
	private boolean mecanica;
	private boolean ofimatica;
	private boolean seguridad;
	private boolean ventas;

	public Obrero(String codigo, String nombre, String passwd, String telefono, String correoElectronico,
			String provincia, String municipio, String direccion, String apellidos, String sexo, Date fechaNacimiento,
			String cedula, boolean estadoEmpleado, boolean conduccion, boolean electricidad, boolean limpieza,
			boolean mantenimiento, boolean mecanica, boolean ofimatica, boolean seguridad, boolean ventas) {
		super(codigo, nombre, passwd, telefono, correoElectronico, provincia, municipio, direccion, apellidos, sexo,
				fechaNacimiento, cedula, estadoEmpleado);
		this.conduccion = conduccion;
		this.electricidad = electricidad;
		this.limpieza = limpieza;
		this.mantenimiento = mantenimiento;
		this.mecanica = mecanica;
		this.ofimatica = ofimatica;
		this.seguridad = seguridad;
		this.ventas = ventas;
	}

	public boolean isConduccion() {
		return conduccion;
	}

	public void setConduccion(boolean conduccion) {
		this.conduccion = conduccion;
	}

	public boolean isElectricidad() {
		return electricidad;
	}

	public void setElectricidad(boolean electricidad) {
		this.electricidad = electricidad;
	}

	public boolean isLimpieza() {
		return limpieza;
	}

	public void setLimpieza(boolean limpieza) {
		this.limpieza = limpieza;
	}

	public boolean isMantenimiento() {
		return mantenimiento;
	}

	public void setMantenimiento(boolean mantenimiento) {
		this.mantenimiento = mantenimiento;
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

	public boolean isVentas() {
		return ventas;
	}

	public void setVentas(boolean ventas) {
		this.ventas = ventas;
	}

}
