package Laboratorio1BdaGrupo5.BackendLab1.controllers;

import Laboratorio1BdaGrupo5.BackendLab1.entities.DetalleOrdenEntity;
import Laboratorio1BdaGrupo5.BackendLab1.services.DetalleOrdenService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/detalles")
public class DetalleOrdenController {
    private final DetalleOrdenService service;

    public DetalleOrdenController(DetalleOrdenService service) {
        this.service = service;
    }

    @GetMapping
    public List<DetalleOrdenEntity> getAllDetalles() {
        return service.getAllDetalles();
    }

    @GetMapping("/{id}")
    public DetalleOrdenEntity getDetalleById(@PathVariable Long id) {
        return service.getDetalleById(id);
    }

    @PostMapping
    public Long createDetalle(@RequestBody DetalleOrdenEntity detalle) {
        return service.createDetalle(detalle);
    }

    @PutMapping("/{id}")
    public int updateDetalle(@PathVariable Long id, @RequestBody DetalleOrdenEntity detalle) {
        detalle.setIdDetalle(id);
        return service.updateDetalle(detalle);
    }

    @DeleteMapping("/{id}")
    public int deleteDetalle(@PathVariable Long id) {
        return service.deleteDetalle(id);
    }
}
