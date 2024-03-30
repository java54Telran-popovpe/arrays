package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.util.Arrays;

class ArrayTests {
	Integer[] numbers = { 100, -3, 23, 4, 8, 41, 56, -7 };
	String[] strings = { "abc", "lmn", "123", null, "a" };
	String[] stringsMin = { "abc", "lmn", "123", "y" };
	@Test
	void indexOfTest() {
		assertEquals(1, Arrays.indexOf(strings, "lmn"));
		assertEquals(3, Arrays.indexOf(strings, null));
		assertEquals(-1, Arrays.indexOf(numbers, 150));
		assertEquals(4, Arrays.indexOf(numbers, 8));
	}
	@Test
	void minStringTest() {
		assertEquals("y", Arrays.min(stringsMin, new StringLengthComparator()));
		assertEquals("123", Arrays.min(stringsMin, new StringCompareToComparator()));
	}
	
	@Test
	void bubbleSortTest() {
		Integer[] expected = { 4, 8, 56, 100, 41, 23,-3,  -7 };
		Integer[] numbersCopy = java.util.Arrays.copyOf(numbers, numbers.length);
		Arrays.bubbleSort(numbersCopy, new EvenOddComparator());
		assertArrayEquals(expected, numbersCopy);
	}
	@Test 
	void searchTest() {
		Integer[] expectedEven  = { 100,  4, 8,  56, };
		assertArrayEquals(expectedEven, Arrays.search(numbers, el -> el % 2 == 0) );
		Integer[] expectedNegative  = { -3, -7 };
		assertArrayEquals(expectedNegative, Arrays.search(numbers, el -> el < 0 ) );
	}
	@Test 
	void removeIfTest() {
		Integer[] expectedWithoutEven =  { -3, 23, 41, -7 };
		assertArrayEquals( expectedWithoutEven, Arrays.removeIf(numbers, el -> el % 2 == 0 ));
		Integer[]  expectedWithoutNegative = { 100,  23, 4, 8, 41, 56 };
		assertArrayEquals( expectedWithoutNegative, Arrays.removeIf(numbers, el -> el < 0 ));
	}
	@Test
	void binarySearchTest() {
		Integer[] arrayForTest = { 10, 15, 20, 20, 20, 30, 30 };
		int key = 15;
		assertEquals( java.util.Arrays.binarySearch(arrayForTest, key), Arrays.binarySearch(arrayForTest, key, Integer::compare));
		key = 1;
		assertEquals( java.util.Arrays.binarySearch(arrayForTest, key), Arrays.binarySearch(arrayForTest, key, Integer::compare));
		key = 7;
		assertEquals( java.util.Arrays.binarySearch(arrayForTest, key), Arrays.binarySearch(arrayForTest, key, Integer::compare));
		key = 20;
		assertEquals( java.util.Arrays.binarySearch(arrayForTest, key), Arrays.binarySearch(arrayForTest, key, Integer::compare));
		key = 100;
		assertEquals( java.util.Arrays.binarySearch(arrayForTest, key), Arrays.binarySearch(arrayForTest, key, Integer::compare));
		key = 30;
		assertEquals( java.util.Arrays.binarySearch(arrayForTest, key), Arrays.binarySearch(arrayForTest, key, Integer::compare));
	}


}
