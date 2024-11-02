package Laboratorio1BdaGrupo5.BackendLab1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    private Integer id_producto;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
    private String estado;
    private Integer id_categoria;

}