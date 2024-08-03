package com.example.duanmau_ph40193.ChucNang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmau_ph40193.Login.DangNhap;
import com.example.duanmau_ph40193.ManHinhChao;
import com.example.duanmau_ph40193.R;

public class HinhChao2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hinh_chao2);




        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(HinhChao2.this, DangNhap.class);
                startActivity(intent);
            }
        },3000);
    }
}