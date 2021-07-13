package com.leslie.recylerviewmodel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.leslie.recylerviewmodel.test.alphabetlist.TestActivity6;
import com.leslie.recylerviewmodel.test.grid.TestActivity4;
import com.leslie.recylerviewmodel.test.headerfoooter.TestActivity2;
import com.leslie.recylerviewmodel.test.hover.TestActivity5;
import com.leslie.recylerviewmodel.test.list.TestActivity1;
import com.leslie.recylerviewmodel.test.refresh.TestActivity7;
import com.leslie.recylerviewmodel.test.waterfall.TestActivity3;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        findViewById(R.id.btn7).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn1){
           startActivity(new Intent(this, TestActivity1.class));
        } else if (id == R.id.btn2){
            startActivity(new Intent(this, TestActivity2.class));
        } else if (id == R.id.btn3){
            startActivity(new Intent(this, TestActivity3.class));
        } else if (id == R.id.btn4){
            startActivity(new Intent(this, TestActivity4.class));
        } else if (id == R.id.btn5){
            startActivity(new Intent(this, TestActivity5.class));
        } else if (id == R.id.btn6){
            startActivity(new Intent(this, TestActivity6.class));
        } else if (id == R.id.btn7){
            startActivity(new Intent(this, TestActivity7.class));
        }
    }
}
