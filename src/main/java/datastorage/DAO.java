package datastorage;

import java.sql.SQLException;
import java.util.List;

/**
 * The DAO interface with CRUD methods. Gets implemented by other DAO classes.
 */
public interface DAO<T> {
    /**
     *
     * @param t
     * @throws SQLException
     */
    void create(T t) throws SQLException;

    /**
     *
     * @param key
     * @return Object T
     * @throws SQLException
     */
    T read(long key) throws SQLException;

    /**
     *
     * @return List of objects T
     * @throws SQLException
     */
    List<T> readAll() throws SQLException;

    /**
     *
     * @param t
     * @throws SQLException
     */
    void update(T t) throws SQLException;

    /**
     *
     * @param key
     * @throws SQLException
     */
    void deleteById(long key) throws SQLException;
}
