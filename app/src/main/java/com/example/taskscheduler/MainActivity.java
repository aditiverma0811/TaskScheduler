package com.example.taskscheduler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements TaskAdapter.ItemClickListener{
    // Constant for logging
    private static final String TAG = MainActivity.class.getSimpleName();
    // Member variables for the adapter and RecyclerView
    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    // COMPLETED (1) Create AppDatabase member variable for the Database
   private AppDatabase mDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Set the RecyclerView to its corresponding view
        recyclerView=findViewById(R.id.recyclerViewTask);
        // Set the layout for the RecyclerView to be a linear layout, which measures and
        // positions items within a RecyclerView into a linear list
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Initialize the adapter and attach it to the RecyclerView
        adapter = new TaskAdapter(this,this);
        recyclerView.setAdapter(adapter);


        DividerItemDecoration decoration=new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decoration);

        /*
         Add a touch helper to the RecyclerView to recognize when a user swipes to delete an item.
         An ItemTouchHelper enables touch behavior (like swipe and move) on each ViewHolder,
         and uses callbacks to signal when a user is performing these actions.
         */
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {
                // Get the diskIO Executor from the instance of AppExecutors and
                // call the diskIO execute method with a new Runnable and implement its run method
                AppExecutor.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                                   int position=viewHolder.getAdapterPosition();
                                   List<TaskEntry>tasks=adapter.getTasks();
                                   mDb.taskDao().deleteTask(tasks.get(position));
                    }
                });

            }
        }).attachToRecyclerView(recyclerView);
        FloatingActionButton fabButton = findViewById(R.id.fab);

        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new intent to start an AddTaskActivity
                Intent addTaskIntent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(addTaskIntent);
            }
        });
        // COMPLETED (2) Initialize member variable for the data base

        mDb=AppDatabase.getInstance(getApplicationContext());
        reteriveTask();
    }


    private void reteriveTask() {
        // COMPLETED (6) Declare a ViewModel variable and initialize it by calling ViewModelProviders.of
        MainViewModel viewModel= ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.getTasks().observe(this, new Observer<List<TaskEntry>>() {
            @Override
            public void onChanged(List<TaskEntry> taskEntries) {
                adapter.setTasks(taskEntries);
            }
        });
    }

    @Override
    public void onItemClickListener(int id) {
            // Launch AddTaskActivity adding the itemId as an extra in the intent
        //  Launch AddTaskActivity with itemId as extra for the key AddTaskActivity.EXTRA_TASK_ID
        Intent i=new Intent(MainActivity.this,AddTaskActivity.class);
        i.putExtra(AddTaskActivity.EXTRA_TASK_ID,id);
        startActivity(i);

    }
}
