package tests;

import static org.junit.Assert.*;
import org.junit.*;

import commands.CmdAddShape;
import mvc.Model.Model;
import mvc.Model.Point;

public class CmdAddShapeTest {
	private Model model;
	private Point point;
	private CmdAddShape cmdAddShape;

	@Before
	public void setUp() {
		model = new Model();
		point = new Point(1, 2);
		cmdAddShape = new CmdAddShape(point, model);
	}

	@Test
	public void testExecute() {
		cmdAddShape.execute();
		assertTrue(model.doesContainShape(point));
	}

}
