package model;

import java.time.LocalDate;

public class Caregiver extends User{
	private long cid;
	private String phoneNumber;
	private boolean archived;
	private LocalDate archivedAt;

	/**
	 *
	 * @param firstName
	 * @param surname
	 * @param phoneNumber
	 */
	public Caregiver(String firstName, String surname, String phoneNumber, boolean archived, LocalDate archivedAt) {
		super(firstName, surname);
		this.phoneNumber = phoneNumber;
		this.archived = archived;
		this.archivedAt = archivedAt;
	}

	/**
	 *
	 * @param firstName
	 * @param surname
	 * @param cid
	 * @param phoneNumber
	 */
	public Caregiver(long cid, String firstName, String surname, String phoneNumber, boolean archived, LocalDate archivedAt) {
		super(firstName, surname);
		this.cid = cid;
		this.phoneNumber = phoneNumber;
		this.archived = archived;
		this.archivedAt = archivedAt;
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
}
