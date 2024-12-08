package pe.idat.Pedidos.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.idat.Pedidos.Model.EstadoPedido;
import pe.idat.Pedidos.Model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
    // Buscar pedidos por cliente
    List<Pedido> findByClienteContainingIgnoreCase(String cliente);

    // Buscar pedidos por estado
    List<Pedido> findByEstado(EstadoPedido estado);

    // Buscar pedidos realizados después de una fecha específica
    List<Pedido> findByFechaPedidoAfter(Date fecha);

    // Buscar pedidos realizados entre dos fechas
    List<Pedido> findByFechaPedidoBetween(Date fechaInicio, Date fechaFin);

    // Buscar pedidos por cliente y estado
    List<Pedido> findByClienteAndEstado(String cliente, EstadoPedido estado);
    // Buscar pedidos con un total mayor a un monto
    List<Pedido> findByTotalGreaterThan(BigDecimal total);
}
