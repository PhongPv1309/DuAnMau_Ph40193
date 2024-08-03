package com.example.duanmau_ph40193.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau_ph40193.Database.Dbheper;
import com.example.duanmau_ph40193.Model.Sach;


import java.util.ArrayList;

public class ThongKeDao {
    Dbheper dbheper;
    public ThongKeDao(Context context){
        dbheper =new Dbheper(context);
    }
    public ArrayList<Sach> getTop10(){
        ArrayList<Sach> list=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=dbheper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT pm.masach, sc.tensach,COUNT(pm.masach) FROM PHIEUMUON pm,SACH sc WHERE pm.masach=sc.masach GROUP BY pm.masach,sc.tensach ORDER by COUNT (pm.masach) DESC LIMIT 10",null);
        if (cursor.getCount() !=0){
            cursor.moveToFirst();
            do {
                list.add(new Sach(cursor.getInt(0),cursor.getString(1),cursor.getInt(2)));
            }while (cursor.moveToNext());
        }

        return list;
    }
    public int getDoanhThu(String ngaybatdau,String ngayketthuc){
        ngaybatdau=ngayketthuc.replace("/","");
        ngayketthuc=ngayketthuc.replace("/","");
        SQLiteDatabase sqLiteDatabase=dbheper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT SUM(tienthue) FROM PHIEUMUON WHERE substr(ngay,7)||substr(ngay,4,2)||substr(ngay,1,2) between ? and ?", new String[]{ngaybatdau, ngayketthuc});
        if (cursor.getCount()!=0){
            cursor.moveToFirst();
            return cursor.getInt(0);
        }
        return 0;
    }

}
