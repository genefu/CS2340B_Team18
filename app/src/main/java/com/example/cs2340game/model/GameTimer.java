package com.example.cs2340game.model;

import android.os.Handler;
import android.os.Looper;

public class GameTimer {

    private Handler handler;
    private Runnable runnable;
    private int ticks;
    private TimerListener listener;

    //Constructor
    public GameTimer(TimerListener listener) {
        this.listener = listener;
        handler = new Handler(Looper.myLooper());
        startTimer();
    }

    //Starts the timer
    private void startTimer() {
        runnable = new Runnable() {
            @Override
            public void run() {
                ticks++;
                listener.onTimerUpdate(ticks);
                handler.postDelayed(this, 33); //updates game roughly 30 times a second
            }
        };

        handler.postDelayed(runnable, 33);
    }

    // Stops the timer
    public void stopTimer() {
        handler.removeCallbacks(runnable);
    }

    // Interface to implement if you want to use the timer in a view
    public interface TimerListener {
        void onTimerUpdate(int seconds);
    }
}