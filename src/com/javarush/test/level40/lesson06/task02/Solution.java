package com.javarush.test.level40.lesson06.task02;

/* Принадлежность точки многоугольнику
Дан многоугольник, заданный координатами своих вершин.
Ребра многоугольника не пересекаются.
Необходимо реализовать метод isPointInPolygon(Point point, List<Point> polygon), который проверит,
принадлежит ли переданная точка многоугольнику.
*/

import java.util.ArrayList;
import java.util.List;

class Point {
    public int x;
    public int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Solution {
    public static void main(String[] args) {
        List<Point> polygon = new ArrayList<>();
        polygon.add(new Point(0, 0));
        polygon.add(new Point(0, 10));
        polygon.add(new Point(10, 10));
        polygon.add(new Point(10, 0));

        System.out.println(isPointInPolygon(new Point(5, 5), polygon));
        System.out.println(isPointInPolygon(new Point(100, 100), polygon));
        System.out.println(isPointInPolygon(new Point(10, 10), polygon));

//        Polygon polygon1 = new Polygon(polygon);
//        for (Point p : polygon1.getOrderedPoints()) {
//            System.out.println("x = " + p.x + "; y = " + p.y);
//        }

//        Line line1 = new Line(new Point(0, 1), new Point(2, 3));
//        Line line2 = new Line(new Point(20, 10), new Point(10, 0));
//        System.out.println("line1: " + line1);
//        System.out.println("line2: " + line2);
//        System.out.println(new LinearEquationSystem(line1, line2).solve());
//        Section section1 = new Section(polygon.get(1), polygon.get(2));
//        Section section2 = new Section(polygon.get(2), polygon.get(3));
//        Point point1 = new Point(100, 100);
//        System.out.println(section1.hasPoint(point1));
//        System.out.println(polygon1.hasPointInside(point1));
    }

    public static boolean isPointInPolygon(Point point, List<Point> polygon) {
        boolean result = false;
        //напишите тут ваш код
//        result = new Polygon(polygon).hasPointInside(point);
//        result = new Polygon(polygon).hasPointInsideByAngle(point);
        /*
        * Не принимает два варианта решения:
        *  - трассировка прямой;
        *  - вычисление суммы углов между точкой и вершинами.*/

//        return result;
        int mulCheck = 1;
        for (int i = 0; i < polygon.size(); i++)
        {
            Point a = polygon.get(i);
            Point b;
            if (i == polygon.size() - 1)
            {
                b = polygon.get(0);
            } else {
                b = polygon.get(i + 1);
            }
            mulCheck *= check(a, b, point);
        }
        return mulCheck != 1;
    }

    private static int check(Point a, Point b, Point point)
    {
        long ax = a.x - point.x;
        long ay = a.y - point.y;
        long bx = b.x - point.x;
        long by = b.y - point.y;
        int s = Long.signum(ax * by - ay * bx);
        if (s == 0 && (ay == 0 || by == 0) && ax * bx <= 0)
            return 0;
        if (ay < 0 ^ by < 0)
        {
            if (by < 0)
                return s;
            return -s;
        }
        return 1;
    }

    public static class Polygon {
        private List<Point> orderedPoints;

        public Polygon(List<Point> pointList) {
            this.orderedPoints = getOrderedPointList(pointList);
        }

        public List<Point> getOrderedPoints() {
            return orderedPoints;
        }

        private List<Point> getOrderedPointList(List<Point> pointList) {
//            List<Point> copyOfPointList = new ArrayList<>(pointList);
//            List<Point> result = new ArrayList<>();
//
//            result.add(copyOfPointList.remove(copyOfPointList.size() - 1));
//            Point currentPoint;
//            int nextIndex;
//            while (copyOfPointList.size() > 0) {
//                currentPoint = result.get(result.size() - 1);
//                nextIndex = getIndexOfCloserPoint(currentPoint, copyOfPointList);
//                result.add(copyOfPointList.remove(nextIndex));
//            }

//            return result;
            return pointList;
        }

        private double getDistance(Point point1, Point point2) {
            double distance = 0;
            double deltaX = point2.x - point1.x;
            double deltaY = point2.y - point1.y;
            distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
            return distance;
        }

        private int getIndexOfCloserPoint(Point point, List<Point> pointList) {
            int index = 0;
            double minDistance = getDistance(point, pointList.get(0));
            double currentDistance = minDistance;
            for (int i = 0; i < pointList.size(); i++) {
                currentDistance = getDistance(point, pointList.get(i));
                if (currentDistance < minDistance) {
                    index = i;
                    minDistance = currentDistance;
                }
            }
            return index;
        }

        public boolean hasPointInside(Point point) {
            List<RealPoint> commonPoints = new ArrayList<>();
            Point startPoint = getStartPoint();
            Line pointLine = new Line(startPoint, point);
            Section section;
            RealPoint commonPoint;
            for (int i = 1; i <= orderedPoints.size(); i++) {
                if (i == orderedPoints.size())
                    section = new Section(orderedPoints.get(0), orderedPoints.get(orderedPoints.size() - 1));
                else
                    section = new Section(orderedPoints.get(i - 1), orderedPoints.get(i));
                if (section.hasPoint(point))
                    return true;
                commonPoint = (RealPoint) section.getCommonPoint(pointLine);
                if (commonPoint != null) {
                    commonPoints.add(commonPoint);
                }
            }
            if (isInnerPoint(point, commonPoints))
                return true;
            return false;
        }

        public boolean hasPointInsideByAngle(Point point) {
            double angle, angleSum = 0;
            Vector vector1, vector2;
            Point point1, point2;
            for (int i = 1; i <= orderedPoints.size(); i++) {
                if (i == orderedPoints.size()) {
                    point1 = orderedPoints.get(i - 1);
                    point2 = orderedPoints.get(0);
                } else {
                    point1 = orderedPoints.get(i);
                    point2 = orderedPoints.get(i - 1);
                }

                vector1 = new Vector(point, point1);
                vector2 = new Vector(point, point2);

                angle = Math.acos(vector1.getCos(vector2));
                angleSum += angle;
            }
            if (angleSum < Math.PI)
                return false;
            else
                return true;
        }

        private boolean isInnerPoint(Point point, List<RealPoint> commonPoints) {
            double x = point.x, y = point.y;
            if (point instanceof RealPoint) {
                x = ((RealPoint) point).getX();
                y = ((RealPoint) point).getY();
            }
            int countLeft = 0, countRight = 0, countTop = 0, countSub = 0;
            for (RealPoint rp : commonPoints) {
                if (rp.getX() < x)
                    countLeft++;
                if (rp.getX() > x)
                    countRight++;
                if (rp.getY() < y)
                    countSub++;
                if (rp.getY() > y)
                    countTop++;
            }
            if ((countLeft != countRight) || (countTop != countSub))
                return false;
            else
                return true;
        }

        private RealPoint getStartPoint() {
            Point point1 = orderedPoints.get(0);
            Point point2 = orderedPoints.get(1);
            double x = (point1.x + point2.x) / 2;
            double y = (point1.y + point2.y) / 2;
            return new RealPoint(x, y);
        }
    }

    public static class Line {
        private double a, b, c;

        public Line(double a, double b, double c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public Line(Point p1, Point p2) {
            setParams(p1, p2);
        }

        public double getA() {
            return a;
        }

        public double getB() {
            return b;
        }

        public double getC() {
            return c;
        }

        public void setParams(Point p1, Point p2) {
            double x1 = p1.x, y1 = p1.y, x2 = p2.x, y2 = p2.y;
            if (p1 instanceof RealPoint) {
                x1 = ((RealPoint) p1).getX();
                y1 = ((RealPoint) p1).getY();
            }
            if (p2 instanceof RealPoint) {
                x2 = ((RealPoint) p2).getX();
                y2 = ((RealPoint) p2).getY();
            }
            if (x1 == x2) {
                this.a = 1;
                this.b = 0;
                this.c = -x1;
            } else if (y1 == y2) {
                this.a = 0;
                this.b = 1;
                this.c = -y1;
            } else {
                this.a = y2 - y1;
                this.b = x1 - x2;
                this.c = x2 * y1 - y2 * x1;
            }

        }

        public Point getCommonPoint(Line line1, Line line2) {
            return new LinearEquationSystem(line1, line2).solve();
        }

        boolean hasPoint(Point point) {
            double x = point.x, y = point.y;
            return ((a * x + b * y + c) == 0);
        }

        public Point getCommonPoint(Line line) {
            return new LinearEquationSystem(this, line).solve();
        }

        @Override
        public String toString() {
            String result = "Line{" +
                    "a=" + a +
                    ", b=" + b +
                    ", c=" + c +
                    '}';
            String equation1 = a + "x + " + b + "y + " + c + " = 0";
            String equation2 = "";
            if (b == 0)
                equation2 = "x = " + (-c / a);
            else if (a == 0)
                equation2 = "y = " + (-c / b);
            else
                equation2 = "y = " + (-a / b) + "x + " + (-c / b);
            result += "\n\t" + equation1 + "\n\t" + equation2;
            return result;
        }
    }

    public static class Section extends Line {
        Point firstPoint, secondPont;

        private Section(double a, double b, double c) {
            super(a, b, c);
        }

        public Section(Point p1, Point p2) {
            super(p1, p2);
            this.firstPoint = p1;
            this.secondPont = p2;
        }

        @Override
        public boolean hasPoint(Point point) {
            boolean result = false;
            double x1 = firstPoint.x, y1 = firstPoint.y;
            double x2 = secondPont.x, y2 = secondPont.y;
            double x = point.x, y = point.y;
            if (point instanceof RealPoint) {
                point = (RealPoint) point;
                x = ((RealPoint) point).getX();
                y = ((RealPoint) point).getY();
            }
            if (x >= x1 && x <= x2 && y >= y1 && y <= y2)
                result = true;
            if (x >= x2 && x <= x1 && y >= y2 && y <= y1)
                result = true;
            return result;
        }

        public boolean hasPointOutside(Point point) {
            return super.hasPoint(point);
        }

        public Point getCommonPoint(Section section1, Section section2) {
            Point commonPoint = super.getCommonPoint(section1, section2);
            if (commonPoint == null)
                return null;
            if (section1.hasPoint(commonPoint) && section2.hasPoint(commonPoint))
                return commonPoint;
            else
                return null;
        }

        public Point getCommonPoint(Section section) {
            return getCommonPoint(this, section);
        }

        public Point getCommonPoint(Section section, Line line) {
            Point commonPoint = super.getCommonPoint(section, line);
            if (commonPoint == null)
                return null;
            if (section.hasPoint(commonPoint))
                return commonPoint;
            else
                return null;
        }

        public Point getCommonPoint(Line line) {
            return getCommonPoint(this, line);
        }
    }


    public static class RealPoint extends Point {
        public double x, y;

        public RealPoint(double x, double y) {
            super(0, 0);
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        @Override
        public String toString() {
            return "RealPoint{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

        public boolean isMatch(Point point) {
            return getX() == point.x && getY() == point.y;
        }
    }

    public static class LinearEquationSystem {
        Line line1, line2;
//        Point eqSolution;

        public LinearEquationSystem(Line line1, Line line2) {
            this.line1 = line1;
            this.line2 = line2;
        }

        public LinearEquationSystem(Point a, Point b, Point c, Point d) {
            this.line1 = new Line(a, b);
            this.line2 = new Line(c, d);
        }

        public Point solve(Line line1, Line line2) {
            double x, y;
            double a1 = line1.getA();
            double b1 = line1.getB();
            double c1 = line1.getC();
            double a2 = line2.getA();
            double b2 = line2.getB();
            double c2 = line2.getC();
            if ((a1 * b2) == (a2 * b1) || a1 == 0 & b1 == 0 || a2 == 0 && b2 == 0)
                return null;
            if (a1 == 0) {
                y = -c1 / b1;
                if (a2 == 0) {
                    return null;
                } else {
                    x = -(b2 * y + c2) / a2;
                    return new RealPoint(x, y);
                }
            } else {
                y = (a1 * c2 - c1 * a2) / (b1 * a2 - b2 * a1);
                x = -(c1 + b1 * y) / a1;
            }

            return new RealPoint(x, y);
        }

        public Point solve() {
            return solve(this.line1, this.line2);
        }
    }

    public static class Vector {
        public double x, y;

        public Vector(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public Vector(Point p1, Point p2) {
            this.x = p2.x - p1.x;
            this.y = p2.y - p1.y;
        }

        public double getCos(Vector a, Vector b) {
            return a.getScalarMult(b) / (a.getLength() * b.getLength());
        }

        public double getCos(Vector vector) {
            return getCos(this, vector);
        }

        public double getScalarMutl(Vector v1, Vector v2) {
            return v1.x * v2.x + v1.y * v2.y;
        }

        public double getScalarMult(Vector vector) {
            return getScalarMutl(this, vector);
        }

        public double getLength() {
            return Math.sqrt(x * x + y * y);
        }
    }

}
