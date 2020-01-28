package com.example.butler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ModeAdapter extends RecyclerView.Adapter<ModeAdapter.CustomViewHolder> {
    Context mContext;
    List<TasksClass> tasks;
    public ModeAdapter(List<TasksClass>tasks){
        this.tasks=tasks;
    }
    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_tasklist_item,
                viewGroup, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder customViewHolder, int i) {
        TasksClass t=tasks.get(i);
        customViewHolder.taskName.setText(t.getName());
    }


    @Override
    public int getItemCount() {
        return 0;
    }
    class CustomViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView taskName;


        public CustomViewHolder(View itemView) {
            super(itemView);
            taskName=itemView.findViewById(R.id.text);
        }
    }
}
