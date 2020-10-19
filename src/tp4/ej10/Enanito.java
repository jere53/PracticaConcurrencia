package tp4.ej10;

public class Enanito implements Runnable {
    private Casa casa;
    private boolean alimentado;

    public Enanito(Casa casa) {
        this.casa = casa;
    }

    public boolean isAlimentado() {
        return alimentado;
    }

    public void setAlimentado(boolean alimentado) {
        this.alimentado = alimentado;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10000); //trabaja
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            alimentado = false;

            casa.tratarDeSentarse();
            casa.comer(this);

        }
    }
}
