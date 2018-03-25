package com.example.marco.myemotion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.IOException;

/**
 * Created by marco on 14/03/18.
 */

public class DBmanager {
    private DBHelper dbhelper;

    public DBmanager(Context ctx) {
        dbhelper = new DBHelper(ctx);
        try {
            dbhelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public SQLiteDatabase getDatabaseAccess() {
        return dbhelper.getReadableDatabase();
    }

    public DBHelper getDbhelper(){
        return dbhelper;
    };


}
