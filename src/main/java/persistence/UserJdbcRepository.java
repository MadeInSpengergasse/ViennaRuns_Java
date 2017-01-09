package persistence;

import domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UserJdbcRepository extends AbstractJdbcRepository<User, Long> implements JdbcRepository<User, Long> {

    private static final String tname = "User";
    private static final User user = new User();
    private static PreparedStatement findByIdStatement;
    private static PreparedStatement findAllStatement;
    private static PreparedStatement insertStatement;
    private static PreparedStatement updateStatement;
    private static PreparedStatement deleteStatement;

    @Override
    public Optional<User> findById(Connection con, Long id) throws Exception {
        if (findByIdStatement == null) {
            findByIdStatement = con.prepareStatement(String.format("SELECT * FROM %s WHERE u_id=%s", tname, id));
        }
        findByIdStatement.setString(1, id.toString());
        ResultSet res = findByIdStatement.executeQuery();

        User u = new User(res.getLong("id"), res.getInt("version"), res.getString("u_name"), res.getString("u_password"));

        return Optional.of(u);
    }

    @Override
    public List<User> findAll(Connection con) throws Exception {
        if (findAllStatement == null) {
            findAllStatement = con.prepareStatement(String.format("SELECT * FROM %s", tname));
        }
        ResultSet res = findAllStatement.executeQuery();
        List<User> users = new LinkedList<>();

        while (res.next()) {
            long id = res.getLong("id");
            Integer version = res.getInt("version");
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
                insertStatement = con.prepareStatement(String.format("INSERT INTO %s (u_user,u_password) VALUES(%s,%s)", tname, entity.getName(), entity.getPassword()));
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
        int result = 0;
        try {
            result = (insertStatement.execute()) ? 1 : 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(Connection con, User entity) throws PersistenceException {
        if (updateStatement == null) {
            try {
                updateStatement = con.prepareStatement(String.format("UPDATE %s SET u_user=%s,u_password=%s WHERE u_id=%s", tname, entity.getName(), entity.getPassword(), entity.getId()));
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
        int result = 0;
        try {
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
        return user.getId().toString();
    }
}
