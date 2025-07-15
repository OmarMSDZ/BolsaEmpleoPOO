package logica;

import java.util.ArrayList;
import java.util.Date;

public class Obrero extends Persona {

	private ArrayList<String> misHabilidades;

	public Obrero(String codigo, String nombre, String apellido, String sexo, String passwd, Date fechaNacimiento,
			String cedula, String telefono, String correoElectronico, String provincia, String direccion,
			String dispHorarios, boolean dispMudarse, boolean licencia, String tipoEmpleado, String modalidad,
			boolean estadoEmpleado, ArrayList<Solicitud> misSolicitudes, ArrayList<String> misHabilidades) {
		super(codigo, nombre, apellido, sexo, passwd, fechaNacimiento, cedula, telefono, correoElectronico, provincia,
				direccion, dispHorarios, dispMudarse, licencia, tipoEmpleado, modalidad, estadoEmpleado,
				misSolicitudes);
		this.misHabilidades = misHabilidades;
	}

	public ArrayList<String> getMisHabilidades() {
		return misHabilidades;
	}

	public void setMisHabilidades(ArrayList<String> misHabilidades) {
		this.misHabilidades = misHabilidades;
	}

	 

}
