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
import android.widget.Toast;

import com.example.duanmau_ph40193.Adapter.ThanhVienAdapter;
import com.example.duanmau_ph40193.Dao.ThanhVienDao;
import com.example.duanmau_ph40193.Model.ThanhVien;
import com.example.duanmau_ph40193.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class QuanLyThanhVien extends Fragment {
    ThanhVienDao  thanhVienDao;
    RecyclerView recyclerThanhVien;



    public QuanLyThanhVien() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view=inflater.inflate(R.layout.fragment_quan_ly_thanh_vien, container, false);
        recyclerThanhVien=view.findViewById(R.id.recyclerThanhVien);
        FloatingActionButton floatAdd=view.findViewById(R.id.floatAdd);

        thanhVienDao=new ThanhVienDao(getContext());
        loadDaTa();
        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdialog();

            }
        });

       return view;
    }
    private void loadDaTa(){
        ArrayList<ThanhVien> list=thanhVienDao.getDSThanhVien();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerThanhVien.setLayoutManager(linearLayoutManager);
        ThanhVienAdapter adapter=new ThanhVienAdapter(getContext(),list,thanhVienDao);
        recyclerThanhVien.setAdapter(adapter);
    }
    private void showdialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        LayoutInflater inflater=getLayoutInflater();
        View view=inflater.inflate(R.layout.dialog_them_thanhvien,null);
        builder.setView(view);

        EditText edtHoTen=view.findViewById(R.id.edtHoten);
        EditText edtNamSinh=view.findViewById(R.id.edtNamSinh);
        EditText edtGioiTinh=view.findViewById(R.id.edtGioiTinh);
        EditText edtluong=view.findViewById(R.id.edtLuong);

//    edtGioiTinh.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            AlertDialog.Builder builder1=new AlertDialog.Builder(getActivity());
//            builder1.setTitle("Chon gioi Tinh");
//            String[]gt={"Nam","Nu"};
//            builder1.setItems(gt, new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    edtGioiTinh.setText(gt[i]);
//                }
//            });
//            AlertDialog dialog=builder1.create();
//            dialog.show();
//
//        }
//    });
    edtluong.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    });

        builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String hoten = edtHoTen.getText().toString().trim();
                String namsinh = edtNamSinh.getText().toString().trim();
                String gioitinh = edtGioiTinh.getText().toString().trim();
                String luong = edtluong.getText().toString().trim();

                // Kiểm tra không được để trống các trường
//                if (hoten.isEmpty() || namsinh.isEmpty() || gioitinh.isEmpty() || luong.isEmpty()) {
//                    Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                // Chuyển đổi lương thành số nguyên
//                int luongInt = Integer.parseInt(luong);
//
//                // Kiểm tra lương không nhỏ hơn 10000
//                if (luongInt < 10000) {
//                    Toast.makeText(getContext(), "Lương phải lớn hơn hoặc bằng 10000", Toast.LENGTH_SHORT).show();
//                    return;
//                }

                // Tiếp tục thêm thành viên nếu các điều kiện đúng
                boolean check = thanhVienDao.themThanhVien(hoten, namsinh, gioitinh, luong);
                if (check) {
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    loadDaTa();
                } else {
                    Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();


    }
}