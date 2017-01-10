package persistence;

import domain.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UserJdbcRepository extends AbstractJdbcRepository<User, Long> implements JdbcRepository<User, Long> {

    private static final String tname = "User";
    private static final String primaryKeyColumnName = "u_id";

    private static PreparedStatement findByIdStatement;
    private static PreparedStatement findAllStatement;
    private static PreparedStatement insertStatement;
    private static PreparedStatement updateStatement;
    private static PreparedStatement deleteStatement;

    @Override
    public Optional<User> findById(Connection con, Long id) throws Exception {
        if (findByIdStatement == null) {
            findByIdStatement = con.prepareStatement(String.format("SELECT * FROM %s WHERE %s=?", tname, primaryKeyColumnName));
        }
        findByIdStatement.setLong(1, id);
        ResultSet res = findByIdStatement.executeQuery();
        User u = null;
        if (res.next()) {
            u = new User(res.getLong(primaryKeyColumnName), res.getInt("u_version"), res.getString("u_name"), res.getString("u_password"));
        }
        return Optional.ofNullable(u);
    }

    @Override
    public List<User> findAll(Connection con) throws Exception {
        if (findAllStatement == null) {
            findAllStatement = con.prepareStatement(String.format("SELECT * FROM %s", tname));
        }
        ResultSet res = findAllStatement.executeQuery();
        List<User> users = new LinkedList<>();

        while (res.next()) {
            long id = res.getLong(primaryKeyColumnName);
            Integer version = res.getInt("u_version");
            String name = res.getString("u_name");
            String password = res.getString("u_password");
            User u = new User(id, version, name, password);

            users.add(u);
        }
        return users;
    }

    @Override
    public int insert(Connection con, User entity) throws PersistenceException {
        if (insertStatement == null) {
            try {
                insertStatement = con.prepareStatement(String.format("INSERT INTO %s (u_name, u_password) VALUES (?, ?)", tname), Statement.RETURN_GENERATED_KEYS);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        int result = 0;
        try {
            insertStatement.setString(1, entity.getName());
            insertStatement.setString(2, entity.getPassword());
            result = (insertStatement.execute()) ? 1 : 0;

            ResultSet rs = insertStatement.getGeneratedKeys();
            long id = 0;
            if (rs.next()) {
                id = rs.getLong(1);
            }
            entity.setId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(Connection con, User entity) throws PersistenceException {
        if (updateStatement == null) {
            try {
                updateStatement = con.prepareStatement(String.format("UPDATE %s SET u_name=?, u_password=? WHERE %s=?", tname, primaryKeyColumnName));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        int result = 0;
        try {
            updateStatement.setString(1, entity.getName());
            updateStatement.setString(2, entity.getPassword());
            updateStatement.setLong(3, entity.getId());
            result = (updateStatement.execute()) ? 1 : 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void storeDeleteByIdStmt(PreparedStatement deleteByIdStmt) {
        if (deleteByIdStmt != null) {
            deleteStatement = deleteByIdStmt;
        }
    }

    @Override
    protected PreparedStatement getDeleteByIdStmt() {
        return deleteStatement;
    }

    @Override
    protected String getTableName() {
        return tname;
    }

    @Override
    protected String getPrimaryKeyColumnName() {
        return primaryKeyColumnName;
    }
}
