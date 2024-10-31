package Laboratorio1BdaGrupo5.BackendLab1.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.sql2o.Sql2o;

@Configuration
public class DatabaseContext {
    @Bean
    public Sql2o sql2o() {
        return new Sql2o("jdbc:postgresql://${DB_HOST}:5432/BackendLab1", "${USER}", "${PASSWORD}");
    }
}
