package com.example.taskorganizer.list;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskorganizer.R;

public class TaskViewHolder extends RecyclerView.ViewHolder {

    public TextView tvTaskTitle, tvTaskDesc;
    public Button btTaskDelete;



    public TaskViewHolder(@NonNull View v) {
        super(v);

        tvTaskTitle = v.findViewById(R.id.tvTaskTitle);
        tvTaskDesc = v.findViewById(R.id.tvTaskDesc);
        btTaskDelete = v.findViewById(R.id.btTaskDelete);

    }
}
