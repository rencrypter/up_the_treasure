package com.upthetreasure489474634635.assets;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.upthetreasure489474634635.R;

public class Coin {
    private int x;
    private int y;
    private boolean active;

    Bitmap coin1, coin2, coin3;
    int width, height;

    public Coin(Resources res, int initialX, int initialY) {
        this.x = initialX;
        this.y = initialY;
        this.active = true;

        coin1 = BitmapFactory.decodeResource(res, R.drawable.coin);
        coin2 = BitmapFactory.decodeResource(res, R.drawable.coin);
        coin3 = BitmapFactory.decodeResource(res, R.drawable.coin);


        //
        width = coin1.getWidth();
        height = coin1.getHeight();

        coin1 = Bitmap.createScaledBitmap(coin1, width, height, false);
        coin2 = Bitmap.createScaledBitmap(coin2, width, height, false);
        coin3 = Bitmap.createScaledBitmap(coin3, width, height, false);

    }


    //
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isActive() {
        return active;
    }

    public void updatePosition(int speed) {
        if (active) {
            y += speed;
        }
    }

    public void resetPosition(int newX) {
        x = newX;
        y = 0;
        activate(); // Resetting, so activate the coin again
    }

    public void deactivate() {
        active = false;
    }

    public void activate() {
        active = true;
    }

}
