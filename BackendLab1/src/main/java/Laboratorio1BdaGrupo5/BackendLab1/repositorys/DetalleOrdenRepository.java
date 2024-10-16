package Laboratorio1BdaGrupo5.BackendLab1.repositorys;

import Laboratorio1BdaGrupo5.BackendLab1.entities.DetalleOrdenEntity;
import org.sql2o.Sql2o;
import org.sql2o.Connection;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class DetalleOrdenRepository {
    private final Sql2o sql2o;

    public DetalleOrdenRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public List<DetalleOrdenEntity> getAll() {
        String sql = "SELECT * FROM detalle_orden";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(DetalleOrdenEntity.class);
        }
    }

    public DetalleOrdenEntity findById(Long id) {
        String sql = "SELECT * FROM detalle_orden WHERE id_detalle = :id";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(DetalleOrdenEntity.class);
        }
    }

    public Long insert(DetalleOrdenEntity detalle) {
        String sql = "INSERT INTO detalle_orden (id_orden, id_producto, cantidad, precio_unitario) " +
                "VALUES (:idOrden, :idProducto, :cantidad, :precioUnitario)";
        try (Connection con = sql2o.open()) {
            return (Long) con.createQuery(sql, true)
                    .addParameter("idOrden", detalle.getIdOrden())
                    .addParameter("idProducto", detalle.getIdProducto())
                    .addParameter("cantidad", detalle.getCantidad())
                    .addParameter("precioUnitario", detalle.getPrecioUnitario())
                    .executeUpdate()
                    .getKey(Long.class);
        }
    }

    public int update(DetalleOrdenEntity detalle) {
        String sql = "UPDATE detalle_orden SET id_orden = :idOrden, id_producto = :idProducto, " +
                "cantidad = :cantidad, precio_unitario = :precioUnitario WHERE id_detalle = :idDetalle";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("idOrden", detalle.getIdOrden())
                    .addParameter("idProducto", detalle.getIdProducto())
                    .addParameter("cantidad", detalle.getCantidad())
                    .addParameter("precioUnitario", detalle.getPrecioUnitario())
                    .addParameter("idDetalle", detalle.getIdDetalle())
                    .executeUpdate()
                    .getResult();
        }
    }

    public int delete(Long id) {
        String sql = "DELETE FROM detalle_orden WHERE id_detalle = :id";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate()
                    .getResult();
        }
    }
}
