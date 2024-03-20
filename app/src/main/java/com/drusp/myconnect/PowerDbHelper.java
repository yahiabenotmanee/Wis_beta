package com.drusp.myconnect;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PowerDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "wis";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "power";
    private static final String COLUMN_POWER = "power";

    public PowerDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_POWER + " INTEGER)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void savePowerStatus(int powerStatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_POWER, powerStatus);
        db.insertWithOnConflict(TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    @SuppressLint("Range")
    public int getPowerStatus() {
        SQLiteDatabase db = this.getReadableDatabase();
        int powerStatus = 0;
        String query = "SELECT " + COLUMN_POWER + " FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            powerStatus = cursor.getInt(cursor.getColumnIndex(COLUMN_POWER));
        }
        cursor.close();
        db.close();
        return powerStatus;
    }
}
