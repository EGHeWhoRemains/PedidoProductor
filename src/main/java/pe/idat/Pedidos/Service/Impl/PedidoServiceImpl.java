package pe.idat.Pedidos.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import pe.idat.Pedidos.Kafka.StockProducer;
import pe.idat.Pedidos.Model.DetallePedido;
import pe.idat.Pedidos.Model.EstadoPedido;
import pe.idat.Pedidos.Model.Pedido;
import pe.idat.Pedidos.Model.ProductoStockRequest;
import pe.idat.Pedidos.Repository.DetallePedidoRepository;
//import pe.idat.Pedidos.Repository.DetallePedidoRepository;
import pe.idat.Pedidos.Repository.PedidoRepository;
import pe.idat.Pedidos.Service.PedidoService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {
    private final PedidoRepository pedidoRepository;
    @Autowired
    private DetallePedidoRepository detallePedidoRepository;
    @Autowired
    private StockProducer stockProducer;
    public List<Pedido> listarPedidos(){

        return pedidoRepository.findAll();
    }
    @Override
    public Pedido obtenerPedidoPorId(Integer idPedido) {
        return pedidoRepository.findById(idPedido).orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
    }
    @Override
    public Pedido crearPedido(Pedido pedido) {
        pedido.setFechaPedido(LocalDateTime.now());
        pedidoRepository.save(pedido);
        for (DetallePedido detalle : pedido.getDetalles()) {
            Integer idProducto = detalle.getIdProducto();
            Integer cantidad = detalle.getCantidad();
            ProductoStockRequest stockRequest = new ProductoStockRequest(idProducto, cantidad);
            stockProducer.sendStockVerificationRequest(stockRequest);
            System.out.println("Pedido creado y mensaje enviado a Kafka.");
        }
        return pedido;
    }
    @Override 
    @Transactional 
    public void quitarProductoDelPedido(Integer idPedido, Integer idDetallePedido) { 
        Optional<DetallePedido> optionalDetalle = detallePedidoRepository.findById(idDetallePedido); 
        if (optionalDetalle.isPresent() && optionalDetalle.get().getPedido().getIdPedido().equals(idPedido)) { 
            detallePedidoRepository.deleteById(idDetallePedido);
        } else { 
            throw new RuntimeException("DetallePedido no encontrado o no pertenece al pedido especificado");
        } 
    }
    @Override
    public Pedido actualizarPedido(Integer idPedido, Pedido pedido) {
        // Validamos si el pedido existe
        Pedido pedidoExistente = pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        // Actualizamos las propiedades
        pedidoExistente.setCliente(pedido.getCliente());
        pedidoExistente.setDireccionEntrega(pedido.getDireccionEntrega());
        pedidoExistente.setTotal(pedido.getTotal());
        pedidoExistente.setEstado(pedido.getEstado());
        pedidoExistente.setDetalles(pedido.getDetalles()); // Actualizamos los detalles

        return pedidoRepository.save(pedidoExistente);
    }
    @Override
    public Pedido actualizarEstado(Integer idPedido, EstadoPedido estado){
        Pedido pedido = pedidoRepository.findById(idPedido).orElseThrow(() -> new EntityNotFoundException("Pedido no encontrado"));
        pedido.setEstado(estado);
        return pedidoRepository.save(pedido);
    }
    @Override
    public boolean eliminarPedido(Integer idPedido){
        if (pedidoRepository.existsById(idPedido)) {
            pedidoRepository.deleteById(idPedido);
            return true;
        }
        return false;
    }
}
