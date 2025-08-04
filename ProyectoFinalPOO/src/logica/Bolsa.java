package logica;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JTextField;

public class Bolsa implements Serializable {

	private static final long serialVersionUID = 1L;

	private ArrayList<Usuario> listaUsuarios;
	private ArrayList<Solicitud> listaSolicitudes;
	private ArrayList<MatchOferta> listaMatchOferta;
	private ArrayList<Oferta> listaOfertas;
	public int genCodUsu = 0;
	public int genCodSoli = 0;
	public int genCodMatch = 0;
	public int genCodOfer = 0;
	public static Bolsa bolsaLaboral = null;
	public static Usuario usuarioActivo = null;
	// threads, con el modificador transient no se serializan ni se guardan en
	// archivo
	private transient Thread hiloMatching;
	private transient MatchRunnable matchRunnable;

	public Bolsa() {
		super();
		listaUsuarios = new ArrayList<Usuario>();
		listaSolicitudes = new ArrayList<Solicitud>();
		listaMatchOferta = new ArrayList<MatchOferta>();
		listaOfertas = new ArrayList<Oferta>();
	}

	public static Bolsa getInstancia() {
		if (bolsaLaboral == null) {
			bolsaLaboral = new Bolsa();
		}
		return bolsaLaboral;
	}

	public static void setInstancia(Bolsa bolsa) {
		bolsaLaboral = bolsa;
	}

	public ArrayList<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public static Usuario getUsuarioActivo() {
		return usuarioActivo;
	}

	public static void setUsuarioActivo(Usuario usuarioActivo) {
		Bolsa.usuarioActivo = usuarioActivo;
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

	public int getGenCodUsu() {
		return genCodUsu;
	}

	public void setGenCodUsu(int genCodUsu) {
		this.genCodUsu = genCodUsu;
	}

	public int getGenCodSoli() {
		return genCodSoli;
	}

	public void setGenCodSoli(int genCodSoli) {
		this.genCodSoli = genCodSoli;
	}

	public int getGenCodMatch() {
		return genCodMatch;
	}

	public void setGenCodMatch(int genCodMatch) {
		this.genCodMatch = genCodMatch;
	}

	public int getGenCodOfer() {
		return genCodOfer;
	}

	public void setGenCodOfer(int genCodOfer) {
		this.genCodOfer = genCodOfer;
	}

	public static Bolsa getBolsaLaboral() {
		return bolsaLaboral;
	}

	public static void setBolsaLaboral(Bolsa bolsaLaboral) {
		Bolsa.bolsaLaboral = bolsaLaboral;
	}

	// Métodos aquí debajo

	// Buscar por código
	public Usuario buscarUsuarioByCodigo(String cod) {
		Usuario aux = null;
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < listaUsuarios.size()) {
			if (listaUsuarios.get(i).getCodigo().equalsIgnoreCase(cod)) {
				aux = listaUsuarios.get(i);
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
		while (!encontrado && i < listaOfertas.size()) {

			if (listaOfertas.get(i).getCodigo().equalsIgnoreCase(cod)) {
				aux = listaOfertas.get(i);
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
		while (!encontrado && i < listaSolicitudes.size()) {

			if (listaSolicitudes.get(i).getCodigo().equalsIgnoreCase(cod)) {
				aux = listaSolicitudes.get(i);
				encontrado = true;
			}
			i++;
		}
		return aux;
	}

	public MatchOferta buscarMatchOfertaByCodigo(String cod) {
		MatchOferta aux = null;
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < listaMatchOferta.size()) {

			if (listaMatchOferta.get(i).getCodigo().equalsIgnoreCase(cod)) {
				aux = listaMatchOferta.get(i);
				encontrado = true;
			}
			i++;
		}
		return aux;
	}

	// Buscar índice por código
	private int buscarIndexUsuarioByCodigo(String id) {
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
	public void insertarUsuario(Usuario usuario) {
		listaUsuarios.add(usuario);
	}

	public void insertarOferta(Oferta oferta) {
		listaOfertas.add(oferta);
		Empresa auxEmpresa = oferta.getEmpReclutadora();
		auxEmpresa.insertarOferta(oferta);
		modificarUsuario(auxEmpresa);
	}

	public void insertarSolicitud(Solicitud solicitud) {
		listaSolicitudes.add(solicitud);
		Persona aux = solicitud.getSolicitante();
		aux.insertarSolicitud(solicitud);
		modificarUsuario(aux);
	}

	// Modificar
	public void modificarUsuario(Usuario updated) {
		int index = buscarIndexUsuarioByCodigo(updated.getCodigo());
		listaUsuarios.set(index, updated);
	}

	public void modificarSolicitud(Solicitud updated) {
		int index = buscarIndexSolicitudByCodigo(updated.getCodigo());
		listaSolicitudes.set(index, updated);
	}

	public void modificarOferta(Oferta updated) {
		int index = buscarIndexOfertaByCodigo(updated.getCodigo());
		listaOfertas.set(index, updated);
		Empresa aux = updated.getEmpReclutadora();
		aux.modificarOferta(updated);
		modificarUsuario(aux);
	}

	// Eliminar
	public void eliminarOferta(Oferta selected) {
		listaOfertas.remove(selected);
		Empresa aux = selected.getEmpReclutadora();
		aux.removerOferta(selected);
		modificarUsuario(aux);
	}

	public void eliminarSolicitud(Solicitud selected) {
		listaSolicitudes.remove(selected);
		Persona aux = selected.getSolicitante();
		aux.removerSolicitud(selected);
		modificarUsuario(aux);
	}

	// Generación de códigos
	public String generarCodigoUsuario() {
		genCodUsu++;
		return "U-" + genCodUsu;
	}

	public String generarCodigoOferta() {
		genCodOfer++;
		return "O-" + genCodOfer;
	}

	public String generarCodigoSolicitud() {
		genCodSoli++;
		return "S-" + genCodSoli;
	}

	public String generarCodigoMatch() {
		genCodMatch++;
		return "MTCH-" + genCodMatch;
	}

	// Validar contraseña y email del usuario
	public Usuario validarLoginUsuarios(String email, String passwd) {
		Usuario aux = null;
		boolean encontrado = false;
		int i = 0;

		while (!encontrado && i < listaUsuarios.size()) {
			if (listaUsuarios.get(i).getCorreoElectronico().equalsIgnoreCase(email)
					&& listaUsuarios.get(i).getPasswd().equalsIgnoreCase(passwd)) {
				aux = listaUsuarios.get(i);
				encontrado = true;
			}
			i++;
		}
		return aux;
	}

	// Desactivar cuenta usuario
	public void desactivarCuenta(Persona persActiva) {
		persActiva.setEstado(false);
		modificarUsuario(persActiva);
	}

	// Algoritmo de matching
	public ArrayList<MatchOferta> realizarMatching(ArrayList<Oferta> ofertas, ArrayList<Solicitud> solicitudes) {
		ArrayList<MatchOferta> MatchesOfertas = new ArrayList<>();
//	    System.out.println("Cantidad de ofertas: " + ofertas.size());
//	    System.out.println("Cantidad de solicitudes: " + solicitudes.size());

//		for (int i = 0; i < solicitudes.size(); i++) {
//			System.out.println(
//					"Solicitud[" + i + "]: " + solicitudes.get(i).getCodigo() + " - " + solicitudes.get(i).hashCode());
//		}

		for (Oferta oferta : ofertas) {
			for (Solicitud solicitud : solicitudes) {

				// Validación básica
				if (oferta == null || solicitud == null || solicitud.getSolicitante() == null)
					continue;

				String estadoSol = solicitud.getEstadoSolicitud();
				boolean solicitanteEmpleado = solicitud.getSolicitante().isEstadoEmpleado();

//	            System.out.println("Estado solicitud: " + estadoSol + ", Solicitante empleado? " + solicitanteEmpleado);
				System.out.println(
						"Total de matches actuales en la bolsa: " + Bolsa.getInstancia().getListaMatchOferta().size());

				if (!(estadoSol.trim().equalsIgnoreCase("PAUSADA") || estadoSol.trim().equalsIgnoreCase("CANCELADA"))
						&& !solicitanteEmpleado) {

					int criteriosCumplidos = 0;
					int totalCriterios = 8;

					if (oferta.getModalidad().trim().equalsIgnoreCase(solicitud.getModalidad().trim()))
						criteriosCumplidos++;

					if (oferta.getTipo().trim().equalsIgnoreCase(solicitud.getTipoEmpleo().trim()))
						criteriosCumplidos++;

					if (oferta.getArea().trim().equalsIgnoreCase(solicitud.getArea().trim()))
						criteriosCumplidos++;

					if (oferta.getNivelEducacion().equalsIgnoreCase("Universitario / Grado")
							&& solicitud.getSolicitante() instanceof Universitario) {
						criteriosCumplidos++;

					} else if (oferta.getNivelEducacion().equalsIgnoreCase("Técnico superior")
							&& solicitud.getSolicitante() instanceof TecnicoSuperior) {
						criteriosCumplidos++;

					} else if (oferta.getNivelEducacion().equalsIgnoreCase("Obrero")
							&& solicitud.getSolicitante() instanceof Obrero) {
						criteriosCumplidos++;

					}

					if (oferta.isRequiereLicencia() == solicitud.getSolicitante().isLicenciaConducir())
						criteriosCumplidos++;

					if (oferta.isRequiereMovilidad() == solicitud.isDispMovilidad())
						criteriosCumplidos++;

					if (oferta.getHorario().equalsIgnoreCase(solicitud.getDispHorarios()))
						criteriosCumplidos++;

					if (solicitud.getSalarioDeseado() <= oferta.getSalarioEstimado())
						criteriosCumplidos++;

					double porcentaje = (criteriosCumplidos * 100.0) / totalCriterios;
					System.out.println("Evaluando oferta " + oferta.getCodigo() + " con solicitud "
							+ solicitud.getCodigo() + " - porcentaje: " + porcentaje);

					// Evitar duplicados de match oferta/solicitud (MOVER A OTRO METODO)
					boolean yaExisteMatch = false;
					for (MatchOferta existente : Bolsa.getInstancia().getListaMatchOferta()) {
						if (existente.getOfertaMatcheo() != null && existente.getSolicitudMatcheo() != null
								&& existente.getOfertaMatcheo().equals(oferta)
								&& existente.getSolicitudMatcheo().equals(solicitud)) {
							yaExisteMatch = true;
							break;
						}
					}

					if (porcentaje >= 70.0 && !yaExisteMatch) {
						String codigo = generarCodigoMatch();
						MatchOferta match = new MatchOferta(codigo, new Date(), oferta, solicitud, null, false);
						MatchesOfertas.add(match);
						// solo para prueba
						System.out.println(" Match creado: " + match.getCodigo() + "  Oferta: " + oferta.getCodigo()
								+ " | Solicitud: " + solicitud.getCodigo());
					}
				}
			}
		}

		return MatchesOfertas;
	}

	// Ejecutar match, solo si hay al menos una oferta y una solicitud registrada
	public void match() {
		if (listaOfertas.size() > 0 && listaSolicitudes.size() > 0) {
			ArrayList<MatchOferta> nuevosMatch = realizarMatching(listaOfertas, listaSolicitudes);
			for (MatchOferta match : nuevosMatch) {
				if (!listaMatchOferta.contains(match)) {
					listaMatchOferta.add(match);
				}
			}
		}
	}

	// iniciar y detener hilos que ejecutan el matching automatico, solo se
	// ejecutara un hilo de esto para toda la app
	public void iniciarMatchingAutomatico() {
		if (hiloMatching == null || !hiloMatching.isAlive()) {
			matchRunnable = new MatchRunnable(); // logica del hilo
			hiloMatching = new Thread(matchRunnable); // este es el hilo a ejecutar
			hiloMatching.start();
		}
	}

	public void detenerMatchingAutomatico() {
		if (matchRunnable != null) {
			matchRunnable.detener();
		}
		if (hiloMatching != null && hiloMatching.isAlive()) {
			hiloMatching.interrupt();
		}
	}

	// contar matches oferta y solicitud (fines visuales)
	public int contarMatchesOferta(Oferta oferta) {
		int cantidad = 0;
		if (!listaMatchOferta.isEmpty()) {
			for (MatchOferta matchOferta : listaMatchOferta) {
				if (matchOferta.getOfertaMatcheo().equals(oferta)) {
					cantidad++;
				}
			}
		}
		return cantidad;
	}

	// QUITAR
	public int contarMatchesSolicitud(Solicitud solicitud) {
		int cantidad = 0;
		if (!listaMatchOferta.isEmpty() && solicitud != null && solicitud.getCodigo() != null) {
			for (MatchOferta matchOferta : listaMatchOferta) {
				Solicitud solMatcheada = matchOferta.getSolicitudMatcheo();
				if (solMatcheada != null && solMatcheada.getCodigo() != null
						&& solMatcheada.getCodigo().equals(solicitud.getCodigo())) {
					cantidad++;
				}
			}
		}
		return cantidad;
	}

	// Contratar candidato, esto cierra las solicitudes del empleado y cambia su
	// estado a empleado
	public void contratarPersona(Persona persona, Solicitud solicitud) {
		persona.setEstadoEmpleado(true);
		solicitud.cambiarEstadoAprobada();
		persona.pausarSolicitudes();
		modificarUsuario(persona);
	}

	public boolean validarCorreo(String correoIngresado) {
		boolean valido = true;
		int i = 0;

		while (valido && i < listaUsuarios.size()) {
			if (listaUsuarios.get(i).correoElectronico.equalsIgnoreCase(correoIngresado)) {
				valido = false;
			}
			i++;
		}
		return valido;
	}

	public static void guardarEstado() {
		try (ObjectOutputStream bolsaWrite = new ObjectOutputStream(new FileOutputStream("BdLaborea.dat"))) {
			bolsaWrite.writeObject(getInstancia());
			System.out.println("Datos guardados correctamente");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Contadores para mostrar en la pantalla de administrador (Intefaz visual)
	public int cantidadUsuarios() {
		int cantidad = listaUsuarios.size();
		return cantidad;
	}

	public int cantidadEmpresas() {
		int empresas = 0;

		for (Usuario actual : listaUsuarios) {
			if (actual instanceof Empresa) {
				empresas++;
			}
		}

		return empresas;
	}

	public int cantidadMatcheos() {
		int cantMatches = listaMatchOferta.size();
		return cantMatches;
	}

	public ArrayList<Integer> cantidadesCantidatos() {
		ArrayList<Integer> cantidades = new ArrayList<>();
		int personas = 0, universitarios = 0, tecnicos = 0, obreros = 0;

		for (Usuario actual : listaUsuarios) {
			if (actual instanceof Persona) {
				personas++;
				if (actual instanceof Universitario) {
					universitarios++;
				} else if (actual instanceof TecnicoSuperior) {
					tecnicos++;
				} else {
					obreros++;
				}
			}
		}

		cantidades.add(personas);
		cantidades.add(universitarios);
		cantidades.add(tecnicos);
		cantidades.add(obreros);

		return cantidades; // cantidades[0] = cantidad de personas, cantidades[1] = cantidad de
							// universitarios, cantidades[2] = cantidad de técnicos, cantidades[3] =
							// cantidad de obreros
	}

	public int cantOfertasDisp() {
		int ofertasDisponibles = 0;

		for (Oferta actual : listaOfertas) {
			if (actual.isEstadoOferta() && actual.getCantVacantes() > 0) {
				ofertasDisponibles++;
			}
		}

		return ofertasDisponibles;
	}

	public int cantidadSolicPend() {
		int solicitudesPend = 0;

		for (Solicitud actual : listaSolicitudes) {
			if (actual.getEstadoSolicitud().equalsIgnoreCase("ACTIVA")) {
				solicitudesPend++;
			}
		}

		return solicitudesPend;
	}

	// Cantidad de matcheos disponibles, no es un contrato y quedan vacantes
	// disponibles
	public int cantMatheosActivos() {
		int matchDisponible = 0;

		for (MatchOferta matchActual : listaMatchOferta) {
			Oferta aux = matchActual.getOfertaMatcheo();
			if (!matchActual.isAceptacionEmpresa() && aux.getCantVacantes() > 0) {
				matchDisponible++;
			}
		}

		return matchDisponible;
	}

	public int cantContratos() {
		int contratos = 0;

		for (MatchOferta matchActual : listaMatchOferta) {
			if (matchActual.isAceptacionEmpresa()) {
				contratos++;
			}
		}

		return contratos;
	}
}
