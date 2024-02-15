package com.upthetreasure489474634635;

import android.content.Context;
import android.os.Vibrator;

public class VibrationEffect {


    private static Vibrator vibrator;

    // Initialize Vibrator
    public static void VibrationEffect(Context context) {
        vibrateEffect(context);
    }

    private static void vibrateEffect(Context context) {

        vibrator = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);

        if (vibrator.hasVibrator()) {
            // Vibrate for 500 milliseconds
            vibrator.vibrate(500);
        }
    }

}

