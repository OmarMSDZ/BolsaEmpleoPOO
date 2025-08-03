package logica;

import java.io.Serializable;
import java.util.ArrayList;

public class Empresa extends Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	private String rnc;
	private String tipoEmpresa;
	private String sectorEmpresarial;
	private ArrayList<Oferta> misOfertas;

	public Empresa(String codigo, String nombre, String passwd, String telefono, String correoElectronico,
			String provincia, String municipio, String direccion, boolean estado, String rnc, String tipoEmpresa,
			String sectorEmpresial) {
		super(codigo, nombre, passwd, telefono, correoElectronico, provincia, municipio, direccion, estado);
		this.rnc = rnc;
		this.tipoEmpresa = tipoEmpresa;
		this.sectorEmpresarial = sectorEmpresial;
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

	public String getSectorEmpresarial() {
		return sectorEmpresarial;
	}

	public void setSectorEmpresarial(String sectorEmpresarial) {
		this.sectorEmpresarial = sectorEmpresarial;
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

	public void insertarOferta(Oferta oferta) {
		misOfertas.add(oferta);
	}

	public int buscarIndiceOfertaCodigo(Oferta updated) {
		int index = -1;
		int i = 0;
		boolean encontrado = false;

		while (!encontrado && i < misOfertas.size()) {
			if (misOfertas.get(i).getCodigo().equalsIgnoreCase(updated.getCodigo())) {
				encontrado = true;
				index = i;
			}
			i++;
		}
		return index;
	}

	public void modificarOferta(Oferta updated) {
		int index = buscarIndiceOfertaCodigo(updated);
		misOfertas.set(index, updated);
	}

	void removerOferta(Oferta selected) {
		misOfertas.remove(selected);
	}
}