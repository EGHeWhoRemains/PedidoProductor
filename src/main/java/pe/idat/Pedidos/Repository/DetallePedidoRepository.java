package pe.idat.Pedidos.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.idat.Pedidos.Model.DetallePedido;
import pe.idat.Pedidos.Model.EstadoDetallePedido;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer>{
    // Buscar detalles de pedido por ID de pedido
    List<DetallePedido> findByPedidoIdPedido(Integer idPedido);

    // Buscar detalles de pedido por ID de producto
    //List<DetallePedido> findByProductoIdProducto(Integer idProducto);

    // Buscar detalles activos de un pedido específico
    List<DetallePedido> findByPedidoIdPedidoAndEstado(Integer idPedido, EstadoDetallePedido estado);

    // Buscar detalles cancelados
    List<DetallePedido> findByEstado(EstadoDetallePedido estado);
}
