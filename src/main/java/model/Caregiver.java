package model;

import java.time.LocalDate;

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
	 * @param phoneNumber
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
	 * @param firstName
	 * @param surname
	 * @param cid
	 * @param phoneNumber
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

	public boolean getArchived() {
		return archived;
	}

	public LocalDate getArchivedAt() {
		return archivedAt;
	}

	public String getUsername() { return username; }

	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() { return password; }
	public Role getRole() { return role; }
}
