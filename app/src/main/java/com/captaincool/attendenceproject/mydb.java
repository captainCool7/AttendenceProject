package com.captaincool.attendenceproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class mydb {
    private static final String TAG = "myapp";
    SQLiteDatabase db;
    Context context;
    ArrayList<String> arr;
    int pIndex,aIndex;
    mydb(Context c)
    {
        context = c;
    }
    void createdb()
    {
        try {
            pIndex = 0;
            aIndex = 0;
            db = context.openOrCreateDatabase("Atten1", MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS teacher (id int PRIMARY KEY,name VARCHAR(30),username VARCHAR(30),password VARCHAR(30),present int,absent int);");
            db.execSQL("INSERT INTO teacher (id, name,present,absent) VALUES (1,'chhaya bind',1,11)");
            db.execSQL("INSERT INTO teacher (id, name,present,absent) VALUES (2,'fatema rizivi',9,12)");
            db.execSQL("INSERT INTO teacher (id, name,present,absent) VALUES (3,'mrunalini pawar',16,18)");
            db.execSQL("INSERT INTO teacher (id, name,present,absent) VALUES (4,'prajakta kshirsagar',6,12)");
            db.execSQL("INSERT INTO teacher (id, name,present,absent) VALUES (5,'prashant ozare',6,21)");
            db.execSQL("INSERT INTO teacher (id, name,present,absent) VALUES (6,'sanjana shelar',14,22)");
            db.execSQL("INSERT INTO teacher (id, name,present,absent) VALUES (7,'subhadra ambat',6,13)");
            db.execSQL("INSERT INTO teacher (id, name,present,absent) VALUES (8,'vinod javaliya',6,24)");
            db.execSQL("INSERT INTO teacher (id, name,present,absent) VALUES (9,'vipul mahyavanshi',6,12)");
            db.execSQL("INSERT INTO teacher (id, name,present,absent) VALUES (10,'yogita gangurde',6,17)");
        }catch (Exception e)
        {
            Log.i("myapp", "E is: "+e);
            e.printStackTrace();
        }
    }
    ArrayList<ExampleItem> show()
    {
        ArrayList<ExampleItem> names = new ArrayList<>();
        ArrayList<String> dna = new ArrayList<>();
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
                names.add(new ExampleItem(c.getString(nameIndex)));
                dna.add(c.getString(nameIndex));
                c.moveToNext();
            }
        } catch (Exception e) {
            Log.i("myapp", "E1 is: "+e);
            e.printStackTrace();
        }
        Log.i("myapp", "Names are: "+names);
        arr = dna;
        Log.i("myapp", "Names are: "+dna);
        return names;
    }

    public void getPresentAbsent(String name)
    {
        try {
            String qur = "SELECT * FROM teacher where name = '"+ name +"'";
            Log.i(TAG, "getPresentAbsent: "+qur);
            Cursor c = db.rawQuery(qur, null);
            int presentIndex = c.getColumnIndex("present");
            int absentIndex = c.getColumnIndex("absent");
            c.moveToFirst();
            while (c != null) {
                Log.i("myapp", "Name is " + c.getString(presentIndex));
                Log.i("myapp", "id is " + c.getString(absentIndex));
                pIndex = presentIndex;
                Log.i("myapp", "p"+pIndex);
                aIndex = absentIndex;
                Log.i("myapp", "p"+aIndex);
                c.moveToNext();
            }
        } catch (Exception e) {
            Log.i("myapp", "E1 is: "+e);
            e.printStackTrace();
        }
    }

    public void markPresent(String s)
    {
        getPresentAbsent(s);
        ContentValues cv = new ContentValues();
        cv.put("present",pIndex+1);
        db.update("teacher",cv,"name=="+s,null);
        Log.i("myapp", "Successful");
    }
    public void markAbsent(String s)
    {
        getPresentAbsent(s);
        ContentValues cv = new ContentValues();
        cv.put("absent",aIndex+1);
        db.update("teacher",cv,"name=="+s,null);
        Log.i("myapp", "Successful");
    }

    public ArrayList<String> getName()
    {
        return arr;
    }
}
