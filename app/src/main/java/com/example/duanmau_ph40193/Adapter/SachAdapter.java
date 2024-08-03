package com.example.duanmau_ph40193.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_ph40193.Model.Sach;
import com.example.duanmau_ph40193.R;

import java.util.ArrayList;

public class SachAdapter extends  RecyclerView.Adapter<SachAdapter.ViewHolder>{
    private Context context;
    private ArrayList<Sach>list;

    public SachAdapter(Context context, ArrayList<Sach> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=((Activity)context).getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.item_recycler_sach,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtMaSach.setText("Mã Sach:"+list.get(position).getMasach());
        holder.txtTenSach.setText("Tên Sách:"+list.get(position).getTensach());
        holder.txtMaLoai.setText("Mã loại:"+list.get(position).getMaloai());
        holder.txtTenLoai.setText("Tên Loại:"+list.get(position).getTenloai());
        holder.txtGiaThue.setText("số lượng:"+list.get(position).getGiathue());
        holder.txtmausac.setText("màu sac:"+list.get(position).getMausac());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtMaSach,txtTenSach,txtGiaThue,txtMaLoai,txtTenLoai,txtmausac;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaSach=itemView.findViewById(R.id.txtMaSach);
            txtTenSach=itemView.findViewById(R.id.txtTenSach);
            txtGiaThue=itemView.findViewById(R.id.txtGiaThue);
            txtMaLoai=itemView.findViewById(R.id.txtMaLoai);
            txtTenLoai=itemView.findViewById(R.id.txtTenLoai);
            txtmausac=itemView.findViewById(R.id.txtMausac);


        }
    }
}
