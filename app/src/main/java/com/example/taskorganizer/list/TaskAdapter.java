package com.example.taskorganizer.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskorganizer.R;
import com.example.taskorganizer.list.models.Task;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskViewHolder> {
    private List<Task> tasks;
    private Context ctx;

    public TaskAdapter(List<Task> tasks, Context ctx) {
        this.tasks = tasks;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_task, parent, false);

        return new TaskViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int i) { //ojo el final antes de TaskViewHolder
        holder.tvTaskTitle.setText(tasks.get(i).getTitle());
        holder.tvTaskDesc.setText(tasks.get(i).getDescription());

        holder.btTaskDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //codigo para eliminar una task
                tasks.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size(); //esto es lo que presenta por pantalla
    }
}
