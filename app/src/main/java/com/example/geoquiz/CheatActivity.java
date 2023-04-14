package com.example.geoquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private static final String TAG = "CheatActivity";
    private static final String EXTRA_ANSWER_IS_TURE = "com.example.geoquiz.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN = "com.example.geoquiz.answer_shown";
    private static final String KEY_SHOW_ANSWER = "ShowAnswer";
    private boolean mAnswerIsTrue;
    private TextView mAnswerTextView;
    private Button mShowAnswer;
    private boolean mAnswerHasShown;

    public static Intent newIntent(Context packageContext, boolean answer_is_true) {
        Intent i = new Intent(packageContext, CheatActivity.class);
        i.putExtra(EXTRA_ANSWER_IS_TURE, answer_is_true);
        return i;
    }

    public static boolean wasAnswerShown(Intent result) {
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TURE, false);

        mAnswerTextView = findViewById(R.id.answerTextView);

        mShowAnswer = findViewById(R.id.showAnswerButton);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                mAnswerHasShown = true;
                setAnswerShownResult(true);
            }
        });

        mAnswerHasShown = false;

        if (savedInstanceState != null) {
            mAnswerHasShown = savedInstanceState.getBoolean(KEY_SHOW_ANSWER, false);
            if (mAnswerHasShown) {
                if (mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                setAnswerShownResult(true);
            }
        }


    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "OnSaveInstanceState");
        savedInstanceState.putBoolean(KEY_SHOW_ANSWER, mAnswerHasShown);
    }

    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }
}