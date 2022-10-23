package com.application.vozyk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    String  happyMood, midMood, sadMood;
    Integer happyMoodNum, midMoodNum, sadMoodNum;
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


    private static final String CREATE_TABLE_MOODS = " create table MOOD ( " +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "happyCount TEXT NOT NULL , " +
            "midCount TEXT NOT NULL , " +
            "sadCount TEXT NOT NULL, " +
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
        MyDB.execSQL(CREATE_TABLE_MOODS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldTable, int newTable) {
        MyDB.execSQL("DROP TABLE IF EXISTS LOGIN");
        MyDB.execSQL("DROP TABLE IF EXISTS MEDICATION");
        MyDB.execSQL("DROP TABLE IF EXISTS MOOD");
    }

    public Boolean insertMoods(Integer happyCount, Integer midCount , Integer sadCount, Integer userID){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        String newUserID = String.valueOf(userID);
        String newHappyCount = String.valueOf(happyCount);
        String newMidCount = String.valueOf(midCount);
        String newSadCount = String.valueOf(sadCount);
        contentValues.put("happyCount", newHappyCount);
        contentValues.put("midCount", newMidCount);
        contentValues.put("sadCount", newSadCount);
        contentValues.put("userID", newUserID);
        long result = MyDB.insert("MOOD", null, contentValues);
        return result != -1;
    }

    public Boolean checkIfMoods(Integer userID) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        String newUserID = String.valueOf(userID);
        Cursor cursor = MyDB.rawQuery("Select * from MOOD where userID = ?", new String[]{newUserID});
        if (cursor.getCount() > 0)
            return true;
        else
            cursor.close();
            return false;

    }

    public Boolean updateHappyCount(Integer happyCount, Integer userID){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        String newUserID = String.valueOf(userID);
        String newHappyCount = String.valueOf(happyCount);
        ContentValues cv = new ContentValues();
        cv.put("happyCount" ,newHappyCount);
        long result = MyDB.update("MOOD", cv, "userID=?", new String[]{newUserID});
        return result != -1;
    }

    public Boolean updateMidCount(Integer midCount, Integer userID){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        String newUserID = String.valueOf(userID);
        String newMidCount = String.valueOf(midCount);
        ContentValues cv = new ContentValues();
        cv.put("midCount" ,newMidCount);
        long result = MyDB.update("MOOD", cv, "userID=?", new String[]{newUserID});
        return result != -1;
    }

    public Boolean updateSadCount(Integer sadCount, Integer userID){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        String newUserID = String.valueOf(userID);
        String newSadCount = String.valueOf(sadCount);
        ContentValues cv = new ContentValues();
        cv.put("sadCount" ,newSadCount);
        long result = MyDB.update("MOOD", cv, "userID=?", new String[]{newUserID});
        return result != -1;
    }

    public String getMoods(Integer userID){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> specific = new ArrayList<>();
        String newUserID = String.valueOf(userID);
        String[] columns = {"_id", "happyCount", "midCount", "sadCount", "userID"};
        Cursor cursor
                =db.query("MOOD",columns,null,null,null,null,null);
        StringBuffer currentMoods = new StringBuffer();
        while (cursor.moveToNext())
        {
            String moodID
                    =cursor.getString(cursor.getColumnIndexOrThrow("_id"));
            happyMood
                    =cursor.getString(cursor.getColumnIndexOrThrow("happyCount"));
            midMood
                    =cursor.getString(cursor.getColumnIndexOrThrow("midCount"));
            sadMood
                    =cursor.getString(cursor.getColumnIndexOrThrow("sadCount"));
            String userID2
                    =cursor.getString(cursor.getColumnIndexOrThrow("userID"));


            if(userID2.equals(newUserID)){
                currentMoods.append("Your mood ratings so far since using this app are: " +" \n" + "Happy: " + happyMood + " \n" + "Medium: " + midMood + " \n" + "Sad: " + sadMood);
            }
             happyMoodNum = Integer.parseInt(happyMood);
             midMoodNum = Integer.parseInt(midMood);
             sadMoodNum = Integer.parseInt(sadMood);
        }

        if(happyMoodNum > midMoodNum && happyMoodNum > sadMoodNum){
            currentMoods.append(" \n" + "You have quoted you've been happy more times than the rest! Keep it up!");
        }else if (midMoodNum > happyMoodNum && midMoodNum > sadMoodNum){
            currentMoods.append(" \n" + "You have quoted you've been neutral more times than the rest! You're almost there! Keep it up!");
        }else if(sadMoodNum > happyMoodNum && sadMoodNum > happyMoodNum){
            currentMoods.append(" \n" + "You have quoted you've been sad more times than the rest! Keep your head held high! You can get through this, with our help!");
        }

        cursor.close();
        return currentMoods.toString();
    }

    public Boolean checkDetails(String emailAd, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from LOGIN where email = ? and password = ?", new String[] {emailAd,password});
        if(cursor.getCount()>0)
            return true;
        else
            cursor.close();
            return false;
    }

    public Integer getLoggedInUserID(String emailAd, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();

        Cursor cursor = MyDB.rawQuery("Select _id from LOGIN where email = ? and password = ?", new String[] {emailAd,password});
        int id = 0;
        if(cursor.moveToFirst()){
            id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
        }
        cursor.close();

        return id;
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

    public ArrayList<String> getLoggedInUserEmailPass(Integer ID){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ArrayList<String> returnedItems = new ArrayList<>();
        String idAsString = String.valueOf(ID);
        Cursor fullNameCursor = MyDB.rawQuery("Select full_name from LOGIN where _id = ?", new String[] {idAsString});
        if(fullNameCursor.moveToFirst()){
            String full_name
                    = fullNameCursor.getString(fullNameCursor.getColumnIndexOrThrow("full_name"));
            returnedItems.add(full_name);

        }
        fullNameCursor.close();

        Cursor emailCursor = MyDB.rawQuery("Select email from LOGIN where _id = ?", new String[] {idAsString});
        if(emailCursor.moveToFirst()){
            String email
                    = emailCursor.getString(emailCursor.getColumnIndexOrThrow("email"));
            returnedItems.add(email);

        }
        emailCursor.close();


        Cursor passwordCursor = MyDB.rawQuery("Select password from LOGIN where _id = ?", new String[] {idAsString});
        if(passwordCursor.moveToFirst()){
            String password
                    = passwordCursor.getString(passwordCursor.getColumnIndexOrThrow("password"));
            returnedItems.add(password);

        }
        passwordCursor.close();

        return returnedItems;
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