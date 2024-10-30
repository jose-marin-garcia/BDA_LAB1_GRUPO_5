package Laboratorio1BdaGrupo5.BackendLab1.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleOrden {
    private Integer idDetalle;
    private Integer idOrden;
    private Integer idProducto;
    private Integer cantidad;
    private BigDecimal precioUnitario;

    public DetalleOrden(Integer idOrden, Integer idProducto){
        this.idOrden = idOrden;
        this.idProducto = idProducto;
    }
}
