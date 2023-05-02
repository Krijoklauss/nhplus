package model;

/**
 * Person is the parent class for patients and nurses.
 */
public abstract class Person {
    private String firstName;
    private String surname;

    /**
     * provides constructor for child-classes
     * @param firstName
     * @param surname
     */
    public Person(String firstName, String surname) {
        this.firstName = firstName;
        this.surname = surname;
    }

    /**
     *
     * @return person/object first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName gets set as new first name for object
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return person/object surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     *
     * @param surname gets set as new surname for object
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }
}
