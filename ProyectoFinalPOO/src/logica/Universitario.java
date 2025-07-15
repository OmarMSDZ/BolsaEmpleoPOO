package logica;

import java.util.ArrayList;
import java.util.Date;

public class Universitario extends Persona {

	private String carrera;

	public Universitario(String codigo, String nombre, String apellido, String sexo, String passwd,
			Date fechaNacimiento, String cedula, String telefono, String correoElectronico, String provincia,
			String direccion, String dispHorarios, boolean dispMudarse, boolean licencia, String tipoEmpleado,
			String modalidad, boolean estadoEmpleado, ArrayList<Solicitud> misSolicitudes, String carrera) {
		super(codigo, nombre, apellido, sexo, passwd, fechaNacimiento, cedula, telefono, correoElectronico, provincia,
				direccion, dispHorarios, dispMudarse, licencia, tipoEmpleado, modalidad, estadoEmpleado,
				misSolicitudes);
		this.carrera = carrera;
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	

}
