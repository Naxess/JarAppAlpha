package com.com220.sli.jarappalpha;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MemoryManager extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME= "memoryInfo";
    private static final String TABLE_MEMORIES = "memories";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_MEMORY_ADDR = "memory_address";

    public MemoryManager(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_MEMORY_TABLE = "CREATE TABLE " + TABLE_MEMORIES +
                "("
                + KEY_ID + "INTEGER PRIMARY KEY," + KEY_NAME + "TEXT,"
                + KEY_MEMORY_ADDR + " TEXT" + ")";
        db.execSQL(CREATE_MEMORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMORIES);
        onCreate(db);
    }
}
