package com.exercise.springquestionchoices.repository;

import com.exercise.springquestionchoices.model.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Artificial database for the questions
public class QuestionBank {
    //Singleton pattern
    private static QuestionBank instance;
    public static QuestionBank getInstance() {
        if (instance == null) instance = new QuestionBank();

        return instance;
    }
    private QuestionBank() {
        questionList = new ArrayList<>();

        Collections.addAll(questionList, QUESTIONS);
    }

    //the hardcoded questions
    private static final Question[] QUESTIONS = new Question[]{
            new Question("Which is the longest river in the world?", new String[]{"Amazon River", "Nile River", "Yellow River", "Congo River"},       1),
            new Question("Which is the largest body of water?",      new String[]{"Indian Ocean", "Atlantic Ocean", "Pacific Ocean", "Arctic Ocean"}, 2),
            new Question("What is the capital of Iceland?",          new String[]{"Reykjavík", "Torshavn", "Dublin", "Hafnarfjörður"},                0),
            new Question("What is the capital of Latvia?",           new String[]{"Talinn", "Vilnius", "Latvia", "Riga"},                             3)
    };

    public final List<Question> questionList;
}