package com.javarush.test.level05.lesson07.task05;

/* Создать класс прямоугольник (Rectangle)
Создать класс прямоугольник (Rectangle). Его данными будут top, left, width, height (левая координата, верхняя, ширина и высота). Создать для него как можно больше методов initialize(…)
Примеры:
-	заданы 4 параметра: left, top, width, height
-	ширина/высота не задана (оба равны 0)
-	высота не задана (равно ширине) создаём квадрат
-	создаём копию другого прямоугольника (он и передаётся в параметрах)
*/

public class Rectangle
{
    private double left;
    private double top;
    private double width;
    private double height;

    public void initialize(double left, double top, double width, double height){
        this.left=left;
        this.top=top;
        this.width=width;
        this.height=height;
    }
    public void initialize(double left, double top){
        this.left=left;
        this.top=top;
        this.width=0;
        this.height=0;
    }
    public void initialize(double left, double top, double width){
        this.left=left;
        this.top=top;
        this.width=width;
        this.height=width;
    }
    public void initialize(Rectangle rectangle/*, Rectangle rectName*/){
//        rectName = new Rectangle();
        /*rectName*/this.left=rectangle.left;
        /*rectName*/this.top=rectangle.top;
        /*rectName*/this.width=rectangle.width;
        /*rectName*/this.height=rectangle.height;
    }
    //напишите тут ваш код

}
