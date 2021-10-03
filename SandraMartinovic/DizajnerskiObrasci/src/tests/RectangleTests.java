package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.awt.Color;
import java.awt.Graphics;

import org.junit.Before;
import org.junit.Test;

import mvc.Model.Point;
import mvc.Model.Rectangle;

public class RectangleTests {
	private Graphics graphics;
	private int coordinateX;
	private int coordinateY;
	private int height;
	private int width;
	private Color outlineColor;
	private Color inlineColor;
	private Rectangle rectangle;

	@Before
	public void setUp() {
		graphics = mock(Graphics.class);
		coordinateX = 1;
		coordinateY = 2;
		height = 1;
		width = 2;
		outlineColor = Color.BLACK;
		inlineColor = Color.YELLOW;
		rectangle = new Rectangle(new Point(coordinateX, coordinateY), height, width, outlineColor);
	}
	
	@Test
	public void testDrawShapeSelected() {
		rectangle.setSelected(true);
		rectangle.draw(graphics);
		verify(graphics).drawRect(coordinateX, coordinateY, width, height);
	}
	
	@Test
	public void testDrawShapeNotSelected() {
		rectangle.draw(graphics);
		verify(graphics).drawRect(coordinateX, coordinateY, width, height);
	}
	
	@Test
	public void testEqualsExpectedFalseHeight() {
		assertFalse(rectangle.equals(new Rectangle(new Point(1, 2), 2, 2)));
	}
	
	@Test
	public void testEqualsExpectedFalseWidth() {
		assertFalse(rectangle.equals(new Rectangle(new Point(1, 2), 1, 4)));
	}
	
	@Test
	public void testContainsTrueExcepted() {
		assertTrue(rectangle.contains(new Point (1, 2)));
	}
	
	@Test
	public void testContainsFalseExcepted() {
		assertFalse(rectangle.contains(new Point (31, 20)));
	}
}
