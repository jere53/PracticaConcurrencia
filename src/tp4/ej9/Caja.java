package tp4.ej9;

public class Caja implements Runnable{
    private int nroCaja;
    private Supermercado supermercado;
    private CanastaOfertas ofertas;

    public Caja(int nroCaja, Supermercado supermercado, CanastaOfertas ofertas) {
        this.nroCaja = nroCaja;
        this.supermercado = supermercado;
        this.ofertas = ofertas;
    }

    @Override
    public void run() {
        while (true) {
            supermercado.atender();
            Producto p = supermercado.getSiguienteCompra();
            if (ofertas.pertenece(p)) {
                System.out.println("se vendio el producto " + p.getIdProd() + " a $" + (0.9 * p.getPrecioProd()));
            } else {
                System.out.println("se vendio el producto " + p.getIdProd() + " a $" + p.getPrecioProd());
            }
        }
    }
}
