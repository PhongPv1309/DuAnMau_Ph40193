package com.example.duanmau_ph40193.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.duanmau_ph40193.Dao.ThuThuDao;
import com.example.duanmau_ph40193.R;
import com.example.duanmau_ph40193.Thanhmenu;
import com.google.android.material.textfield.TextInputEditText;

public class DangNhap extends AppCompatActivity {
    private TextInputEditText edtUser;
    private TextInputEditText edtPass;
    private CheckBox checkBox;
    private Button btnLogin;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        edtUser =findViewById(R.id.edtUser);
        edtPass=findViewById(R.id.edtPass);
        btnLogin=findViewById(R.id.btnLogin);

        ThuThuDao thuThuDao=new ThuThuDao(this);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=edtUser.getText().toString();
                String pass=edtPass.getText().toString();
                if (thuThuDao.checkDangNhap(user,pass)){
                    SharedPreferences sharedPreferences=getSharedPreferences("THONGTIN",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("matt",user);
                    editor.commit();

                    startActivity(new Intent(DangNhap.this,Thanhmenu.class));
                }else {
                    Toast.makeText(DangNhap.this, "Sai Thông tin ", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}
