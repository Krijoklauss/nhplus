package datastorage;

import java.sql.SQLException;
import java.util.List;

/**
 * The DAO interface with CRUD methods. Gets implemented by other DAO classes.
 */
public interface DAO<T> {
    void create(T t) throws SQLException;

    T read(long key) throws SQLException;

    List<T> readAll() throws SQLException;

    void update(T t) throws SQLException;

    void deleteById(long key) throws SQLException;
}
