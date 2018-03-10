package com.example.bpeeten.auto_dashboard_2.dbHelpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by bpeeten on 11/02/18.
 */

public class AutoDashboardDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "autodashboard_test.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_USERS   = "users";
    public static final String COLUMN_ID     = "userId";
    public static final String COLUMN_NAME   = "name";
    public static final String COLUMN_MAIL   = "email";
    public static final String COLUMN_PASSWD = "passwd";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_USERS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_MAIL + " TEXT, " +
                    COLUMN_PASSWD + " TEXT " + ")";

    public AutoDashboardDBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(sqLiteDatabase);

        //sqLiteDatabase.execSQL(TABLE_CREATE);
    }
}

