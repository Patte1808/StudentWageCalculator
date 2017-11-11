package de.pvoss.wagecalculatorandroidcomponents;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.pvoss.wagecalculatorandroidcomponents.models.Workday;

/**
 * Created by Pattelicious on 11.11.17.
 */

public class WorkdayRecyclerViewAdapter extends RecyclerView.Adapter<WorkdayRecyclerViewAdapter.RecyclerViewHolder> {
    private List<Workday> workdayList;
    private View.OnLongClickListener longClickListener;

    public WorkdayRecyclerViewAdapter(List<Workday> workdayList, View.OnLongClickListener longClickListener) {
        this.workdayList = workdayList;
        this.longClickListener = longClickListener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.workday_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        Workday workday = workdayList.get(position);
        String hourlyWage = NumberFormat.getCurrencyInstance().format(workday.getTotalWage());

        holder.workdayDescription.setText(workday.getDescription());
        holder.hourlyWage.setText(hourlyWage);
        holder.itemView.setTag(workday);
        holder.itemView.setOnLongClickListener(longClickListener);

        if(position > 0 && workdayList.get(position -1).getStart().getMonth() == workday.getStart().getMonth()
                && workdayList.get(position -1).getStart().getYear() == workday.getStart().getYear()) {
            holder.sectionHeader.setVisibility(View.GONE);
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(workday.getStart());

            String currentDate = new SimpleDateFormat("MMM YY").format(calendar.getTime());

            double monthlyWage = 0;

            /*
            Todo: use javarx or something equivalent (e.g. lambda filter)
             */
            for(Workday currentWorkday : workdayList) {
                Date workdayStart = currentWorkday.getStart();

                if(workdayStart.getMonth() == workday.getStart().getMonth() &&
                        workdayStart.getYear() == workday.getStart().getYear()) {
                    monthlyWage += (double) currentWorkday.getTotalWage();
                }
            }

            /*
            Todo: remove hardcoded string
             */
            holder.sectionHeader.setText(currentDate     + " (Overall " + NumberFormat.getCurrencyInstance().format(monthlyWage) + ")");
        }
    }

    @Override
    public int getItemCount() {
        return workdayList.size();
    }

    public void addItems(List<Workday> workdayList) {
        this.workdayList = workdayList;
        notifyDataSetChanged();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.section_header)
        TextView sectionHeader;

        @BindView(R.id.workday_description)
        TextView workdayDescription;

        @BindView(R.id.hourly_wage)
        TextView hourlyWage;

        RecyclerViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
