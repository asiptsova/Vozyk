package com.application.vozyk.ui.meds;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.application.vozyk.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class CustomAdapterNew extends ArrayAdapter<MedsRecordHandler> {

    private final ArrayList<MedsRecordHandler> arrayList;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference databaseReference = database.getReference();
    final FirebaseUser user;


    public CustomAdapterNew(@NonNull Context context, @NonNull ArrayList<MedsRecordHandler> arrayList) {
        super(context, 0, arrayList);
        this.arrayList = arrayList;
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null)
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.custom_list_view, parent, false);
        TextView name_view = itemView.findViewById(R.id.tv_meds_name);
        name_view.setText(arrayList.get(position).getName());

        TextView food_view = itemView.findViewById(R.id.tv_meds_food);
        if (arrayList.get(position).getBeforeFood())
            food_view.setText("Before Food");
        else
            food_view.setText("After Food");
        TextView time = itemView.findViewById(R.id.tv_meds_time);
        StringBuilder output_time = new StringBuilder();
        for (Time.AlarmBundle i : arrayList.get(position).getReminder()) {
            String j = i.getTime();
            if (j.contains(Time.MORNING))
                output_time.append("Morning ");
             else if (j.contains(Time.AFTERNOON))
                output_time.append("Afternoon ");
             else if (j.contains(Time.NIGHT))
                output_time.append("Night ");
            else
                output_time.append(j.substring(0, 2)).append(":").append(j.substring(2, 4));
        }
        time.setText(output_time.toString());
        TextView doseView = itemView.findViewById(R.id.note_view);

        if (arrayList.get(position).getDose().isEmpty())
            doseView.setText("No Dose");
        else
            doseView.setText(arrayList.get(position).getDose());

        itemView.findViewById(R.id.delete_btn).setOnClickListener(view -> {
            MedsRecordHandler mrd = arrayList.get(position);
            databaseReference.child("MedicineRecord").child(user.getUid()).child(mrd.key).removeValue();
            Toast.makeText(getContext(), "Deleted", Toast.LENGTH_SHORT).show();
        });

        itemView.findViewById(R.id.update_btn).setOnClickListener(view -> {

            Intent i = new Intent(getContext(), UpdateMeds.class);
            MedsRecordHandler mrd = arrayList.get(position);

            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("key", mrd.key);
            getContext().startActivity(i);
        });

        return itemView;
    }
}
