package com.example.duanmau_ph40193.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_ph40193.Dao.LoaiSachDao;
import com.example.duanmau_ph40193.Model.LoaiSach;
import com.example.duanmau_ph40193.R;

import java.util.ArrayList;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.ViewHolder> {
    private  Context context;
    private ArrayList<LoaiSach> list;

    public LoaiSachAdapter(Context context, ArrayList<LoaiSach> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=((Activity)context).getLayoutInflater();
        View view=inflater.inflate(R.layout.item_recycler_loaisach,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTenLoai.setText("Tên loại:"+list.get(position).getTenloai());
        holder.txtMaLoai.setText("Mã Loại:"+String.valueOf(list.get(position).getId()));
        holder.txtTieude.setText("Tieu Đề:"+String.valueOf(list.get(position).getTieude()));

        holder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoaiSachDao loaiSachDao=new LoaiSachDao(context);
                int check=loaiSachDao.xoaLoaiSach(list.get(holder.getAdapterPosition()).getId());
                switch (check){
                    case 1:
                        //load data
                        list.clear();
                        list=loaiSachDao.getDSLoaiSach();
                        notifyDataSetChanged();
                        break;
                    case -1:
                        Toast.makeText(context, "Không thể xóa vì đã có sách thuộc thể loại này ", Toast.LENGTH_SHORT).show();
                        break;
                    case 0:
                        Toast.makeText(context, "Xóa loại sách không thành công", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtMaLoai,txtTenLoai,txtTieude;
        ImageView ivDel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaLoai=itemView.findViewById(R.id.txtMaLoai);
            txtTenLoai=itemView.findViewById(R.id.txtTenLoai);
            txtTieude=itemView.findViewById(R.id.txtTieuDe);

            ivDel=itemView.findViewById(R.id.ivDel);
        }
    }
}
