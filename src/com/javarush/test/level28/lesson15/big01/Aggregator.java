package com.javarush.test.level28.lesson15.big01;

import com.javarush.test.level28.lesson15.big01.model.HHStrategy;
import com.javarush.test.level28.lesson15.big01.model.Model;
import com.javarush.test.level28.lesson15.big01.model.MoikrugStrategy;
import com.javarush.test.level28.lesson15.big01.model.Provider;
import com.javarush.test.level28.lesson15.big01.view.HtmlView;

/**
 * Created by Admin on 24.10.2016.
 */
public class Aggregator {
    public static void main(String[] args) {
        HtmlView htmlView = new HtmlView();
        Provider hh = new Provider(new HHStrategy());
        Provider moiKrug = new Provider(new MoikrugStrategy());
        Model model = new Model(htmlView, hh, moiKrug);
        Controller controller = new Controller(model);
        htmlView.setController(controller);
        htmlView.userCitySelectEmulationMethod();
    }
}
