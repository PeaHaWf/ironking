package com.example.thealarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.thealarm.Adapter.gridviewadapter;
import com.example.thealarm.Adapter.listviewadapter;

import java.util.ArrayList;
import java.util.List;
public class Listviewactivity extends AppCompatActivity {
    private gridviewadapter gr_adapter;
private RecyclerView rc;
private Button btn_delete;
private List<String>datas=new ArrayList<>();
private listviewadapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listviewactivity);
        RelativeLayout relativeLayout=findViewById(R.id.Relative_list_back);
        AnimationDrawable background= (AnimationDrawable) relativeLayout.getBackground();
        background.setEnterFadeDuration(2500);
        background.setExitFadeDuration(5000);
        background.start();
        rc=findViewById(R.id.re1);
        btn_delete=findViewById(R.id.btn_delete);

        initdata();
        showlist(true,false);
        //删除现有的数据
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.cnt > 0) {
                    SharedPreferences sp = getSharedPreferences("time", MODE_PRIVATE);
                    SharedPreferences.Editor ed = sp.edit();
                    int cnt=MainActivity.cnt-1;
                    ed.remove(MainActivity.id[cnt]);
                    ed.commit();
                    delete_from_lists(cnt);
                    MainActivity.cnt--;
                }
            }
        });
    }
private void initdata()
{
    SharedPreferences sp = getSharedPreferences("time",MODE_PRIVATE);
    int cnt=MainActivity.cnt;
    for(int i=0;i<cnt;i++)
    {
        String s = sp.getString(MainActivity.id[i],"");
        datas.add(s);
    }

}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemid=item.getItemId();
        switch(itemid)
        {
            case R.id.list_ho:
                showlist(true,false);
                break;
            case R.id.list_ho_re:
                showlist(true,true);
                break;
            case R.id.list_ver:
                showlist(false,false);
                break;
            case R.id.list_ver_re:
                showlist(false,true);
                break;
            case R.id.grid_horizontal:
                showgrid(false,false);
                break;
            case R.id.grid_vertical:
                showgrid(true,false);
                break;
            case R.id.grid_vertical_re:
                showgrid(true,true);
                break;
            case R.id.grid_horizontal_re:
                showgrid(false,true);
                break;
            case R.id.stst:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showgrid(boolean vertical,boolean reverse) {
        GridLayoutManager gr=new GridLayoutManager(this,3);
        gr.setOrientation(vertical? LinearLayoutManager.VERTICAL:LinearLayoutManager.HORIZONTAL);
        gr.setReverseLayout(reverse);
        gr_adapter=new gridviewadapter(datas);
        rc.setAdapter(gr_adapter);
        rc.setLayoutManager(gr);
    }

    private void showlist(boolean vertical,boolean reverse) {
        //设置样式
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(vertical? LinearLayoutManager.VERTICAL:LinearLayoutManager.HORIZONTAL);
        linearLayoutManager.setReverseLayout(reverse);
        rc.setLayoutManager(linearLayoutManager);
        //创建适配器
        adapter=new listviewadapter(datas);
        //设置到RecyclerView里面去
        rc.setAdapter(adapter);
    }
    private void delete_from_lists(int cnt){
        datas.remove(cnt);
        adapter.notifyItemRemoved(cnt);
        adapter.notifyItemRangeChanged(cnt, adapter.getItemCount());
        if(gr_adapter!=null){
        gr_adapter.notifyItemRemoved(cnt);
        gr_adapter.notifyItemRangeChanged(cnt, gr_adapter.getItemCount());
    }
    }
}