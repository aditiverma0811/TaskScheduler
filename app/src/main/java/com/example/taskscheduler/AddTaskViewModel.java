package com.example.taskscheduler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
// COMPLETED (5) Make this class extend ViewModel

public class AddTaskViewModel extends ViewModel {
    // COMPLETED (6) Add a task member variable for the TaskEntry object wrapped in a LiveData

    private LiveData<TaskEntry> task;
    // COMPLETED (8) Create a constructor where you call loadTaskById of the taskDao to initialize the tasks variable
    // Note: The constructor should receive the database and the taskId


    public AddTaskViewModel(AppDatabase database,int taskId) {
        task=database.taskDao().loadTaskById(taskId);
    }
    // COMPLETED (7) Create a getter for the task variable

    public LiveData<TaskEntry> getTask() {
        return task;
    }
}
