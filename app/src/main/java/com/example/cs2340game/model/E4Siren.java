package com.example.cs2340game.model;

import androidx.core.math.MathUtils;

import com.example.cs2340game.views.MapLayout;

import java.util.HashSet;

public class E4Siren extends Enemy implements Movable, Collidable {
    final private String SPRITE = "siren";
    final private int ENEMY_SIZE = 64;
    private int baseHealth;
    private int baseDefense;
    private int baseStrength;
    private String id;
    private int speed;
    private Vector movementVector;
    private MovementStrategy movementStrategy;
    private HashSet<Vector> appliedVectors;
    private Direction directionFacing;
    private int posX; //position of center x
    private int posY; //position of center y

    public E4Siren(String name, MovementStrategy movementStrategy, int posX, int posY) {
        this.id = id;
        baseHealth = 40;
        this.baseDefense = 0;
        this.baseStrength = 10;
        this.speed = 40;
        movementVector = new Vector();
        this.movementStrategy = movementStrategy;
        appliedVectors = new HashSet<>();
        this.directionFacing = Direction.UP;
        this.posX = posX;
        this.posY = posY;
    }

    public void attack() {
        //TO BE IMPLEMENTED
    }

    public void applyVector(Vector v) {
        //Log.d("keyPress", "Applied Vector");
        if (appliedVectors.add(v)) {
            movementVector.addVector(v);
            updateDirection();
            updatePosition();
        }
    }

    public void removeVector(Vector v) {
        //Log.d("keyPress", "Removed Vector");
        if (appliedVectors.remove(v)) {
            movementVector.subtractVector(v);
            updateDirection();
            updatePosition();
        }
    }

    public void clearVectors() {
        appliedVectors.clear();
        movementVector = new Vector();
    }

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

    public void setMovementStrategy(MovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }

    public void updatePosition() {
        int[] temp = new int[]{posX, posY};
        movementStrategy.move(movementVector, temp);
        posX = temp[0];
        posY = temp[1];
        //Log.d("collision", "Before: " + posX + " " + posY);
        Collidable.CollisionBox collisionBox;
        collisionBox = checkCollision();
        if (collisionBox != Collidable.CollisionBox.NONE) {
            moveToValidPosition(collisionBox);
        }
        //Log.d("collision", "After: " + posX + " " + posY);
    }

    public Collidable.CollisionBox checkCollision() {
        Tile[][] tileMap = MapLayout.getInstance().getTileMap();
        Tile[] coveredTiles = getTileCoverage(tileMap);
        for (int i = 0; i < 4; i++) {
            if (coveredTiles[i].isWall()) {
                switch (i) {
                    case 0: return Collidable.CollisionBox.TOP_LEFT;
                    case 1: return Collidable.CollisionBox.TOP_RIGHT;
                    case 2: return Collidable.CollisionBox.BOTTOM_LEFT;
                    case 3: return Collidable.CollisionBox.BOTTOM_RIGHT;
                    default: throw new IllegalArgumentException("Invalid collision box");
                }
            }
        }
        return Collidable.CollisionBox.NONE;
    }

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

    // Getter for total health
    public int getHealth() {
        return baseHealth;
    }

    // Getter for total strength
    public int getStrength() {
        return baseStrength;
    }

    // Getter for player name
    public String getID() {
        return id;
    }

    // Setter for player avatar
    public void setID(String id) {
        this.id = id;
    }
}
