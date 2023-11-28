package com.example.cs2340game.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340game.R;
import com.example.cs2340game.viewmodels.EndViewModel;

public class LoseView extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lose_view);
    }

    public void toEndView(View view) {
        startActivity(new Intent(LoseView.this, EndView.class));
    }
}
