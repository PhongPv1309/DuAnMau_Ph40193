package com.example.duanmau_ph40193.ChucNang;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.duanmau_ph40193.Dao.ThongKeDao;
import com.example.duanmau_ph40193.R;

import java.util.Calendar;


public class DoanhThu extends Fragment {



    public DoanhThu() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view=inflater.inflate(R.layout.fragment_doanh_thu, container, false);
        EditText edtStart =view.findViewById(R.id.edtStart);
        EditText edtEnd=view.findViewById(R.id.edtEnd);
        Button btnThongKe=view.findViewById(R.id.btnThongKe);
        TextView txtKetQua=view.findViewById(R.id.txtKetQua);

        Calendar calendar=Calendar.getInstance();

        edtStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                            edtStart.setText(i2+"/"+(i1 +1)+"/"+i);
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();

            }

        });
        edtEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                edtEnd.setText(i2+"/"+(i1 +1)+"/"+i);
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();

            }
        });


        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThongKeDao thongKeDao=new ThongKeDao(getContext());
                String ngaybatdau=edtStart.getText().toString();
                String ngaykethuc=edtEnd.getText().toString();
                int doanhthu=thongKeDao.getDoanhThu(ngaybatdau,ngaykethuc);
                txtKetQua.setText(doanhthu+"VND");
            }
        });
      return view;
    }
}