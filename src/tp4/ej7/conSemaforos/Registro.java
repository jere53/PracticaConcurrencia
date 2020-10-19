package tp4.ej7.conSemaforos;

import java.util.concurrent.Semaphore;

public class Registro{
    Semaphore mutex = new Semaphore(1);
    Semaphore rwMutex = new Semaphore(1);
    int cantLectores = 0;
    int dato;

    public void write (int nuevoDato) throws InterruptedException {
        rwMutex.acquire();
        Thread.sleep(1000); // escribir
        dato = nuevoDato * 10;
        System.out.println("se escribio un: " + dato);
        rwMutex.release();
    }

    public void read() throws InterruptedException {
        mutex.acquire();
        cantLectores++;
        if (cantLectores == 1) {
            rwMutex.acquire();
        }
        mutex.release();
        Thread.sleep(1000); //leer
        System.out.println("la lectura da : " + dato);
        mutex.acquire();
        cantLectores--;
        if (cantLectores == 0) {
            rwMutex.release();
        }
        mutex.release();
    }
}
