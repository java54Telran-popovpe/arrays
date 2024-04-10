package telran.employees;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import telran.util.Arrays;

public class Company implements Iterable<Employee>{
	private Employee[] employees;
	public void addEmployee( Employee employee ) {
		employees = Arrays.insertSorted(employees,  employee, (a,b)-> a.compareTo(b));
	}
	
	public Company( Employee[] employees) {
		this.employees  = Arrays.copy(employees);
		Arrays.bubbleSort(this.employees, (a,b)-> a.compareTo(b));
	}

	@Override
	public Iterator<Employee> iterator() {
		return new CompanyIterator();
	}
	
	public Employee getEmployee( long id) {
		Employee result = null;
		int resultOfSearch = -1;
		Comparator<Employee> cmp = (a,b) -> a.compareTo(b);
		if ( ( resultOfSearch = Arrays.binarySearch(employees, new Employee( id, 0, null), cmp) ) > -1 ) {
			result = new Employee(	employees[resultOfSearch].getId(), 
									employees[resultOfSearch].getBasicSalary(),
									employees[resultOfSearch].getDepartment());
		}
		return result;
	}
	
	public Employee removeEmployee(long id) {
		Employee result = null;
		int resultOfSearch = -1;
		Comparator<Employee> cmp = (a,b) -> a.compareTo(b);
		if ( ( resultOfSearch = Arrays.binarySearch(employees, new Employee( id, 0, null), cmp) ) < 0 ) {
			throw new NoSuchElementException();
		}
		result = employees[resultOfSearch];
		Employee[] resultingArray = new Employee[ employees.length -1  ];
		java.lang.System.arraycopy(employees, 0, resultingArray, 0, resultOfSearch);
		java.lang.System.arraycopy(employees, resultOfSearch + 1, resultingArray, resultOfSearch, employees.length - resultOfSearch - 1 );
		employees = resultingArray;
		return result;
	}
	
	public int getDepartmentBudget( String department) {
		int result = 0;
		for ( int i = 0; i < employees.length; i++ ) {
			if ( employees[i].getDepartment() == department )
				result += employees[i].getBasicSalary();
		}
		return result;
	}
	
	private class CompanyIterator implements Iterator<Employee> {
		private long current = 0;
		@Override
		public boolean hasNext() {
			return current <= employees.length - 1;
		}
		@Override
		public Employee next() {
			Employee currentEmployee = employees[(int) current++];
			return new Employee(currentEmployee.getId(),currentEmployee.getBasicSalary(), currentEmployee.getDepartment());
		}
		
	}
	
 
}
