package model;

import utils.DateConverter;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * A patient can have multiple treatments
 */
public class Treatment {
    private long tid;
    private long pid;
    private LocalDate date;
    private LocalTime begin;
    private LocalTime end;
    private String description;
    private String remarks;

    /**
     * constructs a treatment from the params below
     * @param pid
     * @param date
     * @param begin
     * @param end
     * @param description
     * @param remarks
     */
    public Treatment(long pid, LocalDate date, LocalTime begin,
                     LocalTime end, String description, String remarks) {
        this.pid = pid;
        this.date = date;
        this.begin = begin;
        this.end = end;
        this.description = description;
        this.remarks = remarks;
    }

    /**
     * constructs a treatment from the params below
     * @param tid
     * @param pid
     * @param date
     * @param begin
     * @param end
     * @param description
     * @param remarks
     */
    public Treatment(long tid, long pid, LocalDate date, LocalTime begin,
                     LocalTime end, String description, String remarks) {
        this.tid = tid;
        this.pid = pid;
        this.date = date;
        this.begin = begin;
        this.end = end;
        this.description = description;
        this.remarks = remarks;
    }

    /**
     *
     * @return treatment id
     */
    public long getTid() {
        return tid;
    }

    /**
     *
     * @return patient id
     */
    public long getPid() {
        return this.pid;
    }

    /**
     *
     * @return date of treatment
     */
    public String getDate() {
        return date.toString();
    }

    /**
     *
     * @return starting time of treatment
     */
    public String getBegin() {
        return begin.toString();
    }

    /**
     *
     * @return end time of treatment
     */
    public String getEnd() {
        return end.toString();
    }

    /**
     * convert the param into LocalDate format and save it in date
     * @param s_date
     */
    public void setDate(String s_date) {
        LocalDate date = DateConverter.convertStringToLocalDate(s_date);
        this.date = date;
    }

    /**
     * convert the param into LocalTime format and save it in begin
     * @param begin
     */
    public void setBegin(String begin) {
        LocalTime time = DateConverter.convertStringToLocalTime(begin);
        this.begin = time;
    }

    /**
     * convert the param into LocalTime format and save it in end
     * @param end
     */
    public void setEnd(String end) {
        LocalTime time = DateConverter.convertStringToLocalTime(end);
        this.end = time;
    }

    /**
     *
     * @return description of treatment
     */
    public String getDescription() {
        return description;
    }

    /**
     * set
     * @param description as description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return remarks (free text)
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * set
     * @param remarks as remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     *
     * @return object attributes as string
     */
    public String toString() {
        return "\nBehandlung" + "\nTID: " + this.tid +
                "\nPID: " + this.pid +
                "\nDate: " + this.date +
                "\nBegin: " + this.begin +
                "\nEnd: " + this.end +
                "\nDescription: " + this.description +
                "\nRemarks: " + this.remarks + "\n";
    }
}