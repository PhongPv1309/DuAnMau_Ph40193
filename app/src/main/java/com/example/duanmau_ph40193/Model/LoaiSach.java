package com.example.duanmau_ph40193.Model;

public class LoaiSach {
    private int id;
    private String tenloai;
    private String tieude;

    public LoaiSach(int id, String tenloai, String tieude) {
        this.id = id;
        this.tenloai = tenloai;
        this.tieude = tieude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }
}
