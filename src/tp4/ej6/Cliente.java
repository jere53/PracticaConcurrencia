package tp4.ej6;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cliente implements Runnable{
    private int id;
    private boolean atendido = false;
    private Barberia barberia;

    public Cliente(int id, Barberia barberia) {
        this.id = id;
        this.barberia = barberia;
    }

    public int getId() {
        return id;
    }

    public void setAtendido(boolean atendido) {
        this.atendido = atendido;
    }

    private synchronized void esperarCorte(){
        while (!atendido) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        System.out.println("El cliente " + id + " llega a la barberia");
        boolean pudoSentarse = barberia.intentarSentarse(this);
        if (!pudoSentarse) {
            System.out.println("El cliente " + id + " se fue porque no habia lugar");
            return;
        }
        System.out.println("El cliente " + id + " espera a ser atendido");
        this.esperarCorte();
        System.out.println("El cliente " + id + " se va con su corte");
    }
}
