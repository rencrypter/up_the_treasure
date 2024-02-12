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

import io.paperdb.Paper;

public class FlyingCharacterView extends View {


    private Bitmap man[] = new Bitmap[2];
    //
    private long manX;
    private long manY;
    private int manSpeed;
    private int canvasWidth, canvasHeight;
    private Bitmap background;
    private Paint scorePaint = new Paint();


    Boolean touch = false;
    //
    private int score;

    //yellow balls
    private int coinX, coinY;
    private int coinSpeed = 5;
    //    private Paint yellowPaint = new Paint();
    Bitmap coinDrawable;

    //green balls
    private int giftX, giftY, greenSpeed = 2;
    //    private Paint greenPaint = new Paint();
    Bitmap giftsDrawable;
    private int[] tileX, tileY;

    Bitmap tile;

    private int numTiles = 5;

    private float angle = 0;
    private int radius = 100;

    //
    private boolean isClimbing = false;
    private int[] tileSpeeds;
    //
    private Matrix matrix;
    float characterAngle;
    int characterXPos, characterYPos;
    int tileCenterX, tileCenterY;
    //
    private boolean moveTiles = false;

    Bitmap lava;

    public FlyingCharacterView(Context context) {
        super(context);

        background = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
        lava = BitmapFactory.decodeResource(getResources(), R.drawable.ic_lawa_draw);
        matrix = new Matrix();
        //
        Paper.init(context);
        //
        characterLoading();

        tile = BitmapFactory.decodeResource(getResources(), R.drawable.tile);
        //

        //
        coinDrawable = BitmapFactory.decodeResource(getResources(), R.drawable.coin);

        giftsDrawable = BitmapFactory.decodeResource(getResources(), R.drawable.gift1);
        //


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

    private void giftAchieDrawables() {
        if (Ref.countForAchiev == 5) {
            giftsDrawable = BitmapFactory.decodeResource(getResources(), R.drawable.gift1);

        } else if (Ref.countForAchiev == 10) {
            giftsDrawable = BitmapFactory.decodeResource(getResources(), R.drawable.gift2);

        } else if (Ref.countForAchiev == 15) {
            giftsDrawable = BitmapFactory.decodeResource(getResources(), R.drawable.gift3);

        } else if (Ref.countForAchiev == 20) {
            giftsDrawable = BitmapFactory.decodeResource(getResources(), R.drawable.gift4);

        } else if (Ref.countForAchiev == 25) {
            giftsDrawable = BitmapFactory.decodeResource(getResources(), R.drawable.gift5);

        } else if (Ref.countForAchiev == 30) {
            giftsDrawable = BitmapFactory.decodeResource(getResources(), R.drawable.gift6);

        } else if (Ref.countForAchiev == 35) {
            giftsDrawable = BitmapFactory.decodeResource(getResources(), R.drawable.gift7);

        } else if (Ref.countForAchiev == 40) {
            giftsDrawable = BitmapFactory.decodeResource(getResources(), R.drawable.gift8);

        } else if (Ref.countForAchiev == 45) {
            giftsDrawable = BitmapFactory.decodeResource(getResources(), R.drawable.gift9);

        } else if (Ref.countForAchiev == 50) {
            giftsDrawable = BitmapFactory.decodeResource(getResources(), R.drawable.gift10);

        } else if (Ref.countForAchiev == 55) {
            giftsDrawable = BitmapFactory.decodeResource(getResources(), R.drawable.gift11);

        } else if (Ref.countForAchiev == 60) {
            giftsDrawable = BitmapFactory.decodeResource(getResources(), R.drawable.gift12);

        }
    }

    private void characterLoading() {

        if (Ref.character == 1) {
            man[0] = BitmapFactory.decodeResource(getResources(), R.drawable.men1_1);
            man[1] = BitmapFactory.decodeResource(getResources(), R.drawable.men1_2);
        } else if (Ref.character == 2) {
            man[0] = BitmapFactory.decodeResource(getResources(), R.drawable.men2_1);
            man[1] = BitmapFactory.decodeResource(getResources(), R.drawable.men2_2);
        } else if (Ref.character == 3) {
            man[0] = BitmapFactory.decodeResource(getResources(), R.drawable.men3_1);
            man[1] = BitmapFactory.decodeResource(getResources(), R.drawable.men3_2);
        } else if (Ref.character == 4) {
            man[0] = BitmapFactory.decodeResource(getResources(), R.drawable.men4_1);
            man[1] = BitmapFactory.decodeResource(getResources(), R.drawable.men4_2);
        } else if (Ref.character == 5) {
            man[0] = BitmapFactory.decodeResource(getResources(), R.drawable.men5_1);
            man[1] = BitmapFactory.decodeResource(getResources(), R.drawable.men5_2);
        } else if (Ref.character == 6) {
            man[0] = BitmapFactory.decodeResource(getResources(), R.drawable.men6_1);
            man[1] = BitmapFactory.decodeResource(getResources(), R.drawable.men6_2);
        } else if (Ref.character == 7) {
            man[0] = BitmapFactory.decodeResource(getResources(), R.drawable.men7_1);
            man[1] = BitmapFactory.decodeResource(getResources(), R.drawable.men7_2);
        } else if (Ref.character == 8) {
            man[0] = BitmapFactory.decodeResource(getResources(), R.drawable.men8_1);
            man[1] = BitmapFactory.decodeResource(getResources(), R.drawable.men8_2);
        } else if (Ref.character == 9) {
            man[0] = BitmapFactory.decodeResource(getResources(), R.drawable.men9_1);
            man[1] = BitmapFactory.decodeResource(getResources(), R.drawable.men9_2);
        } else if (Ref.character == 10) {
            man[0] = BitmapFactory.decodeResource(getResources(), R.drawable.men10_1);
            man[1] = BitmapFactory.decodeResource(getResources(), R.drawable.men10_2);
        } else if (Ref.character == 11) {
            man[0] = BitmapFactory.decodeResource(getResources(), R.drawable.men11_1);
            man[1] = BitmapFactory.decodeResource(getResources(), R.drawable.men11_2);
        }

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
        manY = manY + manSpeed;
        manSpeed = Math.min(manSpeed + 2, 20); // Limit speed

        int minmanY = man[0].getHeight();
        int maxmanY = canvasHeight - man[0].getHeight();
        manY = Math.max(minmanY, Math.min(maxmanY, manY)); // Constrain to canvas bounds

        if (manY < minmanY) {
            manY = minmanY;
        }
        if (manY > maxmanY) {
            manY = maxmanY;
        }
        manSpeed = manSpeed + 2;
        // Calculate the center of the tile
        tileCenterX = tileX[0] + tile.getWidth() / 2;
        tileCenterY = tileY[0] + tile.getHeight() / 2;

        // Calculate the character's position revolving around the center of the tile
        characterAngle = (float) Math.toRadians(angle);
        characterXPos = tileCenterX + (int) (radius * Math.cos(characterAngle)) - man[0].getWidth() / 2;
        characterYPos = tileCenterY + (int) (radius * Math.sin(characterAngle)) - man[0].getHeight() / 2;

        // Update the angle for the next frame
        angle += 2;

        // Apply rotation to the character drawable
        matrix.reset();
        matrix.postTranslate(characterXPos, characterYPos);
        matrix.postRotate((float) Math.toDegrees(characterAngle), characterXPos + man[0].getWidth() / 2, characterYPos + man[0].getHeight() / 2);

        // Draw the character at the calculated position with rotation
        if (isClimbing) {
            canvas.drawBitmap(man[1], manX, manY, null);
//            isClimbing = false;
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

        giftAchieDrawables();


        //gifts
        giftsAndAchievOnCanvas(canvas, minmanY, minmanY);
        //
        canvas.drawText("Score : " + score, 20, 60, scorePaint);//scorepaint


        int lavaHeight = lava.getHeight();
        canvas.drawBitmap(lava, 0, canvasHeight - lavaHeight, null);
    }

    private void giftsAndAchievOnCanvas(Canvas canvas, int minmanY, int maxmanY) {

        if (Ref.countForAchiev == 5) {
            drawGiftAndAchie(canvas, minmanY, maxmanY);
            Ref.achi1 = true;
            Paper.book().write("achi1", Ref.achi1);
        } else if (Ref.countForAchiev == 10) {
            drawGiftAndAchie(canvas, minmanY, maxmanY);
            Ref.achi2 = true;
            Paper.book().write("achi2", Ref.achi2);
        } else if (Ref.countForAchiev == 15) {
            drawGiftAndAchie(canvas, minmanY, maxmanY);
            Ref.achi3 = true;
            Paper.book().write("achi3", Ref.achi3);
        } else if (Ref.countForAchiev == 20) {
            drawGiftAndAchie(canvas, minmanY, maxmanY);
            Ref.achi4 = true;
            Paper.book().write("achi4", Ref.achi4);
        } else if (Ref.countForAchiev == 25) {
            drawGiftAndAchie(canvas, minmanY, maxmanY);
            Ref.achi5 = true;
            Paper.book().write("achi5", Ref.achi5);
        } else if (Ref.countForAchiev == 30) {
            drawGiftAndAchie(canvas, minmanY, maxmanY);
            Ref.achi6 = true;
            Paper.book().write("achi6", Ref.achi6);
        } else if (Ref.countForAchiev == 35) {
            drawGiftAndAchie(canvas, minmanY, maxmanY);
            Ref.achi7 = true;
            Paper.book().write("achi7", Ref.achi7);
        } else if (Ref.countForAchiev == 40) {
            drawGiftAndAchie(canvas, minmanY, maxmanY);
            Ref.achi8 = true;
            Paper.book().write("achi8", Ref.achi8);
        } else if (Ref.countForAchiev == 45) {
            drawGiftAndAchie(canvas, minmanY, maxmanY);
            Ref.achi9 = true;
            Paper.book().write("achi9", Ref.achi9);
        } else if (Ref.countForAchiev == 50) {
            drawGiftAndAchie(canvas, minmanY, maxmanY);
            Ref.achi10 = true;
            Paper.book().write("achi10", Ref.achi10);
        } else if (Ref.countForAchiev == 55) {
            drawGiftAndAchie(canvas, minmanY, maxmanY);
            Ref.achi11 = true;
            Paper.book().write("achi11", Ref.achi11);
        } else if (Ref.countForAchiev == 60) {
            drawGiftAndAchie(canvas, minmanY, maxmanY);
            Ref.achi12 = true;
            Paper.book().write("achi12", Ref.achi12);
        }

        Paper.book().write("counting", Ref.countForAchiev);
    }

    private void drawGiftAndAchie(Canvas canvas, int minmanY, int maxmanY) {
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
            isClimbing = true;
            Ref.countForAchiev++;
//            // Set the man's position to the tap coordinates
//            manX = (int) event.getX() - man[0].getWidth() / 2;
//            manY = (int) event.getY() - man[0].getHeight() / 2;

            double deltaX = tileCenterX - manX - man[0].getWidth() / 2;
            double deltaY = tileCenterY - manY - man[0].getHeight() / 2;

            double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

            long tangentX = Math.round(deltaX / distance);
            long tangentY = Math.round(deltaY / distance);

            // Stop circular motion by setting characterAngle to 0 or a constant value
            characterAngle = 0;

            // Immediately update position using the calculated tangent vector
            manX = tileCenterX + tangentX * radius - man[0].getWidth() / 2;
            manY = tileCenterY + tangentY * radius - man[0].getHeight() / 2;

            manSpeed = 0;
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
