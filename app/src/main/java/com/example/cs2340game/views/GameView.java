package com.example.cs2340game.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340game.R;
import com.example.cs2340game.model.Model;
import com.example.cs2340game.viewmodels.GameViewModel;

public class GameView extends AppCompatActivity {
    private TextView nameTextView;
    private TextView healthTextView;
    private TextView strengthTextView;
    private ImageView playerSprite;
    private GameViewModel viewModel;
    private ImageView gameView;
    private TileMap tileSet;

    //Displays the view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_view);

        viewModel = new GameViewModel();
        nameTextView = (TextView) findViewById(R.id.nameReceiver);
        nameTextView.setText(Model.getPlayerName());
        healthTextView = (TextView) findViewById(R.id.HealthStat);
        healthTextView.setText("Health: " + Integer.toString(viewModel.getHealth()));
        strengthTextView = (TextView) findViewById(R.id.StrengthStat);
        strengthTextView.setText("Strength: " + Integer.toString(viewModel.getStrength()));
        playerSprite = (ImageView) findViewById(R.id.player_sprite);
        int id = this.getResources().getIdentifier(Model.getPlayer().getAvatar(),
                "drawable", this.getPackageName());
        playerSprite.setImageResource(id);

        gameView = (ImageView) findViewById(R.id.tileSet1);
        tileSet = new TileMap(gameView, "1", this);
        //gameView.setImageBitmap(tileSet.getTileSet());

    }

    //Switches view to EndView
    public void toEndView(View view) {
        startActivity(new Intent(GameView.this, EndView.class));
    }

}