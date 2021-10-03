package tests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.awt.Color;
import java.awt.Graphics;

import org.junit.Before;
import org.junit.Test;

import mvc.Model.Line;
import mvc.Model.Point;

public class LineTests {
	private Graphics graphics;
	private int startPointCoordinateX;
	private int startPointCoordinateY;
	private int endPointCoordinateX;
	private int endPointCoordinateY;
	private Color outlineColor;
	private Line line;
	
	@Before
	public void setUp() {
		graphics = mock(Graphics.class);
		startPointCoordinateX = 1;
		startPointCoordinateY = 2;
		endPointCoordinateX = 3;
		endPointCoordinateY = 4;
		outlineColor = Color.PINK;
		line = new Line(new Point(startPointCoordinateX, startPointCoordinateY, outlineColor), new Point(endPointCoordinateX, endPointCoordinateY, outlineColor), outlineColor);
	}
	
	@Test
	public void testDrawShapeSelected() {
		line.setSelected(true);
		line.draw(graphics);
		verify(graphics).setColor(outlineColor);
		verify(graphics).drawLine(startPointCoordinateX, startPointCoordinateY, endPointCoordinateX, endPointCoordinateY);
		verify(graphics).setColor(Color.PINK);
	}
	
	@Test
	public void testDrawShapeNotSelected() {
		line.draw(graphics);
		verify(graphics).setColor(outlineColor);
		verify(graphics).drawLine(startPointCoordinateX, startPointCoordinateY, endPointCoordinateX, endPointCoordinateY);
		verify(graphics).setColor(Color.BLACK);
	}

}
