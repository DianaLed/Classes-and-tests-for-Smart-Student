package com.company.tests;

import com.company.Question;
import com.company.Subject;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ClassTestSubject {


    @Test
    public void testBaseConst() {
        Subject subject=new Subject();
        assertEquals(subject.getSizeAllQuest(), 0);
        assertEquals(subject.getSizeKnow(), 0);
        assertEquals(subject.getSizeNoKnow(), 0);
    }

    @Test
    public void testConstWhithName() {
        String name="name";
        Subject subject=new Subject(name);
        assertEquals(subject.getSizeAllQuest(), 0);
        assertEquals(subject.getNameOfSubme(), name);
        String name1="name1";
        subject.setNameOfSub(name1);
        assertEquals(subject.getNameOfSubme(), name1);
    }

    @Test
    public void testConstAllQ() {
        String name="name";
        ArrayList<Question> questionArrayList=new ArrayList<>();

        Subject subject1=new Subject(name, questionArrayList);
        assertEquals(subject1.getSizeAllQuest(), 0);

        for(int i=0; i<4; i++){
            Question question=new Question();
            questionArrayList.add(question);
        }
        Subject subject2=new Subject(name, questionArrayList);
        assertEquals(subject2.getSizeAllQuest(), 4);

        subject2.addQuestion(new Question());
        assertEquals(subject2.getSizeAllQuest(), 5);

        subject2.deleteQuestion(1);
        assertEquals(subject2.getSizeAllQuest(), 4);

        subject2.changeQuestion(0, "Q", "A");
        assertEquals(subject2.getQuestion(0).getAnswer(), "A");
        assertEquals(subject2.getQuestion(0).getQuestion(), "Q");
    }

    @Test
    public void testAlhoritm() {
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
        assertEquals(subject1.getSizeAllQuest(), 5);
        assertEquals(subject1.getPositionOldestDay(), 4);
        assertEquals(subject1.getPositionHardQuestionAndNotToDay(), 3);
        assertEquals(subject1.getPositionNoKnowNoViev(), 0);
        assertEquals(subject1.getArrListVievAndNoKnow().size(), 2);
        Integer a=1;
        ArrayList<Integer> sort=subject1.getSortArrListVievAndNoKnow();
        assertEquals(subject1.getSortArrListVievAndNoKnow().get(0), a);
    }

}
