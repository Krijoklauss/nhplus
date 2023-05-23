package datastorage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * The implementation of the DAO interface and CRUD operations.
 * Overrides DAO methods and implements abstract new ones.
 */
public abstract class DAOimp<T> implements DAO<T> {
    protected Connection conn;

    /**
     * Constructs a DAOimp from given param.
     * @param conn
     */
    public DAOimp(Connection conn) {
        this.conn = conn;
    }

    /**
     *
     * @param t object to be created
     * @throws SQLException if database errors occur
     */
    @Override
    public void create(T t) throws SQLException {
        Statement st = conn.createStatement();
        st.executeUpdate(getCreateStatementString(t));
    }

    /**
     *
     * @param key for object
     * @return object
     * @throws SQLException if database errors occur
     */
    @Override
    public T read(long key) throws SQLException {
        T object = null;
        Statement st = conn.createStatement();
        ResultSet result = st.executeQuery(getReadByIDStatementString(key));
        if (result.next()) {
            object = getInstanceFromResultSet(result);
        }
        return object;
    }

    /**
     *
     * @return list of objects
     * @throws SQLException if database errors occur
     */
    @Override
    public List<T> readAll() throws SQLException {
        ArrayList<T> list = new ArrayList<T>();
        T object = null;
        Statement st = conn.createStatement();
        ResultSet result = st.executeQuery(getReadAllStatementString());
        list = getListFromResultSet(result);
        return list;
    }

    /**
     *
     * @param t object to update
     * @throws SQLException if database errors occur
     */
    @Override
    public void update(T t) throws SQLException {
        Statement st = conn.createStatement();
        st.executeUpdate(getUpdateStatementString(t));
    }

    /**
     *
     * @param key for object to be deleted
     * @throws SQLException if database errors occur
     */
    @Override
    public void deleteById(long key) throws SQLException {
        Statement st = conn.createStatement();
        st.executeUpdate(getDeleteStatementString(key));
    }

    /**
     *
     * @param t
     * @return String
     */
    protected abstract String getCreateStatementString(T t);

    /**
     *
     * @param key
     * @return String
     */
    protected abstract String getReadByIDStatementString(long key);

    /**
     *
     * @param set
     * @return Object T
     * @throws SQLException
     */
    protected abstract T getInstanceFromResultSet(ResultSet set) throws SQLException;

    /**
     *
     * @return String
     */
    protected abstract String getReadAllStatementString();

    /**
     *
     * @param set
     * @return Objects T as ArrayList
     * @throws SQLException
     */
    protected abstract ArrayList<T> getListFromResultSet(ResultSet set) throws SQLException;

    /**
     *
     * @param t
     * @return String
     */
    protected abstract String getUpdateStatementString(T t);

    /**
     *
     * @param key
     * @return String
     */
    protected abstract String getDeleteStatementString(long key);
}
