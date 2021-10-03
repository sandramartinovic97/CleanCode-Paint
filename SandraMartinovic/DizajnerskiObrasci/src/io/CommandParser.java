package io;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import commands.CmdAddShape;
import commands.CmdBringToBack;
import commands.CmdBringToFront;
import commands.CmdDeselectShape;
import commands.CmdRemoveShape;
import commands.CmdSelectShape;
import commands.CmdToBack;
import commands.CmdToFront;
import commands.CmdUpdateCircle;
import commands.CmdUpdateDonut;
import commands.CmdUpdateHexagon;
import commands.CmdUpdateLine;
import commands.CmdUpdatePoint;
import commands.CmdUpdateRectangle;
import commands.Command;
import mvc.Model.Circle;
import mvc.Model.Donut;
import mvc.Model.HexagonAdapter;
import mvc.Model.Line;
import mvc.Model.Model;
import mvc.Model.Point;
import mvc.Model.Rectangle;
import mvc.Model.Shape;

public class CommandParser {
	private Model model;

	public CommandParser(Model model) {
		this.model = model;
	}

	public Command parseCommand(String line) throws Exception {
		String command = line.split("_")[0];
		String withoutCommand = line.split("_")[1];
		if (command.equals("Add")) {
			return parseAdd(withoutCommand);
		} else if (command.equals("Remove")) {
			return parseRemove(withoutCommand);
		} else if (command.equals("Update")) {
			return parseUpdate(withoutCommand);
		} else if (command.equals("Bring back")) {
			return parseBringBack(withoutCommand);
		} else if (command.equals("Bring front")) {
			return parseBringFront(withoutCommand);
		} else if (command.equals("Bring to end")) {
			return parseBringToEnd(withoutCommand);
		} else if (command.equals("Bring to front")) {
			return parseBringToFront(withoutCommand);
		} else if (command.equals("Select")) {
			return parseSelect(withoutCommand);
		} else if (command.equals("Deselect")) {
			return parseDeselect(withoutCommand);
		}
		return null;
	}

	private Command parseDeselect(String text) throws Exception {
		String[] shapeStrings = text.split(";");
		List<Shape> helperListOfShapes = new ArrayList<Shape>();
		for (String row : shapeStrings) {
			Shape shape = parseShape(row, true);
			helperListOfShapes.add(shape);
		}
		return new CmdDeselectShape(helperListOfShapes);
	}

	private Command parseSelect(String text) throws Exception {
		Shape shape = parseShape(text, true);
		Command command = new CmdSelectShape(shape);
		return command;
	}

	private Command parseBringToFront(String oldIndex) {
		return new CmdBringToFront(model, Integer.parseInt(oldIndex));
	}

	private Command parseBringToEnd(String oldIndex) {
		return new CmdBringToBack(model, Integer.parseInt(oldIndex));
	}

	private Command parseBringBack(String oldIndex) {
		return new CmdToBack(model, Integer.parseInt(oldIndex));
	}

	private Command parseBringFront(String oldIndex) {
		return new CmdToFront(model, Integer.parseInt(oldIndex));
	}

	private Command parseUpdate(String text) throws Exception {
		Command command = null;
		Shape oldShape = parseShape(text.split(";")[0], false);
		Shape newShape = parseShape(text.split(";")[1], false);
		if (oldShape instanceof Point && newShape instanceof Point) {
			for (Shape shape : this.model.getShapes()) {
				if (shape instanceof Point && ((Point) oldShape).equals((Point) shape)) {
					oldShape = shape;
				}
			}
			command = new CmdUpdatePoint((Point) oldShape, (Point) newShape);
		} else if (oldShape instanceof Line && newShape instanceof Line) {
			for (Shape shape : this.model.getShapes()) {
				if (shape instanceof Line && ((Line) oldShape).equals(shape)) {
					oldShape = shape;
				}
			}
			command = new CmdUpdateLine((Line) oldShape, (Line) newShape);
		} else if (oldShape instanceof Rectangle && newShape instanceof Rectangle) {
			for (Shape shape : this.model.getShapes()) {
				if (shape instanceof Rectangle && ((Rectangle) oldShape).equals((Rectangle) shape)) {
					oldShape = shape;
				}
			}
			command = new CmdUpdateRectangle((Rectangle) oldShape, (Rectangle) newShape);
		} else if (oldShape instanceof Donut && newShape instanceof Donut) {
			for (Shape shape : this.model.getShapes()) {
				if (shape instanceof Donut && ((Donut) oldShape).equals((Donut) shape)) {
					oldShape = shape;
				}
			}
			command = new CmdUpdateDonut((Donut) oldShape, (Donut) newShape);
		} else if (oldShape instanceof Circle && newShape instanceof Circle) {
			for (Shape shape : this.model.getShapes()) {
				if (shape instanceof Circle && ((Circle) oldShape).equals((Circle) shape)) {
					oldShape = shape;
				}
			}
			command = new CmdUpdateCircle((Circle) oldShape, (Circle) newShape);
		} else if (oldShape instanceof HexagonAdapter && newShape instanceof HexagonAdapter) {
			for (Shape shape : this.model.getShapes()) {
				if (shape instanceof HexagonAdapter && ((HexagonAdapter) oldShape).equals((HexagonAdapter) shape)) {
					oldShape = shape;
				}
			}
			command = new CmdUpdateHexagon((HexagonAdapter) oldShape, (HexagonAdapter) newShape);
		}
		return command;
	}

	private Command parseRemove(String text) throws Exception {
		String[] shapeStrings = text.split(";");
		List<Shape> helperListOfShapes = new ArrayList<Shape>();
		for (String row : shapeStrings) {
			Shape shape = parseShape(row, true);
			helperListOfShapes.add(shape);
		}
		return new CmdRemoveShape(helperListOfShapes, model);
	}

	private Command parseAdd(String text) throws Exception {
		Shape shape = parseShape(text, false);
		Command command = new CmdAddShape(shape, model);
		return command;
	}

	private Shape parseShape(String text, boolean isLog) throws Exception {
		String shape = text.split(":")[0];
		String[] props = text.split(",");
		if (shape.equals("Point")) {
			Point point = new Point();
			point.setX(Integer.parseInt(props[0].split("=")[1]));
			point.setY(Integer.parseInt(props[1].split("=")[1]));
			point.setOutlineColor(new Color(Integer.parseInt(props[2].split("=")[1])));
			point.setSelected(Boolean.parseBoolean(props[3].split("=")[1]));
			if (isLog) {
				for (Shape test : this.model.getShapes()) {
					if (test instanceof Point && point.equals((Point) test)) {
						return test;
					}
				}
			} else {
				return point;
			}
		} else if (shape.equals("Line")) {
			Line line = new Line();
			line.setStartPoint(
					new Point(Integer.parseInt(props[0].split("=")[1]), Integer.parseInt(props[1].split("=")[1])));
			line.setEndPoint(
					new Point(Integer.parseInt(props[2].split("=")[1]), Integer.parseInt(props[3].split("=")[1])));
			line.setOutlineColor(new Color(Integer.parseInt(props[4].split("=")[1])));
			line.setSelected(Boolean.parseBoolean(props[5].split("=")[1]));
			if (isLog) {
				for (Shape test : this.model.getShapes()) {
					if (test instanceof Line && line.equals((Line) test)) {
						return test;
					}
				}
			} else {
				return line;
			}
		} else if (shape.equals("Rectangle")) {
			Rectangle rectangle = new Rectangle();
			rectangle.setUpperLeftPoint(
					new Point(Integer.parseInt(props[0].split("=")[1]), Integer.parseInt(props[1].split("=")[1])));
			rectangle.setHeight(Integer.parseInt(props[2].split("=")[1]));
			rectangle.setWidth(Integer.parseInt(props[3].split("=")[1]));
			rectangle.setOutlineColor(new Color(Integer.parseInt(props[4].split("=")[1])));
			rectangle.setInnerColor(new Color(Integer.parseInt(props[5].split("=")[1])));
			rectangle.setSelected(Boolean.parseBoolean(props[6].split("=")[1]));
			if (isLog) {
				for (Shape test : this.model.getShapes()) {
					if (test instanceof Rectangle && rectangle.equals((Rectangle) test)) {
						return test;
					}
				}
			} else {
				return rectangle;
			}
		} else if (shape.equals("Circle")) {
			Circle circle = new Circle();
			circle.setCenter(
					new Point(Integer.parseInt(props[0].split("=")[1]), Integer.parseInt(props[1].split("=")[1])));
			circle.setRadius(Integer.parseInt(props[2].split("=")[1]));
			circle.setOutlineColor(new Color(Integer.parseInt(props[3].split("=")[1])));
			circle.setInnerColor(new Color(Integer.parseInt(props[4].split("=")[1])));
			circle.setSelected(Boolean.parseBoolean(props[5].split("=")[1]));
			if (isLog) {
				for (Shape test : this.model.getShapes()) {
					if (test instanceof Circle && circle.equals((Circle) test)) {
						return test;
					}
				}
			} else {
				return circle;
			}
		} else if (shape.equals("Donut")) {
			Donut donut = new Donut();
			donut.setCenter(
					new Point(Integer.parseInt(props[0].split("=")[1]), Integer.parseInt(props[1].split("=")[1])));
			donut.setRadius(Integer.parseInt(props[2].split("=")[1]));
			donut.setInnerRadius(Integer.parseInt(props[3].split("=")[1]));
			donut.setOutlineColor(new Color(Integer.parseInt(props[4].split("=")[1])));
			donut.setInnerColor(new Color(Integer.parseInt(props[5].split("=")[1])));
			donut.setSelected(Boolean.parseBoolean(props[6].split("=")[1]));
			if (isLog) {
				for (Shape test : this.model.getShapes()) {
					if (test instanceof Donut && donut.equals((Donut) test)) {
						return test;
					}
				}
			} else {
				return donut;
			}
		} else if (shape.equals("Hexagon")) {
			HexagonAdapter hexagonAdapter = new HexagonAdapter(
					new Point(Integer.parseInt(props[0].split("=")[1]), Integer.parseInt(props[1].split("=")[1])),
					Integer.parseInt(props[2].split("=")[1]));
			hexagonAdapter.setOutlineColor(new Color(Integer.parseInt(props[3].split("=")[1])));
			hexagonAdapter.setInnerColor(new Color(Integer.parseInt(props[4].split("=")[1])));
			hexagonAdapter.setSelected(Boolean.parseBoolean(props[5].split("=")[1]));
			if (isLog) {
				for (Shape test : this.model.getShapes()) {
					if (test instanceof HexagonAdapter && hexagonAdapter.equals((HexagonAdapter) test)) {
						return test;
					}
				}
			} else {
				return hexagonAdapter;
			}
		}
		return null;
	}

}
