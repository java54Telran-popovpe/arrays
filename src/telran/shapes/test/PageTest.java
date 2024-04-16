package telran.shapes.test;


import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.shapes.Canvas;
import telran.shapes.Page;
import telran.shapes.Rectanle;
import telran.shapes.Shape;
import telran.shapes.Square;
import telran.shapes.exceptions.NoCanvasException;
import telran.shapes.exceptions.ShapeAlreadyExistsException;
import telran.shapes.exceptions.ShapeNotFoundException;
import telran.util.Arrays;

class PageTest {
	Rectanle rectangle = new Rectanle(100, 10, 50);
	Square square = new Square(101, 20);
	Canvas canvas, canvas1;
	Page page;
	
	@BeforeEach
	void init() {
		page = new Page();
		canvas = new Canvas(201);
		canvas.addShape(new Rectanle(202, 5, 10));
		canvas.addShape(new Square(203, 4));
		canvas1 = new Canvas(301);
		canvas1.addShape(new Rectanle(302, 5, 10) );
		canvas1.addShape( new Square(303, 4) );
		canvas1.addShape(canvas);
		page.addShape(canvas1);
		page.addShape( new Square(50, 1));
	}

	@Test
	void testAddShapeShape() {
		assertEquals(2, numberOfShapes(page));
		assertTrue(compareArrayWithPageContent(new Shape[] {canvas1, new Square(50, 1)}, page) );
		page.addShape(new Rectanle(51, 4, 6));
		assertTrue(compareArrayWithPageContent(new Shape[] {canvas1, new Square(50, 1), new Rectanle(51, 4, 6)}, page) );
		assertTrue(!compareArrayWithPageContent(new Shape[] {canvas1, new Square(49, 1), new Rectanle(51, 4, 6)}, page) );
		assertThrowsExactly(ShapeAlreadyExistsException.class, () -> page.addShape(new Rectanle(51, 18, 4)));
	}

	@Test
	void testAddShapeLongArray() {
		assertTrue(compareArrayWithCanvasContent(new Shape[] {new Rectanle(202, 5, 10), new Square(203, 4)}, canvas) );
		page.addShape( new long[] {301, 201}, new Square(204, 10));
		assertTrue(compareArrayWithCanvasContent(new Shape[] {new Rectanle(202, 5, 10), new Square(203, 4), new Square(204, 10)}, canvas) );
		page.addShape( new long[] {301}, new Square(304, 10));
		assertTrue(compareArrayWithCanvasContent(new Shape[] {new Rectanle(302, 5, 10), new Square(303, 4), new Square(304, 10), canvas}, canvas1) );
		
		assertThrowsExactly(ShapeNotFoundException.class, () -> page.addShape(new long[] {301, 201, 101}, new Canvas(20)));
		assertThrowsExactly(ShapeNotFoundException.class, () -> page.addShape(new long[] {310, 201, 101}, new Canvas(20)));
		assertThrowsExactly(NoCanvasException.class, () -> page.addShape(new long[] {301, 201, 203}, new Canvas(20)));
	}

	@Test
	void testRemoveShapeLong() {
		Shape deleted = page.removeShape(301);
		assertEquals(canvas1, deleted);
		assertTrue(compareArrayWithPageContent(new Shape[] {new Square(50, 1)}, page) );
		assertThrowsExactly(ShapeNotFoundException.class, () -> page.removeShape(0));
	}

	@Test
	void testRemoveShapeLongArrayLong() {
		assertTrue(compareArrayWithCanvasContent(new Shape[] {new Rectanle(202, 5, 10), new Square(203, 4)}, canvas) );
		Shape deleted = page.removeShape( new long[] {301, 201}, 203);
		assertEquals(new Square(203, 4), deleted);
		assertTrue(compareArrayWithCanvasContent(new Shape[] {new Rectanle(202, 5, 10) }, canvas) );
		page.removeShape( new long[] {301}, 303);
		assertTrue(compareArrayWithCanvasContent(new Shape[] {new Rectanle(302, 5, 10), canvas}, canvas1) );
		assertThrowsExactly(ShapeNotFoundException.class, () -> page.removeShape( new long[] {301, 201}, 203));
		assertThrowsExactly(ShapeNotFoundException.class, () -> page.removeShape( new long[] {301, 78}, 203));
	}

	@Test
	void testIterator() {
		assertTrue(compareArrayWithPageContent(new Shape[] {canvas1, new Square(50, 1),}, page) );
	}
	
		
	private int numberOfShapes( Page page) {
		int result = 0;
		for ( @SuppressWarnings("unused") Shape shape: page) {
			result++;
		}
		return result;
	}
	
	private boolean arraysContainSameShape( Shape[] shapes1, Shape[] shapes2) {
		boolean equals = shapes1.length == shapes2.length;
		if ( equals ) {
			int i = 0;
			while ( i < shapes1.length && equals ) {
				equals = Arrays.indexOf(shapes2, shapes1[i]) > -1 ? true : false;
				i++;
			}
		}
		return equals;
		
	}
	private boolean compareArrayWithPageContent( Shape[] shapes1, Page page ) {
		Shape[] shapesOfPage = {};
		for ( Shape shape: page) {
			shapesOfPage = Arrays.add(shapesOfPage, shape);
		}
		return arraysContainSameShape(shapes1, shapesOfPage);
	}
	private boolean compareArrayWithCanvasContent( Shape[] shapes1, Canvas canvas ) {
		Shape[] shapesOfCanvas = {};
		for ( Shape shape: canvas) {
			shapesOfCanvas = Arrays.add(shapesOfCanvas, shape);
		}
		return arraysContainSameShape(shapes1, shapesOfCanvas);
	}

}
