package tp4.ej8;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CorrectorOrtografico {
    //Es parecido al registro de lectores-escritores
    //Esta variante no tiene consistencia temporal. Para asegurar consistencia temporal se deberia priorizar a los
    //escritores.

    private Semaphore mutex = new Semaphore(1);
    private Semaphore rw_mutex = new Semaphore(1);
    private HashSet<String> diccionario = new HashSet<>();
    private int cantLectores = 0;

    public void cargarPalabra(String palabra) throws InterruptedException {
        rw_mutex.acquire();
        diccionario.add(palabra);
        System.out.println("se agrego \"" + palabra + "\" al diccionario");
        rw_mutex.release();
    }

    public void eliminarPalabra(String palabra) throws InterruptedException {
        rw_mutex.acquire();
        diccionario.remove(palabra);
        System.out.println("se elimino \"" + palabra + "\" del diccionario");
        rw_mutex.release();
    }

    public List<String> verificarPalabra(HashSet<String> palabras) throws InterruptedException {
        mutex.acquire();
        cantLectores++;
        if (cantLectores == 1) {
            rw_mutex.acquire();
        }
        mutex.release();
        List res = new LinkedList<String>();
        for (String palabra : palabras) {
            if (!diccionario.contains(palabra)){
                res.add(palabra);
            }
        }
        mutex.acquire();
        cantLectores--;
        if (cantLectores == 0) {
            rw_mutex.release();
        }
        mutex.release();
        return res;
    }

}
