public class Visitante implements Runnable{
    private Parque parque;
    private int id;
    private boolean fueDePaseo = false;

    public Visitante(Parque parque, int id) {
        this.parque = parque;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public synchronized void setFueDePaseo(boolean fueDePaseo) {
        this.fueDePaseo = fueDePaseo;
        notify();
    }

    private synchronized void esperarPaseo () {
        while (!fueDePaseo) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
            try {
                Thread.sleep((long) (Math.random() * 3000)); //deambula por el museo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("el visitante " + id + " se mete en la fila");
            parque.irDePaseo(this);
            esperarPaseo();
            System.out.println("el visitante " + id + " volvio de su paseo");
    }
}