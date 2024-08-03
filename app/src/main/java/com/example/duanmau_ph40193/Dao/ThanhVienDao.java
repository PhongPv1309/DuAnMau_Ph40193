package com.example.duanmau_ph40193.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau_ph40193.Database.Dbheper;
import com.example.duanmau_ph40193.Model.ThanhVien;

import java.util.ArrayList;

public class ThanhVienDao {
    Dbheper dbheper;

    public  ThanhVienDao(Context context){
        dbheper=new Dbheper(context);
    }
    public ArrayList<ThanhVien> getDSThanhVien(){
        ArrayList<ThanhVien> list=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=dbheper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM THANHVIEN",null);
        if (cursor.getCount()!=0){
            cursor.moveToFirst();
            do {
                list.add(new ThanhVien(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3), cursor.getString(4)));
            }while (cursor.moveToNext());
        }
        return list;
    }
    public boolean themThanhVien(String hoten,String namsinh,String gioitinh,String luong){
        SQLiteDatabase sqLiteDatabase=dbheper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("hoten",hoten);
        contentValues.put("namsinh",namsinh);
        contentValues.put("gioitinh",gioitinh);
        contentValues.put("luong",luong);
        long check=sqLiteDatabase.insert("THANHVIEN",null,contentValues);
        if (check ==-1)
            return false;
        return true;
    }
    public int xoaThongTinTv(int matv){
        SQLiteDatabase sqLiteDatabase=dbheper.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT *FROM PHIEUMUON WHERE matv=?",new String[]{String.valueOf(matv)} );
        if (cursor.getCount()!=0){
            return -1;

        }
        long check=sqLiteDatabase.delete("THANHVIEN","matv=?",new String[]{String.valueOf(matv)});
        if (check==-1)
            return 0;
        return 1;
    }
}
