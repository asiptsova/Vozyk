package com.application.vozyk.ui.meds;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.application.vozyk.R;
import com.application.vozyk.ui.lab.labActivity;
import com.application.vozyk.ui.doctor.DoctorActivity;

public class MedsFragment extends Fragment {

        public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.activity_meds, container, false);
            final View pills=root.findViewById(R.id.pills);
            final View visits=root.findViewById(R.id.visits);
            final View labs=root.findViewById(R.id.labs);
            pills.setOnClickListener(v -> startActivity(new Intent(getContext(), MedsPills.class)));
            visits.setOnClickListener(v -> startActivity(new Intent(getContext(), DoctorActivity.class)));
            labs.setOnClickListener(v -> startActivity(new Intent(getContext(), labActivity.class)));
            return root;
    }
}