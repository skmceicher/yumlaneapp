package com.saksham.yuminfo.login;

/**
 * Created by IT001 on 23-Jun-16.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class UserRepo {
    private DBHelper dbHelper;

    public UserRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(User student) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(User.KEY_status, student.status);
        values.put(User.KEY_phone,student.phone);
        values.put(User.KEY_name, student.name);

        // Inserting Row
        long user_Id = db.insert(User.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) user_Id;
    }

    public void delete(int user_Id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(User.TABLE, User.KEY_ID + "= ?", new String[] { String.valueOf(user_Id) });
        db.close(); // Closing database connection
    }

    public void update(User user) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(User.KEY_status, user.status);
        values.put(User.KEY_phone,user.phone);
        values.put(User.KEY_name, user.name);

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(User.TABLE, values, User.KEY_ID + "= ?", new String[] { String.valueOf(user.user_ID) });
        db.close(); // Closing database connection
    }

    public ArrayList<HashMap<String, String>> getStudentList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                User.KEY_ID + "," +
                User.KEY_name + "," +
                User.KEY_phone + "," +
                User.KEY_status +
                " FROM " + User.TABLE;

        //Student student = new Student();
        ArrayList<HashMap<String, String>> studentList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> student = new HashMap<String, String>();
                student.put("id", cursor.getString(cursor.getColumnIndex(User.KEY_ID)));
                student.put("name", cursor.getString(cursor.getColumnIndex(User.KEY_name)));
                studentList.add(student);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return studentList;

    }

    public User getStudentById(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                User.KEY_ID + "," +
                User.KEY_name + "," +
                User.KEY_phone + "," +
                User.KEY_status +
                " FROM " + User.TABLE
                + " WHERE " +
                User.KEY_ID + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        int iCount =0;
        User student = new User();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                student.user_ID =cursor.getInt(cursor.getColumnIndex(User.KEY_ID));
                student.name =cursor.getString(cursor.getColumnIndex(User.KEY_name));
                student.phone  =cursor.getString(cursor.getColumnIndex(User.KEY_phone));
                student.status =cursor.getInt(cursor.getColumnIndex(User.KEY_status));

            } while (cursor.moveToNext());
        }
        else
        {
            student.user_ID =0;
            student.name ="";
            student.phone  ="";
            student.status =0;
        }

        cursor.close();
        db.close();
        return student;
    }

}