package com.upthetreasure489474634635.activities;

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

public class GameOverActivity extends AppCompatActivity {

    ActivityGameOverBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGameOverBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //


        binding.scoreTxt.setText(String.valueOf(Ref.score));
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


        if(Ref.achi12){
            binding.achieImg.setImageResource(R.drawable.ic_achie_gallery_12);
            binding.achieImg.setVisibility(View.VISIBLE);
        } else if (Ref.achi11) {
            binding.achieImg.setImageResource(R.drawable.ic_achie_gallery_11);
            binding.achieImg.setVisibility(View.VISIBLE);
        }else if (Ref.achi10) {
            binding.achieImg.setImageResource(R.drawable.ic_achie_gallery_10);
            binding.achieImg.setVisibility(View.VISIBLE);
        }else if (Ref.achi9) {
            binding.achieImg.setImageResource(R.drawable.ic_achie_gallery_9);
            binding.achieImg.setVisibility(View.VISIBLE);
        }else if (Ref.achi8) {
            binding.achieImg.setImageResource(R.drawable.ic_achie_gallery_8);
            binding.achieImg.setVisibility(View.VISIBLE);
        }else if (Ref.achi7) {
            binding.achieImg.setImageResource(R.drawable.ic_achie_gallery_7);
            binding.achieImg.setVisibility(View.VISIBLE);
        }else if (Ref.achi6) {
            binding.achieImg.setImageResource(R.drawable.ic_achie_gallery_6);
            binding.achieImg.setVisibility(View.VISIBLE);
        }else if (Ref.achi5) {
            binding.achieImg.setImageResource(R.drawable.ic_achie_gallery_5);
            binding.achieImg.setVisibility(View.VISIBLE);
        }else if (Ref.achi4) {
            binding.achieImg.setImageResource(R.drawable.ic_achie_gallery_4);
            binding.achieImg.setVisibility(View.VISIBLE);
        }else if (Ref.achi3) {
            binding.achieImg.setImageResource(R.drawable.ic_achie_gallery_3);
            binding.achieImg.setVisibility(View.VISIBLE);
        }else if (Ref.achi2) {
            binding.achieImg.setImageResource(R.drawable.ic_achie_gallery_2);
            binding.achieImg.setVisibility(View.VISIBLE);
        }else if (Ref.achi1) {
            binding.achieImg.setImageResource(R.drawable.ic_achie_gallery_1);
            binding.achieImg.setVisibility(View.VISIBLE);
        }
    }
}