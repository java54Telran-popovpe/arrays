package telran.util;


import java.util.Comparator;
import java.util.function.Predicate;

import telran.employees.Employee;

public class Arrays {
	public static <T> int indexOf( T[] array, T element) {
		int i = 0;
		while ( i < array.length && !equals( array[i], element)) {
			i++;
		}
		return i == array.length ? -1 : i;
	}
	
	private static <T> boolean equals(T element1, T element2) {
		return element1 == null ? element1 == element2 : element1.equals(element2);
	}
	public static <T> T min( T[] array, Comparator<T> comp) {
		T result = null;
		if ( array != null && array.length > 0) {
			result = array[0];
			for ( int i = 1; i < array.length; i++ ) {
				if ( comp.compare(result, array[i]) > 0) {
					result = array[i];
				}
			}
		}
		return result;
	}
	
	public static <T> void bubbleSort( T[] array, Comparator<T> comp) {
		if ( array != null && array.length > 1) {
			boolean swaped;
			do {
				swaped = false;
				for ( int i = 0; i < array.length - 1; i++ ) {
					if ( comp.compare(array[ i ], array[i + 1]) > 0) {
						T temp = array[ i ];
						array[ i ] = array[ i + 1 ];
						array[ i + 1 ] = temp;
						swaped = true;
					}
				}
			} while( swaped );
			
		}
	}
	
	public static <T extends Comparable<T>> void bubbleSort( T[] array) {
		bubbleSort( array, (a,b) -> a.compareTo(b));
		return;
	}
	
	public static <T> T[] insertSorted (T[] array, T element, Comparator<T> comp) {
		int resultOfSearch = -1;
		if ( ( resultOfSearch = Arrays.binarySearch(array, element, comp) ) > -1 ) 
			throw new IllegalStateException();
		T[] result =java.util.Arrays.copyOf(array, array.length + 1);;
		int pointOfInsert = -(resultOfSearch + 1);
		java.lang.System.arraycopy(array, 0, result, 0, pointOfInsert);
		result[pointOfInsert] = element;
		java.lang.System.arraycopy(array, pointOfInsert, result, pointOfInsert + 1, array.length - pointOfInsert );
		return  result;
	} 
	
	public static <T> T[] search(T[] array, Predicate<T> predicate) {
		T[] arrResult = java.util.Arrays.copyOf(array, array.length);
		int index = 0;
		for ( int i = 0; i < array.length; i++ ) {
			if ( predicate.test(array[ i ]) ) {
				arrResult[ index++ ] = array[ i ];
			}
		}
		return java.util.Arrays.copyOf(arrResult, index );
	}
	
	public static <T> T[] removeIf( T[] array, Predicate<T> predicate ) {
		return search(array, e -> !predicate.test(e));
	}
	
	public static <T> int binarySearch( T[] array, T key, Comparator<T> comp){
		int result = -1;
		int leftIndex = 0;
		int rightIndex = array.length - 1;
		while ( leftIndex <= rightIndex && result < 0) {
			int middle = (leftIndex + rightIndex ) / 2;
			int cmpResult = comp.compare(key, array[ middle ]);
			if ( cmpResult == 0 ) {
				result = middle;
			} else if ( cmpResult > 0 ) {
				leftIndex = middle + 1;
			} else {
				rightIndex = middle - 1;
			}
			middle = (leftIndex + rightIndex ) / 2;
		}
		if ( result < 0)
			result = -leftIndex - 1;
		return result;
	}
	public static <T> T[] add( T[] array, T element) {
		T[] result = java.util.Arrays.copyOf(array, array.length + 1);
		result[ array.length ] = element;
		return result;
	}
	
	public static <T> T[] copy(T[] array) {
		return java.util.Arrays.copyOf(array, array.length);
	}
}
