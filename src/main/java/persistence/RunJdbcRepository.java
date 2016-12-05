package persistence;

import domains.FeelingAfterRun;
import domains.Run;
import domains.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class RunJdbcRepository extends AbstractJdbcRepository<Run, Long> {


    private static final String tname = "Run";
    private static final String tuser = "User";
    private static final String tfar = "FeelingAfterRun";
    private static final Run run = new Run();
    private static PreparedStatement findByIdStatement;
    private static PreparedStatement findAllStatement;
    private static PreparedStatement insertStatement;
    private static PreparedStatement updateStatement;
    private static PreparedStatement deleteStatement;

    @Override
    public Optional<Run> findById(Connection con, Long id) throws Exception {
        if (findByIdStatement == null) {
            findByIdStatement = con.prepareStatement(String.format("SELECT * FROM %s INNER JOIN %s ON r_user = u_id INNER JOIN %s ON r_feeling=far_feeling WHERE id=%s", tname, tuser, tfar, id));
        }
        findByIdStatement.setString(1, id.toString());
        ResultSet res = findByIdStatement.executeQuery();

        User u = new User(res.getLong("id"), res.getInt("version"), res.getString("u_name"), res.getString("u_password"));
        FeelingAfterRun far = new FeelingAfterRun(res.getLong("id"), res.getInt("version"), res.getString("far_feeling"));
        Run r = new Run(res.getLong("id"), res.getInt("version"), u, res.getFloat("r_distance"), res.getInt("r_duration"), res.getDate("r_date"), far);

        return Optional.of(r);
    }

    @Override
    public List<Run> findAll(Connection con) throws Exception {
        if (findAllStatement == null) {
            findAllStatement = con.prepareStatement(String.format("SELECT * FROM %s", tname));
        }
        ResultSet res = findAllStatement.executeQuery();
        List<Run> runs = new LinkedList<>();

        while (res.next()) {
            User u = new User(res.getLong("id"), res.getInt("version"), res.getString("u_name"), res.getString("u_password"));
            FeelingAfterRun far = new FeelingAfterRun(res.getLong("id"), res.getInt("version"), res.getString("far_feeling"));
            Run r = new Run(res.getLong("id"), res.getInt("version"), u, res.getFloat("r_distance"), res.getInt("r_duration"), res.getDate("r_date"), far);

            runs.add(r);
        }
        return runs;
    }

    @Override
    protected int insert(Connection con, Run entity) throws PersistenceException {
        if (insertStatement == null) {
            try {
                insertStatement = con.prepareStatement(String.format("INSERT INTO %s (r_user, r_distance, r_duration, r_date, r_feeling) VALUES(%s, %s, %s, %s, %s)", tname, entity.getUser().getId(), entity.getDistance(), entity.getDuration(), entity.getDate(), entity.getFeeling().getId()));
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
    protected int update(Connection con, Run entity) throws PersistenceException {
        if (updateStatement == null) {
            try {
                updateStatement = con.prepareStatement(String.format("UPDATE %s SET r_user=%s,r_distance=%s, r_duration=%s, r_date=%s, r_feeling=%s WHERE r_id=%s", tname, entity.getUser().getId(), entity.getDistance(), entity.getDuration(), entity.getDate(), entity.getFeeling().getId(), entity.getId()));
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
        return run.getId().toString();
    }
}
