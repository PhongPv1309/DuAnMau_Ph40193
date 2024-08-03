package com.example.duanmau_ph40193.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau_ph40193.Database.Dbheper;
import com.example.duanmau_ph40193.Model.Sach;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SachDao {
    Dbheper dbheper;
    public SachDao(Context context){
        dbheper=new Dbheper(context);
    }
    //Lấy sách trong thư viện
    public ArrayList<Sach> getDSDauSach(){
        ArrayList<Sach> list=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=dbheper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT sc.masach,sc.tensach,sc.giathue,sc.maloai,lo.tenloai FROM SACH sc,LOAISACH lo WHERE sc.maloai=lo.maloai",null);
        if (cursor.getCount() !=0){
            cursor.moveToFirst();
            do{
                list.add(new Sach(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3),cursor.getString(4)));

            }while (cursor.moveToNext());
        }
        return list;
    }
    public boolean themSachMoi(String tensach,int giatien,int maloai){
        SQLiteDatabase sqLiteDatabase=dbheper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("tensach",tensach);
        contentValues.put("giathue",giatien);
        contentValues.put("maloai",maloai);
        long check=sqLiteDatabase.insert("SACH",null,contentValues);
        if (check==-1)
            return false;
        return true;

    }




}
