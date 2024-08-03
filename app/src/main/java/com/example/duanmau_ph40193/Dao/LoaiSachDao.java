package com.example.duanmau_ph40193.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau_ph40193.Database.Dbheper;
import com.example.duanmau_ph40193.Model.LoaiSach;

import java.util.ArrayList;

public class LoaiSachDao {
    Dbheper dbheper;
    public  LoaiSachDao(Context context){
        dbheper=new Dbheper(context);
    }
    //lấy danh sách loại sách
  public ArrayList<LoaiSach> getDSLoaiSach(){
        ArrayList<LoaiSach> list=new ArrayList<>();
      SQLiteDatabase sqLiteDatabase=dbheper.getReadableDatabase();
      Cursor cursor=sqLiteDatabase.rawQuery("SELECT*FROM LOAISACH",null);
      if (cursor.getCount() !=0) {
          cursor.moveToFirst();
          do {
              list.add(new LoaiSach(cursor.getInt(0),cursor.getString(1),cursor.getString(2)));
          }while (cursor.moveToNext());

      }
      return list;
  }
  //Thêm loại sách
    public Boolean themLoaiSach(String tenloai,String tieude){
        SQLiteDatabase sqLiteDatabase=dbheper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("tenloai",tenloai);
        contentValues.put("tieude",tieude);
        long check=sqLiteDatabase.insert("LOAISACH",null,contentValues);
        if (check==-1)
            return false;
        return true;
    }
    //xóa loại sách
    public int xoaLoaiSach(int id){
        SQLiteDatabase sqLiteDatabase=dbheper.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT*FROM SACH WHERE maloai=?",new String[]{String.valueOf(id)});
        if (cursor.getCount()!=0){
            return -1;
        }
        long check=sqLiteDatabase.delete("LOAISACH","maloai=?",new String[]{String.valueOf(id)} );
        if (check==-1)
            return 0;
            return 1;
    }
}
