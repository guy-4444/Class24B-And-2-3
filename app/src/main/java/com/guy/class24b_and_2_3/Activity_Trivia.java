package com.guy.class24b_and_2_3;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.android.material.textview.MaterialTextView;

public class Activity_Trivia extends AppCompatActivity {

    private AppCompatImageView trivia_IMG_question;
    private AppCompatImageView[] trivia_IMG_hearts;
    private MaterialTextView trivia_LBL_score;
    private MaterialButton trivia_BTN_green;
    private MaterialButton trivia_BTN_red;
    private LinearProgressIndicator trivia_PRG_progress;

    private GameManager gameManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);

        findViews();
        gameManager = new GameManager(12, 3);


        updateLivesUI();
        nextQuestion();

        trivia_BTN_green.setOnClickListener(v -> answered(true));
        trivia_BTN_red.setOnClickListener(v -> answered(false));

        trivia_PRG_progress.setMax(gameManager.getNumOfQuestions());
    }

    private void openAdvertisementDialog() {
        new MaterialAlertDialogBuilder(this)
                .setTitle("No lives")
                .setMessage("watch ad for extra live")
                .setPositiveButton("Yes", (dialog, which) -> showVideoAd())
                .setNegativeButton("No", (dialog, which) -> noVideoAd())
        .show();
    }

    private void showVideoAd() {
        gameManager.addExtraLive();
        updateLivesUI();
        updateQuestionUI();
    }

    private void noVideoAd() {
        gameDone();
    }


    private void answered(boolean greenClicked) {
        Log.d("pttt", "answered clicked: " + greenClicked);



        if (gameManager.isCorrect(greenClicked)) {
            gameManager.incrementScore();
        } else {
            gameManager.decreaseLive();
        }

        trivia_LBL_score.setText("" + gameManager.getScore());
        updateLivesUI();

        nextQuestion();
    }

    private void updateLivesUI() {
        int SZ = trivia_IMG_hearts.length;

        for (int i = 0; i < SZ; i++) {
            trivia_IMG_hearts[i].setVisibility(View.VISIBLE);
        }

        for (int i = 0; i < SZ - gameManager.getLives(); i++) {
            trivia_IMG_hearts[SZ - i - 1].setVisibility(View.INVISIBLE);
        }
    }

    private void nextQuestion() {
        if (gameManager.getLives() == 0) {
            lose();
            return;
        }

        gameManager.nextQuestion();

        if (gameManager.noMoreQuestions()) {
            win();
            return;
        }

        updateQuestionUI();
    }

    private void updateQuestionUI() {
        int image = gameManager.getCurrentImage();
        trivia_IMG_question.setImageResource(image);
        trivia_PRG_progress.setProgress(gameManager.getCurrentIndex() + 1);
    }

    private void lose() {
        Toast.makeText(this, "You lose", Toast.LENGTH_SHORT).show();
        openAdvertisementDialog();
    }

    private void win() {
        Toast.makeText(this, "You win " + gameManager.getScore(), Toast.LENGTH_SHORT).show();
        gameDone();
    }

    private void gameDone() {
        Log.d("pttt", "Game Done");
        trivia_IMG_question.setVisibility(View.INVISIBLE);
        trivia_BTN_green.setEnabled(false);
        trivia_BTN_red.setEnabled(false);
        finish();
    }


    private void findViews() {
        trivia_IMG_question = findViewById(R.id.trivia_IMG_question);
        trivia_LBL_score = findViewById(R.id.trivia_LBL_score);
        trivia_BTN_green = findViewById(R.id.trivia_BTN_green);
        trivia_BTN_red = findViewById(R.id.trivia_BTN_red);
        trivia_PRG_progress = findViewById(R.id.trivia_PRG_progress);

        trivia_IMG_hearts = new AppCompatImageView[] {
                findViewById(R.id.trivia_IMG_heart1),
                findViewById(R.id.trivia_IMG_heart2),
                findViewById(R.id.trivia_IMG_heart3),
                findViewById(R.id.trivia_IMG_heart4),
        };
    }
}