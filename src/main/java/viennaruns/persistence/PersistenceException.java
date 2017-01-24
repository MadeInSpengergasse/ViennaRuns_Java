package viennaruns.persistence;

import java.sql.SQLException;

public class PersistenceException extends RuntimeException {

    public static PersistenceException forException(Exception e) {
        return new PersistenceException();
    }

    public static PersistenceException forSqlException(SQLException e) {
        return new PersistenceException();
    }
}
