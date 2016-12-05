package persistence;

import domains.Run;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class RunRepository extends AbstractJdbcRepository<Run, Long> {


    @Override
    public Optional<Run> findById(Connection con, Long id) throws Exception {
        return null;
    }

    @Override
    public List<Run> findAll(Connection con) throws Exception {
        return null;
    }

    @Override
    protected int insert(Connection con, Run entity) throws PersistenceException {
        return 0;
    }

    @Override
    protected int update(Connection con, Run entity) throws PersistenceException {
        return 0;
    }

    @Override
    protected void storeDeleteByIdStmt(PreparedStatement deleteByIdStmt) {

    }

    @Override
    protected PreparedStatement getDeleteByIdStmt() {
        return null;
    }

    @Override
    protected String getTableName() {
        return null;
    }

    @Override
    protected String getPrimaryKeyColumnName() {
        return null;
    }
}
