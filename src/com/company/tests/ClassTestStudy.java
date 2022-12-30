package com.company.tests;

import com.company.Question;
import com.company.Study;
import com.company.Subject;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ClassTestStudy {

    @Test
    public void testGetNewQuestion() {
        String name="name";
        String ques="Сколько дней в году?"; //Вопрос
        String answer="365"; //Ответ
        LocalDate lastDate=LocalDate.of(2022, 2, 1); //Дата, когда попадался этот вопрос последний раз
        Integer sizeOfView=0; //сколько раз попадался этот вопрос //так же это показатель был вопрос или нет.
        Double percentKnow=0.0; //Процент знаний

        ArrayList<Question> questionArrayList=new ArrayList<>();
        for(int i=0; i<3; i++){
            Question question=new Question(ques, answer, LocalDate.of(2022, 2, 1).plusDays(i), sizeOfView+i, percentKnow+i*0.01);
            questionArrayList.add(question);
        }
        questionArrayList.add(new Question(ques, answer, lastDate, 7, 1.0)); //Самый сложный
        questionArrayList.add(new Question(ques, answer, LocalDate.of(2022, 2, 1).plusDays(-1), 2, 1.0)); //самый старый выученный

        Subject subject1=new Subject(name, questionArrayList);
        assertEquals(subject1.getSizeNoKnow(), 3);
        assertEquals(subject1.getSizeAllQuest(), 5);

        Study study=new Study(subject1, 3, 0);
        assertFalse(study.isEndOfPlan());

        Question q=study.GetNewQuestion();
        assertEquals(q, questionArrayList.get(1));
        study.clickReady(1.0);

        Question q1=study.GetNewQuestion();
        assertEquals(q1, questionArrayList.get(2));
        study.clickReady(1.0);

        Question q2=study.GetNewQuestion();
        assertEquals(q2, questionArrayList.get(0));
        study.clickReady(1.0);
        //Дальше идут рандомные вопросы, так как за сегодня все пройдено.
    }

    @Test
    public void testGetNewQuestion2() {
        String name="name";
        String ques="Сколько дней в году?"; //Вопрос
        String answer="365"; //Ответ
        LocalDate lastDate=LocalDate.of(2022, 2, 1); //Дата, когда попадался этот вопрос последний раз
        Integer sizeOfView=0; //сколько раз попадался этот вопрос //так же это показатель был вопрос или нет.
        Double percentKnow=0.0; //Процент знаний

        ArrayList<Question> questionArrayList=new ArrayList<>();
        for(int i=0; i<3; i++){
            Question question=new Question(ques, answer, LocalDate.of(2022, 2, 1).plusDays(i), sizeOfView+i, percentKnow+i*0.01);
            questionArrayList.add(question);
        }
        questionArrayList.add(new Question(ques, answer, lastDate, 7, 1.0)); //Самый сложный
        questionArrayList.add(new Question(ques, answer, LocalDate.of(2022, 2, 1).plusDays(-1), 2, 1.0)); //самый старый выученный

        Subject subject1=new Subject(name, questionArrayList);
        assertEquals(subject1.getSizeNoKnow(), 3);
        assertEquals(subject1.getSizeAllQuest(), 5);

        Study study=new Study(subject1, 3, 0);
        assertFalse(study.isEndOfPlan());

        int i=0;
        while (!study.isEndOfPlan()){
            i++;
            study.GetNewQuestion();
            study.clickReady(1.0);
        }
        assertEquals(3, i);
    }
}
