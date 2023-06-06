package model;

import java.time.LocalDate;

/**
 * Caregivers are nurses treating the patients
 */
public class Caregiver extends User {
	private long cid;
	private String username;
	private String password;
	private String phoneNumber;
	private Role role;
	private boolean archived;
	private LocalDate archivedAt;

	/**
	 *
	 * @param firstName
	 * @param surname
	 * @param username
	 * @param password
	 * @param phoneNumber
	 * @param role
	 * @param archived
	 * @param archivedAt
	 */
	public Caregiver(String firstName, String surname, String username, String password, String phoneNumber, Role role, boolean archived, LocalDate archivedAt) {
		super(firstName, surname);
		this.phoneNumber = phoneNumber;
		this.archived = archived;
		this.archivedAt = archivedAt;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	/**
	 *
	 * @param cid
	 * @param firstName
	 * @param surname
	 * @param username
	 * @param password
	 * @param phoneNumber
	 * @param role
	 * @param archived
	 * @param archivedAt
	 */
	public Caregiver(long cid, String firstName, String surname, String username, String password, String phoneNumber, Role role, boolean archived, LocalDate archivedAt) {
		super(firstName, surname);
		this.cid = cid;
		this.phoneNumber = phoneNumber;
		this.archived = archived;
		this.archivedAt = archivedAt;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	/**
	 *
	 * @return cid
	 */
	public long getCid() {
		return cid;
	}

	/**
	 * set
	 * @param cid as cid
	 */
	public void setCid(long cid) {
		this.cid = cid;
	}

	/**
	 *
	 * @return phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * set
	 * @param phoneNumber as phoneNumber
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 *
	 * @return archived
	 */
	public boolean getArchived() {
		return archived;
	}

	/**
	 *
	 * @return archivedAt
	 */
	public LocalDate getArchivedAt() {
		return archivedAt;
	}

	/**
	 *
	 * @return username
	 */
	public String getUsername() { return username; }

	/**
	 *
	 * @param username as username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 *
	 * @return password
	 */
	public String getPassword() { return password; }

	/**
	 *
	 * @return role
	 */
	public Role getRole() { return role; }
}
