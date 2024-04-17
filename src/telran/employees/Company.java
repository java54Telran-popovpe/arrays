package telran.employees;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import telran.util.Arrays;

public class Company implements Iterable<Employee>{
	
	private Employee[] employees;
	
	public void addEmployee( Employee employee ) {
		if ( getEmployee( employee.getId() ) != null) {
			throw new IllegalStateException();
		}
		employees = Arrays.insertSorted(employees,  employee, Comparator.naturalOrder());
	}
	
	public Company( Employee[] employees) {
		this.employees  = Arrays.copy(employees);
		Arrays.bubbleSort(this.employees);
	}

	@Override
	public Iterator<Employee> iterator() {
		return new CompanyIterator();
	}
	
	public Employee getEmployee( long id) {
		int resultOfSearch = Arrays.binarySearch(employees, new Employee( id, 0, null), Comparator.naturalOrder());
		return resultOfSearch < 0 ? null : employees[resultOfSearch];
	}
	
	public Employee removeEmployee(long id) {

		Employee employeeToRemove = getEmployee(id);
		if ( employeeToRemove == null ) {
			throw new NoSuchElementException();
		}
		employees = Arrays.removeIf(employees, e -> e.getId() == id );
		return employeeToRemove;
	}
	
	public int getDepartmentBudget( String department) {
		int result = 0;
		for ( Employee emp: employees ) {
			if ( emp.getDepartment().equals(department) )
				result += emp.computeSalary();
		}
		return result;
	}
	
	public String[] getDepartments() {
		String[]  result = new String[0];
		for ( Employee emp: employees ) {
			String department = emp.getDepartment();
			if ( Arrays.indexOf(result, department) < 0 )
				result = Arrays.add(result, department);
		}
		return result;
	}
	
	private class CompanyIterator implements Iterator<Employee> {
		private int current = 0;
		@Override
		public boolean hasNext() {
			return current < employees.length;
		}
		@Override
		public Employee next() {
			if ( !hasNext()) 
				throw new NoSuchElementException();
			return employees[current++];
			
		}
	}
	 public Manager[] getManagersWithMostFactor() {
		 
		 Employee[] empManagers =  Arrays.search(employees, emp -> emp instanceof Manager);
		 Manager[] managers = new Manager[ empManagers.length];
		 for ( int i = 0; i < empManagers.length; i++ )
			 managers[ i ] = (Manager) empManagers[ i ];

		 final float mostFactor = getMostFactor(managers) ;
		 return Arrays.search(managers,  manager -> manager.getFactor() == mostFactor );
	 }
	 
	 private float getMostFactor( Manager[] managers) {
		 float mostFactor = 0f;
		 for ( int i = 0; i < managers.length; i++ ) {
			 mostFactor = Math.max(managers[i].getFactor(), mostFactor);
		 }
		 return mostFactor;
	 }
}
