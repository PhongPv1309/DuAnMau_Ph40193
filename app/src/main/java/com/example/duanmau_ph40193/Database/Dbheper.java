package com.example.duanmau_ph40193.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Dbheper extends SQLiteOpenHelper {
    public Dbheper(Context context) {
        super(context,"DANGKIMONHOC",null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String dbTHuTHu="CREATE TABLE THUTHU (\n" +
                "  matt TEXT PRIMARY KEY,\n" +
                "  hoten TEXT,\n" +
                "  matkhau TEXT\n" +
                ")";
        db.execSQL(dbTHuTHu);

        String dbThanhVien="CREATE TABLE THANHVIEN(matv integer primary key autoincrement,hoten text,namsinh text,gioitinh text,luong text)";
        db.execSQL(dbThanhVien);

        String dbLoai ="CREATE TABLE lOAISACH(maloai integer primary key autoincrement,tenloai text,tieude text)";
        db.execSQL(dbLoai);

        //db.execSQL("INSERT INTO LOAISACH VALUES(1,'Thiếu nhi'),(2,'Tình cảm'),(3,'Hành động')");

        String dbSach ="CREATE TABLE SACH(masach integer primary key autoincrement,tensach text,giathue integer,maloai integer, mausac text references LOAISACH(maloai))";
        db.execSQL(dbSach);


        String dbPhieuMuon = "CREATE TABLE PHIEUMUON(mapm integer primary key autoincrement,matv integer references THANHVIEN(matv),matt text references THUTHU(matt),masach integer references SACH(masach),ngay text,trasach integer,tienthue integer)";
        db.execSQL(dbPhieuMuon);

        //data mẫu

        db.execSQL("INSERT INTO LOAISACH VALUES (1, 'Thiếu nhi','tieude1'),(2,'Tình cảm','tieude2'),(3, 'Giáo khoa','tieude3')");
        db.execSQL("INSERT INTO SACH VALUES (1, 'Hãy đợi đấy', 250, 1,'Đỏ'), (2, 'Thằng cuội', 100, 1,'Đỏ'), (3, 'Lập trình Android', 2000, 3,'Đỏ')");
        db.execSQL("INSERT INTO THUTHU VALUES ('thuthu01','Phạm Văn Phong','123'),('thuthu02','Trần Văn Hùng','123abc')");
        db.execSQL("INSERT INTO THANHVIEN VALUES (1,'Cao Thu Trang','2000','Nam','347'),(2,'Trần Mỹ Kim','2000','Nữ','890')");
        //trả sách: 1: đã trả - 0: chưa trả
        db.execSQL("INSERT INTO PHIEUMUON VALUES (1,1,'thuthu01', 1, '19/03/2023', 1, 250),(2,1,'thuthu01', 3, '19/03/2023', 0, 200),(3,2,'thuthu02', 1, '19/03/2023', 1, 200)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if (i!=i1){
            db.execSQL("DROP TABLE IF EXISTS THUTHU");
            db.execSQL("DROP TABLE IF EXISTS THANHVIEN");
            db.execSQL("DROP TABLE IF EXISTS LOAISACH");
            db.execSQL("DROP TABLE IF EXISTS SACH");
            db.execSQL("DROP TABLE IF EXISTS PHIEUMUON");
            onCreate(db);

        }

    }
}
