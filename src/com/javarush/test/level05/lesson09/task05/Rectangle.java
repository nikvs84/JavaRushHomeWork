package com.javarush.test.level05.lesson09.task05;

/* Создать класс прямоугольник (Rectangle)
Создать класс прямоугольник (Rectangle). Его данными будут top, left, width, height (левая координата, верхняя, ширина и высота). Создать для него как можно больше конструкторов:
Примеры:
-	заданы 4 параметра: left, top, width, height
-	ширина/высота не задана (оба равны 0)
-	высота не задана (равно ширине) создаём квадрат
-	создаём копию другого прямоугольника (он и передаётся в параметрах)
*/

public class Rectangle
{
    private double left, top, width, height;

    public Rectangle(){

    }
    public Rectangle(double left, double top, double width, double height){
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;
    }
    public Rectangle(double left, double top){
        this.left = left;
        this.top = top;
        this.width = 0;
        this.height = 0;
    }
    public Rectangle(double left, double top, double width){
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = this.width;
    }
    public Rectangle(Rectangle rect/*, Rectangle rectName*/){
        //Rectangle rectName = new Rectangle();
        this.height = rect.height;
        this.width = rect.width;
        this.left = rect.left;
        this.top = rect.top;
    }
    //напишите тут ваш код
//    public static void main(String[] args)
//    {
//        Rectangle rectangle = new Rectangle(3,9);
//        System.out.println(rectangle.left+" "+rectangle.top+" "+rectangle.height+" "+rectangle.width);
//        Rectangle newRec = new Rectangle(rectangle);
//        System.out.println(newRec.left+" "+newRec.top+" "+newRec.height+" "+newRec.width);
//    }
}
