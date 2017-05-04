package com.javarush.test.level14.lesson06.home01;

import com.javarush.test.level13.lesson06.task01.*;

/**
 * Created by Admin on 18.07.2016.
 */
public class BelarusianHen extends Hen {

    public BelarusianHen() {
//        this.countOfEggsPerMonth = 35;
//        this.country = BELARUS;
    }

    public int getCountOfEggsPerMonth() {
        return 35/*countOfEggsPerMonth*/;
    }

    public String getDescription() {
        return super.getDescription() + " Моя страна - " + Country.BELARUS + ". Я несу " + this.getCountOfEggsPerMonth() + " яиц в месяц.";
    }
}
