package logica;
import java.io.Serializable;
import java.util.Date;

public class MatchOferta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigo;
	private Date fechaMatcheo;
	private Oferta ofertaMatcheo;
	private Solicitud solicitudMatcheo;
	private boolean aceptacionEmpresa;
 

	public MatchOferta(String codigo, Date fechaMatcheo, Oferta ofertaMatcheo, Solicitud solicitudMatcheo,
			boolean aceptacionEmpresa) {
		super();
		this.codigo = codigo;
		this.fechaMatcheo = fechaMatcheo;
		this.ofertaMatcheo = ofertaMatcheo;
		this.solicitudMatcheo = solicitudMatcheo;
		this.aceptacionEmpresa = aceptacionEmpresa;
 
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

	 

}
