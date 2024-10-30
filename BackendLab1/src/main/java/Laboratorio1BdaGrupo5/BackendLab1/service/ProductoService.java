package Laboratorio1BdaGrupo5.BackendLab1.service;

import Laboratorio1BdaGrupo5.BackendLab1.models.Producto;
import Laboratorio1BdaGrupo5.BackendLab1.repository.ProductoRepository;
import Laboratorio1BdaGrupo5.BackendLab1.repository.ProductoRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepositoryImp productoRepository;

    public Producto getProductoById(Long id) {
        return productoRepository.getProductoById(id);
    }

}
