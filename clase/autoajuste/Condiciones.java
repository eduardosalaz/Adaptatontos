package clase.autoajuste;

public class Condiciones {
    private int camuflaje;
    private int distancia;
    private int hora;

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

    public void actualizaCondiciones(int hora) {
        int aleatorio;

        this.hora = hora;

        if (hora <= 2 || hora > 24) // 0-2, 25-34
            camuflaje = 99;
        else if (hora > 2 && hora < 7 || hora > 20 && hora <= 24) // 3-6, 21-24
            camuflaje = 80;
        else if (hora >= 7 && hora < 11 || hora >= 18 && hora < 21) // 7-10, 18-20
            camuflaje = 60;
        else // 11-17
            camuflaje = 30;
        aleatorio = generaNumAleatorio(0, 1);
        if (aleatorio == 1) {
            if (distancia < 144) {
                distancia += 5;
            }
        } else {
            if (distancia > 5) {
                distancia -= 5;
            }
        }

    }

    public int getCamuflaje() {
        return camuflaje;
    }

    public int getHora() {
        return hora;
    }

    public int getDistancia() {
        return distancia;
    }

}
