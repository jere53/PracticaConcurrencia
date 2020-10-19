package tp4.ej11;

import java.util.concurrent.Semaphore;

public class ClubVinos {
    private Semaphore reponerIngredientes = new Semaphore(0);
    private Semaphore jarra = new Semaphore(6); // 10lts c/u
    private Semaphore estacionMezcla = new Semaphore(2);
    private Semaphore unidadesFermentacion = new Semaphore(7);
    private Semaphore envasesJugo = new Semaphore(15); //5 lts c/u
    private Semaphore packsLevadura = new Semaphore(20); //10 lts c/u

    public void conseguirJarras() {
        try {
            jarra.acquire(2); //que agarre ambas para evitar deadlocks.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void mezclarVino() {
        try {
            estacionMezcla.acquire();
            envasesJugo.acquire(2); //agarra 2 para hacer 10 lts (la capacidad de la jarra)
            packsLevadura.acquire();
            reponerIngredientes.release(); // que se habilite al admin reponer ingredientes.
            estacionMezcla.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void fermentarVino() {
        try {
            unidadesFermentacion.acquire();
            Thread.sleep(4000); // que espere 4 semanas
            unidadesFermentacion.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void decantarVino() {
        try {
            Thread.sleep(500); //que lo decante
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        jarra.release(2); //libera las jarras que uso.
    }

    public void reponer() {
        try {
            reponerIngredientes.acquire(); //si hay que reponer ingredientes...
            synchronized (this) {
                //se fija cuanto hay que reponer de c/u
                //esta mal poner el numero maximo constante y no usar una variable para el 15 y 20
                //desde un punto de vista de disenio, pero deberia andar igual para este enunciado
                int repEnvJug = 15 - envasesJugo.availablePermits();
                int repPaqLev = 20 - packsLevadura.availablePermits();
                envasesJugo.release(repEnvJug);
                packsLevadura.release(repPaqLev);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void realizarCata(MiembroClub m) {
        try {
            Thread.sleep(300);
            m.setProbaronSuVino(true);
            System.out.println("Se probo el vino del miembro " + Thread.currentThread().getId());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
