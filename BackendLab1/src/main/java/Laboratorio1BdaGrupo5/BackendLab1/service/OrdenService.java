package Laboratorio1BdaGrupo5.BackendLab1.service;

import Laboratorio1BdaGrupo5.BackendLab1.models.Orden;
import Laboratorio1BdaGrupo5.BackendLab1.repository.OrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdenService {
    @Autowired
    private OrdenRepository ordenRepository;
    
    public List<Orden> getAllOrdenes(int limit, int offset) {
        try {
            return ordenRepository.getOrdenes(limit, offset);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la lista de ordenes", e);
        }       
    }
    
    public Orden getOrdenById(Integer idOrden) {
        try {
            Orden orden = ordenRepository.getOrdenById(idOrden);
            if (orden != null) {
                return orden;
            } else {
                throw new RuntimeException("Orden no encontrada");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la orden", e);
        }
    }

    public void createOrden(Orden orden) {
        try {
            ordenRepository.createOrden(orden);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear la orden", e);
        }
    }

    public void updateOrden(Orden orden) {
        try {
            // Verificar que la orden existe antes de actualizar
            getOrdenById(orden.getId_orden());
            ordenRepository.updateOrden(orden);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar la orden", e);
        }
    }

    public void deleteOrden(Integer idOrden) {
        try {
            // Verificar que la orden existe antes de eliminar
            getOrdenById(idOrden);
            ordenRepository.deleteOrden(idOrden);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la orden", e);
        }
    }
}
