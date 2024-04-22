package com.example.taskorganizer.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.taskorganizer.list.models.Task;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PreferencesManager {
    private final String PREFS_NAME = "task_prefs";
    private final String PREFS_TASK = "prefs_task_list";
    private Context ctx;

    public PreferencesManager(Context ctx) {
        this.ctx = ctx;
    }

    public String getPrefs(String key){
        SharedPreferences prefs = ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(key, "");
    }

    public void setPrefs(String key, String value) {
        if(value == null){
            value = "";
        }
        SharedPreferences.Editor editor = ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void saveTaskList(List<Task> tasks){
        Gson gson = new Gson();

        String jsonTask = gson.toJson(tasks);
        setPrefs(PREFS_TASK, jsonTask);
    }

    public void saveTask(Task task){
        List<Task> tasks = loadTaskList();
        if (tasks == null){
            tasks = new ArrayList<>();
        }

        tasks.add(task);
        saveTaskList(tasks);
    }



    public void resetTaskList(){
        List<Task> tasks = new ArrayList<>();
        saveTaskList(tasks);
    }

    public List<Task> loadTaskList(){
        String jsonTask = getPrefs(PREFS_TASK);
        Type taskType = new TypeToken<List<Task>>(){}.getType();

        return new Gson().fromJson(jsonTask, taskType);
    }
}
