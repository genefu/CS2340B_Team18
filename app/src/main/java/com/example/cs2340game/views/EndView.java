package com.example.cs2340game.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340game.R;
import com.example.cs2340game.viewmodels.EndViewModel;

public class EndView extends AppCompatActivity {
    private TextView[] leaderboardPlayers;
    private TextView[] leaderboardScores;
    private TextView winStatus;
    private EndViewModel viewModel;

    //Displays the view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_view);


        leaderboardPlayers = new TextView[10];
        leaderboardScores = new TextView[10];
        winStatus = (TextView) findViewById(R.id.win_status);
        addLeaderboardTextViews();

        viewModel = new EndViewModel(leaderboardPlayers, leaderboardScores, winStatus);
    }

    //Adds all leaderboard text views to their respective arrays
    public void addLeaderboardTextViews() {
        leaderboardPlayers[0] = (TextView) findViewById(R.id.player1);
        leaderboardPlayers[1] = (TextView) findViewById(R.id.player2);
        leaderboardPlayers[2] = (TextView) findViewById(R.id.player3);
        leaderboardPlayers[3] = (TextView) findViewById(R.id.player4);
        leaderboardPlayers[4] = (TextView) findViewById(R.id.player5);
        leaderboardPlayers[5] = (TextView) findViewById(R.id.player6);
        leaderboardPlayers[6] = (TextView) findViewById(R.id.player7);
        leaderboardPlayers[7] = (TextView) findViewById(R.id.player8);
        leaderboardPlayers[8] = (TextView) findViewById(R.id.player9);
        leaderboardPlayers[9] = (TextView) findViewById(R.id.player10);

        leaderboardScores[0] = (TextView) findViewById(R.id.score1);
        leaderboardScores[1] = (TextView) findViewById(R.id.score2);
        leaderboardScores[2] = (TextView) findViewById(R.id.score3);
        leaderboardScores[3] = (TextView) findViewById(R.id.score4);
        leaderboardScores[4] = (TextView) findViewById(R.id.score5);
        leaderboardScores[5] = (TextView) findViewById(R.id.score6);
        leaderboardScores[6] = (TextView) findViewById(R.id.score7);
        leaderboardScores[7] = (TextView) findViewById(R.id.score8);
        leaderboardScores[8] = (TextView) findViewById(R.id.score9);
        leaderboardScores[9] = (TextView) findViewById(R.id.score10);
    }

    //Switches view to WelcomeView
    public void toWelcomeView(View view) {
        startActivity(new Intent(EndView.this, WelcomeView.class));
    }
}
