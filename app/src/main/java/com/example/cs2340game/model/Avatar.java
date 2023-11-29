package com.example.cs2340game.model;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.core.math.MathUtils;

import com.example.cs2340game.model.Enemies.Enemy;
import com.example.cs2340game.model.MovementStrategies.Collidable;
import com.example.cs2340game.model.MovementStrategies.Movable;
import com.example.cs2340game.model.MovementStrategies.MovementStrategy;
import com.example.cs2340game.model.MovementStrategies.Vector;
import com.example.cs2340game.model.MovementStrategies.WalkStrategy;
import com.example.cs2340game.model.Powerups.BasicPowerUp;
import com.example.cs2340game.model.Powerups.PowerUp;
import com.example.cs2340game.model.Powerups.PowerUpDecorator;
import com.example.cs2340game.model.Powerups.PowerUpSprite;
import com.example.cs2340game.model.Powerups.RangeUpDecorator;
import com.example.cs2340game.model.Powerups.ScoreUpDecorator;
import com.example.cs2340game.model.Powerups.SpeedUpDecorator;
import com.example.cs2340game.views.MapLayout;

import java.util.HashSet;
import java.util.TreeSet;

public class Avatar implements Movable, Collidable {
    private static Avatar avatarInstance;
    public static final int AVATAR_SIZE = 64;
    private Vector movementVector;
    private MovementStrategy movementStrategy;
    private HashSet<Vector> appliedVectors;
    private String sprite;
    private boolean isOnExit; // if avatar is on the exit tile
    private Direction directionFacing;
    private int invincibilityTime; // cannot lose health from attack
    private int posX; //position of center x
    private int posY; //position of center y
    private double speed;


    private Avatar(String sprite, MovementStrategy movementStrategy) {
        movementVector = new Vector();
        this.movementStrategy = movementStrategy;
        appliedVectors = new HashSet<>();
        this.sprite = sprite;
        this.directionFacing = Direction.UP;
        this.posX = AVATAR_SIZE / 2 + Tile.TILE_SIZE * 17;
        this.posY = AVATAR_SIZE / 2 + Tile.TILE_SIZE * 17;
        this.invincibilityTime = 0;
        isOnExit = false;
        this.speed = 1;
    }

    public static Avatar getInstance(String sprite) {
        if (avatarInstance == null) {
            synchronized (Model.class) {
                synchronized (Player.class) {
                    if (avatarInstance == null) {
                        avatarInstance = new Avatar(sprite, new WalkStrategy());
                    }
                }
            }
        }
        return avatarInstance;
    }

    public static Avatar getInstance() {
        if (avatarInstance == null) {
            synchronized (Player.class) {
                if (avatarInstance == null) {
                    throw new IllegalArgumentException(
                            "Avatar doesn't exist, needs a sprite parameter");
                }
            }
        }
        return avatarInstance;
    }

    //for restarting game
    public void resetAvatar() {
        movementVector = new Vector();
        appliedVectors = new HashSet<>();
        this.directionFacing = Direction.UP;
        this.invincibilityTime = 0;
        isOnExit = false;
    }

    //for use in JUnits
    public static void clearInstance() {
        avatarInstance = null;
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
            directionFacing = Direction.UP;
        } else if (y > 0 && x > 0) {
            directionFacing = Direction.UP_RIGHT;
        } else if (y == 0 && x > 0) {
            directionFacing = Direction.RIGHT;
        } else if (y < 0 && x > 0) {
            directionFacing = Direction.DOWN_RIGHT;
        } else if (y < 0 && x == 0) {
            directionFacing = Direction.DOWN;
        } else if (y < 0 && x < 0) {
            directionFacing = Direction.DOWN_LEFT;
        } else if (y == 0 && x < 0) {
            directionFacing = Direction.LEFT;
        } else if (y > 0 && x < 0) {
            directionFacing = Direction.UP_LEFT;
        }
    }

    public void setMovementStrategy(MovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }

    public void updatePosition() {
        int[] temp = new int[]{posX, posY};
        movementStrategy.move(movementVector, temp, speed);
        posX = (int) (temp[0]);
        posY = (int) (temp[1]);
        //Log.d("collision", "Before: " + posX + " " + posY);
        Collidable.CollisionBox collisionBox;
        collisionBox = checkCollision();
        if (collisionBox != Collidable.CollisionBox.NONE) {
            moveToValidPosition(collisionBox);
        }
        //Log.d("collision", "After: " + posX + " " + posY);
    }

    public void updateInvincibility() {
        if (invincibilityTime > 0) {
            invincibilityTime--;
        }
    }

    public void checkEnemyCollision(TreeSet<Enemy> enemies) {
        if (invincibilityTime > 0) {
            return;
        }
        for (Enemy e: enemies) {
            if (e.getDistance(posX, posY) < 60) {
                invincibilityTime += 40;
                Player.getInstance().removeHealth(e.getStrength());
            }
        }
    }

    public int checkPowerUpCollision(HashSet<PowerUpSprite> powerUps) {
        for (PowerUpSprite p: powerUps) {
            if (p.getDistance(posX, posY) < 60) {
                int powerUp = p.checkPowerUp();
                if (powerUp == 0) {
                    if (sprite.equals("sprite1")) {
                        setSprite("sprite1blue");
                    } else if (sprite.equals("sprite2")) {
                        setSprite("sprite2blue");
                    } else if (sprite.equals("sprite3")) {
                        setSprite("sprite3blue");
                    }
                    return p.checkPowerUp();
                } else if (powerUp == 1) {
                    if (sprite.equals("sprite1")) {
                        setSprite("sprite1yellow");
                    } else if (sprite.equals("sprite2")) {
                        setSprite("sprite2yellow");
                    } else if (sprite.equals("sprite3")) {
                        setSprite("sprite3yellow");
                    }
                    return p.checkPowerUp();
                } else if (powerUp == 2) {
                    //TODO Range increase
                    return p.checkPowerUp();
                }
            }
        }
        return -1;
    }

    public CollisionBox checkCollision() {
        Tile[][] tileMap = MapLayout.getInstance().getTileMap();
        Tile[] coveredTiles = getTileCoverage(tileMap);
        for (int i = 0; i < 4; i++) {
            if (coveredTiles[i].isExit()) {
                isOnExit = true;
            }
        }
        for (int i = 0; i < 4; i++) {
            if (coveredTiles[i].isWall()) {
                switch (i) {
                case 0: return CollisionBox.TOP_LEFT;
                case 1: return CollisionBox.TOP_RIGHT;
                case 2: return CollisionBox.BOTTOM_LEFT;
                case 3: return CollisionBox.BOTTOM_RIGHT;
                default: throw new IllegalArgumentException("Invalid collision box");
                }
            }
        }
        return CollisionBox.NONE;
    }

    public void moveToValidPosition(CollisionBox collisionBox) {
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

    public boolean isOnExit() {
        return isOnExit;
    }

    public void resetOnExit() {
        isOnExit = false;
    }

    public void setPosition(int x, int y) {
        posX = AVATAR_SIZE / 2 + x;
        posY = AVATAR_SIZE / 2 + y;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public String getSprite() {
        return sprite;
    }
    public Vector getMovementVector() {
        return movementVector;
    }

    public double getSpeed() {
        return speed;
    }

    public Direction getDirectionFacing() {
        return directionFacing;
    }
    public String getDirectionFacingString() {
        return directionFacing.toString();
    }

    public void setMovementVector(Vector v) {
        movementVector = v;
    }

    public void setSpeed(double speed) { this.speed = speed; }

    public int getInvincibilityTime() {
        return invincibilityTime;
    }

    public Bitmap getBitMap(Context context) {
        Resources res = context.getResources();
        return Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, res.getIdentifier(sprite,
                "drawable", context.getPackageName())), Tile.TILE_SIZE, Tile.TILE_SIZE, false);
    }
}
