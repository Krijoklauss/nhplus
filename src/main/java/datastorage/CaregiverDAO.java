package datastorage;

import model.Caregiver;
import utils.DateConverter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
		String archivedAt = (caregiver.getArchivedAt() != null) ? "'" + caregiver.getArchivedAt() + "'" : null;
		return String.format("INSERT INTO caregiver (firstname, surname, phonenumber, archived, archivedat) VALUES ('%s', '%s', '%s', '%b', %s)",
			caregiver.getFirstName(), caregiver.getSurname(), caregiver.getPhoneNumber(), caregiver.getArchived(), archivedAt);
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
		LocalDate archivedAt = DateConverter.convertStringToLocalDate(result.getString(6));
		Caregiver c = new Caregiver(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getBoolean(5), archivedAt);
		return c;
	}

	/**
	 * @return all caregivers
	 */
	@Override
	protected String getReadAllNotArchivedStatementString() {
		return "SELECT * FROM caregiver WHERE archived = false";
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
			LocalDate archivedAt = DateConverter.convertStringToLocalDate(result.getString(6));
			c = new Caregiver(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getBoolean(5), archivedAt);
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
	 * @return String
	 */

	/**
	 * @return
	 */
	@Override
	protected String getDeleteStatementString() {
		return String.format("DELETE FROM caregiver WHERE archived = true AND archivedat <= DATE_SUB(CURDATE(), INTERVAL 10 YEAR)");
	}

	@Override
	protected String getArchiveStatementString(long key) {
		LocalDate date = LocalDate.now();
		return String.format("UPDATE caregiver SET archived=true, archivedat='%s' WHERE cid=%d", date, key);
	}
}
