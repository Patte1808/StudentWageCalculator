package de.pvoss.wagecalculatorandroidcomponents;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.pvoss.wagecalculatorandroidcomponents.features.add.AddWorkday;
import de.pvoss.wagecalculatorandroidcomponents.models.Workday;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener {

    private MainActivityViewModel mainActivityViewModel;
    private WorkdayRecyclerViewAdapter recyclerViewAdapter;

    @BindView(R.id.workday_list)
    RecyclerView recyclerView;

    @BindView(R.id.add_workday)
    FloatingActionButton addWorkday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        recyclerViewAdapter = new WorkdayRecyclerViewAdapter(new ArrayList<Workday>(), this);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        mainActivityViewModel.getWorkdayList().observe(MainActivity.this,
                new Observer<List<Workday>>() {
                    @Override
                    public void onChanged(@Nullable List<Workday> workdayList) {
                        recyclerViewAdapter.addItems(workdayList);
                    }
                });


        addWorkday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddWorkday.class));
            }
        });
    }

    @Override
    public boolean onLongClick(View v) {
        Workday workday = (Workday) v.getTag();
        return true;
    }
}
