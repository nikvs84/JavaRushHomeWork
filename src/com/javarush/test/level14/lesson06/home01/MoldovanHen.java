package com.javarush.test.level14.lesson06.home01;

import com.javarush.test.level13.lesson06.task01.*;

/**
 * Created by Admin on 18.07.2016.
 */
public class MoldovanHen extends Hen {
    public MoldovanHen() {
//        this.countOfEggsPerMonth = 30;
//        this.country = MOLDOVA;
    }

    public int getCountOfEggsPerMonth() {
        return 30/*countOfEggsPerMonth*/;
    }

    public String getDescription() {
        return super.getDescription() + " Моя страна - " + Country.MOLDOVA + ". Я несу " + this.getCountOfEggsPerMonth() + " яиц в месяц.";
    }
}
