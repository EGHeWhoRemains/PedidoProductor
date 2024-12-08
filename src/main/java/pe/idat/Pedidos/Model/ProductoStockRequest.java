package pe.idat.Pedidos.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductoStockRequest {
    private Integer idProducto;
    private Integer cantidad;
}
