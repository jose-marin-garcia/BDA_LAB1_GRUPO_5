package Laboratorio1BdaGrupo5.BackendLab1.repository;

import Laboratorio1BdaGrupo5.BackendLab1.models.Orden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class OrdenRepositoryImp implements OrdenRepository {
    @Autowired
    private Sql2o sql2o;

    @Override
    public List<Orden> getOrdenes(int limit, int offset) {
        String queryText = "SELECT * FROM orden LIMIT :limit OFFSET :offset";
        try (Connection connection = sql2o.open()) {
            System.out.println("Conexión exitosa a la base de datos");
            return connection.createQuery(queryText)
                    .addParameter("limit", limit)
                    .addParameter("offset", offset)
                    .executeAndFetch(Orden.class);
        } catch (Exception e) {
            System.err.println("Error en la conexión a la base de datos: " + e.getMessage());
            throw new RuntimeException("Error al obtener ordenes", e);
        }
    }

    @Override
    public Orden getOrdenById(Integer idOrden) {
        String queryText = "SELECT * FROM orden WHERE id_orden = :idOrden";
        try (Connection connection = sql2o.open()) {
            System.out.println("Conexión exitosa a la base de datos");
            Orden orden = connection.createQuery(queryText)
                    .addParameter("idOrden", idOrden)
                    .executeAndFetchFirst(Orden.class);
            return orden;
        } catch (Exception e) {
            System.err.println("Error en la conexión a la base de datos: " + e.getMessage());
            throw new RuntimeException("Error al obtener la orden por ID", e);
        }
    }

    @Override
    public void createOrden(Orden orden) {
        String queryText = "INSERT INTO orden (fecha_orden, estado, id_cliente, total) " +
                "VALUES (:fecha_orden, :estado, :id_cliente, :total)";
        try (Connection connection = sql2o.open()) {
            System.out.println("Conexión exitosa a la base de datos");
            orden.setEstado(orden.getEstado().toLowerCase());
            connection.createQuery(queryText)
                    .addParameter("fecha_orden", orden.getFechaOrden())
                    .addParameter("estado", orden.getEstado())
                    .addParameter("id_cliente", orden.getIdCliente())
                    .addParameter("total", orden.getTotal())
                    .executeUpdate();
        } catch (Exception e) {
            System.err.println("Error en la conexión a la base de datos: " + e.getMessage());
            throw new RuntimeException("Error al crear la orden", e);
        }
    }

    @Override
    public void updateOrden(Orden orden) {
        String queryText = "UPDATE orden SET fecha_orden = :fecha_orden, estado = :estado, " +
                "id_cliente = :id_cliente, total = :total WHERE id_orden = :idOrden";
        try (Connection connection = sql2o.open()) {
            System.out.println("Conexión exitosa a la base de datos");
            connection.createQuery(queryText)
                    .addParameter("fecha_orden", orden.getFechaOrden())
                    .addParameter("estado", orden.getEstado())
                    .addParameter("id_cliente", orden.getIdCliente())
                    .addParameter("total", orden.getTotal())
                    .addParameter("idOrden", orden.getIdOrden())
                    .executeUpdate();
        } catch (Exception e) {
            System.err.println("Error en la conexión a la base de datos: " + e.getMessage());
            throw new RuntimeException("Error al actualizar el orden", e);
        }
    }

    @Override
    public void deleteOrden(Integer idOrden) {
        String queryText = "DELETE FROM orden WHERE id_orden = :idOrden";
        try (Connection connection = sql2o.open()) {
            System.out.println("Conexión exitosa a la base de datos");
            connection.createQuery(queryText)
                    .addParameter("idOrden", idOrden)
                    .executeUpdate();
        } catch (Exception e) {
            System.err.println("Error en la conexión a la base de datos: " + e.getMessage());
            throw new RuntimeException("Error al eliminar la orden", e);
        }
    }
}
