package Laboratorio1BdaGrupo5.BackendLab1.services;

import Laboratorio1BdaGrupo5.BackendLab1.entities.DetalleOrdenEntity;
import Laboratorio1BdaGrupo5.BackendLab1.repositorys.DetalleOrdenRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DetalleOrdenService {
    private final DetalleOrdenRepository repository;

    public DetalleOrdenService(DetalleOrdenRepository repository) {
        this.repository = repository;
    }

    public List<DetalleOrdenEntity> getAllDetalles() {
        return repository.getAll();
    }

    public DetalleOrdenEntity getDetalleById(Long id) {
        return repository.findById(id);
    }

    public Long createDetalle(DetalleOrdenEntity detalle) {
        return repository.insert(detalle);
    }

    public int updateDetalle(DetalleOrdenEntity detalle) {
        return repository.update(detalle);
    }

    public int deleteDetalle(Long id) {
        return repository.delete(id);
    }
}
