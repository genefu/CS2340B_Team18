package com.example.cs2340game;

import static org.junit.Assert.assertEquals;

import com.example.cs2340game.model.Avatar;
import com.example.cs2340game.model.Enemies.SpiderEnemy;
import com.example.cs2340game.model.Enemies.SpiderFactory;
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
}
