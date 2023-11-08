class Producto {
    private String nombre;
    private int cantidad;
    private double precio;
    private int plu;

    public Producto(String nombre, double precio, int cantidad, int plu) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.plu = plu;
    }

    public int getPlu() {
        return plu;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Cantidad: " + cantidad + ", Precio: $" + String.format("%.2f", precio) + ", Código: " + plu;
    }
}