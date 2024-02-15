package com.upthetreasure489474634635.assets;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.upthetreasure489474634635.R;


public class Treasure {

    Bitmap treasure[] = new Bitmap[12];
    int width, height;

    Treasure(Resources res) {

        treasure[0] = BitmapFactory.decodeResource(res, R.drawable.gift1);


        treasure[1] = BitmapFactory.decodeResource(res, R.drawable.gift2);


        treasure[2] = BitmapFactory.decodeResource(res, R.drawable.gift3);


        treasure[3] = BitmapFactory.decodeResource(res, R.drawable.gift4);

        treasure[4] = BitmapFactory.decodeResource(res, R.drawable.gift5);

        treasure[5] = BitmapFactory.decodeResource(res, R.drawable.gift6);


        treasure[6] = BitmapFactory.decodeResource(res, R.drawable.gift7);

        treasure[7] = BitmapFactory.decodeResource(res, R.drawable.gift8);

        treasure[8] = BitmapFactory.decodeResource(res, R.drawable.gift9);

        treasure[9] = BitmapFactory.decodeResource(res, R.drawable.gift10);


        treasure[10] = BitmapFactory.decodeResource(res, R.drawable.gift11);

        treasure[11] = BitmapFactory.decodeResource(res, R.drawable.gift12);


        //
        width = treasure[0].getWidth();
        height = treasure[0].getHeight();

    }
}
