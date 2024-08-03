package com.example.duanmau_ph40193;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmau_ph40193.ChucNang.DangXuat;
import com.example.duanmau_ph40193.ChucNang.DoanhThu;
import com.example.duanmau_ph40193.ChucNang.DoiMatKhau;
import com.example.duanmau_ph40193.ChucNang.QuanLyLoaiSach;
import com.example.duanmau_ph40193.ChucNang.QuanLyPhieuMuon;
import com.example.duanmau_ph40193.ChucNang.QuanLySach;
import com.example.duanmau_ph40193.ChucNang.QuanLyThanhVien;
import com.example.duanmau_ph40193.ChucNang.Top10;
import com.example.duanmau_ph40193.Dao.ThuThuDao;
import com.example.duanmau_ph40193.Login.DangNhap;
import com.google.android.material.navigation.NavigationView;

public class Thanhmenu extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView nav;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanhmenu);

        drawerLayout=findViewById(R.id.drawerLayout);
        nav=findViewById(R.id.nav);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.phieumuon){
                    QuanLyPhieuMuon quanLyPhieuMuon=new QuanLyPhieuMuon();
                    replaceFrg(quanLyPhieuMuon);
                    toolbar.setTitle(item.getTitle());
                }
                if (item.getItemId()==R.id.loaisach){
                    QuanLyLoaiSach quanLyLoaiSach=new QuanLyLoaiSach();
                    replaceFrg(quanLyLoaiSach);
                    toolbar.setTitle(item.getTitle());
                }
                if (item.getItemId()==R.id.sach){
                    QuanLySach quanLySach=new QuanLySach();
                    replaceFrg(quanLySach);
                    toolbar.setTitle(item.getTitle());
                }
                if (item.getItemId()==R.id.thanhvien){
                    QuanLyThanhVien quanLyThanhVien=new QuanLyThanhVien();
                    replaceFrg(quanLyThanhVien);
                    toolbar.setTitle(item.getTitle());
                }
                if (item.getItemId()==R.id.top10){
                    Top10 top10=new Top10();
                    replaceFrg(top10);
                    toolbar.setTitle(item.getTitle());
                }
                if (item.getItemId()==R.id.doanhthu){
                    DoanhThu doanhThu =new DoanhThu();
                    replaceFrg(doanhThu);
                    toolbar.setTitle(item.getTitle());
                }
                if (item.getItemId()==R.id.doimatkhau){
                    DoiMatKhau doiMatKhau=new DoiMatKhau();
                    replaceFrg(doiMatKhau);
                    toolbar.setTitle(item.getTitle());
                    showDialodDoiMatKhau();
                }
                if (item.getItemId()==R.id.dangxuat){
                    Intent intent = new Intent(Thanhmenu.this, DangNhap.class);
                    startActivity(intent);
                    finish();
                }


                return true;
            }
        });
    }
    public void replaceFrg(Fragment frg){
        FragmentManager fm=getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.frmnav,frg).commit();
        drawerLayout.closeDrawer(GravityCompat.START);

    }
    public void showDialodDoiMatKhau(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        LayoutInflater inflater=getLayoutInflater();
        View view=inflater.inflate(R.layout.dialog_doimatkhau,null);
        EditText edtOldPass=view.findViewById(R.id.edtPassOld);
        EditText edtNewPass=view.findViewById(R.id.edtNewPass);
        EditText edtReNewPass=view.findViewById(R.id.edtReNewPass);

        builder.setView(view);

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builder.setPositiveButton("Cập Nhật", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String oldPass=edtOldPass.getText().toString();
                String newPass=edtNewPass.getText().toString();
                String reNewPass=edtReNewPass.getText().toString();
                if (newPass.equals(reNewPass)){
                    SharedPreferences sharedPreferences=getSharedPreferences("THONGTIN",MODE_PRIVATE);
                    String matt=sharedPreferences.getString("matt","");
                    //cập nhật
                    ThuThuDao thuThuDao=new ThuThuDao(Thanhmenu.this);
                    boolean check=thuThuDao.capNhatMatKhau(matt,oldPass,newPass);
                    if (check){
                        Toast.makeText(Thanhmenu.this, "Cập nhật Mật khảu thành công", Toast.LENGTH_SHORT).show();
                         Intent intent=new Intent(Thanhmenu.this,DangNhap.class);
                         intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                         startActivity(intent);
                    }else{
                        Toast.makeText(Thanhmenu.this, "Cập nhatah mật khẩu thất bại ", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(Thanhmenu.this, "Mật khẩu không trùng khớp với nhau", Toast.LENGTH_SHORT).show();
                }
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();



    }

}