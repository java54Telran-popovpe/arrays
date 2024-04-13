package telran.shapes;

import java.util.Objects;

abstract public class Shape {
	protected long id;

	public long getId() {
		return id;
	}
	
	public Shape( long id) {
		this.id = id;
	}
	
	abstract public int square();
	
	abstract public int perimeter();

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Shape other = (Shape) obj;
		return id == other.id;
	}
	
	

}
