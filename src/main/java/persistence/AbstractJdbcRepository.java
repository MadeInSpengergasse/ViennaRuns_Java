package persistence;

import domains.BaseModel;
import org.hsqldb.types.Types;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by lukas on 11/7/16.
 */
public abstract class AbstractJdbcRepository<DOMAIN extends BaseModel<DOMAIN,
        PRIMARY_KEY>, PRIMARY_KEY extends Number>
        implements JdbcRepository<DOMAIN, PRIMARY_KEY> {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     *   *
     *   * @param con
     *   * @param entity
     *   * @return
     *   * @throws PersistenceException
     *  
     */
    public final int save(Connection con, DOMAIN entity) throws Throwable {
        try {
            if (entity.isNew()) {
                return insert(con, entity);
            } else {
                return update(con, entity);
            }
        } catch (Exception ex) {
            throw PersistenceException.forException(ex);
        }
    }

    protected abstract int insert(Connection con, DOMAIN entity) throws
            PersistenceException;

    protected abstract int update(Connection con, DOMAIN entity) throws
            PersistenceException;

    public final int delete(Connection con, PRIMARY_KEY id) throws
            Throwable {
        PreparedStatement deleteByIdStmt = getDeleteByIdStmt();

        try {
            if (deleteByIdStmt == null) {
                String deleteByIdSQL = String.format("DELETE FROM %s WHERE %s = ?",
                        getTableName(), getPrimaryKeyColumnName());
                logger.debug("crafted deleteByIdStmt: '{}'", deleteByIdSQL);
                deleteByIdStmt = con.prepareStatement(deleteByIdSQL);
                storeDeleteByIdStmt(deleteByIdStmt);
            }
            // null check is obsolete now
            if (id == null) {
                deleteByIdStmt.setNull(1, Types.BIGINT);
            } else {
                deleteByIdStmt.setLong(1, id.longValue());
            }
            return deleteByIdStmt.executeUpdate();
        } catch (SQLException sqlEx) {
            throw PersistenceException.forSqlException(sqlEx);
        }
    }

    protected abstract void storeDeleteByIdStmt(PreparedStatement deleteByIdStmt);

    protected abstract PreparedStatement getDeleteByIdStmt();

    protected abstract String getTableName();

    protected abstract String getPrimaryKeyColumnName();
}