package clase.autoajuste;

public class HiloPercep extends Thread {
    private Condiciones con;
    private int hora;

    public HiloPercep() {
        hora = 0;
        con = new Condiciones(hora);
    }

    public Condiciones getCondiciones() {
        return con;
    }

    public void run() {
        try {
            while (true) {
                hora++;
                con.actualizaCondiciones(hora);// checar que onda con el 35
                sleep(3000);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
