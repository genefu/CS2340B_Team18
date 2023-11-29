package com.example.cs2340game.model.Enemies;

import static com.example.cs2340game.model.MovementStrategies.StandardVectors.DOWNLEFT_VECTOR;
import static com.example.cs2340game.model.MovementStrategies.StandardVectors.DOWNRIGHT_VECTOR;
import static com.example.cs2340game.model.MovementStrategies.StandardVectors.DOWN_VECTOR;
import static com.example.cs2340game.model.MovementStrategies.StandardVectors.LEFT_VECTOR;
import static com.example.cs2340game.model.MovementStrategies.StandardVectors.RIGHT_VECTOR;
import static com.example.cs2340game.model.MovementStrategies.StandardVectors.UPLEFT_VECTOR;
import static com.example.cs2340game.model.MovementStrategies.StandardVectors.UPRIGHT_VECTOR;
import static com.example.cs2340game.model.MovementStrategies.StandardVectors.UP_VECTOR;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.core.math.MathUtils;

import com.example.cs2340game.model.Avatar;
import com.example.cs2340game.model.Model;
import com.example.cs2340game.model.MovementStrategies.Collidable;
import com.example.cs2340game.model.MovementStrategies.EnemyMovable;
import com.example.cs2340game.model.MovementStrategies.Movable;
//import com.example.cs2340game.model.MovementStrategies.MovementStrategy;
//import com.example.cs2340game.model.MovementStrategies.SprintStrategy;
import com.example.cs2340game.model.MovementStrategies.WalkStrategy;
import com.example.cs2340game.model.Tile;
import com.example.cs2340game.model.MovementStrategies.Vector;
import com.example.cs2340game.views.MapLayout;

import java.util.HashSet;
public class DevilEnemy extends Enemy implements Movable,
        Collidable, Comparable<Enemy>, EnemyMovable {
    private final String sprite = "devil";

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
    private Avatar avatar;
    private WalkStrategy walkStrategy;
    private Model model;

    public DevilEnemy(int id, int posX, int posY) {
        this.id = id;
        this.baseHealth = 200;
        this.health = baseHealth;
        this.baseDefense = 10;
        this.strength = 15;
        this.speed = 12;
        movementVector = new Vector();
        appliedVectors = new HashSet<>();
        this.directionFacing = Direction.UP;
        this.posX = ENEMY_SIZE / 2 + posX;
        this.posY = ENEMY_SIZE / 2 + posY;
        this.avatar = Avatar.getInstance();
        this.walkStrategy = new WalkStrategy();
        model = Model.getInstance();
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
            if (coveredTiles[i].isWall() || coveredTiles[i].isWater()) {
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
        return (int) Math.sqrt((posX - x) * (posX - x) + (posY - y) * (posY - y)); //gets the distance
    }

    @Override
    public int getHealth() {
        return health;
    } //gets health

    @Override
    public int getStrength() { //gets strength
        return strength; }

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
    } //get player id

    @Override
    public Bitmap getBitmap(Context context) { //gets the bitmap for the game
        Resources res = context.getResources();
        return Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, res.getIdentifier(sprite,
                "drawable", context.getPackageName())), Tile.TILE_SIZE, Tile.TILE_SIZE, false);
    }

    @Override
    public int compareTo(Enemy enemy) {
        return (this.strength - enemy.getStrength()) * 100 + this.id;
    }

    @Override
    public void movement() {
        if (avatar.getPosX() < this.getPosX() + 192 && avatar.getPosX() > this.getPosX() - 192) {
            if (avatar.getPosY()
                    < this.getPosX() + 192 && avatar.getPosY() > this.getPosY() - 192) {
                combatMovement();
            }
        } else {
            basicMovement();
        }
    }

    @Override
    public void combatMovement() {
        if (avatar.getPosX() < this.getPosX() && avatar.getPosY() < this.getPosY()) {
            movementVector = DOWNLEFT_VECTOR;
        } else if (avatar.getPosX() < this.getPosX() && avatar.getPosY() > this.getPosY()) {
            movementVector = UPLEFT_VECTOR;
        } else if (avatar.getPosX() > this.getPosX() && avatar.getPosY() < this.getPosY()) {
            movementVector = DOWNRIGHT_VECTOR;
        } else if (avatar.getPosX() > this.getPosX() && avatar.getPosY() > this.getPosY()) {
            movementVector = UPRIGHT_VECTOR;
        } else if (avatar.getPosX() == this.getPosX() && avatar.getPosY() < this.getPosY()) {
            movementVector = DOWN_VECTOR;
        } else if (avatar.getPosX() == this.getPosX() && avatar.getPosY() > this.getPosY()) {
            movementVector = UP_VECTOR;
        } else if (avatar.getPosX() < this.getPosX() && avatar.getPosY() == this.getPosY()) {
            movementVector = LEFT_VECTOR;
        } else if (avatar.getPosX() > this.getPosX() && avatar.getPosY() == this.getPosY()) {
            movementVector = RIGHT_VECTOR;
        }
        int[] temp = new int[]{posX, posY};
        walkStrategy.move(movementVector, temp, 1);
        posX = temp[0];
        posY = temp[1];
        updatePosition();
        updateDirection();
    }

    @Override
    public void basicMovement() {
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
        default:
            break;
        }
        if (randomMovement > 95) {
            int[] temp = new int[]{posX, posY};
            Double speed = 1.0;
            if (model.getDifficulty() == Model.Difficulty.EASY) {
                speed = 0.7;
            } else if (model.getDifficulty() == Model.Difficulty.HARD) {
                speed = 1.3;
            }
            walkStrategy.move(movementVector, temp, speed);
            posX = temp[0];
            posY = temp[1];
            updateDirection();
            updatePosition();
        }
    }

    public void setPosX(int x) {
        posX = x;
    }
    public void setPosY(int y) {
        posY = y;
    }
}
