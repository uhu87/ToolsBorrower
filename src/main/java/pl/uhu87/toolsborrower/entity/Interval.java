package pl.uhu87.toolsborrower.entity;

import javax.persistence.Entity;
import java.time.LocalDate;


public class Interval {

    LocalDate start;
    LocalDate end;

    public Interval() {
    }

    public Interval(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }
}
