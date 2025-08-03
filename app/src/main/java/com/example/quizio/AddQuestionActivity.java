package com.example.quizio;

import android.content.SharedPreferences;
import android.os.Bundle;
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
import java.util.Arrays;
import java.util.List;

public class AddQuestionActivity extends AppCompatActivity {

    private EditText etQuestion, etOption1, etOption2, etOption3, etOption4;
    private Spinner spinnerCategory, spinnerCorrectAnswer;
    private Button btnAddQuestion;

    private static final String PREFS_NAME = "QuizAppPrefs";
    private static final String KEY_CUSTOM_QUESTIONS = "CustomQuestions";

    private List<QuestionModel> customQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        etQuestion = findViewById(R.id.etQuestion);
        etOption1 = findViewById(R.id.etOption1);
        etOption2 = findViewById(R.id.etOption2);
        etOption3 = findViewById(R.id.etOption3);
        etOption4 = findViewById(R.id.etOption4);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        spinnerCorrectAnswer = findViewById(R.id.spinnerCorrectAnswer);
        btnAddQuestion = findViewById(R.id.btnAddQuestion);

        setupCategorySpinner();
        loadCustomQuestions();

        btnAddQuestion.setOnClickListener(v -> addQuestion());
    }

    private void setupCategorySpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categories_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);
    }

    private void loadCustomQuestions() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String json = prefs.getString(KEY_CUSTOM_QUESTIONS, null);
        if (json != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<QuestionModel>>() {}.getType();
            customQuestions = gson.fromJson(json, type);
        } else {
            customQuestions = new ArrayList<>();
        }
    }

    private void addQuestion() {
        String question = etQuestion.getText().toString().trim();
        String option1 = etOption1.getText().toString().trim();
        String option2 = etOption2.getText().toString().trim();
        String option3 = etOption3.getText().toString().trim();
        String option4 = etOption4.getText().toString().trim();
        String category = spinnerCategory.getSelectedItem().toString();
        String correctAnswer = option1; // Default for now (can choose via another spinner)

        if (question.isEmpty() || option1.isEmpty() || option2.isEmpty() ||
                option3.isEmpty() || option4.isEmpty()) {
            Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        List<String> optionsList = Arrays.asList(option1, option2, option3, option4);

        QuestionModel newQuestion = new QuestionModel(question, optionsList, correctAnswer, category);
        customQuestions.add(newQuestion);

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        Gson gson = new Gson();
        editor.putString(KEY_CUSTOM_QUESTIONS, gson.toJson(customQuestions));
        editor.apply();

        Toast.makeText(this, "Question Added!", Toast.LENGTH_SHORT).show();
        clearInputs();
    }

    private void clearInputs() {
        etQuestion.setText("");
        etOption1.setText("");
        etOption2.setText("");
        etOption3.setText("");
        etOption4.setText("");
    }
}
