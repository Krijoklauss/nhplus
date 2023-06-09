package datastorage;

/**
 * A DAO factory of which only one instance can be created.
 * It's there to create other DAOs.
 */
public class DAOFactory {

    private static DAOFactory instance;

    private DAOFactory() {

    }

    public static DAOFactory getDAOFactory() {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }

    /**
     * @return a new TreatmentDAO.
     */
    public TreatmentDAO createTreatmentDAO() {
        return new TreatmentDAO(ConnectionBuilder.getConnection());
    }

    /**
     * @return a new PatientDAO.
     */
    public PatientDAO createPatientDAO() {
        return new PatientDAO(ConnectionBuilder.getConnection());
    }

    /**
     *
     * @return a new CaregiverDAO.
     */
    public CaregiverDAO createCaregiverDAO() {
        return new CaregiverDAO(ConnectionBuilder.getConnection());
    }

    /**
     *
     * @return a new RoleDAO.
     */
    public RoleDAO createRoleDAO() { return new RoleDAO(ConnectionBuilder.getConnection()); }
}