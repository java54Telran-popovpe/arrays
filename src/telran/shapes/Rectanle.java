package telran.shapes;

public class Rectanle extends Shape {
	
	private int width;
	private int hight;
	
	public Rectanle(long id, int width, int hight) {
		super(id);
		this.width = width;
		this.hight = hight;
	}
	
	

	public int getWidth() {
		return width;
	}



	public int getHight() {
		return hight;
	}



	@Override
	public int square() {
		return width * hight;
	}

	@Override
	public int perimeter() {
		return  ( width + hight ) * 2;
	}

}
