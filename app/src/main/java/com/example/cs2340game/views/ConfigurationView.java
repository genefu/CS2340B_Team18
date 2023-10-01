package com.example.cs2340game.views;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.text.TextWatcher;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340game.R;
import com.example.cs2340game.model.GameTimer;
import com.example.cs2340game.model.Model;
import com.example.cs2340game.viewmodels.ConfigurationViewModel;

public class ConfigurationView extends AppCompatActivity implements View.OnClickListener, GameTimer.TimerListener {
    private ConfigurationViewModel viewModel;
    private GameTimer gameTimer;
    EditText nameSender;
    //Displays the view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuration_view);
        nameSender = (EditText) findViewById(R.id.nameTextField);
        viewModel = new ConfigurationViewModel();
        gameTimer = new GameTimer(this);

        ImageButton easy = (ImageButton) findViewById(R.id.easy_button);
        easy.setOnClickListener(this);
        ImageButton medium = (ImageButton) findViewById(R.id.medium_button);
        medium.setOnClickListener(this);
        ImageButton hard = (ImageButton) findViewById(R.id.hard_button);
        hard.setOnClickListener(this);
        ImageButton triangle = (ImageButton) findViewById(R.id.sprite1);
        triangle.setOnClickListener(this);
        ImageButton square = (ImageButton) findViewById(R.id.sprite2);
        square.setOnClickListener(this);
        ImageButton circle = (ImageButton) findViewById(R.id.sprite3);
        circle.setOnClickListener(this);
    }

    //Switches view to GameView
    public void toGameView(View view) {
        Log.d("iwantdeath", "Going to Game View");
        String playerName = nameSender.getText().toString();
        Model.setPlayerName(playerName);
        //sendIntent.putExtra("nameSend",nameSender.getText().toString()); Obsolete
        startActivity(new Intent(this, GameView.class));
    }

    //registers a click in general (difficulty or sprite) and sets corresponding value
    @Override
    public void onClick(View view) {
        Log.d("iwantdeath", "OnClick");
        if (view.getId() == R.id.easy_button) {
            viewModel.onDifficultyClicked(0);
        } else if (view.getId() == R.id.medium_button) {
            viewModel.onDifficultyClicked(1);
        } else if (view.getId() == R.id.hard_button) {
            viewModel.onDifficultyClicked(2);
        } else if (view.getId() == R.id.sprite1) {
            viewModel.onSpriteClicked("sprite1");
        } else if (view.getId() == R.id.sprite2) {
            viewModel.onSpriteClicked("sprite2");
        } else if (view.getId() == R.id.sprite3) {
            viewModel.onSpriteClicked("sprite3");
        } else if (view.getId() == R.id.next_button) {
            Log.d("gameTick", "Detected next button");
            toGameView(view);
        }
    }
    //Updates next button based on if the playerName input is filled out
    public void updateNextButton() {
        String playerName = nameSender.getText().toString();
        if (TextUtils.isEmpty(playerName.trim())) {
            ((TextView) findViewById(R.id.nameRequirement)).setText("Premium");
            findViewById(R.id.next_button).setEnabled(false);
        } else {
            Model.setPlayerName(playerName);
            findViewById(R.id.next_button).setEnabled(true);
        }
    }

    //Game timer
    @Override
    public void onTimerUpdate(int ticks) {
        Log.d("iwantdeath", "tick: " + ticks);
        updateNextButton();
    }
}
