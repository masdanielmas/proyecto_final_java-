import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Producto> listaDeProductos = new ArrayList<>();
        Inventario inventario = new Inventario();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Ingrese el nombre del producto (o '0' para terminar): ");
            String nombre = scanner.nextLine();
            if (nombre.equals("0")) {
                break;
            }

            System.out.print("Ingrese la cantidad de productos: ");
            int cantidad = Integer.parseInt(scanner.nextLine());

            System.out.print("Ingrese el precio del producto: ");
            double precio = Double.parseDouble(scanner.nextLine());

            System.out.print("Ingrese el código del artículo: ");
            int plu = Integer.parseInt(scanner.nextLine());

            Producto producto = new Producto(nombre, precio, cantidad, plu);
            listaDeProductos.add(producto);
            inventario.agregarProducto(producto);
        }

        Factura factura = new Factura();

        while (true) {
            System.out.println("\nMenú de opciones:");
            System.out.println("1. Consultar producto por código");
            System.out.println("2. Facturar");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            String opcion = scanner.nextLine();

            if (opcion.equals("1")) {
                System.out.print("Ingrese el código del artículo a consultar: ");
                int codigo = Integer.parseInt(scanner.nextLine());
                Producto producto = consultarProducto(listaDeProductos, codigo);
                if (producto != null) {
                    System.out.println("Datos del producto:");
                    System.out.println(producto);
                } else {
                    System.out.println("Código de artículo no encontrado.");
                }
            } else if (opcion.equals("2")) {
                facturar(listaDeProductos, inventario, factura, scanner);
            } else if (opcion.equals("3")) {
                break;
            } else {
                System.out.println("Opción no válida. Intente nuevamente.");
            }
        }

        System.out.println("Gracias por usar el programa.");
    }

    public static Producto consultarProducto(List<Producto> listaDeProductos, int codigo) {
        for (Producto producto : listaDeProductos) {
            if (producto.getPlu() == codigo) {
                return producto;
            }
        }
        return null;
    }

    public static void facturar(List<Producto> listaDeProductos, Inventario inventario, Factura factura, Scanner scanner) {
        while (true) {
            System.out.print("Ingrese el código del artículo a agregar a la factura (0 para salir): ");
            int codigo = Integer.parseInt(scanner.nextLine());
            if (codigo == 0) {
                break;
            }

            Producto producto = consultarProducto(listaDeProductos, codigo);
            if (producto != null) {
                System.out.print("Ingrese la cantidad de '" + producto.toString() + "' a facturar: ");
                int cantidadAFacturar = Integer.parseInt(scanner.nextLine());

                int stockDisponible = inventario.obtenerStock(codigo);

                if (cantidadAFacturar <= stockDisponible && cantidadAFacturar > 0) {
                    boolean stockActualizado = inventario.actualizarStock(codigo, cantidadAFacturar);

                    if (stockActualizado) {
                        factura.agregarProducto(producto, cantidadAFacturar);
                        System.out.println("Agregado a la factura: " + cantidadAFacturar + " unidades de '" + producto.toString() + "'");
                    } else {
                        System.out.println("No hay suficientes unidades disponibles de '" + producto.toString() + "'.");
                    }
                } else {
                    System.out.println("Cantidad no válida. Intente nuevamente.");
                }
            } else {
                System.out.println("Código de artículo no encontrado. Intente nuevamente.");
            }
        }

        System.out.println("\nFactura:");
        for (Producto producto : factura.getProductos()) {
            System.out.printf("%d unidades de '%s': $%.2f%n", producto.getCantidad(), producto.toString(), producto.getPrecio() * producto.getCantidad());
        }
        System.out.printf("Total: $%.2f%n", factura.getTotal());
    }
}