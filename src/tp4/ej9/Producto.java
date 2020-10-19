package tp4.ej9;

import java.util.Objects;

public class Producto {
    int idProd;
    int precioProd;
    int minStock;
    int stockActual;

    public Producto(int idProd, int precioProd) {
        this.idProd = idProd;
        this.precioProd = precioProd;
    }

    public int getStockActual() {
        return stockActual;
    }

    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }

    public Producto(int idProd, int precioProd, int minStock) {
        this.idProd = idProd;
        this.precioProd = precioProd;
        this.minStock = minStock;
    }

    public int getIdProd() {
        return idProd;
    }

    public int getPrecioProd() {
        return precioProd;
    }

    public int getMinStock() {
        return minStock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return idProd == producto.idProd &&
                precioProd == producto.precioProd &&
                minStock == producto.minStock;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProd, precioProd, minStock);
    }
}
