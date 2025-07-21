package logica;

import java.util.ArrayList;

public class Empresa extends Usuario {

	private String rnc;
	private String tipoEmpresa;
	private ArrayList<Oferta> misOfertas;

	public Empresa(String codigo, String nombre, String passwd, String telefono, String correoElectronico,
			String provincia, String municipio, String direccion, boolean estado, String rnc, String tipoEmpresa,
			ArrayList<Oferta> misOfertas) {
		super(codigo, nombre, passwd, telefono, correoElectronico, provincia, municipio, direccion, estado);
		this.rnc = rnc;
		this.tipoEmpresa = tipoEmpresa;
		this.misOfertas = misOfertas;
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