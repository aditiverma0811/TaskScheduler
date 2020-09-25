package com.example.taskscheduler;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

// COMPLETED (1) Make this class extend ViewModel ViewModelProvider.NewInstanceFactory

public class AddTaskViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    // COMPLETED (2) Add two member variables. One for the database and one for the taskId

    private final AppDatabase mDb;
    private final int mTaskId;
    // COMPLETED (3) Initialize the member variables in the constructor with the parameters received

    public AddTaskViewModelFactory(AppDatabase mDb, int mTaskId) {
        this.mDb = mDb;
        this.mTaskId = mTaskId;
    }
    // COMPLETED (4) Uncomment the following method

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AddTaskViewModel(mDb,mTaskId);
    }
}
