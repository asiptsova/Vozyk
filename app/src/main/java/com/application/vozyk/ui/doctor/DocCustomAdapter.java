package com.application.vozyk.ui.doctor;

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

public class DocCustomAdapter extends ArrayAdapter<DoctorDataModel> {

    private final FirebaseUser user;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference myRef = database.getReference();
    private final ArrayList<DoctorDataModel> arrayList;


    public DocCustomAdapter(@NonNull Context context, ArrayList<DoctorDataModel> arrayList) {
        super(context, 0, arrayList);
        this.arrayList = arrayList;
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View currentItemView = convertView;
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.doctor_custom_list_view, parent, false);
        }
        TextView doc_name = currentItemView.findViewById(R.id.tv_doc_name);
        TextView doc_reason = currentItemView.findViewById(R.id.tv_doc_reason);
        TextView doc_time = currentItemView.findViewById(R.id.tv_doc_time);
        ImageView delete_btn = currentItemView.findViewById(R.id.doc_delete_btn);

        DoctorDataModel doctorDataModel = arrayList.get(position);

        doc_name.setText(doctorDataModel.getName());
        doc_reason.setText(doctorDataModel.getReason());
        String result = doctorDataModel.getDate() + "/" + (doctorDataModel.getMonth()) + "/" + doctorDataModel.getYear();
        doc_time.setText(result);


        delete_btn.setOnClickListener(view -> {
            DoctorDataModel ddm = arrayList.get(position);
            myRef.child("AppointmentRecord").child(user.getUid()).child(ddm.key).removeValue();
            Toast.makeText(getContext(), "Deleted", Toast.LENGTH_SHORT).show();
        });


        return currentItemView;
    }
}
