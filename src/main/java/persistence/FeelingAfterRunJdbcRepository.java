package persistence;

import domains.FeelingAfterRun;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class FeelingAfterRunJdbcRepository extends AbstractJdbcRepository<FeelingAfterRun, Long> implements JdbcRepository<FeelingAfterRun, Long> {
    private static final String tname = "FeelingAfterRun";
    private static final FeelingAfterRun far = new FeelingAfterRun();
    private static PreparedStatement findByIdStatement;
    private static PreparedStatement findAllStatement;
    private static PreparedStatement insertStatement;
    private static PreparedStatement updateStatement;
    private static PreparedStatement deleteStatement;

    @Override
    public Optional<FeelingAfterRun> findById(Connection con, Long id) throws Exception {
        if (findByIdStatement == null) {
            findByIdStatement = con.prepareStatement(String.format("SELECT * FROM %s WHERE far_id=%s", tname, id));
        }
        findByIdStatement.setString(1, id.toString());
        ResultSet res = findByIdStatement.executeQuery();

        FeelingAfterRun f = new FeelingAfterRun(res.getLong("id"), res.getInt("version"), res.getString("far_feeling"));

        return Optional.of(f);
    }

    @Override
    public List<FeelingAfterRun> findAll(Connection con) throws Exception {
        if (findAllStatement == null) {
            findAllStatement = con.prepareStatement(String.format("SELECT * FROM %s", tname));
        }
        ResultSet res = findAllStatement.executeQuery();
        List<FeelingAfterRun> feelingAfterRuns = new LinkedList<>();

        while (res.next()) {
            FeelingAfterRun far = new FeelingAfterRun(res.getLong("id"), res.getInt("version"), res.getString("far_feeling"));
            feelingAfterRuns.add(far);
        }
        return feelingAfterRuns;
    }

    @Override
    protected int insert(Connection con, FeelingAfterRun entity) throws PersistenceException {
        if (insertStatement == null) {
            try {
                //check
                insertStatement = con.prepareStatement(String.format("INSERT INTO %s (f_feeling) VALUES(%s)", tname, entity.getFeeling()));
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
    protected int update(Connection con, FeelingAfterRun entity) throws PersistenceException {
        if (updateStatement == null) {
            try {
                //check
                updateStatement = con.prepareStatement(String.format(("UPDATE %s SET f_feeling=%s WHERE f_id=%s"), tname, entity.getFeeling(), entity.getId()));
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
        if (deleteByIdStmt != null) deleteStatement = deleteByIdStmt;
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
        return far.getId().toString();
    }
}
