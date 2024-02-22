package com.upthetreasure489474634635.activities;

import static com.upthetreasure489474634635.SplashScreenActivity.isMyServiceRunning;
import static com.upthetreasure489474634635.assets.Gameview.coins;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.upthetreasure489474634635.R;
import com.upthetreasure489474634635.Ref;
import com.upthetreasure489474634635.SoundsClass;
import com.upthetreasure489474634635.VibrationEffect;
import com.upthetreasure489474634635.databinding.ActivityGameOverBinding;
import com.upthetreasure489474634635.databinding.ActivityGameplayBinding;
import com.upthetreasure489474634635.services.BgMusicService;

public class GameOverActivity extends AppCompatActivity {

    ActivityGameOverBinding binding;

    @Override
    protected void onPause() {
        super.onPause();
        if (isMyServiceRunning(BgMusicService.class, GameOverActivity.this)) {
            BgMusicService.onPause();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isMyServiceRunning(BgMusicService.class, GameOverActivity.this)) {
            BgMusicService.onResume();
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGameOverBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //


        binding.scoreTxt.setText(String.valueOf(coins));
        //
        achieve_display();
        //
        binding.returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Ref.isSoundEnabled){
                    SoundsClass.playButtonClickSound(GameOverActivity.this);
                }
//                if(Ref.isVibrateEnabled){
//                    VibrationEffect.VibrationEffect(GameOverActivity.this);
//                }
                startActivity(new Intent(GameOverActivity.this, GameplayActivity.class));
                finish();
            }
        });
        ///
        binding.menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Ref.isSoundEnabled){
                    SoundsClass.playButtonClickSound(GameOverActivity.this);
                }
//                if(Ref.isVibrateEnabled){
//                    VibrationEffect.VibrationEffect(GameOverActivity.this);
//                }
                startActivity(new Intent(GameOverActivity.this, MenuScreenActivity.class));
                finish();
            }
        });
    }

    private void achieve_display() {


        if(Ref.achiToShow12){
            binding.achieImg.setImageResource(R.drawable.ic_achie_gallery_12);
            binding.achieImg.setVisibility(View.VISIBLE);
            Ref.achiToShow12 = false;
        } else if (Ref.achiToShow11) {
            binding.achieImg.setImageResource(R.drawable.ic_achie_gallery_11);
            binding.achieImg.setVisibility(View.VISIBLE);
            Ref.achiToShow11 = false;
        }else if (Ref.achiToShow10) {
            binding.achieImg.setImageResource(R.drawable.ic_achie_gallery_10);
            binding.achieImg.setVisibility(View.VISIBLE);
            Ref.achiToShow10 = false;
        }else if (Ref.achiToShow9) {
            binding.achieImg.setImageResource(R.drawable.ic_achie_gallery_9);
            binding.achieImg.setVisibility(View.VISIBLE);
            Ref.achiToShow9 = false;
        }else if (Ref.achiToShow8) {
            binding.achieImg.setImageResource(R.drawable.ic_achie_gallery_8);
            binding.achieImg.setVisibility(View.VISIBLE);
            Ref.achiToShow8 = false;
        }else if (Ref.achiToShow7) {
            binding.achieImg.setImageResource(R.drawable.ic_achie_gallery_7);
            binding.achieImg.setVisibility(View.VISIBLE);
            Ref.achiToShow7 = false;
        }else if (Ref.achiToShow6) {
            binding.achieImg.setImageResource(R.drawable.ic_achie_gallery_6);
            binding.achieImg.setVisibility(View.VISIBLE);
            Ref.achiToShow6 = false;
        }else if (Ref.achiToShow5) {
            binding.achieImg.setImageResource(R.drawable.ic_achie_gallery_5);
            binding.achieImg.setVisibility(View.VISIBLE);
            Ref.achiToShow5 = false;
        }else if (Ref.achiToShow4) {
            binding.achieImg.setImageResource(R.drawable.ic_achie_gallery_4);
            binding.achieImg.setVisibility(View.VISIBLE);
            Ref.achiToShow4 = false;
        }else if (Ref.achiToShow3) {
            binding.achieImg.setImageResource(R.drawable.ic_achie_gallery_3);
            binding.achieImg.setVisibility(View.VISIBLE);
            Ref.achiToShow3 = false;
        }else if (Ref.achiToShow2) {
            binding.achieImg.setImageResource(R.drawable.ic_achie_gallery_2);
            binding.achieImg.setVisibility(View.VISIBLE);
            Ref.achiToShow2 = false;
        }else if (Ref.achiToShow1) {
            binding.achieImg.setImageResource(R.drawable.ic_achie_gallery_1);
            binding.achieImg.setVisibility(View.VISIBLE);
            Ref.achiToShow1 = false;
        }
    }
}