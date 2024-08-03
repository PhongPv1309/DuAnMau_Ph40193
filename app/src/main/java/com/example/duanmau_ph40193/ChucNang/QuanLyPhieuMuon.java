package com.example.duanmau_ph40193.ChucNang;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.duanmau_ph40193.Adapter.PhieuMuonAdapter;
import com.example.duanmau_ph40193.Dao.PhieuMuonDao;
import com.example.duanmau_ph40193.Dao.SachDao;
import com.example.duanmau_ph40193.Dao.ThanhVienDao;
import com.example.duanmau_ph40193.Model.PhieuMuon;
import com.example.duanmau_ph40193.Model.Sach;
import com.example.duanmau_ph40193.Model.ThanhVien;
import com.example.duanmau_ph40193.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;


public class QuanLyPhieuMuon extends Fragment {
    PhieuMuonDao phieuMuonDao;
    RecyclerView recyclerQLPhieuMuon;
    ArrayList<PhieuMuon> list;
    public QuanLyPhieuMuon() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.fragment_quan_ly_phieu_muon, container, false);

      recyclerQLPhieuMuon=view.findViewById(R.id.recyclerQLPhieuMuon);
        FloatingActionButton floatAdd=view.findViewById(R.id.floatAdd);


        loadData();

        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

         return  view;
    }
    private void loadData(){
        phieuMuonDao=new PhieuMuonDao(getContext());
        list=phieuMuonDao.getDSPhieuMuon();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerQLPhieuMuon.setLayoutManager(linearLayoutManager);
        PhieuMuonAdapter adapter=new PhieuMuonAdapter(list,getContext());
        recyclerQLPhieuMuon.setAdapter(adapter);

    }
    private void showDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        LayoutInflater inflater=getLayoutInflater();
        View view=inflater.inflate(R.layout.dialog_them_phieumuon,null);
        Spinner spnThanhVien=view.findViewById(R.id.spnThanhVien);
        Spinner spnSach=view.findViewById(R.id.spnSach);
        EditText edtTien=view.findViewById(R.id.edtTien);
        getDataThanhVien(spnThanhVien);
        getDataSach(spnSach);
        builder.setView(view);

        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //lấy mã thành viên
                HashMap<String,Object> hsTV= (HashMap<String, Object>) spnThanhVien.getSelectedItem();
                int matv = (int) hsTV.get("matv");
                //lấy mã sách
                HashMap<String,Object> hsSach=(HashMap<String, Object>)spnSach.getSelectedItem();
                int masach=(int) hsSach.get("masach");

                int tien= Integer.parseInt(edtTien.getText().toString());


                themPhieuMuon(matv,masach,tien);

            }
        });

        builder.setNegativeButton("hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog alertDialog=builder.create();
        alertDialog.show();

    }
    private void getDataThanhVien(Spinner spnThanhVien){
        ThanhVienDao thanhVienDao=new ThanhVienDao(getContext());
        ArrayList<ThanhVien> list=thanhVienDao.getDSThanhVien();

        ArrayList<HashMap<String,Object>> listHM=new ArrayList<>();
        for (ThanhVien tv:list){
            HashMap<String,Object>hs=new HashMap<>();
            hs.put("matv",tv.getMatv());
            hs.put("hoten",tv.getHoten());
            listHM.add(hs);
        }
        SimpleAdapter simpleAdapter=new SimpleAdapter(
                getContext(),
                listHM,
                android.R.layout.simple_list_item_1,
                new String[]{"hoten"},
                new int[]{android.R.id.text1});
        spnThanhVien.setAdapter(simpleAdapter);
    }


    private void getDataSach(Spinner spnSach){
        SachDao sachDao=new SachDao(getContext());
        ArrayList<Sach> list=sachDao.getDSDauSach();

        ArrayList<HashMap<String,Object>> listHM=new ArrayList<>();
        for (Sach sc:list){
            HashMap<String,Object>hs=new HashMap<>();
            hs.put("masach",sc.getMasach());
            hs.put("tensach",sc.getTensach());
            listHM.add(hs);
        }
        SimpleAdapter simpleAdapter=new SimpleAdapter(
                getContext(),
                listHM,
                android.R.layout.simple_list_item_1,
                new String[]{"tensach"},
                new int[]{android.R.id.text1});
        spnSach.setAdapter(simpleAdapter);
    }

    private void themPhieuMuon(int matv,int masach,int tien){
       //lấy mã thủ thư
        SharedPreferences sharedPreferences= getContext().getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);
        String matt=sharedPreferences.getString("matt","");
        //lấy ngày hiện  tại
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String ngay=simpleDateFormat.format(currentTime);

        PhieuMuon phieuMuon=new PhieuMuon(matv,matt,masach,ngay,0,tien);
        boolean kiemtra=phieuMuonDao.themPhieuMuon(phieuMuon);
        if (kiemtra){
            Toast.makeText(getContext(), "Thêm Phiêu Mượn Thành Công", Toast.LENGTH_SHORT).show();
            loadData();
        }else {
            Toast.makeText(getContext(), "Thêm phiếu mượn thất bại", Toast.LENGTH_SHORT).show();
        }


    }
}