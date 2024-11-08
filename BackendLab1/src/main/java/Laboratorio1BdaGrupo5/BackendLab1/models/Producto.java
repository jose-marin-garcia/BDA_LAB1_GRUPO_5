package Laboratorio1BdaGrupo5.BackendLab1.models;




import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "\"producto\"")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"id_producto\"")
    private Integer idProducto;

    private String nombre;

    private String descripcion;

    private Double precio;

    private Integer stock;

    private String estado;

    @Column(name = "id_categoria")
    private Integer idCategoria;

}