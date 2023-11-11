package com.example.cs2340game.model.MovementStrategies;

import static com.example.cs2340game.model.StandardVectors.DOWNLEFT_VECTOR;
import static com.example.cs2340game.model.StandardVectors.DOWNRIGHT_VECTOR;
import static com.example.cs2340game.model.StandardVectors.DOWN_VECTOR;
import static com.example.cs2340game.model.StandardVectors.LEFT_VECTOR;
import static com.example.cs2340game.model.StandardVectors.RIGHT_VECTOR;
import static com.example.cs2340game.model.StandardVectors.UPLEFT_VECTOR;
import static com.example.cs2340game.model.StandardVectors.UPRIGHT_VECTOR;
import static com.example.cs2340game.model.StandardVectors.UP_VECTOR;

import com.example.cs2340game.model.Avatar;
import com.example.cs2340game.model.Enemies.Enemy;
import com.example.cs2340game.model.Model;
import com.example.cs2340game.model.MovementStrategies.Movable;
import com.example.cs2340game.model.MovementStrategies.MovementStrategy;
import com.example.cs2340game.model.MovementStrategies.Vector;


public class EnemyMovement implements Movable {

    private Vector movementVector;
    private enemyDirection directionFacing;
    private Enemy enemy;
    private Avatar avatar;
    private Model model;
    private MovementStrategy movementStrategy;
    enum enemyDirection {
        UP, UP_RIGHT, RIGHT, DOWN_RIGHT, DOWN, DOWN_LEFT, LEFT, UP_LEFT
    }

    public EnemyMovement() {
        this.model = Model.getInstance();
        this.avatar = Avatar.getInstance();
        enemy = new Enemy();
        movementStrategy = new WalkStrategy();
    }


    public void movement() {
        if (avatar.getPosX() < enemy.getPosX() + 192 && avatar.getPosX() > enemy.getPosX() - 192) {
            if (avatar.getPosY() < enemy.getPoxY() + 192 && avatar.getPosY() > enemy.getPosY() - 192) {
                combatMovement();
            }
        }
        else {
            basicMovement();
        }
    }

    public void combatMovement() {
        movementStrategy = new SprintStrategy();
        

        updateDirection();
    }
    public void basicMovement() {
        // this method is for the enemy movement when the enemy is not close to the enemy
        double randomMovement = 100 * Math.random();
        int randomVector = (int) (8 * Math.random());
        switch (randomVector) {
            case 0:
                movementVector = UP_VECTOR;
                break;
            case 1:
                movementVector = DOWN_VECTOR;
                break;
            case 2:
                movementVector = LEFT_VECTOR;
                break;
            case 3:
                movementVector = RIGHT_VECTOR;
                break;
            case 4:
                movementVector = UPLEFT_VECTOR;
                break;
            case 5:
                movementVector = UPRIGHT_VECTOR;
                break;
            case 6:
                movementVector = DOWNLEFT_VECTOR;
                break;
            case 7:
                movementVector = DOWNRIGHT_VECTOR;
                break;
        }
        if (randomMovement > 95) {
            int[] temp = new int[]{posX, posY};
            movementStrategy.move(movementVector, temp);
            enemy.setPosX(posX);
            enemy.setPosY(posY);
            updateDirection();
        }
    }

    @Override
    public void applyVector(Vector v) {

    }

    @Override
    public void removeVector(Vector v) {

    }

    @Override
    public void clearVectors() {

    }

    public void updateDirection() {
        double x = movementVector.getX();
        double y = -movementVector.getY();
        if (y > 0 && x == 0) {
            directionFacing = enemyDirection.UP;
        } else if (y > 0 && x > 0) {
            directionFacing = enemyDirection.UP_RIGHT;
        } else if (y == 0 && x > 0) {
            directionFacing = enemyDirection.RIGHT;
        } else if (y < 0 && x > 0) {
            directionFacing = enemyDirection.DOWN_RIGHT;
        } else if (y < 0 && x == 0) {
            directionFacing = enemyDirection.DOWN;
        } else if (y < 0 && x < 0) {
            directionFacing = enemyDirection.DOWN_LEFT;
        } else if (y == 0 && x < 0) {
            directionFacing = enemyDirection.LEFT;
        } else if (y > 0 && x < 0) {
            directionFacing = enemyDirection.UP_LEFT;
        }
    }

    @Override
    public void updatePosition() {

    }

}
