package com.example.duanmau_ph40193.ChucNang;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duanmau_ph40193.Adapter.Top10Adapter;
import com.example.duanmau_ph40193.Dao.ThongKeDao;
import com.example.duanmau_ph40193.Model.Sach;
import com.example.duanmau_ph40193.R;

import java.util.ArrayList;


public class Top10 extends Fragment {


    public Top10() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_top10, container, false);
        RecyclerView recyclertop10=view.findViewById(R.id.recylerTop10);
        ThongKeDao thongKeDao=new ThongKeDao(getContext());
        ArrayList<Sach> list=thongKeDao.getTop10();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclertop10.setLayoutManager(linearLayoutManager);
        Top10Adapter adapter=new Top10Adapter(getContext(),list);
        recyclertop10.setAdapter(adapter);
        return view;
    }
}