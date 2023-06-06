package datastorage;

import model.Role;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoleDAO extends DAOimp<Role> {
    /**
     * Constructs a DAOimp from given param.
     *
     * @param conn
     */
    public RoleDAO(Connection conn) {
        super(conn);
    }

    /**
     * @param role
     * @return String
     */
    @Override
    protected String getCreateStatementString(Role role) {
        return String.format("INSERT INTO patient (rid, name, power) VALUES ('%s', '%s', '%s')", null, role.getName(), role.getPower());
    }

    /**
     * @param key
     * @return String
     */
    @Override
    protected String getReadByIDStatementString(long key) {
        return String.format("SELECT * FROM role WHERE rid = %d", key);
    }

    /**
     * @param result
     * @return Object T
     * @throws SQLException
     */
    @Override
    protected Role getInstanceFromResultSet(ResultSet result) throws SQLException {
        Role r = null;
        r = new Role(
                result.getInt(1),
                result.getString(2),
                result.getInt(3)
        );
        return r;
    }

    /**
     * @return String
     */
    @Override
    protected String getReadAllNotArchivedStatementString() {
        return "SELECT * FROM role";
    }

    /**
     * @param result
     * @return Objects T as ArrayList
     * @throws SQLException
     */
    @Override
    protected ArrayList<Role> getListFromResultSet(ResultSet result) throws SQLException {
        ArrayList<Role> roles = new ArrayList<Role>();
        Role r = null;
        while(result.next()) {
            r = new Role(
              result.getInt(1),
              result.getString(2),
              result.getInt(3)
            );
            roles.add(r);
        }
        return roles;
    }

    /**
     * @param role
     * @return String
     */
    @Override
    protected String getUpdateStatementString(Role role) {
        return String.format("UPDATE role SET name = %s, power = %d WHERE rid=%d",  role.getName(), role.getPower(), role.getRid());
    }

    /**
     * @param key
     * @return String
     */
    @Override
    protected String getArchiveStatementString(long key) {
        return null;
    }

    /**
     * @return String
     */
    @Override
    protected String getDeleteStatementString() {
        return "DELETE FROM role";
    }
}
