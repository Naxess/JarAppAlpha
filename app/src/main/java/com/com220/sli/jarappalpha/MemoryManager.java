package com.com220.sli.jarappalpha;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

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
    public void addMemory(Memory memory)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, memory.getMemoryName());
        values.put(KEY_MEMORY_ADDR, memory.getMemoryAddress());
        db.insert(TABLE_MEMORIES, null, values);
        db.close();
    }
    public Memory getMemory(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_MEMORIES, new String[] { KEY_ID,
                        KEY_NAME, KEY_MEMORY_ADDR }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if(cursor != null)
            cursor.moveToFirst();
        Memory contact = new Memory(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
        return contact;
    }
    public List<Memory> getAllMemories()
    {
        List<Memory> memoryList = new ArrayList<Memory>();
        String selectQuery = "SELECT * FROM " + TABLE_MEMORIES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst())
        {
            do
            {
                Memory memory = new Memory();
                memory.setId(Integer.parseInt(cursor.getString(0)));
                memory.setMemoryName(cursor.getString(1));
                memory.setMemoryAddress(cursor.getString(2));
                memoryList.add(memory);
            }while(cursor.moveToNext());
        }
        return memoryList;
    }
    public int getMemoryCount()
    {
        String countQuery = "SELECT * FROM " + TABLE_MEMORIES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }
    public int updateMemory(Memory memory)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, memory.getMemoryName());
        values.put(KEY_MEMORY_ADDR, memory.getMemoryAddress());

        return db.update(TABLE_MEMORIES, values, KEY_ID + " = ?",
                new String[]{String.valueOf(memory.getId())});
    }
    public void deleteMemory(Memory memory)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MEMORIES, KEY_ID + " = ?",
                new String[] { String.valueOf(memory.getId()) });
        db.close();
    }
}
