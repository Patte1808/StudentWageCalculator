package de.pvoss.wagecalculatorandroidcomponents.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * Created by Pattelicious on 11.11.17.
 */

@Entity
public class Workday {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String description;
    private Date start;
    private Date end;
    private double hourlyWage;

    public Workday(String description,
                   Date start,
                   Date end,
                   double hourlyWage) {
        this.description = description;
        this.start = start;
        this.end = end;
        this.hourlyWage = hourlyWage;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public double getHourlyWage() {
        return hourlyWage;
    }

    public void setHourlyWage(double hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

    public long getTotalWage() {
        long timeDifference = this.getEnd().getTime() - this.getStart().getTime();

        return (timeDifference / (60 * 60 * 1000)) * (long) this.getHourlyWage();
    }
}
