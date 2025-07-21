package logica;

public class PersonaMatcheo {

	// Clase para almacenar las personas junto con la cantidad de requisitos que
	// clase para almacenar las personas junto con la cantidad de requisitos que
	// cumplen con respecto a una oferta
	// para pasarlas por el algoritmo de matcheo
	Persona persona;
	int reqCumplidos;

	public PersonaMatcheo(Persona persona, int reqCumplidos) {
		super();
		this.persona = persona;
		this.reqCumplidos = reqCumplidos;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public int getReqCumplidos() {
		return reqCumplidos;
	}

	public void setReqCumplidos(int reqCumplidos) {
		this.reqCumplidos = reqCumplidos;
	}

}
