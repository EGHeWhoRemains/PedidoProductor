package pe.idat.Pedidos.Service;

import java.util.List;
import java.util.Optional;

import pe.idat.Pedidos.Model.EstadoPedido;
import pe.idat.Pedidos.Model.Pedido;

public interface PedidoService {
    List<Pedido> listarPedidos();
    Pedido obtenerPedidoPorId(Integer idPedido);
    Pedido crearPedido(Pedido pedido);
    Pedido actualizarPedido(Integer idPedido, Pedido pedido);
    boolean eliminarPedido(Integer idPedido);
    Pedido actualizarEstado(Integer idPedido, EstadoPedido estado);
    void quitarProductoDelPedido(Integer idPedido, Integer idDetallePedido);
    //List<Pedido> buscarPorEstado(EstadoPedido estado);
    //List<Pedido> buscarPorCliente(String cliente);
}
