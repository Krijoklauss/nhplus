package model;

public class Caregiver extends User{

	private long cid;

	private String phoneNumber;

	/**
	 *
	 * @param firstName
	 * @param surname
	 * @param phoneNumber
	 */
	public Caregiver(String firstName, String surname, String phoneNumber) {
		super(firstName, surname);
		this.phoneNumber = phoneNumber;
	}

	/**
	 *
	 * @param firstName
	 * @param surname
	 * @param cid
	 * @param phoneNumber
	 */
	public Caregiver(long cid, String firstName, String surname, String phoneNumber) {
		super(firstName, surname);
		this.cid = cid;
		this.phoneNumber = phoneNumber;
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


}
