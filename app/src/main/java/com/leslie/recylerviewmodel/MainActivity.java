package com.leslie.recylerviewmodel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.leslie.recylerviewmodel.test.alphabetlist.TestActivity6;
import com.leslie.recylerviewmodel.test.carousel.TestActivity11;
import com.leslie.recylerviewmodel.test.grid.TestActivity4;
import com.leslie.recylerviewmodel.test.headerfoooter.TestActivity2;
import com.leslie.recylerviewmodel.test.hover.TestActivity5;
import com.leslie.recylerviewmodel.test.list.TestActivity1;
import com.leslie.recylerviewmodel.test.mvp.TestActivity12;
import com.leslie.recylerviewmodel.test.otherVm.TestActivity8;
import com.leslie.recylerviewmodel.test.refresh.TestActivity7;
import com.leslie.recylerviewmodel.test.video.TestActivity10;
import com.leslie.recylerviewmodel.test.vmRequest.TestActivity9;
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
        findViewById(R.id.btn8).setOnClickListener(this);
        findViewById(R.id.btn9).setOnClickListener(this);
        findViewById(R.id.btn10).setOnClickListener(this);
        findViewById(R.id.btn11).setOnClickListener(this);
        findViewById(R.id.btn12).setOnClickListener(this);
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
        } else if (id == R.id.btn8){
            startActivity(new Intent(this, TestActivity8.class));
        } else if (id == R.id.btn9){
            startActivity(new Intent(this, TestActivity9.class));
        } else if (id == R.id.btn10){
            startActivity(new Intent(this, TestActivity10.class));
        } else if (id == R.id.btn11){
            startActivity(new Intent(this, TestActivity11.class));
        } else if (id == R.id.btn12){
            startActivity(new Intent(this, TestActivity12.class));
        }
    }
}
