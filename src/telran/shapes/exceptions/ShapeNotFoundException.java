package telran.shapes.exceptions;

@SuppressWarnings("serial")
public class ShapeNotFoundException extends RuntimeException {
	public ShapeNotFoundException( long id ) {
		super( String.format( "Shape with id %d not found", id));
	}

}
