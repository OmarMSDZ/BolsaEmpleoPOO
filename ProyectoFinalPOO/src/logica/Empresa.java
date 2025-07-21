package logica;

import java.util.ArrayList;

public class Empresa extends Usuario {

	private String rnc;
	private String tipoEmpresa;
	private ArrayList<Oferta> misOfertas;

	public Empresa(String codigo, String nombre, String passwd, String telefono, String correoElectronico,
			String provincia, String municipio, String direccion, String rnc, String tipoEmpresa) {
		super(codigo, nombre, passwd, telefono, correoElectronico, provincia, municipio, direccion);
		this.rnc = rnc;
		this.tipoEmpresa = tipoEmpresa;
		misOfertas = new ArrayList<Oferta>();
	}

	public String getRnc() {
		return rnc;
	}

	public void setRnc(String rnc) {
		this.rnc = rnc;
	}

	public String getTipoEmpresa() {
		return tipoEmpresa;
	}

	public void setTipoEmpresa(String tipoEmpresa) {
		this.tipoEmpresa = tipoEmpresa;
	}

	public ArrayList<Oferta> getMisOfertas() {
		return misOfertas;
	}

	public void setMisOfertas(ArrayList<Oferta> misOfertas) {
		this.misOfertas = misOfertas;
	}

}
