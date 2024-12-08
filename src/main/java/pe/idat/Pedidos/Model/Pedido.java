package pe.idat.Pedidos.Model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Integer idPedido;
    @Column(name = "fecha_pedido", nullable = false)
    private java.time.LocalDateTime fechaPedido;
    @Column(name = "cliente", nullable = false)
    private String cliente;
    @Column(name = "direccion_entrega", length = 255)
    private String direccionEntrega;
    @Column(name = "total", nullable = false, precision = 10, scale = 2)
    private BigDecimal total;
    @Column(name = "estado", nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoPedido estado = EstadoPedido.Pendiente;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<DetallePedido> detalles = new ArrayList<>();
}
