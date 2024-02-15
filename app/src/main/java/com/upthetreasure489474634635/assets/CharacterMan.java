package com.upthetreasure489474634635.assets;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import com.upthetreasure489474634635.R;
import com.upthetreasure489474634635.Ref;

public class CharacterMan {

    Gameview gameView;
    Bitmap flight1, flight2;
    float x, y;
    int width, height;


    CharacterMan(Gameview gameView, Resources res) {

        this.gameView = gameView;

        if (Ref.character == 1) {

            flight1 = BitmapFactory.decodeResource(res, R.drawable.men1_1);
            flight2 = BitmapFactory.decodeResource(res, R.drawable.men1_2);
        } else if (Ref.character == 2) {

            flight1 = BitmapFactory.decodeResource(res, R.drawable.men2_1);
            flight2 = BitmapFactory.decodeResource(res, R.drawable.men2_2);
        } else if (Ref.character == 3) {

            flight1 = BitmapFactory.decodeResource(res, R.drawable.men3_1);
            flight2 = BitmapFactory.decodeResource(res, R.drawable.men3_2);
        } else if (Ref.character == 4) {

            flight1 = BitmapFactory.decodeResource(res, R.drawable.men4_1);
            flight2 = BitmapFactory.decodeResource(res, R.drawable.men4_2);
        } else if (Ref.character == 5) {

            flight1 = BitmapFactory.decodeResource(res, R.drawable.men5_1);
            flight2 = BitmapFactory.decodeResource(res, R.drawable.men5_2);
        } else if (Ref.character == 6) {

            flight1 = BitmapFactory.decodeResource(res, R.drawable.men6_1);
            flight2 = BitmapFactory.decodeResource(res, R.drawable.men6_2);
        } else if (Ref.character == 7) {

            flight1 = BitmapFactory.decodeResource(res, R.drawable.men7_1);
            flight2 = BitmapFactory.decodeResource(res, R.drawable.men7_2);
        } else if (Ref.character == 8) {

            flight1 = BitmapFactory.decodeResource(res, R.drawable.men8_1);
            flight2 = BitmapFactory.decodeResource(res, R.drawable.men8_2);
        } else if (Ref.character == 9) {

            flight1 = BitmapFactory.decodeResource(res, R.drawable.men9_1);
            flight2 = BitmapFactory.decodeResource(res, R.drawable.men9_2);
        } else if (Ref.character == 10) {

            flight1 = BitmapFactory.decodeResource(res, R.drawable.men10_1);
            flight2 = BitmapFactory.decodeResource(res, R.drawable.men10_2);
        } else if (Ref.character == 11) {

            flight1 = BitmapFactory.decodeResource(res, R.drawable.men11_1);
            flight2 = BitmapFactory.decodeResource(res, R.drawable.men11_2);
        }
        width = flight1.getWidth();
        height = flight1.getHeight();
        x = width / 2;
        y = height / 2;
        flight1 = Bitmap.createScaledBitmap(flight1, width, height, false);
        flight2 = Bitmap.createScaledBitmap(flight2, width, height, false);


    }

}