package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import commands.*;
import mvc.Model.Model;
import mvc.Model.Point;
import mvc.Model.Shape;

public class CmdSelectShapeTests {
	private Model model;
	private Point point;
	private Shape shape;
	private CmdSelectShape cmdSelectShape;

	@Before
	public void setUp() {
		model = new Model();
		shape = new Point(4, 5);
		model.addShape(point);
		cmdSelectShape = new CmdSelectShape(shape);
	}

	@Test
	public void testExecuteShapeAddedToSelectedShapes() {
		cmdSelectShape.execute();
		assertTrue(model.doesContainShape(point));
	}
	
	@Test
	public void testExecuteShapeRemovedToSelectedShapes() {
		cmdSelectShape.unexecute();
		assertTrue(model.doesContainShape(point));
	}

}
