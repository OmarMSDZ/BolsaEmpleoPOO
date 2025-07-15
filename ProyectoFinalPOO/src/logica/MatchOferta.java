package logica;
import java.util.Date;

public class MatchOferta {

	private String codigo;
	private Date fechaMatcheo;
	private Oferta ofertaMatcheo;
	private Solicitud solicitudMatcheo;
	private boolean aceptacionEmpresa;
	private boolean aceptacionCandidato;

	public MatchOferta(String codigo, Date fechaMatcheo, Oferta ofertaMatcheo, Solicitud solicitudMatcheo,
			boolean aceptacionEmpresa, boolean aceptacionCandidato) {
		super();
		this.codigo = codigo;
		this.fechaMatcheo = fechaMatcheo;
		this.ofertaMatcheo = ofertaMatcheo;
		this.solicitudMatcheo = solicitudMatcheo;
		this.aceptacionEmpresa = aceptacionEmpresa;
		this.aceptacionCandidato = aceptacionCandidato;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Date getFechaMatcheo() {
		return fechaMatcheo;
	}

	public void setFechaMatcheo(Date fechaMatcheo) {
		this.fechaMatcheo = fechaMatcheo;
	}

	public Oferta getOfertaMatcheo() {
		return ofertaMatcheo;
	}

	public void setOfertaMatcheo(Oferta ofertaMatcheo) {
		this.ofertaMatcheo = ofertaMatcheo;
	}

	public Solicitud getSolicitudMatcheo() {
		return solicitudMatcheo;
	}

	public void setSolicitudMatcheo(Solicitud solicitudMatcheo) {
		this.solicitudMatcheo = solicitudMatcheo;
	}

	public boolean isAceptacionEmpresa() {
		return aceptacionEmpresa;
	}

	public void setAceptacionEmpresa(boolean aceptacionEmpresa) {
		this.aceptacionEmpresa = aceptacionEmpresa;
	}

	public boolean isAceptacionCandidato() {
		return aceptacionCandidato;
	}

	public void setAceptacionCandidato(boolean aceptacionCandidato) {
		this.aceptacionCandidato = aceptacionCandidato;
	}

}
