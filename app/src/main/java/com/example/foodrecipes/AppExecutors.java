package com.example.foodrecipes;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static com.example.foodrecipes.util.Constants.THREAD_POOL;

public class AppExecutors {

    private static AppExecutors instance;

    public static AppExecutors getInstance() {
        if (instance == null) {
            return new AppExecutors();
        }
        return instance;
    }

    //TODO: pool size to constants
    private final ScheduledExecutorService mNetworkIO = Executors.newScheduledThreadPool(THREAD_POOL);

    public ScheduledExecutorService getNetworkIO() {
        return mNetworkIO;
    }
}
