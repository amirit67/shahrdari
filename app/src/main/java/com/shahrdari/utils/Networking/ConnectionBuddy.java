package com.shahrdari.utils.Networking;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Željko Plesac on 09/10/15.
 * Configuration class for ConnectionBuddy instance. Use this to customize the library behaviour.
 */
public class ConnectionBuddy {

    /*private static final String HEADER_KEY_USER_AGENT = "User-Agent";
    //private Map<String, String> params = new HashMap<>();
    private static final String HEADER_VALUE_USER_AGENT = "Android";
    private static final String HEADER_KEY_CONNECTION = "Connection";
    private static final String HEADER_VALUE_CONNECTION = "close";
    private static final String NETWORK_CHECK_URL = "http://clients3.google.com/generate_204";
    private static final int CONNECTION_TIMEOUT = 1500;
    private static volatile ConnectionBuddy instance;*/
    private ConnectionBuddyConfiguration configuration;

    private ExecutorService executor;
    private Executor callbackExecutor = new Executor() {

        Handler mainHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(Runnable command) {
            mainHandler.post(command);
        }
    };

    public synchronized void init(ConnectionBuddyConfiguration configuration) {
        if (configuration == null) {
            throw new IllegalArgumentException();
        }
        if (this.configuration == null) {
            this.configuration = configuration;
        }
    }

    public ConnectionBuddyConfiguration getConfiguration() {
        return configuration;
    }

    public void testNetworkRequest(Context cn, final NetworkRequestCheckListener listener) {
        int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
        int KEEP_ALIVE_TIME = 1;
        TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
        BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>();
        if (executor == null) {
            executor = new ThreadPoolExecutor(NUMBER_OF_CORES,
                    NUMBER_OF_CORES * 2,
                    KEEP_ALIVE_TIME,
                    KEEP_ALIVE_TIME_UNIT,
                    taskQueue,
                    new BackgroundThreadFactory());
        }

        executor.execute(() -> {
            /*try {

                UserLogin login = new UserLogin();
                login.setUsername(Investam2Application.preferences.getString(Constants.User_Name, ""));
                login.setPassword(Investam2Application.preferences.getString(Constants.Password, ""));

                Investam2Application.getRemoteRepository().InvestamApplicantLogin2(login).enqueue(new Callback<UserLogin>() {
                    @Override
                    public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {
                        if (response.body().getStatusCode() == 200)
                            callbackExecutor.execute(() -> listener.onResponseObtained(response.body()));
                        else {
                            if (response.body().getStatusCode() != 401)
                                listener.onNoResponse(true, response.body().getStatusCode());
                            else
                                listener.onNoResponse(false, response.body().getStatusCode());
                        }
                    }

                    @Override
                    public void onFailure(Call<UserLogin> call, Throwable t) {
                        callbackExecutor.execute(() -> listener.onNoResponse(false, 101));
                    }
                });

            } catch (Exception e) {
                callbackExecutor.execute(() -> listener.onNoResponse(false, 101));
            }*/
        });
    }

    public interface NetworkRequestCheckListener {

        // void onResponseObtained(UserLogin tkModel);

        void onNoResponse(boolean state, int code);

    }

    private static class BackgroundThreadFactory implements ThreadFactory {
        private static int sTag = 1;

        @Override
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName("CustomThread" + sTag);
            //thread.setPriority(Process.THREAD_PRIORITY_BACKGROUND);

            // A exception handler is created to log the exception from threads
            thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                @Override
                public void uncaughtException(Thread thread, Throwable ex) {
                    //Log.e(Util.LOG_TAG, thread.getName() + " encountered an error: " + ex.getMessage());
                }
            });
            return thread;
        }
    }

}