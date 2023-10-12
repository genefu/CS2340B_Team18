package com.example.cs2340game.viewmodels;

import android.util.Log;
import android.widget.TextView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.cs2340game.model.Score;
import com.example.cs2340game.model.Model;

import java.util.ArrayList;

//View Model for End Screen
public class EndViewModel extends BaseObservable {
    private Model model;
    private TextView winStatus;
    @Bindable
    private ArrayList<Score> leaderboard;

    // Constructor
    public EndViewModel(TextView[] leaderboardPlayers,
                        TextView[] leaderboardScores, TextView winStatus,
                        TextView[] leaderboardTimes) {
        this.model = Model.getInstance();
        //initialize leaderboard
        Log.d("iwantdeath", "initialized");
        leaderboard = new ArrayList<>();
        int i = 0;
        for (Score score : model.getLeaderboard()) {
            Log.d("iwantdeath", score.toString());
            leaderboard.add(score);
            leaderboardPlayers[i].setText(score.getPlayerName());
            leaderboardScores[i].setText(String.valueOf(score.getScore()));
            leaderboardTimes[i].setText(score.getDateTime());
            i++;
        }

        winStatus.setText(model.isWinner() ? "You win" : "You lose");
    }

    //Getter for leaderboard
    public ArrayList<Score> getLeaderboard() {
        Log.d("iwantdeath", "getleaderboard");
        return leaderboard;
    }

    // actions to be performed
    // when user clicks a button
    public void onButtonClicked() {
        //TODO implement if button added
    }
}
