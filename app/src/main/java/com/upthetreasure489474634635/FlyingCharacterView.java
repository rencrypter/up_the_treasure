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

    private Matrix matrix;
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
        tileX = new int[numTiles];
        tileY = new int[numTiles];
        for (int i = 0; i < numTiles; i++) {
            tileX[i] = (int) Math.floor(Math.random() * (600 - tile.getWidth()));
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

        //tiles
        for (int i = 0; i < numTiles; i++) {
            canvas.drawBitmap(tile, tileX[i], tileY[i], null); //apple/red
        }

        //
        int minmanY = man[0].getHeight();
        int maxmanY = canvasHeight - man[0].getHeight();
        manY = manY + manSpeed;

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
//        //tiles
//        tileY = tileY + tileSpeed;
//        if (hitBallChecker(tileX, tileY)) {
//            tileY = -100;
//            Toast.makeText(getContext(), "Game Over", Toast.LENGTH_SHORT).show();
//
////            lifeCounterOfFish--;
////            if (lifeCounterOfFish == 0) {
////                Toast.makeText(getContext(), "Game Over", Toast.LENGTH_SHORT).show();
////
////                //
////                Intent i = new Intent(getContext(), GameOverActivity.class);
////                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
////                getContext().startActivity(i);
////            }
//        }
//
//        if (tileY > canvasHeight) {
//            tileY = 0;
//            tileX = (int) Math.floor(Math.random() * (maxmanY - minmanY) + minmanY);
//        }
//        canvas.drawBitmap(tile, tileX, tileY, null); //apple/red


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

            manSpeed = -22;
        }
        return true;
    }


    // Inner class for Tile
//    private class Tile {
//        private int x, y;
//        private Bitmap tile;
//
//        public Tile(int x, int y) {
//            this.x = x;
//            this.y = y;
//            this.tile = BitmapFactory.decodeResource(getResources(), R.drawable.tile);
//        }
//
//        public void draw(Canvas canvas) {
//            canvas.drawBitmap(tile, x, y, null);
//        }
//
//        public void update(int targetX, int targetY) {
//            // Update the position of the tile towards the target (character)
//            x += (targetX - x) / 20;
//            y += (targetY - y) / 20;
//        }
//    }
}
