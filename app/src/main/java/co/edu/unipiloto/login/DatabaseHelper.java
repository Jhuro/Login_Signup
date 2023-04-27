package co.edu.unipiloto.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="Usuarios.db";
    public static final String TABLE_NAME = "Usuarios";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "FULLNAME";
    public static final String COL_3 = "USERNAME";
    public static final String COL_4 = "EMAIL";
    public static final String COL_5 = "PASSWORD";
    public static final String COL_6 = "LATITUD";
    public static final String COL_7 = "LONGITUD";
    public static final String COL_8 = "EDAD";
    public static final String COL_9 = "ROL";
    public static final String COL_10 = "GENDER";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "FULLNAME TEXT, USERNAME TEXT, EMAIL TEXT, PASSWORD TEXT, LATITUD REAL, LONGITUD REAL, EDAD INTEGER, ROL TEXT, GENDER INTEGER) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void initData(){
        SQLiteDatabase db = this.getWritableDatabase();
        onUpgrade(db, 1, 1);
    }

    public boolean insertData(Usuario user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, user.getFullName());
        contentValues.put(COL_3, user.getUsername());
        contentValues.put(COL_4, user.getEmail());
        contentValues.put(COL_5, user.getPassword());
        contentValues.put(COL_6, user.getLatitud());
        contentValues.put(COL_7, user.getLongitud());
        contentValues.put(COL_8, user.getEdad());
        contentValues.put(COL_9, user.getRol());
        contentValues.put(COL_10, user.getGender());

        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result==-1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from " + TABLE_NAME, null);
        return result;
    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where id="+id+"",null);
        return res;
    }
}
