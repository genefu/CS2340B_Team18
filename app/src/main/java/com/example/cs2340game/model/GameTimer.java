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
                handler.postDelayed(this, 25); //updates game roughly 40 times a second
            }
        };

        handler.postDelayed(runnable, 25);
    }

    // Stops the timer
    public void stopTimer() {
        handler.removeCallbacks(runnable);
    }

    //Getter for ticks
    public int getTicks() {
        return ticks;
    }

    // Interface to implement if you want to use the timer in a view
    public interface TimerListener {
        void onTimerUpdate(int ticks);
    }
}