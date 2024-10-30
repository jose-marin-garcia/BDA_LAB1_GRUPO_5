//package Laboratorio1BdaGrupo5.BackendLab1.repository;

import Laboratorio1BdaGrupo5.BackendLab1.models.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;

//@Repository
//public interface ProductoRepositoryImp implements ProductoRepository {

//    @Autowired
//    private Sql2o sql2o;
//
//    @Override
//    public Producto getProductoById(Long productoId) {
//        String sql = "SELECT * FROM producto WHERE id_producto = :id";
//        try (org.sql2o.Connection con = sql2o.open()) {
//            return con.createQuery(sql) {
//                    .addParameter("id", productoId)
//                        .executeAndFetchFirst(Producto.class);
//            }
//        }
//        catch (Exception e){
//            throw new RuntimeException("No se logro conseguir el producto");
//        }
//    }
//}
