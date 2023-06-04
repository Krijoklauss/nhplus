package datastorage;

import model.Patient;
import utils.DateConverter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Implements the Interface <code>DAOImp</code>. Overrides methods to generate specific patient-SQL-queries.
 */
public class PatientDAO extends DAOimp<Patient> {

	/**
	 * constructs Onbject. Calls the Constructor from <code>DAOImp</code> to store the connection.
	 *
	 * @param conn
	 */
	public PatientDAO(Connection conn) {
		super(conn);
	}

	/**
	 * generates a <code>INSERT INTO</code>-Statement for a given patient
	 *
	 * @param patient for which a specific INSERT INTO is to be created
	 * @return <code>String</code> with the generated SQL.
	 */
	@Override
	protected String getCreateStatementString(Patient patient) {
		String archivedAt = (patient.getArchivedAt() != null) ? "'" + patient.getArchivedAt() + "'" : null;
		return String.format("INSERT INTO patient (firstname, surname, dateOfBirth, carelevel, roomnumber, archived, archivedat) VALUES ('%s', '%s', '%s', '%s', '%s','%b',%s)",
			patient.getFirstName(), patient.getSurname(), patient.getDateOfBirth(), patient.getCareLevel(), patient.getRoomnumber(), patient.getArchived(), archivedAt);
	}

	/**
	 * generates a <code>select</code>-Statement for a given key
	 *
	 * @param key for which a specific SELECTis to be created
	 * @return <code>String</code> with the generated SQL.
	 */
	@Override
	protected String getReadByIDStatementString(long key) {
		return String.format("SELECT * FROM patient WHERE pid = %d", key);
	}

	/**
	 * maps a <code>ResultSet</code> to a <code>Patient</code>
	 *
	 * @param result ResultSet with a single row. Columns will be mapped to a patient-object.
	 * @return patient with the data from the resultSet.
	 */
	@Override
	protected Patient getInstanceFromResultSet(ResultSet result) throws SQLException {
		Patient p = null;
		LocalDate date = DateConverter.convertStringToLocalDate(result.getString(4));
		LocalDate archivedAt = DateConverter.convertStringToLocalDate(result.getString(8));
		p = new Patient(result.getInt(1), result.getString(2),
			result.getString(3), date,
			result.getString(5), result.getString(6),
			result.getBoolean(7), archivedAt);
		return p;
	}

	/**
	 * generates a <code>SELECT</code>-Statement for all not archived patients.
	 *
	 * @return <code>String</code> with the generated SQL.
	 */
	@Override
	protected String getReadAllNotArchivedStatementString() {
		return "SELECT * FROM patient WHERE archived=false";
	}

	/**
	 * maps a <code>ResultSet</code> to a <code>Patient-List</code>
	 *
	 * @param result ResultSet with a multiple rows. Data will be mapped to patient-object.
	 * @return ArrayList with patients from the resultSet.
	 */
	@Override
	protected ArrayList<Patient> getListFromResultSet(ResultSet result) throws SQLException {
		ArrayList<Patient> list = new ArrayList<Patient>();
		Patient p = null;
		while (result.next()) {
			LocalDate birthDate = DateConverter.convertStringToLocalDate(result.getString(4));
			LocalDate archivedAt = DateConverter.convertStringToLocalDate(result.getString(8));
			p = new Patient(result.getInt(1), result.getString(2),
				result.getString(3), birthDate,
				result.getString(5), result.getString(6),
				result.getBoolean(7), archivedAt);
			list.add(p);
		}
		return list;
	}

	/**
	 * generates a <code>UPDATE</code>-Statement for a given patient
	 *
	 * @param patient for which a specific update is to be created
	 * @return <code>String</code> with the generated SQL.
	 */
	@Override
	protected String getUpdateStatementString(Patient patient) {
		String archivedAt = (patient.getArchivedAt() != null) ? "'" + patient.getArchivedAt() + "'" : null;
		return String.format("UPDATE patient SET firstname = '%s', surname = '%s', dateOfBirth = '%s', carelevel = '%s', " +
				"roomnumber = '%s', archived='%b', archivedat=%s WHERE pid = %d", patient.getFirstName(), patient.getSurname(), patient.getDateOfBirth(),
			patient.getCareLevel(), patient.getRoomnumber(), patient.getArchived(), archivedAt, patient.getPid());
	}

	/**
	 * generates a <code>UPDATE</code>-Statement for a given key
	 *
	 * @param key for which a specific UPDATE is to be created
	 * @return <code>String</code> with the generated SQL.
	 */
	@Override
	protected String getArchiveStatementString(long key) {
		LocalDate date = LocalDate.now();
		return String.format("UPDATE patient SET archived=true, archivedat='%s' WHERE pid=%d", date, key);
	}

	/**
	 * generates a <code>DELETE</code>-Statement for automatic deleting patients
	 *
	 * @return <code>String</code> with generated SQL.
	 */
	protected String getDeleteStatementString() {
		return String.format("DELETE FROM patient WHERE archived = true AND archivedat <= DATE_SUB(CURDATE(), INTERVAL 10 YEAR)");
	}
}
