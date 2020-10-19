package tp4.ej9;

import java.util.HashSet;
import java.util.Set;

public class CanastaOfertas {
    private Set<Producto> ofertas = new HashSet<>();

    public CanastaOfertas() {
    }

    public CanastaOfertas(Set<Producto> ofertas) {
        this.ofertas = ofertas;
    }

    public synchronized void agregarProducto(Producto p) {
        ofertas.add(p);
    }

    public synchronized void quitarProducto(Producto p) {
        ofertas.remove(p);
    }

    public synchronized boolean pertenece(Producto p) {
        return ofertas.contains(p);
    }
}
