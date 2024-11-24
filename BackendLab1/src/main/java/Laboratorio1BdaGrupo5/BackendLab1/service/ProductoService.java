package Laboratorio1BdaGrupo5.BackendLab1.service;

import Laboratorio1BdaGrupo5.BackendLab1.models.Categoria;
import Laboratorio1BdaGrupo5.BackendLab1.models.Producto;
import Laboratorio1BdaGrupo5.BackendLab1.repository.ProductoRepository;
import Laboratorio1BdaGrupo5.BackendLab1.repository.ProductoRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepositoryImp productoRepository;

    @Autowired CategoriaService categoriaService;


    public List<Producto> getAllProductos(int limit, int offset, String search) {
        try {
            if (search.isEmpty()) {
                return productoRepository.getProductos(limit, offset);
            } else {
                return productoRepository.getProductosSearch(limit, offset, search);
            }
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

    public List<Producto> getProductosPorCategoria(Integer idCategoria) {
        try {
            return productoRepository.getProductosPorCategoria(idCategoria);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la lista de productos por categoría", e);
        }
    }

    public List<Producto> getProductosPorCategoria(String nombre){
        try {
            List<Categoria> categorias = categoriaService.searchCategoria(nombre);
            List<Producto> productos = new ArrayList<>();
            for (Categoria categoria : categorias) {
                productos.addAll(getProductosPorCategoria(categoria.getIdCategoria()));
            }
            return productos;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener productos por categoria", e);
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
            getProductoById(producto.getIdProducto());
            productoRepository.updateProducto(producto);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el producto", e);
        }
    }

    public void deleteProducto(Integer idProducto) {
        try {
            getProductoById(idProducto);
            productoRepository.deleteProducto(idProducto);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el producto", e);
        }
    }

    public long getTotalCount() {
        try {
            return productoRepository.getTotalCount();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el total de productos", e);
        }
    }

    public String getProductoName(Integer id) {
        try {
            return productoRepository.getProductoName(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el nombre del producto", e);
        }
    }

    public Producto getMostVariablePriceProduct() {
        try {
            Producto p = productoRepository.getMostVariablePriceProduct();
            if (p == null) {
                return productoRepository.getFirstProducto();
            }
            return productoRepository.getMostVariablePriceProduct();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el producto con precio más variable", e);
        }
    }
}
