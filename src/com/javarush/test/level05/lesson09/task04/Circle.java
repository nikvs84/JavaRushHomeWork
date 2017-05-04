package com.javarush.test.level05.lesson09.task04;

/* Создать класс Circle
Создать класс (Circle) круг, с тремя конструкторами:
- centerX, centerY, radius
- centerX, centerY, radius, width
- centerX, centerY, radius, width, color
*/

public class Circle
{
    private double centerX, centerY, radius, width;
    private String color;
//    private double centerY;
//    private double radius;
//    private double width;
    public Circle(double centerX, double centerY, double radius){
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }
    public Circle(double centerX, double centerY, double radius, double width){
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.width = width;
    }
    public Circle(double centerX, double centerY, double radius, double width, String color){
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.width = width;
        this.color = color;
    }
    //напишите тут ваш код

}
