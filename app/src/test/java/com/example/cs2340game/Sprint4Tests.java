package com.example.cs2340game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import com.example.cs2340game.model.Avatar;
import com.example.cs2340game.model.Enemies.DevilEnemy;
import com.example.cs2340game.model.Enemies.DevilFactory;
import com.example.cs2340game.model.Enemies.MedusaEnemy;
import com.example.cs2340game.model.Enemies.MedusaFactory;
import com.example.cs2340game.model.Enemies.SirenEnemy;
import com.example.cs2340game.model.Enemies.SirenFactory;
import com.example.cs2340game.model.Enemies.SpiderEnemy;
import com.example.cs2340game.model.Enemies.SpiderFactory;
import com.example.cs2340game.model.Model;
import com.example.cs2340game.model.MovementStrategies.StandardVectors;
import com.example.cs2340game.model.MovementStrategies.WalkStrategy;
import com.example.cs2340game.model.Player;
import com.example.cs2340game.views.MapLayout;

import org.junit.Assert;
import org.junit.Test;

public class Sprint4Tests {
    @Test
    public void playerNoCollision() {
        SpiderFactory spiderFactory = SpiderFactory.getInstance();
        Avatar avatar = Avatar.getInstance("sprite1");
        SpiderEnemy spider = (SpiderEnemy) spiderFactory.createEnemy(1, 60, 0);
        avatar.setPosition(0, 0);
        int distance = spider.getDistance(avatar.getPosX(), avatar.getPosY());
        System.out.println(distance);
        Assert.assertEquals(false, distance < 60);
    }

    @Test
    public void playerCollision() {
        SpiderFactory spiderFactory = SpiderFactory.getInstance();
        Avatar avatar = Avatar.getInstance("sprite1");
        SpiderEnemy spider = (SpiderEnemy) spiderFactory.createEnemy(1, 59, 0);
        avatar.setPosition(0, 0);
        Assert.assertEquals(true, spider.getDistance(avatar.getPosX(), avatar.getPosY()) < 60);
    }

    @Test
    public void spiderCollision() {
        Player.clearInstance();
        Avatar avatar = Avatar.getInstance("sprite1");
        Player player = Player.getInstance("name");
        Model model = Model.getInstance();
        model.clearEnemies();
        SpiderFactory spiderFactory = SpiderFactory.getInstance();
        SpiderEnemy spider = (SpiderEnemy) spiderFactory.createEnemy(1, 0, 0);
        model.addEnemy(spider);
        avatar.setPosition(0, 0);
        avatar.checkEnemyCollision(model.getRenderedEnemies());
        assertEquals(30, player.getHealth());
    }

    @Test
    public void losesWhenAllHealthLost() {
        Player.clearInstance();
        Model.clearInstance();
        Player player = Player.getInstance("hi");
        Model model = Model.getInstance();
        Avatar avatar = player.getAvatar();
        model.clearEnemies();
        MedusaFactory medusaFactory = MedusaFactory.getInstance();
        MedusaEnemy medusa = (MedusaEnemy) medusaFactory.createEnemy(1, 0, 0);
        model.addEnemy(medusa);
        avatar.setPosition(0, 0);
        avatar.checkEnemyCollision(model.getRenderedEnemies());
        assertEquals(0, player.getHealth());
        assertEquals(false, model.isWinner());
    }
  
    @Test
    public void medusaCollision() {
        Player.clearInstance();
        Avatar avatar = Avatar.getInstance("sprite1");
        Player player = Player.getInstance("name");
        Model model = Model.getInstance();
        model.clearEnemies();
        MedusaFactory medusaFactory = MedusaFactory.getInstance();
        MedusaEnemy medusa = (MedusaEnemy) medusaFactory.createEnemy(1, 0, 0);
        model.addEnemy(medusa);
        avatar.setPosition(0, 0);
        avatar.checkEnemyCollision(model.getRenderedEnemies());
        assertEquals(0, player.getHealth());
    }

    @Test
    public void devilCollision() {
        Player.clearInstance();
        Avatar avatar = Avatar.getInstance("sprite1");
        Player player = Player.getInstance("name");
        Model model = Model.getInstance();
        model.clearEnemies();
        DevilFactory devilFactory = DevilFactory.getInstance();
        DevilEnemy devil = (DevilEnemy) devilFactory.createEnemy(1, 0, 0);
        model.addEnemy(devil);
        avatar.setPosition(0, 0);
        avatar.checkEnemyCollision(model.getRenderedEnemies());
        assertEquals(35, player.getHealth());

    }

    @Test
    public void sirenCollision() {
        Player.clearInstance();
        Avatar avatar = Avatar.getInstance("sprite1");
        Player player = Player.getInstance("name");
        Model model = Model.getInstance();
        model.clearEnemies();
        SirenFactory sirenFactory = SirenFactory.getInstance();
        SirenEnemy siren = (SirenEnemy) sirenFactory.createEnemy(1, 0, 0);
        model.addEnemy(siren);
        avatar.setPosition(0, 0);
        avatar.checkEnemyCollision(model.getRenderedEnemies());
        assertEquals(40, player.getHealth());
    }

    @Test
    public void enemyMoveCloser() {
        Player.clearInstance();
        Avatar avatar = Avatar.getInstance("sprite1");
        Player player = Player.getInstance("name");
        Model model = Model.getInstance();
        SirenFactory sirenFactory = SirenFactory.getInstance();
        SirenEnemy siren = (SirenEnemy) sirenFactory.createEnemy(1, 1, 1);
        model.addEnemy(siren);
        avatar.setPosition(0, 0);
        int x = 1;
        int y = 1;
        Assert.assertNotEquals(siren.getPosX(), x);
        Assert.assertNotEquals(siren.getPosY(), y);
    }

    @Test
    public void playerMovesRight() {
        MapLayout mapLayout = MapLayout.getInstance(2);
        Avatar avatar = Avatar.getInstance("sprite3");
        avatar.setMovementStrategy(new WalkStrategy());
        avatar.applyVector(StandardVectors.RIGHT_VECTOR);
        assertEquals((int) 1, (int) avatar.getMovementVector().getX());
        assertEquals((int) 0, (int) avatar.getMovementVector().getY());
    }

    @Test
    public void playerMovesLeft() {
        MapLayout mapLayout = MapLayout.getInstance(2);
        Avatar avatar = Avatar.getInstance("sprite3");
        avatar.setMovementStrategy(new WalkStrategy());
        avatar.applyVector(StandardVectors.LEFT_VECTOR);
        assertEquals((int) -1, (int) avatar.getMovementVector().getX());
        assertEquals((int) 0, (int) avatar.getMovementVector().getY());
    }
}
