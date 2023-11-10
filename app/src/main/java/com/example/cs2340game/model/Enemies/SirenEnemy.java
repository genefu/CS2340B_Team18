package com.example.cs2340game.model.Enemies;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.core.math.MathUtils;

import com.example.cs2340game.model.Collidable;
import com.example.cs2340game.model.Movable;
import com.example.cs2340game.model.Tile;
import com.example.cs2340game.model.MovementStrategies.Vector;
import com.example.cs2340game.views.MapLayout;

import java.util.HashSet;

public class SirenEnemy extends Enemy implements Movable, Collidable, Comparable<Enemy> {
    final private String SPRITE = "siren";
    private int health;
    private int baseHealth;
    private int baseDefense;
    private int strength;
    private int id;
    private int speed;
    private Vector movementVector;
    private HashSet<Vector> appliedVectors;
    private Direction directionFacing;
    private int posX; //position of center x
    private int posY; //position of center y

    public SirenEnemy(int id, int posX, int posY) {
        this.id = id;
        this.baseHealth = 40;
        this.health = baseHealth;
        this.baseDefense = 0;
        this.strength = 10;
        this.speed = 40;
        movementVector = new Vector();
        appliedVectors = new HashSet<>();
        this.directionFacing = Direction.UP;
        this.posX = ENEMY_SIZE / 2 + posX;
        this.posY = ENEMY_SIZE / 2 + posY;
    }

    @Override
    public void attack() {
        //TO BE IMPLEMENTED
    }

    @Override
    public void applyVector(Vector v) {
        //Log.d("keyPress", "Applied Vector");
        if (appliedVectors.add(v)) {
            movementVector.addVector(v);
            updateDirection();
            updatePosition();
        }
    }

    @Override
    public void removeVector(Vector v) {
        //Log.d("keyPress", "Removed Vector");
        if (appliedVectors.remove(v)) {
            movementVector.subtractVector(v);
            updateDirection();
            updatePosition();
        }
    }

    @Override
    public void clearVectors() {
        appliedVectors.clear();
        movementVector = new Vector();
    }

    @Override
    public void updateDirection() {
        double x = movementVector.getX();
        double y = -movementVector.getY();
        if (y > 0 && x == 0) {
            directionFacing = Movable.Direction.UP;
        } else if (y > 0 && x > 0) {
            directionFacing = Movable.Direction.UP_RIGHT;
        } else if (y == 0 && x > 0) {
            directionFacing = Movable.Direction.RIGHT;
        } else if (y < 0 && x > 0) {
            directionFacing = Movable.Direction.DOWN_RIGHT;
        } else if (y < 0 && x == 0) {
            directionFacing = Movable.Direction.DOWN;
        } else if (y < 0 && x < 0) {
            directionFacing = Movable.Direction.DOWN_LEFT;
        } else if (y == 0 && x < 0) {
            directionFacing = Movable.Direction.LEFT;
        } else if (y > 0 && x < 0) {
            directionFacing = Movable.Direction.UP_LEFT;
        }
    }

    @Override
    public void updatePosition() {
        int[] temp = new int[]{posX, posY};
        posX += (int) (movementVector.getX() * speed);
        posY += (int) (movementVector.getY() * speed);
        //Log.d("collision", "Before: " + posX + " " + posY);
        Collidable.CollisionBox collisionBox;
        collisionBox = checkCollision();
        if (collisionBox != Collidable.CollisionBox.NONE) {
            moveToValidPosition(collisionBox);
        }
        //Log.d("collision", "After: " + posX + " " + posY);
    }

    @Override
    public Collidable.CollisionBox checkCollision() {
        Tile[][] tileMap = MapLayout.getInstance().getTileMap();
        Tile[] coveredTiles = getTileCoverage(tileMap);
        for (int i = 0; i < 4; i++) {
            if (coveredTiles[i].isWall() || coveredTiles[i].isLand()) {
                switch (i) {
                    case 0:
                        return Collidable.CollisionBox.TOP_LEFT;
                    case 1:
                        return Collidable.CollisionBox.TOP_RIGHT;
                    case 2:
                        return Collidable.CollisionBox.BOTTOM_LEFT;
                    case 3:
                        return Collidable.CollisionBox.BOTTOM_RIGHT;
                    default:
                        throw new IllegalArgumentException("Invalid collision box");
                }
            }
        }
        return Collidable.CollisionBox.NONE;
    }

    @Override
    public void moveToValidPosition(Collidable.CollisionBox collisionBox) {
        int baseX;
        int baseY;
        switch (collisionBox) {
            case TOP_LEFT:
                baseX = posX - 32;
                baseY = posY - 32;
                break;
            case TOP_RIGHT:
                baseX = posX + 31;
                baseY = posY - 32;
                break;
            case BOTTOM_LEFT:
                baseX = posX - 32;
                baseY = posY + 31;
                break;
            case BOTTOM_RIGHT:
                baseX = posX + 31;
                baseY = posY + 31;
                break;
            default:
                return;
        }
        //Log.d("collision", "BaseXY: " + baseX + " " + baseY);
        //Log.d("collision", collisionBox.toString() + " " + directionFacing.toString());
        switch (directionFacing) {
            case UP:
                posY += Tile.TILE_SIZE - baseY % Tile.TILE_SIZE;
                break;
            case UP_RIGHT:
                posX -= baseX % Tile.TILE_SIZE + 1;
                posY += Tile.TILE_SIZE - baseY % Tile.TILE_SIZE;
                break;
            case RIGHT:
                posX -= baseX % Tile.TILE_SIZE + 1;
                break;
            case DOWN_RIGHT:
                posX -= baseX % Tile.TILE_SIZE + 1;
                posY -= baseY % Tile.TILE_SIZE + 1;
                break;
            case DOWN:
                posY -= baseY % Tile.TILE_SIZE + 1;
                break;
            case DOWN_LEFT:
                posX += Tile.TILE_SIZE - baseX % Tile.TILE_SIZE;
                posY -= baseY % Tile.TILE_SIZE + 1;
                break;
            case LEFT:
                posX += Tile.TILE_SIZE - baseX % Tile.TILE_SIZE;
                break;
            case UP_LEFT:
                posX += Tile.TILE_SIZE - baseX % Tile.TILE_SIZE;
                posY += Tile.TILE_SIZE - baseY % Tile.TILE_SIZE;
                break;
            default:
                break;
        }
    }

    @Override
    // Returns the set of all tiles that the avatar is currently on top of
    public Tile[] getTileCoverage(Tile[][] tileMap) {
        Tile[] tilesCovered = new Tile[4];
        int top = MathUtils.clamp((int) ((posY - 32) / Tile.TILE_SIZE), 0, 19);
        int left = MathUtils.clamp((int) ((posX - 32) / Tile.TILE_SIZE), 0, 33);

        int bottom = MathUtils.clamp((int) ((posY + 31) / Tile.TILE_SIZE), 0, 19);
        int right = MathUtils.clamp((int) ((posX + 31) / Tile.TILE_SIZE), 0, 33);
        tilesCovered[0] = (tileMap[top][left]); // Top left corner
        tilesCovered[1] = (tileMap[top][right]); // Top right corner
        tilesCovered[2] = (tileMap[bottom][left]); // Bottom left corner
        tilesCovered[3] = (tileMap[bottom][right]); // Bottom right corner
        return tilesCovered;
    }

    @Override
    public int getDistance(int x, int y) {
        return (int) Math.sqrt((posX - x) * (posX - x) + (posY - y) * (posY - y));
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getStrength() { return strength; }

    @Override
    public int getPosX() {
        return posX;
    }

    @Override
    public int getPosY() {
        return posY;
    }

    @Override
    // Getter for player name
    public int getID() {
        return id;
    }

    @Override
    public Bitmap getBitmap(Context context) {
        Resources res = context.getResources();
        return Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, res.getIdentifier(SPRITE,
                "drawable", context.getPackageName())), Tile.TILE_SIZE, Tile.TILE_SIZE, false);
    }

    @Override
    public int compareTo(Enemy enemy) {
        return (this.strength - enemy.getStrength()) * 100 + this.id;
    }
}