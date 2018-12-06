package com.exercise.springquestionchoices.model;

import java.util.Arrays;

public class Question {
    private final String name;
    private final String[] answers;
    //the index of the correct answer
    private final int correctAnswerIndex;

    public Question(String name, String[] answers, int correctAnswerIndex) {
        this.name = name;
        this.answers = answers;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getName() {
        return name;
    }

    public String[] getAnswers() {
        return answers;
    }

    public String getCorrectAnswer() {
        return answers[correctAnswerIndex];
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    @Override
    public String toString() {
        return "Question{" +
                "name='" + name + '\'' +
                ", answers=" + Arrays.toString(answers) +
                ", correctAnswer=" + getCorrectAnswer() +
                '}';
    }
}

