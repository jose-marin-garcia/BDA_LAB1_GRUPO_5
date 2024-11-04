package Laboratorio1BdaGrupo5.BackendLab1.controller;

import Laboratorio1BdaGrupo5.BackendLab1.models.Categoria;
import Laboratorio1BdaGrupo5.BackendLab1.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/categoria")
public class CategoriaController {
    @Autowired
    CategoriaService categoriaService;

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoriaById(Integer idCategoria) {
        try {
            Categoria categoria = categoriaService.getCategoriaById(idCategoria);
            return ResponseEntity.ok(categoria);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    @PostMapping("/")
    public ResponseEntity<String> createCliente(@RequestBody Categoria categoria) {
        try {
            categoriaService.createCategoria(categoria);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Categoria creada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al crear la Categoria");
        }
    }

    @PutMapping("/")
    public ResponseEntity<String> updateCliente(@RequestBody Categoria categoria) {
        try {
            categoriaService.updateCategoria(categoria);
            return ResponseEntity.ok("Categoria actualizada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al actualizar la Categoria");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCliente(@PathVariable Integer id) {
        try {
            categoriaService.deleteCategoria(id);
            return ResponseEntity.ok("Categoria eliminada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al eliminar la Categoria");
        }
    }
}
