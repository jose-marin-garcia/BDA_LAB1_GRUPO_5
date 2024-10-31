package Laboratorio1BdaGrupo5.BackendLab1.repository;

import Laboratorio1BdaGrupo5.BackendLab1.models.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoRepository {

    List<Producto> getProductos(int limit, int offset); // Para paginación
    Producto getProductoById(Integer idProducto);
    void createProducto(Producto producto);
    void updateProducto(Producto producto);
    void deleteProducto(Integer idProducto);

}
