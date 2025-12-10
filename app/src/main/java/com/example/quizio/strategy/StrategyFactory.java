package com.example.quizio.strategy;

import android.content.Context;
import com.example.quizio.models.MCQQuestion;
import com.example.quizio.models.QuestionModel;
import com.example.quizio.models.TrueFalseQuestion;

public class StrategyFactory {

    public static QuestionStrategy createStrategy(QuestionModel question, Context context) {
        if (question instanceof MCQQuestion) {
            return new MCQStrategy(context);
        } else if (question instanceof TrueFalseQuestion) {
            return new TrueFalseStrategy(context);
        } else {
            return new MCQStrategy(context); // Default
        }
    }
}