package tp4.ej7.conSemaforos;

public class Main {
    public static void main(String[] args) {
        Registro r = new Registro();
        for (int i = 0; i < 10; i++){
            Thread t = new Thread(()-> {
                while (true) {
                    try {
                        Thread.sleep(100);
                        r.read();
                    } catch (InterruptedException e){
                        return;
                    }
                }
            });
            t.setDaemon(true);
            t.start();
        }

        for (int i = 0; i < 3; i++){
            Thread t = new Thread(()-> {
                while (true) {
                    try {
                        Thread.sleep(100);
                        r.write((int) (Math.random() * 10) );
                    } catch (InterruptedException e){
                        return;
                    }
                }
            });
            t.start();
        }
    }
}
