package tp4.ej10;

import java.util.concurrent.Semaphore;

public class Casa {
    private Semaphore silla = new Semaphore(4);
    private Semaphore enanoSentado = new Semaphore(0);
    private Semaphore enanoAlimentado = new Semaphore(0);
    private Semaphore mutex = new Semaphore(1);

    public void tratarDeSentarse() {
        try {
            mutex.acquire(); //forzamos a que un enano le avise a blancanieves ni bien se sienta para evitar deadlocks
            if (silla.tryAcquire()) {
                enanoSentado.release();
                System.out.println("el enano " + Thread.currentThread().getId() + " se sento y le aviso a blancanieves");
            }
            mutex.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void comer(Enanito enanito) {
        while (!enanito.isAlimentado()) {
            try {
                enanoAlimentado.acquire(); //que espere a ser alimentado.
                // No hay problema con los deadlocks porque blancanieves no tiene que esperar a este hilo si llego a sentarse.
                Thread.sleep(1000); //come
                enanito.setAlimentado(true);
                System.out.println("el enano " + Thread.currentThread().getId() + " comio y se fue");
                silla.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void servirComida() {
        try {
            enanoSentado.acquire();
            Thread.sleep(800); //sirve la comida
            enanoAlimentado.release();
            System.out.println("Blancanieves le dio de comer a un enano");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
