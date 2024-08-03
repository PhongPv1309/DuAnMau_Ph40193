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

public class Top10Adapter extends RecyclerView.Adapter<Top10Adapter.ViewHolder> {
    private Context context;
    private ArrayList<Sach> list;

    public Top10Adapter(Context context, ArrayList<Sach> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=((Activity)context).getLayoutInflater();
        View view=inflater.inflate(R.layout.item_recycler_top10,parent,false);
        return new ViewHolder(view); /// next
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtMasach.setText(String.valueOf("Mã Sách:"+list.get(position).getMasach()));
        holder.txtTenSach.setText(String.valueOf("Tên Sách:"+list.get(position).getTensach()));
        holder.txtSoLuongMuon.setText(String.valueOf("Số Lượng Mượn:"+list.get(position).getSoluongdamuon()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtMasach,txtTenSach,txtSoLuongMuon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMasach=itemView.findViewById(R.id.txtMaSach);
            txtTenSach=itemView.findViewById(R.id.txtTenSach);
            txtSoLuongMuon=itemView.findViewById(R.id.txtSoLuongMuon);

        }
    }
}
