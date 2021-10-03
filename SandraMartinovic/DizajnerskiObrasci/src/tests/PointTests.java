package tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.awt.Color;
import java.awt.Graphics;

import org.junit.Before;
import org.junit.Test;

import mvc.Model.Point;

public class PointTests {
	private Graphics graphics;
	private int coordinateX;
	private int coordinateY;
	private Color outlineColor;
	private Point point;
	
	@Before
	public void setUp() {
		graphics = mock(Graphics.class);
		coordinateX = 1;
		coordinateY = 2;
		outlineColor = Color.RED;
		point = new Point(coordinateX, coordinateY, outlineColor);
	}
	
	@Test
	public void testDrawShapeSelected() {
		point.setSelected(true);
		point.draw(graphics);
		verify(graphics).setColor(outlineColor);
		verify(graphics).drawLine(coordinateX - 1, coordinateY - 1, coordinateX + 1, coordinateY + 1);
		verify(graphics).drawLine(coordinateX - 1, coordinateY + 1, coordinateX + 1, coordinateY - 1);
		verify(graphics).setColor(Color.BLUE);
	}
	
	@Test
	public void testDrawShapeNotSelected() {
		point.draw(graphics);
		verify(graphics).setColor(outlineColor);
		verify(graphics).drawLine(coordinateX - 1, coordinateY - 1, coordinateX + 1, coordinateY + 1);
		verify(graphics).drawLine(coordinateX - 1, coordinateY + 1, coordinateX + 1, coordinateY - 1);
	}
	
	@Test
	public void testEqualsExpectedTrue() {
		assertTrue(point.equals(new Point(1, 2, Color.RED)));
	}
	
	@Test
	public void testEqualsExpectedFalseXcoordinate() {
		assertFalse(point.equals(new Point(2, 2, Color.RED)));
	}
	
	@Test
	public void testEqualsExpectedFalseYcoordinate() {
		assertFalse(point.equals(new Point(1, 4, Color.RED)));
	}
	
	@Test
	public void testEqualsExpectedFalseColor() {
		assertFalse(point.equals(new Point(1, 2, Color.GREEN)));
	}

}
