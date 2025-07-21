package logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Bolsa {

	private ArrayList<Usuario> listaUsuarios;
	private ArrayList<Solicitud> listaSolicitudes;
	private ArrayList<MatchOferta> listaMatchOferta;
	private ArrayList<Oferta> listaOfertas;
	public static int genCodUsu = 0;
	public static int genCodSoli = 0;
	public static int genCodMatch = 0;
	public static int genCodOfer = 0;
	public static Bolsa bolsaLaboral = null;

	public Bolsa() {
		super();
		this.listaUsuarios = new ArrayList<Usuario>();
		this.listaSolicitudes = new ArrayList<Solicitud>();
		this.listaMatchOferta = new ArrayList<MatchOferta>();
		this.listaOfertas = new ArrayList<Oferta>();
	}

	public static Bolsa getInstancia() {
		if (bolsaLaboral == null) {
			bolsaLaboral = new Bolsa();
		}
		return bolsaLaboral;
	}

	public ArrayList<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public ArrayList<Solicitud> getListaSolicitudes() {
		return listaSolicitudes;
	}

	public void setListaSolicitudes(ArrayList<Solicitud> listaSolicitudes) {
		this.listaSolicitudes = listaSolicitudes;
	}

	public ArrayList<MatchOferta> getListaMatchOferta() {
		return listaMatchOferta;
	}

	public void setListaMatchOferta(ArrayList<MatchOferta> listaMatchOferta) {
		this.listaMatchOferta = listaMatchOferta;
	}

	public ArrayList<Oferta> getListaOfertas() {
		return listaOfertas;
	}

	public void setListaOfertas(ArrayList<Oferta> listaOfertas) {
		this.listaOfertas = listaOfertas;
	}

	public static int getGenCodUsu() {
		return genCodUsu;
	}

	public static void setGenCodUsu(int genCodUsu) {
		Bolsa.genCodUsu = genCodUsu;
	}

	public static int getGenCodSoli() {
		return genCodSoli;
	}

	public static void setGenCodSoli(int genCodSoli) {
		Bolsa.genCodSoli = genCodSoli;
	}

	public static int getGenCodMatch() {
		return genCodMatch;
	}

	public static void setGenCodMatch(int genCodMatch) {
		Bolsa.genCodMatch = genCodMatch;
	}

	public static int getGenCodOfer() {
		return genCodOfer;
	}

	public static void setGenCodOfer(int genCodOfer) {
		Bolsa.genCodOfer = genCodOfer;
	}

	public static Bolsa getBolsaLaboral() {
		return bolsaLaboral;
	}

	public static void setBolsaLaboral(Bolsa bolsaLaboral) {
		Bolsa.bolsaLaboral = bolsaLaboral;
	}

	// metodos aqui debajo

	// buscar por codigo
	public Usuario buscarUsuarioByCodigo(String cod) {
		Usuario aux = null;
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < listaUsuarios.size()) { // de esta forma se hace el bucle para buscar el objeto que
			// tenga el codigo

			if (listaUsuarios.get(i).getCodigo().equalsIgnoreCase(cod)) { // con equal se busca si cod es igual al id
				// del objeto
				aux = listaUsuarios.get(i); // en caso de encontrar se asigna a aux y se pone encontrado como true
				encontrado = true;
			}
			i++;
		}
		return aux;
	}

	public Oferta buscarOfertaByCodigo(String cod) {
		Oferta aux = null;
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < listaOfertas.size()) { // de esta forma se hace el bucle para buscar el objeto que
			// tenga el codigo

			if (listaOfertas.get(i).getCodigo().equalsIgnoreCase(cod)) { // con equal se busca si cod es igual al id del
				// objeto
				aux = listaOfertas.get(i); // en caso de encontrar se asigna a aux y se pone encontrado como true
				encontrado = true;
			}
			i++;
		}
		return aux;
	}

	public Solicitud buscarSolicitudByCodigo(String cod) {
		Solicitud aux = null;
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < listaSolicitudes.size()) { // de esta forma se hace el bucle para buscar el objeto que
			// tenga el codigo

			if (listaSolicitudes.get(i).getCodigo().equalsIgnoreCase(cod)) { // con equal se busca si cod es igual al id
				// del objeto
				aux = listaSolicitudes.get(i); // en caso de encontrar se asigna a aux y se pone encontrado como true
				encontrado = true;
			}
			i++;
		}
		return aux;
	}

	// buscar indice por codigo
	private int buscarIndexUsuarioByCodigo(String id) {
		// TODO Auto-generated method stub
		int index = -1;
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < listaUsuarios.size()) {
			if (listaUsuarios.get(i).getCodigo().equalsIgnoreCase(id)) {
				index = i;
				encontrado = true;
			}
			i++;
		}

		return index;
	}

	private int buscarIndexSolicitudByCodigo(String id) {
		// TODO Auto-generated method stub
		int index = -1;
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < listaSolicitudes.size()) {
			if (listaSolicitudes.get(i).getCodigo().equalsIgnoreCase(id)) {
				index = i;
				encontrado = true;
			}
			i++;
		}

		return index;
	}

	private int buscarIndexOfertaByCodigo(String id) {
		// TODO Auto-generated method stub
		int index = -1;
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < listaOfertas.size()) {
			if (listaOfertas.get(i).getCodigo().equalsIgnoreCase(id)) {
				index = i;
				encontrado = true;
			}
			i++;
		}

		return index;
	}

	// insertar
	public void insertarUsuario(Usuario usu) {
		listaUsuarios.add(usu);
	}

	public void insertarOferta(Oferta ofer) {
		listaOfertas.add(ofer);
	}

	public void insertarSolicitud(Solicitud soli) {
		listaSolicitudes.add(soli);
		// al insertar una solicitud debe agregarse a la lista solicitudes hechas por la
		// persona
		Persona aux = soli.getSolicitante();
		aux.insertarSolicitud(soli);
		modificarUsuario(aux);
	}

	// modificar

	public void modificarUsuario(Usuario updated) {
		int index = buscarIndexUsuarioByCodigo(updated.getCodigo()); // buscar indice a actualizar
		listaUsuarios.set(index, updated); // actualizar elemento del listado en ese indice
	}

	public void modificarSolicitud(Solicitud updated) {
		int index = buscarIndexSolicitudByCodigo(updated.getCodigo()); // buscar indice a actualizar
		listaSolicitudes.set(index, updated); // actualizar elemento del listado en ese indice
	}

	public void modificarOferta(Oferta updated) {
		int index = buscarIndexOfertaByCodigo(updated.getCodigo()); // buscar indice a actualizar
		listaOfertas.set(index, updated); // actualizar elemento del listado en ese indice
	}

	// eliminar

	public void eliminarOferta(Oferta selected) {
		listaOfertas.remove(selected);
	}

	public void eliminarSolicitud(Solicitud selected) {
		listaSolicitudes.remove(selected);
		Persona aux = selected.getSolicitante();
		aux.removerSolicitud(selected);
		modificarUsuario(aux);
	}

	// generacion de codigos

	public String generarCodigoOferta() {
		genCodOfer++;
		return "O-" + genCodOfer; // codigo a registrar
	}

	public String generarCodigoSolicitud() {
		genCodSoli++;
		return "S-" + genCodSoli; // codigo a registrar
	}

	public static String generarCodigoMatch() {
		genCodMatch++;
		return "MTCH-" + genCodMatch; // codigo a registrar
	}

	// validar contraseña y email del usuario
	public Usuario validarLoginUsuarios(String email, String passwd) {
		Usuario aux = null;
		boolean encontrado = false;
		int i = 0;

		while (!encontrado && i < listaUsuarios.size()) {
			if (listaUsuarios.get(i).getCorreoElectronico().equalsIgnoreCase(email)
					&& listaUsuarios.get(i).getPasswd().equalsIgnoreCase(passwd)) {
				aux = listaUsuarios.get(i);
			}
		}
		return aux;
	}

	// Desactivar cuenta usuario
	public void desactivarCuenta(Persona persActiva) {
		persActiva.setEstado(false);
		modificarUsuario(persActiva);
	}

	// validar contraseña y email de empresa
	/*
	 * public boolean validarLoginEmpresa(String email, String passwd) { boolean
	 * valido = false;
	 * 
	 * for (Empresa emp : listaEmpresas) {
	 * if(emp.getCorreo().equalsIgnoreCase(email) &&
	 * emp.getPassw().equalsIgnoreCase(passwd)) { valido = true; } } return valido;
	 * }
	 */
	// obtener Persona en base a email y contraseña
	/*
	 * public Persona obtLoginPersona(String email, String passwd) { Persona aux =
	 * null;
	 * 
	 * for (Persona pers : listaPersonas) {
	 * if(pers.getCorreoElectronico().equalsIgnoreCase(email) &&
	 * pers.getPasswd().equalsIgnoreCase(passwd)) { aux = pers; } } return aux; }
	 */
	// obtener Empresa en base a email y contraseña
	/*
	 * public Empresa obtLoginEmpresa(String email, String passwd) { Empresa aux =
	 * null;
	 * 
	 * for (Empresa emp : listaEmpresas) {
	 * if(emp.getCorreo().equalsIgnoreCase(email) &&
	 * emp.getPassw().equalsIgnoreCase(passwd)) { aux = emp; } } return aux; }
	 */

	// algoritmo de matching
	public ArrayList<MatchOferta> realizarMatching(ArrayList<Oferta> ofertas, ArrayList<Solicitud> solicitudes) {
		ArrayList<MatchOferta> MatchesOfertas = new ArrayList<MatchOferta>();
		for (Oferta oferta : ofertas) {
			for (Solicitud solicitud : solicitudes) {
				int totalCriterios = 8;

				int criteriosCumplidos = 0;

				if (oferta.getModalidad().equalsIgnoreCase(solicitud.getModalidad()))
					criteriosCumplidos++;
				if (oferta.getTipo().equalsIgnoreCase(solicitud.getTipoEmpleo()))
					criteriosCumplidos++;

				// para los 3 tipos de niveles de estudios
				if (oferta.getNivelEducacion().equals("Universitario")
						&& solicitud.getSolicitante() instanceof Universitario)
					criteriosCumplidos++;
				if (oferta.getNivelEducacion().equals("Tecnico Superior")
						&& solicitud.getSolicitante() instanceof TecnicoSuperior)
					criteriosCumplidos++;
				if (oferta.getNivelEducacion().equals("Obrero") && solicitud.getSolicitante() instanceof Obrero)
					criteriosCumplidos++;

				// solo obtener la carrera de los universitarios
				if (oferta.getArea().equalsIgnoreCase(solicitud.getArea()))
					criteriosCumplidos++;
				if (oferta.isRequiereLicencia() == solicitud.isLicencia())
					criteriosCumplidos++;
				if (oferta.isRequiereMovilidad() == solicitud.isDispMovilidad())
					criteriosCumplidos++;
				if (oferta.getHorario().equalsIgnoreCase(solicitud.getDispHorarios()))
					criteriosCumplidos++;
				if (solicitud.getSalarioDeseado() <= oferta.getSalarioEstimado())
					criteriosCumplidos++;

				double porcentaje = (criteriosCumplidos * 100.0) / totalCriterios;

				// si tiene un porcentaje por encima de 60, recomendar para el puesto y crear el
				// enlace con la clase de matching
				if (porcentaje >= 60.0) {
					String codigo = generarCodigoMatch();
					MatchOferta match = new MatchOferta(codigo, new Date(), oferta, solicitud, false, false);
					MatchesOfertas.add(match);
				}
			}
		}
		return MatchesOfertas;
	}

}