package model;

/**
 * Roles are predefined login roles which allow different type of actions in the gui
 */
public class Role {
    private int rid;
    private String name;
    private int power;

    /**
     * Constructs a new Role
     *
     * @param rid
     * @param name
     * @param power
     */
    public Role(int rid, String name, int power) {
        this.rid = rid;
        this.name = name;
        this.power = power;
    }

    /**
     *
     * @return rid
     */
    public int getRid() { return rid; }

    /**
     *
     * @param rid as rid
     */
    public void setRid(int rid) { this.rid = rid; }

    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name as name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return power
     */
    public int getPower() {
        return power;
    }

    /**
     *
     * @param power as power
     */
    public void setPower(int power) {
        this.power = power;
    }
}
