package Laboratorio1BdaGrupo5.BackendLab1.repository;

import Laboratorio1BdaGrupo5.BackendLab1.models.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

@Repository
public class CategoriaRepositoryImp implements CategoriaRepository{
    @Autowired
    private Sql2o sql2o;

    @Override
    public Categoria getCategoriaById(Integer id_categoria) {
        String queryText = "SELECT * FROM categoria WHERE id_cliente = :id_cliente";
        try (Connection connection = sql2o.open()) {
            System.out.println("Conexión exitosa a la base de datos");
            Categoria categoria = connection.createQuery(queryText)
                    .addParameter("id_categoria", id_categoria)
                    .executeAndFetchFirst(Categoria.class);
            return categoria;
        } catch (Exception e) {
            System.err.println("Error en la conexión a la base de datos: " + e.getMessage());
            throw new RuntimeException("Error al obtener la categoria por Id", e);
        }
    }

    @Override
    public void createCategoria(Categoria categoria) {
        String queryText = "INSERT INTO categoria (id_categoria, nombre) " +
                "VALUES (:id_categoria, :nombre)";
        try (Connection connection = sql2o.open()) {
            connection.createQuery(queryText)
                    .addParameter("id_categoria", categoria.getIdCategoria())
                    .addParameter("nombre", categoria.getNombre())
                    .executeUpdate();
        } catch (Exception e) {
            System.err.println("Error en la conexión a la base de datos: " + e.getMessage());
            throw new RuntimeException("No se pudo crear la Categoria", e);
        }
    }

    @Override
    public void updateCategoria(Categoria categoria) {
        String queryText = "UPDATE categoria SET nombre = :nombre WHERE id_categoria = :id_categoria";
        try (Connection connection = sql2o.open()) {
            System.out.println("Conexión exitosa a la base de datos");
            connection.createQuery(queryText)
                    .addParameter("nombre", categoria.getNombre())
                    .addParameter("id_cliente", categoria.getIdCategoria())
                    .executeUpdate();
        } catch (Exception e) {
            System.err.println("Error en la conexión a la base de datos: " + e.getMessage());
            throw new RuntimeException("Error al actualizar la Categoria", e);
        }
    }

    @Override
    public void deleteCategoria(Integer id_categoria) {
        String queryText = "DELETE FROM categoria WHERE id_categoria = :id_categoria";
        try (Connection connection = sql2o.open()) {
            System.out.println("Conexión exitosa a la base de datos");
            connection.createQuery(queryText)
                    .addParameter("id_categoria", id_categoria)
                    .executeUpdate();
        } catch (Exception e) {
            System.err.println("Error en la conexión a la base de datos: " + e.getMessage());
            throw new RuntimeException("Error al eliminar la Categoria", e);
        }
    }
}