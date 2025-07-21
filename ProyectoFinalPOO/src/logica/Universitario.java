package logica;

import java.util.Date;

public class Universitario extends Persona {

	private String carrera;

	public Universitario(String codigo, String nombre, String passwd, String telefono, String correoElectronico,
			String provincia, String municipio, String direccion, String apellidos, String sexo, Date fechaNacimiento,
			String cedula, boolean estadoEmpleado, String carrera) {
		super(codigo, nombre, passwd, telefono, correoElectronico, provincia, municipio, direccion, apellidos, sexo,
				fechaNacimiento, cedula, estadoEmpleado);
		this.carrera = carrera;
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

}
