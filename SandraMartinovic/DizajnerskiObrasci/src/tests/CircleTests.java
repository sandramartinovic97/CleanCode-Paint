package tests;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.awt.Graphics;

import org.junit.Before;
import org.junit.Test;

import mvc.Model.Circle;
import mvc.Model.Point;

public class CircleTests {
	private Graphics graphics;
	private int coordinateX;
	private int coordinateY;
	private int radius;
	private Point center;
	private Circle circle;
	
	@Before
	public void setUp() {
		graphics = mock(Graphics.class);
		coordinateX = 1;
		coordinateY = 2;
		radius = 3;
		center = new Point (coordinateX, coordinateY);
		circle = new Circle(center, radius);
	}
	
	@Test
	public void testDrawShapeSelected() {
		circle.setSelected(true);
		circle.draw(graphics);
		verify(graphics).drawOval(coordinateX - radius, coordinateY - radius, radius * 2, radius * 2);
	}
	
	@Test
	public void testDrawShapeNotSelected() {
		circle.draw(graphics);
		verify(graphics).drawOval(coordinateX - radius, coordinateY - radius, radius * 2, radius * 2);
	}
	
	@Test
	public void testEqualsExpectedFalseXcoordinate() {
		assertFalse(circle.equals(new Circle(new Point(2, 2), 3)));
	}

	@Test
	public void testEqualsExpectedFalseYcoordinate() {
		assertFalse(circle.equals(new Circle(new Point(1, 3), 3)));
	}
	
	@Test
	public void testEqualsExpectedFalseRadius() {
		assertFalse(circle.equals(new Circle(new Point(1, 2), 5)));
	}
}
