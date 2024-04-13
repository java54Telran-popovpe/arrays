package telran.employees.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.*;

import telran.employees.*;
import telran.util.Arrays;

class CompanyTest {

	//There are two Wage Employee and one Manager in Development department
	//FIX ME there should be one object at least for all classes
	private static final String DEPARTMENT1 = "Development";
	private static final long ID1 = 122;
	private static final long ID2 = 123;
	private static final int SALARY1 = 1000;
	private static final int HOURS1 = 40;
	private static final int HOURS2 = 35;
	private static final int WAGE1 = 10;
	private static final long ID3 = 124;
	private static final int SALARY2 = 1100;
	private static final float FACTOR1 = 1.2f;

	WageEmployee employee1 = new WageEmployee(ID1, SALARY1, DEPARTMENT1, HOURS1, WAGE1);
	WageEmployee employee2 = new WageEmployee(ID2, SALARY1, DEPARTMENT1, HOURS2, WAGE1);
	Manager employee3 = new Manager(ID3, SALARY2, DEPARTMENT1, FACTOR1);
	
	//There are three salesmen and one manager in Sales Department
	private static final long ID4 = 125;
	private static final long ID5 = 126;
	private static final long ID6 = 127;
	private static final int SALARY3 = 900;
	private static final String DEPARTMENT2 = "Sales";

	private static final int HOURS3 = 15;
	private static final int WAGE2 = 8;
	private static final long SALES1 = 100000;
	private static final float PERCENT1 = 0.5f;
	private static final float PERCENT2 = 0.3f;
	private static final float PERCENT3 = 0.45f;
	private static final float FACTOR2 = 1.08f;
	SalesPerson employee4 = new SalesPerson(ID4, SALARY3, DEPARTMENT2, HOURS3, WAGE2, PERCENT1, SALES1);
	SalesPerson employee5 = new SalesPerson(ID5, SALARY3, DEPARTMENT2, HOURS3, WAGE2, PERCENT2, SALES1);
	SalesPerson employee6 = new SalesPerson(ID6, SALARY3, DEPARTMENT2, HOURS3, WAGE2, PERCENT3, SALES1);
	
	private static final long ID7 = 127;
	Manager employee7 = new Manager(ID7, SALARY2, DEPARTMENT2, FACTOR2);
	
	
	Company company;
	
	@BeforeEach
	void setCompany() {
		company = new Company( new Employee[] {employee1, employee2, employee3, employee4, employee5, employee6, employee7});
	}

	@Test
	void testAddEmployee() {
		Employee newEmployee = new Employee( 120,  1500, "QA");
		company.addEmployee(newEmployee);
		Employee[] expected = { (Employee)newEmployee, (Employee)employee1, (Employee)employee2, (Employee)employee3, (Employee)employee4, (Employee)employee5, (Employee)employee6, (Employee)employee7 };
		Employee[] actual = toArrayFromIterable( new Employee[expected.length], company);
		assertArrayEquals( expected, actual);
		assertThrowsExactly(IllegalStateException.class,() -> company.addEmployee(employee1));

	}

	@Test
	void testIterator() {
		Employee[] expected = {  employee1, employee2, employee3, employee4, employee5, employee6, employee7 };
		assertArrayEquals( expected, toArrayFromIterable(new Employee[expected.length], company ));
	}

	@Test
	void testGetEmployee() {
		assertEquals((Employee)employee1, company.getEmployee(ID1));
		assertEquals(null, company.getEmployee(0));
	}

	@Test
	void testRemoveEmployee() {
		Employee[] expected = { employee1, employee3, employee4, employee5, employee6, employee7 };
		Employee removedEmployee = company.removeEmployee(ID2);
		assertArrayEquals( expected, toArrayFromIterable(new Employee[expected.length], company ));
		assertEquals(removedEmployee, employee2 );
		assertThrowsExactly( NoSuchElementException.class, () -> company.removeEmployee(ID2));
		
	}

	@Test
	void testGetDepartmentBudget() {
	
		assertEquals(	
				SALARY1 +  HOURS1 * WAGE1 +
				SALARY1 +  HOURS2 * WAGE1 + 
				SALARY2 * FACTOR1
						, company.getDepartmentBudget( DEPARTMENT1) );
	
		assertEquals(	SALARY3 + HOURS3 * WAGE2 + SALES1 * PERCENT1 / 100 + 
						SALARY3 + HOURS3 * WAGE2 + SALES1 * PERCENT2 / 100 +
						SALARY3 + HOURS3 * WAGE2 + SALES1 * PERCENT3 / 100 +
						SALARY2 * FACTOR2, company.getDepartmentBudget( DEPARTMENT2) );
		assertEquals(0, company.getDepartmentBudget( " ") );
	}
	
	@Test
	void testGetDepartments() {
		String[] expected = { DEPARTMENT1, DEPARTMENT2 };
		String[] actual = company.getDepartments();
		assertEquals( expected.length, actual.length );
		for (String dep: expected ) {
			assertTrue( Arrays.indexOf(actual, dep) > -1);
		}
		company = new Company( new Employee[0] );
		actual = company.getDepartments();
		expected = new String[0];
		assertArrayEquals(expected, actual);
		
	}
	
	protected <T> T[] toArrayFromIterable( T[] array, Iterable<T> iterable) {
		int index = 0;
		for ( T obj: iterable ) {
			array[index++] = obj;
		}
		return array;
	}

}
