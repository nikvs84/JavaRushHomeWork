package com.javarush.test.level23.lesson13.big01;

public class SnakeSection
{
    private int x;
    private int y;


    public SnakeSection(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }
    @Override
    public int hashCode() {
        int result = x * 31 + y;
        return result;
    }
    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        SnakeSection section = (SnakeSection) o;
        int xx = section.getX();
        int yy = section.getY();
        return this.x == xx && this.y == yy;
    }
}
