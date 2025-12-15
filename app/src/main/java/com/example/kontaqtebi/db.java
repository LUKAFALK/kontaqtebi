package com.example.kontaqtebi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class db  extends SQLiteOpenHelper {

    private static final String DB_NAME = "contacts.db";
    private static final int DB_VERSION = 1;

    public db(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE contacts (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "phone TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    public void addContact(String name, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("phone", phone);
        db.insert("contacts", null, cv);
    }

    public ArrayList<contact> getContacts() {
        ArrayList<contact> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM contacts", null);

        if (c.moveToFirst()) {
            do {
                list.add(new contact(
                        c.getInt(0),
                        c.getString(1),
                        c.getString(2)
                ));
            } while (c.moveToNext());
        }
        c.close();
        return list;
    }
}
