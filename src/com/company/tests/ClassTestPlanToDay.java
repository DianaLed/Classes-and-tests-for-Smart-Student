package com.company.tests;

import com.company.PlanToDay;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ClassTestPlanToDay {
    PlanToDay planToDay=new PlanToDay(LocalDate.of(2002, 1,20 ), 10);
    @Test
    public void testChangeSizeOfQuetion() {
        int n = 2;
        planToDay.setSizeOfQuetion(n);
        assertEquals(n, planToDay.getSizeOfQuetion());
    }
    @Test
    public void testDate() {
        LocalDate localDate=LocalDate.of(2002, 1,20 );
        assertTrue(localDate.isEqual(planToDay.getDate()));
        planToDay.setDate(LocalDate.now());
        assertTrue(LocalDate.now().isEqual(planToDay.getDate()));
    }

    @Test
    public void testDate2() {
        LocalDate localDate=LocalDate.now();
        PlanToDay planToDay1=new PlanToDay(localDate, 10);
        assertTrue(planToDay1.getSizeOfQuetion()==10);
    }

}
