package com.guy.class24b_and_2_3;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class Activity_Trivia extends AppCompatActivity {

    private AppCompatImageView trivia_IMG_question;
    private AppCompatImageView[] trivia_IMG_hearts;
    private MaterialTextView trivia_LBL_score;
    private MaterialButton trivia_BTN_green;
    private MaterialButton trivia_BTN_red;

    private GameManager gameManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);

        findViews();
        gameManager = new GameManager(10, 3);


        updateLivesUI();
        nextQuestion();

        trivia_BTN_green.setOnClickListener(v -> answered(true));
        trivia_BTN_red.setOnClickListener(v -> answered(false));

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
        gameManager.nextQuestion();

        if (gameManager.getLives() == 0) {
            lose();
            return;
        } else if (gameManager.noMoreQuestions()) {
            win();
            return;
        }

        int image = gameManager.getCurrentImage();
        trivia_IMG_question.setImageResource(image);
    }

    private void lose() {
        Toast.makeText(this, "You lose", Toast.LENGTH_SHORT).show();
        gameDone();
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

        trivia_IMG_hearts = new AppCompatImageView[] {
                findViewById(R.id.trivia_IMG_heart1),
                findViewById(R.id.trivia_IMG_heart2),
                findViewById(R.id.trivia_IMG_heart3),
                findViewById(R.id.trivia_IMG_heart4),
        };
    }
}