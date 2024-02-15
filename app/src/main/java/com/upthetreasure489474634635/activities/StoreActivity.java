package com.upthetreasure489474634635.activities;

import static com.upthetreasure489474634635.Ref.isSoundEnabled;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.upthetreasure489474634635.R;
import com.upthetreasure489474634635.Ref;
import com.upthetreasure489474634635.SoundsClass;
import com.upthetreasure489474634635.VibrationEffect;
import com.upthetreasure489474634635.databinding.ActivityStoreBinding;

import io.paperdb.Paper;

public class StoreActivity extends AppCompatActivity {

    ActivityStoreBinding binding;

    //
    private int currentImageIndex = 0;
    private Integer[] images = {
            R.drawable.chr_1,
            R.drawable.chr_2,
            R.drawable.chr_3,
            R.drawable.chr_4,
            R.drawable.chr_5,
            R.drawable.chr_6,
            R.drawable.chr_7,
            R.drawable.chr_8,
            R.drawable.chr_9,
            R.drawable.chr_10,
            R.drawable.chr_11

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStoreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //
        Paper.init(this);
        //

        binding.scoreTxt.setText(Ref.score);

        updateImageView();
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSoundEnabled) {
                    SoundsClass.playButtonClickSound(StoreActivity.this);
                }
                if(Ref.isVibrateEnabled){
                    VibrationEffect.VibrationEffect(StoreActivity.this);
                }
                showPreviousImage();
            }
        });

        binding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSoundEnabled) {
                    SoundsClass.playButtonClickSound(StoreActivity.this);
                }
                if(Ref.isVibrateEnabled){
                    VibrationEffect.VibrationEffect(StoreActivity.this);
                }
                showNextImage();
            }
        });

        binding.backScreenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSoundEnabled) {
                    SoundsClass.playButtonClickSound(StoreActivity.this);
                }
                if(Ref.isVibrateEnabled){
                    VibrationEffect.VibrationEffect(StoreActivity.this);
                }
                finish();
            }
        });

        //select btn
        binding.selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ref.character = currentImageIndex+1;
                Paper.book().write("character",Ref.character);
            }
        });
    }

    private void updateImageView() {

        binding.largerImageView.setImageResource(images[currentImageIndex]);
        updateButtonVisibility();
    }

    private void updateButtonVisibility() {

        // Update visibility of next button
//        binding.nextBtn.setVisibility(currentImageIndex < images.length - 1 ? View.VISIBLE : View.INVISIBLE);
        binding.nextBtn.setImageResource(currentImageIndex < images.length - 1 ? R.drawable.btn_next_arrow : R.drawable.btn_next_arrow_hide);

        // Update visibility of previous button
//        binding.backBtn.setVisibility(currentImageIndex > 0 ? View.VISIBLE : View.INVISIBLE);
        binding.backBtn.setImageResource(currentImageIndex > 0 ? R.drawable.btn_back_arrow : R.drawable.btn_back_arrow_hide);
    }

    private void showNextImage() {
        if (currentImageIndex < images.length - 1) {
            currentImageIndex++;
            updateImageView();
        }
    }

    private void showPreviousImage() {
        if (currentImageIndex > 0) {
            currentImageIndex--;
            updateImageView();
        }
    }
}