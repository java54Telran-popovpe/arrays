package telran.util.test;

import java.util.Comparator;

public class EvenOddComparator implements Comparator<Integer> {

	@Override
	public int compare(Integer o1, Integer o2) {
		boolean iso1Even = ( o1 % 2 == 0 );
		boolean iso2Even = ( o2 % 2 == 0 );
		int result;
		//Four cases:
		//even against even -> return o1 - o2
		if ( iso1Even && iso2Even ) 
			result = o1 - o2;
		//even against odd -> return -1
		else if ( iso1Even && !iso2Even) 
			result = -1;
		//odd against even -> return 1
		else if ( !iso1Even && iso2Even )
			result = 1;
		//odd against odd -> return o2 - o1
		else 
			result = o2 - o1;
		return result;
	}

}
