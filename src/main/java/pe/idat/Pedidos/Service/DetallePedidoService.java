package pe.idat.Pedidos.Service;

import java.util.List;
import java.util.Optional;

import pe.idat.Pedidos.Model.DetallePedido;

public interface DetallePedidoService {
    DetallePedido crearDetallePedido(DetallePedido detallePedido); 
    DetallePedido actualizarDetallePedido(DetallePedido detallePedido); 
    void eliminarDetallePedido(Integer idDetallePedido); 
    List<DetallePedido> listarDetalles(); 
    DetallePedido obtenerDetallePedidoPorId(Integer idDetallePedido);
    /*DetallePedido crearDetallePedido(DetallePedido detallePedido);
    DetallePedido actualizarDetallePedido(Integer idDetallePedido, DetallePedido detallePedido);
    List<DetallePedido> obtenerDetallesPorPedido(Integer idPedido);
    void eliminarDetallePedido(Integer idDetallePedido);*/
}
