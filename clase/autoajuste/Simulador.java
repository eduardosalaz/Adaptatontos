package clase;

public class Simulador {
	public int camuflaje;
    public int distancia;

    public static void main(String args[]) {
        System.out.println();
        System.out.println("=== INICIANDO SIMULADOR DE STEALTH ===");
        System.out.println();
        System.out.println();

        HiloPercep percepcion = new HiloPercep();
        percepcion.start();
        HiloAjuste accion = new HiloAjuste(percepcion);
        accion.start();

    }

}
