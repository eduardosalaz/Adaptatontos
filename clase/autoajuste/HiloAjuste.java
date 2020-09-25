package clase;

public class HiloAjuste extends Thread {
	private HiloPercep hiloPercep;
    private Stealth stealth = new Stealth();
    

    public HiloAjuste(HiloPercep hiloPercep) {
        this.hiloPercep = hiloPercep;
    }

    public void run() {
        try {
            while (true) {
              
            	realizaAjuste();
            	sleep(3000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void realizaAjuste() {
        int distancia;
        float camuflaje;
        int hora;
        boolean finished;
        Condiciones con = hiloPercep.getCondiciones();
        distancia = con.getDistancia();
        camuflaje = con.getCamuflaje();
        hora = con.getHora();
        finished = con.isFinished();
        if(finished) {
            double resultado = stealth.difuso(distancia, camuflaje);
            mostrarAjuste(distancia, camuflaje, hora, resultado);
        }
        
    }

    private void mostrarAjuste(int distancia, float camuflaje, int hora, double resultado) {
        String mensaje = "Para la hora " + String.valueOf(hora) + " el agente cuenta con el grado de camuflaje "
                + String.valueOf(camuflaje) + " y a una distancia del enemigo de " + String.valueOf(distancia)
                + " el estado del enemigo está en " + String.valueOf(resultado);
        System.out.println(mensaje);
        if (resultado <= 35) {
            System.out.println("Estado es Patrullaje ");
          } else if (resultado > 35 && resultado <= 60) {
            System.out.println("Estado es Alerta ");
          } else if (resultado > 60 && resultado <= 85) {
            System.out.println("Estado es Encontrado ");
          } else if (resultado > 85) {
            System.out.println("Estado es Combate ");
          }
    }
}
