package tp4.ej6;

public class Main {
    public static void main(String[] args) {
        Barberia b = new Barberia(5);
        Thread barbero = new Thread(new Barbero(b));
        barbero.setDaemon(true);
        barbero.start();
        for (int i = 0; i < 10; i++){
            Thread thread = new Thread(new Cliente(i, b));
            thread.start();
        }
    }
}
