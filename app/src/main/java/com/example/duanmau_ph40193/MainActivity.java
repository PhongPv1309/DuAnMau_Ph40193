package com.example.duanmau_ph40193;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.duanmau_ph40193.Dao.SachDao;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SachDao dao=new SachDao(this);
        dao.getDSDauSach();

    }
}