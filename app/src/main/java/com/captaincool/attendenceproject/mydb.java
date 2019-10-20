package com.captaincool.attendenceproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import static android.content.Context.MODE_PRIVATE;

public class mydb {
    SQLiteDatabase db;
    Context context;
    mydb(Context c)
    {
        c = context;
    }
    void createdb()
    {
        try {
            db = context.openOrCreateDatabase("Atten", MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS teacher (id int PRIMARY KEY,name VARCHAR(30),username VARCHAR(30),password VARCHAR(30))");
            db.execSQL("INSERT INTO teacher (id, name) VALUES (1,'chhaya bind')");
            db.execSQL("INSERT INTO teacher (id, name) VALUES (2,'fatema rizivi')");
            db.execSQL("INSERT INTO teacher (id, name) VALUES (3,'mrunalini pawar')");
            db.execSQL("INSERT INTO teacher (id, name) VALUES (4,'prajakta kshirsagar')");
            db.execSQL("INSERT INTO teacher (id, name) VALUES (5,'prashant ozare')");
            db.execSQL("INSERT INTO teacher (id, name) VALUES (6,'sanjana shelar')");
            db.execSQL("INSERT INTO teacher (id, name) VALUES (7,'subhadra ambat')");
            db.execSQL("INSERT INTO teacher (id, name) VALUES (8,'vinod javaliya')");
            db.execSQL("INSERT INTO teacher (id, name) VALUES (9,'vipul mahyavanshi')");
            db.execSQL("INSERT INTO teacher (id, name) VALUES (10,'yogita gangurde')");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    boolean is_exits()
    {

     return false;
    }
    void show()
    {
        try {
            Cursor c = db.rawQuery("SELECT * FROM teacher", null);
            int nameIndex = c.getColumnIndex("name");
            int idIndex = c.getColumnIndex("id");
            int usernameIndex = c.getColumnIndex("username");
            c.moveToFirst();
            while (c != null) {
                Log.i("myapp", "Name is " + c.getString(nameIndex));
                Log.i("myapp", "id is " + c.getString(idIndex));
                Log.i("myapp", "username is " + c.getString(usernameIndex));
                c.moveToNext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
