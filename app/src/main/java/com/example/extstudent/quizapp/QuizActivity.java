package com.example.extstudent.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {

    //Widgets
    private ArrayList<QuizQuestion> quizQuestionList = null;
    QuizQuestion currentQuestion = null;
    int currentQuestionNumber = 1;

    private int currentScore = 0;
    private int maxScore = 0;

    TextView textViewQuestionTitle = null;
    TextView textViewQuestion = null;
    TextView textViewScore = null;

    RadioGroup radioGroupQuestion = null;
    RadioButton radioButtonA = null;
    RadioButton radioButtonB = null;
    RadioButton radioButtonC = null;
    RadioButton radioButtonD = null;

    Button buttonSubmit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Initialize widgets.
        this.textViewQuestionTitle = (TextView)findViewById(R.id.textViewQuestionTitle);    // The question title
        this.textViewQuestion = (TextView)findViewById(R.id.textViewQuestion);              // The question asked.
        this.textViewScore = (TextView)findViewById(R.id.textViewScore);                    // The current score.

        // Initialize the radio buttons for question multiple choice answers.
        radioGroupQuestion = (RadioGroup)findViewById(R.id.radioGroup);             // Create a group for radio buttons.
        radioButtonA = (RadioButton)findViewById(R.id.ChoiceA);
        radioButtonB = (RadioButton)findViewById(R.id.ChoiceB);
        radioButtonC = (RadioButton)findViewById(R.id.ChoiceC);
        radioButtonD = (RadioButton)findViewById(R.id.ChoiceD);

        // Initialize the submit question answer along with the callback function.
        buttonSubmit = (Button)findViewById(R.id.buttonSubmit);
        this.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Step 1: Call validateAnswer() to check if the current answer (provided by user) is correct (valid).
                // Step 2: Compare the current question number with the max score. If the case where the current question number
                //         is less than the max score (we have more questions to go.
                // Step 3: If we still have questions to ask: (1) Set the currentQuestion variable, (2) prompt the user with setQuestionView.
                //         and (3) remember to increment the currentQuestionNumber.
                // Step 4: If there are now questions left in the array, transition the user to the results activity.
                if(validateAnswer()) {
                    if(currentQuestionNumber < maxScore) {
                        ///////////////////////////////////////////////////////////////////////////////////////////////////////
                        // TO-DO: Set currentQuestion to the next question (because we want to process what is next).
                        // You can set the reference from the quizQuestionList.
                        // Use currentQuestionNumber as the index (remember to increment this at the end so that we can fetch the next question index).
                        ///////////////////////////////////////////////////////////////////////////////////////////////////////
                    }
                    else {
                        Intent inent = new Intent(com.example.extstudent.quizapp.QuizActivity.this, com.example.extstudent.quizapp.ResultsActivity.class);
                        // No questions left to ask. Transition the user to the results page.

                        ///////////////////////////////////////////////////////////////////////////////////////////////////////
                        // TO-DO: Create a new intent that will transition to user to next ResultsActivity.
                        // HINT: We want to pass in some extra values for the results class to use. So use something like:
                        //       intent.putExtra(...);
                        ///////////////////////////////////////////////////////////////////////////////////////////////////////
                    }
                }
            }
        });

         // Initiate all questions first.
        this.initQuestions();

        // Ask use the first question when this activity loads.
        this.setQuestionView(this.currentQuestion);
    }



    private void initQuestions() {
        this.quizQuestionList = new ArrayList<QuizQuestion>();  // Initialize our question array.
        //Question 1
        QuizQuestion FirstQuestion = new QuizQuestion();
        FirstQuestion.setQuestion("What country offered presidency to Albert Einstein in 1952?");
        FirstQuestion.setChoiceA("Egypt");
        FirstQuestion.setChoiceB("Israel");
        FirstQuestion.setChoiceC("Germany");
        FirstQuestion.setChoiceD("France");
        FirstQuestion.setCorrectAnswer("Israel");
        quizQuestionList.add(FirstQuestion);
        //Question 2
        QuizQuestion SecondQuestion = new QuizQuestion();
        SecondQuestion.setQuestion("The scientific unit named after Sir Isaac Newton measures what?");
        quizQuestionList.add(SecondQuestion);
        //Question 3
        QuizQuestion ThirdQuestion = new QuizQuestion();
        ThirdQuestion.setQuestion("What inorganic molecule is produced by lightning?");
        //Question 4
        QuizQuestion FourthQuestion = new QuizQuestion();
        FourthQuestion.setQuestion("What color do you get from adding all the colors of light?");


        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        // TO-DO: Set your currentQuestion to point to your first question here (uncomment out the code below).
        ///////////////////////////////////////////////////////////////////////////////////////////////////////

        //this.currentQuestion = /*Your first question*/;

        // Set the current, score, and total question size.
        this.currentQuestionNumber = 1;
        this.maxScore = this.quizQuestionList.size();
        this.currentScore = 0;
    }

    private void setQuestionView(QuizQuestion quizQuestion) {
        if(quizQuestion == null) {
            Log.d("[DEBUG]", "quizQuestion is null in setQuestionView.");
            return;
        }

        // Clear the radio button checks just encase it was been set previously.
        radioGroupQuestion.clearCheck();

        // Loads the current question view.
        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        // TO-DO: Set the UI view (all your widgets) with the current QuizQuestion passed in.
        // Hint: Use your getters from the QuizQuestion class to get the values stored there.
        // Set the following widget text:
        // - The question text (i.e. Question #1).
        // - Set the question to ask.
        // - Set all for radio button text.
        // - Set the score view with the current score (remeber to convert integer to string).
        //   Example: Score: 2
        ///////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    private boolean validateAnswer() {
        // Validate the current answer selected.
        int selectedButtonId = this.radioGroupQuestion.getCheckedRadioButtonId();
        if(selectedButtonId != -1) {
            String answerSelectedStr = ((RadioButton)findViewById(selectedButtonId)).getText().toString();

            if (currentQuestion.isCorrectAnswer(answerSelectedStr)) {
                // Answer is correct.
                Log.d("ANSWER: ", "Correct");
                currentScore++;
            }
            else {
                Log.d("ANSWER: ", "Incorrect");
            }
            return true; // Allow to continue to next question.
        }
        else {
            // No answer selected.
            Toast.makeText(getApplicationContext(), "Please Select An Answer",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}