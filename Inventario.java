import java.util.HashMap;
import java.util.Map;

class Inventario {
    private Map<Integer, Integer> inventario;

    public Inventario() {
        this.inventario = new HashMap<>();
    }

    public void agregarProducto(Producto producto) {
        inventario.put(producto.getPlu(), producto.getCantidad());
    }

    public int obtenerStock(int codigo) {
        return inventario.getOrDefault(codigo, 0);
    }

    public boolean actualizarStock(int codigo, int cantidad) {
        int stockActual = inventario.getOrDefault(codigo, 0);
        if (stockActual >= cantidad) {
            inventario.put(codigo, stockActual - cantidad);
            return true;
        }
        return false;
    }
}
