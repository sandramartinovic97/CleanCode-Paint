package tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.awt.*;
import java.awt.geom.*;

import org.junit.*;

import mvc.Model.Donut;
import mvc.Model.Point;

public class DonutTests {
	private Graphics2D graphics;
	private int coordinateX;
	private int coordinateY;
	private int outerRadius;
	private int innerRadius;
	private Color outlineColor;
	private Color inlineColor;
	private Donut donut;
	private Area area;
	
	@Before
	public void setUp() throws Exception {
		graphics = mock(Graphics2D.class);
		coordinateX = 1;
		coordinateY = 2;
		outerRadius = 3;
		innerRadius = 2;
		outlineColor = Color.BLACK;
		inlineColor = Color.WHITE;

		donut = new Donut(new Point(coordinateX, coordinateY), outerRadius, innerRadius);

		area = new Area(new Ellipse2D.Double(coordinateX - outerRadius, coordinateY - outerRadius, outerRadius * 2, outerRadius * 2));
		area.subtract(new Area(new Ellipse2D.Double((coordinateX - innerRadius), (coordinateY - innerRadius), innerRadius * 2, innerRadius * 2)));
	}
	
	@Test
	public void testEqualsExpectedFalseOuterRadius() throws Exception {
		assertFalse(donut.equals(new Donut(new Point(1, 2), 4, 2)));
	}
	
	@Test
	public void testEqualsExpectedFalseInnerRadius() throws Exception {
		assertFalse(donut.equals(new Donut(new Point(1, 2), 3, 1)));
	}
}
