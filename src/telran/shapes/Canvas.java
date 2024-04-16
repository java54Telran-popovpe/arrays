package telran.shapes;

import java.util.Iterator;
import java.util.NoSuchElementException;

import telran.shapes.exceptions.ShapeAlreadyExistsException;
import telran.shapes.exceptions.ShapeNotFoundException;
import telran.util.Arrays;

public class Canvas extends Shape implements Iterable<Shape> {
	
	protected Shape[] shapes = new Shape[0];

	
	public Canvas( 	long id ) {
		super( id );
	}
	@Override
	public int perimeter() {
		int result = 0;
		for ( Shape shape: shapes )
			result += shape.perimeter();
		return result;
	}
	
	@Override
	public int square() {
		int result = 0;
		for ( Shape shape: shapes )
			result += shape.square();
		return result;
	}
	
	public void addShape( Shape shape ) {
		if ( getIndexOfShapeByID(shape.id) > -1) 
			throw new ShapeAlreadyExistsException( shape.id );
		shapes = Arrays.add(shapes, shape);
	}
	
	public Shape removeShape( long id ) {
		int removedElementIndex = getIndexOfShapeByID( id );
		if ( removedElementIndex < 0 ) {
			throw new ShapeNotFoundException(id);
		}
		Shape removedShape  = shapes[removedElementIndex];
		shapes = Arrays.removeIf(shapes, e -> e.id == id);
		return removedShape;
	}

	private int getIndexOfShapeByID(long id) {
		int i = 0;
		while ( i < shapes.length && shapes[ i ].id != id) {
			i++;
		}
		return i == shapes.length ? -1 : i;
	}
	@Override
	public Iterator<Shape> iterator() {
		return new CanvasIterator();
	}
	
	private class CanvasIterator implements Iterator<Shape> {
		int current = 0;
		@Override
		public boolean hasNext() {
			return current < shapes.length;
		}

		@Override
		public Shape next() {
			if ( !hasNext()) 
				throw new NoSuchElementException();
			return shapes[current++];
		}
		
	}
		
}
