package tp4.ej10;

public class Blancanieves implements Runnable{
    private Casa casa;

    public Blancanieves(Casa casa) {
        this.casa = casa;
    }

    @Override
    public void run() {
        while (true) {
            casa.servirComida(); //si no hay enanos se va a pasear (bloquea)
        }

    }
}
