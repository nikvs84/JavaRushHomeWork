package com.javarush.test.level05.lesson07.task04;

/* Создать класс Circle
Создать класс (Circle) круг, с тремя инициализаторами:
- centerX, centerY, radius
- centerX, centerY, radius, width
- centerX, centerY, radius, width, color
*/

public class Circle
{
    private double centerX;
    private double centerY;
    private double radius;
    private double width;
    private String color;

    public void initialize(double centerX, double centerY, double radius){
        this.centerX=centerX;
        this.centerY=centerY;
        this.radius=radius;
    }
    public void initialize(double centerX, double centerY, double radius, double width){
        this.centerX=centerX;
        this.centerY=centerY;
        this.radius=radius;
        this.width=width;
    }
    public void initialize(double centerX, double centerY, double radius, double width, String color){
        this.centerX=centerX;
        this.centerY=centerY;
        this.radius=radius;
        this.width=width;
        this.color=color;
    }
    //напишите тут ваш код

}
