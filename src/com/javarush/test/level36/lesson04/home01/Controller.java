package com.javarush.test.level36.lesson04.home01;

import java.util.List;

/**
 * Created by Admin on 29.11.2016.
 */
public class Controller {
    Model model;

    public Controller() {
        this.model = new Model();
    }

    public List<String> onDataListShow() {
        return model.getStringDataList();
    }

}
