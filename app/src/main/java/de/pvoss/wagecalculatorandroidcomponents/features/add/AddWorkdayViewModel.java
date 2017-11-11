package de.pvoss.wagecalculatorandroidcomponents.features.add;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import de.pvoss.wagecalculatorandroidcomponents.models.Workday;
import de.pvoss.wagecalculatorandroidcomponents.service.AppDatabase;

/**
 * Created by Pattelicious on 11.11.17.
 */

public class AddWorkdayViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;

    public AddWorkdayViewModel(@NonNull Application application) {
        super(application);

        this.appDatabase = AppDatabase.getInstance(this.getApplication());
    }

    public void addWorkday(final Workday workday) {
        new addAsyncTask(appDatabase).execute(workday);
    }

    private static class addAsyncTask extends AsyncTask<Workday, Void, Void> {

        private AppDatabase db;

        addAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final Workday... params) {
            db.workdayModel().addWorkday(params[0]);
            return null;
        }

    }
}
