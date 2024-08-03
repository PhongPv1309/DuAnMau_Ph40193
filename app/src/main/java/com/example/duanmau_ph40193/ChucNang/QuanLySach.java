package com.example.duanmau_ph40193.ChucNang;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import com.example.duanmau_ph40193.Adapter.SachAdapter;
import com.example.duanmau_ph40193.Dao.LoaiSachDao;
import com.example.duanmau_ph40193.Dao.SachDao;
import com.example.duanmau_ph40193.Model.LoaiSach;
import com.example.duanmau_ph40193.Model.Sach;
import com.example.duanmau_ph40193.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;


public class QuanLySach extends Fragment {
    SachDao sachDao;
    RecyclerView recyclerSach;


    public QuanLySach() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_quan_ly_sach, container, false);
        recyclerSach= view.findViewById(R.id.recyclerSach);
        FloatingActionButton floatAdd=view.findViewById(R.id.floatadd);
         sachDao=new SachDao(getContext());
         loadData();

        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              showDiglog();
            }
        });

        return  view;
    }
    private void loadData(){
        ArrayList<Sach> list=sachDao.getDSDauSach();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerSach.setLayoutManager(linearLayoutManager);
        SachAdapter adapter=new SachAdapter(getContext(),list);
        recyclerSach.setAdapter(adapter);

    }
    private void showDiglog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        LayoutInflater inflater=getLayoutInflater();
        View view=inflater.inflate(R.layout.diglog_themsach,null);
        builder.setView(view);
        EditText edtTenSach=view.findViewById(R.id.edtTenSach);
        EditText edtTien=view.findViewById(R.id.edtTien);
        Spinner spnLoaiSach=view.findViewById(R.id.spnLoaisach);
        EditText edtmau=view.findViewById(R.id.edtMausac);
            edtmau.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            AlertDialog.Builder builder1=new AlertDialog.Builder(getActivity());
            builder1.setTitle("Chon gioi Tinh");
            String[]gt={"Xanh","Đỏ","Tím","Vàng"};
            builder1.setItems(gt, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    edtmau.setText(gt[i]);
                }
            });
            AlertDialog dialog=builder1.create();
            dialog.show();
        }
    });



        SimpleAdapter simpleAdapter=new SimpleAdapter(
                getContext(),
                getDSloaiSach(),
                android.R.layout.simple_list_item_1,
                new String[]{"tenloai"},
                new int[]{android.R.id.text1}
        );
        spnLoaiSach.setAdapter(simpleAdapter);

        builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String tensach = edtTenSach.getText().toString();
                String tienString = edtTien.getText().toString();

                // Kiểm tra xem có trường nào đang rỗng không
                if (tensach.isEmpty() || tienString.isEmpty()) {
                    Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                int tien = Integer.parseInt(tienString);
                HashMap<String, Object> hs = (HashMap<String, Object>) spnLoaiSach.getSelectedItem();
                int maloai = (int) hs.get("maloai");

                boolean check = sachDao.themSachMoi(tensach, tien, maloai);
                if (check) {
                    Toast.makeText(getContext(), "Thêm sách thành công", Toast.LENGTH_SHORT).show();
                    loadData();
                } else {
                    Toast.makeText(getContext(), "Thêm không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
    private ArrayList<HashMap<String,Object>> getDSloaiSach(){
        LoaiSachDao loaiSachDao=new LoaiSachDao(getContext());
        ArrayList<LoaiSach> list=loaiSachDao.getDSLoaiSach();
        ArrayList<HashMap<String,Object>> listHM=new ArrayList<>();
        for (LoaiSach loai:list){
            HashMap<String,Object> hs=new HashMap<>();
            hs.put("maloai",loai.getId());
            hs.put("tenloai",loai.getTenloai());
            listHM.add(hs);
        }
        return listHM;
    }
}