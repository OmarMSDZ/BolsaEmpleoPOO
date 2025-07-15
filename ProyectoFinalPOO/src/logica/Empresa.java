package logica;

import java.util.ArrayList;

public class Empresa {

	private String codigo;
	private String nombre;
	private String passw;
	private String tipoEmpresa;
	private String telefono;
	private String correo;
	private String provincia;
	private String direccion;
	private ArrayList<Oferta> misOfertas;

	public Empresa(String codigo, String nombre, String passw, String tipoEmpresa, String telefono, String correo, String provincia, String direccion) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.passw = passw;
		this.tipoEmpresa = tipoEmpresa;
		this.telefono = telefono;
		this.correo = correo;
		this.provincia = provincia;
		this.direccion = direccion;
		
		misOfertas = new ArrayList<Oferta>();
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassw() {
		return passw;
	}

	public void setPassw(String passw) {
		this.passw = passw;
	}

	public String getTipoEmpresa() {
		return tipoEmpresa;
	}

	public void setTipoEmpresa(String tipoEmpresa) {
		this.tipoEmpresa = tipoEmpresa;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public ArrayList<Oferta> getMisOfertas() {
		return misOfertas;
	}

	public void setMisOfertas(ArrayList<Oferta> misOfertas) {
		this.misOfertas = misOfertas;
	}

}
