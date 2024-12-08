package pe.idat.Pedidos.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pe.idat.Pedidos.Model.Pedido;
import pe.idat.Pedidos.Service.PedidoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("api/pedidos")
@RequiredArgsConstructor
public class PedidoController {
    private final PedidoService pedidoService;
    @GetMapping("/")
    public ResponseEntity<List<Pedido>> listarPedidos() {
        List<Pedido> pedidos = pedidoService.listarPedidos();
        return ResponseEntity.ok(pedidos);
    }
    @GetMapping("/{idPedido}")
    public ResponseEntity<Pedido> obtenerPedidoPorId(@PathVariable Integer idPedido) {
        Pedido pedido = pedidoService.obtenerPedidoPorId(idPedido);
        if (pedido == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedido);
    }
    @PostMapping("/")
    public ResponseEntity<Pedido> crearPedido(@RequestBody Pedido pedido) {
        Pedido nuevoPedido = pedidoService.crearPedido(pedido);
        String mensaje = "Nuevo Pedido creado: "+pedido.getIdPedido();
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPedido);
    }
    @PutMapping("/{idPedido}")
    public ResponseEntity<Pedido> actualizarPedido(@PathVariable Integer idPedido, @RequestBody Pedido pedido) {
        Pedido pedidoActualizado = pedidoService.actualizarPedido(idPedido, pedido);
        if (pedidoActualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedidoActualizado);
    }
    @DeleteMapping("/{idPedido}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable Integer idPedido){
        boolean eliminado = pedidoService.eliminarPedido(idPedido);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
