package com.application.vozyk.ui.habits;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.application.vozyk.R;

import java.util.ArrayList;


class HabitAdapter extends RecyclerView.Adapter<HabitAdapter.HabitView> {

    private final Context mContext;
    private final ArrayList<Habit> habitList;

    public HabitAdapter(Context context, ArrayList<Habit> habits) {
        mContext = context;
        habitList = habits;
    }

    @NonNull
    @Override
    public HabitView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_habit, parent, false);
        return new HabitView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HabitView holder, final int position) {
        holder.habitName.setText(habitList.get(position).getHabitName());
        holder.upButton.setOnClickListener(v -> {
            habitList.get(position).incrementProgress();
            notifyItemChanged(position);
        });

        holder.downButton.setOnClickListener(v -> Toast.makeText(mContext, habitList.get(position).getHabitName(), Toast.LENGTH_SHORT).show());
        holder.progress.setMax(habitList.get(position).getMaxProgress());
        holder.progress.setProgress(habitList.get(position).getProgress(), true);
    }

    @Override
    public int getItemCount() {
        return habitList.size();
    }

    static class HabitView extends RecyclerView.ViewHolder {

        private final TextView habitName;
        private final ProgressBar progress;
        private final ImageButton upButton;
        private final ImageButton downButton;

        public HabitView(@NonNull View itemView) {
            super(itemView);
            habitName = itemView.findViewById(R.id.habitname);
            progress = itemView.findViewById(R.id.progressBar);
            upButton = itemView.findViewById(R.id.habityes);
            downButton = itemView.findViewById(R.id.habitno);
        }
    }
}
