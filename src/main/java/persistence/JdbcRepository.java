package persistence;

import domains.BaseModel;


import java.sql.Connection;
import java.util.List;
import java.util.Optional;

/**
 * Created by lukas on 11/7/16.
 */
public interface JdbcRepository<DOMAIN extends BaseModel<DOMAIN, PRIMARY_KEY>, PRIMARY_KEY extends Number> {

    int save(Connection con, DOMAIN entity) throws Throwable;

    int delete(Connection con, PRIMARY_KEY id) throws Throwable;

    default int delete(Connection con, DOMAIN entity) throws Exception{
        return (entity.isNew()) ? 1 : delete(con, entity.getId());
    }

    Optional<DOMAIN> findById(Connection con, PRIMARY_KEY id) throws  Exception;

    List<DOMAIN> findAll(Connection con) throws  Exception;
}
