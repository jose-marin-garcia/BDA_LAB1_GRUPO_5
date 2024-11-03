package Laboratorio1BdaGrupo5.BackendLab1.controller;

import Laboratorio1BdaGrupo5.BackendLab1.models.Orden;
import Laboratorio1BdaGrupo5.BackendLab1.service.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/orden")
public class OrdenController {
    
    @Autowired
    private OrdenService ordenService;

    @GetMapping
    public ResponseEntity<List<Orden>> getAllOrdens(
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "0") int offset) {
        try {
            List<Orden> ordens = ordenService.getAllOrdenes(limit, offset);
            return ResponseEntity.ok(ordens);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orden> getOrdenById(@PathVariable Integer id) {
        try {
            Orden orden = ordenService.getOrdenById(id);
            return ResponseEntity.ok(orden);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    @PostMapping
    public ResponseEntity<String> createOrden(@RequestBody Orden orden) {
        try {
            ordenService.createOrden(orden);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Orden creada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al crear la orden");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrden(@PathVariable Integer id, @RequestBody Orden orden) {
        try {
            orden.setId_orden(id); // Asegurarse de que el ID sea consistente
            ordenService.updateOrden(orden);
            return ResponseEntity.ok("Orden actualizada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al actualizar la orden");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrden(@PathVariable Integer id) {
        try {
            ordenService.deleteOrden(id);
            return ResponseEntity.ok("Orden eliminada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al eliminar la orden");
        }
    }
}
