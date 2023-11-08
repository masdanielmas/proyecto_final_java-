import java.util.ArrayList;
import java.util.List;

class Factura {
    private List<Producto> productos;
    private double total;

    public Factura() {
        this.productos = new ArrayList<>();
        this.total = 0.0;
    }

    public void agregarProducto(Producto producto, int cantidad) {
        productos.add(producto);
        total += producto.getPrecio() * cantidad;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public double getTotal() {
        return total;
    }
}