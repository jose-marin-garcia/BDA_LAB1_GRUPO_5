package Laboratorio1BdaGrupo5.BackendLab1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orden {

    private Integer id_orden;
    private Date fecha_orden;
    private String estado;
    private Integer id_cliente;
    private Float total;
}
