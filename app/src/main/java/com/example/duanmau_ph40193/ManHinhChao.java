package com.example.duanmau_ph40193;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;

import com.example.duanmau_ph40193.ChucNang.HinhChao2;
import com.example.duanmau_ph40193.Login.DangNhap;


public class ManHinhChao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chao);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent sever2=new Intent(ManHinhChao.this, HinhChao2.class);

                startActivity(sever2);
                finish();//
            }
        },3000);/// set giây



    }
}