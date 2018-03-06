package ru.stqa.excersize2;

public class SecondExcersize {

  public static void main(String[] args) {

    Point p1 = new Point(1.0, 2.0);
    Point p2 = new Point(5.0, 2.0);

    Point p3 = new Point(0.0, 4.0);
    Point p4 = new Point(5.0, 15.0);

    Point p5 = new Point(-5.0, 10.0);
    Point p6 = new Point(8.0, 22.0);


    System.out.println("Расстояние между точкой p1" + p1 + " и точкой p2" + p2 + " = " + p1.distance(p2));
    System.out.println("Расстояние между точкой p3" + p3 + " и точкой p4" + p4 + " = " + p3.distance(p4));
    System.out.println("Расстояние между точкой p5" + p5 + " и точкой p6" + p6 + " = " + p5.distance(p6));


  }


}
