package tp4.ej10;

public class Main {

    public static void main(String[] args) {
        Casa casa = new Casa();
        Blancanieves blancanieves = new Blancanieves(casa);
        Enanito[] enanitos = new Enanito[7];

        new Thread(blancanieves).start();

        for (int i = 0; i < enanitos.length; i++) {
            enanitos[i] = new Enanito(casa);
            new Thread(enanitos[i]).start();
        }
    }
}
