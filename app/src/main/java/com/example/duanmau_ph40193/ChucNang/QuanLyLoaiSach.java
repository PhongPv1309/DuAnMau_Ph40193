package com.example.duanmau_ph40193.ChucNang;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmau_ph40193.Adapter.LoaiSachAdapter;
import com.example.duanmau_ph40193.Dao.LoaiSachDao;
import com.example.duanmau_ph40193.Model.LoaiSach;
import com.example.duanmau_ph40193.R;

import java.util.ArrayList;


public class QuanLyLoaiSach extends Fragment {
    RecyclerView recyclerLoaiSach;
    LoaiSachDao dao;



    public QuanLyLoaiSach() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_quan_ly_loai_sach, container, false);
        recyclerLoaiSach=view.findViewById(R.id.recyclerLoaiSach);
        EditText edtLoaiSach=view.findViewById(R.id.edtLoaiSach);
        EditText edtTieude=view.findViewById(R.id.edtTieude);
        Button btnThem=view.findViewById(R.id.btnThem);
        dao=new LoaiSachDao(getContext());
        loadData();


        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenloai=edtLoaiSach.getText().toString();
                String tieude=edtTieude.getText().toString();

                if (dao.themLoaiSach(tenloai,tieude)){
                    //thông báo+load lại danh sách
                    loadData();
                    edtLoaiSach.setText(" ");
                    edtTieude.setText(" ");

                }else {
                    Toast.makeText(getContext(), "Thêm loại sách không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
    public void loadData(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerLoaiSach.setLayoutManager(linearLayoutManager);
        ArrayList<LoaiSach>list=dao.getDSLoaiSach();
        LoaiSachAdapter adapter=new LoaiSachAdapter(getContext(),list);
        recyclerLoaiSach.setAdapter(adapter);
    }
}