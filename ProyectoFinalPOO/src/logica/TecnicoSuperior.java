package logica;

import java.util.Date;

public class TecnicoSuperior extends Persona {

	private String tecnico;
	private int anniosExperiencia;

	public TecnicoSuperior(String codigo, String nombre, String passwd, String telefono, String correoElectronico,
			String provincia, String municipio, String direccion, String apellidos, String sexo, Date fechaNacimiento,
			String cedula, boolean estadoEmpleado, String tecnico, int anniosExperiencia) {
		super(codigo, nombre, passwd, telefono, correoElectronico, provincia, municipio, direccion, apellidos, sexo,
				fechaNacimiento, cedula, estadoEmpleado);
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
