package com.sflazarus.truecitizenquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView questionTextView;

    private int currentQuestionIndex=0;


    private final Question[] questionBank = new Question[]{
            new Question(R.string.question_amendments, false), //correct: 27
            new Question(R.string.question_constitution, true),
            new Question(R.string.question_declaration, true),
            new Question(R.string.question_independence_rights, true),
            new Question(R.string.question_religion, true),
            new Question(R.string.question_government, false),
            new Question(R.string.question_government_feds, false),
            new Question(R.string.question_government_senators, true),
            //and add more!
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button falseButton = findViewById(R.id.false_button);
        Button trueButton = findViewById(R.id.true_button);
        ImageButton nextButton = findViewById(R.id.next_button);
        ImageButton previousButton=findViewById(R.id.previous_button);
        questionTextView= findViewById(R.id.answer_text_view);

        falseButton.setOnClickListener(this);
        trueButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        previousButton.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.false_button:
                checkAnswer(false);
                break;
            case R.id.true_button:
                checkAnswer(true);
                break;
            case R.id.next_button:
//                go to next question
                currentQuestionIndex=(currentQuestionIndex+1)% questionBank.length;
                updateQuestion();
                break;
            case R.id.previous_button:
                currentQuestionIndex=currentQuestionIndex==0?7:(currentQuestionIndex-1)% questionBank.length;
                updateQuestion();
                break;
            default:
                break;
        }
    }
    private void updateQuestion(){
        Log.d("CQI", "onClick: "+currentQuestionIndex);
        questionTextView.setText(questionBank[currentQuestionIndex].getAnswerResId());
    }

    private void checkAnswer(boolean userChooseCorrect){
        boolean answerIsTrue= questionBank[currentQuestionIndex].isAnswerTrue();
        int toastMessageId;
        if (userChooseCorrect==answerIsTrue)
            toastMessageId=R.string.correct_answer;
        else
            toastMessageId=R.string.wrong_answer;

        Toast.makeText(getApplicationContext(),toastMessageId
            ,Toast.LENGTH_SHORT).show();
    }
}