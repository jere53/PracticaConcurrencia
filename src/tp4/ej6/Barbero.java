package tp4.ej6;

public class Barbero implements Runnable{
    private Barberia barberia;

    public Barbero(Barberia barberia) {
        this.barberia = barberia;
    }

    @Override
    public void run() {
        while (true) {
            //asumimos que si no hay un siguiente se pone a dormir.
            Cliente cliente = barberia.obtenerSiguiente();
            System.out.println("El barbero atiende al cliente " + cliente.getId());
            try {
                Thread.sleep((long) (2000 * Math.random() + 100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("El barbero le corto el pelo al cliente " + cliente.getId());
            cliente.setAtendido(true);
        }
    }
}
