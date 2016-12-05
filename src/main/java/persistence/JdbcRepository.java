package persistence;

import domains.BaseModel;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface JdbcRepository<DOMAIN extends BaseModel<DOMAIN, PRIMARY_KEY>, PRIMARY_KEY extends Number> {

    int save(Connection con, DOMAIN entity) throws PersistenceException;

    int delete(Connection con, PRIMARY_KEY id) throws PersistenceException;

    default int delete(Connection con, DOMAIN entity) throws PersistenceException {
        return (entity.isNew()) ? 1 : delete(con, entity.getId());
    }

    Optional<DOMAIN> findById(Connection con, PRIMARY_KEY id) throws Exception;

    List<DOMAIN> findAll(Connection con) throws Exception;
}
