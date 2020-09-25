package com.example.taskscheduler;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
// COMPLETED (1) make this class extend AndroidViewModel and implement its default constructor

public class MainViewModel extends AndroidViewModel {
    // Constant for logging
    private static final String TAG = MainViewModel.class.getSimpleName();
    // COMPLETED (2) Add a tasks member variable for a list of TaskEntry objects wrapped in a LiveData
    private LiveData<List<TaskEntry>> tasks;
    public MainViewModel(@NonNull Application application) {
        super(application);
        // COMPLETED (4) In the constructor use the loadAllTasks of the taskDao to initialize the tasks variable

        AppDatabase database=AppDatabase.getInstance(getApplication());
        tasks=database.taskDao().loadAllTasks();
    }
    // COMPLETED (3) Create a getter for the tasks variable

    public LiveData<List<TaskEntry>> getTasks() {
        return tasks;
    }
}
