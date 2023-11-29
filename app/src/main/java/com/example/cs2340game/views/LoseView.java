package com.example.cs2340game.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.media.MediaPlayer;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340game.R;

public class LoseView extends AppCompatActivity {
    private MediaPlayer soundEffect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lose_view);
        soundEffect = MediaPlayer.create(this, R.raw.losseffect);
        soundEffect.setVolume(200.0f, 200.0f);
        soundEffect.start();
    }

    public void toLeaderboard(View view) {
        startActivity(new Intent(LoseView.this, EndView.class));
    }

    public void toEndView(View view) {
        startActivity(new Intent(LoseView.this, EndView.class));
    }
}
