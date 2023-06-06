package model;

public class Role {

    private int rid;
    private String name;
    private int power;
    public Role(int rid, String name, int power) {
        this.rid = rid;
        this.name = name;
        this.power = power;
    }

    public int getRid() { return rid; }
    public void setRid(int rid) { this.rid = rid; }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getPower() {
        return power;
    }
    public void setPower(int power) {
        this.power = power;
    }
}
