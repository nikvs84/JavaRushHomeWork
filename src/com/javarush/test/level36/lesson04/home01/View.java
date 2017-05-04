package com.javarush.test.level36.lesson04.home01;

/**
 * Created by Admin on 29.11.2016.
 */
public class View {
    Controller controller = new Controller();

    public View() {}

    public void fireEventShowData() {
        System.out.println(controller.onDataListShow());
    }
}
