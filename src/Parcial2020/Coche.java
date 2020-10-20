public class Coche implements Runnable {
    private Parque parque;

    public Coche(Parque parque) {
        this.parque = parque;
    }


    @Override
    public void run() {
        while (true) {
            Visitante[] pasajeros = parque.esperarPasajeros(); //consigue los pasajeros

            parque.circular(); //pasea

            for (Visitante visitante : pasajeros) {
                visitante.setFueDePaseo(true);
            }
        }

    }
}
