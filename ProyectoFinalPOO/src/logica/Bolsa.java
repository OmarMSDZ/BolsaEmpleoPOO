package logica;

import java.util.ArrayList;

public class Bolsa {

	private ArrayList<Persona> listaUsuarios;
	private ArrayList<Solicitud> listaSolicitudes;
	private ArrayList<Oferta> listaOfertas;
	public static int genCodPers = 0;
	public static int genCodSoli = 0;
	public static int genCodEmpr = 0;
	public static int genCodOfer = 0;
	public static Bolsa bolsaLaboral = null;

	public Bolsa() {
		super();
		listaUsuarios = new ArrayList<Persona>();
		listaSolicitudes = new ArrayList<Solicitud>();
		listaOfertas = new ArrayList<Oferta>();
	}

	public static Bolsa getInstancia() {
		if (bolsaLaboral == null) {
			bolsaLaboral = new Bolsa();
		}

		return bolsaLaboral;
	}

	public ArrayList<Persona> getListaPersonas() {
		return listaUsuarios;
	}

	public void setListaPersonas(ArrayList<Persona> listaPersona) {
		this.listaUsuarios = listaPersona;
	}

	public ArrayList<Solicitud> getListaSolicitudes() {
		return listaSolicitudes;
	}

	public void setListaSolicitudes(ArrayList<Solicitud> listaSolicitudes) {
		this.listaSolicitudes = listaSolicitudes;
	}

	public ArrayList<Oferta> getListaOfertas() {
		return listaOfertas;
	}

	public void setListaOferta(ArrayList<Oferta> listaOferta) {
		this.listaOfertas = listaOferta;
	}

	// Métodos aquí debajo

	// buscar por codigo
	public Persona buscarClienteByCodigo(String cod) {
		Persona aux = null;
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

	/* public Empresa buscarEmpresaByCodigo(String cod) {
		Empresa aux = null;
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < listaEmpresas.size()) { // de esta forma se hace el bucle para buscar el objeto que
															// tenga el codigo

			if (listaEmpresas.get(i).getCodigo().equalsIgnoreCase(cod)) { // con equal se busca si cod es igual al id
																			// del objeto
				aux = listaEmpresas.get(i); // en caso de encontrar se asigna a aux y se pone encontrado como true
				encontrado = true;
			}
			i++;
		}
		return aux;
	} */

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

	// buscar índice por código
	private int buscarIndexPersonaByCodigo(String id) {
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

	/*
	private int buscarIndexEmpresaByCodigo(String id) {
		// TODO Auto-generated method stub
		int index = -1;
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < listaEmpresas.size()) {
			if (listaEmpresas.get(i).getCodigo().equalsIgnoreCase(id)) {
				index = i;
				encontrado = true;
			}
			i++;
		}

		return index;
	} */

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

	// Insertar
	public void insertarPersona(Persona pers) {
		listaUsuarios.add(pers);
	}

	/*
	public void insertarEmpresa(Empresa emp) {
		listaEmpresas.add(emp);
	} */

	public void insertarOferta(Oferta ofer) {
		listaOfertas.add(ofer);
	}

	public void insertarSolicitud(Solicitud soli) {
		listaSolicitudes.add(soli);
		// al insertar una solicitud debe agregarse a la lista de persona
		Persona aux = soli.getSolicitante();
		aux.insertarSolicitud(soli);
		modificarPersona(aux);
	}

	// modificar
	public void modificarPersona(Persona updated) {
		int index = buscarIndexPersonaByCodigo(updated.getCodigo()); // buscar indice a actualizar
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

	/* public void modificarEmpresa(Empresa updated) {
		int index = buscarIndexEmpresaByCodigo(updated.getCodigo()); // buscar indice a actualizar
		listaEmpresas.set(index, updated); // actualizar elemento del listado en ese indice
	} */

	// eliminar
	public void eliminarPersona(Persona selected) {
		listaUsuarios.remove(selected);
	}

	/* public void eliminarEmpresa(Empresa selected) {
		listaEmpresas.remove(selected);
	} */

	public void eliminarOferta(Oferta selected) {
		listaOfertas.remove(selected);
	}

	public void eliminarSolicitud(Solicitud selected) {
		listaSolicitudes.remove(selected);
	}

	// generacion de codigos
	public String generarCodigoPersona() {
		genCodPers++;
		return "P-" + genCodPers; // codigo a registrar
	}

	public String generarCodigoEmpresa() {
		genCodEmpr++;
		return "E-" + genCodEmpr; // codigo a registrar
	}

	public String generarCodigoOferta() {
		genCodOfer++;
		return "O-" + genCodOfer; // codigo a registrar
	}

	public String generarCodigoSolicitud() {
		genCodSoli++;
		return "S-" + genCodSoli; // codigo a registrar
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

	/* // Algoritmo de matcheo de PERSONAS según la oferta, devolver la lista de las
	// personas organizada por mayor cantidad de requisitos cumplido

	public ArrayList<Persona> algMatching(Oferta of) {

		ArrayList<Persona> listaPersonasMatch = new ArrayList<Persona>(); // lista a retornar
		ArrayList<PersonaMatcheo> listaTempPersonasMatch = new ArrayList<PersonaMatcheo>();// temporal
		// recorrer toda la lista de personas
		for (Persona persona : listaUsuarios) {
			int cantidadReqCump = 0; // cantidad requisitos con los que cumple la persona
			// revisar si tiene licencia o no
			if ((of.isRequiereLicencia() && persona.isLicencia())
					|| (!of.isRequiereLicencia() && !persona.isLicencia())) {
				cantidadReqCump++;
			}
			// revisar si puede movilizarse o no
			if ((of.isRequiereMovilidad() && persona.isDispMudarse())
					|| (!of.isRequiereMovilidad() && !persona.isDispMudarse())) {
				cantidadReqCump++;
			}
			// revisar la formacion
			if ((of.getFormacion().equalsIgnoreCase("Universitario") && persona instanceof Universitario)
					|| (of.getFormacion().equalsIgnoreCase("Tecnico Superior") && persona instanceof TecnicoSuperior)
					|| (of.getFormacion().equalsIgnoreCase("Obrero") && persona instanceof Obrero)) {
				cantidadReqCump++;
			}

			// revisar disp de horario, modalidad y tipo de trabajo
			if (of.getHorario() == persona.getDispHorarios())
				cantidadReqCump++;
			if (of.getModalidad() == persona.getModalidad())
				cantidadReqCump++;
			if (of.getTipo() == persona.getTipoEmpleado())
				cantidadReqCump++;
			listaTempPersonasMatch.add(new PersonaMatcheo(persona, cantidadReqCump)); // añadir a lista temporal para
																						// luego organizar por cant req
		}
		// ordenar lista temporal de matcheos e insertar en la lista a retornar
		listaTempPersonasMatch.sort((a, b) -> Integer.compare(b.reqCumplidos, a.reqCumplidos));

		for (PersonaMatcheo personaMatcheo : listaTempPersonasMatch) {
			listaPersonasMatch.add(personaMatcheo.getPersona());
		}

		return listaPersonasMatch;
	} */

}
