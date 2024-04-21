package telran.shapes;

import java.util.Iterator;
import java.util.NoSuchElementException;

import telran.shapes.exceptions.ShapeAlreadyExistsException;
import telran.shapes.exceptions.ShapeNotFoundException;
import telran.util.Arrays;

public class ShapeContainer implements Iterable<Shape> {
	private Shape[] shapes = new Shape[0];
	
	public void addShape( Shape shape ) {
		if ( Arrays.indexOf(shapes, shape) > -1 ) {
			throw new ShapeAlreadyExistsException( shape.id);
		}
		shapes = Arrays.add(shapes, shape);
	}
	
	public Shape removeShape( long id ) {
		int removedElementIndex = Arrays.indexOf(shapes, new Canvas(id));
		if ( removedElementIndex < 0 ) {
			throw new ShapeNotFoundException(id);
		}
		Shape removedShape =  shapes[removedElementIndex];
		shapes = Arrays.removeIf(shapes, e -> e.id == id);
		return removedShape;
	}

	@Override
	public Iterator<Shape> iterator() {
		// TODO Auto-generated method stub
		return new Iterator<Shape>() {

			private int current = 0;
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return current < shapes.length;
			}

			@Override
			public Shape next() {
				if ( !hasNext() )
					throw new NoSuchElementException();
				return shapes[ current++ ];
			}
		};
	}
	

}
