package Laboratorio1BdaGrupo5.BackendLab1.service;

import Laboratorio1BdaGrupo5.BackendLab1.model.Producto;
import Laboratorio1BdaGrupo5.BackendLab1.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public Producto getProductoById(Long id) {
        return productoRepository.getProductoById(id);
    }

}
