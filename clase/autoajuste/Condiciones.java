package clase;

public class Condiciones {
	private float camuflaje;
    private int distancia = 80;
    private int hora;
    public boolean finished = false;
    
    public boolean isFinished() {
		return finished;
	}


	public Condiciones(int horaInicial) {
        actualizaCondiciones(horaInicial);
    }

    public int generaNumAleatorio(int limInf, int limSup) {
        double valor;
        Double resultado;

        valor = Math.floor(Math.random() * (limSup - limInf + 1)) + limInf;

        resultado = new Double(valor);

        return resultado.intValue();
    }
    public float generaNumAleatorioF(int limInf, int limSup) {
        double valor;
        Double resultado;

        valor = Math.floor(Math.random() * (limSup - limInf + 1)) + limInf;

        resultado = new Double(valor);

        return resultado.floatValue();
    }
    

    public void actualizaCondiciones(int hora) {
        int aleatorio;

        this.hora = hora;
        //TODO arreglar esta cagada

        if (hora <= 2) // 0-2, 25-34
            camuflaje *= 1.2;
        else if (hora > 2 && hora < 7 || hora > 20 && hora <= 24) // 3-6, 21-24
            camuflaje *= 1.1;
        else if (hora >= 7 && hora < 11 || hora >= 18 && hora < 21) // 7-10, 18-20
            camuflaje *=  0.95;
        else // 11-17
            camuflaje *=  0.89;
        aleatorio = generaNumAleatorio(0, 1);
        if (aleatorio == 1) {
            if (distancia < 144) {
                distancia += 7;
            }
        } else {
            if (distancia > 5) {
                distancia -= 7;
            }
        }
        finished = true;

    }

    public float getCamuflaje() {
        return camuflaje;
    }

    public int getHora() {
        return hora;
    }

    public int getDistancia() {
        return distancia;
    }
}
