package com.application.vozyk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final String CREATE_TABLE_LOGIN = " create table LOGIN (" +
            " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "full_name TEXT NOT NULL , " +
            "password TEXT NOT NULL, " +
            "email TEXT NOT NULL);";

    private static final String CREATE_TABLE_MEDICATION = " create table MEDICATION ( " +
            "_uid INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "medication_name TEXT NOT NULL , " +
            "medication_dosage TEXT NOT NULL, " +
            "medication_date TEXT NOT NULL, " +
            "medication_time TEXT NOT NULL, " +
            "userID INTEGER, FOREIGN KEY(userID) REFERENCES LOGIN(_id));";


    private static final String DB_NAME = "Vozyk";
    public static final String FULL_NAME = "full_name";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String TABLE_NAME_LOGIN = "LOGIN";
    public static final String TABLE_NAME_MEDICATION = "MEDICATION";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL(CREATE_TABLE_LOGIN);
        MyDB.execSQL(CREATE_TABLE_MEDICATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldTable, int newTable) {
        MyDB.execSQL("DROP TABLE IF EXISTS LOGIN");
        MyDB.execSQL("DROP TABLE IF EXISTS MEDICATION");
    }


    public Boolean updateUserInformation(String ID, String fullName, String email, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("full_name", fullName);
        cv.put("email", email);
        cv.put("password", password);
        long result = MyDB.update(TABLE_NAME_LOGIN, cv, "_id=?", new String[]{ID});
        return result != -1;
    }


    public void insertData(String username, String password, String email){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(DatabaseHelper.FULL_NAME, username);
        contentValues.put(DatabaseHelper.PASSWORD, password);
        contentValues.put(DatabaseHelper.EMAIL, email);
        long result = MyDB.insert(DatabaseHelper.TABLE_NAME_LOGIN, null, contentValues);
    }

    // Medication Functions

    public void insertMedication(String medicationName, String medicationDosage, String medicationTime, String medicationDate, Integer userID){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        String newUserID = String.valueOf(userID);
        ContentValues contentValues= new ContentValues();
        contentValues.put("medication_name", medicationName);
        contentValues.put("medication_dosage", medicationDosage);
        contentValues.put("medication_date", medicationTime);
        contentValues.put("medication_time", medicationDate);
        contentValues.put("userID", newUserID);
        MyDB.insert("MEDICATION", null, contentValues);
    }

    public Cursor readAllData(Integer myIntValue){
        SQLiteDatabase db1 = this.getWritableDatabase();
        String userID = String.valueOf(myIntValue);
        Cursor phoneNumberCursor = db1.rawQuery("Select * from MEDICATION where userID = ?", new String[]{userID});
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = phoneNumberCursor;
        }
        return cursor;
    }

    public void updateMedication(String ID, String newMed, String newDosage, String newDate, String newTime){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("medication_name", newMed);
        cv.put("medication_dosage", newDosage);
        cv.put("medication_date", newDate);
        cv.put("medication_time", newTime);
        db.update(TABLE_NAME_MEDICATION, cv, "_uid=?", new String[]{ID});
    }

    public Boolean deleteMedication(String ID){
        SQLiteDatabase db = this.getWritableDatabase();
        long deleted = db.delete(TABLE_NAME_MEDICATION, "_uid=?", new String[]{ID});
        return deleted != -1;
    }
}