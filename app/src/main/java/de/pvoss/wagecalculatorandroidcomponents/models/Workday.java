package de.pvoss.wagecalculatorandroidcomponents.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Pattelicious on 11.11.17.
 */

@Entity
public class Workday {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String description;

    public Workday(String description) {
        this.description = description;
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
}
