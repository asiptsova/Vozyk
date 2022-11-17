package com.application.vozyk.ui.notifications;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.application.vozyk.R;
import com.application.vozyk.lab.labActivity;
import com.application.vozyk.ui.doctor.DoctorActivity;

public class NotificationFragment extends Fragment {

        public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.activity_notification_fragment, container, false);
            final Button pills=root.findViewById(R.id.pills);
            final Button visits=root.findViewById(R.id.visits);
            final Button labs=root.findViewById(R.id.labs);

            pills.setOnClickListener(v -> startActivity(new Intent(getContext(), NotificationsPills.class)));
            visits.setOnClickListener(v -> startActivity(new Intent(getContext(), DoctorActivity.class)));
            labs.setOnClickListener(v -> startActivity(new Intent(getContext(), labActivity.class)));
            return root;
    }
}