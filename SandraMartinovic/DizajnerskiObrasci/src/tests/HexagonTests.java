package tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.awt.Graphics;

import org.junit.*;

import mvc.Model.HexagonAdapter;
import mvc.Model.Point;

public class HexagonTests {
	private Graphics graphics;
	private HexagonAdapter hexagonAdapter;
	private int radius;
	private int coordinateX;
	private int coordinateY;
	
	@Before
	public void setUp() {
		graphics = mock(Graphics.class);
		radius = 3;
		coordinateX = 1;
		coordinateY = 2;
		hexagonAdapter = new HexagonAdapter(new Point(coordinateX, coordinateY), radius);
	}
	
	@Test
	public void testEqualsExpectedFalseRadius() {
		assertFalse(hexagonAdapter.equals(new HexagonAdapter(new Point(1, 2), 4)));
	}
	
	@Test
	public void testContainsTrueExcepted() {
		assertTrue(hexagonAdapter.contains(new Point(1, 2)));
	}
	
	@Test
	public void testContainsfalseExcepted() {
		assertFalse(hexagonAdapter.contains(new Point(35, 44)));
	}

}
