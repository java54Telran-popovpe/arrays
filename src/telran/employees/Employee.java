package telran.employees;

import java.util.Objects;

public class Employee implements Comparable<Employee>{
	private long id;
	private int basicSalary;
	private String department;
	
	@Override
	public int compareTo(Employee o) {
		return Long.compare(id, o.id);
	}

	public long getId() {
		return id;
	}

	
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
		Employee other = (Employee) obj;
		return id == other.id;
	}

	public int getBasicSalary() {
		return basicSalary;
	}

	public String getDepartment() {
		return new String(department);
	}
	public int computeSalary() {
		return getBasicSalary();
	}

	public Employee(long id, int basicSalary, String department) {
		this.id = id;
		this.basicSalary = basicSalary;
		this.department = department;
	}
	
	
	
}
