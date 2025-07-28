package logica;

public class MatchRunnable implements Runnable {

	private boolean activo = true;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (activo) {
			try {
				Bolsa.getInstancia().match(); //ejecutar metodo match
//				System.out.println("Matching realizado automáticamente.");
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				System.out.println("MatchingThread interrumpido.");
				activo = false;
			}
		}
	}

	public void detener() {
		activo = false;
	}

}
