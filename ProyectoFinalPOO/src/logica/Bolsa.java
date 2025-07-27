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
	public static int genCodUsu = 0;
	public static int genCodSoli = 0;
	public static int genCodMatch = 0;
	public static int genCodOfer = 0;
	public static Bolsa bolsaLaboral = null;
	public static Usuario usuarioActivo = null;
	//threads, con el modificador transient no se serializan ni se guardan en archivo
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
	}

	// Eliminar
	public void eliminarOferta(Oferta selected) {
		listaOfertas.remove(selected);
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

	public static String generarCodigoMatch() {
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
		ArrayList<MatchOferta> MatchesOfertas = new ArrayList<MatchOferta>();
		for (Oferta oferta : ofertas) {
			for (Solicitud solicitud : solicitudes) {
				// no puede estar cancelada ni pausada la solicitud, la persona debe estar
				// desempleada
				if (!(solicitud.getEstadoSolicitud().equalsIgnoreCase("PAUSADA")
						|| solicitud.getEstadoSolicitud().equalsIgnoreCase("CANCELADA"))
						&& !solicitud.getSolicitante().isEstado()) {

					int totalCriterios = 8;

					int criteriosCumplidos = 0;

					if (oferta.getModalidad().equalsIgnoreCase(solicitud.getModalidad()))
						criteriosCumplidos++;
					if (oferta.getTipo().equalsIgnoreCase(solicitud.getTipoEmpleo()))
						criteriosCumplidos++;

					// Para los 3 tipos de niveles de estudios
					if (oferta.getNivelEducacion().equals("Universitario")
							&& solicitud.getSolicitante() instanceof Universitario)
						criteriosCumplidos++;
					if (oferta.getNivelEducacion().equals("Tecnico Superior")
							&& solicitud.getSolicitante() instanceof TecnicoSuperior)
						criteriosCumplidos++;
					if (oferta.getNivelEducacion().equals("Obrero") && solicitud.getSolicitante() instanceof Obrero)
						criteriosCumplidos++;

					// Solo obtener la carrera de los universitarios
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

					// Si tiene un porcentaje por encima de 60, recomendar para el puesto y crear el
					// enlace con la clase de matching
					if (porcentaje >= 60.0) {
						boolean yaAceptadoPorCandidato = false;

						// Verificar si ya existe un match con el mismo solicitante y oferta, y el
						// candidato ya aceptó
						for (MatchOferta existente : Bolsa.getInstancia().getListaMatchOferta()) {
							if (existente.getOfertaMatcheo().equals(oferta)
									&& existente.getSolicitudMatcheo().equals(solicitud)
									&& existente.isAceptacionCandidato()) {
								yaAceptadoPorCandidato = true;
								break;
							}
						}

						// Solo agregar si no ha sido aceptado por el candidato antes
						if (!yaAceptadoPorCandidato) {
							String codigo = generarCodigoMatch();
							MatchOferta match = new MatchOferta(codigo, new Date(), oferta, solicitud, false, false);
							MatchesOfertas.add(match);
						}

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

	//iniciar y detener hilos que ejecutan el matching automatico, solo se ejecutara un hilo de esto para toda la app
	public void iniciarMatchingAutomatico() {
	    if (hiloMatching == null || !hiloMatching.isAlive()) {
	        matchRunnable = new MatchRunnable(); //logica del hilo
	        hiloMatching = new Thread(matchRunnable); //este es el hilo a ejecutar
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
	// contratar persona, esto cierra las solicitudes del empleado y cambia su
	// estado
	public void contratarPersona(Persona pers) {
		pers.setEstado(true);

		// Pausar todas las solicitudes abiertas de esa persona
		for (Solicitud sol : pers.getMisSolicitudes()) {
			sol.setEstadoSolicitud("PAUSADA");
		}
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
}
