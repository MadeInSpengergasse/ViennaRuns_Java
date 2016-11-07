package persistence;

import java.sql.SQLException;

/**
 * Created by lukas on 11/7/16.
 */
public class PersistenceException extends RuntimeException {

    public static PersistenceException forException(Exception e) {
        return new PersistenceException();
    }

    public static PersistenceException forSqlException(SQLException e) {
        return new PersistenceException();
    }
}
