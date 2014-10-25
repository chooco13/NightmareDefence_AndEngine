package com.night.manager;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.night.base.BaseWave;

public class DBManager
{
    // ---------------------------------------------
    // Constants
    // ---------------------------------------------
    
    private static final String ADDRESS_KEY_ID = "ID";
    
    private static final String ADDRESS_KEY_WAVE = "wave";
    
    private static final String ADDRESS_KEY_MOB1 = "mob1";
    
    private static final String ADDRESS_KEY_MOB2 = "mob2";
    
    private static final String ADDRESS_KEY_MOB3 = "mob3";
    
    private static final String ADDRESS_KEY_MOB4 = "mob4";
    
    private static final String ADDRESS_KEY_MOB5 = "mob5";
    
    private static final String ADDRESS_KEY_MOB6 = "mob6";
    
    private static final String ADDRESS_KEY_MOB7 = "mob7";
    
    private static final String ADDRESS_KEY_WAY = "way";
    
    private static final int ADDRESS_KEY_COLUMN_ID = 0;
    
    private static final int ADDRESS_KEY_COLUMN_WAVE = 1;
    
    private static final int ADDRESS_KEY_COLUMN_MOB1 = 2;
    
    private static final int ADDRESS_KEY_COLUMN_MOB2 = 3;
    
    private static final int ADDRESS_KEY_COLUMN_MOB3 = 4;
    
    private static final int ADDRESS_KEY_COLUMN_MOB4 = 5;
    
    private static final int ADDRESS_KEY_COLUMN_MOB5 = 6;
    
    private static final int ADDRESS_KEY_COLUMN_MOB6 = 7;
    
    private static final int ADDRESS_KEY_COLUMN_MOB7 = 8;
    
    private static final int ADDRESS_KEY_COLUMN_WAY = 9;
    
    private static String ADDRESS_DATABASE_TABLE;
    
    @SuppressWarnings("unused")
    private static String ADDRESS_DATABASE_CREATE;
    
    // ---------------------------------------------
    // VARIABLES
    // ---------------------------------------------
    
    private static final String DATABASE_NAME = "STAGE";
    
    private static final int DATABASE_VERSION = 1;
    
    private DBHelper dbHelper;
    
    private SQLiteDatabase db;
    
    private final Context context;
    
    // ---------------------------------------------
    // CONSTRUCTOR
    // ---------------------------------------------
    
    public DBManager(Context ctx, int stage_num)
    {
        switch (stage_num)
        {
            case 1:
                ADDRESS_DATABASE_TABLE = "STAGE1";
                break;
            case 2:
                ADDRESS_DATABASE_TABLE = "STAGE2";
                break;
            case 3:
                ADDRESS_DATABASE_TABLE = "STAGE3";
                break;
            case 4:
                ADDRESS_DATABASE_TABLE = "STAGE4";
                break;
            case 5:
                ADDRESS_DATABASE_TABLE = "STAGE5";
                break;
            case 6:
                ADDRESS_DATABASE_TABLE = "STAGE6";
                break;
            case 7:
                ADDRESS_DATABASE_TABLE = "STAGE7";
                break;
        }
        ADDRESS_DATABASE_CREATE = "create table " + ADDRESS_DATABASE_TABLE + " (" + ADDRESS_KEY_ID + " integer, "
                + ADDRESS_KEY_WAVE + " integer, " + ADDRESS_KEY_MOB1 + " integer, " + ADDRESS_KEY_MOB2 + " integer, "
                + ADDRESS_KEY_MOB3 + " integer, " + ADDRESS_KEY_MOB4 + " integer, " + ADDRESS_KEY_MOB5 + " integer, "
                + ADDRESS_KEY_MOB6 + " integer, " + ADDRESS_KEY_MOB7 + " integer, " + ADDRESS_KEY_WAY + " integer);";
        context = ctx;
        dbHelper = new DBHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    // ---------------------------------------------
    // DB METHODS
    // ---------------------------------------------
    
    public DBManager open() throws SQLException
    {
        db = dbHelper.getReadableDatabase();
        return this;
    }
    
    public void close()
    {
        db.close();
    }
    
    public ArrayList<BaseWave> selectAllWaveList()
    {
        ArrayList<BaseWave> returnValue = null;
        
        Cursor c = db.query(ADDRESS_DATABASE_TABLE, null, null, null, null, null, null);
        
        if ((c.getCount() == 0) || !c.moveToFirst())
        {
            
        }
        else if (c.moveToFirst())
        {
            returnValue = new ArrayList<BaseWave>();
            do
            {
                returnValue.add(new BaseWave(c.getInt(ADDRESS_KEY_COLUMN_ID), c.getInt(ADDRESS_KEY_COLUMN_WAVE), c
                        .getInt(ADDRESS_KEY_COLUMN_MOB1), c.getInt(ADDRESS_KEY_COLUMN_MOB2), c
                        .getInt(ADDRESS_KEY_COLUMN_MOB3), c.getInt(ADDRESS_KEY_COLUMN_MOB4), c
                        .getInt(ADDRESS_KEY_COLUMN_MOB5), c.getInt(ADDRESS_KEY_COLUMN_MOB6), c
                        .getInt(ADDRESS_KEY_COLUMN_MOB7), 1, c.getInt(ADDRESS_KEY_COLUMN_WAY)));
            } while (c.moveToNext());
        }
        c.close();
        
        return returnValue;
    }
    
    // ---------------------------------------------
    // DB HELPER
    // ---------------------------------------------
    
    private static class DBHelper extends SQLiteOpenHelper
    {
        public DBHelper(Context context, String dbName, CursorFactory factory, int version)
        {
            super(context, dbName, factory, version);
        }
        
        public void onCreate(SQLiteDatabase db)
        {
            
        }
        
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            db.execSQL("drop table if exists " + ADDRESS_DATABASE_TABLE);
            
        }
    }
    
}
