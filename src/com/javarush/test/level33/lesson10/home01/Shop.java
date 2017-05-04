package com.javarush.test.level33.lesson10.home01;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * Created by Admin on 13.11.2016.
 */
@XmlType(name = "shop")
@XmlRootElement
public class Shop {
    @XmlElementWrapper(name = "goods", nillable = true)
    public List<String> names;
    public int count;
    public double profit;
    public List<String> secretData;

    @Override
    public String toString() {
        return "Shop{" +
                "names=" + names +
                ", count=" + count +
                ", profit=" + profit +
                ", secretData=" + secretData +
                '}';
    }
}
