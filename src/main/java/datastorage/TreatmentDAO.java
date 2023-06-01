package datastorage;

import model.Treatment;
import utils.DateConverter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TreatmentDAO extends DAOimp<Treatment> {
    /**
     * Sets connection from DAOimp
     * @param conn
     */
    public TreatmentDAO(Connection conn) {
        super(conn);
    }

    /**
     * Generates SQL statement for creating new treatments
     * @param treatment
     * @return
     */
    @Override
    protected String getCreateStatementString(Treatment treatment) {
        return String.format("INSERT INTO treatment (pid, treatment_date, begin, end, description, remarks) VALUES " +
                "(%d, '%s', '%s', '%s', '%s', '%s')", treatment.getPid(), treatment.getDate(),
                treatment.getBegin(), treatment.getEnd(), treatment.getDescription(),
                treatment.getRemarks());
    }

    /**
     * Generates SQL statement for retrieving a treatment record by its ID
     * @param key
     * @return
     */
    @Override
    protected String getReadByIDStatementString(long key) {
        return String.format("SELECT * FROM treatment WHERE tid = %d", key);
    }

    /**
     * Converts the resultSet into a Treatment
     * @param result
     * @return
     * @throws SQLException
     */
    @Override
    protected Treatment getInstanceFromResultSet(ResultSet result) throws SQLException {
        LocalDate date = DateConverter.convertStringToLocalDate(result.getString(3));
        LocalTime begin = DateConverter.convertStringToLocalTime(result.getString(4));
        LocalTime end = DateConverter.convertStringToLocalTime(result.getString(5));
        Treatment m = new Treatment(result.getLong(1), result.getLong(2),
                date, begin, end, result.getString(6), result.getString(7));
        return m;
    }

    /**
     * Generates SQL statement to retrieve all treatments
     * @return
     */
    @Override
    protected String getReadAllNotArchivedStatementString() {
        return "SELECT treatment.* FROM treatment LEFT JOIN patient on treatment.pid=patient.pid WHERE patient.archived=false";
    }

    /**
     * Converts the ResultSet into a ArrayList
     * @param result
     * @return
     * @throws SQLException
     */
    @Override
    protected ArrayList<Treatment> getListFromResultSet(ResultSet result) throws SQLException {
        ArrayList<Treatment> list = new ArrayList<Treatment>();
        Treatment t = null;
        while (result.next()) {
            LocalDate date = DateConverter.convertStringToLocalDate(result.getString(3));
            LocalTime begin = DateConverter.convertStringToLocalTime(result.getString(4));
            LocalTime end = DateConverter.convertStringToLocalTime(result.getString(5));
            t = new Treatment(result.getLong(1), result.getLong(2),
                    date, begin, end, result.getString(6), result.getString(7));
            list.add(t);
        }
        return list;
    }

    /**
     * Generates SQL statement for updating a treatment by ID
     * @param treatment
     * @return
     */
    @Override
    protected String getUpdateStatementString(Treatment treatment) {
        return String.format("UPDATE treatment SET pid = %d, treatment_date ='%s', begin = '%s', end = '%s'," +
                "description = '%s', remarks = '%s' WHERE tid = %d", treatment.getPid(), treatment.getDate(),
                treatment.getBegin(), treatment.getEnd(), treatment.getDescription(), treatment.getRemarks(),
                treatment.getTid());
    }

    /**
     * Generates SQL statement for deleting a treatment by ID
     * @param key
     * @return
     */
    @Override
    protected String getArchiveStatementString(long key) {
        return String.format("Delete FROM treatment WHERE tid= %d", key);
    }

    /**
     * Generates an Arraylist for all treatments by the passed patientID
     * @param pid
     * @return
     * @throws SQLException
     */
    public List<Treatment> readTreatmentsByPid(long pid) throws SQLException {
        ArrayList<Treatment> list = new ArrayList<Treatment>();
        Treatment object = null;
        Statement st = conn.createStatement();
        ResultSet result = st.executeQuery(getReadAllTreatmentsOfOnePatientByPid(pid));
        list = getListFromResultSet(result);
        return list;
    }

    /**
     * Generates SQL statement for retrieving a treatment record by patientID
     * @param pid
     * @return
     */
    private String getReadAllTreatmentsOfOnePatientByPid(long pid){
        return String.format("SELECT * FROM treatment WHERE pid = %d", pid);
    }

    /**
     * Generates SQL statement for deleting a treatment by treatmentId
     * @param key
     * @throws SQLException
     */
    public void deleteByTid(long key) throws SQLException {
        Statement st = conn.createStatement();
        st.executeUpdate(String.format("DELETE FROM treatment WHERE tid= %d", key));
    }

    /**
     * Generates SQL statement for deleting a treatment
     * @return
     */
    protected String getDeleteStatementString() {
        return String.format("DELETE FROM treatment WHERE tid IN (SELECT tid FROM treatment JOIN patient ON treatment.pid = patient.pid WHERE patient.archived = true AND patient.archivedat <= DATE_SUB(CURDATE(), INTERVAL 10 YEAR))");
    }
}