package logica;

import java.util.ArrayList;

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
	
	public Bolsa getInstancia() {
		if(bolsaLaboral==null) {
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

	
	
	
	//metodos aqui debajo
	/*
	//buscar por codigo
	public Persona buscarClienteByCodigo(String cod) {
		Persona aux = null;
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < listaPersonas.size()){ //de esta forma se hace el bucle para buscar el objeto que tenga el codigo

			if(listaPersonas.get(i).getCodigo().equalsIgnoreCase(cod)) { //con equal se busca si cod es igual al id del objeto
				aux = listaPersonas.get(i); //en caso de encontrar  se asigna a aux y se pone encontrado como true
				encontrado = true;
			}
			i++;
		}
		return aux;
	}
	public Empresa buscarEmpresaByCodigo(String cod) {
		Empresa aux = null;
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < listaEmpresas.size()){ //de esta forma se hace el bucle para buscar el objeto que tenga el codigo

			if(listaEmpresas.get(i).getCodigo().equalsIgnoreCase(cod)) { //con equal se busca si cod es igual al id del objeto
				aux = listaEmpresas.get(i); //en caso de encontrar  se asigna a aux y se pone encontrado como true
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
		while (!encontrado && i < listaOfertas.size()){ //de esta forma se hace el bucle para buscar el objeto que tenga el codigo

			if(listaOfertas.get(i).getCodigo().equalsIgnoreCase(cod)) { //con equal se busca si cod es igual al id del objeto
				aux = listaOfertas.get(i); //en caso de encontrar  se asigna a aux y se pone encontrado como true
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
		while (!encontrado && i < listaSolicitudes.size()){ //de esta forma se hace el bucle para buscar el objeto que tenga el codigo

			if(listaSolicitudes.get(i).getCodigo().equalsIgnoreCase(cod)) { //con equal se busca si cod es igual al id del objeto
				aux = listaSolicitudes.get(i); //en caso de encontrar  se asigna a aux y se pone encontrado como true
				encontrado = true;
			}
			i++;
		}
		return aux;
	}



	//buscar indice por codigo 
	private int buscarIndexPersonaByCodigo(String id) {
		// TODO Auto-generated method stub
		int index = -1;
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < listaPersonas.size()) { 
			if (listaPersonas.get(i).getCodigo().equalsIgnoreCase(id)) {  
				index = i; 
				encontrado = true;
			}
			i++;
		}

		return index;
	}

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

	//insertar
	public void insertarPersona(Persona pers) {
		listaPersonas.add(pers);
	}
	public void insertarEmpresa(Empresa emp) {
		listaEmpresas.add(emp);
	}
	public void insertarOferta(Oferta ofer) {
		listaOfertas.add(ofer);
	}
	public void insertarSolicitud(Solicitud soli) {
		listaSolicitudes.add(soli);
		//al insertar una solicitud debe agregarse a la lista de persona
		Persona aux = soli.getSolicitante();
		aux.insertarSolicitud(soli);
		modificarPersona(aux);
	}

	//modificar
	public void modificarPersona(Persona updated) {
		int index = buscarIndexPersonaByCodigo(updated.getCodigo()); //buscar indice a actualizar
		listaPersonas.set(index, updated); //actualizar elemento del listado en ese indice
	}
	public void modificarSolicitud(Solicitud updated) {
		int index = buscarIndexSolicitudByCodigo(updated.getCodigo()); //buscar indice a actualizar
		listaSolicitudes.set(index, updated); //actualizar elemento del listado en ese indice
	}
	public void modificarOferta(Oferta updated) {
		int index = buscarIndexOfertaByCodigo(updated.getCodigo()); //buscar indice a actualizar
		listaOfertas.set(index, updated); //actualizar elemento del listado en ese indice
	}
	public void modificarEmpresa(Empresa updated) {
		int index = buscarIndexEmpresaByCodigo(updated.getCodigo()); //buscar indice a actualizar
		listaEmpresas.set(index, updated); //actualizar elemento del listado en ese indice
	}
	//eliminar
	public void eliminarPersona(Persona selected) {
		listaPersonas.remove(selected);
	}
	public void eliminarEmpresa(Empresa selected) {
		listaEmpresas.remove(selected);
	}
	public void eliminarOferta(Oferta selected) {
		listaOfertas.remove(selected);
	}
	public void eliminarSolicitud(Solicitud selected) {
		listaSolicitudes.remove(selected);
	}

	//generacion de codigos
	public String generarCodigoPersona() {
		genCodPers++;
		return "P-" + genCodPers; //codigo a registrar
	}
	public String generarCodigoEmpresa() {
		genCodEmpr++;
		return "E-" + genCodEmpr; //codigo a registrar
	}
	public String generarCodigoOferta() {
		genCodOfer++;
		return "O-" + genCodOfer; //codigo a registrar
	}
	public String generarCodigoSolicitud() {
		genCodSoli++;
		return "S-" + genCodSoli; //codigo a registrar
	}
	//validar contraseña y email de persona
	public boolean validarLoginPersona(String email, String passwd) {
		boolean valido = false;

		for (Persona pers : listaPersonas) {
			if(pers.getCorreoElectronico().equalsIgnoreCase(email) && pers.getPasswd().equalsIgnoreCase(passwd)) {
				valido = true;
			}
		}
		return valido;
	}

	//validar contraseña y email de empresa
	public boolean validarLoginEmpresa(String email, String passwd) {
		boolean valido = false;

		for (Empresa emp : listaEmpresas) {
			if(emp.getCorreo().equalsIgnoreCase(email) && emp.getPassw().equalsIgnoreCase(passwd)) {
				valido = true;
			}
		}
		return valido;
	}
	//obtener Persona en base a email y contraseña
	public Persona obtLoginPersona(String email, String passwd) {
		Persona aux = null;

		for (Persona pers : listaPersonas) {
			if(pers.getCorreoElectronico().equalsIgnoreCase(email) && pers.getPasswd().equalsIgnoreCase(passwd)) {
				aux = pers;
			}
		}
		return aux;
	}

	//obtener Empresa en base a email y contraseña
	public Empresa obtLoginEmpresa(String email, String passwd) {
		Empresa aux = null;

		for (Empresa emp : listaEmpresas) {
			if(emp.getCorreo().equalsIgnoreCase(email) && emp.getPassw().equalsIgnoreCase(passwd)) {
				aux = emp;
			}
		}
		return aux;
	}

	//algoritmo de matcheo de personas segun la oferta, devolver la lista de las personas organizada por mayor cantidad de requisitos cumplido

	public ArrayList<Persona> algMatching(Oferta of){

		ArrayList<Persona> listaPersonasMatch = new ArrayList<Persona>(); //lista a retornar
		ArrayList<PersonaMatcheo> listaTempPersonasMatch = new ArrayList<PersonaMatcheo>();//temporal
		//recorrer toda la lista de personas
		for (Persona persona : listaPersonas) {
			int cantidadReqCump= 0; //cantidad requisitos con los que cumple la persona
			//revisar si tiene licencia o no
			if((of.isRequiereLicencia() && persona.isLicencia())||(!of.isRequiereLicencia() && !persona.isLicencia())) {
				cantidadReqCump++;
			} 
			//revisar si puede movilizarse o no
			if((of.isRequiereMovilidad() && persona.isDispMudarse())||(!of.isRequiereMovilidad() && !persona.isDispMudarse())) {
				cantidadReqCump++;
			} 
			//revisar la formacion
			if((of.getFormacion().equalsIgnoreCase("Universitario") && persona instanceof Universitario)||
					(of.getFormacion().equalsIgnoreCase("Tecnico Superior") && persona instanceof TecnicoSuperior)||
					(of.getFormacion().equalsIgnoreCase("Obrero") && persona instanceof Obrero)) {
				cantidadReqCump++;
			}

			//revisar disp de horario, modalidad y tipo de trabajo
			if(of.getHorario()==persona.getDispHorarios()) cantidadReqCump++;
			if(of.getModalidad()==persona.getModalidad()) cantidadReqCump++;
			if(of.getTipo()==persona.getTipoEmpleado()) cantidadReqCump++;
			listaTempPersonasMatch.add(new PersonaMatcheo(persona, cantidadReqCump)); //añadir a lista temporal para luego organizar por cant req
		}
		//ordenar lista temporal de matcheos e insertar en la lista a retornar
		listaTempPersonasMatch.sort((a, b) -> Integer.compare(b.reqCumplidos, a.reqCumplidos));

		for (PersonaMatcheo personaMatcheo : listaTempPersonasMatch) {
			listaPersonasMatch.add(personaMatcheo.getPersona());
		}

		return listaPersonasMatch;
	}
*/


}
