package tp3.ej2_1;

public class Main {

    public static void main(String[] args) {
        HelloThreads h1 = new HelloThreads(10000);
        Thread t1 = new Thread(h1);
        HelloThreads h2 = new HelloThreads(10000);
        Thread t2 = new Thread(h2);
        HelloThreads h3 = new HelloThreads(10000);
        Thread t3 = new Thread(h3);
        HelloThreads h4 = new HelloThreads(10000);
        Thread t4 = new Thread(h4);
        HelloThreads h5 = new HelloThreads(10000);
        Thread t5 = new Thread(h5);
        HelloThreads h6 = new HelloThreads(10000);
        Thread t6 = new Thread(h6);
        HelloThreads h7 = new HelloThreads(10000);
        Thread t7 = new Thread(h7);
        HelloThreads h8 = new HelloThreads(10000);
        Thread t8 = new Thread(h8);
        HelloThreads h9 = new HelloThreads(10000);
        Thread t9 = new Thread(h9);
        HelloThreads h10 = new HelloThreads(10000);
        Thread t10 = new Thread(h10);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();
    }
}
