package telran.shapes.exceptions;

@SuppressWarnings("serial")
public class NoCanvasException extends RuntimeException {
	public NoCanvasException( long id ) {
		super( String.format( "The shape with id %d as not a canvas", id ));
	}
}
