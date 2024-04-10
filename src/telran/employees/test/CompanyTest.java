package telran.employees.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.*;

import telran.employees.Company;
import telran.employees.Employee;

class CompanyTest {
	private static final long ID1 = 123;
	private static final int SALARY1 = 1000;
	private static final String DEPARTMENT1 = "Development";
	private static final long ID2 = 124;
	private static final long ID3 = 125;
	private static final int SALARY2 = 2000;
	private static final int SALARY3 = 3000;
	private static final String DEPARTMENT2 = "QA";
	Employee employee1 = new Employee(ID1, SALARY1, DEPARTMENT1);
	Employee employee2 = new Employee(ID2, SALARY2, DEPARTMENT1);
	Employee employee3 = new Employee(ID3, SALARY3, DEPARTMENT2);
	Company company;
	
	@BeforeEach
	void setCompany() {
		company = new Company( new Employee[] {employee1, employee2, employee3});
	}

	@Test
	void testAddEmployee() {
		Employee newEmployee = new Employee( 120,  1500, "QA");
		company.addEmployee(newEmployee);
		Employee[] expected = { newEmployee, employee1, employee2, employee3 };
		assertArrayEquals( expected, toArrayFromIterable( new Employee[expected.length], company));
		assertThrowsExactly(IllegalStateException.class,() -> company.addEmployee(employee1));

	}

	@Test
	void testIterator() {
		Employee[] expected = {  employee1, employee2, employee3 };
		assertArrayEquals( expected, toArrayFromIterable(new Employee[expected.length], company ));
	}

	@Test
	void testGetEmployee() {
		assertEquals(employee1, company.getEmployee(ID1));
		assertEquals(null, company.getEmployee(0));
	}

	@Test
	void testRemoveEmployee() {
		Employee[] expected = { employee1,  employee3 };
		Employee removedEmployee = company.removeEmployee(ID2);
		assertArrayEquals( expected, toArrayFromIterable(new Employee[expected.length], company ));
		assertEquals(removedEmployee, employee2 );
		assertThrowsExactly( NoSuchElementException.class, () -> company.removeEmployee(ID2));
		
	}

	@Test
	void testGetDepartmentBudget() {
		assertEquals(SALARY1+SALARY2, company.getDepartmentBudget( DEPARTMENT1) );
		assertEquals(SALARY3, company.getDepartmentBudget( DEPARTMENT2) );
		assertEquals(0, company.getDepartmentBudget( " ") );
	}
	
	protected <T> T[] toArrayFromIterable( T[] array, Iterable<T> iterable) {
		int index = 0;
		for ( T obj: iterable ) {
			array[index++] = obj;
		}
		return array;
	}

}
