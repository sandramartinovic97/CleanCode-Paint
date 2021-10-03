package mvc.Model;

import java.util.List;
import java.util.ArrayList;

public class Model {

	private List<Shape> shapes = new ArrayList<Shape>();

	public List<Shape> getShapes() {
		return shapes;
	}

	public void addShape(Shape shape) {
		shapes.add(shape);
	}

	public void addShapeOnPosition(Shape shape, int index) {
		shapes.add(index, shape);
	}

	public void removeShape(Shape shape) {
		shapes.remove(shape);
	}

	public void removeAll() {
		shapes.clear();
	}

	public Shape getIndexOfShape(int index) {
		return shapes.get(index);
	}

	public void setShapes(List<Shape> shapes) {
		this.shapes = shapes;
	}

	public void swap(int indexOfPreviousShape, int indexOfShape) {
		Shape helper = shapes.get(indexOfPreviousShape);
		shapes.set(indexOfPreviousShape, shapes.get(indexOfShape));
		shapes.set(indexOfShape, helper);
	}
	
	public boolean doesContainShape(Shape shape) {
		return shapes.contains(shape);
	}
}
