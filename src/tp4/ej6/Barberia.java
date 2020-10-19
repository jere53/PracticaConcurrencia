package tp4.ej6;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Barberia {
    List<Cliente> sillas = new LinkedList<>();
    int maxSillas;
    Lock lock = new ReentrantLock();
    Condition clientesEsperando = lock.newCondition();

    public Barberia(int maxSillas) {
        this.maxSillas = maxSillas;
    }

    public boolean intentarSentarse(Cliente cliente){
        lock.lock();
        try {
            if (sillas.size() >= maxSillas){
                return false;
            }
            sillas.add(cliente);
            clientesEsperando.signal();
            return true;
        } finally {
            lock.unlock();
        }
    }

    public Cliente obtenerSiguiente(){
        lock.lock();
        try {
            while (sillas.isEmpty()){
                clientesEsperando.await();
            }
            return sillas.remove(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } finally {
            lock.unlock();
        }
    }
}
