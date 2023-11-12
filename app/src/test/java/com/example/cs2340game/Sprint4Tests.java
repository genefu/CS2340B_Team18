package com.example.cs2340game;

import static org.junit.Assert.assertEquals;

import com.example.cs2340game.model.Avatar;
import com.example.cs2340game.model.Enemies.DevilEnemy;
import com.example.cs2340game.model.Enemies.DevilFactory;
import com.example.cs2340game.model.Enemies.MedusaEnemy;
import com.example.cs2340game.model.Enemies.MedusaFactory;
import com.example.cs2340game.model.Enemies.SpiderEnemy;
import com.example.cs2340game.model.Enemies.SpiderFactory;
import com.example.cs2340game.model.Model;
import com.example.cs2340game.model.Player;

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
    public void medusaCollision() {
        Avatar avatar = Avatar.getInstance("sprite1");
        Player player = Player.getInstance("name");
        Model model = Model.getInstance();
        MedusaFactory medusaFactory = MedusaFactory.getInstance();
        MedusaEnemy medusa = (MedusaEnemy) medusaFactory.createEnemy(1, 0, 0);
        model.addEnemy(medusa);
        avatar.setPosition(0, 0);
        avatar.checkEnemyCollision(model.getRenderedEnemies());
        assertEquals(0, player.getHealth());
    }

    @Test
    public void devilCollision() {
        Avatar avatar = Avatar.getInstance("sprite1");
        Player player = Player.getInstance("name");
        Model model = Model.getInstance();
        DevilFactory devilFactory = DevilFactory.getInstance();
        DevilEnemy devil = (DevilEnemy) devilFactory.createEnemy(1, 0, 0);
        model.addEnemy(devil);
        avatar.setPosition(0, 0);
        avatar.checkEnemyCollision(model.getRenderedEnemies());
        assertEquals(35, player.getHealth());
    }
}
