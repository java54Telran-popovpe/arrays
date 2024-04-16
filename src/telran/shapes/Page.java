package telran.shapes;

import java.util.Iterator;
import java.util.NoSuchElementException;

import telran.shapes.exceptions.NoCanvasException;
import telran.shapes.exceptions.ShapeAlreadyExistsException;
import telran.shapes.exceptions.ShapeNotFoundException;
import telran.util.Arrays;

public class Page implements Iterable<Shape> {
	private Shape[] shapes = new Shape[0];
	
	public void addShape( Shape shape ) {
		if ( Arrays.indexOf(shapes, shape) > -1 ) {
			throw new ShapeAlreadyExistsException( shape.id);
		}
		shapes = Arrays.add(shapes, shape);
	}
	
	public void addShape( long[] canvasIDs, Shape shape) {
		Canvas canvas = getCanvas( canvasIDs );
		canvas.addShape(shape);
	}
	
	private Canvas getCanvas( long[] canvasIDs ) {
		Canvas canvas = getCanvasByID(shapes, canvasIDs[0]);
		for ( int i = 1; i < canvasIDs.length; i++ ) {
			canvas = getCanvasByID( canvas.shapes, canvasIDs[i]);
		}
		return canvas;
	}

	private Canvas getCanvasByID(Shape[] shapes, long id) {
		int index = getIndexOfShapeByID( id, shapes);
		if ( index < 0)
			throw new ShapeNotFoundException(index);
		Shape shape = shapes[index];
		Canvas result = null;
		if ( shape instanceof Canvas )
			result = (Canvas) shape;
		else
			throw new NoCanvasException(index);
		
		return result;
	}

	public Shape removeShape( long id ) {
		Shape removedShape = checkDeletionPossibility( id, shapes);
		shapes = Arrays.removeIf(shapes, e -> e.id == id);
		return removedShape;
	}
	private Shape checkDeletionPossibility( long id, Shape[] shapes ) {
		int removedElementIndex = getIndexOfShapeByID( id, shapes );
		if ( removedElementIndex < 0 ) {
			throw new ShapeNotFoundException(id);
		}
		return shapes[removedElementIndex];
	}

	private int getIndexOfShapeByID(long id, Shape[] shapes) {
		int i = 0;
		while ( i < shapes.length && shapes[ i ].id != id) {
			i++;
		}
		return i == shapes.length ? -1 : i;
	}
	
	public Shape removeShape( long[] canvasIDs, long id ) {
		Canvas canvas = getCanvas( canvasIDs );
		Shape removedShape = checkDeletionPossibility( id, canvas.shapes );
		canvas.shapes = Arrays.removeIf(canvas.shapes, e -> e.id == id);
		return removedShape;
	}

	@Override
	public Iterator<Shape> iterator() {

		return new PageIterator();
	}
	
	private class PageIterator implements Iterator<Shape> {
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
