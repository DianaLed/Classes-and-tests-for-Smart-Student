package com.company;

import java.time.LocalDate;

public class PlanToDay {
    private LocalDate date;
    private int sizeOfQuetion;

    public PlanToDay(LocalDate date_, int size_of_quet){
        date=  date_;
        sizeOfQuetion =size_of_quet;
    }

    public void changeSizeOfQuetion(int a){
        sizeOfQuetion =a;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate localDate) {
        date=localDate;
    }

    public int getSizeOfQuetion(){
        return sizeOfQuetion;
    }
    public void setSizeOfQuetion(int sizeOfQ){
        sizeOfQuetion=sizeOfQ;
    }
}
