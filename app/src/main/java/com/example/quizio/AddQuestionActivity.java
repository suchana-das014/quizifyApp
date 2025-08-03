package com.example.quizio;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AddQuestionActivity extends AppCompatActivity {

    private EditText editQuestion, editOption1, editOption2, editOption3, editOption4;
    private Spinner spinnerCorrect;
    private Button buttonSaveQuestion;

    private static final String PREFS_NAME = "QuizAppPrefs";
    private static final String KEY_CUSTOM_QUESTIONS = "CustomQuestions";

    private List<QuestionModel> questionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        editQuestion = findViewById(R.id.editQuestion);
        editOption1 = findViewById(R.id.editOption1);
        editOption2 = findViewById(R.id.editOption2);
        editOption3 = findViewById(R.id.editOption3);
        editOption4 = findViewById(R.id.editOption4);
        spinnerCorrect = findViewById(R.id.spinnerCorrect);
        buttonSaveQuestion = findViewById(R.id.buttonSaveQuestion);

        // Spinner options (for correct answer)
        String[] options = {"Option 1", "Option 2", "Option 3", "Option 4"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, options);
        spinnerCorrect.setAdapter(adapter);

        // Load existing custom questions from SharedPreferences
        loadCustomQuestions();

        buttonSaveQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveQuestion();
            }
        });
    }

    private void loadCustomQuestions() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String json = prefs.getString(KEY_CUSTOM_QUESTIONS, null);
        if (json != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<QuestionModel>>() {}.getType();
            List<QuestionModel> savedQuestions = gson.fromJson(json, type);
            if (savedQuestions != null) {
                questionList = savedQuestions;
            }
        }
    }

    private void saveQuestion() {
        String question = editQuestion.getText().toString().trim();
        String option1 = editOption1.getText().toString().trim();
        String option2 = editOption2.getText().toString().trim();
        String option3 = editOption3.getText().toString().trim();
        String option4 = editOption4.getText().toString().trim();
        int correctIndex = spinnerCorrect.getSelectedItemPosition();

        // Validate fields
        if (question.isEmpty() || option1.isEmpty() || option2.isEmpty() ||
                option3.isEmpty() || option4.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Prepare options list
        List<String> options = new ArrayList<>();
        options.add(option1);
        options.add(option2);
        options.add(option3);
        options.add(option4);

        // Create new question model
        QuestionModel newQuestion = new QuestionModel(question, options, options.get(correctIndex));

        // Add to question list and save to SharedPreferences
        questionList.add(newQuestion);
        saveQuestionsToPrefs();

        Toast.makeText(this, "Question added successfully!", Toast.LENGTH_SHORT).show();

        // Clear fields after saving
        clearFields();
    }

    private void saveQuestionsToPrefs() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        Gson gson = new Gson();
        String json = gson.toJson(questionList);
        editor.putString(KEY_CUSTOM_QUESTIONS, json);
        editor.apply();
    }

    private void clearFields() {
        editQuestion.setText("");
        editOption1.setText("");
        editOption2.setText("");
        editOption3.setText("");
        editOption4.setText("");
        spinnerCorrect.setSelection(0);
    }
}
