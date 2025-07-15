package logica;

public abstract class Usuario {

	protected String codigo;
	protected String nombre;
	protected String passwd;
	protected String telefono;
	protected String correoElectronico;
	protected String provincia;
	protected String municipio;
	protected String direccion;

	public Usuario(String codigo, String nombre, String passwd, String telefono, String correoElectronico,
			String provincia, String municipio, String direccion) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.passwd = passwd;
		this.telefono = telefono;
		this.correoElectronico = correoElectronico;
		this.provincia = provincia;
		this.municipio = municipio;
		this.direccion = direccion;
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

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	// metodos aqui debajo

}
