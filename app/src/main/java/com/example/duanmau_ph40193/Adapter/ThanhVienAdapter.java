package com.example.duanmau_ph40193.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_ph40193.ChucNang.QuanLyThanhVien;
import com.example.duanmau_ph40193.Dao.ThanhVienDao;
import com.example.duanmau_ph40193.Model.ThanhVien;
import com.example.duanmau_ph40193.R;

import java.util.ArrayList;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ThanhVien> list;
    private ThanhVienDao thanhVienDao;

    public ThanhVienAdapter(Context context, ArrayList<ThanhVien> list,ThanhVienDao thanhVienDao) {
        this.context = context;
        this.list = list;
        this.thanhVienDao=thanhVienDao;

    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=((Activity)context).getLayoutInflater();
        View view=inflater.inflate(R.layout.item_recycler_thanhvien,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtMaTv.setText("Ma TV:"+list.get(position).getMatv());
        holder.txtHoTen.setText("Ho Ten::"+list.get(position).getHoten());
        holder.txtNamSinh.setText("Nam Sinh:"+list.get(position).getNamsinh());
        holder.txtGioitinh.setText("Gioi Tinh:"+list.get(position).getGioitinh());
        holder.txtLuong.setText("số nhà:"+list.get(position).getLuong());
        holder.btnxoatv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int check=thanhVienDao.xoaThongTinTv(list.get(holder.getAdapterPosition()).getMatv());
                switch (check){
                    case 1:
                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        loadData();
                        break;
                    case 0:
                        Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        break;
                    case -1:
                        Toast.makeText(context, "Thành viên đang tồn tại không được xoa", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;

                }

            }
        });



    }
    private void loadData(){
        list.clear();
        list=thanhVienDao.getDSThanhVien();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtMaTv,txtHoTen,txtNamSinh,txtGioitinh,txtLuong;
        ImageButton btnxoatv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaTv=itemView.findViewById(R.id.txtMaTv);
            txtHoTen=itemView.findViewById(R.id.txtHoTen);
            txtNamSinh=itemView.findViewById(R.id.txtNamSinh);
            txtGioitinh=itemView.findViewById(R.id.txtGioiTinh);
            txtLuong=itemView.findViewById(R.id.txtLuong);
            btnxoatv=itemView.findViewById(R.id.btnXoaThanhVien);
        }
    }
}
