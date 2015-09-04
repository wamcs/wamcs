package com.example.cost.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cost.R;
import com.example.cost.Util;
import com.example.cost.activity.BillDetail;
import com.example.cost.activity.Cost;

import java.util.ArrayList;
import java.util.Map;

public class BodyItemAdapter extends RecyclerView.Adapter<BodyItemAdapter.BillViewHolder>
{

    private Context context;
    private ArrayList<Map<String,Object>> datalsit;
    private int length;
    private Intent intent;
    private Activity activity;
    public BodyItemAdapter(Context context, ArrayList<Map<String, Object>> datalsit,Activity activity){
        this.context=context;
        this.datalsit=datalsit;
        this.activity=activity;
        length=datalsit.size();
    }


    @Override
    public BillViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.view_activity_body_item,parent,false);
        BillViewHolder billViewHolder=new BillViewHolder(view);
        billViewHolder.content= (TextView) view.findViewById(R.id.view_activity_body_content);
        billViewHolder.money= (TextView) view.findViewById(R.id.view_activity_body_money);
        billViewHolder.linearLayout= (LinearLayout) view.findViewById(R.id.view_activity_body_layout);
        return billViewHolder;
    }

    @Override
    public void onBindViewHolder(BillViewHolder holder, final int position){
        holder.content.setWidth(Util.width/3);
        holder.money.setWidth(Util.width/3);
        holder.content.setText(datalsit.get(length-1-position).get("content").toString());
        if((int)datalsit.get(length-1-position).get("income")==0)
            holder.money.setText(datalsit.get(length-1-position).get("pay").toString());
        else
            holder.money.setText(datalsit.get(length - 1 - position).get("income").toString());

        holder.linearLayout.setBackgroundColor(context.getResources().
                getColor((int)datalsit.get(length-1-position).get("color")));
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(context,BillDetail.class);
                intent.putExtra("billitemID", (int) datalsit.get(length - 1 - position).get("ID"));
                int[] location=new int[2];
                v.getLocationOnScreen(location);
                location[0]+=v.getWidth()/2;
                intent.putExtra("location", location);
                activity.startActivity(intent);
                activity.overridePendingTransition(0,0);
            }
        });

    }

    @Override
    public int getItemCount() {
        return length;
    }


    class BillViewHolder extends RecyclerView.ViewHolder{
        private TextView content;
        private TextView money;
        private LinearLayout linearLayout;
        public BillViewHolder(View itemView) {
            super(itemView);
        }
    }


}
