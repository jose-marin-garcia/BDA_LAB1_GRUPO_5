package Laboratorio1BdaGrupo5.BackendLab1.entities;


import lombok.Data;

@Data
public class DetalleOrdenEntity {
    private Long idDetalle;
    private Integer idOrden;
    private Integer idProducto;
    private Integer cantidad;
    private Double precioUnitario;
}
