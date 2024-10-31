package Laboratorio1BdaGrupo5.BackendLab1.service;

import Laboratorio1BdaGrupo5.BackendLab1.models.Producto;
import Laboratorio1BdaGrupo5.BackendLab1.repository.ProductoRepository;
import Laboratorio1BdaGrupo5.BackendLab1.repository.ProductoRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepositoryImp productoRepository;


    public List<Producto> getAllProductos(int limit, int offset) {
        try {
            return productoRepository.getProductos(limit, offset);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la lista de productos", e);
        }
    }

    public Producto getProductoById(Integer idProducto) {
        try {
            Producto producto = productoRepository.getProductoById(idProducto);
            if (producto != null) {
                return producto;
            } else {
                throw new RuntimeException("Producto no encontrado");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el producto", e);
        }
    }

    public void createProducto(Producto producto) {
        try {
            productoRepository.createProducto(producto);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el producto", e);
        }
    }

    public void updateProducto(Producto producto) {
        try {
            // Verificar que el producto existe antes de actualizar
            getProductoById(producto.getIdProducto());
            productoRepository.updateProducto(producto);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el producto", e);
        }
    }

    public void deleteProducto(Integer idProducto) {
        try {
            // Verificar que el producto existe antes de eliminar
            getProductoById(idProducto);
            productoRepository.deleteProducto(idProducto);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el producto", e);
        }
    }

}
