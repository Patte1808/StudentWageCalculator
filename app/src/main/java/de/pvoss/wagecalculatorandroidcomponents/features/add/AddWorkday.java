package de.pvoss.wagecalculatorandroidcomponents.features.add;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.pvoss.wagecalculatorandroidcomponents.R;
import de.pvoss.wagecalculatorandroidcomponents.models.Workday;

public class AddWorkday extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener {

    @BindView(R.id.workday_description_edit)
    TextInputEditText workdayDescription;

    @BindView(R.id.save_workday)
    Button saveWorkday;

    @BindView(R.id.set_start_time)
    TextInputEditText setStartTime;

    @BindView(R.id.set_end_time)
    TextInputEditText setEndTime;

    @BindView(R.id.set_hourly_wage)
    TextInputEditText setHourlyWage;

    private AddWorkdayViewModel addWorkdayViewModel;
    private Date startDate;
    private Date endDate;

    private enum ORIGIN { START, END };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workday);

        ButterKnife.bind(this);

        addWorkdayViewModel = ViewModelProviders.of(this).get(AddWorkdayViewModel.class);

        setStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        AddWorkday.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show(getFragmentManager(), ORIGIN.START.toString());
            }
        });

        setEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        AddWorkday.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show(getFragmentManager(), ORIGIN.END.toString());
            }
        });

        saveWorkday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(workdayDescription.getText() == null) {
                    Toast.makeText(AddWorkday.this, "Missing fields",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Workday workday = new Workday(workdayDescription.getText().toString(),
                            startDate, endDate, Double.parseDouble(setHourlyWage.getText().toString()));

                    addWorkdayViewModel.addWorkday(workday);
                }

                finish();
            }
        });
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

        if(ORIGIN.valueOf(view.getTag()) == ORIGIN.START) {
            startDate = new Date(year, monthOfYear, dayOfMonth);
        } else {
            endDate = new Date(year, monthOfYear, dayOfMonth);
        }


        TimePickerDialog timePickerDialog = TimePickerDialog.newInstance(
                AddWorkday.this,
                true
        );

        timePickerDialog.show(getFragmentManager(), view.getTag());
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        Calendar calendar = Calendar.getInstance();

        if(ORIGIN.valueOf(view.getTag()) == ORIGIN.START) {
            calendar.setTime(startDate);
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(Calendar.MINUTE, minute);
            calendar.set(Calendar.SECOND, second);

            startDate = calendar.getTime();
            setStartTime.setText(startDate + "");
        } else {
            calendar.setTime(endDate);
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(Calendar.MINUTE, minute);
            calendar.set(Calendar.SECOND, second);

            endDate = calendar.getTime();
            setEndTime.setText(endDate + "");
        }
        Log.wtf("as", "onTimeSet: " + view.getView().getTag());

    }
}
