package de.pvoss.wagecalculatorandroidcomponents.service;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import de.pvoss.wagecalculatorandroidcomponents.models.Workday;
import de.pvoss.wagecalculatorandroidcomponents.utils.Converters;

/**
 * Created by Pattelicious on 11.11.17.
 */

@Database(entities = {Workday.class}, version = 1)
@TypeConverters(Converters.class)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "wagecalculator")
                    .build();
        }

        return INSTANCE;
    }

    public abstract WorkdayDao workdayModel();

}
