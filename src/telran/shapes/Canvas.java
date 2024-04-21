package telran.shapes;

import java.util.Iterator;
import java.util.NoSuchElementException;

import telran.shapes.exceptions.ShapeAlreadyExistsException;
import telran.shapes.exceptions.ShapeNotFoundException;
import telran.util.Arrays;

public class Canvas extends Shape implements Iterable<Shape> {
	
	protected ShapeContainer shapeContainer = new ShapeContainer();

	
	public Canvas( 	long id ) {
		super( id );
	}
	@Override
	public int perimeter() {
		int result = 0;
		for ( Shape shape: shapeContainer )
			result += shape.perimeter();
		return result;
	}
	
	@Override
	public int square() {
		int result = 0;
		for ( Shape shape: shapeContainer )
			result += shape.square();
		return result;
	}
	
	public void addShape( Shape shape ) {
		shapeContainer.addShape(shape);
	}
	
	public Shape removeShape( long id ) {
		return shapeContainer.removeShape(id);
	}

	@Override
	public Iterator<Shape> iterator() {
		return shapeContainer.iterator();
	}
	
}
