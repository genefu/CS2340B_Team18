package com.example.cs2340game.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.media.MediaPlayer;
import androidx.appcompat.app.AppCompatActivity;
import com.example.cs2340game.R;

public class WinView extends AppCompatActivity {
    private MediaPlayer winEffect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_view);
        winEffect = MediaPlayer.create(this, R.raw.wineffect);
        winEffect.setVolume(200.0f, 200.0f);
        winEffect.start();
    }

    public void toLeaderboard(View view) {
        startActivity(new Intent(WinView.this, EndView.class));
    }
    public void toEndView() {
        startActivity(new Intent(WinView.this, EndView.class));
    }
}
