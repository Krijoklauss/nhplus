package datastorage;

import model.Caregiver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CaregiverDAO extends DAOimp<Caregiver> {

	/**
	 * Constructs a DAOimp from given param.
	 *
	 * @param conn
	 */
	public CaregiverDAO(Connection conn) {
		super(conn);
	}

	/**
	 * @param caregiver
	 * @return
	 */
	@Override
	protected String getCreateStatementString(Caregiver caregiver) {
		return String.format("INSERT INTO caregiver (firstname, surname, phonenumber) VALUES ('%s', '%s', '%s')",
			caregiver.getFirstName(), caregiver.getSurname(), caregiver.getPhoneNumber());
	}

	/**
	 * @param key
	 * @return
	 */
	@Override
	protected String getReadByIDStatementString(long key) {
		return String.format("SELECT * FROM caregiver WHERE cid = %d", key);
	}

	/**
	 * @param result
	 * @return
	 * @throws SQLException
	 */
	@Override
	protected Caregiver getInstanceFromResultSet(ResultSet result) throws SQLException {
		Caregiver c = new Caregiver(result.getInt(1), result.getString(2), result.getString(3), result.getString(4));
		return c;
	}

	/**
	 * @return all caregivers
	 */
	@Override
	protected String getReadAllStatementString() {
		return "SELECT * FROM caregiver";
	}

	/**
	 * @param result
	 * @return
	 * @throws SQLException
	 */
	@Override
	protected ArrayList<Caregiver> getListFromResultSet(ResultSet result) throws SQLException {
		ArrayList<Caregiver> list = new ArrayList<Caregiver>();
		Caregiver c = null;
		while (result.next()) {
			c = new Caregiver(result.getInt(1), result.getString(2), result.getString(3), result.getString(4));
			list.add(c);
		}
		return list;
	}

	/**
	 * @param caregiver
	 * @return
	 */
	@Override
	protected String getUpdateStatementString(Caregiver caregiver) {
		return String.format("UPDATE caregiver SET firstname = '%s', surname = '%s', phonenumber = '%s' WHERE cid = %d", caregiver.getFirstName(), caregiver.getSurname(), caregiver.getPhoneNumber(), caregiver.getCid());
	}

	/**
	 * @param key
	 * @return
	 */
	@Override
	protected String getDeleteStatementString(long key) {
		return String.format("Delete FROM caregiver WHERE cid= %d", key);
	}
}
