package com.upthetreasure489474634635.assets;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.widget.Toast;

import com.upthetreasure489474634635.R;
import com.upthetreasure489474634635.Ref;
import com.upthetreasure489474634635.activities.GameOverActivity;

import java.util.Random;

import io.paperdb.Paper;

public class Gameview extends SurfaceView implements Runnable {

    int screenX, screenY;
    int lavaBoundry;
    public static float screenRatioX, screenRatioY;
    public float angle = 0;

    boolean isPlaying = false, isMovingCharacter = false, isCharacterRevolve = false, isCharacterShoot = false;
    //
    private Background background1;
    private CharacterMan character;
    private Mountains[] mountains;

    //
    Treasure treasure;
    private int treasureX, treasureY;
    private int treasureSpeed = 8;
    private Handler handler;
    private final long DRAW_INTERVAL = 10000;

    //
    private Paint paint;
    private Paint scorePaint = new Paint();

    //coins
    Bitmap coinDrawable;
    private int coinX, coinY;
    private int coinSpeed = 10;
    Thread thread;
    Random random;
    Matrix matrix;
    private int radius = 100;
    float characterXPos, characterYPos;
    private int collidingMountainIndex = 0;

    public Gameview(Context context, int ScreenX, int ScreenY) {
        super(context);
        matrix = new Matrix();//matrix
        this.screenX = ScreenX;
        this.screenY = ScreenY;
        //
        screenRatioX = 1920f / screenX;
        screenRatioY = 1080f / screenY;

        background1 = new Background(screenX, screenY, getResources());
        character = new CharacterMan(this, getResources());

        treasure = new Treasure(getResources());
        handler = new Handler();
        //paint
        paint = new Paint();
        paint.setTextSize(128);
        paint.setColor(Color.WHITE);


        //mountains
        mountains = new Mountains[5];


        for (int i = 0; i < 5; i++) {
            mountains[i] = new Mountains(getResources());
            mountains[i].x = screenX / 2 - mountains[i].width / 2;
            mountains[i].y = (i + 1) * ScreenY / (5 + 1); // Initial positions above screen// 5 is the max mountains
        }
        //coins
        coinDrawable = BitmapFactory.decodeResource(getResources(), R.drawable.coin);


        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(50);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);

        random = new Random();
    }

    @Override
    public void run() {

        while (isPlaying) {
            draw();
            update();
            sleep();

        }
    }

    private void draw() {
        if (getHolder().getSurface().isValid()) {

            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background1.background, background1.x, background1.y, paint);

            if (!isCharacterRevolve) {
                revolveAroundTheMountain(canvas);
            } else {
                canvas.drawBitmap(character.flight2, characterXPos, characterYPos, paint);
            }
            //
            for (Mountains mountain : mountains)
                canvas.drawBitmap(mountain.getmountain(), mountain.x, mountain.y, paint);

            //character man params
            int minmanY = character.height;
            int maxmanY = canvas.getHeight() - character.height;
            //coins
            coinY = coinY + coinSpeed;
            if (hitWithCoinsChecker(coinX, coinY)) {
                Ref.score = Ref.score + 5;
                Paper.book().write("score", Ref.score);
                coinY = -100;
            }
            if (coinY > canvas.getHeight()) {
                coinY = 0;
                coinX = (int) Math.floor(Math.random() * (maxmanY - minmanY) + minmanY);
            }
            canvas.drawBitmap(coinDrawable, coinX, coinY, null);

            //treasure
            treasureY = treasureY + treasureSpeed;
            if (hitWithCoinsChecker(treasureX, treasureY)) {
                saveTreasureInDb();
                treasureY = -100;
            }
            if (treasureY > canvas.getHeight()) {
                treasureY = 0;
                treasureX = (int) Math.floor(Math.random() * (maxmanY - minmanY) + minmanY);

                Ref.currentTreasureIndex = (Ref.currentTreasureIndex + 1) % treasure.treasure.length;  // Move to the next bitmap in a circular manner
            }
            canvas.drawBitmap(treasure.treasure[Ref.currentTreasureIndex], treasureX, treasureY, null);

            lavaBoundry = canvas.getHeight() - background1.lavaHeight;
            canvas.drawBitmap(background1.lava, background1.x, canvas.getHeight() - background1.lavaHeight, paint);
            //score
            canvas.drawText("Score : " + Ref.score, 20, 60, scorePaint);//scorepaint
            getHolder().unlockCanvasAndPost(canvas);

        }
    }


    private void saveTreasureInDb() {


        if (Ref.currentTreasureIndex == 1) {

            Ref.achi1 = true;
            Paper.book().write("achi1", Ref.achi1);
        } else if (Ref.currentTreasureIndex == 2) {

            Ref.achi2 = true;
            Paper.book().write("achi2", Ref.achi2);
        } else if (Ref.currentTreasureIndex == 3) {

            Ref.achi3 = true;
            Paper.book().write("achi3", Ref.achi3);
        } else if (Ref.currentTreasureIndex == 4) {

            Ref.achi4 = true;
            Paper.book().write("achi4", Ref.achi4);
        } else if (Ref.currentTreasureIndex == 5) {

            Ref.achi5 = true;
            Paper.book().write("achi5", Ref.achi5);
        } else if (Ref.currentTreasureIndex == 6) {

            Ref.achi6 = true;
            Paper.book().write("achi6", Ref.achi6);
        } else if (Ref.currentTreasureIndex == 7) {

            Ref.achi7 = true;
            Paper.book().write("achi7", Ref.achi7);
        } else if (Ref.currentTreasureIndex == 8) {

            Ref.achi8 = true;
            Paper.book().write("achi8", Ref.achi8);
        } else if (Ref.currentTreasureIndex == 9) {

            Ref.achi9 = true;
            Paper.book().write("achi9", Ref.achi9);
        } else if (Ref.currentTreasureIndex == 10) {

            Ref.achi10 = true;
            Paper.book().write("achi10", Ref.achi10);
        } else if (Ref.currentTreasureIndex == 11) {

            Ref.achi11 = true;
            Paper.book().write("achi11", Ref.achi11);
        } else if (Ref.currentTreasureIndex == 12) {

            Ref.achi12 = true;
            Paper.book().write("achi12", Ref.achi12);
        }

        Paper.book().write("counting", Ref.currentTreasureIndex);
    }

    private boolean hitWithCoinsChecker(int x, int y) {
        if (characterXPos < x && x < (characterXPos + character.width) && characterYPos < y && y < (characterYPos + character.height)) {
            return true;
        }
        return false;
    }

    private void revolveAroundTheMountain(Canvas canvas) {

        // Character revolving around the first mountain
        if (collidingMountainIndex >= 0) {
            float mountainX = mountains[collidingMountainIndex].x + mountains[collidingMountainIndex].width / 2;
            float mountainY = mountains[collidingMountainIndex].y + mountains[collidingMountainIndex].height / 2;


            // Create a rotation matrix around the mountain's center
            characterXPos = mountainX + (int) (radius * Math.cos(angle)) - character.width / 2;
            characterYPos = mountainY + (int) (radius * Math.sin(angle)) - character.height / 2;

            // Update the angle for the next frame
            angle += 0.1f;

            // Apply rotation to the character drawable
            matrix.reset();
            matrix.postTranslate(characterXPos, characterYPos);
            matrix.postRotate((float) Math.toDegrees(angle), characterXPos + character.width / 2, characterYPos + character.height / 2);

            canvas.drawBitmap(character.flight1, matrix, paint);
        } else if (collidingMountainIndex == -1) {

        }
        //

    }


    private void update() {

        for (int i = 0; i < 5; i++) {
            mountains[i].y += mountains[i].speed;

            if (mountains[i].y > screenY) { // Change the condition to check if it's off the bottom
                mountains[i].y = -mountains[i].height; // Reset position above the screen
                mountains[i].x = screenX / 2 - getRandomX(mountains[i].width);
            }
        }


        //
        // Check if the character is out of bounds
        if (characterXPos < 0 || characterXPos > screenX || characterYPos < 0 || characterYPos > screenY || characterYPos > lavaBoundry) {
            gameOver();
        }


    }

    private int getCollidingMountainIndex() {
        // Find the index of the mountain with which the projectile collides
        // You can use more specific logic based on your game requirements

        for (int i = 0; i < mountains.length; i++) {
            Rect projectileRect = new Rect((int) characterXPos, (int) characterYPos, (int) (characterXPos + character.width), (int) (characterYPos + character.height));

            Rect mountainRect = new Rect((int) mountains[i].x, (int) mountains[i].y, (int) (mountains[i].x + mountains[i].width), (int) (mountains[i].y + mountains[i].height));

            if (Rect.intersects(projectileRect, mountainRect)) {
                // Collision detected, return the index of the colliding mountain
                return i;
            }
        }

        // No collision detected, return -1
        return -1;
    }

    private boolean checkProjectileMountainCollision() {
        // Assuming you have a projectile, adjust as needed
        // Check if the projectile's position overlaps with any Mountain object's boundaries
        // You can use simple rectangle overlapping checks or more advanced collision detection methods

        // Example simple rectangle overlapping check
        Rect projectileRect = new Rect((int) characterXPos, (int) characterYPos, (int) (characterXPos + character.width), (int) (characterYPos + character.height));

        for (int i = 0; i < mountains.length; i++) {
            Rect mountainRect = new Rect((int) mountains[i].x, (int) mountains[i].y, (int) (mountains[i].x + mountains[i].width), (int) (mountains[i].y + mountains[i].height));

            if (Rect.intersects(projectileRect, mountainRect)) {
                // Collision detected
                return true;
            }
        }

        // No collision detected
        return false;
    }


    private void gameOver() {
        // Stop the game
        isPlaying = false;

        // Show a toast message on the UI thread
        post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), "Game Over", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getContext(), GameOverActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                getContext().startActivity(i);
            }
        });
    }


    public int getRandomX(int maxWidth) {
        // Check for invalid input to avoid errors
        if (maxWidth <= 0) {
            throw new IllegalArgumentException("maxWidth must be positive");
        }

        // Use Random or SecureRandom based on security requirements

        // Ensure the random number is within the valid range
        return random.nextInt(maxWidth);
    }

    private void sleep() {
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    // pause and resume
    public void resume() {
        isPlaying = true;
        thread = new Thread(this);
        thread.start();

    }


    public void pause() {

        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //ontouch Listener
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isMovingCharacter) {
            isCharacterRevolve = true;
            isMovingCharacter = true;
            Ref.countForAchiev++;
            moveCharacTowardsTangent(event.getX(), event.getY());
            angle = 0;
        }

        return true;
    }

    private void moveCharacTowardsTangent(float targetX, float targetY) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                float speed = 30; // Adjust the speed here
                // Calculate the tangent angle
                float dx = characterXPos - mountains[0].x - mountains[0].width / 2;
                float dy = characterYPos - mountains[0].y - mountains[0].height / 2;
                float tangentAngle = (float) Math.atan2(dy, dx);

                float moveX = (float) (speed * Math.cos(tangentAngle));
                float moveY = (float) (speed * Math.sin(tangentAngle));

                while (characterXPos > 0 && characterXPos < screenX && characterYPos > 0 && characterYPos < screenY) {
                    characterXPos += moveX;
                    characterYPos += moveY;
                    draw();

                    if (getCollidingMountainIndex() != collidingMountainIndex) {
                        if (checkProjectileMountainCollision()) {
                            // Collision detected, trigger character revolving

                            angle = 0;
                            // Store the colliding mountain's index for reference during revolving
                            collidingMountainIndex = getCollidingMountainIndex();
                            break;
                        }
                    }

                    try {
                        Thread.sleep(20); // Adjust the sleep time for smoother animation
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                isCharacterRevolve = false;
                angle = 0;
                isMovingCharacter = false;
            }
        }).start();
    }


}
