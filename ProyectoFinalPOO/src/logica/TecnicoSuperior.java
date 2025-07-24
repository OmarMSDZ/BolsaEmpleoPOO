package logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class TecnicoSuperior extends Persona implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tecnico;
	private int aniosExperiencia;

	public TecnicoSuperior(String codigo, String nombre, String passwd, String telefono, String correoElectronico,
			String provincia, String municipio, String direccion, boolean estado, String apellidos, String sexo,
			Date fechaNacimiento, String cedula, boolean estadoEmpleado, String tecnico, int aniosExperiencia) {
		super(codigo, nombre, passwd, telefono, correoElectronico, provincia, municipio, direccion, estado, apellidos,
				sexo, fechaNacimiento, cedula, estadoEmpleado);
		this.tecnico = tecnico;
		this.aniosExperiencia = aniosExperiencia;
	}

	public String getTecnico() {
		return tecnico;
	}

	public void setTecnico(String tecnico) {
		this.tecnico = tecnico;
	}

	public int getAniosExperiencia() {
		return aniosExperiencia;
	}

	public void setAniosExperiencia(int aniosExperiencia) {
		this.aniosExperiencia = aniosExperiencia;
	}

}
