package com.example.butler;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.CustomViewHolder> {
    Context mContext;
    List<TasksClass> task;
    String tokenID;
    public Adapter(Context context, List<TasksClass> task, String token){
mContext=context;
this.task=task;
tokenID=token;
    }
    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycle_view,
                viewGroup, false);
        return new CustomViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder customViewHolder, int i) {
        TasksClass t=task.get(i);
        customViewHolder.taskName.setText(t.getName());
        customViewHolder.taskDevice.setText(t.getDevice());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
           TextView taskName;
           TextView taskDevice;


        public CustomViewHolder(View itemView) {
            super(itemView);
            taskName=itemView.findViewById(R.id.task_name);
            taskDevice=itemView.findViewById(R.id.task_device);
        }
    }

}
