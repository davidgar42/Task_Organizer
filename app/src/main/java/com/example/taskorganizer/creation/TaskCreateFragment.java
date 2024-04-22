package com.example.taskorganizer.creation;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.taskorganizer.R;
import com.example.taskorganizer.list.models.Task;
import com.example.taskorganizer.utils.PreferencesManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TaskCreateFragment extends Fragment   {
    Calendar calendarExpitation, calendarToday;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_task_create, container, false); //infla la vista
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        calendarExpitation = Calendar.getInstance();
        calendarToday = Calendar.getInstance();


        final EditText etTaskName = v.findViewById(R.id.etTaskName);
        final EditText etTaskDescription = v.findViewById(R.id.etTaskDescription);
        final EditText etTaskExpiration = v.findViewById(R.id.etTaskExpiration);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                calendarExpitation.set(Calendar.YEAR, year);
                calendarExpitation.set(Calendar.MONTH, month);
                calendarExpitation.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                calendarExpitation.getTime();
                String format = "dd/MM/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(format);

                etTaskExpiration.setText(sdf.format(calendarExpitation.getTime()));
            }
        };


        etTaskExpiration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), date, calendarExpitation.get(Calendar.YEAR), calendarExpitation.get(Calendar.MONTH), calendarExpitation.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



        Button btCreateTask = v.findViewById(R.id.btCreateTask);

        btCreateTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskName = etTaskName.getText().toString();
                String taskDescription = etTaskDescription.getText().toString();
                String taskExpiration = etTaskExpiration.getText().toString();

                if(taskName.isEmpty() || taskExpiration.isEmpty() || taskDescription.isEmpty()){
                    return;
                }

                Task task = new Task(taskName, taskDescription, calendarToday.getTime(), calendarExpitation.getTime());

                PreferencesManager pm = new PreferencesManager(getActivity());
                pm.saveTask(task);
            }
        });


        //PreferencesManager pm = new PreferencesManager(getActivity());
        //pm.resetTaskList(); //borra todas las tareas guardas y guarda un arraylist vacio
    }
}
