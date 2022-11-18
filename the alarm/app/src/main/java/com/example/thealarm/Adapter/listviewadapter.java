package com.example.thealarm.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thealarm.R;

import java.util.List;

public class listviewadapter extends RecyclerView.Adapter<listviewadapter.InnerHolder>{

    private final List<String> datas;

    public listviewadapter(List<String> datas)
    {
        this.datas=datas;
    }
    @NonNull
    @Override
    //创建内部holder
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //传入条目界面
        View view=View.inflate(parent.getContext(), R.layout.list_view_item,null);//get view
        return new InnerHolder(view);
    }
//绑定内部holder，用来设置数据
    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.setData(datas.get(position));
    }
   //返回条目个数
    @Override
    public int getItemCount() {
        if(datas!=null)
        {
            return datas.size();
        }
        return 0;
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private TextView tx;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            tx=itemView.findViewById(R.id.list_title);
        }
        //设置数据
        public void setData(String s) {
            tx.setText(s);
        }
    }
    
}
