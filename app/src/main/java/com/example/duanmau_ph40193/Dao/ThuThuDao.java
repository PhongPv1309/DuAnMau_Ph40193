package com.example.duanmau_ph40193.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau_ph40193.Database.Dbheper;

public class ThuThuDao {
    Dbheper dbheperl;
    public ThuThuDao (Context context){
        dbheperl=new Dbheper(context);
    }

    // Đăng nhập
    public boolean checkDangNhap(String matt,String matkhau){
        SQLiteDatabase sqLiteDatabase=dbheperl.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT*FROM THUTHU WHERE matt = ? AND matkhau = ?",new String[]{matt,matkhau});
        if (cursor.getCount()!=0){
            return true;
        }else {
            return false;
        }
    }
    public  boolean capNhatMatKhau(String username,String oldPass,String newPass){
        SQLiteDatabase sqLiteDatabase=dbheperl.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT*FROM THUTHU WHERE matt=? AND matkhau=?",new String[]{username,oldPass});
        if (cursor.getCount()>0){
            ContentValues contentValues=new ContentValues();
            contentValues.put("matkhau",newPass);
            long check= sqLiteDatabase.update("THUTHU",contentValues,"matt=?",new String[]{username});
            if (check==-1)
                return false;
                return true;
        }
        return false;
    }
}
