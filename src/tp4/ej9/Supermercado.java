package tp4.ej9;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class Supermercado {
    private LinkedList<Producto> ventasPendientes = new LinkedList<>();
    private Semaphore clientes = new Semaphore(0);

    public synchronized void comprar(Producto compra){
        clientes.release();
        ventasPendientes.add(compra);
    }

    public void atender() {
        try {
            clientes.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized Producto getSiguienteCompra(){
        return ventasPendientes.remove(0);
    }

    public static void main(String[] args) {

        int n = 10;

        Supermercado s = new Supermercado();
        CanastaOfertas o = new CanastaOfertas();

        Thread[] cajas = new Thread[n];
        for  (int i = 0; i < n; i++) {
            cajas[i] = new Thread(new Caja(i, s, o));
        }

        Producto p = new Producto(1, 100);
        Producto p1 = new Producto(2, 10000);
        Producto p2 = new Producto(3, 1000);

        o.agregarProducto(p1);
        o.agregarProducto(p2);

        for (int i = 0; i < 100; i++) {
            s.comprar(p);
            s.comprar(p1);
            s.comprar(p2);
        }

        for (Thread t : cajas) {
            t.setDaemon(true);
            t.start();
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
