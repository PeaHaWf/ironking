package com.example.thealarm;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private Button bt,bt1,bt2;
    private TextView time;
    static public String id[]=new String[]{"first","second","third","forth","fifth","sixth","seventh","eight","ninth","tenth","eleventh","12th","13th","14th","15th","16th","17th","18th","19th","20th"};
    static public int cnt=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Time tt=new Time(0,0,0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        RelativeLayout relativeLayout=findViewById(R.id.Relative_mainback);
        AnimationDrawable background= (AnimationDrawable) relativeLayout.getBackground();
        background.setEnterFadeDuration(2500);
        background.setExitFadeDuration(5000);
        background.start();
        bt = findViewById(R.id.btn_1);
        bt1=findViewById(R.id.btn_2);//记录数据
        bt2=findViewById(R.id.btn_3);
        time = findViewById(R.id.tx_1);
        Timer timer=new Timer();
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Listviewactivity.class);
                startActivity(intent);
            }
        });
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                tt.add_time();
                String e=tt.to_string();
                time.post(new Runnable() {
                    @Override
                    public void run() {
                        time.setText(e);
                    }
                });
            }
        },1000,1000);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tt.add_time();
                String e=tt.to_string();
                time.post(new Runnable() {
                    @Override
                    public void run() {
                        time.setText(e);
                    }
                });
            }
        });
        bt1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (cnt < 20) {
                    SharedPreferences sh = getSharedPreferences("time", MODE_PRIVATE);
                    SharedPreferences.Editor ed = sh.edit();
                    ed.putString(id[cnt++],tt.to_string());
                    ed.commit();
                }
            }
        });
    }
}
class Time{
    public int hh,mm,ss;
    Time(int hh,int mm,int ss){
        this.hh=hh;
        this.mm=mm;
        this.ss=ss;
    }
    void add_time(){
        ss++;
        if(ss>=60)
        {mm++;ss=0;}
        if(mm>=60)
        {hh++;mm=0;}
        if(hh>=24){
            hh=0;
        }

    }
    String to_string()
    {
        String h=String.format("%02d",hh);
        String s=String.format("%02d",ss);
        String m=String.format("%02d",mm);
        String e=h+':'+m+':'+s;
        return e;
    }
}