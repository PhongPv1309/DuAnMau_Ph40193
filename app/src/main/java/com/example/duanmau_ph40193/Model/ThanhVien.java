package com.example.duanmau_ph40193.Model;

public class ThanhVien {
    private int matv;
    private String hoten;
    private String namsinh;
    private String gioitinh;

    private String luong;

    public ThanhVien(int matv, String hoten, String namsinh, String gioitinh, String luong) {
        this.matv = matv;
        this.hoten = hoten;
        this.namsinh = namsinh;
        this.gioitinh = gioitinh;
        this.luong = luong;
    }

    public int getMatv() {
        return matv;
    }

    public void setMatv(int matv) {
        this.matv = matv;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getNamsinh() {
        return namsinh;
    }

    public void setNamsinh(String namsinh) {
        this.namsinh = namsinh;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getLuong() {
        return luong;
    }

    public void setLuong(String luong) {
        this.luong = luong;
    }
}
