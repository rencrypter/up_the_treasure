package com.upthetreasure489474634635.assets;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.upthetreasure489474634635.R;

public class Background {

    Bitmap background, lava, lavaDummy;

    int x = 0, y = 0;
    int lavaHeight, lavaDummyHeight;

    Background(int screenX, int screenY, Resources res) {

        background = BitmapFactory.decodeResource(res, R.drawable.bg);
        background = Bitmap.createScaledBitmap(background, screenX, screenY, false);

        lava = BitmapFactory.decodeResource(res, R.drawable.ic_lawa_draw);
        lavaHeight = lava.getHeight();
        lava = Bitmap.createScaledBitmap(lava, lava.getWidth(), lavaHeight, false);

        //lava dummy
        lavaDummy = BitmapFactory.decodeResource(res, R.drawable.ic_lawa_draw);
        lavaDummyHeight = lavaDummy.getHeight();
        lavaDummy = Bitmap.createScaledBitmap(lavaDummy, lavaDummy.getWidth(), lavaDummyHeight, false);

    }
}