package Laboratorio1BdaGrupo5.BackendLab1.repository;

import Laboratorio1BdaGrupo5.BackendLab1.models.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

@Repository
public class ClienteRepositoryImp implements ClienteRepository {
    @Autowired
    private Sql2o sql2o;

    @Override
    public Cliente getClienteById(Integer idCliente) {
        String queryText = "SELECT * FROM producto WHERE id_cliente = :idCliente";
        try (Connection connection = sql2o.open()) {
            System.out.println("Conexión exitosa a la base de datos");
            Cliente cliente = connection.createQuery(queryText)
                    .addParameter("idCliente", idCliente)
                    .executeAndFetchFirst(Cliente.class);
            return cliente;
        } catch (Exception e) {
            System.err.println("Error en la conexión a la base de datos: " + e.getMessage());
            throw new RuntimeException("Error al obtener el cliente por Id", e);
        }
    }

    @Override
    public void createCliente(Cliente cliente) {
        String queryText = "INSERT INTO cliente (idCliente, nombre, direccion, email, telefono) " +
                "VALUES (:idCliente, :nombre, :direccion, :email, :telefono)";
        try (Connection connection = sql2o.open()) {
            connection.createQuery(queryText)
                    .addParameter("idCliente", cliente.getIdCliente())
                    .addParameter("nombre", cliente.getNombre())
                    .addParameter("direccion", cliente.getDireccion())
                    .addParameter("email", cliente.getEmail())
                    .addParameter("telefono", cliente.getTelefono())
                    .executeUpdate();
        } catch (Exception e) {
            System.err.println("Error en la conexión a la base de datos: " + e.getMessage());
            throw new RuntimeException("No se pudo crear el Cliente", e);
        }
    }

    @Override
    public void updateCliente(Cliente cliente) {
        String queryText = "UPDATE cliente SET nombre = :nombre, direccion = :direccion, " +
                "email = :email, telefono = :telefono WHERE id_cliente = :idCliente";
        try (Connection connection = sql2o.open()) {
            System.out.println("Conexión exitosa a la base de datos");
            connection.createQuery(queryText)
                    .addParameter("nombre", cliente.getNombre())
                    .addParameter("descripcion", cliente.getDireccion())
                    .addParameter("precio", cliente.getEmail())
                    .addParameter("stock", cliente.getTelefono())
                    .addParameter("idCliente", cliente.getIdCliente())
                    .executeUpdate();
        } catch (Exception e) {
            System.err.println("Error en la conexión a la base de datos: " + e.getMessage());
            throw new RuntimeException("Error al actualizar el Cliente", e);
        }
    }

    @Override
    public void deleteCliente(Integer idCliente) {
        String queryText = "DELETE FROM cliente WHERE id_cliente = :idCliente";
        try (Connection connection = sql2o.open()) {
            System.out.println("Conexión exitosa a la base de datos");
            connection.createQuery(queryText)
                    .addParameter("idCliente", idCliente)
                    .executeUpdate();
        } catch (Exception e) {
            System.err.println("Error en la conexión a la base de datos: " + e.getMessage());
            throw new RuntimeException("Error al eliminar el Cliente", e);
        }
    }

}