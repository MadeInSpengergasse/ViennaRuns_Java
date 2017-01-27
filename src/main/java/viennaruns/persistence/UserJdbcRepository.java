package viennaruns.persistence;

import viennaruns.domain.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UserJdbcRepository extends AbstractJdbcRepository<User, Long> implements JdbcRepository<User, Long> {

    private static final String tname = "User";
    private static final String primaryKeyColumnName = "u_id";

    private static PreparedStatement findByIdStatement;
    private static PreparedStatement findAllStatement;
    private static PreparedStatement findByNameStatement;
    private static PreparedStatement insertStatement;
    private static PreparedStatement updateStatement;
    private static PreparedStatement deleteStatement;
    private static PreparedStatement getVersionByIdStatement;

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
            entity.setVersion(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(Connection con, User entity) throws PersistenceException {
        if (updateStatement == null) {
            try {
                updateStatement = con.prepareStatement(String.format("UPDATE %s SET u_version=?, u_name=?, u_password=? WHERE %s=?", tname, primaryKeyColumnName));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (getVersionByIdStatement == null) {
            try {
                getVersionByIdStatement = con.prepareStatement(String.format("SELECT u_version FROM %s WHERE %s=?", tname, primaryKeyColumnName));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        int result = 0;
        try {
            getVersionByIdStatement.setLong(1, entity.getId());
            ResultSet res = getVersionByIdStatement.executeQuery();
            int ver = 0;
            if (res.next())
                ver = res.getInt("u_version");
            if (entity.getVersion() != ver) {
                throw new PersistenceException();
            }
            updateStatement.setInt(1, ver + 1);
            updateStatement.setString(2, entity.getName());
            updateStatement.setString(3, entity.getPassword());
            updateStatement.setLong(4, entity.getId());
            entity.setVersion(ver + 1);
            result = (updateStatement.execute()) ? 1 : 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<User> findByNameLike(Connection con, String like) throws SQLException {
        if (findByNameStatement == null) {
            findByNameStatement = con.prepareStatement(String.format("SELECT * FROM %s WHERE u_name LIKE ?", tname));
        }
        findByNameStatement.setString(1, "%" + like + "%");
        ResultSet res = findByNameStatement.executeQuery();
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
