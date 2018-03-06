package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.excersize2.Point;

public class PointTests {


  @Test
  public void testDistance(){
    Point p1 = new Point(2,7);
    Point p2 = new Point(-2,7);
    Point p3 = new Point(-5,1);
    Point p4 = new Point(-5,-7);
    Point p5 = new Point(0,3);
    Point p6 = new Point(-4,0);



    Assert.assertEquals(p1.distance(p2),4.0);
    Assert.assertEquals(p3.distance(p4),8.0);
    Assert.assertEquals(p5.distance(p6),5.0);

  }
}


