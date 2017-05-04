package com.javarush.test.level14.lesson06.home01;

import com.javarush.test.level13.lesson06.task01.*;

/**
 * Created by Admin on 18.07.2016.
 */
public class RussianHen extends Hen {
    public RussianHen() {
//        this.countOfEggsPerMonth = 34;
//        this.country = RUSSIA;
    }

    public int getCountOfEggsPerMonth() {
        return 34/*countOfEggsPerMonth*/;
    }

    public String getDescription() {
        return super.getDescription() + " Моя страна - " + Country.RUSSIA + ". Я несу " + this.getCountOfEggsPerMonth() + " яиц в месяц.";
    }
}
