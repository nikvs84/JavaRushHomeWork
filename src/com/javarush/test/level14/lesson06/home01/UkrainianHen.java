package com.javarush.test.level14.lesson06.home01;

import com.javarush.test.level13.lesson06.task01.*;

/**
 * Created by Admin on 18.07.2016.
 */
public class UkrainianHen extends Hen {
    public UkrainianHen() {
//        this.countOfEggsPerMonth = 33;
//        this.country = UKRAINE;
    }

    public int getCountOfEggsPerMonth() {
        return 33/*countOfEggsPerMonth*/;
    }

    public String getDescription() {
        return super.getDescription() + " Моя страна - " + Country.UKRAINE + ". Я несу " + this.getCountOfEggsPerMonth() + " яиц в месяц.";
    }
}
