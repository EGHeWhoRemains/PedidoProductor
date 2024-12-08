package pe.idat.Pedidos.Service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import pe.idat.Pedidos.Model.DetallePedido;
import pe.idat.Pedidos.Repository.DetallePedidoRepository;
import pe.idat.Pedidos.Service.DetallePedidoService;

@Service
@RequiredArgsConstructor
public class DetallePedidoServiceImpl implements DetallePedidoService {
    @Autowired 
    private DetallePedidoRepository detallePedidoRepository;
    @Override 
    @Transactional 
    public DetallePedido crearDetallePedido(DetallePedido detallePedido) { 
        return detallePedidoRepository.save(detallePedido);
    }
    @Override 
    @Transactional 
    public DetallePedido actualizarDetallePedido(DetallePedido detallePedido) { 
        return detallePedidoRepository.save(detallePedido);
    }
    @Override 
    @Transactional 
    public void eliminarDetallePedido(Integer idDetallePedido) { 
        detallePedidoRepository.deleteById(idDetallePedido); 
    
    }
    @Override 
    public List<DetallePedido> listarDetalles() { 
        return detallePedidoRepository.findAll(); 
    }
    @Override 
    public DetallePedido obtenerDetallePedidoPorId(Integer idDetallePedido) {
        return detallePedidoRepository.findById(idDetallePedido).orElse(null);
    }

    /*@Override
    public List<DetallePedido> obtenerDetallesPorPedido(Integer idPedido) {
        return List.of();
    }*/
    /*private final DetallePedidoRepository detallePedidoRepository;
    @Override
    public DetallePedido crearDetallePedido(DetallePedido detallePedido) {
        return detallePedidoRepository.save(detallePedido);
    }
    @Override
    public DetallePedido actualizarDetallePedido(Integer idDetallePedido, DetallePedido detallePedido) {
        DetallePedido detalleExistente = detallePedidoRepository.findById(idDetallePedido)
                .orElseThrow(() -> new RuntimeException("Detalle de pedido no encontrado"));

        detalleExistente.setCantidad(detallePedido.getCantidad());
        detalleExistente.setPrecioUnitario(detallePedido.getPrecioUnitario());
        detalleExistente.setEstado(detallePedido.getEstado());
        detalleExistente.setSubtotal(detallePedido.getSubtotal()); // Si se desea actualizar subtotal

        return detallePedidoRepository.save(detalleExistente);
    }
    @Override
    public List<DetallePedido> obtenerDetallesPorPedido(Integer idPedido) {
        return detallePedidoRepository.findByPedidoIdPedido(idPedido);
    }
    @Override
    public void eliminarDetallePedido(Integer idDetallePedido) {
        DetallePedido detalle = detallePedidoRepository.findById(idDetallePedido)
                .orElseThrow(() -> new RuntimeException("Detalle de pedido no encontrado"));
        detallePedidoRepository.delete(detalle);
    }*/
}
