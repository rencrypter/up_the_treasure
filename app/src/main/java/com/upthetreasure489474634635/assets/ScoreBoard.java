package com.upthetreasure489474634635.assets;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.upthetreasure489474634635.R;

public class ScoreBoard extends View {

    private Bitmap scoreBoard;
    Paint paint;

    //
    int canvasWidth;
    int canvasHeight;

    int bitmapWidth;
    int bitmapHeight;

    public ScoreBoard(Context context, Resources r, int x, int y) {
        super(context);
        this.canvasHeight = y;
        this.canvasWidth = x;
        //
        init(r);
    }

    private void init(Resources r) {
        // Load your button image
        scoreBoard = BitmapFactory.decodeResource(r, R.drawable.score_board);
        bitmapHeight = scoreBoard.getHeight();
        bitmapWidth = scoreBoard.getWidth();
        // Initialize paint for drawing
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {



// Calculate the coordinates to draw the bitmap in the center
        int x = (canvasWidth - bitmapWidth) / 2;
        int y = (canvasHeight - bitmapHeight) / 2;
        canvas.drawBitmap(scoreBoard, x, y, paint);
    }



}
