package com.example.cs2340game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import com.example.cs2340game.model.Enemies.DevilFactory;
import com.example.cs2340game.model.Enemies.Enemy;
import com.example.cs2340game.model.Enemies.MedusaEnemy;
import com.example.cs2340game.model.Enemies.MedusaFactory;
import com.example.cs2340game.model.Enemies.SirenFactory;
import com.example.cs2340game.model.Enemies.SpiderFactory;
import com.example.cs2340game.model.Model;
import com.example.cs2340game.model.Player;
import com.example.cs2340game.model.Powerups.BasicPowerUp;
import com.example.cs2340game.model.Powerups.PowerUp;
import com.example.cs2340game.model.Powerups.RangeUpDecorator;
import com.example.cs2340game.model.Powerups.ScoreUpDecorator;
import com.example.cs2340game.model.Powerups.SpeedUpDecorator;
import com.example.cs2340game.model.Tile;
import com.example.cs2340game.viewmodels.GameViewModel;
import com.example.cs2340game.views.GameView;

import org.junit.Assert;
import org.junit.Test;

import java.util.TreeSet;

public class Sprint5Tests {
    @Test
    public void scoreModifier() {
        Model model = Model.getInstance();
        ScoreUpDecorator d = new ScoreUpDecorator(new BasicPowerUp(model.getPlayer()));
        Assert.assertEquals(20, model.getScore().getScoreValue());
        model.getScore().addScoreValue(2, model.getPlayer().getScoreMultiplier());
        Assert.assertEquals(28, model.getScore().getScoreValue());
        d.applyPowerUp();
        model.getScore().addScoreValue(2, model.getPlayer().getScoreMultiplier());
        Assert.assertEquals(40, model.getScore().getScoreValue());
    }

    @Test
    public void speedModifier() {
        Model.clearInstance();
        Model model = Model.getInstance();
        Player player = model.getPlayer();
        SpeedUpDecorator s = new SpeedUpDecorator(new BasicPowerUp(model.getPlayer()));
        Assert.assertEquals(1, player.getAvatar().getSpeed(), .1);
        s.applyPowerUp();
        Assert.assertEquals(1.2, player.getAvatar().getSpeed(), .1);
    }

    @Test
    public void scoreDecreaseWithTime() {
        Model.clearInstance();
        Model model = Model.getInstance();
        int score = model.getScore().getScoreValue();
        GameViewModel gameViewModel = new GameViewModel();
        gameViewModel.decrementScore();
        assertFalse("yay", score == model.getScore().getScoreValue());
    }

    @Test
    public void scoreIncreasesWithEnemyDefeat() {
        Model.clearInstance();
        Player.clearInstance();
        Model model = Model.getInstance();
        Player player = Player.getInstance();
        GameViewModel gameViewModel = new GameViewModel();
        player.addDefeatedEnemies(1);
        gameViewModel.updateScore(player.getEnemiesDefeated() - gameViewModel.getEnemiesDefeated());
        assertEquals(24, model.getScore().getScoreValue());
    }

    @Test
    public void removeMedusa() {
        Model.clearInstance();
        Model model = Model.getInstance();
        MedusaFactory medusaFactory = MedusaFactory.getInstance();
        model.addEnemy(medusaFactory.createEnemy(1, Tile.TILE_SIZE * 12, Tile.TILE_SIZE * 10));
        model.addEnemy(medusaFactory.createEnemy(2, Tile.TILE_SIZE * 12, Tile.TILE_SIZE * 10));
        model.removeEnemy(1);
        assertEquals(1, model.getRenderedEnemies().size());
    }

    @Test
    public void removeDevil() {
        Model.clearInstance();
        Model model = Model.getInstance();
        DevilFactory devilFactory = DevilFactory.getInstance();
        model.addEnemy(devilFactory.createEnemy(1, Tile.TILE_SIZE * 12, Tile.TILE_SIZE * 10));
        model.addEnemy(devilFactory.createEnemy(2, Tile.TILE_SIZE * 12, Tile.TILE_SIZE * 10));
        model.removeEnemy(1);
        assertEquals(1, model.getRenderedEnemies().size());
    }

    @Test
    public void removeSiren() {
        Model.clearInstance();
        Model model = Model.getInstance();
        SirenFactory sirenFactory = SirenFactory.getInstance();
        model.addEnemy(sirenFactory.createEnemy(1, Tile.TILE_SIZE * 12, Tile.TILE_SIZE * 10));
        model.addEnemy(sirenFactory.createEnemy(2, Tile.TILE_SIZE * 12, Tile.TILE_SIZE * 10));
        model.removeEnemy(1);
        assertEquals(1, model.getRenderedEnemies().size());
    }

    @Test
    public void removeSpider() {
        Model.clearInstance();
        Model model = Model.getInstance();
        SpiderFactory spiderFactory = SpiderFactory.getInstance();
        model.addEnemy(spiderFactory.createEnemy(1, Tile.TILE_SIZE * 12, Tile.TILE_SIZE * 10));
        model.addEnemy(spiderFactory.createEnemy(2, Tile.TILE_SIZE * 12, Tile.TILE_SIZE * 10));
        model.removeEnemy(1);
        assertEquals(1, model.getRenderedEnemies().size());
    }
}
