package telran.shapes;

import java.util.Iterator;
import java.util.NoSuchElementException;

import telran.util.Arrays;

public class Canvas extends Shape implements Iterable<Shape> {
	
	private Shape[] shapes;

	
	public Canvas( 	long id, Shape[] shapes ) {
		super( id );
		this.shapes = Arrays.copy( shapes );
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
		if ( this.id == shape.getId() || Arrays.indexOf(shapes, shape) > -1 )
			throw new IllegalStateException( String.format( "Error adding shape ID %d: shape always exists", id ) );
		shapes = Arrays.add(shapes, shape);
	}
	
	public Shape removeShape( long id ) {
		int i = 0;
		while( i < shapes.length && shapes[i].getId() != id ) {
			i++;
		};
		if (i == shapes.length ) {
			throw new NoSuchElementException( String.format("Error removing shape ID %d: shape not exists", id));
		}
		Shape removedShape = shapes[i];
		shapes = Arrays.removeIf(shapes, e -> e.getId() == id);
		return removedShape;
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
