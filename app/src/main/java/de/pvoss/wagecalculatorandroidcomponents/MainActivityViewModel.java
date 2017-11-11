package de.pvoss.wagecalculatorandroidcomponents;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import de.pvoss.wagecalculatorandroidcomponents.models.Workday;
import de.pvoss.wagecalculatorandroidcomponents.service.AppDatabase;

/**
 * Created by Pattelicious on 11.11.17.
 */

public class MainActivityViewModel extends AndroidViewModel {

    private final LiveData<List<Workday>> workdayList;

    private AppDatabase appDatabase;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        appDatabase = AppDatabase.getInstance(this.getApplication());

        workdayList = appDatabase.workdayModel().getAllWorkdays();
    }

    public LiveData<List<Workday>> getWorkdayList() {
        return workdayList;
    }
}
