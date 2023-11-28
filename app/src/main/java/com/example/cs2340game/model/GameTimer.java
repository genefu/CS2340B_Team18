package com.example.cs2340game.model;

import android.os.Handler;
import android.os.Looper;

public class GameTimer {

    private Handler handler;
    private Runnable runnable;
    private int ticks;
    private TimerListener listener;
    private boolean running;

    //Constructor for the gamer timer
    public GameTimer(TimerListener listener) {
        this.listener = listener;
        handler = new Handler(Looper.myLooper());
        startTimer();
        running = true;
    }

    //Starts the timer
    private void startTimer() {
        int delayMilis = 1000 / Model.REFRESH_RATE;
        runnable = new Runnable() {
            @Override
            public void run() {
                ticks++;
                listener.onTimerUpdate(ticks);
                if (running) {
                    handler.postDelayed(this, delayMilis); //updates game roughly 20 times a second
                } else {
                    handler.removeCallbacks(this);
                }
            }
        };

        handler.postDelayed(runnable, delayMilis);
    }

    // Stops the timer
    public void stopTimer() {
        running = false;
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