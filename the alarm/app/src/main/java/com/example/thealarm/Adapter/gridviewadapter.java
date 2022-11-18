package com.example.thealarm.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thealarm.R;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class gridviewadapter extends RecyclerView.Adapter<gridviewadapter.innerholder> {

    private List<String>datas;
    public gridviewadapter(List<String> s){
        this.datas=s;
    }
    @NonNull
    @Override

    public gridviewadapter.innerholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(parent.getContext(), R.layout.grid_view,null);
        return new innerholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull gridviewadapter.innerholder holder, int position) {
        holder.setdata(datas.get(position));
    }

    @Override
    public int getItemCount() {
        if(datas!=null)
            return datas.size();
        return 0;
    }
    public class innerholder extends RecyclerView.ViewHolder {
      private TextView tx;
        public innerholder(@NonNull View itemView) {
            super(itemView);
            tx=itemView.findViewById(R.id.grid_title);
        }

        public void setdata(String s) {
            tx.setText(s);
        }
    }
}
