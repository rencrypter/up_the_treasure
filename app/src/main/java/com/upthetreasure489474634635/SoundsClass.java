package com.upthetreasure489474634635;

import android.content.Context;
import android.media.MediaPlayer;

public class SoundsClass {

    private static MediaPlayer mediaPlayer;

    public static void playButtonClickSound(Context context) {
        playSound(context, R.raw.kabatsa_btn_sound);
    }
//


    private static void playSound(Context context, int soundResourceId) {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(context, soundResourceId);
        if (mediaPlayer != null) {
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                }
            });
        }
    }
}
