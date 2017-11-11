package de.pvoss.wagecalculatorandroidcomponents.service;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import de.pvoss.wagecalculatorandroidcomponents.models.Workday;

/**
 * Created by Pattelicious on 11.11.17.
 */

@Dao
public interface WorkdayDao {

    @Query("select * from Workday")
    LiveData<List<Workday>> getAllWorkdays();

    @Query("select * from Workday where id = :id")
    Workday getWorkdayById(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addWorkday(Workday workday);

    @Delete
    void deleteWorkday(Workday workday);
}
