package com.javarush.test.level36.lesson04.home01;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 29.11.2016.
 */
public class Service {
    List<String> data = new ArrayList<String>() {{
        add("First string");
        add("Second string");
        add("Third string");
    }};

    public List<String> getData() {

        return data;
    }
}
