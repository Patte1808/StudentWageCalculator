package de.pvoss.wagecalculatorandroidcomponents.features.add;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.pvoss.wagecalculatorandroidcomponents.R;
import de.pvoss.wagecalculatorandroidcomponents.models.Workday;

public class AddWorkday extends AppCompatActivity {

    @BindView(R.id.workday_description_edit)
    EditText workdayDescription;

    @BindView(R.id.save_workday)
    Button saveWorkday;

    private AddWorkdayViewModel addWorkdayViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workday);

        ButterKnife.bind(this);

        addWorkdayViewModel = ViewModelProviders.of(this).get(AddWorkdayViewModel.class);

        saveWorkday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(workdayDescription.getText() == null) {
                    Toast.makeText(AddWorkday.this, "Missing fields", Toast.LENGTH_SHORT).show();
                } else {
                    addWorkdayViewModel.addWorkday(new Workday(workdayDescription.getText().toString()));
                }

                finish();
            }
        });
    }
}
