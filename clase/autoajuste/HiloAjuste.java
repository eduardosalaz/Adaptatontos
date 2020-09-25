package clase.autoajuste;

public class HiloAjuste extends Thread {
    private HiloPercep hiloPercep;
    private Stealth stealth;

    public HiloAjuste(HiloPercep hiloPercep) {
        this.hiloPercep = hiloPercep;
    }

    public void run() {

        try {
            while (true) {
                realizaAjuste();
                sleep(2000);
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void realizaAjuste() {
        int distancia;
        int camuflaje;
        int hora;
        Condiciones con = hiloPercep.getCondiciones();
        distancia = con.getDistancia();
        camuflaje = con.getCamuflaje();
        hora = con.getHora();
        double resultado = stealth.realizarAjuste(distancia, camuflaje);
        mostrarAjuste(distancia, camuflaje, hora, resultado);
    }

    private void mostrarAjuste(int distancia, int camuflaje, int hora, double resultado) {
        String mensaje = "Para la hora " + String.valueOf(hora) + " el agente cuenta con el grado de camuflaje "
                + String.valueOf(camuflaje) + " y a una distancia del enemigo de " + String.valueOf(distancia)
                + " el estado del enemigo está en " + String.valueOf(resultado);
        System.out.println(mensaje);
    }
}
