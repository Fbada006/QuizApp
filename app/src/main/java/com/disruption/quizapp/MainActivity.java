package com.disruption.quizapp;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /*Variables for the checkboxes*/
    private CheckBox question_1_a;
    private CheckBox question_1_b;
    private CheckBox question_1_c;
    private CheckBox question_1_d;
    private CheckBox question_2_a;
    private CheckBox question_2_b;
    private CheckBox question_2_c;
    private CheckBox question_2_d;

    /*Variables for the EditText*/
    private EditText question_3_edit_text;
    private EditText question_4_edit_text;
    private EditText question_6_edit_text;

    /*Variables for the radioButtons*/
    private RadioButton question_5_a_radio_button;
    private RadioButton question_5_b_radio_button;
    private RadioButton question_7_a_radio_button;
    private RadioButton question_7_b_radio_button;
    private RadioButton question_8_a_radio_button;
    private RadioButton question_8_b_radio_button;

    /*Buttons*/
    private Button submitQuiz;
    private Button retakeQuiz;

    /*Variable for tracking the score of the user*/
    private int userQuizScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Hide the retake survey button when this activity is created just to be sure
        //that the edges of the button is not visible when the user opens this activity
        retakeQuiz = findViewById(R.id.retake_quiz);
        retakeQuiz.setVisibility(View.INVISIBLE);

        //Find all the views by id
        submitQuiz = findViewById(R.id.submit_quiz);
        question_1_a = findViewById(R.id.question_1_a);
        question_1_b = findViewById(R.id.question_1_b);
        question_1_c = findViewById(R.id.question_1_c);
        question_1_d = findViewById(R.id.question_1_d);
        question_2_a = findViewById(R.id.question_2_a);
        question_2_b = findViewById(R.id.question_2_b);
        question_2_c = findViewById(R.id.question_2_c);
        question_2_d = findViewById(R.id.question_2_d);
        question_3_edit_text = findViewById(R.id.question_3_edit_text);
        question_4_edit_text = findViewById(R.id.question_4_edit_text);
        question_6_edit_text = findViewById(R.id.question_6_edit_text);
        question_5_a_radio_button = findViewById(R.id.question_5_a_radio_button);
        question_5_b_radio_button = findViewById(R.id.question_5_b_radio_button);
        question_7_a_radio_button = findViewById(R.id.question_7_a_radio_button);
        question_7_b_radio_button = findViewById(R.id.question_7_b_radio_button);
        question_8_a_radio_button = findViewById(R.id.question_8_a_radio_button);
        question_8_b_radio_button = findViewById(R.id.question_8_b_radio_button);
    }


    /**
     * This method checks for correct answers to the quiz
     *
     * @param view is the button
     */
    public void submitQuiz(View view) {

        //Use an alert dialog to ensure that the user is really sure of submitting the survey for grading
        new AlertDialog.Builder(this)
                .setTitle(R.string.confirm_submission)
                .setMessage(R.string.do_you_want_to_submit)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //Make a toast with the user score
                        Toast.makeText(MainActivity.this, getString(R.string.activity_user_score) +
                                calculateUserScore() + "/8", Toast.LENGTH_LONG).show();

                        //Hide the submit quiz button and show the retake quiz button
                        submitQuiz.setVisibility(View.INVISIBLE);
                        retakeQuiz.setVisibility(View.VISIBLE);
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .show();

    }

    /*Helper method for calculating the score and setting a green background for correct answers and red for wrong answers*/
    public int calculateUserScore() {
        //Check the correct answer for question 1
        if (question_1_a.isChecked() && question_1_b.isChecked() && question_1_c.isChecked() && !question_1_d.isChecked()) {
            userQuizScore += 1;
            question_1_a.setBackgroundColor(Color.GREEN);
            question_1_b.setBackgroundColor(Color.GREEN);
            question_1_c.setBackgroundColor(Color.GREEN);
        } else if (question_1_d.isChecked()) {
            question_1_d.setBackgroundColor(Color.RED);
        }

        //Check the correct answer for question 2
        if (question_2_b.isChecked() && question_2_c.isChecked() && !question_2_a.isChecked() && !question_2_d.isChecked()) {
            userQuizScore += 1;
            question_2_b.setBackgroundColor(Color.GREEN);
            question_2_c.setBackgroundColor(Color.GREEN);
        } else if (question_2_a.isChecked() && question_2_d.isChecked()) {
            question_2_a.setBackgroundColor(Color.RED);
            question_2_d.setBackgroundColor(Color.RED);
        } else if (question_2_a.isChecked()) {
            question_2_a.setBackgroundColor(Color.RED);
        } else if (question_2_d.isChecked()) {
            question_2_d.setBackgroundColor(Color.RED);
        }

        //Check the correct answer for question 3
        String question3 = question_3_edit_text.getText().toString().trim();
        if (question3.equalsIgnoreCase(getString(R.string.activity_kotlin))) {
            userQuizScore += 1;
            question_3_edit_text.setBackgroundColor(Color.GREEN);
        } else {
            question_3_edit_text.setBackgroundColor(Color.RED);
        }

        //Check the correct answer for question 4
        String question4 = question_4_edit_text.getText().toString().trim();
        if (question4.equalsIgnoreCase(getString(R.string.activity_xml))) {
            userQuizScore += 1;
            question_4_edit_text.setBackgroundColor(Color.GREEN);
        } else {
            question_4_edit_text.setBackgroundColor(Color.RED);
        }

        //Check for question 5
        if (question_5_a_radio_button.isChecked()) {
            userQuizScore += 1;
            question_5_a_radio_button.setBackgroundColor(Color.GREEN);
        } else if (question_5_b_radio_button.isChecked()) {
            question_5_b_radio_button.setBackgroundColor(Color.RED);
        }

        //Check for question 6
        String question6 = question_6_edit_text.getText().toString().trim();
        if (question6.equalsIgnoreCase(getString(R.string.activity_oop))) {
            userQuizScore += 1;
            question_6_edit_text.setBackgroundColor(Color.GREEN);
        } else {
            question_6_edit_text.setBackgroundColor(Color.RED);
        }

        //Question 7
        if (question_7_a_radio_button.isChecked()) {
            userQuizScore += 1;
            question_7_a_radio_button.setBackgroundColor(Color.GREEN);
        } else if (question_7_b_radio_button.isChecked()) {
            question_7_b_radio_button.setBackgroundColor(Color.RED);
        }

        //Question 8
        if (question_8_a_radio_button.isChecked()) {
            userQuizScore += 1;
            question_8_a_radio_button.setBackgroundColor(Color.GREEN);
        } else if (question_8_b_radio_button.isChecked()) {
            question_8_b_radio_button.setBackgroundColor(Color.RED);
        }

        return userQuizScore;
    }

    /**
     * This method resets the quiz so that the user can take it again
     *
     * @param view is the button
     */
    public void retakeQuiz(View view) {
        resetQuiz();

        //Hide the button and show the submit quiz one
        retakeQuiz.setVisibility(View.INVISIBLE);
        submitQuiz.setVisibility(View.VISIBLE);

        //Scroll to the top of the layout
        ScrollView scrollView = findViewById(R.id.activity_scrollView);
        scrollView.fullScroll(ScrollView.FOCUS_UP);
    }

    /*Helper method for resetting everything*/
    public void resetQuiz() {
        //Reset the score back to 0
        userQuizScore = 0;

        //Clear all the RadioGroups and their colors
        RadioGroup radioGroup5 = findViewById(R.id.radio_group_5);
        radioGroup5.clearCheck();
        question_5_a_radio_button.setBackgroundColor(Color.TRANSPARENT);
        question_5_b_radio_button.setBackgroundColor(Color.TRANSPARENT);

        RadioGroup radioGroup7 = findViewById(R.id.radio_group_7);
        radioGroup7.clearCheck();
        question_7_a_radio_button.setBackgroundColor(Color.TRANSPARENT);
        question_7_b_radio_button.setBackgroundColor(Color.TRANSPARENT);

        RadioGroup radioGroup8 = findViewById(R.id.radio_group_8);
        radioGroup8.clearCheck();
        question_8_a_radio_button.setBackgroundColor(Color.TRANSPARENT);
        question_8_b_radio_button.setBackgroundColor(Color.TRANSPARENT);


        //Clear all the EdiTexts
        question_6_edit_text.getText().clear();
        question_6_edit_text.setBackgroundColor(Color.TRANSPARENT);

        question_4_edit_text.getText().clear();
        question_4_edit_text.setBackgroundColor(Color.TRANSPARENT);

        question_3_edit_text.getText().clear();
        question_3_edit_text.setBackgroundColor(Color.TRANSPARENT);

        //Clear the CheckBoxes
        if (question_1_a.isChecked()) {
            question_1_a.setChecked(false);
            question_1_a.setBackgroundColor(Color.TRANSPARENT);
        }
        if (question_1_b.isChecked()) {
            question_1_b.setChecked(false);
            question_1_b.setBackgroundColor(Color.TRANSPARENT);
        }
        if (question_1_c.isChecked()) {
            question_1_c.setChecked(false);
            question_1_c.setBackgroundColor(Color.TRANSPARENT);
        }
        if (question_1_d.isChecked()) {
            question_1_d.setChecked(false);
            question_1_d.setBackgroundColor(Color.TRANSPARENT);
        }
        if (question_2_a.isChecked()) {
            question_2_a.setChecked(false);
            question_2_a.setBackgroundColor(Color.TRANSPARENT);
        }
        if (question_2_b.isChecked()) {
            question_2_b.setChecked(false);
            question_2_b.setBackgroundColor(Color.TRANSPARENT);
        }
        if (question_2_c.isChecked()) {
            question_2_c.setChecked(false);
            question_2_c.setBackgroundColor(Color.TRANSPARENT);
        }
        if (question_2_d.isChecked()) {
            question_2_d.setChecked(false);
            question_2_d.setBackgroundColor(Color.TRANSPARENT);
        }
    }
}
