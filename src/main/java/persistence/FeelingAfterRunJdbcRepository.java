package persistence;

import domain.FeelingAfterRun;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class FeelingAfterRunJdbcRepository extends AbstractJdbcRepository<FeelingAfterRun, Long> implements JdbcRepository<FeelingAfterRun, Long> {
    private static final String tname = "FeelingAfterRun";
    private static final String primaryKeyColumnName = "far_id";

    private static PreparedStatement findByIdStatement;
    private static PreparedStatement findAllStatement;
    private static PreparedStatement insertStatement;
    private static PreparedStatement updateStatement;
    private static PreparedStatement deleteStatement;
    private static PreparedStatement getVersionByIdStatement;

    @Override
    public Optional<FeelingAfterRun> findById(Connection con, Long id) throws Exception {
        if (findByIdStatement == null) {
            findByIdStatement = con.prepareStatement(String.format("SELECT * FROM %s WHERE %s=?", tname, primaryKeyColumnName));
        }
        findByIdStatement.setLong(1, id);
        ResultSet res = findByIdStatement.executeQuery();

        FeelingAfterRun f = null;
        if (res.next()) {
            f = new FeelingAfterRun(res.getLong(primaryKeyColumnName), res.getInt("far_version"), res.getString("far_feeling"));
        }
        return Optional.ofNullable(f);
    }

    @Override
    public List<FeelingAfterRun> findAll(Connection con) throws Exception {
        if (findAllStatement == null) {
            findAllStatement = con.prepareStatement(String.format("SELECT * FROM %s", tname));
        }
        ResultSet res = findAllStatement.executeQuery();
        List<FeelingAfterRun> feelingAfterRuns = new LinkedList<>();

        while (res.next()) {
            FeelingAfterRun far = new FeelingAfterRun(res.getLong(primaryKeyColumnName), res.getInt("far_version"), res.getString("far_feeling"));
            feelingAfterRuns.add(far);
        }
        return feelingAfterRuns;
    }

    @Override
    public int insert(Connection con, FeelingAfterRun entity) throws PersistenceException {
        if (insertStatement == null) {
            try {
                //check
                insertStatement = con.prepareStatement(String.format("INSERT INTO %s (far_feeling) VALUES(?)", tname), Statement.RETURN_GENERATED_KEYS);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        int result = 0;
        try {
            insertStatement.setString(1, entity.getFeeling());
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
    public int update(Connection con, FeelingAfterRun entity) throws PersistenceException {
        if (updateStatement == null) {
            try {
                //check
                updateStatement = con.prepareStatement(String.format(("UPDATE %s SET far_version=?, far_feeling=? WHERE %s=?"), tname, primaryKeyColumnName));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(getVersionByIdStatement == null) {
            try {
                getVersionByIdStatement = con.prepareStatement(String.format("SELECT far_version FROM %s WHERE %s=?", tname, primaryKeyColumnName));
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
                ver = res.getInt("far_version");
            if(entity.getVersion() != ver) {
                throw new PersistenceException();
            }
            updateStatement.setInt(1, ver+1);
            updateStatement.setString(2, entity.getFeeling());
            updateStatement.setLong(3, entity.getId());
            entity.setVersion(ver+1);
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
        return primaryKeyColumnName;
    }
}
