package com.exercise.springquestionchoices.controller;

import com.exercise.springquestionchoices.model.Question;
import com.exercise.springquestionchoices.repository.QuestionBank;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Iterator;

@Controller
public class QuestionController {
    private Iterator<Question> qIterator;
    private Question currentQuestion;
    private int nQuestions;
    private int correctAnswers;
    private boolean initialised = false;

    @GetMapping("/question_page")
    public String questionPage(Model model) {
        if (!initialised) return "redirect:/";

        model.addAttribute("question", currentQuestion);
        return "question_page";
    }

    @PostMapping("/question_page")
    public String questionPage(@ModelAttribute("answer") String index) {
        int answer;
        try {
            if (Integer.parseInt(index) == currentQuestion.getCorrectAnswerIndex())
                answer = 1;
            else
                answer = 0;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            answer = -1;
        }

        if (answer == 1) {
            correctAnswers++;
        }

        if (answer != -1) {
            if (qIterator.hasNext()) {
                currentQuestion = qIterator.next();
            } else {
                return "redirect:/quiz_ended";
            }
        }

        return "redirect:/question_page";
    }

    @GetMapping("/")
    public String init() {
        if (!initialised) {
            QuestionBank questionBank = QuestionBank.getInstance();
            qIterator = questionBank.questionList.iterator();
            nQuestions = questionBank.questionList.size();
            correctAnswers = 0;
            currentQuestion = qIterator.next();
            initialised = true;
        }

        return "redirect:/question_page";
    }

    @GetMapping("/quiz_ended")
    public String quizEnded(Model model) {
        if (!initialised) return "redirect:/";

        String sPercentage = String.format("%.2f", (float)correctAnswers/nQuestions*100);

        model.addAttribute("nQuestions", nQuestions);
        model.addAttribute("correctAnswers", correctAnswers);
        model.addAttribute("percentage", sPercentage);

        return "quiz_ended";
    }
}
