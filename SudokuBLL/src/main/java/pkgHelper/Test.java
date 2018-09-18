package pkgHelper;

import java.util.Arrays;

public class Test {
	public static void main(String[] args) {
		int col = 1;

	    int[][] arr4 = {{1,2,3}, {2,3,4}, {4,5,6}};

	    int [] thisCol = {2,3,5};

	    LatinSquare cd = new LatinSquare(arr4);
	    System.out.println(Arrays.toString(cd.getColumn(col)));
	    System.out.println(Arrays.equals(cd.getColumn(col), thisCol));
	}
}
