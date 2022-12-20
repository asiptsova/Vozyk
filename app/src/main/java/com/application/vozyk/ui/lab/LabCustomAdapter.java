package com.application.vozyk.ui.lab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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

public class LabCustomAdapter extends ArrayAdapter<LabTestDataModel> {

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference df = database.getReference();
    final FirebaseUser user;
    private final ArrayList<LabTestDataModel> arrayList;

    public LabCustomAdapter(@NonNull Context context, ArrayList<LabTestDataModel> arrayList) {
        super(context, 0, arrayList);
        this.arrayList = arrayList;
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.lab_custom_list_view, parent, false);
        }

        TextView labName = itemView.findViewById(R.id.tv_lab_name);
        TextView labDoctor = itemView.findViewById(R.id.tv_lab_doctor);
        TextView lab_date = itemView.findViewById(R.id.view_lab_time);
        ImageView delete = itemView.findViewById(R.id.bt_lab_delete);
        LabTestDataModel labTestDataModel = arrayList.get(position);

        labName.setText(labTestDataModel.getTestName());
        labDoctor.setText(labTestDataModel.getDoctorName());

        String result = labTestDataModel.getDay() + "/" + (labTestDataModel.getMonth()) + "/" + labTestDataModel.getYear();
        lab_date.setText(result);

        delete.setOnClickListener(view -> {
            LabTestDataModel obj = arrayList.get(position);
            df.child("LabTestRecord").child(user.getUid()).child(obj.key).removeValue();
            Toast.makeText(getContext(), "Deleted", Toast.LENGTH_SHORT).show();
        });
        return itemView;
    }
}
