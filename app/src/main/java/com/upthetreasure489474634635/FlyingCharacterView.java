package com.upthetreasure489474634635;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class FlyingCharacterView extends View {


    private Bitmap man[] = new Bitmap[2];
    //
    private int manX;
    private int manY;
    private int manSpeed;
    private int canvasWidth, canvasHeight;
    private Bitmap background;
    private Paint scorePaint = new Paint();


    Boolean touch = false;
    //
    private int score;

    //yellow balls
    private int coinX, coinY;
    private int coinSpeed = 12;
    //    private Paint yellowPaint = new Paint();
    Bitmap coinDrawable;

    //green balls
    private int giftX, giftY, greenSpeed = 10;
    //    private Paint greenPaint = new Paint();
    Bitmap giftsDrawable;
    private int[] tileX, tileY;
    private int tileSpeed = 0;
    Bitmap tile;

    private int numTiles = 5;

    private float angle = 0;
    private int radius = 100;

    //
    private boolean isClimbing = false;
    private int[] tileSpeeds;
    //
    private Matrix matrix;
    //
    private boolean moveTiles = false;

    public FlyingCharacterView(Context context) {
        super(context);

        matrix = new Matrix();
        //
        man[0] = BitmapFactory.decodeResource(getResources(), R.drawable.men1_1);
        man[1] = BitmapFactory.decodeResource(getResources(), R.drawable.men1_2);

        tile = BitmapFactory.decodeResource(getResources(), R.drawable.tile);
        //
        background = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
        //
        coinDrawable = BitmapFactory.decodeResource(getResources(), R.drawable.coin);

        //

        giftsDrawable = BitmapFactory.decodeResource(getResources(), R.drawable.gift1);

        // Initialize tile positions randomly
        tileSpeeds = new int[numTiles];
        tileX = new int[numTiles]; // Initialize tileX array
        tileY = new int[numTiles];
        // Set speeds for individual tiles (you can customize this as needed)
        for (int i = 0; i < numTiles; i++) {
            tileSpeeds[i] = 10 + i * 2; // Example: increasing speed for lower tiles
            tileX[i] = (int) Math.floor(Math.random() * (500 - tile.getWidth()));
            tileY[i] = (int) Math.floor(Math.random() * (900 - tile.getHeight()));
        }

        //
        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(50);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);

        manX = (600 - man[0].getWidth()) / 2;
        //
        manY = 550;
        score = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

        canvas.drawBitmap(background, 0, 0, null); //bg

        if (moveTiles) {
            for (int i = 0; i < numTiles; i++) {
                // If a tile has reached the bottom edge of the canvas,
                // move it out of view and adjust other tiles accordingly
                if (tileY[i] > canvasHeight) {
                    numTiles--;
                    for (int j = i; j < numTiles; j++) {
                        tileY[j] = tileY[j + 1] - tile.getHeight();
                        tileX[j] = tileX[j + 1];
                    }
                } else {
                    // Update tileY only when moveTiles is true
                    tileY[i] += tileSpeeds[i];
                }

                canvas.drawBitmap(tile, tileX[i], tileY[i], null);
            }
        }
        // Additional checks to ensure tiles are drawn correctly at game start
        for (int i = 0; i < numTiles; i++) {
            if (tileY[i] <= canvasHeight) { // Check if tile is within canvas bounds
                canvas.drawBitmap(tile, tileX[i], tileY[i], null);
            }
        }
        //
        // Character movement


        //
        int minmanY = man[0].getHeight();
        int maxmanY = canvasHeight - man[0].getHeight();
        manY = manY + manSpeed;
        manY = Math.max(minmanY, Math.min(maxmanY, manY)); // Constrain to canvas bounds
        manSpeed = Math.min(manSpeed + 2, 20); // Limit speed

        if (manY < minmanY) {
            manY = minmanY;
        }
        if (manY > maxmanY) {
            manY = maxmanY;
        }
        manSpeed = manSpeed + 2;
        // Calculate the center of the tile
        int tileCenterX = tileX[0] + tile.getWidth() / 2;
        int tileCenterY = tileY[0] + tile.getHeight() / 2;

        // Calculate the character's position revolving around the center of the tile
        float characterAngle = (float) Math.toRadians(angle);
        int characterXPos = tileCenterX + (int) (radius * Math.cos(characterAngle)) - man[0].getWidth() / 2;
        int characterYPos = tileCenterY + (int) (radius * Math.sin(characterAngle)) - man[0].getHeight() / 2;

        // Update the angle for the next frame
        angle += 2;

        // Apply rotation to the character drawable
        matrix.reset();
        matrix.postTranslate(characterXPos, characterYPos);
        matrix.postRotate((float) Math.toDegrees(characterAngle), characterXPos + man[0].getWidth() / 2, characterYPos + man[0].getHeight() / 2);

        // Draw the character at the calculated position with rotation
        if (touch) {
            canvas.drawBitmap(man[1], matrix, null);
            touch = false;
        } else {
            canvas.drawBitmap(man[0], matrix, null);
        }
        if (isClimbing) {
            canvas.drawBitmap(man[1], matrix, null);
        } else {
            canvas.drawBitmap(man[0], matrix, null);
        }
        //coins
        coinY = coinY + coinSpeed;
        if (hitBallChecker(coinX, coinY)) {
            score = score + 5;
            coinY = -100;
        }
        if (coinY > canvasHeight) {
            coinY = 0;
            coinX = (int) Math.floor(Math.random() * (maxmanY - minmanY) + minmanY);
        }
        canvas.drawBitmap(coinDrawable, coinX, coinY, null);


        //gifts
        giftY = giftY + greenSpeed;
        if (hitBallChecker(giftX, giftY)) {
            score = score + 10;
            giftY = -100;
        }


        if (giftY > canvasHeight) {
            giftY = 0;
            giftX = (int) Math.floor(Math.random() * (maxmanY - minmanY) + minmanY);
        }

        canvas.drawBitmap(giftsDrawable, giftX, giftY, null);
        //
        canvas.drawText("Score : " + score, 20, 60, scorePaint);//scorepaint

    }

    public boolean hitBallChecker(int x, int y) {
        if (manX < x && x < (manX + man[0].getWidth()) && manY < y && y < (manY + man[0].getHeight())) {
            return true;
        }
        return false;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            touch = true;

            if (!isClimbing) {
                // Change the character's drawable when tapped
                // ... (if you want to change the drawable)

                // Move the character up to the next tile
                int currentTile = findCurrentTile(manX + man[0].getWidth() / 2, manY + man[0].getHeight() / 2);
                if (manY + man[0].getHeight() >= tileY[currentTile]) {
                    // Add a new tile at the top
                    tileY[numTiles - 1] -= tile.getHeight();
                    tileY[0] = -tile.getHeight();
                    tileX[0] = (int) Math.floor(Math.random() * (canvasWidth - tile.getWidth()));
                    numTiles++;

////

                    // Move the character to the top of the new tile
                    manY = tileY[currentTile] - man[0].getHeight();
                    //
                    // Remove the bottom tile
                    for (int i = numTiles - 2; i > 0; i--) { // Change the condition to i > 0
                        tileY[i] = tileY[i - 1]; // Also adjust the indices for assignment
                        tileX[i] = tileX[i - 1];
                    }
                    numTiles--;

                    // Increase score
                    score++;

                    isClimbing = true;
                    manSpeed = 0; // Set climbing speed
                    moveTiles = true;
                    // Delay setting isClimbing to false until after the man has reached the top
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            moveTiles = false;
                            isClimbing = false;
                            manSpeed = 0;
                        }
                    }, 500);
                }
            }
        }
        return true;
    }


    private int findCurrentTile(int manX, int manY) {
        for (int i = numTiles - 2; i >= 0; i--) {
            if (manX >= tileX[i] - tile.getWidth() / 2 // Offset by half tile width
                    && manX <= tileX[i] + tile.getWidth() / 2 // Offset by half tile width
                    && manY >= tileY[i] && manY <= (tileY[i] + tile.getHeight())) {
                return i + 1; // Return index of tile above current tile
            }
        }
        return 0;
    }


}
