import controller.EmpleadosController;
import controller.PedidosController;
import controller.ProductosController;
import model.Empleado;

public class Entrada {

    public static void main(String[] args) {


        ProductosController controllerProductos = new ProductosController();
        EmpleadosController controllerEmpleados = new EmpleadosController();
        PedidosController controllerPedidos = new PedidosController();
        controllerProductos.importarProductos();
        controllerEmpleados.addEmpleado(new Empleado("Borja", "Martin","borja@ue.com"));
        controllerEmpleados.addEmpleado(new Empleado("Celia", "Martin","celia@ue.com"));
        controllerEmpleados.addEmpleado(new Empleado("Daniel", "Herrera","daniel@ue.com"));
        int idPedidio = 43;
        controllerPedidos.addPedido(controllerProductos.getProducto(idPedidio));
        controllerEmpleados.getEmpleados();
        controllerProductos.getAllProductos();


    }
}
