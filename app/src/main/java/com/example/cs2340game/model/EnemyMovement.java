package com.example.cs2340game.model;

public class EnemyMovement {

    private Vector movementVector;
    private enemyDirection directionFacing;
    private Enemy enemy;
    private Avatar avatar;
    private Model model;
    private StandardVectors[] standard
    enum enemyDirection {
        UP, UP_RIGHT, RIGHT, DOWN_RIGHT, DOWN, DOWN_LEFT, LEFT, UP_LEFT
    }

    public EnemyMovement() {
        this.model = Model.getInstance();
        this.avatar = Avatar.getInstance();
        enemy = new Enemy();
    }

    public void startingPosition() {
        movementVector = new Vector(Math.random());
        /* this meant to place the enemy in a starting position that is some tiles away from the
        player starting position */
        enemy.posX = avatar.getPosX() + movementVector.getX();
        enemy.posY = avatar.getPosY() + movementVector.getY();
    }

    public void movement() {
        if (avatar.getPosX() < enemy.getPosX + 192 && avatar.getPosX() > enemy.getPosX - 192) {
            if (avatar.getPosY() < enemy.getPoxY() + 192 && avatar.getPosY() > enemy.getPosY - 192) {
                combatMovement();
            }
        }
        else {
            basicMovement();
        }
    }

    public void combatMovement() {

    }
    public void basicMovement() {
        // this method is for the enemy movement when the enemy is not close to the enemy
        double randomMovement = 100 * Math.random();
        int randomVector = (int) (8 * Math.random());
        switch (randomVector) {
            case 0:
                movementVector = new Vector(1, 1);
                break;
            case 1:
                movementVector = new Vector(-1, 1);
                break;
            case 2:
                movementVector = new Vector(1, -1);
                break;
            case 3:
                movementVector = new Vector(-1, -1);
                break;
            case 4:
                movementVector = new Vector(0, 1);
                break;
            case 5:
                movementVector = new Vector(1, 0);
                break;
            case 6:
                movementVector = new Vector(0, -1);
                break;
            case 7:
                movementVector = new Vector(-1, 0);
                break;
        }
        if (randomMovement > 95) {
            enemy.posX += movementVector.getX();
            enemy.posY += movementVector.getY();
        }
    }
}
