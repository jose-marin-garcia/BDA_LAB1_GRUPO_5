package Laboratorio1BdaGrupo5.BackendLab1.service;

import Laboratorio1BdaGrupo5.BackendLab1.models.DetalleOrden;
import Laboratorio1BdaGrupo5.BackendLab1.models.Orden;
import Laboratorio1BdaGrupo5.BackendLab1.models.Producto;
import Laboratorio1BdaGrupo5.BackendLab1.repository.OrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrdenService {
    @Autowired
    private OrdenRepository ordenRepository;

    @Autowired
    private DetalleOrdenService detalleOrdenService;

    @Autowired ProductoService productoService;
    
    public List<Orden> getAllOrdenes(int limit, int offset) {
        try {
            return ordenRepository.getOrdenes(limit, offset);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la lista de ordenes", e);
        }       
    }

    public List<Orden> getAllOrdenesById(int limit, int offset, int idCliente){
        try{
            return ordenRepository.getOrdenesById(limit,offset,idCliente);
        }catch (Exception e){
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
            getOrdenById(orden.getIdOrden());
            ordenRepository.updateOrden(orden);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar la orden", e);
        }
    }

    public void deleteOrden(Integer idOrden) {
        try {
            getOrdenById(idOrden);
            ordenRepository.deleteOrden(idOrden);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la orden", e);
        }
    }

    public void pagar(List<DetalleOrden> detalles, Orden orden) {
        try{
            double total = 0;
            for (DetalleOrden detalle : detalles) {
                Producto p = productoService.getProductoById(detalle.getIdProducto());
                detalle.setPrecioUnitario(BigDecimal.valueOf(p.getPrecio()));
                total += p.getPrecio() * detalle.getCantidad();
            }
            orden.setTotal((float) total);
            orden = ordenRepository.createOrden(orden);
            for (DetalleOrden detalle : detalles) {
                detalle.setIdOrden(orden.getIdOrden());
                detalleOrdenService.createDetalleOrden(detalle, orden.getIdCliente());
            }
        } catch (Exception e){
            throw new RuntimeException("Error al pagar la orden", e);
        }

    }
}
