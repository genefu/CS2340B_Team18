package com.example.cs2340game.views;

import android.content.Intent;
import android.os.Bundle;
//import android.text.SpannableString;
//import android.text.Spanned;
import android.text.TextUtils;
//import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
//import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.content.res.ResourcesCompat;

import com.example.cs2340game.R;
import com.example.cs2340game.model.GameTimer;
import com.example.cs2340game.model.Model;
import com.example.cs2340game.model.Player;
import com.example.cs2340game.viewmodels.ConfigurationViewModel;
//import com.google.android.material.button.MaterialButton;

public class ConfigurationView extends AppCompatActivity implements
        View.OnClickListener, GameTimer.TimerListener {
    private Model model;
    private ConfigurationViewModel viewModel;
    private GameTimer gameTimer;
    private EditText nameSender;
    private ImageButton easy;
    private ImageButton medium;
    private ImageButton hard;
    private ImageButton char1;
    private ImageButton char2;
    private ImageButton char3;
    //Displays the view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuration_view);
        this.model = Model.getInstance();
        nameSender = (EditText) findViewById(R.id.nameTextField);
        viewModel = new ConfigurationViewModel();
        gameTimer = new GameTimer(this);

        easy = (ImageButton) findViewById(R.id.easy_button);
        easy.setOnClickListener(this);
        medium = (ImageButton) findViewById(R.id.medium_button);
        medium.setOnClickListener(this);
        hard = (ImageButton) findViewById(R.id.hard_button);
        hard.setOnClickListener(this);
        char1 = (ImageButton) findViewById(R.id.sprite1);
        char1.setOnClickListener(this);
        char2 = (ImageButton) findViewById(R.id.sprite2);
        char2.setOnClickListener(this);
        char3 = (ImageButton) findViewById(R.id.sprite3);
        char3.setOnClickListener(this);
    }

    //Switches view to GameView
    public void toGameView() {
        Log.d("iwantdeath", "Going to Game View");
        String playerName = nameSender.getText().toString();
        viewModel.resetGame();
        model.setPlayerName(playerName);
        gameTimer.stopTimer();
        model.getScore().setScoreValue(20);
        //sendIntent.putExtra("nameSend",nameSender.getText().toString()); Obsolete
        startActivity(new Intent(this, GameView.class));
    }

    //registers a click in general (difficulty or sprite) and sets corresponding value
    @Override
    public void onClick(View view) {
        Log.d("iwantdeath", "OnClick");
        if (view.getId() == R.id.easy_button) {
            viewModel.onDifficultyClicked(Model.Difficulty.EASY);
            easy.setImageResource(R.drawable.triangle_sprite_highlighted);
            medium.setImageResource(R.drawable.square_sprite);
            hard.setImageResource(R.drawable.circle_sprite);
        } else if (view.getId() == R.id.medium_button) {
            viewModel.onDifficultyClicked(Model.Difficulty.MEDIUM);
            easy.setImageResource(R.drawable.triangle_sprite);
            medium.setImageResource(R.drawable.square_sprite_highlighted);
            hard.setImageResource(R.drawable.circle_sprite);
        } else if (view.getId() == R.id.hard_button) {
            viewModel.onDifficultyClicked(Model.Difficulty.HARD);
            easy.setImageResource(R.drawable.triangle_sprite);
            medium.setImageResource(R.drawable.square_sprite);
            hard.setImageResource(R.drawable.circle_sprite_highlighted);
        } else if (view.getId() == R.id.sprite1) {
            viewModel.onSpriteClicked("sprite1");
            char1.setImageResource(R.drawable.sprite1_highlighted);
            char2.setImageResource(R.drawable.sprite2);
            char3.setImageResource(R.drawable.sprite3);
        } else if (view.getId() == R.id.sprite2) {
            viewModel.onSpriteClicked("sprite2");
            char1.setImageResource(R.drawable.sprite1);
            char2.setImageResource(R.drawable.sprite2_highlighted);
            char3.setImageResource(R.drawable.sprite3);
        } else if (view.getId() == R.id.sprite3) {
            viewModel.onSpriteClicked("sprite3");
            char1.setImageResource(R.drawable.sprite1);
            char2.setImageResource(R.drawable.sprite2);
            char3.setImageResource(R.drawable.sprite3_highlighted);
        } else if (view.getId() == R.id.next_button) {
            Log.d("gameTick", "Detected next button");
            toGameView();
        }
    }

    //Updates next button based on if the playerName input is filled out
    public void updateNextButton() {
        String playerName = nameSender.getText().toString();
        if (TextUtils.isEmpty(playerName.trim())) {
            ((TextView) findViewById(R.id.nameRequirement)).setText(
                    "A name must be entered before continuing");
            findViewById(R.id.next_button).setEnabled(false);
        } else {
            ((TextView) findViewById(R.id.nameRequirement)).setText(
                    " ");
            model.setPlayerName(playerName);
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
