package com.company.tests;

import com.company.*;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ClassTestPlanToSub {

    @Test
    public void testBazaConst() {
        String name = "name";
        String ques = "Сколько дней в году?"; //Вопрос
        String answer = "365"; //Ответ
        LocalDate lastDate = LocalDate.of(2022, 2, 1); //Дата, когда попадался этот вопрос последний раз
        Integer sizeOfView = 0; //сколько раз попадался этот вопрос //так же это показатель был вопрос или нет.
        Double percentKnow = 0.0; //Процент знаний

        ArrayList<PlanToDay> futurePlan =new ArrayList<PlanToDay>();
        ArrayList<PlanToDay> lastPlan=new ArrayList<PlanToDay>();

        ArrayList<Question> questionArrayList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Question question = new Question(ques, answer, LocalDate.of(2022, 2, 1).plusDays(i), sizeOfView + i, percentKnow + i * 0.01);
            questionArrayList.add(question);
        }
        questionArrayList.add(new Question(ques, answer, lastDate, 7, 1.0)); //Самый сложный
        questionArrayList.add(new Question(ques, answer, LocalDate.of(2022, 2, 1).plusDays(-1), 2, 1.0)); //самый старый выученный

        LocalDate dateOfExams = LocalDate.now().plusDays(2);
        Subject subject1 = new Subject(name, questionArrayList);
        PlanToSub planToSub=new PlanToSub(subject1, 0, dateOfExams, futurePlan, lastPlan);
        assertEquals(planToSub.getFuturePlan(), futurePlan);
        assertEquals(planToSub.getLastPlan(), lastPlan);
        assertEquals(planToSub.getDateOfExams(), dateOfExams);
        assertEquals(planToSub.getTodayLearned(), 0);
    }

    @Test
    public void testBaseConst() {
        String name = "name";
        String ques = "Сколько дней в году?"; //Вопрос
        String answer = "365"; //Ответ
        LocalDate lastDate = LocalDate.of(2022, 2, 1); //Дата, когда попадался этот вопрос последний раз
        Integer sizeOfView = 0; //сколько раз попадался этот вопрос //так же это показатель был вопрос или нет.
        Double percentKnow = 0.0; //Процент знаний

        ArrayList<Question> questionArrayList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Question question = new Question(ques, answer, LocalDate.of(2022, 2, 1).plusDays(i), sizeOfView + i, percentKnow + i * 0.01);
            questionArrayList.add(question);
        }
        questionArrayList.add(new Question(ques, answer, lastDate, 7, 1.0)); //Самый сложный
        questionArrayList.add(new Question(ques, answer, LocalDate.of(2022, 2, 1).plusDays(-1), 2, 1.0)); //самый старый выученный

        Subject subject1 = new Subject(name, questionArrayList);
        assertEquals(subject1.getSizeNoKnow(), 3);
        assertEquals(subject1.getSizeAllQuest(), 5);

        LocalDate dateOfExams = LocalDate.now().plusDays(2);
        PlanToSub planToSub = new PlanToSub(subject1, dateOfExams);
        LocalDate localDate1 = LocalDate.now();
        planToSub.minusDayToPlan(localDate1);
        planToSub.minusDayToPlan(LocalDate.now().plusDays(2));
        planToSub.minusDayToPlan(LocalDate.now().plusDays(1));

        planToSub.plusDayToPlan(localDate1);
        planToSub.plusDayToPlan(localDate1.plusDays(1));
        planToSub.plusDayToPlan(localDate1.plusDays(2));
        planToSub.plusDayToPlan(localDate1);
        planToSub.plusDayToPlan(localDate1);
        assertEquals(planToSub.getFuturePlan().size(), 2);
        planToSub.minusDayToPlan(LocalDate.now().plusDays(1));
        assertEquals(planToSub.getFuturePlan().size(), 1);
    }

    @Test
    public void testNextDay() {
        String name = "name";
        String ques = "Сколько дней в году?"; //Вопрос
        String answer = "365"; //Ответ
        LocalDate lastDate = LocalDate.of(2022, 2, 1); //Дата, когда попадался этот вопрос последний раз
        Integer sizeOfView = 0; //сколько раз попадался этот вопрос //так же это показатель был вопрос или нет.
        Double percentKnow = 0.0; //Процент знаний

        ArrayList<Question> questionArrayList = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            Question question = new Question(ques, answer, LocalDate.of(2022, 2, 1).plusDays(i), sizeOfView + i, percentKnow + i * 0.01);
            questionArrayList.add(question);
        }
        questionArrayList.add(new Question(ques, answer, lastDate, 7, 1.0)); //Самый сложный
        questionArrayList.add(new Question(ques, answer, LocalDate.of(2022, 2, 1).plusDays(-1), 2, 1.0)); //самый старый выученный

        Subject subject1 = new Subject(name, questionArrayList);

        LocalDate dateOfExams = LocalDate.now().plusDays(10);
        PlanToSub planToSub = new PlanToSub(subject1, dateOfExams);
        LocalDate localDate1 = LocalDate.now();
        planToSub.plusDayToPlan(localDate1);
        planToSub.plusDayToPlan(localDate1.plusDays(1));
        planToSub.plusDayToPlan(localDate1.plusDays(2));
        planToSub.plusDayToPlan(localDate1.plusDays(3));
        planToSub.plusDayToPlan(localDate1.plusDays(5));
        assertEquals(planToSub.getFuturePlan().size(), 5);
        planToSub.FORTESTnextDay(localDate1);
        assertEquals(planToSub.getFuturePlan().size(), 5);
        int i = 0;
        Study newStudy = new Study(planToSub.getSub(), planToSub.getFuturePlan().get(0).getSizeOfQuetion(), planToSub.getTodayLearned());
        for (int j = 0; j < 3; j++) {
            newStudy.GetNewQuestion();
            newStudy.clickReady(1.0);
            planToSub.progress(); //только когда чел знает на 100%
            i++;
        } //Прошло 3 дня и 3 вопроса.До этого мы знали 2 вопроса->должны знать 3.
        planToSub.FORTESTnextDay(localDate1.plusDays(i));
        assertEquals(planToSub.getSub().getSizeKnow(), 5);
    }

    @Test
    public void testProgress() {
        String name = "name";
        String ques = "Сколько дней в году?"; //Вопрос
        String answer = "365"; //Ответ
        LocalDate lastDate = LocalDate.of(2022, 2, 1); //Дата, когда попадался этот вопрос последний раз
        Integer sizeOfView = 0; //сколько раз попадался этот вопрос //так же это показатель был вопрос или нет.
        Double percentKnow = 0.0; //Процент знаний

        ArrayList<Question> questionArrayList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Question question = new Question(ques, answer, LocalDate.of(2022, 2, 1).plusDays(i), sizeOfView + i, percentKnow + i * 0.01);
            questionArrayList.add(question);
        }
        questionArrayList.add(new Question(ques, answer, lastDate, 7, 1.0)); //Самый сложный
        questionArrayList.add(new Question(ques, answer, LocalDate.of(2022, 2, 1).plusDays(-1), 2, 1.0)); //самый старый выученный

        Subject subject1 = new Subject(name, questionArrayList);



        LocalDate dateOfExams = LocalDate.now().plusDays(10);
        PlanToSub planToSub = new PlanToSub(subject1, dateOfExams);
        assertEquals(planToSub.getSub().getNameOfSubme(), name);
        LocalDate localDate1 = LocalDate.now();
        planToSub.plusDayToPlan(localDate1);
        planToSub.plusDayToPlan(localDate1.plusDays(1));
        planToSub.plusDayToPlan(localDate1.plusDays(2));
        planToSub.plusDayToPlan(localDate1.plusDays(3));
        planToSub.plusDayToPlan(localDate1.plusDays(5));
        int i = 0;
        Study newStudy = new Study(planToSub.getSub(), planToSub.getFuturePlan().get(0).getSizeOfQuetion(), planToSub.getTodayLearned());
        for (int j = 0; j < 3; j++) {
            newStudy.GetNewQuestion();
            newStudy.clickReady(1.0);
            planToSub.progress(); //только когда чел знает на 100%
            i++;
        }
        planToSub.FORTESTnextDay(localDate1.plusDays(i));
        assertEquals(planToSub.getSub().getSizeKnow(), 5);

        newStudy.GetNewQuestion();
        newStudy.clickReady(1.0);
        planToSub.progress();


    }

    @Test
    public void testProgress2() {
        String name = "name";
        String ques = "Сколько дней в году?"; //Вопрос
        String answer = "365"; //Ответ
        LocalDate lastDate = LocalDate.of(2022, 2, 1); //Дата, когда попадался этот вопрос последний раз
        Integer sizeOfView = 0; //сколько раз попадался этот вопрос //так же это показатель был вопрос или нет.
        Double percentKnow = 0.0; //Процент знаний

        ArrayList<Question> questionArrayList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Question question = new Question(ques, answer, LocalDate.of(2022, 2, 1).plusDays(i), sizeOfView + i, percentKnow + i * 0.01);
            questionArrayList.add(question);
        }
        questionArrayList.add(new Question(ques, answer, lastDate, 7, 1.0)); //Самый сложный
        questionArrayList.add(new Question(ques, answer, LocalDate.of(2022, 2, 1).plusDays(-1), 2, 1.0)); //самый старый выученный

        Subject subject1 = new Subject(name, questionArrayList);

        LocalDate dateOfExams = LocalDate.now().plusDays(10);
        PlanToSub planToSub = new PlanToSub(subject1, dateOfExams);
        LocalDate localDate1 = LocalDate.now();
        planToSub.plusDayToPlan(localDate1);
        planToSub.plusDayToPlan(localDate1.plusDays(1));
        planToSub.plusDayToPlan(localDate1.plusDays(2));
        planToSub.plusDayToPlan(localDate1.plusDays(3));
        planToSub.plusDayToPlan(localDate1.plusDays(5));
        int i = 0;
        Study newStudy = new Study(planToSub.getSub(), planToSub.getFuturePlan().get(0).getSizeOfQuetion(), planToSub.getTodayLearned());
        for (int j = 0; j < 3; j++) {
            newStudy.GetNewQuestion();
            newStudy.clickReady(0.0);
            planToSub.progress();
            i++;
        }
        planToSub.FORTESTnextDay(localDate1.plusDays(i));
        assertEquals(planToSub.getSub().getSizeKnow(), 2);
    }

    @Test
    public void testProgress3() {
        String name = "name";
        String ques = "Сколько дней в году?"; //Вопрос
        String answer = "365"; //Ответ
        LocalDate lastDate = LocalDate.of(2022, 2, 1); //Дата, когда попадался этот вопрос последний раз
        Integer sizeOfView = 0; //сколько раз попадался этот вопрос //так же это показатель был вопрос или нет.
        Double percentKnow = 0.0; //Процент знаний

        ArrayList<Question> questionArrayList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Question question = new Question(ques, answer, LocalDate.of(2022, 2, 1).plusDays(i), sizeOfView + i, percentKnow + i * 0.01);
            questionArrayList.add(question);
        }
        questionArrayList.add(new Question(ques, answer, lastDate, 7, 1.0)); //Самый сложный
        questionArrayList.add(new Question(ques, answer, LocalDate.of(2022, 2, 1).plusDays(-1), 2, 1.0)); //самый старый выученный

        Subject subject1 = new Subject(name, questionArrayList);

        LocalDate dateOfExams = LocalDate.now().plusDays(10);
        PlanToSub planToSub = new PlanToSub(subject1, dateOfExams);
        LocalDate localDate1 = LocalDate.now();
        planToSub.plusDayToPlan(localDate1);
        planToSub.plusDayToPlan(localDate1.plusDays(1));
        planToSub.plusDayToPlan(localDate1.plusDays(2));
        planToSub.plusDayToPlan(localDate1.plusDays(3));
        planToSub.plusDayToPlan(localDate1.plusDays(5));
        int i = 0;
        Study newStudy = new Study(planToSub.getSub(), planToSub.getFuturePlan().get(0).getSizeOfQuetion(), planToSub.getTodayLearned());
        planToSub.FORTESTnextDay(LocalDate.now());
        for (int j = 0; j < 10; j++) {
            Question question1 = newStudy.GetNewQuestion();
            newStudy.clickReady(0.0);
            planToSub.progress();
            i++;
        }
        planToSub.FORTESTnextDay(localDate1.plusDays(i));
        assertEquals(planToSub.getSub().getSizeKnow(), 0);
        assertEquals(planToSub.getLastPlan().get(0).getSizeOfQuetion(), -2);
    }

}
