package com.example.taskscheduler;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Global executor pools for the whole application.
 * <p>
 * Grouping tasks like this avoids the effects of task starvation (e.g. disk reads don't wait behind
 * webservice requests).
 */
public class AppExecutor  {
    // For Singleton instantiation
    private static final Object LOCK=new Object();
    private static AppExecutor sInstance;
    private  final Executor diskIO;
    private  final Executor mainThread;
    private  final Executor networkIO;

    public AppExecutor(Executor diskIO, Executor mainThread, Executor networkIO) {
        this.diskIO = diskIO;
        this.mainThread = mainThread;
        this.networkIO = networkIO;
    }

    public static AppExecutor getInstance(){
        if (sInstance==null){
            synchronized (LOCK){
                sInstance=new AppExecutor(Executors.newSingleThreadExecutor(),
                        Executors.newFixedThreadPool(3),new MainThreadExecutor());
            }
        }
        return sInstance;
    }
    public Executor diskIO(){return diskIO; }
    public Executor mainThread(){return mainThread; }
    public Executor networkIO(){return networkIO; }

    private static class MainThreadExecutor implements Executor {
        private android.os.Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(Runnable command) {
            mainThreadHandler.post(command);
        }
    }
}
