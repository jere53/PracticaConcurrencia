package Parcial2020;

public class Main {
    public static void main(String[] args) {
        Parque parque = new Parque();
        for (int i = 0; i < 2; i++) {
            Thread coche = new Thread(new Coche(parque));
            coche.setDaemon(true);
            coche.start();
        }

        for (int i = 0; i < 20; i++) {
            new Thread(new Visitante(parque, i)).start();
        }
    }
}
