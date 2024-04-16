package telran.shapes.test;


import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

import telran.shapes.Rectanle;
import telran.shapes.Square;
import telran.shapes.exceptions.ShapeAlreadyExistsException;
import telran.shapes.exceptions.ShapeNotFoundException;
import telran.util.Arrays;
import telran.shapes.Shape;
import telran.shapes.Canvas;

class ShapesTest {
	Rectanle rectangle = new Rectanle(100, 10, 50);
	Square square = new Square(101, 20);
	@Test
	void testRectangle() {
		assertEquals( 120, rectangle.perimeter());
		assertEquals( 500, rectangle.square());
	}
	@Test
	void testSquare() {
		assertEquals(80, square.perimeter());
		assertEquals( 400, square.square());
	}
	@Test
	void testCanvas() {
		Canvas canvas = new Canvas(201);
		canvas.addShape(new Rectanle(202, 5, 10));
		canvas.addShape(new Square(203, 4));
		assertEquals( 46, canvas.perimeter());
		assertEquals( 66, canvas.square());
		Canvas canvas1 = new Canvas(301);
		canvas1.addShape(new Rectanle(302, 5, 10) );
		canvas1.addShape( new Square(303, 4) );
		canvas1.addShape(canvas);
		
		assertEquals(3, numberOfShapes(canvas1));
		assertEquals( 92, canvas1.perimeter());
		assertEquals( 132, canvas1.square());
		assertThrowsExactly(ShapeAlreadyExistsException.class, () -> canvas1.addShape(new Rectanle(302, 8, 4)) );
		canvas1.addShape(new Rectanle(304, 8, 4));
		assertEquals(4, numberOfShapes(canvas1));
		assertEquals( 116, canvas1.perimeter());
		assertEquals( 164, canvas1.square());
		canvas1.removeShape(302);
		assertEquals(3, numberOfShapes(canvas1));
		assertEquals( 116 - 30, canvas1.perimeter());
		assertEquals( 164 - 50, canvas1.square());
		
		Iterator<Shape> itr = canvas1.iterator();
		Shape[] resultOfIterator = new Shape[0];
		while( itr.hasNext())
			resultOfIterator = Arrays.add(resultOfIterator, itr.next());
		assertEquals(3, resultOfIterator.length);
		for ( Shape shape: resultOfIterator)
			assertTrue(Arrays.indexOf(new Long[]{ 303l, 201l, 304l}, shape.getId() ) > -1 );
		assertThrowsExactly(ShapeNotFoundException.class, () -> canvas1.removeShape(0) );
		canvas1.removeShape(303);
		Shape removedShape = canvas1.removeShape(201);
		assertEquals(201, removedShape.getId());
		canvas1.removeShape(304);
		assertEquals(0, numberOfShapes(canvas1));
	}
	private int numberOfShapes( Canvas canvas) {
		int result = 0;
		for ( @SuppressWarnings("unused") Shape shape: canvas) {
			result++;
		}
		return result;
		
	}

}
