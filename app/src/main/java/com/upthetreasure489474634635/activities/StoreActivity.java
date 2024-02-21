package com.upthetreasure489474634635.activities;

import static com.upthetreasure489474634635.Ref.isSoundEnabled;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
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
    private Integer[] images = {R.drawable.chr_1, R.drawable.chr_2, R.drawable.chr_3, R.drawable.chr_4, R.drawable.chr_5, R.drawable.chr_6, R.drawable.chr_7, R.drawable.chr_8, R.drawable.chr_9, R.drawable.chr_10, R.drawable.chr_11

    };

    private int[] costOfCharacters = {0, 10, 10, 10, 20, 20, 20, 25, 25, 30, 30

    };

    private boolean[] characterPurchased = new boolean[costOfCharacters.length]; // Track purchased characters


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStoreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //
        Paper.init(this);
        //

        binding.scoreTxt.setText(String.valueOf(Ref.score));

        // Initialize purchased character states
        for (int i = 0; i < characterPurchased.length; i++) {
            if (Paper.book().contains("ch" + (i + 1))) {
                characterPurchased[i] = true;
            } else {
                characterPurchased[i] = false;
            }
        }
        //
        if (Ref.character == 1) {
            binding.costBtn.setVisibility(View.GONE);
            binding.selectBtn1.setVisibility(View.GONE);
            binding.selectedBtn.setVisibility(View.VISIBLE);
        }

//        selectButtonVisib();
        updateImageView();

        //cost btn
        binding.costBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((costOfCharacters[currentImageIndex]) <= (Ref.score)) {
                    Ref.score = Ref.score - costOfCharacters[currentImageIndex];
                    //
                    Paper.book().write("score", Ref.score);
                    Log.d("TAGcc", "showPreviousImage: " + currentImageIndex);

                    //save in db
                    Ref.character = currentImageIndex + 1;
                    Paper.book().write("character", Ref.character);
                    //
                    characterPurchased[currentImageIndex] = true; // Mark character as purchased
                    Paper.book().write("ch" + (currentImageIndex + 1), true);
//                    saveCharThatbuy();
                    selectCharacter(currentImageIndex);
                    binding.scoreTxt.setText(String.valueOf(Ref.score));
                    Snackbar.make(view, R.string.buy, 1000).show();

                } else {
                    Snackbar.make(view, R.string.cant_buy, 1000).show();
                }

                if (isSoundEnabled) {
                    SoundsClass.playButtonClickSound(StoreActivity.this);
                }
            }


        });
        //selectCharacterBtn
        binding.selectBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //save in db
                selectCharacter(currentImageIndex);
                if (isSoundEnabled) {
                    SoundsClass.playButtonClickSound(StoreActivity.this);
                }
                Snackbar.make(view, R.string.selected, 1000).show();
            }
        });
        //selected btn
        binding.selectedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isSoundEnabled) {
                    SoundsClass.playButtonClickSound(StoreActivity.this);
                }
                showPreviousImage();
//              selectButtonVisib();
            }
        });

        binding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (isSoundEnabled) {
                    SoundsClass.playButtonClickSound(StoreActivity.this);
                }
                showNextImage();

//                selectButtonVisib();
            }
        });

        binding.backScreenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSoundEnabled) {
                    SoundsClass.playButtonClickSound(StoreActivity.this);
                }
                finish();
            }
        });


    }

    private void selectCharacter(int selectedIndex) {
        // Save the selected character in PaperDB
        Ref.character = selectedIndex + 1;
        Paper.book().write("character", Ref.character);
        // Update the visibility of the selectBtn based on the selected character
        updateSelectedCharacterButtonVisibility();
        // Show a Snackbar or Toast to indicate that the character is selected
        Snackbar.make(binding.getRoot(), R.string.selected, 1000).show();
    }

    private void updateSelectedCharacterButtonVisibility() {
        // Check if selected character is the current one
        boolean isSelectedCharacter = Ref.character == currentImageIndex + 1;

        // Update button visibility based on purchase and selection
        binding.selectedBtn.setVisibility(isSelectedCharacter ? View.VISIBLE : View.GONE);

        binding.selectBtn1.setVisibility(!isSelectedCharacter &&
                characterPurchased[currentImageIndex] ? View.VISIBLE : View.GONE);
        binding.costBtn.setVisibility(!isSelectedCharacter && !characterPurchased[currentImageIndex]
                ? View.VISIBLE : View.GONE);
    }


    private void saveCharThatbuy() {
        if (Ref.character == 1) {
            Ref.ch1 = true;
            Paper.book().write("ch1", Ref.ch1);
        } else if (Ref.character == 2) {
            Ref.ch2 = true;
            Paper.book().write("ch2", Ref.ch2);
        } else if (Ref.character == 3) {
            Ref.ch3 = true;
            Paper.book().write("ch3", Ref.ch3);
        } else if (Ref.character == 4) {
            Ref.ch4 = true;
            Paper.book().write("ch4", Ref.ch4);

        } else if (Ref.character == 5) {
            Ref.ch5 = true;
            Paper.book().write("ch5", Ref.ch5);
        } else if (Ref.character == 6) {
            Ref.ch6 = true;
            Paper.book().write("ch6", Ref.ch6);
        } else if (Ref.character == 7) {
            Ref.ch7 = true;
            Paper.book().write("ch7", Ref.ch7);
        } else if (Ref.character == 8) {
            Ref.ch8 = true;
            Paper.book().write("ch8", Ref.ch8);

        } else if (Ref.character == 9) {
            Ref.ch9 = true;
            Paper.book().write("ch9", Ref.ch9);
        } else if (Ref.character == 10) {
            Ref.ch10 = true;
            Paper.book().write("ch10", Ref.ch10);

        } else if (Ref.character == 11) {
            Ref.ch11 = true;
            Paper.book().write("ch11", Ref.ch11);

        }
    }

    private void selectButtonVisib() {
        if (Ref.character == 1) {
            binding.selectedBtn.setImageResource(R.drawable.selected_btn);
        } else if (Ref.character == 2) {
            binding.selectedBtn.setImageResource(R.drawable.selected_btn);

        } else if (Ref.character == 3) {
            binding.selectedBtn.setImageResource(R.drawable.selected_btn);

        } else if (Ref.character == 4) {
            binding.selectedBtn.setImageResource(R.drawable.selected_btn);

        } else if (Ref.character == 5) {
            binding.selectedBtn.setImageResource(R.drawable.selected_btn);

        } else if (Ref.character == 6) {
            binding.selectedBtn.setImageResource(R.drawable.selected_btn);

        } else if (Ref.character == 7) {
            binding.selectedBtn.setImageResource(R.drawable.selected_btn);

        } else if (Ref.character == 8) {
            binding.selectedBtn.setImageResource(R.drawable.selected_btn);

        } else if (Ref.character == 9) {
            binding.selectedBtn.setImageResource(R.drawable.selected_btn);

        } else if (Ref.character == 10) {
            binding.selectedBtn.setImageResource(R.drawable.selected_btn);

        } else if (Ref.character == 11) {
            binding.selectedBtn.setImageResource(R.drawable.selected_btn);

        }
    }

    private void updateImageView() {

        binding.largerImageView.setImageResource(images[currentImageIndex]);
        binding.costTxt.setText(String.valueOf(costOfCharacters[currentImageIndex]));

        updateButtonVisibility();
    }

    private boolean updateCharacterBtnThatAreBuyAlready() {

        if (Ref.ch1) {
            binding.selectedBtn.setVisibility(View.GONE);
            binding.costBtn.setVisibility(View.GONE);
            binding.selectBtn1.setVisibility(View.VISIBLE);
            return true;
        }
        if (Ref.ch2) {
            binding.selectedBtn.setVisibility(View.GONE);
            binding.costBtn.setVisibility(View.GONE);
            binding.selectBtn1.setVisibility(View.VISIBLE);
            return true;
        }
        if (Ref.ch3) {
            binding.selectedBtn.setVisibility(View.GONE);
            binding.costBtn.setVisibility(View.GONE);
            binding.selectBtn1.setVisibility(View.VISIBLE);
            return true;
        }
        if (Ref.ch4) {
            binding.selectedBtn.setVisibility(View.GONE);
            binding.costBtn.setVisibility(View.GONE);
            binding.selectBtn1.setVisibility(View.VISIBLE);
            return true;
        }
        if (Ref.ch5) {
            binding.selectedBtn.setVisibility(View.GONE);
            binding.costBtn.setVisibility(View.GONE);
            binding.selectBtn1.setVisibility(View.VISIBLE);
            return true;
        }
        if (Ref.ch6) {
            binding.selectedBtn.setVisibility(View.GONE);
            binding.costBtn.setVisibility(View.GONE);
            binding.selectBtn1.setVisibility(View.VISIBLE);
            return true;
        }
        if (Ref.ch7) {
            binding.selectedBtn.setVisibility(View.GONE);
            binding.costBtn.setVisibility(View.GONE);
            binding.selectBtn1.setVisibility(View.VISIBLE);
            return true;
        }
        if (Ref.ch8) {
            binding.selectedBtn.setVisibility(View.GONE);
            binding.costBtn.setVisibility(View.GONE);
            binding.selectBtn1.setVisibility(View.VISIBLE);
            return true;
        }
        if (Ref.ch9) {
            binding.selectedBtn.setVisibility(View.GONE);
            binding.costBtn.setVisibility(View.GONE);
            binding.selectBtn1.setVisibility(View.VISIBLE);
            return true;
        }
        if (Ref.ch10) {
            binding.selectedBtn.setVisibility(View.GONE);
            binding.costBtn.setVisibility(View.GONE);
            binding.selectBtn1.setVisibility(View.VISIBLE);
            return true;
        }
        if (Ref.ch11) {
            binding.selectedBtn.setVisibility(View.GONE);
            binding.costBtn.setVisibility(View.GONE);
            binding.selectBtn1.setVisibility(View.VISIBLE);
            return true;
        }
        return false;
    }

    private void updateButtonVisibility() {

        // Update visibility of next button
//        binding.nextBtn.setVisibility(currentImageIndex < images.length - 1 ? View.VISIBLE : View.INVISIBLE);
        binding.nextBtn.setImageResource(currentImageIndex < images.length - 1 ? R.drawable.btn_next_arrow : R.drawable.btn_next_arrow_hide);

        // Update visibility of previous button
//        binding.backBtn.setVisibility(currentImageIndex > 0 ? View.VISIBLE : View.INVISIBLE);
        binding.backBtn.setImageResource(currentImageIndex > 0 ? R.drawable.btn_back_arrow : R.drawable.btn_back_arrow_hide);


        //


    }

    private void showPreviousImage() {
        if (currentImageIndex > 0) {

            currentImageIndex--;
            updateImageView();

            Log.d("TAGcc", "showPreviousImage: " + currentImageIndex);
            updateSelectedCharacterButtonVisibility();
            if (costOfCharacters[currentImageIndex] == 0) {
                if (Ref.character == 1) {
                    binding.costBtn.setVisibility(View.GONE);
                    binding.selectBtn1.setVisibility(View.GONE);
                    binding.selectedBtn.setVisibility(View.VISIBLE);
                } else {
                    binding.costBtn.setVisibility(View.GONE);
                    binding.selectBtn1.setVisibility(View.VISIBLE);
                    binding.selectedBtn.setVisibility(View.GONE);
                }

            }
        }
    }

    private void showNextImage() {
        if (currentImageIndex < images.length - 1) {

            currentImageIndex++;
            updateImageView();
//            if (characterPurchased[currentImageIndex]) {
//                binding.costBtn.setVisibility(View.GONE);
//                binding.selectBtn1.setVisibility(View.VISIBLE);
//                binding.selectedBtn.setVisibility(View.GONE);
//            } else if (characterPurchased[currentImageIndex] == false) {
//                binding.costBtn.setVisibility(View.VISIBLE);
//                binding.selectBtn1.setVisibility(View.GONE);
//                binding.selectedBtn.setVisibility(View.GONE);
//            }
            updateSelectedCharacterButtonVisibility();

        }
    }

    private void changeCostBtnIntoSelectedBtn() {
        if (currentImageIndex == Ref.character - 1) {

            binding.costBtn.setVisibility(View.GONE);
            binding.selectBtn1.setVisibility(View.GONE);
            binding.selectedBtn.setVisibility(View.VISIBLE);
        } else {

            binding.costBtn.setVisibility(View.VISIBLE);
            binding.selectedBtn.setVisibility(View.GONE);
            binding.selectBtn1.setVisibility(View.GONE);


        }

//
    }


}