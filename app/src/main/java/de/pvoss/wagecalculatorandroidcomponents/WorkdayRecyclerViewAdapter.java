package de.pvoss.wagecalculatorandroidcomponents;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        holder.workdayDescription.setText(workday.getDescription());
        holder.itemView.setTag(workday);
        holder.itemView.setOnLongClickListener(longClickListener);
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
        @BindView(R.id.workday_description)
        TextView workdayDescription;

        RecyclerViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
