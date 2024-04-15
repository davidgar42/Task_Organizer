package com.example.taskorganizer.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskorganizer.R;
import com.example.taskorganizer.list.models.Task;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TaskListFragment extends Fragment   {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_task_list, container, false); //infla la vista
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        RecyclerView rvTaskList = v.findViewById(R.id.rvTaskList);

        List<Task> tasks = new ArrayList<>();

        Date creationDate = Calendar.getInstance().getTime();
        Date expirationDate = Calendar.getInstance().getTime();

        Task task = new Task("pasear al perro", "debo pasearlo", creationDate, expirationDate);
        tasks.add(task);
        tasks.add(task);
        tasks.add(task);
        tasks.add(task);
        tasks.add(task);

        TaskAdapter adapter= new TaskAdapter(tasks, getActivity());//le pasa el contexto con getActivity
        rvTaskList.setAdapter(adapter);
        rvTaskList.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        rvTaskList.setLayoutManager(manager);
    }
}
