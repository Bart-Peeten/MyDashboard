package com.example.bpeeten.auto_dashboard_2.dbHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.bpeeten.auto_dashboard_2.models.User;

/**
 * Created by bpeeten on 11/02/18.
 */

public class UserOperations {

    public static final String LOGTAG = "EMP_MNGMNT_SYS";

    SQLiteOpenHelper dbhandler;
    SQLiteDatabase database;

    public UserOperations(Context context) {
        this.dbhandler = new AutoDashboardDBHelper(context);
    }

    public void open(){
        Log.i(LOGTAG,"Database Opened");
        database = dbhandler.getWritableDatabase();
    }

    public void close(){
        Log.i(LOGTAG, "Database Closed");
        dbhandler.close();
    }

    //Add new User by calling this method
    public long addUser(User user)
    {
        // getting db instance for writing the user
        open();
        ContentValues cv = new ContentValues();
        cv.put(AutoDashboardDBHelper.COLUMN_NAME, user.getName());
        cv.put(AutoDashboardDBHelper.COLUMN_MAIL, user.getEmail());
        cv.put(AutoDashboardDBHelper.COLUMN_PASSWD, user.getPasswd());
        //inserting row
        long id = database.insertWithOnConflict(AutoDashboardDBHelper.TABLE_USERS, null, cv, SQLiteDatabase.CONFLICT_REPLACE);
        //close the database to avoid any leak
        close();

        return id;
    }

    public User checkUser(User user) {
        String email = user.getEmail();
        String passwd = user.getEmail();
        User logedInUser = new User();
        String[] whereArgs = new String[]{email};

        Log.e("TAG", email);

        open();

        //Cursor cursor = database.rawQuery(queryString, whereArgs);
        Cursor cursor = database.query(AutoDashboardDBHelper.TABLE_USERS, new String[] { "*" },
                AutoDashboardDBHelper.COLUMN_MAIL + " = ?",
                whereArgs,
                null, null, null);

        if (cursor != null) {
            Log.e("TAG cursor", cursor.getCount()+"");
            while (cursor.moveToNext()) {
                logedInUser.setName(cursor.getString(cursor.getColumnIndex(AutoDashboardDBHelper.COLUMN_NAME)));
                logedInUser.setEmail(cursor.getString(cursor.getColumnIndex(AutoDashboardDBHelper.COLUMN_MAIL)));
                logedInUser.setPasswd(cursor.getString(cursor.getColumnIndex(AutoDashboardDBHelper.COLUMN_PASSWD)));
                // Print to console for
                Log.e("TAG", logedInUser.getName() + " " +
                        logedInUser.getEmail() + " " +
                        logedInUser.getPasswd());
            }
        }

        cursor.close();

        close();

        if (passwd.equals(logedInUser.getPasswd())){
            return logedInUser;
        } else {
            return null;
        }
    }

}

