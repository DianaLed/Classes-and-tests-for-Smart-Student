package com.company.tests;

import com.company.Question;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class ClassTestQuestion {

    private String question="Сколько дней в году?"; //Вопрос
    private String answer="365"; //Ответ
    private LocalDate lastDate=LocalDate.of(2022, 2, 1); //Дата, когда попадался этот вопрос последний раз
    private Integer sizeOfView=5; //сколько раз попадался этот вопрос //так же это показатель был вопрос или нет.
    private Double percentKnow=0.0; //Процент знаний

//----------Тесты на компиляторы---------------
    @Test
    public void testSetBadLocalDate() {
        LocalDate Ld=LocalDate.of(LocalDate.now().getYear()+1, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth());
        boolean expected1 = false;
        try {
            Question myClass = new Question(question, answer, Ld, sizeOfView, percentKnow);
        } catch (ArithmeticException e) {
            expected1=true;
        }
        assertTrue(expected1);
    }

    @Test
    public void testCompil() {
        String q="ccc", a="aaa";
        Question question=new Question(q, a);
        Double d=0.0;
        assertEquals(question.getPercentKnow(), d);
        assertEquals(question.getQuestion(), q);
        assertEquals(question.getAnswer(), a);
        Integer size=0;
        assertEquals(question.getSizeOfView(), size);
        assertEquals(question.getAnswer(), a);
    }

    @Test
    public void testSetAllGood() {
        boolean expected = true;
        try {
            Question myClass = new Question(question, answer, lastDate, sizeOfView, percentKnow);
        } catch (ArithmeticException e) {
            expected=false;
        }
        assertTrue(expected);
    }

    @Test
    public void testSetBadSizeOfView() {
        LocalDate Ld=LocalDate.of(LocalDate.now().getYear()+1, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth());
        boolean expected2 = false;
        try {
            Question myClass = new Question(question, answer, lastDate, -1, percentKnow);
        } catch (ArithmeticException e) {
            expected2=true;
        }
        assertTrue(expected2);
    }

    @Test
    public void testSetBadPercentKnow() {
        LocalDate Ld=LocalDate.of(LocalDate.now().getYear()+1, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth());
        boolean expected3 = false;
        boolean expected4 = false;
        try {
            Question myClass = new Question(question, answer, lastDate, sizeOfView, -1.0);
        } catch (ArithmeticException e) {
            expected3=true;
        }
        try {
            Question myClass = new Question(question, answer, lastDate, sizeOfView, 2.0);
        } catch (ArithmeticException e) {
            expected4=true;
        }
        assertTrue(expected3==true&&expected4==true);
    }

    //----------Тесты на функции---------------

    @Test
    public void testСhangePrcentAndSize() {
        boolean expected1 = false;
        Question myClass = new Question();
        try {
            myClass.сhangePrcentAndSize(-1.0);
        } catch (ArithmeticException e) {
            expected1=true;
        }
        boolean expected2 = false;
        try {
            myClass.сhangePrcentAndSize(1.01);
        } catch (ArithmeticException e) {
            expected2=true;
        }
        boolean expected3 = true;
        try {
            myClass.сhangePrcentAndSize(1.0);
        } catch (ArithmeticException e) {
            expected3=false;
        }
        boolean expected4 = true;
        try {
            myClass.сhangePrcentAndSize(0.0);
        } catch (ArithmeticException e) {
            expected4=false;
        }
        boolean expected5 = true;
        try {
            myClass.сhangePrcentAndSize(0.9);
        } catch (ArithmeticException e) {
            expected5=false;
        }
        assertTrue(expected1&&expected2&&expected3&&expected4&&expected5);
    }

    @Test
    public void testGet() {
        Question myClass = new Question(question, answer, lastDate, sizeOfView, percentKnow);
        assertEquals(percentKnow, myClass.getPercentKnow());
        assertEquals(sizeOfView, myClass.getSizeOfView());
        assertTrue(lastDate.isEqual(myClass.getLastDate()));
        assertEquals(answer, myClass.getAnswer());
        assertEquals(question, myClass.getQuestion());
    }

    @Test
    public void testSet() {
        Question myClass = new Question();
        Integer a=0;
        myClass.setAnswer(answer);
        myClass.setQuestion(question);
        assertEquals(answer, myClass.getAnswer());
        assertEquals(question, myClass.getQuestion());
        assertTrue(myClass.getLastDate().equals(LocalDate.now()));
        assertEquals(a, myClass.getSizeOfView());
        Double d=0.0;
        assertEquals(d, myClass.getPercentKnow());
    }
}
