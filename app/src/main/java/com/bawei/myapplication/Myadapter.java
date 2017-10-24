package com.bawei.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Administrator on 2017/10/24.
 */

public class Myadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    List<Qbean.DataBean> list;
    Context context;

    public Myadapter(List<Qbean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
          MyHolder myHolder=new MyHolder(view);
           return myHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MyHolder){
         MyHolder viewHolder= (MyHolder) holder;
                viewHolder.t1.setText(list.get(position).getTitle());
               viewHolder.t2.setText(list.get(position).getIntroduction());
            Glide.with(context).load(list.get(position).getImg()).into(viewHolder.i1);
        }



    }

    @Override
    public int getItemCount() {
        return list.size();
    }
   public class MyHolder extends RecyclerView.ViewHolder{
     TextView t1,t2;
       ImageView i1;

       public MyHolder(View itemView) {
           super(itemView);
            t1=itemView.findViewById(R.id.t1);
           t2=itemView.findViewById(R.id.t2);
           i1=itemView.findViewById(R.id.i1);
       }
   }

}
