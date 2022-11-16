package com.application.vozyk.ui.notifications;

import android.app.AlertDialog;
import android.content.Context;

import java.util.ArrayList;


public class InputValidationHandler {

    public static boolean inputValidation(String medicineName, ArrayList<TIME.AlarmBundle> time) {
        return !medicineName.isEmpty() && !time.isEmpty() && medicineName.length() <= 15;
    }


    public static void showDialog(Context context) {
        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(context);

        builder.setMessage("Required fields are empty or too large or have special characters");
        builder.setTitle("MediClock Alert !");

        builder
                .setPositiveButton(
                        "OK",
                        (dialog, which) -> dialog.cancel());


        AlertDialog alertDialog = builder.create();

        alertDialog.show();
    }
}
