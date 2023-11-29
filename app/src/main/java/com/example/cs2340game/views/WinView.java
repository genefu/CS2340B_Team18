package com.example.cs2340game.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340game.R;
import com.example.cs2340game.viewmodels.EndViewModel;

public class WinView extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_view);
    }

    public void toLeaderboard(View view) {
        startActivity(new Intent(WinView.this, EndView.class));
    }
    public void toEndView() {
        startActivity(new Intent(WinView.this, EndView.class));
    }
}
