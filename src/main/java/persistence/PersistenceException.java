package persistence;

import java.sql.SQLException;

/**
 * Created by lukas on 11/7/16.
 */
public class PersistenceException extends RuntimeException {

    public static Throwable forException(Exception e) {
        return new PersistenceException();
    }

    public static Throwable forSqlException(SQLException e) {
        return new PersistenceException();
    }
}
