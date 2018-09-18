package pkgHelper;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class LatinSquareTest {

	@Test
	public void hasDuplicates_test1() {
		int[] arr1 = { 1, 2, 3 };
		int[][] myArray = { { 1, 2, 3 }, { 2, 3, 1 }, { 3, 2, 1 } };
		LatinSquare lq = new LatinSquare(myArray);
		assertFalse(lq.hasDuplicates(arr1));
	}

	@Test
	public void hasDuplicates_test2() {
		int[] arr = { 1, 1, 3, 4, 5 };
		LatinSquare lq = new LatinSquare();
		assertTrue(lq.hasDuplicates(arr));
	}

	@Test
	public void hasDuplicates_test3() {
		int[] arr = { 1, 2, 3, 4, 1 };
		LatinSquare lq = new LatinSquare();
		assertTrue(lq.hasDuplicates(arr));
	}

	@Test
	public void hasDuplicates_test4() {
		int[] arr = null;
		LatinSquare lq = new LatinSquare();
		assertFalse(lq.hasDuplicates(arr));
	}

	@Test
	public void doesElementExist_test1() {
		int[] arr = { 1, 2, 3, 4, 5 };
		int iValue = 3;
		LatinSquare lq = new LatinSquare();
		assertTrue(lq.doesElementExist(arr, iValue));
	}

	@Test
	public void doesElementExist_test2() {
		int[] arr = { 1, 2, 3, 4, 5 };
		int iValue = 99;
		LatinSquare lq = new LatinSquare();
		assertFalse(lq.doesElementExist(arr, iValue));
	}

	@Test
	public void doesElementExist_test3() {
		int[] arr = null;
		int iValue = 99;
		LatinSquare lq = new LatinSquare();
		assertFalse(lq.doesElementExist(arr, iValue));
	}

	@Test
	public void containsZero_test3() {

		int[][] arr3 = { { 0, 2, 3 }, { 2, 3, 4 }, { 3, 4, 5 } };

		LatinSquare bc = new LatinSquare(arr3);

		assertTrue(bc.containsZero(arr3));

	}

	@Test
	public void getColumn_test4() {

		int col = 1;

		int[][] arr4 = { { 1, 2, 3 }, { 2, 3, 4 }, { 4, 5, 6 } };

		int[] thisCol = { 2, 3, 5 };

		LatinSquare cd = new LatinSquare(arr4);

		assertTrue(Arrays.equals(cd.getColumn(col), thisCol));

	}

	@Test

	public void getRow_test5() {

		int row = 1;

		int[][] arr5 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };

		int[] thisRow = { 4, 5, 6 };

		LatinSquare ef = new LatinSquare(arr5);

		assertFalse(thisRow == ef.getRow(row));

	}

	@Test

	public void hasAllValues_test6() {

		LatinSquare gh = new LatinSquare();

		int[] arr6 = { 3, 2, 1 };

		int[] arr7 = { 1, 3, 2 };

		boolean hasAllValues = gh.hasAllValues(arr6, arr7);

		assertTrue(hasAllValues);
	}
}