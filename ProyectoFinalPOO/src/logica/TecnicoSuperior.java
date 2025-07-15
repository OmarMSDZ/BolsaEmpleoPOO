package logica;

import java.util.ArrayList;
import java.util.Date;

public class TecnicoSuperior extends Persona {

	private String tecnico;
	private int anniosExperiencia;
	public TecnicoSuperior(String codigo, String nombre, String apellido, String sexo, String passwd,
			Date fechaNacimiento, String cedula, String telefono, String correoElectronico, String provincia,
			String direccion, String dispHorarios, boolean dispMudarse, boolean licencia, String tipoEmpleado,
			String modalidad, boolean estadoEmpleado, ArrayList<Solicitud> misSolicitudes, String tecnico,
			int anniosExperiencia) {
		super(codigo, nombre, apellido, sexo, passwd, fechaNacimiento, cedula, telefono, correoElectronico, provincia,
				direccion, dispHorarios, dispMudarse, licencia, tipoEmpleado, modalidad, estadoEmpleado,
				misSolicitudes);
		this.tecnico = tecnico;
		this.anniosExperiencia = anniosExperiencia;
	}
	public String getTecnico() {
		return tecnico;
	}
	public void setTecnico(String tecnico) {
		this.tecnico = tecnico;
	}
	public int getAnniosExperiencia() {
		return anniosExperiencia;
	}
	public void setAnniosExperiencia(int anniosExperiencia) {
		this.anniosExperiencia = anniosExperiencia;
	}

	

}
