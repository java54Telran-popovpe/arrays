package telran.employees;

public class Manager extends Employee {
	private float factor;
	public Manager(long id, int basicSalary, String department, float factor) {
		super(id, basicSalary, department);
		this.factor = factor;
	}
	public float getFactor() {
		return factor;
	}
	public void setFactor(float factor) {
		this.factor = factor;
	}
	
	@Override
	public int computeSalary() {
		return Math.round( super.computeSalary() * factor );
	}

}
