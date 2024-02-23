package com.upthetreasure489474634635.assets;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.Toast;

import androidx.core.content.res.ResourcesCompat;

import com.upthetreasure489474634635.R;
import com.upthetreasure489474634635.Ref;
import com.upthetreasure489474634635.VibrationEffect;
import com.upthetreasure489474634635.activities.GameOverActivity;
import com.upthetreasure489474634635.activities.MenuScreenActivity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import io.paperdb.Paper;

public class Gameview extends SurfaceView implements Runnable {


    int screenX, screenY;
    int lavaBoundry;
    public static float screenRatioX, screenRatioY;
    public float angle = 0;
    float tangentAngle;

    public static boolean isPlaying = false, isMovingCharacter = false, isCharacterRevolve = false, isCharacterShoot = false;
    //
    private Background background1, dummyLava;
    private CharacterMan character;
    private Mountains[] mountains;

    private HomeButton homeButton;
    private ScoreBoard scoreBoard;

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
    // Assuming you have these lists to keep track of coin positions
    private List<Integer> coinXPositions = new ArrayList<>();
    private List<Integer> coinYPositions = new ArrayList<>();
    private static final int MAX_COINS_ON_SCREEN = 5;
    Coin[] coinss = new Coin[3];
    Bitmap coinDrawable;
    private int coinX, coinY;
    private int coinSpeed = 10;
    Thread thread;
    Random random;
    Matrix matrix;
    private int radius = 100;
    float characterXPos, characterYPos;
    private int collidingMountainIndex = 0;

    private float timeElapsed = 0.0f;
    private float damping = 0.9f;
    private float previousOffset = 0.0f;
    private int lavaHeight = 0; // Initial lava height (adjust as needed)
    private int lavaIncrement = 5; // Amount to increase lava height per tap

    int canvasHeight = 0;

    public static int coins = 0;

    private boolean isTapDown = false;

    private boolean isExpandLavaScheduled = false;

    private Handler handler1 = new Handler();

    public Gameview(Context context, int ScreenX, int ScreenY) {
        super(context);
        //
        Paper.init(context);
        //
        matrix = new Matrix();//matrix
        this.screenX = ScreenX;
        this.screenY = ScreenY;
        //
        screenRatioX = 1920f / screenX;
        screenRatioY = 1080f / screenY;

        dummyLava = new Background(screenX, screenY, getResources());
        background1 = new Background(screenX, screenY, getResources());
//        homeButton = new HomeButton(getContext(), getResources());
        scoreBoard = new ScoreBoard(getContext(), getResources(), screenX, screenY);
        character = new CharacterMan(this, getResources());

        treasure = new Treasure(getResources());
        handler = new Handler();
        //paint
        paint = new Paint();
        paint.setTextSize(128);
        paint.setColor(Color.WHITE);

        //mountains
        mountains = new Mountains[6];

        //
        lavaHeight = background1.lavaHeight;

        for (int i = 0; i < 6; i++) {
            mountains[i] = new Mountains(getResources());
            mountains[i].x = screenX / 2 - mountains[i].width / 2;
            mountains[i].y = (i + 1) * ScreenY / (6 + 1); // Initial positions above screen// 5 is the max mountains
        }
        //coins
        coinDrawable = BitmapFactory.decodeResource(getResources(), R.drawable.coin);


        scorePaint.setColor(Color.parseColor("#FFD735")); // Set color using hex code
// Set font
        Typeface typeface = ResourcesCompat.getFont(getContext(), R.font.lexington_regular); // Assuming R.font.lexington_regular is a valid font resource
        scorePaint.setTypeface(typeface);
        scorePaint.setTextSize(32);
        scorePaint.setTextAlign(Paint.Align.CENTER);
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

            canvasHeight = canvas.getHeight();
            canvas.drawBitmap(background1.background, background1.x, background1.y, paint);

//            homeButton.draw(canvas);


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

            if (coinss[0] == null) {
                for (int i = 0; i < coinss.length; i++) {
                    coinss[i] = new Coin(getResources(), (int) Math.floor(Math.random() * (maxmanY - minmanY) + minmanY), 0);
                }
            }

            for (int i = 0; i < coinss.length; i++) {
                if (coinss[i].isActive()) {
                    coinss[i].updatePosition(coinSpeed);

                    if (hitWithCoinsChecker(coinss[i].getX(), coinss[i].getY())) {
                        coinss[i].deactivate();
                        coins = coins + 1;
                        Ref.score = Ref.score + 1;
                        Paper.book().write("score", Ref.score);
                        coinY = -100;
                    }

                    if (coinss[i].getY() > canvasHeight) {
                        coinss[i].resetPosition((int) Math.floor(Math.random() * (maxmanY - minmanY) + minmanY));
                    }

                    canvas.drawBitmap(coinDrawable, coinss[i].getX(), coinss[i].getY(), null);
                }
            }
//            coinY = coinY + coinSpeed;
//            if (hitWithCoinsChecker(coinX, coinY)) {
//                coins = coins + 1;
//                Ref.score = Ref.score + 1;
//                Paper.book().write("score", Ref.score);
//                coinY = -100;
//            }
//
//            for (int i = 0; i < MAX_COINS_ON_SCREEN; i++) {
//                if (coinY > canvas.getHeight()) {
//                    coinY = 0;
//                    coinX = (int) Math.floor(Math.random() * (maxmanY - minmanY) + minmanY);
//                }
//            }
//
//            canvas.drawBitmap(coinDrawable, coinX, coinY, null);


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
//                Paper.book().write("counting", Ref.currentTreasureIndex);
            }
            canvas.drawBitmap(treasure.treasure[Ref.currentTreasureIndex], treasureX, treasureY, null);
            //
            scoreBoard.draw(canvas);
            //score
            canvas.drawText("" + coins, (canvas.getWidth() - scoreBoard.bitmapWidth) - 30, (scoreBoard.bitmapHeight / 2) + 15, scorePaint);//scorepaint

            lavaBoundry = canvas.getHeight() - background1.lavaHeight;
            canvas.drawBitmap(background1.lavaDummy, background1.x, canvas.getHeight() - dummyLava.lavaDummyHeight, paint);

            drawLava(canvas);

            getHolder().unlockCanvasAndPost(canvas);

        }
    }

    private void moveAndDrawCoins(Canvas canvas) {
        Iterator<Integer> xIterator = coinXPositions.iterator();
        Iterator<Integer> yIterator = coinYPositions.iterator();

        while (xIterator.hasNext() && yIterator.hasNext()) {
            int coinX = xIterator.next();
            int coinY = yIterator.next();

            coinY += coinSpeed;

            if (hitWithCoinsChecker(coinX, coinY)) {
                coins = coins + 1;
                Ref.score = Ref.score + 1;
                Paper.book().write("score", Ref.score);

                coinY = -100;
                // Remove the collected coin by skipping the current iteration
                continue;
            }

            if (coinY > canvas.getHeight()) {
                // Remove coins that have gone off-screen
                xIterator.remove();
                yIterator.remove();
            }
            canvas.drawBitmap(coinDrawable, coinX, coinY, null);

        }
    }

    private void spawnCoins(Canvas canvas) {
        int minmanY = character.height;
        int maxmanY = canvas.getHeight() - character.height;

        for (int i = 0; i < MAX_COINS_ON_SCREEN; i++) {
            if (coinY > canvas.getHeight()) {
                coinY = 0;
                coinX = (int) Math.floor(Math.random() * (maxmanY - minmanY) + minmanY);
                coinXPositions.add(coinX);
                coinYPositions.add(coinY);
            }


        }
    }


    private void drawLava(Canvas canvas) {
        float offsetY = seesawOffset(timeElapsed);

        // Calculate the effective Y position for the bitmap based on offset
//        float effectiveY = canvasHeight - lavaHeight + offsetY;

        // Draw the lava bitmap with the Y offset
        float originalY = canvasHeight - lavaHeight;
        canvas.drawBitmap(background1.lava, background1.x, originalY + offsetY, paint);
    }

    private float seesawOffset(float time) {
        float frequency = 0.005f; // Increased for faster speed
        float amplitude = 20.0f;
        float newOffset = amplitude * (float) Math.cos(frequency * time);
        float dampedOffset = previousOffset + (newOffset - previousOffset) * damping;
        previousOffset = dampedOffset;
        return dampedOffset;
    }


    private void saveTreasureInDb() {


        if (Ref.currentTreasureIndex == 0) {

            Ref.achi1 = true;
            Ref.achiToShow1 = true;
            Paper.book().write("achi1", Ref.achi1);
        } else if (Ref.currentTreasureIndex == 1) {

            Ref.achi2 = true;
            Ref.achiToShow2 = true;
            Paper.book().write("achi2", Ref.achi2);
        } else if (Ref.currentTreasureIndex == 2) {

            Ref.achi3 = true;
            Ref.achiToShow3 = true;
            Paper.book().write("achi3", Ref.achi3);
        } else if (Ref.currentTreasureIndex == 3) {

            Ref.achi4 = true;
            Ref.achiToShow4 = true;
            Paper.book().write("achi4", Ref.achi4);
        } else if (Ref.currentTreasureIndex == 4) {

            Ref.achi5 = true;
            Ref.achiToShow5 = true;
            Paper.book().write("achi5", Ref.achi5);
        } else if (Ref.currentTreasureIndex == 5) {

            Ref.achi6 = true;
            Ref.achiToShow6 = true;
            Paper.book().write("achi6", Ref.achi6);
        } else if (Ref.currentTreasureIndex == 6) {

            Ref.achi7 = true;
            Ref.achiToShow7 = true;
            Paper.book().write("achi7", Ref.achi7);
        } else if (Ref.currentTreasureIndex == 7) {

            Ref.achi8 = true;
            Ref.achiToShow8 = true;
            Paper.book().write("achi8", Ref.achi8);
        } else if (Ref.currentTreasureIndex == 8) {

            Ref.achi9 = true;
            Ref.achiToShow9 = true;
            Paper.book().write("achi9", Ref.achi9);
        } else if (Ref.currentTreasureIndex == 9) {

            Ref.achi10 = true;
            Ref.achiToShow10 = true;
            Paper.book().write("achi10", Ref.achi10);
        } else if (Ref.currentTreasureIndex == 10) {

            Ref.achi11 = true;
            Ref.achiToShow11 = true;
            Paper.book().write("achi11", Ref.achi11);
        } else if (Ref.currentTreasureIndex == 11) {

            Ref.achi12 = true;
            Ref.achiToShow12 = true;
            Paper.book().write("achi12", Ref.achi12);
        }


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

        // Increment timeElapsed to drive the animation
        timeElapsed += 16; // 16 milliseconds per frame
        if (timeElapsed > 1000000) {
            // Reset timeElapsed to avoid overflow (adjust as needed)
            timeElapsed = 0.0f;
        }

        for (int i = 0; i < 6; i++) {
            mountains[i].y += mountains[i].speed;

            if (mountains[i].y > screenY) { // Change the condition to check if it's off the bottom
                mountains[i].y = -mountains[i].height; // Reset position above the screen
                mountains[i].x = screenX / 2 - getRandomX(mountains[i].width);
            }
        }


        // Check for lava collision after drawing and updating
        if (checkLavaCollision()) {
            gameOver();
        }


    }

    private boolean checkLavaCollision() {
        Rect characterRect = new Rect((int) characterXPos, (int) characterYPos,
                (int) (characterXPos + character.width), (int) (characterYPos + character.height));
        Rect lavaRect = new Rect(0, canvasHeight - lavaHeight, screenX, canvasHeight);

        if (Rect.intersects(characterRect, lavaRect)) {
            return true;
        }

        return false; // No collision
    }


    private int getCollidingMountainIndex() {
        // Find the index of the mountain with which the projectile collides
        // You can use more specific logic based on your game requirements

        for (int i = 0; i < mountains.length; i++) {

            //

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
        //vibrate
        if (Ref.isVibrateEnabled) {
            VibrationEffect.VibrationEffect(getContext());
        }
        // Show a toast message on the UI thread
        post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), R.string.game_over, Toast.LENGTH_SHORT).show();
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
            Thread.sleep(16);
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

        if (isPlaying) {
            // Handle outside taps here
            if (!isMovingCharacter) {

                if (!isExpandLavaScheduled) {
                    handler1.postDelayed(expandLavaRunnable, 1000); // 1000 milliseconds = 1 second
                    isExpandLavaScheduled = true;
                }
                isCharacterRevolve = true;
                isMovingCharacter = true;
                Ref.countForAchiev++;
                moveCharacTowardsTangent(event.getX(), event.getY());
                // Limit lava height to canvas height

                angle = 0;

                // Return true to consume the event
                return true;
            }
        }

        // Return false for unhandled events
        return false;
    }

    private Runnable expandLavaRunnable = new Runnable() {
        @Override
        public void run() {
            expandLavaHeight(12); // Or pass the desired expansionValue
            isExpandLavaScheduled = false;
        }
    };

    private void expandLavaHeight(int expansionValue) {
        // Set boundaries for lava height
        lavaHeight = Math.min(lavaHeight + expansionValue, screenY);
        // Ensure lavaHeight doesn't go below 0
        lavaHeight = Math.max(lavaHeight, 0);
    }

    private void moveCharacTowardsTangent(float targetX, float targetY) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                float speed = 30; // Adjust the speed here
                collidingMountainIndex = getCollidingMountainIndex(); // Assuming this updates correctly

// Use the correct colliding mountain index
                float dx, dy;
                if (collidingMountainIndex >= 0) {
                    dx = characterXPos - mountains[collidingMountainIndex].x - mountains[collidingMountainIndex].width / 2;
                    dy = characterYPos - mountains[collidingMountainIndex].y - mountains[collidingMountainIndex].height / 2;
                } else {
                    dx = characterXPos - mountains[0].x - mountains[0].width / 2;
                    dy = characterYPos - mountains[0].y - mountains[0].height / 2;
                }
                // Calculate the tangent angle

                tangentAngle = (float) Math.atan2(dy, dx);
//                Log.d("TAGtan", "run: "+tangentAngle);
                float moveX = (float) (speed * Math.cos(tangentAngle));
                float moveY = (float) (speed * Math.sin(tangentAngle));

                while (characterXPos > 0 && characterXPos < screenX && characterYPos > 0 && characterYPos < screenY) {
                    if (!isCharacterShoot) {
                        characterXPos += moveX;
                        characterYPos += moveY;
                        draw();

                        if (getCollidingMountainIndex() != collidingMountainIndex) {
                            if (checkProjectileMountainCollision()) {
                                expandLavaHeight(-15);
                                // Collision detected, trigger character revolving
                                angle = 0;
                                // Store the colliding mountain's index for reference during revolving
                                collidingMountainIndex = getCollidingMountainIndex();
                                break;
                            }
                        }// Check for collisions with walls
                        if (characterXPos < 0 || characterXPos + character.width > screenX) {


                            // Hit a wall (left or right)
                            if (characterXPos < 0) {
//                                isCharacterShoot = true;
                                // Hit left wall
                                hitTheWall(moveX, moveY, true);
                            } else {
//                                isCharacterShoot = true;
                                // Hit right wall
                                hitTheWall(moveX, moveY, false);
                            }


                            break;
                        }

                        try {
                            Thread.sleep(16); // Adjust the sleep time for smoother animation
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
//                    break;
                    }
                }
                isCharacterRevolve = false;
                angle = 0;
                isMovingCharacter = false;
            }
        }).start();
    }

    private void hitTheWall(float moveX, float moveY, boolean left) {

        // Reflect character off the walls
        tangentAngle = -tangentAngle;
        moveX = (float) (30 * Math.cos(tangentAngle));
        moveY = (float) (30 * Math.sin(tangentAngle));

        // Move the character in a linear motion after reflection
        while (characterYPos >= 0 && characterYPos <= canvasHeight) {
            if (!isCharacterShoot) {
                if (left) {
                    characterXPos -= moveX;
                    characterYPos -= moveY;
                } else {
                    characterXPos += moveX;
                    characterYPos += moveY;
                }
                draw();


                if (checkProjectileMountainCollision()) {
                    // Collision detected, trigger character revolving
                    expandLavaHeight(-15);
                    isCharacterRevolve = false;
                    angle = 0;
                    isMovingCharacter = false;
                    // Store the colliding mountain's index for reference during revolving
                    collidingMountainIndex = getCollidingMountainIndex();
                    break;
                }

                // Add collision detection logic here if needed
                if (characterXPos < 0 || characterXPos + character.width > screenX) {


                    if (characterXPos < 0) {
                        // Hit left wall
                        hitTheWall(moveX, moveY, true);
                    } else {
                        // Hit right wall
                        hitTheWall(moveX, moveY, false);
                    }

                }
                try {
                    Thread.sleep(16); // Adjust the sleep time for smoother animation
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }


}
