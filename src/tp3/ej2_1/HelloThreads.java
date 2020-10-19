package tp3.ej2_1;

public class HelloThreads implements Runnable {
    private final int n;

    public HelloThreads(int n) {
        this.n = n;
    }

    @Override
    public void run() {
        for (int i = 1; i <= n; i++ ) {
            System.out.println("Hola Threads!. Soy el thread" +
                    Thread.currentThread().getId() + " ejecutando por " + i + "-esima vez");
        }
    }
}
