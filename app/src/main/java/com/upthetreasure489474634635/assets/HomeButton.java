package com.upthetreasure489474634635.assets;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.upthetreasure489474634635.R;
import com.upthetreasure489474634635.Ref;

public class HomeButton extends View {

    private Bitmap homeButton;
    Paint paint;

    public HomeButton(Context context, Resources r) {
        super(context);
        //
        init(r);
    }

    private void init(Resources r) {
        // Load your button image
        homeButton = BitmapFactory.decodeResource(r, R.drawable.home_btn);
        // Initialize paint for drawing
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Draw the button image at the top-left corner
        canvas.drawBitmap(homeButton, 10, 12, paint);
    }



}
