package persistence;

import domain.FeelingAfterRun;
import domain.Run;
import domain.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class RunJdbcRepository extends AbstractJdbcRepository<Run, Long> {

    private static final String tname = "Run";
    private static final String tuser = "User";
    private static final String tfar = "FeelingAfterRun";
    private static final String primaryKeyColumnName = "r_id";

    private static PreparedStatement findByIdStatement;
    private static PreparedStatement findAllStatement;
    private static PreparedStatement insertStatement;
    private static PreparedStatement updateStatement;
    private static PreparedStatement deleteStatement;
    private static PreparedStatement getVersionByIdStatement;

    @Override
    public Optional<Run> findById(Connection con, Long id) throws Exception {
        if (findByIdStatement == null) {
            findByIdStatement = con.prepareStatement(String.format("SELECT * FROM %s INNER JOIN %s ON r_user = u_id INNER JOIN %s ON r_feeling=far_id WHERE %s=?", tname, tuser, tfar, primaryKeyColumnName));
        }
        findByIdStatement.setLong(1, id);
        ResultSet res = findByIdStatement.executeQuery();

        Run r = null;
        if(res.next()) {
            User u = new User(res.getLong("u_id"), res.getInt("u_version"), res.getString("u_name"), res.getString("u_password"));
            FeelingAfterRun far = new FeelingAfterRun(res.getLong("far_id"), res.getInt("far_version"), res.getString("far_feeling"));
            r = new Run(res.getLong(primaryKeyColumnName), res.getInt("r_version"), u, res.getFloat("r_distance"), res.getInt("r_duration"), res.getDate("r_date"), far);
        }
        return Optional.ofNullable(r);
    }

    @Override
    public List<Run> findAll(Connection con) throws Exception {
        if (findAllStatement == null) {
            findAllStatement = con.prepareStatement(String.format("SELECT * FROM %s", tname));
        }
        ResultSet res = findAllStatement.executeQuery();
        List<Run> runs = new LinkedList<>();

        while (res.next()) {
            User u = new User(res.getLong("u_id"), res.getInt("u_version"), res.getString("u_name"), res.getString("u_password"));
            FeelingAfterRun far = new FeelingAfterRun(res.getLong("far_id"), res.getInt("far_version"), res.getString("far_feeling"));
            Run r = new Run(res.getLong(primaryKeyColumnName), res.getInt("r_version"), u, res.getFloat("r_distance"), res.getInt("r_duration"), res.getDate("r_date"), far);

            runs.add(r);
        }
        return runs;
    }

    @Override
    public int insert(Connection con, Run entity) throws PersistenceException {
        if (insertStatement == null) {
            try {
                insertStatement = con.prepareStatement(String.format("INSERT INTO %s (r_user, r_distance, r_duration, r_date, r_feeling) VALUES(?, ?, ?, ?, ?)", tname), Statement.RETURN_GENERATED_KEYS);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        int result = 0;
        try {
            insertStatement.setLong(1, entity.getUser().getId());
            insertStatement.setFloat(2, entity.getDistance());
            insertStatement.setLong(3, entity.getDuration());
            insertStatement.setString(4, entity.getDate().toString()); // TODO: Really like that?
            insertStatement.setLong(5, entity.getFeeling().getId());
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
    public int update(Connection con, Run entity) throws PersistenceException {
        if (updateStatement == null) {
            try {
                updateStatement = con.prepareStatement(String.format("UPDATE %s SET r_user=?,r_distance=?, r_duration=?, r_date=?, r_feeling=? WHERE %s=?", tname, primaryKeyColumnName));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(getVersionByIdStatement == null) {
            try {
                getVersionByIdStatement = con.prepareStatement(String.format("SELECT r_version FROM %s WHERE %s=?", tname, primaryKeyColumnName));
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        int result = 0;
        try {
            getVersionByIdStatement.setLong(1, entity.getId());
            ResultSet res = getVersionByIdStatement.executeQuery();
            int ver = 0;
            if(res.next())
                ver = res.getInt("r_version");
            if(entity.getVersion() != ver) {
                throw new PersistenceException();
            }

            insertStatement.setLong(1, entity.getUser().getId());
            insertStatement.setFloat(2, entity.getDistance());
            insertStatement.setLong(3, entity.getDuration());
            insertStatement.setString(4, entity.getDate().toString()); // TODO: Really like that?
            insertStatement.setLong(5, entity.getFeeling().getId());
            insertStatement.setLong(6, entity.getId());
            result = (updateStatement.execute()) ? 1 : 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void storeDeleteByIdStmt(PreparedStatement deleteByIdStmt) {
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
