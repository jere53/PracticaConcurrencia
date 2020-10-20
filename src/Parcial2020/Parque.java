package Parcial2020;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Parque {
    private List<Visitante> filaPaseos = new LinkedList<>();
    private Semaphore fila = new Semaphore(0);
    private Semaphore mutex = new Semaphore(1);
    private Semaphore tecnico = new Semaphore(1);

    public Visitante[] esperarPasajeros() {
        try {
            fila.acquire(4);
            mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Visitante [] res = new Visitante[4];
        for (int i = 0; i < 4; i++) {
            res[i] = filaPaseos.remove(0);
            System.out.println("el visitante " + res[i].getId() + " se fue de paseo");
        }
        mutex.release();
        return res;
    }

    private void llamarTecnico() throws InterruptedException {
        tecnico.acquire();
        Thread.sleep(1000);
        System.out.println("se rompio el coche " + Thread.currentThread().getId());
        tecnico.release();
    }

    public void circular () {
        try {
            Thread.sleep((long) (Math.random() * 2000)); //pasea un rato
            boolean seRompe = (Math.random() < 0.4);
            if (seRompe) {
                 llamarTecnico();
            }
            Thread.sleep((long) (Math.random() * 2000)); //sigue paseando
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void irDePaseo (Visitante visitante) {
        try {
            mutex.acquire();
            filaPaseos.add(visitante);
            fila.release();
            mutex.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
