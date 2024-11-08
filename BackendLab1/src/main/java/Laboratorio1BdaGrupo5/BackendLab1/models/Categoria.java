package Laboratorio1BdaGrupo5.BackendLab1.models;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {

    @Column(name = "id_categoria")
    private Integer idCategoria;
    private String nombre;
}
