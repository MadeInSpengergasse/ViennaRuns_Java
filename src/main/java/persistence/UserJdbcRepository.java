package persistence;

import domains.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class UserJdbcRepository extends AbstractJdbcRepository<User, Long> implements JdbcRepository<User, Long> {

    private static final String tname = "User";

    @Override
    public Optional<User> findById(Connection con, Long id) throws Exception {
        con.prepareStatement(String.format("Select * from %s where u_username=%s", tname, id));
        return null;
    }

    @Override
    public List<User> findAll(Connection con) throws Exception {
        return null;
    }

    @Override
    protected int insert(Connection con, User entity) throws PersistenceException {
        return 0;
    }

    @Override
    protected int update(Connection con, User entity) throws PersistenceException {
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
        return "User";
    }

    @Override
    protected String getPrimaryKeyColumnName() {
        return null;
    }
}
