package logica;

public class MatchRunnable implements Runnable {

	private boolean activo = true;

	@Override
	public void run() {
	    int matchesPrevios = -1;

	    while (activo) {
	        try {
	        	//evaluar cantidad anterior y cantidad despues del match, si la cantidad despues del match es la misma que la inicial, no ejecutar el thread
	            int totalAntes = Bolsa.getInstancia().getListaMatchOferta().size();
	            Bolsa.getInstancia().match();
	            int totalDespues = Bolsa.getInstancia().getListaMatchOferta().size();

	            // Si no cambió la cantidad, detenemos el hilo
	            if (totalAntes == totalDespues && matchesPrevios == totalDespues) {
	            	System.out.println("Sin nuevos matches. Hilo detenido.");
	                activo = false;
	                break;
	            }
	            matchesPrevios = totalDespues;
	            Thread.sleep(5000);
	        } catch (InterruptedException e) {
	        	 System.out.println("Hilo interrumpido.");
	            Thread.currentThread().interrupt();
	            break;
	        }
	    }
	    System.out.println("Hilo terminado");
	}
	//por si se desea detener el hilo manualmente
	public void detener() {
		activo = false;
	}

}
